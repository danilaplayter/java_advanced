package ru.mentee.power.finaltask.view;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import ru.mentee.power.finaltask.model.Quote;
import ru.mentee.power.finaltask.model.QuoteUpdateListener;

public class ConsoleQuoteView implements QuoteUpdateListener {

  private Scanner scanner;

  public ConsoleQuoteView() {
    this.scanner = new Scanner(System.in);
  }

  public void displayQuotes(Map<String, Quote> quotes){
    if(quotes.isEmpty()){
      System.out.println("Котировки не доступны.");
      return;
    }

    System.out.println("\nТекущие котировки акций:");
    System.out.println("+--------+------------+---------------------+");
    System.out.println("| Ticker |    Цена    | Последнее обновление|");
    System.out.println("+--------+------------+---------------------+");

    quotes.values().forEach(quote -> {
      System.out.printf("| %-6s | %-10.2f | %-19s |\n",
          quote.getTicker(),
          quote.getPrice(),
          quote.getLastUpdate());
    });

    System.out.println("+--------+------------+---------------------+");
  }

  public void displayQuote(Optional<Quote> quote, String ticker) {
    if (quote.isPresent()) {
      Quote q = quote.get();
      System.out.println("\nДетали котировки:");
      System.out.println("+--------+------------+---------------------+");
      System.out.println("| Тикер  | Цена       | Последнее обновление|");
      System.out.println("+--------+------------+---------------------+");
      System.out.printf("| %-6s | %-10.2f | %-19s |\n",
          q.getTicker(),
          q.getPrice(),
          q.getLastUpdate());
      System.out.println("+--------+------------+---------------------+");
    } else {
      System.out.println("\nКотировка для тикера '" + ticker + "' не найдена.");
    }
  }

  public void displayMenu() {
    System.out.println("\nМеню:");
    System.out.println("1. list - Показать все котировки");
    System.out.println("2. get <тикер> - Найти котировку по тикеру");
    System.out.println("3. update - Обновить котировки из источника");
    System.out.println("4. exit - Выход из приложения");
  }

  public String getCommand() {
    System.out.print("\nВведите команду: ");
    return scanner.nextLine().trim();
  }

  public String getTickerSymbol() {
    System.out.print("Введите символ тикера: ");
    return scanner.nextLine().trim().toUpperCase();
  }

  public void showMessage(String message) {
    System.out.println(message);
  }

  public void showError(String error) {
    System.err.println("Ошибка: " + error);
  }

  @Override
  public void onQuotesUpdated(Map<String, Quote> updatedQuotes) {
    System.out.println("\nУведомление:Котировки были обновлены!");
    displayQuotes(updatedQuotes);
  }
}
