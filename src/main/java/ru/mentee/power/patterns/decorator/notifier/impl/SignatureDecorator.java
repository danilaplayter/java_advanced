package ru.mentee.power.patterns.decorator.notifier.impl;

import ru.mentee.power.patterns.decorator.notifier.Notifier;
import ru.mentee.power.patterns.decorator.notifier.NotifierDecorator;

public class SignatureDecorator extends NotifierDecorator {

  public SignatureDecorator(Notifier notifier) {
    super(notifier);
  }

  @Override
  public void send(String message) {
    String signedMessage = message + "\n--\nС уважением, Команда";
    super.send(signedMessage);
  }
}
