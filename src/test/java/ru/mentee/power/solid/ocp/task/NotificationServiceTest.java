package ru.mentee.power.solid.ocp.task;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class NotificationServiceTest {

  @Test
  @DisplayName("Должен вызывать метод send у установленной стратегии")
  void shouldCallSendOnSetStrategy() {
    NotificationService notificationService = new NotificationService();
    NotificationStrategy mockStrategy = Mockito.mock(NotificationStrategy.class);
    notificationService.setNotificationMethod(mockStrategy);

    String message = "Тестовое сообщение";
    String recipient = "получатель";
    notificationService.notifyUser(message, recipient);

    verify(mockStrategy, times(1)).send(message, recipient);
  }

  @Test
  @DisplayName("Не должен падать, если стратегия не установлена, но должен вывести сообщение об ошибке")
  void shouldNotFailIfStrategyNotSetAndPrintError() {
    NotificationService notificationService = new NotificationService();
    notificationService.notifyUser("test", "test"); // Ожидается, что не будет NPE
  }
}