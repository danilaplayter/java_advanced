package ru.mentee.power.patterns.decorator.notifier;

import ru.mentee.power.patterns.decorator.notifier.impl.EmailNotifier;
import ru.mentee.power.patterns.decorator.notifier.impl.EncryptionDecorator;
import ru.mentee.power.patterns.decorator.notifier.impl.SignatureDecorator;
import ru.mentee.power.patterns.decorator.notifier.impl.SmsNotifierDecorator;

public class NotificationDemo {
  public static void main(String[] args) {
    String message = "Важное уведомление о вашем аккаунте.";

    Notifier basicNotifier = new EmailNotifier();
    System.out.println("--- Простое Email Уведомление ---");
     basicNotifier.send(message);

    Notifier emailAndSms = new SmsNotifierDecorator(new EmailNotifier());
    System.out.println("\n--- Email + SMS --- ");
     emailAndSms.send(message);

    Notifier encryptedEmail = new EncryptionDecorator(new EmailNotifier());
    System.out.println("\n--- Email + Шифрование --- ");
     encryptedEmail.send(message);

    Notifier combinedNotifier = new EmailNotifier();
     combinedNotifier = new EncryptionDecorator(combinedNotifier);
     combinedNotifier = new SignatureDecorator(combinedNotifier);
     combinedNotifier = new SmsNotifierDecorator(combinedNotifier);
    System.out.println("\n--- Комбинация: Email -> Шифрование -> Подпись -> SMS --- ");
    combinedNotifier.send(message);

    Notifier combinedNotifier2 = new SmsNotifierDecorator(
        new SignatureDecorator(
            new EncryptionDecorator(
                new EmailNotifier())));
     System.out.println("\n--- Другая комбинация ---");
     combinedNotifier2.send(message);

    Notifier customCombination = new SmsNotifierDecorator(
        new EncryptionDecorator(
            new SignatureDecorator(
                new EmailNotifier())));
    System.out.println("\n--- Новая комбинация: Email -> Подпись -> Шифрование -> SMS ---");
    customCombination.send(message);
  }
}