package ru.mentee.power.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimitiveStreamTask {

  public static void main(String[] args) {
    int[] data = {10, -5, 20, 0, 15, -5, 10, 8, 20, 30, -10};

    System.out.println("Исходный массив: " + Arrays.toString(data));

    // --- Задача 1: Сумма положительных чисел ---
    System.out.println("\n--- Сумма положительных чисел ---");
    // Демонстрация вызова
    int sumOfPositives = PrimitiveStreamTaskUtil.calculateSumOfPositives(data);
    System.out.println("Сумма положительных: " + sumOfPositives);

    // --- Задача 2: Среднее значение всех чисел ---
    System.out.println("\n--- Среднее значение всех чисел ---");
    // Демонстрация вызова
    OptionalDouble averageOptional = PrimitiveStreamTaskUtil.calculateAverage(data);
    double average = averageOptional.orElse(0.0);
    System.out.println("Среднее значение: " + average);

    // --- Задача 3: Список квадратов уникальных чисел ---
    System.out.println("\n--- Список квадратов уникальных чисел ---");
    // Демонстрация вызова
    List<Integer> squaresOfUniqueNumbers = PrimitiveStreamTaskUtil.getSquaresOfUniqueNumbers(data);
    System.out.println("Квадраты уникальных: " + squaresOfUniqueNumbers);

    // --- Задача 4: Статистика для чисел > 10 ---
    System.out.println("\n--- Статистика для чисел > 10 ---");
    // Демонстрация вызова
    IntSummaryStatistics statsGreaterThan10 = PrimitiveStreamTaskUtil.getStatsForNumbersGreaterThan10(data);
    if (statsGreaterThan10.getCount() > 0) { // Проверяем, есть ли данные для статистики
      System.out.println("Количество: " + statsGreaterThan10.getCount());
      System.out.println("Минимум: " + statsGreaterThan10.getMin());
      System.out.println("Максимум: " + statsGreaterThan10.getMax());
      System.out.println("Среднее: " + statsGreaterThan10.getAverage());
    } else {
      System.out.println("Нет чисел больше 10 для статистики.");
    }
  }
}