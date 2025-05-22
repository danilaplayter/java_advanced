package ru.mentee.power.streams;

import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class StreamCollectorsTaskTest {
  private List<Order> orders;

  @BeforeEach
  void setUp() {
    orders = List.of(
        new Order("O1", "Alice", "Laptop", 1, 1200.0, OrderStatus.DELIVERED),
        new Order("O2", "Bob", "Mouse", 2, 25.0, OrderStatus.SHIPPED),
        new Order("O3", "Alice", "Keyboard", 1, 75.0, OrderStatus.PROCESSING),
        new Order("O4", "Charlie", "Monitor", 1, 300.0, OrderStatus.DELIVERED),
        new Order("O5", "Bob", "Webcam", 1, 50.0, OrderStatus.DELIVERED),
        new Order("O6", "Alice", "Mouse", 1, 25.0, OrderStatus.CANCELLED),
        new Order("O7", "Charlie", "Keyboard", 1, 80.0, OrderStatus.NEW)
    );
  }

  @Test
  void testUniqueProductNames() {
    Set<String> uniqueProductNames = orders.stream()
        .map(Order::getProductName)
        .collect(Collectors.toSet());

    assertEquals(5, uniqueProductNames.size());
    assertTrue(uniqueProductNames.contains("Laptop"));
    assertTrue(uniqueProductNames.contains("Mouse"));
    assertTrue(uniqueProductNames.contains("Keyboard"));
    assertTrue(uniqueProductNames.contains("Monitor"));
    assertTrue(uniqueProductNames.contains("Webcam"));
  }

  @Test
  void testOrderMap() {
    Map<String, Order> orderMap = orders.stream()
        .collect(Collectors.toMap(Order::getOrderId, order -> order, (first, second) -> first));

    assertEquals(7, orderMap.size());
    assertEquals("Laptop", orderMap.get("O1").getProductName());
    assertEquals("Mouse", orderMap.get("O2").getProductName());
    assertEquals("Keyboard", orderMap.get("O3").getProductName());
  }

  @Test
  void testTotalSum() {
    double totalSum = orders.stream()
        .mapToDouble(Order::getTotalPrice)
        .sum();

    assertEquals(1200.0 + 50.0 + 75.0 + 300.0 + 50.0 + 25.0 + 80.0, totalSum);
  }

  @Test
  void testOrdersByStatus() {
    Map<OrderStatus, List<Order>> ordersByStatus = orders.stream()
        .collect(Collectors.groupingBy(Order::getStatus));

    assertEquals(3, ordersByStatus.get(OrderStatus.DELIVERED).size());
    assertEquals(1, ordersByStatus.get(OrderStatus.SHIPPED).size());
    assertEquals(1, ordersByStatus.get(OrderStatus.PROCESSING).size());
    assertEquals(1, ordersByStatus.get(OrderStatus.CANCELLED).size());
    assertEquals(1, ordersByStatus.get(OrderStatus.NEW).size());
  }

  @Test
  void testTotalAmountByCustomer() {
    Map<String, Double> totalAmountByCustomer = orders.stream()
        .collect(Collectors.groupingBy(
            Order::getCustomerName,
            Collectors.summingDouble(Order::getTotalPrice)
        ));

    assertEquals(1200.0 + 75.0 + 25.0, totalAmountByCustomer.get("Alice"));
    assertEquals(50.0 + 50.0, totalAmountByCustomer.get("Bob"));
    assertEquals(300.0 + 80.0, totalAmountByCustomer.get("Charlie"));
  }

  @Test
  void testPartitionedOrders() {
    Map<Boolean, List<Order>> partitionedOrders = orders.stream()
        .collect(Collectors.partitioningBy(
            order -> order.getStatus() == OrderStatus.DELIVERED
        ));

    assertEquals(3, partitionedOrders.get(true).size());
    assertEquals(4, partitionedOrders.get(false).size());
  }

  @Test
  void testOrderIdsString() {
    String orderIdsString = orders.stream()
        .map(Order::getOrderId)
        .collect(Collectors.joining(", "));

    assertEquals("O1, O2, O3, O4, O5, O6, O7", orderIdsString);
  }
}