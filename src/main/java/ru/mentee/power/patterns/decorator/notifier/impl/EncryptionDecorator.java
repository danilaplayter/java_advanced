package ru.mentee.power.patterns.decorator.notifier.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;
import ru.mentee.power.patterns.decorator.notifier.NotifierDecorator;

public class EncryptionDecorator extends NotifierDecorator {

  public EncryptionDecorator(Notifier wrappedNotifier) {
    super(wrappedNotifier);
  }

  @Override
  public void send(String message) {
    String encryptedMessage = "[Encrypted]" + message;
    System.out.println("Шифрование сообщения: [" + message + "] -> [" + encryptedMessage + "]");
    super.send(encryptedMessage);
  }
}
