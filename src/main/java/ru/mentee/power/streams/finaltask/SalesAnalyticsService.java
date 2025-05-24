package ru.mentee.power.streams.finaltask;

import java.util.*;
import java.util.stream.Collectors;

public class SalesAnalyticsService {

  public Optional<Sale> findSaleById(List<Sale> sales, String saleId) {
    return sales.stream()
        .filter(t -> t.getSaleId().equals(saleId))
        .findFirst();
  }

  public Set<String> findProductNamesSoldToCustomer(List<Sale> sales, String customerId) {
    return sales.stream()
        .filter(t -> t.getCustomer().getId().equals(customerId))
        .map(t -> t.getProduct().getName())
        .collect(Collectors.toSet());

  }

  public Set<Customer> findCustomersByCity(List<Sale> sales, String city) {
    return sales.stream()
        .map(Sale::getCustomer)
        .filter(customer -> customer.getCity().equals(city))
        .distinct()
        .collect(Collectors.toSet());
  }

  public double calculateTotalRevenue(List<Sale> sales) {
    return sales.stream()
        .mapToDouble(sale -> sale.getProduct().getPrice() *sale.getQuantity())
        .sum();
  }

  public double calculateTotalRevenueByCategory(List<Sale> sales, String category) {
    return sales.stream()
        .filter(sale -> sale.getProduct().getCategory().equals(category))
        .mapToDouble(sale -> sale.getProduct().getPrice() * sale.getQuantity())
        .sum();
  }

  public Optional<Product> findMostExpensiveProductSold(List<Sale> sales) {
    return sales.stream()
        .map(Sale::getProduct)
        .filter(Objects::nonNull)
        .max(Comparator.comparingDouble(Product::getPrice));
  }

  public Map<Customer, List<Sale>> groupSalesByCustomer(List<Sale> sales) {
    return sales.stream()
        .collect(Collectors.groupingBy(Sale::getCustomer));
  }

  public IntSummaryStatistics getProductSalesStatistics(List<Sale> sales, String productId) {
    return sales.stream()
        .filter(sale -> sale.getProduct().getId().equals(productId))
        .mapToInt(Sale::getQuantity)
        .summaryStatistics();
  }

  public String getCustomerCities(List<Sale> sales) {
    return sales.stream()
        .map(sale -> sale.getCustomer().getCity())
        .distinct()
        .sorted()
        .collect(Collectors.joining(", "));

  }

  public boolean checkIfProductWasSoldInCategory(List<Sale> sales, String category, String productName) {
    return sales.stream()
        .anyMatch(sale -> sale.getProduct().getCategory().equals(category)
            && sale.getProduct().getName().equals(productName));
  }
}