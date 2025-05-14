package ru.mentee.power.exceptions.multilevel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DataSource {
  private String filePath;

  public DataSource(String filePath) {
    this.filePath = filePath;
  }

  public List<String> readLines() throws FileReadException {
    try {
      Path path = Paths.get(filePath);
      return Files.lines(path).collect(Collectors.toList());
    } catch (IOException e) {
      throw new FileReadException(filePath, e);
    }
  }
}