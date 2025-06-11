package ru.mentee.power.oop.finaltask.model.factory;

import ru.mentee.power.oop.finaltask.model.animal.Animal;
import ru.mentee.power.oop.finaltask.model.animal.Snake;

public class SnakeFactory extends AnimalFactory {
  private final boolean isPoisonous;

  public SnakeFactory(boolean isPoisonous){
    this.isPoisonous = isPoisonous;
  }

  @Override
  public Animal createAnimal(String name, int age) {
    return new Snake(name, age, isPoisonous);
  }
}
