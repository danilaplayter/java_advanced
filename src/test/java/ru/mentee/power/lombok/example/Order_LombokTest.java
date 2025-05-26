package ru.mentee.power.lombok.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class Order_LombokTest {

  @Test
  @DisplayName("Конструктор и геттеры должны работать корректно")
  void constructorAndGettersShouldWork() {
    String orderId = "order123";
    String customerName = "Иван Петров";
    String productName = "Ноутбук";
    int quantity = 2;
    double pricePerUnit = 50000.0;
    OrderStatus status = OrderStatus.NEW;

    Order_Lombok order = new Order_Lombok(orderId, customerName, productName, quantity, pricePerUnit, status);

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
    Order_Lombok order = new Order_Lombok("id1", "Cust1", "Prod1", 1, 10.0, OrderStatus.NEW)
        .setCustomerName("Новый Клиент")
        .setQuantity(5);

    assertThat(order.getCustomerName()).isEqualTo("Новый Клиент");
    assertThat(order.getQuantity()).isEqualTo(5);
    assertThat(order.getTotalPrice()).isEqualTo(50.0);
  }

  @Test
  @DisplayName("equals и hashCode должны корректно сравнивать объекты")
  void equalsAndHashCodeShouldWorkCorrectly() {
    Order_Lombok order1 = new Order_Lombok("o1", "C1", "P1", 1, 10.0, OrderStatus.NEW);
    Order_Lombok order2 = new Order_Lombok("o1", "C1", "P1", 1, 10.0, OrderStatus.NEW);
    Order_Lombok order3 = new Order_Lombok("o2", "C2", "P2", 2, 20.0, OrderStatus.PROCESSING);

    assertThat(order1).isEqualTo(order2);
    assertThat(order1.hashCode()).isEqualTo(order2.hashCode());
    assertThat(order1).isNotEqualTo(order3);
  }

  @Test
  @DisplayName("toString должен возвращать непустую строку")
  void toStringShouldReturnNonEmptyString() {
    Order_Lombok order = new Order_Lombok("idToString", "CustToString", "ProdToString", 1, 1.0, OrderStatus.NEW);
    assertThat(order.toString())
        .isNotNull()
        .contains("Order_Lombok")
        .contains("idToString")
        .contains("status=NEW");
  }
}