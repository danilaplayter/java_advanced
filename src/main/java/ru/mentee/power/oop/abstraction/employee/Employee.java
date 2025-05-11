package ru.mentee.power.oop.abstraction.employee;

import java.util.Objects;

public abstract class Employee {
  private final int id;
  private String name;

  protected Employee(int id, String name) {
    if (id <= 0) {
      throw new IllegalArgumentException("ID должен быть положительным");
    }
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Имя не может быть null или пустым");
    }
    this.id = id;
    this.name = name.trim();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public abstract double calculateMonthlySalary();

  public void displayInfo() {
    System.out.println("Employee ID: " + id);
    System.out.println("Name: " + name);
  }

  @Override
  public String toString() {
    return "Employee{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return id == employee.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}