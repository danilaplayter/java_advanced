package ru.mentee.power.solid.lsp.task;

import ru.mentee.power.solid.lsp.task.solution.NotificationException;

public class TelegramNotification implements NotificationStrategy {
  @Override
  public void send(String message, String recipient) throws NotificationException {
    if (recipient == null || !recipient.startsWith("@")) {
      throw new NotificationException("TELEGRAM Ошибка: Неверный username " + recipient);
    }
    System.out.println("Отправка TELEGRAM сообщения '" + message + "' получателю " + recipient);
  }
}
