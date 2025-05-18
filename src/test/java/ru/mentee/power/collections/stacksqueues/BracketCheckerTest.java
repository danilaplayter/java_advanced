package ru.mentee.power.collections.stacksqueues;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Тесты для BracketChecker")
class BracketCheckerTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "()",
      "[]",
      "{}",
      "()[]{}",
      "([{}])",
      "({[]})(){}",
      "(((())))[]",
      "Текст (с [вложенными] скобками {и без}) абсолютно корректен."
  })
  @DisplayName("Должен возвращать true для сбалансированных скобок")
  void shouldReturnTrueForBalancedBrackets(String expression) {
    assertThat(BracketChecker.areBracketsBalanced(expression)).isTrue();
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "(",
      ")",
      "(()",
      "())(",
      "([)]",
      "{(})",
      "[",
      "}{ A }",
      "( [ { ] } )"
  })
  @DisplayName("Должен возвращать false для несбалансированных скобок")
  void shouldReturnFalseForUnbalancedBrackets(String expression) {
    assertThat(BracketChecker.areBracketsBalanced(expression)).isFalse();
  }

  @Test
  @DisplayName("Должен возвращать true для строки без скобок")
  void shouldReturnTrueForStringWithoutBrackets() {
    assertThat(BracketChecker.areBracketsBalanced("Просто текст без скобок")).isTrue();
  }

  @Test
  @DisplayName("Должен возвращать true для пустой строки")
  void shouldReturnTrueForEmptyString() {
    assertThat(BracketChecker.areBracketsBalanced("")).isTrue();
  }

  @Test
  @DisplayName("Должен возвращать true для null")
  void shouldHandleNullInput() {
    assertThat(BracketChecker.areBracketsBalanced(null)).isTrue();
  }
}