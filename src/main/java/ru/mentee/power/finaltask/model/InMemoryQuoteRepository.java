package ru.mentee.power.finaltask.model;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InMemoryQuoteRepository implements QuoteRepository{

  private final Map<String, Quote> store = new ConcurrentHashMap<>();

  @Override
  public Optional<Quote> findByTicker(String ticker) {
    return Optional.ofNullable(store.get(ticker.toUpperCase()));
  }

  @Override
  public Map<String, Quote> findAll() {
    return store.values().stream().collect(Collectors.toMap(Quote::getTicker, Function.identity()));
  }

  @Override
  public void saveOrUpdate(Quote quote) {
    store.put(quote.getTicker().toUpperCase(), quote);
  }
}
