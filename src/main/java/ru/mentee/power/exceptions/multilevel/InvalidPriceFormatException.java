package ru.mentee.power.exceptions.multilevel;

public class InvalidPriceFormatException extends DataFormatException {
  private final String invalidValue;

  public InvalidPriceFormatException(String invalidValue) {
    super("Ошибочный формат цены: " + invalidValue);
    this.invalidValue = invalidValue;
  }

  public InvalidPriceFormatException(String invalidValue, Throwable cause) {
    super("Ошибочный формат цены: " + invalidValue, cause);
    this.invalidValue = invalidValue;
  }

  public String getInvalidValue() {
    return invalidValue;
  }
}