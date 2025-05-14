package ru.mentee.power.exceptions.multilevel;

public class PriceCalculationException extends OrderException {
  private final String productName;

  public PriceCalculationException(String productName) {
    super("Ошибка вычисления товара: " + productName);
    this.productName = productName;
  }

  public PriceCalculationException(String productName, Throwable cause) {
    super("Ошибка вычисления товара: " + productName, cause);
    this.productName = productName;
  }

  public String getProductName() {
    return productName;
  }
}