package ru.mentee.power.exceptions.multilevel;

public class EmptyOrderException extends OrderException {
  public EmptyOrderException() {
    super("Заказ пуст");
  }

  public EmptyOrderException(String message) {
    super(message);
  }
}