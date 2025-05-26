package ru.mentee.power.patterns.observer.agency.impl;

import ru.mentee.power.patterns.observer.agency.Subject;
import ru.mentee.power.patterns.observer.subscriber.Observer;

import java.util.ArrayList;
import java.util.List;

public class NewsAgency implements Subject {
  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void registerObserver(Observer o) {
    if (!observers.contains(o)) {  // Проверка на дубликаты
      observers.add(o);
    }
  }

  @Override
  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers(String news) {
    for (Observer observer : observers) {
      observer.update(news);
    }
  }

  public void publishNews(String news) {
    System.out.println("NewsAgency публикует новость: " + news);
    notifyObservers(news);
  }
}