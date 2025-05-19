package ru.mentee.power.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import ru.mentee.power.functional.LambdaBasicsTaskUtil;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LambdaBasicsTaskUtilTest {

  @Test
  @DisplayName("Предикат должен корректно проверять строки, начинающиеся на 'A'")
  void startsWithAPredicateShouldWorkCorrectly() {
    Predicate<String> predicate = LambdaBasicsTaskUtil.createStartsWithAPredicate();

    // Проверяем что предикат не null
    assertThat(predicate).isNotNull();

    // Проверяем строки, начинающиеся с A (разные регистры)
    assertThat(predicate.test("Apple")).isTrue();
    assertThat(predicate.test("android")).isTrue();
    assertThat(predicate.test("Aardvark")).isTrue();
    assertThat(predicate.test("aPPLE")).isTrue();

    // Проверяем строки, не начинающиеся с A
    assertThat(predicate.test("Banana")).isFalse();
    assertThat(predicate.test("cherry")).isFalse();
    assertThat(predicate.test("1Apple")).isFalse();

    // Крайние случаи
    assertThat(predicate.test("")).isFalse();
    assertThat(predicate.test(null)).isFalse();
  }

  @Test
  @DisplayName("Функция должна корректно проверять положительные числа")
  void isPositiveFunctionShouldWorkCorrectly() {
    Function<Integer, Boolean> function = LambdaBasicsTaskUtil.createIsPositiveFunction();

    assertThat(function).isNotNull();

    // Положительные числа
    assertThat(function.apply(1)).isTrue();
    assertThat(function.apply(100)).isTrue();
    assertThat(function.apply(Integer.MAX_VALUE)).isTrue();

    // Не положительные числа
    assertThat(function.apply(0)).isFalse();
    assertThat(function.apply(-1)).isFalse();
    assertThat(function.apply(-100)).isFalse();
    assertThat(function.apply(Integer.MIN_VALUE)).isFalse();
  }

  @Test
  @DisplayName("Консьюмер должен печатать элементы списка")
  void printListElementsConsumerShouldWorkCorrectly() {
    // Сохраняем оригинальный System.out
    PrintStream originalOut = System.out;
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    try {
      Consumer<List<String>> consumer = LambdaBasicsTaskUtil.createPrintListElementsConsumer();
      List<String> testList = List.of("One", "Two", "Three");

      assertThat(consumer).isNotNull();

      // Вызываем консьюмер с тестовым списком
      consumer.accept(testList);

      // Получаем вывод и нормализуем переносы строк
      String output = outContent.toString().replace("\r\n", "\n");

      // Проверяем что каждый элемент был выведен на отдельной строке
      assertThat(output).isEqualTo("One\nTwo\nThree\n");

      // Дополнительная проверка для пустого списка
      outContent.reset();
      consumer.accept(List.of());
      assertThat(outContent.toString()).isEmpty();

    } finally {
      // Восстанавливаем System.out
      System.setOut(originalOut);
    }
  }

  @Test
  @DisplayName("Поставщик должен генерировать случайные числа в диапазоне 0-99")
  void randomIntSupplierShouldGenerateNumbersInRange() {
    Supplier<Integer> supplier = LambdaBasicsTaskUtil.createRandomIntSupplier();

    assertThat(supplier).isNotNull();

    // Проверяем 1000 случайных чисел для надежности
    for (int i = 0; i < 1000; i++) {
      int randomNumber = supplier.get();
      assertThat(randomNumber)
          .as("Random number should be between 0 and 99")
          .isBetween(0, 99);
    }

    // Проверяем что числа действительно разные (хотя есть минимальная вероятность коллизии)
    int firstNumber = supplier.get();
    int secondNumber = supplier.get();
    assertThat(firstNumber).isNotEqualTo(secondNumber);
  }
}