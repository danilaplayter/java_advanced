package ru.mentee.power.functional;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaBasicsTaskUtil {

  public static Predicate<String> createStartsWithAPredicate() {
    return s -> s != null && s.toLowerCase().startsWith("a");
  }

  public static Function<Integer, Boolean> createIsPositiveFunction() {
    return n -> n > 0;
  }

  public static Consumer<List<String>> createPrintListElementsConsumer() {
    return list -> list.forEach(System.out::println);
  }

  public static Supplier<Integer> createRandomIntSupplier() {
    return () -> new Random().nextInt(100);
  }
}
