package ru.mentee.power.solid.lsp.task;

import ru.mentee.power.solid.lsp.task.solution.NotificationException;

class EmailNotification implements NotificationStrategy {

  @Override
  public void send(String message, String recipient) throws NotificationException {
    if (recipient == null || !recipient.contains("@")) {
      System.err.println("EMAIL Ошибка: Неверный адрес получателя " + recipient);
      throw new NotificationException("Неверный формат email адреса: " + recipient);
    }
    System.out.println("Отправка EMAIL сообщения '" + message + "' получателю " + recipient);
  }
}