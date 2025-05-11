package ru.mentee.power.oop.constructors;

/**
 * Класс для описания товара на складе.
 */

public class InventoryItem {

  private String sku;
  private String name;
  private int quantity;
  private double unitPrice;

  public InventoryItem() {
    this("UNKNOWN_SKU", "Unnamed Item");
    System.out.println("[DEBUG] Конструктор по умолчанию вызван.");
  }

  public InventoryItem(String sku, String name) {
    this(sku, name, 0, 0.0);
    System.out.println("[DEBUG] Конструктор (sku, name) вызван.");
  }

  /**
   * Конструктор по всем полям и валидаций ввода количества и цены за единицу.
   */
  public InventoryItem(String sku, String name, int quantity, double unitPrice) {
    this.sku = sku;
    this.name = name;
    this.quantity = quantity >= 0 ? quantity : 0;
    this.unitPrice = unitPrice >= 0 ? unitPrice : 0;
    System.out.println("[DEBUG] Основной конструктор (4 параметра) вызван.");
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * Метод для вывода всех полей объекта.
   */
  public void displayDetails() {
    System.out.println("SKU: " + sku);
    System.out.println("Название: " + name);
    System.out.println("Количество: " + quantity);
    System.out.println("Цена за единицу: " + unitPrice);
    System.out.println("Общая стоимость: " + getTotalValue());
  }

  /**
   * Метод для добавления таких же объектов.
   */
  public void addStock(int amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Количество не может быть отрицательным.");
    }
    quantity += amount;
  }

  /**
   * Метод для уборки amount количества объектов.
   */
  public boolean removeStock(int amount) {
    if (amount > 0 && amount <= quantity) {
      quantity -= amount;
      return true;
    }
    System.out.println("Количество удаляемого и имеющегося товара не может быть меньше нуля.");
    return false;
  }

  public double getTotalValue() {
    return quantity * unitPrice;
  }
}