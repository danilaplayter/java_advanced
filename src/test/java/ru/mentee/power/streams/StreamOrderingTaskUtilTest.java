package ru.mentee.power.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class StreamOrderingTaskUtilTest {

  private List<Transaction> testTransactions;
  private Transaction tx1, tx2, tx3, tx1Dup, tx4, tx5, tx6, tx7, tx8, tx9;

  @BeforeEach
  void setUp() {
    tx1 = new Transaction("tx1", 100.0, "USD", "New York");
    tx2 = new Transaction("tx2", 250.5, "EUR", "London");
    tx3 = new Transaction("tx3", 100.0, "USD", "Tokyo");
    tx1Dup = new Transaction("tx1", 150.0, "USD", "New York");
    tx4 = new Transaction("tx4", 500.0, "USD", "London");
    tx5 = new Transaction("tx5", 50.0, "EUR", "Paris");
    tx6 = new Transaction("tx6", 75.0, "USD", "Tokyo");
    tx7 = new Transaction("tx7", 300.0, "USD", "New York");
    tx8 = new Transaction("tx8", 120.0, "GBP", "London");
    tx9 = new Transaction("tx9", 200.0, "USD", "Paris");

    testTransactions = List.of(tx1, tx2, tx3, tx1Dup, tx4, tx5, tx6, tx7, tx8, tx9);
  }

  @Test
  @DisplayName("getUniqueTransactionIds: Возвращает уникальные ID")
  void getUniqueTransactionIds_ShouldReturnUniqueIds() {
    List<String> result = StreamOrderingTaskUtil.getUniqueTransactionIds(testTransactions);
    assertThat(result).containsExactlyInAnyOrder("tx1", "tx2", "tx3", "tx4", "tx5", "tx6", "tx7", "tx8", "tx9");
  }

  @Test
  @DisplayName("getUniqueTransactionIds: Пустой или null список")
  void getUniqueTransactionIds_EmptyOrNull_ShouldReturnEmptyList() {
    assertThat(StreamOrderingTaskUtil.getUniqueTransactionIds(Collections.emptyList())).isEmpty();
    assertThat(StreamOrderingTaskUtil.getUniqueTransactionIds(null)).isEmpty();
  }

  @Test
  @DisplayName("getTopNTransactionsByCurrency: Находит топ N USD транзакций")
  void getTopNTransactionsByCurrency_Usd_ShouldReturnTopN() {
    List<Transaction> result = StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", 3);
    assertThat(result).hasSize(3);
    assertThat(result).extracting(Transaction::getCurrency).containsOnly("USD");
    assertThat(result).extracting(Transaction::getAmount).containsExactly(500.0, 300.0, 200.0);
  }

  @Test
  @DisplayName("getTopNTransactionsByCurrency: Несуществующая валюта или topN<=0")
  void getTopNTransactionsByCurrency_InvalidParams_ShouldReturnEmptyList() {
    assertThat(StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "XYZ", 3)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", 0)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, "USD", -1)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getTopNTransactionsByCurrency(testTransactions, null, 3)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getTopNTransactionsByCurrency(null, "USD", 3)).isEmpty();
  }

  @Test
  @DisplayName("getSkippedTransactionsSortedByCity: Пропуск и сортировка по городу")
  void getSkippedTransactionsSortedByCity_ShouldSkipAndSort() {
    List<Transaction> result = StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, 2);
    assertThat(result).hasSize(testTransactions.size() - 2);
    assertThat(result).extracting(Transaction::getCity)
        .isSortedAccordingTo(String::compareTo);
  }

  @Test
  @DisplayName("getSkippedTransactionsSortedByCity: SkipCount >= size или < 0, или null список")
  void getSkippedTransactionsSortedByCity_InvalidParams_ShouldHandleCorrectly() {
    assertThat(StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, testTransactions.size())).isEmpty();
    assertThat(StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, testTransactions.size() + 1)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(testTransactions, -1)).isEmpty();
    assertThat(StreamOrderingTaskUtil.getSkippedTransactionsSortedByCity(null, 2)).isEmpty();
  }
}