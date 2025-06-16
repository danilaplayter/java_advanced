package ru.mentee.power.lombok.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class OrderLombokTest {

  @Test
  @DisplayName("Конструктор и геттеры должны работать корректно")
  void constructorAndGettersShouldWork() {
    String orderId = "order123";
    String customerName = "Иван Петров";
    String productName = "Ноутбук";
    int quantity = 2;
    double pricePerUnit = 50000.0;
    OrderStatus status = OrderStatus.NEW;

    OrderLombok order = new OrderLombok(orderId, customerName, productName, quantity, pricePerUnit, status);

    assertThat(order.getOrderId()).isEqualTo(orderId);
    assertThat(order.getCustomerName()).isEqualTo(customerName);
    assertThat(order.getProductName()).isEqualTo(productName);
    assertThat(order.getQuantity()).isEqualTo(quantity);
    assertThat(order.getPricePerUnit()).isEqualTo(pricePerUnit);
    assertThat(order.getStatus()).isEqualTo(status);
    assertThat(order.getTotalPrice()).isEqualTo(100000.0);
  }

  @Test
  @DisplayName("Сеттеры должны изменять значения полей")
  void settersShouldChangeFieldValues() {
    OrderLombok order = new OrderLombok("id1", "Cust1", "Prod1", 1, 10.0, OrderStatus.NEW)
        .setCustomerName("Новый Клиент")
        .setQuantity(5);

    assertThat(order.getCustomerName()).isEqualTo("Новый Клиент");
    assertThat(order.getQuantity()).isEqualTo(5);
    assertThat(order.getTotalPrice()).isEqualTo(50.0);
  }

  @Test
  @DisplayName("equals и hashCode должны корректно сравнивать объекты")
  void equalsAndHashCodeShouldWorkCorrectly() {
    OrderLombok order1 = new OrderLombok("o1", "C1", "P1", 1, 10.0, OrderStatus.NEW);
    OrderLombok order2 = new OrderLombok("o1", "C1", "P1", 1, 10.0, OrderStatus.NEW);
    OrderLombok order3 = new OrderLombok("o2", "C2", "P2", 2, 20.0, OrderStatus.PROCESSING);

    assertThat(order1).isEqualTo(order2);
    assertThat(order1.hashCode()).isEqualTo(order2.hashCode());
    assertThat(order1).isNotEqualTo(order3);
  }

  @Test
  @DisplayName("toString должен возвращать непустую строку")
  void toStringShouldReturnNonEmptyString() {
    OrderLombok order = new OrderLombok("idToString", "CustToString", "ProdToString", 1, 1.0, OrderStatus.NEW);
    assertThat(order.toString())
        .isNotNull()
        .contains("OrderLombok")
        .contains("idToString")
        .contains("status=NEW");
  }
}