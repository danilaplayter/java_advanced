package ru.mentee.power.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveStreamTaskUtil {

  public static int calculateSumOfPositives(int[] data){
    return Arrays.stream(data).filter(n -> n > 0).sum();
  }

  public static OptionalDouble calculateAverage(int[] data){
    return Arrays.stream(data).average();
  }

  public static List<Integer> getSquaresOfUniqueNumbers(int[] data){
    return Arrays
        .stream(data)
        .distinct()
        .map(n -> n * n)
        .boxed()
        .collect(Collectors.toList());
  }

  public static IntSummaryStatistics getStatsForNumbersGreaterThan10(int[] data){
    return Arrays.stream(data)
        .filter(n -> n > 10)
        .summaryStatistics();
  }

  public static boolean hasNegativeNumbers(int[] data){
    return Arrays.stream(data)
        .anyMatch(n -> n < 0);
  }

  public static OptionalInt findMaxEvenNumber(int[] data){
    return Arrays.stream(data)
        .filter(n -> n % 2 == 0)
        .max();
  }

}
