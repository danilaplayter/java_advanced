package ru.mentee.power.oop.abstraction.employee;

import java.util.Objects;

public class SalariedEmployee extends Employee {
  private final double monthlySalary;

  public SalariedEmployee(int id, String name, double monthlySalary) {
    super(id, name);
    if (monthlySalary < 0) {
      throw new IllegalArgumentException("Зарплата не может быть отрицательной");
    }
    this.monthlySalary = monthlySalary;
  }

  @Override
  public double calculateMonthlySalary() {
    return monthlySalary;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Зарплата в месяц: " + monthlySalary);
  }

  public double getMonthlySalary() {
    return monthlySalary;
  }

  @Override
  public String toString() {
    return "SalariedEmployee{" +
        "id=" + getId() +
        ", name='" + getName() + '\'' +
        ", monthlySalary=" + monthlySalary +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    SalariedEmployee that = (SalariedEmployee) o;
    return Double.compare(that.monthlySalary, monthlySalary) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), monthlySalary);
  }
}