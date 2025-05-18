package ru.mentee.power.collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для SimplePriorityQueue")
class SimplePriorityQueueTest {

  private SimplePriorityQueue<Integer> minHeap;
  private SimplePriorityQueue<Integer> maxHeap;

  @BeforeEach
  void setUp() {
    minHeap = new SimplePriorityQueue<>();
    maxHeap = new SimplePriorityQueue<>(Comparator.reverseOrder());
  }

  @Test
  @DisplayName("offer() и poll() должны сохранять порядок min-heap")
  void offerAndPollShouldMaintainMinHeapOrder() {
    minHeap.offer(5);
    minHeap.offer(1);
    minHeap.offer(3);
    minHeap.offer(2);
    minHeap.offer(4);

    assertThat(minHeap.poll()).isEqualTo(1);
    assertThat(minHeap.poll()).isEqualTo(2);
    assertThat(minHeap.poll()).isEqualTo(3);
    assertThat(minHeap.poll()).isEqualTo(4);
    assertThat(minHeap.poll()).isEqualTo(5);
  }

  @Test
  @DisplayName("offer() и poll() должны сохранять порядок max-heap")
  void offerAndPollShouldMaintainMaxHeapOrder() {
    maxHeap.offer(1);
    maxHeap.offer(5);
    maxHeap.offer(3);
    maxHeap.offer(2);
    maxHeap.offer(4);

    assertThat(maxHeap.poll()).isEqualTo(5);
    assertThat(maxHeap.poll()).isEqualTo(4);
    assertThat(maxHeap.poll()).isEqualTo(3);
    assertThat(maxHeap.poll()).isEqualTo(2);
    assertThat(maxHeap.poll()).isEqualTo(1);
  }

  @Test
  @DisplayName("peek() должен возвращать минимальный/максимальный элемент без удаления")
  void peekShouldReturnTopElementWithoutRemoval() {
    minHeap.offer(3);
    minHeap.offer(1);
    minHeap.offer(2);

    assertThat(minHeap.peek()).isEqualTo(1);
    assertThat(minHeap.size()).isEqualTo(3);
    assertThat(minHeap.peek()).isEqualTo(1);
  }

  @Test
  @DisplayName("poll() должен возвращать null для пустой очереди")
  void pollShouldReturnNullForEmptyQueue() {
    assertThat(minHeap.poll()).isNull();
  }

  @Test
  @DisplayName("peek() должен возвращать null для пустой очереди")
  void peekShouldReturnNullForEmptyQueue() {
    assertThat(minHeap.peek()).isNull();
  }

  @Test
  @DisplayName("size() и isEmpty() должны работать корректно")
  void sizeAndIsEmptyShouldWorkCorrectly() {
    assertThat(minHeap.isEmpty()).isTrue();
    assertThat(minHeap.size()).isEqualTo(0);

    minHeap.offer(1);
    assertThat(minHeap.isEmpty()).isFalse();
    assertThat(minHeap.size()).isEqualTo(1);

    minHeap.poll();
    assertThat(minHeap.isEmpty()).isTrue();
    assertThat(minHeap.size()).isEqualTo(0);
  }

  @Test
  @DisplayName("offer() должен бросать NullPointerException при добавлении null")
  void offerShouldThrowNPEForNull() {
    assertThatThrownBy(() -> minHeap.offer(null)).isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("Конструктор должен бросать исключение при некорректной емкости")
  void constructorShouldThrowExceptionForInvalidCapacity() {
    assertThatThrownBy(() -> new SimplePriorityQueue<>(0)).isInstanceOf(
        IllegalArgumentException.class);
    assertThatThrownBy(() -> new SimplePriorityQueue<>(-1)).isInstanceOf(
        IllegalArgumentException.class);
  }
}