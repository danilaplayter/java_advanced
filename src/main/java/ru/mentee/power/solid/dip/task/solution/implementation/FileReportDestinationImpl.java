package ru.mentee.power.solid.dip.task.solution.implementation;

import ru.mentee.power.solid.dip.task.solution.abstraction.ReportDestination;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileReportDestinationImpl implements ReportDestination {
  @Override
  public void write(String content, String destinationHint) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationHint))) {
      writer.write(content);
      System.out.println("DIP Solution: Отчёт успешно записан в файл: " + destinationHint);
    } catch (IOException e) {
      System.err.println("DIP Solution: Ошибка при записи в файл: " + e.getMessage());
    }
  }
}