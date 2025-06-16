package ru.mentee.power.finaltask.model;

import java.util.List;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

public interface QuoteDataSource {
  List<Quote> fetchQuotes() throws DataFetchingException;
}
