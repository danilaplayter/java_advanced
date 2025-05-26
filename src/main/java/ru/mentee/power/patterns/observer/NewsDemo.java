package ru.mentee.power.patterns.observer;

import ru.mentee.power.patterns.observer.agency.Subject;
import ru.mentee.power.patterns.observer.agency.impl.NewsAgency;
import ru.mentee.power.patterns.observer.subscriber.impl.NewsSubscriber;

public class NewsDemo {
  public static void main(String[] args) {
    NewsAgency agency = new NewsAgency();

    NewsSubscriber subscriber1 = new NewsSubscriber("Подписчик 1", agency);
    NewsSubscriber subscriber2 = new NewsSubscriber("Подписчик 2", agency);
    NewsSubscriber subscriber3 = new NewsSubscriber("Подписчик 3");

    agency.registerObserver(subscriber1);
    agency.registerObserver(subscriber2);
    agency.registerObserver(subscriber3);

    System.out.println("--- Публикуем первую новость ---");
    agency.publishNews("В Java 21 вышли виртуальные потоки!");

    System.out.println("\n--- Мария отписывается ---");
    agency.removeObserver(subscriber2);

    System.out.println("\n--- Публикуем вторую новость ---");
    agency.publishNews("Паттерн Наблюдатель очень полезен!");
  }
}