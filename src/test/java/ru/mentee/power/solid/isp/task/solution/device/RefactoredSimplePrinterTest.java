package ru.mentee.power.solid.isp.task.solution.device;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.solid.isp.task.solution.interf.Faxable;
import ru.mentee.power.solid.isp.task.solution.interf.Printable;
import ru.mentee.power.solid.isp.task.solution.interf.Scannable;
import ru.mentee.power.solid.isp.task.solution.interf.Staplable;

class RefactoredSimplePrinterTest {

  private final RefactoredSimplePrinter printer = new RefactoredSimplePrinter();
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  @DisplayName("RefactoredSimplePrinter должен реализовывать Printable")
  void shouldImplementPrintable() {
    assertThat(printer).isInstanceOf(Printable.class);
  }

  @Test
  @DisplayName("RefactoredSimplePrinter НЕ должен реализовывать Scannable, Faxable, Staplable")
  void shouldNotImplementOtherInterfaces() {
    assertThat(printer).isNotInstanceOf(Scannable.class);
    assertThat(printer).isNotInstanceOf(Faxable.class);
    assertThat(printer).isNotInstanceOf(Staplable.class);
  }

  @Test
  @DisplayName("print() должен выводить корректное сообщение")
  void printShouldOutputCorrectMessage() {
    printer.print("test_doc_simple");
    assertThat(outContent.toString()).contains(
        "RefactoredSimplePrinter: Печать документа - test_doc_simple");
  }
}