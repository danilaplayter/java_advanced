package ru.mentee.power.solid.lsp.task.solution;

public class NotificationException extends Exception {
  public NotificationException(String message) {
    super(message);
  }

  public NotificationException(String message, Throwable cause) {
    super(message, cause);
  }
}