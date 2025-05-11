package ru.mentee.power.oop.interfaces.movable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Robot")
class RobotTest {

  private Robot testRobot;
  private final String serial = "SN-007";
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    testRobot = new Robot(serial);
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  @DisplayName("move() должен выводить корректное сообщение")
  void moveShouldPrintCorrectMessage() {
    testRobot.move();
    String expected = "Робот " + serial + " шагает" + System.lineSeparator();
    assertEquals(expected, outputStream.toString());
  }

  @Test
  @DisplayName("stop() должен выводить переопределенное сообщение")
  void stopShouldPrintOverriddenMessage() {
    testRobot.stop();
    String expected = "Робот " + serial + " прекращает движение!" + System.lineSeparator();
    assertEquals(expected, outputStream.toString());
  }
}