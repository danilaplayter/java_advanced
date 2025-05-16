package ru.mentee.power.collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для SimpleLinkedList")
class SimpleLinkedListTest {
  private SimpleLinkedList<Integer> list;

  @BeforeEach
  void setUp() {
    list = new SimpleLinkedList<>();
  }

  @Test
  @DisplayName("add() и size() должны работать корректно")
  void shouldAddElementsAndGetCorrectSize() {
    assertThat(list.size()).isEqualTo(0);
    assertThat(list.isEmpty()).isTrue();

    list.add(1);
    list.add(2);
    list.add(3);

    assertThat(list.size()).isEqualTo(3);
    assertThat(list.isEmpty()).isFalse();
  }

  @Test
  @DisplayName("addFirst() и getFirst() должны работать корректно")
  void shouldAddAndGetFirst() {
    list.addFirst(3);
    list.addFirst(2);
    list.addFirst(1);

    assertThat(list.getFirst()).isEqualTo(1);
    assertThat(list.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("addLast() и getLast() должны работать корректно")
  void shouldAddAndGetLast() {
    list.addLast(1);
    list.addLast(2);
    list.addLast(3);

    assertThat(list.getLast()).isEqualTo(3);
    assertThat(list.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("get() должен возвращать правильный элемент по индексу")
  void shouldGetElementByIndex() {
    list.add(1);
    list.add(2);
    list.add(3);

    assertThat(list.get(0)).isEqualTo(1);
    assertThat(list.get(1)).isEqualTo(2);
    assertThat(list.get(2)).isEqualTo(3);
  }

  @Test
  @DisplayName("get() должен выбрасывать исключение для некорректного индекса")
  void getShouldThrowExceptionForInvalidIndex() {
    list.add(1);

    assertThatThrownBy(() -> list.get(-1))
        .isInstanceOf(IndexOutOfBoundsException.class);

    assertThatThrownBy(() -> list.get(1))
        .isInstanceOf(IndexOutOfBoundsException.class);
  }

  @Test
  @DisplayName("getFirst()/getLast() должны выбрасывать исключение для пустого списка")
  void getFirstLastShouldThrowExceptionOnEmptyList() {
    assertThatThrownBy(() -> list.getFirst())
        .isInstanceOf(NoSuchElementException.class);

    assertThatThrownBy(() -> list.getLast())
        .isInstanceOf(NoSuchElementException.class);
  }

  @Test
  @DisplayName("remove(index) должен удалять элемент и возвращать его")
  void removeByIndexShouldRemoveAndReturnElement() {
    list.add(1);
    list.add(2);
    list.add(3);

    Integer removed = list.remove(1);

    assertThat(removed).isEqualTo(2);

    assertThat(list.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("remove(Object) должен удалять элемент по значению")
  void removeByObjectShouldRemoveElement() {
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(null);

    assertThat(list.remove(Integer.valueOf(2))).isTrue();
    assertThat(list.size()).isEqualTo(3);
    assertThat(list.contains(2)).isFalse();

    assertThat(list.remove(null)).isTrue();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.contains(null)).isFalse();

  }

  @Test
  @DisplayName("removeFirst()/removeLast() должны удалять элементы с концов")
  void removeFirstLastShouldWork() {
    list.add(1);
    list.add(2);
    list.add(3);

    assertThat(list.removeFirst()).isEqualTo(1);
    assertThat(list.size()).isEqualTo(2);

    assertThat(list.removeLast()).isEqualTo(3);
    assertThat(list.size()).isEqualTo(1);
    assertThat(list.getFirst()).isEqualTo(2);
    assertThat(list.getLast()).isEqualTo(2);
  }

  @Test
  @DisplayName("removeFirst()/removeLast() должны выбрасывать исключение для пустого списка")
  void removeFirstLastShouldThrowExceptionOnEmptyList() {
    assertThatThrownBy(() -> list.removeFirst())
        .isInstanceOf(NoSuchElementException.class);

    assertThatThrownBy(() -> list.removeLast())
        .isInstanceOf(NoSuchElementException.class);
  }

  @Test
  @DisplayName("clear() должен очищать список")
  void clearShouldEmptyTheList() {
    list.add(1);
    list.add(2);
    list.add(3);

    list.clear();

    assertThat(list.size()).isEqualTo(0);
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("contains() должен проверять наличие элемента")
  void containsShouldCheckExistence() {
    list.add(1);
    list.add(2);
    list.add(null);

    assertThat(list.contains(1)).isTrue();
    assertThat(list.contains(2)).isTrue();
    assertThat(list.contains(null)).isTrue();
    assertThat(list.contains(3)).isFalse();
  }

  @Test
  @DisplayName("toString() должен возвращать корректное представление")
  void toStringShouldReturnCorrectString() {
    assertThat(list.toString()).isEqualTo("[]");

    list.add(1);
    list.add(2);
    list.add(3);

    assertThat(list.toString()).isEqualTo("[1, 2, 3]");
  }
}