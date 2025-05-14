package ru.mentee.power.exceptions.multilevel;

public class ProductItem {
  private String name;
  private int quantity;
  private double price;

  public ProductItem(String name, int quantity, double price) {
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getPrice() {
    return price;
  }

  public double calculateTotal() {
    return quantity * price;
  }

  @Override
  public String toString() {
    return String.format("%s;%d;%.2f", name, quantity, price);
  }
}