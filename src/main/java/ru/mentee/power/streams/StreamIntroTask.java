package ru.mentee.power.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamIntroTask {

  public static void main(String[] args) {
    List<String> data = Arrays.asList(
        "  Java ", " Kotlin ", "", "  Scala", " Groovy ", " Clojure", " ", "   "
    );

    System.out.println("Исходные данные: " + data);

    // --- Задача: Обработка с помощью Stream API ---
    List<String> processedData = data.stream()
        .filter(str -> !str.isEmpty())
        .map(String::trim)
        .filter(str -> !str.isEmpty())
        .map(String::toUpperCase)
        .sorted()
        .toList();

    System.out.println("Обработанные данные (Stream API): " + processedData);

    // --- (Опционально) Сравнение с императивным подходом ---
    List<String> imperativeResult = new ArrayList<>();
    for (String str : data) {
      if (!str.isEmpty()) {
        String trimmed = str.trim();
        if (!trimmed.isEmpty()) {
          String upperCase = trimmed.toUpperCase();
          imperativeResult.add(upperCase);
        }
      }
    }
    imperativeResult.sort(null);
    System.out.println("Обработанные данные (Императивно): " + imperativeResult);
  }
}