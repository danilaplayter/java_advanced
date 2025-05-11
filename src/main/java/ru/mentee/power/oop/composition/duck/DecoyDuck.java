package ru.mentee.power.oop.composition.duck;

import ru.mentee.power.oop.composition.behavior.impl.FlyNoWay;
import ru.mentee.power.oop.composition.behavior.impl.MuteQuack;

public class DecoyDuck extends Duck {
  public DecoyDuck() {
    flyBehavior = new FlyNoWay();
    quackBehavior = new MuteQuack();
  }

  @Override
  public void display() {
    System.out.println("Я деревянная утка-приманка");
  }
}