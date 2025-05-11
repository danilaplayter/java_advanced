package ru.mentee.power.oop.inheritance;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для класса ElectronicsItem")
class ElectronicsItemTest {

  private ElectronicsItem testElectronicsItem;
  private final String sku = "ELEC01";
  private final String name = "Смартфон";
  private final int quantity = 15;
  private final double price = 35000.0;
  private final int warranty = 12;

  @BeforeEach
  void setUp() {
    testElectronicsItem = new ElectronicsItem(sku, name, quantity, price, warranty);
  }

  @Test
  @DisplayName("Конструктор должен корректно инициализировать все поля")
  void constructorShouldInitializeFieldsCorrectly() {
    assertThat(testElectronicsItem.getSku()).isEqualTo(sku);
    assertThat(testElectronicsItem.getWarrantyMonths()).isEqualTo(warranty);
  }

  @Test
  @DisplayName("Конструктор должен устанавливать гарантию в 0 при отрицательном значении")
  void constructorShouldSetZeroWarrantyForNegativeInput() {

    int negativeWarranty = -6;

    ElectronicsItem itemWithNegativeWarranty = new ElectronicsItem(sku, name, quantity, price,
        negativeWarranty);

    assertThat(itemWithNegativeWarranty.getWarrantyMonths()).isZero();
  }

  @Test
  @DisplayName("Метод displayDetails должен выводить информацию о гарантии")
  void displayDetailsShouldIncludeWarranty() {
    System.out.println("--- Тест displayDetails для ElectronicsItem ---");
    testElectronicsItem.displayDetails();
    System.out.println("--- Конец теста displayDetails ---");
  }

  @Test
  @DisplayName("Унаследованные методы должны работать корректно")
  void inheritedMethodsShouldWork() {
    int initialQuantity = testElectronicsItem.getQuantity();
    int amountToRemove = 3;

    boolean removed = testElectronicsItem.removeStock(amountToRemove);
    double totalValue = testElectronicsItem.getTotalValue();
    assertThat(removed).isTrue();
    assertThat(testElectronicsItem.getQuantity()).isEqualTo(initialQuantity - amountToRemove);
    assertThat(totalValue).isEqualTo((initialQuantity - amountToRemove) * price);
  }
}