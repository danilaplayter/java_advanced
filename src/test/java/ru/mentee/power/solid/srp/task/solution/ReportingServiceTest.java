package ru.mentee.power.solid.srp.task.solution;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito; // Используем Mockito для мокирования зависимостей
import ru.mentee.power.solid.srp.task.ReportData;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReportingServiceTest {

  private DataFilter mockDataFilter = Mockito.mock(DataFilter.class);
  private ReportFormatter mockReportFormatter = Mockito.mock(ReportFormatter.class);
  private ReportPrinter mockReportPrinter = Mockito.mock(ReportPrinter.class);

  private ReportingService reportingService = new ReportingService(mockDataFilter,
      mockReportFormatter, mockReportPrinter);

  @Test
  @DisplayName("Должен вызывать все компоненты в правильной последовательности")
  void shouldCallAllComponentsInOrder() {
    List<ReportData> testData = List.of(new ReportData("Test", 100.0));
    double threshold = 50.0;
    List<ReportData> filteredData = List.of(new ReportData("Filtered Test", 100.0));
    String formattedReport = "Formatted Report String";

    when(mockDataFilter.filterByThreshold(testData, threshold)).thenReturn(filteredData);
    when(mockReportFormatter.formatToString(filteredData)).thenReturn(formattedReport);

    reportingService.generateAndPrintReport(testData, threshold);

    verify(mockDataFilter).filterByThreshold(testData, threshold);
    verify(mockReportFormatter).formatToString(filteredData);
    verify(mockReportPrinter).printToConsole(formattedReport);
  }
}