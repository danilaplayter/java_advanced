package ru.mentee.power.oop.interfaces.movable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты для класса Car")
class CarTest {

  private Car testCar;
  private final String model = "Testla";
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

  @BeforeEach
  void setUp() {
    testCar = new Car(model);
    System.setOut(new PrintStream(outputStream));
  }

  @Test
  @DisplayName("move() должен выводить корректное сообщение")
  void moveShouldPrintCorrectMessage() {
    testCar.move();
    String expected = "Машина " + model + " едет по дороге" + System.lineSeparator();
    assertEquals(expected, outputStream.toString());
  }

  @Test
  @DisplayName("stop() должен выводить сообщение по умолчанию из интерфейса")
  void stopShouldPrintDefaultMessage() {
    testCar.stop();
    String expected = "Остановка объекта" + System.lineSeparator();
    assertEquals(expected, outputStream.toString());
  }
}