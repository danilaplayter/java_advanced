package ru.mentee.power.oop.constructors;

/**
 * Класс для демонстрации работы класса InventoryItem.
 */
public class WarehouseDemo {

  /**
   * Основной метод.
   */
  public static void main(String[] args) {
    System.out.println("Система управления складом запущена!");

    // --- Создание объектов с помощью конструкторов ---

    System.out.println("\n--- Товар 1 (основной конструктор) ---");
    InventoryItem item1 = new InventoryItem("SKU123", "Ноутбук", 5, 50000);
    item1.displayDetails();
    item1.addStock(10);
    System.out.println("После добавления 10 единиц:");
    item1.displayDetails();

    System.out.println("\n--- Товар 2 (конструктор sku, name) ---");
    InventoryItem item2 = new InventoryItem("SKU456", "Смартфон");
    item2.displayDetails();
    boolean removed = item2.removeStock(5);
    System.out.println("Результат попытки снять товар: " + (removed ? "Успешно" : "Не удалось"));
    item2.displayDetails();

    System.out.println("\n--- Товар 3 (конструктор по умолчанию) ---");
    InventoryItem item3 = new InventoryItem();
    item3.displayDetails();
    item3.addStock(100);
    System.out.println("После добавления 100 единиц:");
    item3.displayDetails();

    System.out.println("\nРабота системы управления складом завершена.");
  }
}