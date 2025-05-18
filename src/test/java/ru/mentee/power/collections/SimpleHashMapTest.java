package ru.mentee.power.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для SimpleHashMap")
class SimpleHashMapTest {

  private SimpleHashMap<String, Integer> map;

  @BeforeEach
  void setUp() {
    map = new SimpleHashMap<>();
    map.put("one", 1);
    map.put("two", 2);
    map.put("three", 3);
  }

  @Test
  @DisplayName("put() и get() должны корректно добавлять и получать значения")
  void shouldPutAndGetValues() {
    assertThat(map.get("one")).isEqualTo(1);
    assertThat(map.get("two")).isEqualTo(2);
    assertThat(map.get("three")).isEqualTo(3);
    assertThat(map.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("put() должен заменять значение для существующего ключа и возвращать старое")
  void putShouldReplaceValueForExistingKey() {
    Integer oldValue = map.put("two", 22);
    assertThat(oldValue).isEqualTo(2);
    assertThat(map.get("two")).isEqualTo(22);
    assertThat(map.size()).isEqualTo(3); // Размер не должен измениться
  }

  @Test
  @DisplayName("get() должен возвращать null для несуществующего ключа")
  void getShouldReturnNullForNonExistingKey() {
    assertThat(map.get("four")).isNull();
  }

  @Test
  @DisplayName("containsKey() должен проверять наличие ключа")
  void containsKeyShouldCheckKeyExistence() {
    assertThat(map.containsKey("one")).isTrue();
    assertThat(map.containsKey("four")).isFalse();
  }

  @Test
  @DisplayName("remove() должен удалять пару и возвращать значение")
  void removeShouldDeleteEntryAndReturnValue() {
    Integer removedValue = map.remove("two");
    assertThat(removedValue).isEqualTo(2);
    assertThat(map.containsKey("two")).isFalse();
    assertThat(map.size()).isEqualTo(2);
  }

  @Test
  @DisplayName("remove() должен возвращать null для несуществующего ключа")
  void removeShouldReturnNullForNonExistingKey() {
    Integer removedValue = map.remove("four");
    assertThat(removedValue).isNull();
    assertThat(map.size()).isEqualTo(3);
  }

  @Test
  @DisplayName("Обработка коллизий: добавление элементов с одинаковым индексом корзины")
  void shouldHandleCollisions() {
    // Создаем ключи, которые будут иметь одинаковый хеш
    String key1 = "Aa";
    String key2 = "BB";

    // Проверяем, что хеши одинаковые (для демонстрации)
    assertThat(map.hash(key1)).isEqualTo(map.hash(key2));

    map.put(key1, 100);
    map.put(key2, 200);

    assertThat(map.get(key1)).isEqualTo(100);
    assertThat(map.get(key2)).isEqualTo(200);
    assertThat(map.size()).isEqualTo(5); // 3 из setUp + 2 новых
  }

  @Test
  @DisplayName("resize() должен корректно перестраивать таблицу при росте")
  void resizeShouldRehashTableCorrectly() {
    // Создаем карту с маленькой начальной емкостью
    SimpleHashMap<Integer, String> smallMap = new SimpleHashMap<>(4);

    // Добавляем элементы больше порога (4 * 0.75 = 3)
    for (int i = 0; i < 10; i++) {
      smallMap.put(i, "Value" + i);
    }

    // Проверяем, что все элементы доступны после resize
    for (int i = 0; i < 10; i++) {
      assertThat(smallMap.get(i)).isEqualTo("Value" + i);
    }
    assertThat(smallMap.size()).isEqualTo(10);
  }

  @Test
  @DisplayName("clear() должен удалять все записи")
  void clearShouldRemoveAllEntries() {
    map.clear();
    assertThat(map.isEmpty()).isTrue();
    assertThat(map.size()).isEqualTo(0);
    assertThat(map.get("one")).isNull();
  }
}