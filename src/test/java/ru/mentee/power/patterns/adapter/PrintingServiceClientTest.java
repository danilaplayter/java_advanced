package ru.mentee.power.patterns.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.mentee.power.patterns.adapter.client.PrintingServiceClient;
import ru.mentee.power.patterns.adapter.printer.ModernPrinter;

import static org.mockito.Mockito.verify;

class PrintingServiceClientTest {

  @Test
  @DisplayName("PrintingServiceClient должен вызывать printDocument у ModernPrinter")
  void shouldCallPrintDocumentOnModernPrinter() {
    PrintingServiceClient client = new PrintingServiceClient();
    ModernPrinter mockModernPrinter = Mockito.mock(ModernPrinter.class);
    String reportData = "Some report data";

    client.printReport(mockModernPrinter, reportData);

    verify(mockModernPrinter).printDocument(reportData);
  }
}