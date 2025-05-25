package ru.mentee.power.solid.lsp.task;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mentee.power.solid.lsp.task.solution.NotificationException;

class PushNotificationLspTest {

  // ------ ПРИМЕРЫ ТЕСТОВ ДЛЯ ИСПРАВЛЕННОЙ ВЕРСИИ (ВАРИАНТ А: NotificationException) ------
  // Предположим, вы изменили NotificationStrategy и PushNotification
  // для использования NotificationException

  interface FixedNotificationStrategy {

    void send(String message, String recipient) throws NotificationException;
  }

  class FixedPushNotification implements FixedNotificationStrategy {

    @Override
    public void send(String message, String recipient) throws NotificationException {
      if (recipient == null || !recipient.startsWith("device-")) {
        throw new NotificationException("FIXED: Неверный deviceId для PUSH: " + recipient);
      }
      System.out.println(
          "FIXED: Отправка PUSH сообщения '" + message + "' на устройство " + recipient);
    }
  }

  @Test
  @DisplayName("[РЕШЕНИЕ LSP - Вариант А] FixedPushNotification бросает NotificationException")
  void fixedPushNotification_shouldThrowNotificationException_whenRecipientIsInvalid() {
    FixedNotificationStrategy pushNotifier = new FixedPushNotification();
    String message = "Тест";
    String invalidRecipient = "not-a-device-id";

    assertThatThrownBy(() -> pushNotifier.send(message, invalidRecipient))
        .isInstanceOf(NotificationException.class)
        .hasMessageContaining("FIXED: Неверный deviceId для PUSH");
  }

  class FixedNotificationSender {

    public void sendNotification(FixedNotificationStrategy strategy, String msg, String recipient) {
      System.out.println(
          "\n--- Попытка отправки через " + strategy.getClass().getSimpleName() + " (FIXED) ---");
      try {
        strategy.send(msg, recipient);
        System.out.println("Вызов send() завершен.");
      } catch (NotificationException e) {
        System.err.println("Ошибка уведомления: " + e.getMessage());
      } catch (Exception e) {
        System.err.println("Неожиданная ошибка при отправке: " + e.getMessage());
      }
    }
  }

  @Test
  @DisplayName("[РЕШЕНИЕ LSP - Вариант А] FixedNotificationSender корректно обрабатывает NotificationException")
  void fixedNotificationSender_handlesNotificationException() {
    FixedNotificationSender sender = new FixedNotificationSender();
    FixedNotificationStrategy pushNotifier = new FixedPushNotification();
    String message = "Тест";
    assertDoesNotThrow(() -> sender.sendNotification(pushNotifier, message, "invalid-recipient"));
  }
}