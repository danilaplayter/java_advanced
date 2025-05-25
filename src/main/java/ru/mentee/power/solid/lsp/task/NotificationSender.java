package ru.mentee.power.solid.lsp.task;

import ru.mentee.power.solid.lsp.task.solution.NotificationException;

public class NotificationSender {

  public void sendNotification(NotificationStrategy strategy, String msg, String recipient) {
    System.out.println("\n--- Попытка отправки через " + strategy.getClass().getSimpleName() + " ---");
    try {
      strategy.send(msg, recipient);
      System.out.println("Вызов send() завершен.");
    } catch (NotificationException e) {
      System.err.println("Неожиданная ошибка при отправке: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    NotificationSender sender = new NotificationSender();
    String message = "Тестовое уведомление";

    sender.sendNotification(new EmailNotification(), message, "test@example.com");
    sender.sendNotification(new EmailNotification(), message, "invalid-email");
    sender.sendNotification(new SmsNotification(), message, "+79123456789");
    sender.sendNotification(new SmsNotification(), message, "not-a-phone");
    sender.sendNotification(new TelegramNotification(), message, "@my_telegram");
    sender.sendNotification(new TelegramNotification(), message, "no_at_sign");
    sender.sendNotification(new PushNotification(), message, "device-123");
    sender.sendNotification(new PushNotification(), message, "231");
  }

}
