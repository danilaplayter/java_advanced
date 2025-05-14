package ru.mentee.power.exceptions.multilevel;

public class InvalidProductFormatException extends DataFormatException {
  private final String invalidLine;
  private final int lineNumber;

  public InvalidProductFormatException(String invalidLine, int lineNumber) {
    super("Ошибочный формат продукта в строке " + lineNumber + ": " + invalidLine);
    this.invalidLine = invalidLine;
    this.lineNumber = lineNumber;
  }

  public InvalidProductFormatException(String invalidLine, int lineNumber, Throwable cause) {
    super("Ошибочный формат продукта в строке " + lineNumber + ": " + invalidLine, cause);
    this.invalidLine = invalidLine;
    this.lineNumber = lineNumber;
  }

  public String getInvalidLine() {
    return invalidLine;
  }

  public int getLineNumber() {
    return lineNumber;
  }
}