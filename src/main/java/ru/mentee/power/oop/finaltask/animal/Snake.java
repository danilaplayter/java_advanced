package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.impl.HissBehavior;
import ru.mentee.power.oop.finaltask.behavior.impl.SlitherBehavior;

public class Snake extends Animal {
  private final boolean isPoisonous;

  public Snake(String name, int age, boolean isPoisonous) {
    super(name, age);
    this.isPoisonous = isPoisonous;
    this.moveBehavior = new SlitherBehavior();
    this.soundBehavior = new HissBehavior();
  }

  @Override
  public void displayInfo() {
    System.out.println("Имя: " + getName());
    System.out.println("Возраст: " + getAge());
    System.out.println("Ядовитость: " + isPoisonous);  }

  public boolean isPoisonous() {
    return isPoisonous;
  }


}