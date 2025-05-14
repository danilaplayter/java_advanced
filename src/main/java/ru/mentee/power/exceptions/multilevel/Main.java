package ru.mentee.power.exceptions.multilevel;

public class Main {
  public static void main(String[] args) {
    if (args.length != 2) {
      System.err.println("Использование: java Main <входной_файл> <выходной_файл>");
      System.exit(1);
    }

    String inputFile = args[0];
    String outputFile = args[1];

    OrderService orderService = new OrderService(inputFile, outputFile);

    try {
      orderService.processOrder();
      System.out.println("Заказ успешно обработан!");
    } catch (OrderProcessingException e) {
      System.err.println("Ошибка обработки заказа: " + e.getMessage());

      Throwable cause = e;
      while (cause != null) {
        System.err.println("- " + cause.getClass().getSimpleName() + ": " + cause.getMessage());
        cause = cause.getCause();
      }

      System.exit(1);
    }
  }
}