package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import static org.assertj.core.api.Assertions.*;

class PrimitiveStreamTaskUtilTest {

  @Test
  @DisplayName("Задача 1: Сумма положительных чисел")
  void calculateSumOfPositives_shouldReturnCorrectSum() {
    int[] data = {10, -5, 20, 0, 15};
    int sum = PrimitiveStreamTaskUtil.calculateSumOfPositives(data);
    assertThat(sum).isEqualTo(45);
  }

  @Test
  @DisplayName("Задача 1: Сумма положительных для пустого массива")
  void calculateSumOfPositives_emptyArray_shouldReturnZero() {
    int[] data = {};
    int sum = PrimitiveStreamTaskUtil.calculateSumOfPositives(data);
    assertThat(sum).isEqualTo(0);
  }

  @Test
  @DisplayName("Задача 2: Среднее значение")
  void calculateAverage_shouldReturnCorrectAverage() {
    int[] data = {1, 2, 3, 4, 5};
    OptionalDouble avg = PrimitiveStreamTaskUtil.calculateAverage(data);
    assertThat(avg).isPresent().hasValue(3.0);
  }

  @Test
  @DisplayName("Задача 2: Среднее для пустого массива")
  void calculateAverage_emptyArray_shouldReturnEmptyOptional() {
    int[] data = {};
    OptionalDouble avg = PrimitiveStreamTaskUtil.calculateAverage(data);
    assertThat(avg).isEmpty();
  }

  @Test
  @DisplayName("Задача 3: Квадраты уникальных чисел")
  void getSquaresOfUniqueNumbers_shouldReturnCorrectSquares() {
    int[] data = {1, 2, 1, 3, 2, 4};
    List<Integer> squares = PrimitiveStreamTaskUtil.getSquaresOfUniqueNumbers(data);
    assertThat(squares).containsExactlyInAnyOrder(1, 4, 9, 16);
  }

  @Test
  @DisplayName("Задача 3: Квадраты для пустого массива")
  void getSquaresOfUniqueNumbers_emptyArray_shouldReturnEmptyList() {
    int[] data = {};
    List<Integer> squares = PrimitiveStreamTaskUtil.getSquaresOfUniqueNumbers(data);
    assertThat(squares).isEmpty();
  }

  @Test
  @DisplayName("Задача 4: Статистика для чисел > 10")
  void getStatsForNumbersGreaterThan10_shouldReturnCorrectStats() {
    int[] data = {5, 15, 20, 10, 25};
    IntSummaryStatistics stats = PrimitiveStreamTaskUtil.getStatsForNumbersGreaterThan10(data);
    assertThat(stats.getCount()).isEqualTo(3);
    assertThat(stats.getMin()).isEqualTo(15);
    assertThat(stats.getMax()).isEqualTo(25);
    assertThat(stats.getSum()).isEqualTo(60);
    assertThat(stats.getAverage()).isEqualTo(20.0);
  }

  @Test
  @DisplayName("Задача 4: Статистика когда нет чисел > 10")
  void getStatsForNumbersGreaterThan10_noNumbers_shouldReturnZeroStats() {
    int[] data = {1, 2, 3, 10};
    IntSummaryStatistics stats = PrimitiveStreamTaskUtil.getStatsForNumbersGreaterThan10(data);
    assertThat(stats.getCount()).isEqualTo(0);
  }

  @Test
  @DisplayName("Дополнительный метод: Проверка на отрицательные числа")
  void hasNegativeNumbers_shouldReturnCorrectResult() {
    int[] withNegatives = {1, -2, 3};
    int[] withoutNegatives = {1, 2, 3};

    assertThat(PrimitiveStreamTaskUtil.hasNegativeNumbers(withNegatives)).isTrue();
    assertThat(PrimitiveStreamTaskUtil.hasNegativeNumbers(withoutNegatives)).isFalse();
  }

  @Test
  @DisplayName("Дополнительный метод: Максимальное четное число")
  void findMaxEvenNumber_shouldReturnCorrectValue() {
    int[] data = {1, 4, 3, 8, 5, 2};
    OptionalInt maxEven = PrimitiveStreamTaskUtil.findMaxEvenNumber(data);
    assertThat(maxEven).isPresent().hasValue(8);
  }

  @Test
  @DisplayName("Дополнительный метод: Максимальное четное для массива без четных")
  void findMaxEvenNumber_noEvens_shouldReturnEmpty() {
    int[] data = {1, 3, 5};
    OptionalInt maxEven = PrimitiveStreamTaskUtil.findMaxEvenNumber(data);
    assertThat(maxEven).isEmpty();
  }
}