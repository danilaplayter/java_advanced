package ru.mentee.power.finaltask.model.exceptions;

public class QuoteAppException extends RuntimeException {
  public QuoteAppException(String message) {
    super(message);
  }

  public QuoteAppException(String message, Throwable cause) {
    super(message, cause);
  }
}
