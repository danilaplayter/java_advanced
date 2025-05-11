package ru.mentee.power.oop.abstraction.employee;

import java.util.Objects;

public class HourlyEmployee extends Employee {
  private final double hourlyRate;
  private int hoursWorked;

  public HourlyEmployee(int id, String name, double hourlyRate) {
    super(id, name);
    if (hourlyRate < 0) {
      throw new IllegalArgumentException("Почасовая зарплата не может быть отрицательной.");
    }
    this.hourlyRate = hourlyRate;
    this.hoursWorked = 0;
  }

  @Override
  public double calculateMonthlySalary() {
    return hourlyRate * hoursWorked;
  }

  public void logHours(int hours) {
    if (hours <= 0) {
      throw new IllegalArgumentException("Количество часов должно быть положительным");
    }
    this.hoursWorked += hours;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Hourly Rate: " + hourlyRate);
    System.out.println("Hours Worked: " + hoursWorked);
    System.out.println("Monthly Salary: " + calculateMonthlySalary());
  }

  public double getHourlyRate() {
    return hourlyRate;
  }

  public int getHoursWorked() {
    return hoursWorked;
  }

  @Override
  public String toString() {
    return "HourlyEmployee{" +
        "id=" + getId() +
        ", name='" + getName() + '\'' +
        ", hourlyRate=" + hourlyRate +
        ", hoursWorked=" + hoursWorked +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    HourlyEmployee that = (HourlyEmployee) o;
    return Double.compare(that.hourlyRate, hourlyRate) == 0 &&
        hoursWorked == that.hoursWorked;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), hourlyRate, hoursWorked);
  }
}