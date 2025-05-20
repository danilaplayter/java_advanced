package ru.mentee.power.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreationTaskUtil {

  public static <T> List<T> getFirstNElementsFromList(List<T> source, int n) {
    if (source == null || n <= 0) {
      return Collections.emptyList();
    }
    return source.stream().limit(n).collect(Collectors.toList());
  }

  public static <T> List<T> arrayToList(T[] array) {
    if (array == null) {
      return Collections.emptyList();
    }
    return Arrays.stream(array).collect(Collectors.toList());
  }

  public static List<Integer> getRangeAsList(int start, int end) {
    if (start > end) {
      return Collections.emptyList();
    }
    return IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
  }

  public static <T> List<T> generateRandomValues(int count, Supplier<T> generator) {
    if (count <= 0 || generator == null) {
      return Collections.emptyList();
    }
    return Stream.generate(generator).limit(count).collect(Collectors.toList());
  }

  public static List<Integer> generateSequence(int seed, int step, int count) {
    if (count <= 0) {
      return Collections.emptyList();
    }
    return Stream.iterate(seed, n -> n + step).limit(count).collect(Collectors.toList());
  }

  public static List<String> readFirstNLinesFromFile(Path path, int n) throws IOException {
    if (path == null || n <= 0 || !Files.exists(path)) {
      return Collections.emptyList();
    }
    try (Stream<String> lines = Files.lines(path)) {
      return lines.limit(n).collect(Collectors.toList());
    }
  }

  public static long countEmptyStream() {
    return Stream.empty().count();
  }
}