package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.RoarBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.WalkBehavior;

public class Lion extends Animal {

  private String maneColor;

  public Lion(String name, int age, String maneColor) {
    super(name, age);
    this.maneColor = maneColor;
    this.moveBehavior = new WalkBehavior();
    this.soundBehavior = new RoarBehavior();
  }

  @Override
  public void displayInfo() {
    System.out.println("Имя: " + getName());
    System.out.println("Возраст: " + getAge());
    System.out.println("Цвет гривы: " + getManeColor());
  }

  public String getManeColor() {
    return maneColor;
  }
}
