package ru.mentee.power.oop.finaltask.model.animal;

import lombok.Getter;
import ru.mentee.power.oop.finaltask.model.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.impl.FlyBehavior;

@Getter
public class Eagle extends Animal {

  private double wingSpan;

  public Eagle(String name, int age, double wingSpan) {
    super(name, age, new FlyBehavior(), new ChirpBehavior());
    this.wingSpan = wingSpan;
  }

  @Override
  public void displayInfo() {
    System.out.println("Имя: " + getName());
    System.out.println("Возраст: " + getAge());
    System.out.println("Размах крыльев: " + getWingSpan());
  }
}
