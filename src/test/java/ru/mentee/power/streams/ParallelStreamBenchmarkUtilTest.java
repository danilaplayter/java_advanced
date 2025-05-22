package ru.mentee.power.streams;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

class ParallelStreamBenchmarkUtilTest {

  @Test
  @DisplayName("complexCalculation должен возвращать одинаковые результаты для одинаковых входных данных")
  void complexCalculation_ShouldReturnConsistentResults() {
    long input = 42L;
    long result1 = ParallelStreamBenchmarkUtil.complexCalculation(input);
    long result2 = ParallelStreamBenchmarkUtil.complexCalculation(input);

    assertThat(result1).isEqualTo(result2);
  }

  @Test
  @DisplayName("Последовательная и параллельная обработка должны давать одинаковый результат")
  void sequentialAndParallelProcessing_ShouldReturnSameResult() {
    List<Long> data = LongStream.rangeClosed(1, 100)
        .boxed()
        .collect(Collectors.toList());

    long sequentialResult = ParallelStreamBenchmarkUtil.processDataSequential(data);
    long parallelResult = ParallelStreamBenchmarkUtil.processDataParallel(data);

    assertThat(parallelResult).isEqualTo(sequentialResult);
  }

  @Test
  @DisplayName("Проверка параллельного processData с разными размерами данных")
  void processDataParallel_WithDifferentDataSizes() {
    // Пустой список
    assertThat(ParallelStreamBenchmarkUtil.processDataParallel(List.of())).isEqualTo(0L);

    // Маленький список
    List<Long> smallData = LongStream.rangeClosed(1, 10)
        .boxed()
        .collect(Collectors.toList());
    assertThat(ParallelStreamBenchmarkUtil.processDataParallel(smallData))
        .isEqualTo(ParallelStreamBenchmarkUtil.processDataSequential(smallData));

    // Большой список
    List<Long> largeData = LongStream.rangeClosed(1, 10000)
        .boxed()
        .collect(Collectors.toList());
    assertThat(ParallelStreamBenchmarkUtil.processDataParallel(largeData))
        .isEqualTo(ParallelStreamBenchmarkUtil.processDataSequential(largeData));
  }

  @Test
  @DisplayName("Проверка последовательной обработки")
  void processDataSequential_ShouldReturnCorrectResult() {
    List<Long> data = List.of(1L, 2L, 3L);
    long expected = ParallelStreamBenchmarkUtil.complexCalculation(1L) +
        ParallelStreamBenchmarkUtil.complexCalculation(2L) +
        ParallelStreamBenchmarkUtil.complexCalculation(3L);

    assertThat(ParallelStreamBenchmarkUtil.processDataSequential(data)).isEqualTo(expected);
  }

  @Test
  @DisplayName("Проверка null данных")
  void processData_WithNullData_ShouldReturnZero() {
    assertThat(ParallelStreamBenchmarkUtil.processDataSequential(null)).isEqualTo(0L);
    assertThat(ParallelStreamBenchmarkUtil.processDataParallel(null)).isEqualTo(0L);
  }
}