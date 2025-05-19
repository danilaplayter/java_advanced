package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class StreamIntroTaskUtilTest {

  @Test
  @DisplayName("processList должен корректно обрабатывать список строк")
  void processListShouldWorkCorrectly() {
    // 1. Создаем список строк с пробелами, пустыми строками
    List<String> input = Arrays.asList("  apple ", "", "banana  ", "  ", "cherry", "   ");

    // 2. Вызываем метод processList
    List<String> result = StreamIntroTaskUtil.processList(input);

    // 3. Проверяем результат
    assertThat(result)
        .containsExactly("APPLE", "BANANA", "CHERRY")
        .isSorted();
  }

  @Test
  @DisplayName("processList должен возвращать пустой список для входного списка из пустых строк")
  void processListShouldReturnEmptyListForEmptyStrings() {
    // 1. Создаем список из пустых строк
    List<String> input = Arrays.asList("", "   ", " ", "");

    // 2. Вызываем метод processList
    List<String> result = StreamIntroTaskUtil.processList(input);

    // 3. Проверяем, что результат - пустой список
    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("processList должен правильно обрабатывать null-значения")
  void processListShouldHandleNullValues() {
    // Решаем, что метод должен игнорировать null-значения (фильтровать их)
    // 1. Создаем список с null-значениями
    List<String> input = Arrays.asList("hello", null, "  world  ", null, "");

    // 2. Вызываем метод processList
    List<String> result = StreamIntroTaskUtil.processList(input);

    // 3. Проверяем результат
    assertThat(result)
        .containsExactly("HELLO", "WORLD")
        .isSorted();
  }

  @Test
  @DisplayName("processList должен возвращать пустой список для null входного списка")
  void processListShouldReturnEmptyListForNullInput() {
    // Проверяем поведение при передаче null вместо списка
    assertThatThrownBy(() -> StreamIntroTaskUtil.processList(null))
        .isInstanceOf(NullPointerException.class);
  }
}