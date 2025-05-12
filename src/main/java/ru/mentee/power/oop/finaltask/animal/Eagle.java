package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.FlyBehavior;

public class Eagle extends Animal {

  private double wingSpan;

  public Eagle(String name, int age, double wingSpan) {
    super(name, age);
    this.wingSpan = wingSpan;
    this.soundBehavior = new ChirpBehavior();
    this.moveBehavior = new FlyBehavior();
  }

  @Override
  public void displayInfo() {
    System.out.println("Имя: " + getName());
    System.out.println("Возраст: " + getAge());
    System.out.println("Размах крыльев: " + getWingSpan());
  }

  public double getWingSpan() {
    return wingSpan;
  }
}
