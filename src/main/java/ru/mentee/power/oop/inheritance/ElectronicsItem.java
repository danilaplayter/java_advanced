package ru.mentee.power.oop.inheritance;

import java.util.Objects;

/**
 * Класс для описания электроники. Все поля были унаследованы от InventoryItem и добавлено поле
 * warrantMonth для количества месяцев гарантии.
 */

public class ElectronicsItem extends InventoryItem {

  private int warrantyMonths;

  /**
   * Конструктор унаследован от InventoryItem, и добавлено поле warrantMonths.
   */

  public ElectronicsItem(String sku, String name, int quantity, double unitPrice,
      int warrantyMonths) {
    super(sku, name, quantity, unitPrice);
    this.warrantyMonths = (warrantyMonths >= 0) ? warrantyMonths : 0;
  }

  public int getWarrantyMonths() {
    return warrantyMonths;
  }

  @Override
  public void displayDetails() {
    super.displayDetails();
    System.out.println("Гарантия (мес.): " + warrantyMonths);

  }


  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    ElectronicsItem that = (ElectronicsItem) o;
    return warrantyMonths == that.warrantyMonths;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), warrantyMonths);
  }

  @Override
  public String toString() {
    return "ElectronicsItem{"
        + "warrantyMonths=" + warrantyMonths
        + ", sku='" + sku + '\''
        + ", name='" + name + '\''
        + ", quantity=" + quantity
        + ", unitPrice=" + unitPrice + '}';
  }
}