package ru.mentee.power.oop.inheritance;

import java.time.LocalDate;

/**
 * Класс для демонстрации работы классов ElectronicsItem, FoodItem, InventoryItem.
 */
public class InheritanceDemo {

  /**
   * Основной метод для демонстрации.
   */
  public static void main(String[] args) {
    System.out.println("--- Демонстрация Наследования ---");

    FoodItem milk = new FoodItem("MILK001", "Молоко Простоквашино 3.2%", 10, 85.50,
        LocalDate.now().plusDays(7));
    System.out.println("Создан FoodItem:");
    milk.displayDetails();

    milk.addStock(5);

    ElectronicsItem laptop = new ElectronicsItem("LAPTOP007", "Ноутбук PowerBook Pro", 5, 150000.00,
        24);
    System.out.println("Создан ElectronicsItem:");
    laptop.displayDetails();

    System.out.printf("Общая стоимость ноутбуков: %.2f\n", laptop.getTotalValue());
    laptop.removeStock(1);

    System.out.println(" --- Демонстрация Завершена ---");
  }
}