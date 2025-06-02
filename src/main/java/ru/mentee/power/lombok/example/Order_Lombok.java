package ru.mentee.power.lombok.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Order_Lombok {
  private final String orderId;
  private String customerName;
  private String productName;
  private int quantity;
  private double pricePerUnit;
  private OrderStatus status;

  public double getTotalPrice() {
    return quantity * pricePerUnit;
  }
}

enum OrderStatus { NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED }