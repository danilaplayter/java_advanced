package ru.mentee.power.finaltask.controller;

import java.util.Map;
import java.util.Optional;
import ru.mentee.power.finaltask.model.Quote;
import ru.mentee.power.finaltask.model.QuoteService;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

public class QuoteController {
  private final QuoteService quoteService;
  private final ConsoleQuoteView view;

  public QuoteController(QuoteService quoteService, ConsoleQuoteView view) {
    this.quoteService = quoteService;
    this.view = view;
  }

  public void run() {
    boolean running = true;

    while (running) {
      view.displayMenu();
      String command = view.getCommand();

      switch (command.toLowerCase()) {
        case "list":
          Map<String, Quote> quotes = quoteService.getAllQuotes();
          view.displayQuotes(quotes);
          break;

        case "update":
          try {
            quoteService.updateQuotes();
            view.showMessage("Котировки успешно обновлены.");
          } catch (DataFetchingException e) {
            view.showError("Ошибка при обновлении котировок: " + e.getMessage());
          }
          break;

        case "exit":
          running = false;
          view.showMessage("Завершение работы приложения...");
          break;

        default:
          if (command.startsWith("get ")) {
            String ticker = command.substring(4).trim();
            if (!ticker.isEmpty()) {
              Optional<Quote> quote = quoteService.getQuote(ticker);
              view.displayQuote(quote, ticker);
            } else {
              view.showError("Укажите тикер после команды 'get'");
            }
          } else {
            view.showError("Неизвестная команда: " + command);
          }
      }
    }
  }
}