package ru.mentee.power.collections.eventsourcing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@DisplayName("Тесты для EventProcessor (Event Sourcing)")
class EventProcessorTest {

  private EventProcessor processor;
  private Event highPriorityEvent;
  private Event mediumPriorityEvent1;
  private Event mediumPriorityEvent2;
  private Event lowPriorityEvent;

  @BeforeEach
  void setUp() {
    processor = new EventProcessor();
    highPriorityEvent = new Event("EVT-HIGH-01", 1, "High priority");
    mediumPriorityEvent1 = new Event("EVT-MED-01", 5, "Medium priority 1");
    mediumPriorityEvent2 = new Event("EVT-MED-02", 5, "Medium priority 2");
    lowPriorityEvent = new Event("EVT-LOW-01", 10, "Low priority");
  }

  @Test
  @DisplayName("submitEvent должен добавлять в лог и очередь (если не обработано)")
  void submitEventShouldAddToLogAndQueueIfNotProcessed() {
    assertThat(processor.submitEvent(highPriorityEvent)).isTrue();
    assertThat(processor.getPendingEventCount()).isEqualTo(1);
    assertThat(processor.getFullEventLog()).containsExactly(highPriorityEvent);
  }

  @Test
  @DisplayName("submitEvent должен добавлять в лог, но не в очередь, если обработано")
  void submitEventShouldAddToLogButNotQueueIfProcessed() {
    processor.submitEvent(highPriorityEvent);
    processor.markAsProcessed(highPriorityEvent, "Done");
    assertThat(processor.submitEvent(highPriorityEvent)).isTrue();
    assertThat(processor.getPendingEventCount()).isEqualTo(1);
    assertThat(processor.getFullEventLog()).hasSize(2);
  }

  @Test
  @DisplayName("getNextEventToProcess должен возвращать событие с наивысшим приоритетом")
  void getNextEventToProcessShouldReturnHighestPriority() {
    processor.submitEvent(lowPriorityEvent);
    processor.submitEvent(highPriorityEvent);
    processor.submitEvent(mediumPriorityEvent1);

    Optional<Event> nextEvent = processor.getNextEventToProcess();
    assertThat(nextEvent).isPresent().contains(highPriorityEvent);
  }

  @Test
  @DisplayName("getNextEventToProcess должен возвращать события с одинаковым приоритетом")
  void getNextEventToProcessShouldReturnEventsWithSamePriority() {
    processor.submitEvent(mediumPriorityEvent1);
    processor.submitEvent(mediumPriorityEvent2);

    Optional<Event> first = processor.getNextEventToProcess();
    Optional<Event> second = processor.getNextEventToProcess();

    assertThat(List.of(first.orElse(null), second.orElse(null)))
        .containsExactlyInAnyOrder(mediumPriorityEvent1, mediumPriorityEvent2);
  }

  @Test
  @DisplayName("getNextEventToProcess должен пропускать уже обработанные события")
  void getNextEventToProcessShouldSkipProcessedEvents() {
    processor.submitEvent(highPriorityEvent);
    processor.submitEvent(mediumPriorityEvent1);
    processor.markAsProcessed(highPriorityEvent, "Done");

    Optional<Event> nextEvent = processor.getNextEventToProcess();
    assertThat(nextEvent).isPresent().contains(mediumPriorityEvent1);
  }

  @Test
  @DisplayName("getNextEventToProcess должен возвращать Optional.empty для пустой очереди")
  void getNextEventToProcessShouldReturnEmptyOptional() {
    assertThat(processor.getNextEventToProcess()).isEmpty();
  }

  @Test
  @DisplayName("getFullEventLog должен возвращать все добавленные события в порядке поступления")
  void getFullEventLogShouldReturnAllSubmittedEventsInOrder() {
    processor.submitEvent(highPriorityEvent);
    processor.submitEvent(mediumPriorityEvent1);
    processor.submitEvent(lowPriorityEvent);

    List<Event> log = processor.getFullEventLog();
    assertThat(log).containsExactly(highPriorityEvent, mediumPriorityEvent1, lowPriorityEvent);
  }
}