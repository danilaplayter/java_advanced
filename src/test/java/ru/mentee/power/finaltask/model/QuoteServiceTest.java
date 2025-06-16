package ru.mentee.power.finaltask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

  @Mock
  private QuoteRepository quoteRepository;

  @Mock
  private QuoteDataSource quoteDataSource;

  @Mock
  private QuoteUpdateListener listener;

  private QuoteService quoteService;

  @BeforeEach
  void setUp() {
    quoteService = new QuoteService(quoteRepository, quoteDataSource);
  }

  @Test
  void updateQuotes_shouldFetchAndSaveQuotes() throws DataFetchingException {
    Quote quote1 = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    Quote quote2 = new Quote("GOOG", BigDecimal.valueOf(2750.3), LocalDateTime.now());
    List<Quote> quotes = List.of(quote1, quote2);

    when(quoteDataSource.fetchQuotes()).thenReturn(quotes);
    when(quoteRepository.findAll()).thenReturn(Map.of("AAPL", quote1, "GOOG", quote2));

    quoteService.addListener(listener);
    quoteService.updateQuotes();

    verify(quoteDataSource).fetchQuotes();
    verify(quoteRepository).saveOrUpdate(quote1);
    verify(quoteRepository).saveOrUpdate(quote2);
    verify(listener).onQuotesUpdated(anyMap());
  }

  @Test
  void getQuote_shouldDelegateToRepository() {
    String ticker = "AAPL";
    Quote quote = new Quote(ticker, BigDecimal.valueOf(150.5), LocalDateTime.now());
    when(quoteRepository.findByTicker(ticker)).thenReturn(Optional.of(quote));

    Optional<Quote> result = quoteService.getQuote(ticker);

    assertTrue(result.isPresent());
    assertEquals(quote, result.get());
    verify(quoteRepository).findByTicker(ticker);
  }

  @Test
  void getAllQuotes_shouldDelegateToRepository() {
    Quote quote = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    when(quoteRepository.findAll()).thenReturn(Map.of("AAPL", quote));

    Map<String, Quote> result = quoteService.getAllQuotes();

    assertEquals(1, result.size());
    assertEquals(quote, result.get("AAPL"));
    verify(quoteRepository).findAll();
  }

  @Test
  void listeners_shouldBeNotifiedOnUpdate() throws DataFetchingException {
    Quote quote = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    when(quoteDataSource.fetchQuotes()).thenReturn(List.of(quote));
    when(quoteRepository.findAll()).thenReturn(Map.of("AAPL", quote));

    quoteService.addListener(listener);
    quoteService.updateQuotes();

    verify(listener).onQuotesUpdated(anyMap());

    quoteService.removeListener(listener);
    quoteService.updateQuotes();

    verify(listener, times(1)).onQuotesUpdated(anyMap());
  }
}