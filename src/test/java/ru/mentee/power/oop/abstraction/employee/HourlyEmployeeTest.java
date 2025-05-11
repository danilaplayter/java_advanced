package ru.mentee.power.oop.abstraction.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для HourlyEmployee")
class HourlyEmployeeTest {

  private HourlyEmployee emp;
  private final double hourlyRate = 450.0;
  private final int employeeId = 1;
  private final String employeeName = "Петр Петров";

  @BeforeEach
  void setUp() {
    emp = new HourlyEmployee(employeeId, employeeName, hourlyRate);
  }

  @Test
  @DisplayName("calculateMonthlySalary должен возвращать 0, если часы не залогированы")
  void calculateSalaryShouldBeZeroIfNoHoursLogged() {
    double result = emp.calculateMonthlySalary();

    assertThat(result).isZero();
  }

  @Test
  @DisplayName("calculateMonthlySalary должен корректно считать ЗП после логирования часов")
  void calculateSalaryShouldBeCorrectAfterLoggingHours() {
    int hoursWorked = 160;
    emp.logHours(hoursWorked);

    double result = emp.calculateMonthlySalary();

    assertThat(result).isEqualTo(hourlyRate * hoursWorked);
  }

  @Test
  @DisplayName("logHours должен игнорировать не положительные значения часов")
  void logHoursShouldIgnoreNonPositiveValues() {
    int initialHours = emp.getHoursWorked();

    assertThatThrownBy(() -> emp.logHours(-10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Количество часов должно быть положительны");

    assertThatThrownBy(() -> emp.logHours(0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Количество часов должно быть положительны");

    assertThat(emp.getHoursWorked()).isEqualTo(initialHours);
  }

  @Test
  @DisplayName("logHours должен корректно суммировать часы")
  void logHoursShouldSumHoursCorrectly() {
    int firstBatch = 80;
    int secondBatch = 40;

    emp.logHours(firstBatch);
    emp.logHours(secondBatch);

    assertThat(emp.getHoursWorked()).isEqualTo(firstBatch + secondBatch);
  }

  @Test
  @DisplayName("Конструктор должен выбрасывать исключение при отрицательной ставке")
  void constructorShouldThrowExceptionForNegativeRate() {
    assertThatThrownBy(() -> new HourlyEmployee(1, "Петр Петров", -450.0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Почасовая зарплата не может быть отрицательной.");
  }

  @Test
  @DisplayName("displayInfo должен выводить информацию о работнике")
  void displayInfoShouldPrintEmployeeInfo() {
    emp.logHours(160);

    assertThatCode(() -> emp.displayInfo()).doesNotThrowAnyException();
  }
}