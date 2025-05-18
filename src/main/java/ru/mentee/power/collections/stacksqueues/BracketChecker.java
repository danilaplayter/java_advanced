package ru.mentee.power.collections.stacksqueues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class BracketChecker {

  private static final Map<Character, Character> BRACKET_PAIRS = Map.of(
      ')', '(',
      '}', '{',
      ']', '['
  );

  public static boolean areBracketsBalanced(String expression) {
    if (expression == null) {
      return true;
    }

    Deque<Character> stack = new ArrayDeque<>();

    for (char ch : expression.toCharArray()) {
      if (BRACKET_PAIRS.containsValue(ch)) {
        stack.push(ch);
      } else if (BRACKET_PAIRS.containsKey(ch)) {
        if (stack.isEmpty()) {
          return false;
        }

        char top = stack.pop();
        if (top != BRACKET_PAIRS.get(ch)) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }
}