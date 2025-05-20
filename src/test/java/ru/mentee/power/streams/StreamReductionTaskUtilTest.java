package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.OptionalLong;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class StreamReductionTaskUtilTest {

  @Test
  @DisplayName("Задача 1: Должен печатать числа, кратные 10")
  void shouldPrintNumbersDivisibleBy10() {
    IntStream inputStream = IntStream.of(5, 10, 15, 20, 25, 30);
    // Перехватываем вывод в консоль
    PrintStream originalOut = System.out;
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bos));
    try {
      StreamReductionTaskUtil.printNumbersDivisibleBy10(inputStream);
      String output = bos.toString();
      assertThat(output).contains("10", "20", "30")
          .doesNotContain("5", "15", "25");
      assertThat(output.lines().count()).isEqualTo(3);
    } finally {
      System.setOut(originalOut); // Восстанавливаем оригинальный System.out
    }
  }

  @Test
  @DisplayName("Задача 2: Должен считать числа, кратные 3")
  void shouldCountNumbersDivisibleBy3() {
    IntStream inputStream = IntStream.rangeClosed(1, 10); // 3, 6, 9
    long count = StreamReductionTaskUtil.countNumbersDivisibleBy3(inputStream);
    assertThat(count).isEqualTo(3);
  }

  @Test
  @DisplayName("Задача 3: Должен собирать нечетные числа")
  void shouldCollectOddNumbers() {
    IntStream inputStream = IntStream.rangeClosed(1, 6); // 1, 3, 5
    int[] result = StreamReductionTaskUtil.collectOddNumbers(inputStream);
    assertThat(result).containsExactly(1, 3, 5);
  }

  @Test
  @DisplayName("Задача 4: Должен вычислять сумму")
  void shouldCalculateSum() {
    IntStream inputStream = IntStream.of(1, 2, 3, 4, 5);
    int sum = StreamReductionTaskUtil.calculateSum(inputStream);
    assertThat(sum).isEqualTo(15);
  }

  @Test
  @DisplayName("Задача 5: Должен вычислять произведение")
  void shouldCalculateProduct1to10() {
    IntStream inputStream = IntStream.rangeClosed(1, 5); // 1*2*3*4*5 = 120
    OptionalLong product = StreamReductionTaskUtil.calculateProduct1to10(inputStream);
    assertThat(product).hasValue(120);
  }

  @Test
  @DisplayName("Задача 5: Произведение для пустого потока")
  void shouldCalculateProductEmptyStream() {
    IntStream inputStream = IntStream.empty();
    OptionalLong product = StreamReductionTaskUtil.calculateProduct1to10(inputStream);
    assertThat(product).isEmpty();
  }
}