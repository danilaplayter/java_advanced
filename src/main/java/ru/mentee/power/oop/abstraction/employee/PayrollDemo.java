package ru.mentee.power.oop.abstraction.employee;

import java.util.ArrayList;
import java.util.List;

public class PayrollDemo {
  public static void main(String[] args) {
    System.out.println("--- Расчетный Лист ---");

    List<Employee> employees = new ArrayList<>();

    employees.add(new SalariedEmployee(1, "Иван Иванов", 150000));
    employees.add(new SalariedEmployee(2, "Петр Петров", 180000));
    employees.add(new HourlyEmployee(3, "Сидор Сидоров", 1200));
    employees.add(new HourlyEmployee(4, "Анна Каренина", 950));

    for (Employee employee : employees) {
      if (employee instanceof HourlyEmployee) {
        HourlyEmployee hourlyEmployee = (HourlyEmployee) employee;
        if (hourlyEmployee.getName().equals("Сидор Сидоров")) {
          hourlyEmployee.logHours(160);
        } else {
          hourlyEmployee.logHours(140);
        }
      }
    }

    System.out.println("\n--- Информация о работниках и их ЗП за месяц ---");

    for (Employee employee : employees) {
      employee.displayInfo();
      System.out.printf("Месячная зарплата: %.2f руб.\n", employee.calculateMonthlySalary());
      System.out.println("----------------------");
    }

    System.out.println("\n--- Расчет завершен ---");
  }
}