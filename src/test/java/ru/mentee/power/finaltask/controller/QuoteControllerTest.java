package ru.mentee.power.finaltask.controller;

import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mentee.power.finaltask.model.Quote;
import ru.mentee.power.finaltask.model.QuoteService;
import ru.mentee.power.finaltask.view.ConsoleQuoteView;

@ExtendWith(MockitoExtension.class)
class QuoteControllerTest {

  @Mock
  private QuoteService quoteService;

  @Mock
  private ConsoleQuoteView view;

  private QuoteController controller;

  @BeforeEach
  void setUp() {
    controller = new QuoteController(quoteService, view);
  }

  @Test
  void run_shouldHandleListCommand() {
    when(view.getCommand()).thenReturn("list", "exit");
    when(quoteService.getAllQuotes()).thenReturn(
        Map.of("AAPL", new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now())));

    controller.run();

    verify(view, times(2)).displayMenu();
    verify(quoteService).getAllQuotes();
    verify(view).displayQuotes(anyMap());
    verify(view).showMessage("Завершение работы приложения...");
  }

  @Test
  void run_shouldHandleUpdateCommandSuccessfully() {
    when(view.getCommand()).thenReturn("update", "exit");

    controller.run();

    verify(view, times(2)).displayMenu();
    verify(quoteService).updateQuotes();
    verify(view).showMessage("Котировки успешно обновлены.");
  }

  @Test
  void run_shouldHandleGetCommandWithTicker() {
    String ticker = "GOOG";
    Quote quote = new Quote(ticker, BigDecimal.valueOf(2750.3), LocalDateTime.now());
    when(view.getCommand()).thenReturn("get " + ticker, "exit");
    when(quoteService.getQuote(ticker)).thenReturn(Optional.of(quote));

    controller.run();

    verify(view, times(2)).displayMenu();
    verify(quoteService).getQuote(ticker);
    verify(view).displayQuote(Optional.of(quote), ticker);
  }

  @Test
  void run_shouldHandleGetCommandWithoutTicker() {
    when(view.getCommand()).thenReturn("get ", "exit");

    controller.run();

    verify(view, times(2)).displayMenu(); // Ожидаем 2 вызова
    verify(view).showError("Укажите тикер после команды 'get'");
    verifyNoInteractions(quoteService);
  }

  @Test
  void run_shouldHandleUnknownCommand() {
    when(view.getCommand()).thenReturn("unknown", "exit");

    controller.run();

    verify(view, times(2)).displayMenu();
    verify(view).showError("Неизвестная команда: unknown");
    verifyNoInteractions(quoteService);
  }
}