package ru.mentee.power.oop.finaltask.model.animal;

import ru.mentee.power.oop.finaltask.model.behavior.impl.RoarBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.impl.WalkBehavior;

public class Lion extends Animal {

  private String maneColor;

  public Lion(String name, int age, String maneColor) {
    super(name, age, new WalkBehavior(), new RoarBehavior());
    this.maneColor = maneColor;
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
