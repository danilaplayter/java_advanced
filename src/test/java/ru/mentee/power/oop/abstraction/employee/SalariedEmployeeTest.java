package ru.mentee.power.oop.abstraction.employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для SalariedEmployee")
class SalariedEmployeeTest {

  @Test
  @DisplayName("calculateMonthlySalary должен возвращать фиксированный оклад")
  void calculateMonthlySalaryShouldReturnFixedSalary() {
    double fixedSalary = 100000.0;
    SalariedEmployee emp = new SalariedEmployee(1, "Иван Иванов", fixedSalary);

    double result = emp.calculateMonthlySalary();

    assertThat(result).isEqualTo(fixedSalary);
  }

  @Test
  @DisplayName("Конструктор должен выбрасывать исключение при отрицательном окладе")
  void constructorShouldThrowExceptionForNegativeSalary() {
    assertThatThrownBy(() -> new SalariedEmployee(1, "Иван Иванов", -1000.0))
        .isInstanceOf(
        IllegalArgumentException.class).
        hasMessageContaining("Зарплата не может быть отрицательной");
  }

  @Test
  @DisplayName("displayInfo должен выводить информацию о работнике")
  void displayInfoShouldPrintEmployeeInfo() {
    SalariedEmployee emp = new SalariedEmployee(1, "Иван Иванов", 100000.0);

    assertThatCode(emp::displayInfo).doesNotThrowAnyException();
  }
}