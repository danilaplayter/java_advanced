package ru.mentee.power.exceptions.multilevel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ReportGenerator {
  private String reportPath;

  public ReportGenerator(String reportPath) {
    this.reportPath = reportPath;
  }

  public void generateReport(List<ProductItem> products, double totalPrice) throws FileWriteException {
    Path path = Paths.get(reportPath);

    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
      writer.write("=== Отчёт заказа ===\n");
      writer.write("Продукты:\n");

      for (ProductItem product : products) {
        writer.write(String.format("- %s: %d x %.2f = %.2f\n",
            product.getName(),
            product.getQuantity(),
            product.getPrice(),
            product.calculateTotal()));
      }

      writer.write(String.format("\nИтого: %.2f\n", totalPrice));
    } catch (IOException e) {
      throw new FileWriteException(reportPath, e);
    }
  }
}