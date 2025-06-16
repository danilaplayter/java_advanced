package ru.mentee.power.finaltask.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.List;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

public class JsonQuoteDataSource implements QuoteDataSource {

  private final String filePath;
  private final ObjectMapper objectMapper;

  public JsonQuoteDataSource(String filePath) {
    this.filePath = filePath;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
    this.objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
  }

  @Override
  public List<Quote> fetchQuotes() throws DataFetchingException {
    try {
      File file = new File(filePath);

      if (!file.exists()) {
        System.out.println("Файл не найден: ");
      }

      if (file.length() == 0) {
        System.out.println("Файл пуст: ");
      }

      List<Quote> quotes = objectMapper.readValue(file, new TypeReference<List<Quote>>() {});

      if (quotes.isEmpty()) {
        System.out.println("Файл не содержит котировок");
      }

      return quotes;
    } catch (IOException e) {
      throw new DataFetchingException("Ошибка при чтении JSON-файла: " + e.getMessage(), e);
    }
  }
}
