package ru.mentee.power.solid.isp.task.solution.device;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.solid.isp.task.solution.interf.*; // Импорт всех интерфейсов
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.assertj.core.api.Assertions.assertThat;

class RefactoredOfficeMFUTest {
  private final RefactoredOfficeMFU mfu = new RefactoredOfficeMFU();
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach void setUpStreams() { System.setOut(new PrintStream(outContent)); }
  @AfterEach void restoreStreams() { System.setOut(originalOut); }

  @Test
  @DisplayName("RefactoredOfficeMFU должен реализовывать Printable, Scannable, Faxable")
  void shouldImplementCorrectInterfaces() {
    assertThat(mfu).isInstanceOf(Printable.class);
    assertThat(mfu).isInstanceOf(Scannable.class);
    assertThat(mfu).isInstanceOf(Faxable.class);
  }

  @Test
  @DisplayName("RefactoredOfficeMFU НЕ должен реализовывать Staplable")
  void shouldNotImplementStaplable() {
    assertThat(mfu).isNotInstanceOf(Staplable.class);
  }

  @Test
  @DisplayName("print должен выводить сообщение о печати")
  void print_shouldOutputPrintMessage() {
    String document = "test.doc";
    mfu.print(document);
    assertThat(outContent.toString()).contains("Печать - " + document);
  }

  @Test
  @DisplayName("scan должен выводить сообщение о сканировании")
  void scan_shouldOutputScanMessage() {
    String document = "scan_me.jpg";
    mfu.scan(document);
    assertThat(outContent.toString()).contains("Сканирование - " + document);
  }

  @Test
  @DisplayName("fax должен выводить сообщение о отправке факса")
  void fax_shouldOutputFaxMessage() {
    String document = "fax_this.pdf";
    mfu.fax(document);
    assertThat(outContent.toString()).contains("Отправка факса - " + document);
  }
}