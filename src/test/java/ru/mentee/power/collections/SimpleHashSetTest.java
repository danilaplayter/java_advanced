package ru.mentee.power.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SimpleHashSet")
class SimpleHashSetTest {

  private SimpleHashSet<String> set;

  @BeforeEach
  void setUp() {
    set = new SimpleHashSet<>();
    set.add("one");
    set.add("two");
    set.add("three");
  }

  @Test
  @DisplayName("add() должен добавлять уникальные элементы и возвращать true")
  void addShouldAddUniqueElementsAndReturnTrue() {
    boolean added = set.add("four");
    assertThat(added).isTrue();
    assertThat(set.contains("four")).isTrue();
    assertThat(set.size()).isEqualTo(4);
  }

  @Test
  @DisplayName("add() не должен добавлять дубликаты и возвращать false")
  void addShouldNotAddDuplicatesAndReturnFalse() {
    boolean added = set.add("one");
    assertThat(added).isFalse();
    assertThat(set.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("remove() должен удалять существующий элемент и возвращать true")
  void removeShouldDeleteExistingElementAndReturnTrue() {
    boolean removed = set.remove("two");
    assertThat(removed).isTrue();
    assertThat(set.contains("two")).isFalse();
    assertThat(set.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("remove() должен возвращать false для несуществующего элемента")
  void removeShouldReturnFalseForNonExistingElement() {
    boolean removed = set.remove("four");
    assertThat(removed).isFalse();
    assertThat(set.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("contains() должен проверять наличие элемента")
  void containsShouldCheckExistence() {
    assertThat(set.contains("one")).isTrue();
    assertThat(set.contains("four")).isFalse();
  }

  @Test
  @DisplayName("clear() должен удалять все элементы")
  void clearShouldRemoveAllElements() {
    set.clear();
    assertThat(set.isEmpty()).isTrue();
    assertThat(set.size()).isEqualTo(0);
    assertThat(set.contains("one")).isFalse();
  }

  @Test
  @DisplayName("toString() должен возвращать корректное строковое представление")
  void toStringShouldReturnCorrectRepresentation() {
    String str = set.toString();
    assertThat(str).startsWith("[");
    assertThat(str).endsWith("]");
    assertThat(str).contains("one", "two", "three");
  }
}