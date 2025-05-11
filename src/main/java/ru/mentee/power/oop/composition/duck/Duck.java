package ru.mentee.power.oop.composition.duck;

import ru.mentee.power.oop.composition.behavior.FlyBehavior;
import ru.mentee.power.oop.composition.behavior.QuackBehavior;

public abstract class Duck {
  protected FlyBehavior flyBehavior;
  protected QuackBehavior quackBehavior;

  public Duck() { }

  public void swim() {
    System.out.println("Все утки плавают...");
  }

  public abstract void display();

  public void performFly() {
    flyBehavior.fly();
  }

  public void performQuack() {
    quackBehavior.quack();
  }

  public void setFlyBehavior(FlyBehavior fb) {
    flyBehavior = fb;
  }

  public void setQuackBehavior(QuackBehavior qb) {
    quackBehavior = qb;
  }
}