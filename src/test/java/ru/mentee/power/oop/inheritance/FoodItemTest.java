package ru.mentee.power.oop.inheritance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для класса FoodItem")
class FoodItemTest {

  private FoodItem testFoodItem;
  private final String sku = "FOOD01";
  private final String name = "Йогурт";
  private final int quantity = 20;
  private final double price = 55.0;
  private final LocalDate expiryDate = LocalDate.now().plusDays(10);

  @BeforeEach
  void setUp() {
    testFoodItem = new FoodItem(sku, name, quantity, price, expiryDate);
  }

  @Test
  @DisplayName("Конструктор должен корректно инициализировать все поля")
  void constructorShouldInitializeFieldsCorrectly() {
    assertThat(testFoodItem.getSku()).isEqualTo(sku);
    assertThat(testFoodItem.getExpirationDate()).isEqualTo(expiryDate);
  }

  @Test
  @DisplayName("Метод displayDetails должен выводить информацию о сроке годности"
      + " (проверка через консольный вывод - опционально, или через рефлексию/состояние)")
  void displayDetailsShouldIncludeExpiryDate() {
    System.out.println("--- Тест displayDetails для FoodItem ---");
    System.out.println("--- Конец теста displayDetails ---");
  }

  @Test
  @DisplayName("Унаследованные методы должны работать корректно")
  void inheritedMethodsShouldWork() {
    int amountToAdd = 5;
    int initialQuantity = testFoodItem.getQuantity();
    testFoodItem.addStock(amountToAdd);
    double totalValue = testFoodItem.getTotalValue();

    assertThat(testFoodItem.getQuantity()).isEqualTo(initialQuantity + amountToAdd);
    assertThat(totalValue).isEqualTo((initialQuantity + amountToAdd) * price);
  }

  @Test
  @DisplayName("Конструктор должен выбрасывать исключение, если expirationDate == null")
  void constructorShouldThrowExceptionIfDateIsNull() {
    assertThatThrownBy(() -> new FoodItem(sku, name, quantity, price, null)).isInstanceOf(
            NullPointerException.class)
        .hasMessageContaining("Срок годности не может быть null");
  }
}