package ru.mentee.power.streams;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamIntroTaskUtil {
  public static List<String> processList(List<String> input) {
    return input.stream()
        .filter(Objects::nonNull)
        .filter(str -> !(str.isEmpty()))
        .map(String::trim)
        .filter(str -> !(str.isEmpty()))
        .map(String::toUpperCase)
        .sorted()
        .collect(Collectors.toList());
  }
}
