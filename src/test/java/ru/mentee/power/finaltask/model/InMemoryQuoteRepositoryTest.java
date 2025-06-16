package ru.mentee.power.finaltask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InMemoryQuoteRepositoryTest {

  private InMemoryQuoteRepository repository;

  @BeforeEach
  void setUp() {
    repository = new InMemoryQuoteRepository();
  }

  @Test
  void findByTicker_shouldReturnEmptyForNonExistentTicker() {
    Optional<Quote> result = repository.findByTicker("AAPL");
    assertTrue(result.isEmpty());
  }

  @Test
  void findByTicker_shouldReturnQuoteForExistingTicker() {
    Quote quote = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    repository.saveOrUpdate(quote);

    Optional<Quote> result = repository.findByTicker("AAPL");
    assertTrue(result.isPresent());
    assertEquals(quote, result.get());
  }

  @Test
  void findByTicker_shouldBeCaseInsensitive() {
    Quote quote = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    repository.saveOrUpdate(quote);

    Optional<Quote> result = repository.findByTicker("aapl");
    assertTrue(result.isPresent());
    assertEquals(quote, result.get());
  }

  @Test
  void findAll_shouldReturnAllQuotes() {
    Quote quote1 = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    Quote quote2 = new Quote("GOOG", BigDecimal.valueOf(2750.3), LocalDateTime.now());

    repository.saveOrUpdate(quote1);
    repository.saveOrUpdate(quote2);

    Map<String, Quote> result = repository.findAll();
    assertEquals(2, result.size());
    assertEquals(quote1, result.get("AAPL"));
    assertEquals(quote2, result.get("GOOG"));
  }

  @Test
  void saveOrUpdate_shouldUpdateExistingQuote() {
    Quote initialQuote = new Quote("AAPL", BigDecimal.valueOf(150.5), LocalDateTime.now());
    Quote updatedQuote = new Quote("AAPL", BigDecimal.valueOf(155.7), LocalDateTime.now());

    repository.saveOrUpdate(initialQuote);
    repository.saveOrUpdate(updatedQuote);

    Optional<Quote> result = repository.findByTicker("AAPL");
    assertTrue(result.isPresent());
    assertEquals(updatedQuote.getPrice(), result.get().getPrice());
  }
}