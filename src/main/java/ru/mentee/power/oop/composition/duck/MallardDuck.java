package ru.mentee.power.oop.composition.duck;

import ru.mentee.power.oop.composition.behavior.impl.FlyWithWings;
import ru.mentee.power.oop.composition.behavior.impl.Quack;

public class MallardDuck extends Duck {
  public MallardDuck() {
    flyBehavior = new FlyWithWings();
    quackBehavior = new Quack();
  }

  @Override
  public void display() {
    System.out.println("Я настоящая дикая утка");
  }
}