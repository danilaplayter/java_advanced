package ru.mentee.power.functional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MethodRefsUtilTest {

  @Test
  @DisplayName("Функция должна корректно считать длину строки")
  void getStringLengthFunctionShouldWorkCorrectly() {
    Function<String, Integer> function = MethodRefsUtil.getStringLengthFunction();

    assertThat(function).isNotNull();
    assertThat(function.apply("Java")).isEqualTo(4);
    assertThat(function.apply("")).isEqualTo(0);
    assertThat(function.apply("Hello World")).isEqualTo(11);
  }

  @Test
  @DisplayName("Консьюмер должен корректно печатать строку")
  void getPrintStringConsumerShouldWorkCorrectly() {
    PrintStream originalOut = System.out;
    try {
      ByteArrayOutputStream outContent = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outContent));

      Consumer<String> consumer = MethodRefsUtil.getPrintStringConsumer();
      assertThat(consumer).isNotNull();

      String testString = "Test Output";
      consumer.accept(testString);

      assertThat(outContent.toString().trim()).isEqualTo(testString);

    } finally {
      System.setOut(originalOut);
    }
  }

  @Test
  @DisplayName("Поставщик должен создавать новые потоки")
  void getThreadSupplierShouldWorkCorrectly() {
    Supplier<Thread> supplier = MethodRefsUtil.getThreadSupplier();

    assertThat(supplier).isNotNull();

    Thread thread1 = supplier.get();
    Thread thread2 = supplier.get();

    assertThat(thread1).isNotSameAs(thread2);
    assertThat(thread1).isInstanceOf(Thread.class);
    assertThat(thread2).isInstanceOf(Thread.class);
  }

  @Test
  @DisplayName("BiFunction должна корректно форматировать сумму")
  void getFormatSumFunctionShouldWorkCorrectly() {
    BiFunction<Integer, Integer, String> function = MethodRefsUtil.getFormatSumFunction();

    assertThat(function).isNotNull();
    assertThat(function.apply(5, 7)).isEqualTo("5 + 7 = 12");
    assertThat(function.apply(0, 0)).isEqualTo("0 + 0 = 0");
    assertThat(function.apply(-3, 5)).isEqualTo("-3 + 5 = 2");
  }

  @Test
  @DisplayName("IntBinaryOperator должен находить максимум из двух чисел")
  void getMaxOperatorShouldWorkCorrectly() {
    IntBinaryOperator operator = MethodRefsUtil.getMaxOperator();

    assertThat(operator).isNotNull();
    assertThat(operator.applyAsInt(10, 15)).isEqualTo(15);
    assertThat(operator.applyAsInt(20, 20)).isEqualTo(20);
    assertThat(operator.applyAsInt(-5, -3)).isEqualTo(-3);
  }

  @Test
  @DisplayName("ToIntFunction должна корректно возвращать длину строки")
  void getStringToIntLengthFunctionShouldWorkCorrectly() {
    ToIntFunction<String> function = MethodRefsUtil.getStringToIntLengthFunction();

    assertThat(function).isNotNull();
    assertThat(function.applyAsInt("Function")).isEqualTo(8);
    assertThat(function.applyAsInt("")).isEqualTo(0);
    assertThat(function.applyAsInt("Java")).isEqualTo(4);
  }
}