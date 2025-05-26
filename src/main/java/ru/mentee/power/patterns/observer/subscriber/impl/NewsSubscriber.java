package ru.mentee.power.patterns.observer.subscriber.impl;

import ru.mentee.power.patterns.observer.agency.Subject;
import ru.mentee.power.patterns.observer.subscriber.Observer;

public class NewsSubscriber implements Observer {
  private final String name;
  private Subject subject;

  public NewsSubscriber(String name) {
    this.name = name;
  }

  public NewsSubscriber(String name, Subject subject) {
    this.name = name;
    this.subject = subject;
  }

  @Override
  public void update(String news) {
    System.out.println(name + " получил новость: " + news);
  }

  public void unsubscribe() {
    if (subject != null) {
      subject.removeObserver(this);
      System.out.println(name + " отписался от новостей");
    }
  }
}