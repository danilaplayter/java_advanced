package ru.mentee.power.streams;

enum OrderStatus { NEW, PROCESSING, SHIPPED, DELIVERED, CANCELLED }

class Order {
  private final String orderId;
  private final String customerName;
  private final String productName;
  private final int quantity;
  private final double pricePerUnit;
  private final OrderStatus status;

  public Order(String orderId, String customerName, String productName,
      int quantity, double pricePerUnit, OrderStatus status) {
    this.orderId = orderId;
    this.customerName = customerName;
    this.productName = productName;
    this.quantity = quantity;
    this.pricePerUnit = pricePerUnit;
    this.status = status;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public String getProductName() {
    return productName;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPricePerUnit() {
    return pricePerUnit;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public double getTotalPrice() {
    return quantity * pricePerUnit;
  }

  @Override
  public String toString() {
    return "Order{" +
        "orderId='" + orderId + '\'' +
        ", customerName='" + customerName + '\'' +
        ", productName='" + productName + '\'' +
        ", quantity=" + quantity +
        ", pricePerUnit=" + pricePerUnit +
        ", status=" + status +
        ", total=" + getTotalPrice() +
        '}';
  }
}