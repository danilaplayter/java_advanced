package ru.mentee.power.solid.srp.task.solution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

class ReportPrinterTest {

  private final ReportPrinter reportPrinter = new ReportPrinter();
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent)); // Перехватываем System.out
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut); // Восстанавливаем System.out
  }

  @Test
  @DisplayName("Должен выводить отчет в консоль")
  void shouldPrintReportToConsole() {
    String report = "Тестовый отчет для вывода.";
    reportPrinter.printToConsole(report);
    String expectedOutputStart = "ReportPrinter: Вывод отчета в консоль:" + System.lineSeparator();
    assertThat(outContent.toString()).startsWith(expectedOutputStart);
    assertThat(outContent.toString()).contains(report);
  }
}