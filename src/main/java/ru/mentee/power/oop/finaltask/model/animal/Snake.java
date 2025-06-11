package ru.mentee.power.oop.finaltask.model.animal;

import ru.mentee.power.oop.finaltask.model.behavior.impl.HissBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.impl.SlitherBehavior;

public class Snake extends Animal {
  private final boolean isPoisonous;

  public Snake(String name, int age, boolean isPoisonous) {
    super(name, age, new SlitherBehavior(), new HissBehavior());
    this.isPoisonous = isPoisonous;
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