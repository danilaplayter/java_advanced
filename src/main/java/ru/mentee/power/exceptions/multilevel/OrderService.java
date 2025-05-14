package ru.mentee.power.exceptions.multilevel;

import java.util.List;

public class OrderService {

  private DataSource dataSource;
  private OrderProcessor processor;
  private ReportGenerator reportGenerator;

  public OrderService(String inputFilePath, String outputFilePath) {
    this.dataSource = new DataSource(inputFilePath);
    this.processor = new OrderProcessor();
    this.reportGenerator = new ReportGenerator(outputFilePath);
  }

  public void processOrder() throws OrderProcessingException {
    try {
      List<String> lines = dataSource.readLines();

      List<ProductItem> products = processor.parseProducts(lines);
      double total = processor.calculateOrderTotal(products);

      reportGenerator.generateReport(products, total);
    } catch (FileOperationException e) {
      throw new OrderProcessingException("Ошибка операции с файлом при обработке заказа", e);
    } catch (DataFormatException e) {
      throw new OrderProcessingException("Ошибка формата данных при обработке заказа", e);
    } catch (OrderException e) {
      throw new OrderProcessingException("Ошибка обработки заказа", e);
    }
  }
}