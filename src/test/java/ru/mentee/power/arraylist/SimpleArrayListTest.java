package ru.mentee.power.collections.arraylist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для SimpleArrayList")
class SimpleArrayListTest {

  private SimpleArrayList<String> list;

  @BeforeEach
  void setUp() {
    list = new SimpleArrayList<>();
  }

  @Test
  @DisplayName("Добавление и получение элементов")
  void shouldAddAndGetElements() {
    // Act
    list.add("A");
    list.add("B");
    list.add("C");

    // Assert
    assertThat(list.size()).isEqualTo(3); // Простая проверка размера
    assertThat(list.get(0)).isEqualTo("A");
    assertThat(list.get(1)).isEqualTo("B");
    assertThat(list.get(2)).isEqualTo("C");
  }

  @Test
  @DisplayName("Проверка роста внутреннего массива")
  void shouldGrowInternalArray() {
    SimpleArrayList<Integer> intList = new SimpleArrayList<>(2);
    intList.add(1);
    intList.add(2);
    intList.add(3); // Превысили начальную емкость

    assertThat(intList.size()).isEqualTo(3); // Проверяем размер
    assertThat(intList.get(0)).isEqualTo(1);
    assertThat(intList.get(1)).isEqualTo(2);
    assertThat(intList.get(2)).isEqualTo(3);
  }

  @Test
  @DisplayName("Вставка элемента в середину")
  void shouldAddElementInTheMiddle() {
    list.add("A");
    list.add("C");
    list.add(1, "B"); // Вставка между "A" и "C"

    assertThat(list.size()).isEqualTo(3);
    assertThat(list.get(0)).isEqualTo("A");
    assertThat(list.get(1)).isEqualTo("B");
    assertThat(list.get(2)).isEqualTo("C");
  }

  @Test
  @DisplayName("Удаление элемента по индексу")
  void shouldRemoveElementByIndex() {
    list.add("A");
    list.add("B");
    list.add("C");
    String removed = list.remove(1); // Удаляем "B"

    assertThat(removed).isEqualTo("B");
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo("A");
    assertThat(list.get(1)).isEqualTo("C");
  }

  @Test
  @DisplayName("Удаление элемента по значению")
  void shouldRemoveElementByValue() {
    list.add("A");
    list.add("B");
    list.add("C");
    boolean isRemoved = list.remove("B");

    assertThat(isRemoved).isTrue();
    assertThat(list.size()).isEqualTo(2);
    assertThat(list.get(0)).isEqualTo("A");
    assertThat(list.get(1)).isEqualTo("C");
  }

  @Test
  @DisplayName("Поиск индекса элемента")
  void shouldFindIndexOfElement() {
    list.add("A");
    list.add("B");
    list.add("A");

    assertThat(list.indexOf("A")).isEqualTo(0); // Первое вхождение
    assertThat(list.lastIndexOf("A")).isEqualTo(2); // Последнее вхождение
    assertThat(list.indexOf("D")).isEqualTo(-1); // Несуществующий элемент
  }

  @Test
  @DisplayName("Проверка наличия элемента")
  void shouldCheckIfElementExists() {
    list.add("A");
    assertThat(list.contains("A")).isTrue();
    assertThat(list.contains("B")).isFalse();
  }

  @Test
  @DisplayName("Очистка списка")
  void shouldClearList() {
    list.add("A");
    list.add("B");
    list.clear();

    assertThat(list.size()).isEqualTo(0); // Список пуст
    assertThat(list.isEmpty()).isTrue();
  }

  @Test
  @DisplayName("Замена элемента по индексу")
  void shouldSetElementAtIndex() {
    list.add("A");
    list.add("B");
    String oldValue = list.set(1, "C"); // Заменяем "B" на "C"

    assertThat(oldValue).isEqualTo("B");
    assertThat(list.get(1)).isEqualTo("C");
  }

  @Test
  @DisplayName("toString должен возвращать корректное представление")
  void toStringShouldReturnCorrectRepresentation() {
    list.add("A");
    list.add("B");

    assertThat(list.toString()).isEqualTo("[A, B]");
  }

  @Test
  @DisplayName("toArray должен возвращать массив с элементами")
  void toArrayShouldReturnArrayWithElements() {
    list.add("A");
    list.add("B");
    Object[] array = list.toArray();

    assertThat(array).hasSize(2); // Проверка массива через AssertJ
    assertThat(array).containsExactly("A", "B");
  }

  @Test
  @DisplayName("Обработка некорректных индексов")
  void shouldThrowIndexOutOfBoundsException() {
    // Пустой список
    assertThatThrownBy(() -> list.get(0)).isInstanceOf(IndexOutOfBoundsException.class);

    // Список с одним элементом
    list.add("A");
    assertThatThrownBy(() -> list.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    assertThatThrownBy(() -> list.set(1, "B")).isInstanceOf(IndexOutOfBoundsException.class);
    assertThatThrownBy(() -> list.add(2, "C")).isInstanceOf(IndexOutOfBoundsException.class);
    assertThatThrownBy(() -> list.remove(1)).isInstanceOf(IndexOutOfBoundsException.class);
  }

  @Test
  @DisplayName("Конструктор должен выбрасывать исключение при отрицательной емкости")
  void constructorShouldThrowExceptionForNegativeCapacity() {
    assertThatThrownBy(() -> new SimpleArrayList<>(-1)).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Illegal Capacity: -1");
  }
}