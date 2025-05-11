package ru.mentee.power.oop.inheritance;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс для описания пищевого товара. Все поля были унаследованы от InventoryItem и добавлено поле
 * expirationDate для срока годности.
 */
public class FoodItem extends InventoryItem {

  private LocalDate expirationDate;

  /**
   * Конструктор унаследован от InventoryItem, и добавлено поле expirationDate.
   */
  public FoodItem(String sku, String name, int quantity, double unitPrice,
      LocalDate expirationDate) {
    super(sku, name, quantity, unitPrice);
    if (expirationDate == null) {
      throw new NullPointerException("Срок годности не может быть null");
    }
    this.expirationDate = expirationDate;
    System.out.println("[DEBUG] FoodItem: Конструктор вызван для " + sku);

  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  @Override
  public void displayDetails() {
    super.displayDetails();
    System.out.println("Срок годности: " + getExpirationDate());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FoodItem foodItem = (FoodItem) o;
    return Objects.equals(expirationDate, foodItem.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), expirationDate);
  }

  @Override
  public String toString() {
    return "FoodItem{"
        + "expirationDate=" + expirationDate
        + ", sku='" + sku + '\''
        + ", name='" + name + '\''
        + ", quantity=" + quantity
        + ", unitPrice=" + unitPrice + '}';
  }
}