package ru.mentee.power.oop.constructors;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для класса InventoryItem (с конструкторами)")
class InventoryItemTest {

  @Test
  @DisplayName("Основной конструктор инициализирует все поля корректно")
  void mainConstructorShouldInitializeFields() {
    String sku = "SKU001";
    String name = "Тестовый Товар";
    int quantity = 10;
    double price = 99.99;

    InventoryItem item = new InventoryItem(sku, name, quantity, price);

    assertThat(item.getSku()).isEqualTo(sku);
    assertThat(item.getName()).isEqualTo(name);
    assertThat(item.getQuantity()).isEqualTo(quantity);
    assertThat(item.getUnitPrice()).isEqualTo(price);
  }

  @Test
  @DisplayName("Конструктор (sku, name) устанавливает quantity и price в 0")
  void skuNameConstructorShouldSetDefaults() {
    // Arrange
    String sku = "SKU002";
    String name = "Товар Без Цены";

    InventoryItem item = new InventoryItem(sku, name);

    assertThat(item.getSku()).isEqualTo(sku);
    assertThat(item.getName()).isEqualTo(name);
    assertThat(item.getQuantity()).isZero();
    assertThat(item.getUnitPrice()).isZero();
  }

  @Test
  @DisplayName("Конструктор по умолчанию устанавливает дефолтные sku и name")
  void defaultConstructorShouldSetDefaults() {
    InventoryItem item = new InventoryItem();

    assertThat(item.getSku()).isEqualTo("UNKNOWN_SKU");
    assertThat(item.getName()).isEqualTo("Unnamed Item");
    assertThat(item.getQuantity()).isZero();
    assertThat(item.getUnitPrice()).isZero();
  }

  @Test
  @DisplayName("Основной конструктор обрабатывает отрицательные quantity и price")
  void mainConstructorShouldHandleNegativeValues() {
    InventoryItem item = new InventoryItem("SKU003", "Негатив", -5, -10);

    assertThat(item.getQuantity()).isZero();
    assertThat(item.getUnitPrice()).isZero();
  }

  @Test
  @DisplayName("addStock корректно увеличивает количество")
  void addStockShouldIncreaseQuantity() {
    InventoryItem item = new InventoryItem("SKU004", "Тест Добавления", 10, 100);
    item.addStock(5);
    assertThat(item.getQuantity()).isEqualTo(15);
  }

  @Test
  @DisplayName("removeStock корректно уменьшает количество при достаточном количестве")
  void removeStockShouldDecreaseQuantityWhenEnough() {
    InventoryItem item = new InventoryItem("SKU005", "Тест Удаления", 10, 100);
    boolean result = item.removeStock(3);
    assertThat(result).isTrue();
    assertThat(item.getQuantity()).isEqualTo(7);
  }

  @Test
  @DisplayName("removeStock возвращает false при недостаточном количестве")
  void removeStockShouldReturnFalseWhenNotEnough() {
    InventoryItem item = new InventoryItem("SKU006", "Тест Нехватки", 2, 100);

    boolean result = item.removeStock(5);

    assertThat(result).isFalse();
    assertThat(item.getQuantity()).isEqualTo(2);
  }

  @Test
  @DisplayName("getTotalValue корректно вычисляет общую стоимость")
  void getTotalValueShouldCalculateCorrectly() {
    InventoryItem item = new InventoryItem("SKU007", "Тест Стоимости", 3, 150);
    assertThat(item.getTotalValue()).isEqualTo(450);
  }
}
