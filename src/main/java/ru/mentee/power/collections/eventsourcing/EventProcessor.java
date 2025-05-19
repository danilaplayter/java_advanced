package ru.mentee.power.collections.eventsourcing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import ru.mentee.power.collections.SimpleHashMap;
import ru.mentee.power.collections.SimpleHashSet;
import ru.mentee.power.collections.SimpleLinkedList;
import ru.mentee.power.collections.SimplePriorityQueue;

public class EventProcessor {

  private SimplePriorityQueue<Event> eventQueue;
  private SimpleHashSet<String> processedEventIds;
  private SimpleHashMap<String, String> processingResults;
  private SimpleLinkedList<Event> arrivalLog;

  private static final Comparator<Event> EVENT_COMPARATOR =
      Comparator.comparingInt(Event::getPriority);

  public EventProcessor() {
    this.eventQueue = new SimplePriorityQueue<>(EVENT_COMPARATOR);
    this.processedEventIds = new SimpleHashSet<>();
    this.processingResults = new SimpleHashMap<>();
    this.arrivalLog = new SimpleLinkedList<>();
  }

  public boolean submitEvent(Event event) {
    if (event == null) {
      return false;
    }
    arrivalLog.add(event);
    if (!processedEventIds.contains(event.getEventId())) {
      return eventQueue.offer(event);
    }
    return true;
  }

  public Optional<Event> getNextEventToProcess() {
    while (!eventQueue.isEmpty()) {
      Event event = eventQueue.poll();
      if (event != null && !isProcessed(event.getEventId())) {
        return Optional.of(event);
      }
    }
    return Optional.empty();
  }

  public void markAsProcessed(Event event, String result) {
    if (event == null || result == null) {
      return;
    }
    processedEventIds.add(event.getEventId());
    processingResults.put(event.getEventId(), result);
  }

  public boolean isProcessed(String eventId) {
    return processedEventIds.contains(eventId);
  }

  public Optional<String> getResult(String eventId) {
    return Optional.ofNullable(processingResults.get(eventId));
  }

  public List<Event> getFullEventLog() {
    List<Event> result = new ArrayList<>();
    for (int i = 0; i < arrivalLog.size(); i++) {
      result.add(arrivalLog.get(i));
    }
    return result;
  }

  public int getPendingEventCount() {
    return eventQueue.size();
  }
}