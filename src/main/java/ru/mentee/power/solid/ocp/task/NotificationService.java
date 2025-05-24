package ru.mentee.power.solid.ocp.task;

public class NotificationService {

  private NotificationStrategy strategy;

  public void setNotificationMethod(NotificationStrategy strategy) {
    this.strategy = strategy;
  }

  public void notifyUser(String message, String recipient) {
    if (strategy == null) {
      System.err.println("Ошибка: Метод уведомления не установлен!");
      return;
    }
    strategy.send(message, recipient);
  }

  public static void main(String[] args) {
    NotificationService service = new NotificationService();
    String msg = "Ваш заказ #123 готов";
    String emailRecipient = "user@example.com";
    String phoneRecipient = "+79991234567";

    service.setNotificationMethod(new EmailNotification());
    service.notifyUser(msg, emailRecipient);

    service.setNotificationMethod(new SmsNotification());
    service.notifyUser(msg, phoneRecipient);

    service.setNotificationMethod(new TelegramNotification());
    service.notifyUser(msg, "@telegram_user");
  }
}