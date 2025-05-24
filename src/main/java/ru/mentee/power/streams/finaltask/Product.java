package ru.mentee.power.streams.finaltask;

import java.util.Objects;

public class Product {
  private final String id;
  private final String name;
  private final String category;
  private final double price;

  public Product(String id, String name, String category, double price) {
    this.id = Objects.requireNonNull(id, "Product ID cannot be null");
    this.name = Objects.requireNonNull(name, "Product name cannot be null");
    this.category = Objects.requireNonNull(category, "Product category cannot be null");
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative");
    }
    this.price = price;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return id.equals(product.id); // Сравнение только по ID
  }

  @Override
  public int hashCode() {
    return Objects.hash(id); // Хеш-код только по ID
  }

  @Override
  public String toString() {
    return "Product{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", category='" + category + '\'' +
        ", price=" + String.format("%.2f", price) +
        '}';
  }
}