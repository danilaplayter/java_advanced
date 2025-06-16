package ru.mentee.power.finaltask.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.mentee.power.finaltask.model.exceptions.DataFetchingException;

class JsonQuoteDataSourceTest {

  @TempDir
  Path tempDir;

  @Test
  void fetchQuotes_shouldReadValidJsonFile() throws IOException, DataFetchingException {
    // Создаем временный JSON файл
    String jsonContent = """
        [
            {
                "ticker": "AAPL",
                "price": 150.5,
                "lastUpdate": "2023-05-15T10:15:30"
            },
            {
                "ticker": "GOOG",
                "price": 2750.3,
                "lastUpdate": "2023-05-15T10:16:45"
            }
        ]
        """;
    File jsonFile = tempDir.resolve("quotes.json").toFile();
    Files.writeString(jsonFile.toPath(), jsonContent);

    JsonQuoteDataSource dataSource = new JsonQuoteDataSource(jsonFile.getAbsolutePath());
    List<Quote> quotes = dataSource.fetchQuotes();

    assertEquals(2, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
    assertEquals(BigDecimal.valueOf(150.5), quotes.get(0).getPrice());
    assertEquals("GOOG", quotes.get(1).getTicker());
    assertEquals(BigDecimal.valueOf(2750.3), quotes.get(1).getPrice());
  }

  @Test
  void fetchQuotes_shouldThrowExceptionForNonExistentFile() {
    String nonExistentPath = tempDir.resolve("nonexistent.json").toString();
    JsonQuoteDataSource dataSource = new JsonQuoteDataSource(nonExistentPath);

    assertThrows(DataFetchingException.class, dataSource::fetchQuotes);
  }

  @Test
  void fetchQuotes_shouldThrowExceptionForEmptyFile() throws IOException {
    File emptyFile = tempDir.resolve("empty.json").toFile();
    emptyFile.createNewFile();

    JsonQuoteDataSource dataSource = new JsonQuoteDataSource(emptyFile.getAbsolutePath());

    assertThrows(DataFetchingException.class, dataSource::fetchQuotes);
  }

  @Test
  void fetchQuotes_shouldThrowExceptionForInvalidJson() throws IOException {
    File invalidJsonFile = tempDir.resolve("invalid.json").toFile();
    Files.writeString(invalidJsonFile.toPath(), "invalid json");

    JsonQuoteDataSource dataSource = new JsonQuoteDataSource(invalidJsonFile.getAbsolutePath());

    assertThrows(DataFetchingException.class, dataSource::fetchQuotes);
  }
}