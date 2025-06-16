package ru.mentee.power.finaltask.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

public class QuoteService {

  private final QuoteRepository quoteRepository;
  private final QuoteDataSource quoteDataSource;
  private final List<QuoteUpdateListener> listeners = new ArrayList<>();

  public QuoteService(QuoteRepository quoteRepository, QuoteDataSource quoteDataSource) {
    this.quoteRepository = quoteRepository;
    this.quoteDataSource = quoteDataSource;
  }

  public void addListener(QuoteUpdateListener listener){
      listeners.add(listener);
  }

  public void removeListener(QuoteUpdateListener listener){
    listeners.remove(listener);
  }

  private void notifyListeners(Map<String, Quote> updatedQuotes){
    for(QuoteUpdateListener listener : listeners){
      listener.onQuotesUpdated(updatedQuotes);
    }
  }

  public void updateQuotes() throws DataFetchingException {
    List<Quote> quotes = quoteDataSource.fetchQuotes();
    quotes.forEach(quoteRepository::saveOrUpdate);
    notifyListeners(quoteRepository.findAll());
  }

  public Optional<Quote> getQuote(String ticker){
    return quoteRepository.findByTicker(ticker);
  }

  public Map<String, Quote> getAllQuotes(){
    return  quoteRepository.findAll();
  }
}
