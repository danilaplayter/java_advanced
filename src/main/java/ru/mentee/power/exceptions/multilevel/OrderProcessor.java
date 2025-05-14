package ru.mentee.power.exceptions.multilevel;

import java.util.ArrayList;
import java.util.List;

public class OrderProcessor {
  public List<ProductItem> parseProducts(List<String> lines) throws DataFormatException {
    List<ProductItem> products = new ArrayList<>();
    int lineNumber = 0;

    for (String line : lines) {
      lineNumber++;
      if (line == null || line.trim().isEmpty()) {
        continue;
      }

      String[] parts = line.split(";");
      if (parts.length != 3) {
        throw new InvalidProductFormatException(line, lineNumber);
      }

      try {
        String name = parts[0].trim();
        int quantity = Integer.parseInt(parts[1].trim());
        double price = Double.parseDouble(parts[2].trim());

        products.add(new ProductItem(name, quantity, price));
      } catch (NumberFormatException e) {
        throw new InvalidPriceFormatException(line, e);
      }
    }

    return products;
  }

  public double calculateOrderTotal(List<ProductItem> products) throws OrderException {
    if (products == null || products.isEmpty()) {
      throw new EmptyOrderException();
    }

    double total = 0.0;
    for (ProductItem product : products) {
      try {
        total += product.calculateTotal();
      } catch (Exception e) {
        throw new PriceCalculationException(product.getName(), e);
      }
    }

    return total;
  }
}