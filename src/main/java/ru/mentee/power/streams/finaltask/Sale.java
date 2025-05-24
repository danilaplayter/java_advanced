package ru.mentee.power.streams.finaltask;

import java.util.Objects;

public class Sale {
  private final String saleId;
  private final Customer customer;
  private final Product product;
  private final int quantity;

  public Sale(String saleId, Customer customer, Product product, int quantity) {
    this.saleId = Objects.requireNonNull(saleId, "Sale ID cannot be null");
    this.customer = Objects.requireNonNull(customer, "Customer cannot be null");
    this.product = Objects.requireNonNull(product, "Product cannot be null");
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be positive");
    }
    this.quantity = quantity;
  }

  public String getSaleId() {
    return saleId;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  /**
   * Рассчитывает общую стоимость этой продажи.
   * @return Общая стоимость (цена продукта * количество).
   */
  public double getTotalPrice() {
    return quantity * product.getPrice();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Sale sale = (Sale) o;
    return saleId.equals(sale.saleId); // Сравнение только по ID
  }

  @Override
  public int hashCode() {
    return Objects.hash(saleId); // Хеш-код только по ID
  }

  @Override
  public String toString() {
    return "Sale{" +
        "saleId='" + saleId + '\'' +
        ", customer=" + customer.getName() +
        ", product=" + product.getName() +
        ", quantity=" + quantity +
        ", totalPrice=" + String.format("%.2f", getTotalPrice()) +
        '}';
  }
}