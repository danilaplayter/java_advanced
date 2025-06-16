package ru.mentee.power.finaltask.model;

import java.util.Map;

public interface QuoteUpdateListener {
  void onQuotesUpdated(Map<String, Quote> updatedQuotes);
}
