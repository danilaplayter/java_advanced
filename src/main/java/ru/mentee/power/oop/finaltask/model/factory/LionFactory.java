package ru.mentee.power.oop.finaltask.model.factory;

import ru.mentee.power.oop.finaltask.model.animal.Animal;
import ru.mentee.power.oop.finaltask.model.animal.Lion;

public class LionFactory extends AnimalFactory{
  private final String maneColor;

  public LionFactory(String maneColor){
    this.maneColor = maneColor;
  }

  @Override
  public Animal createAnimal(String name, int age) {
    return new Lion(name, age, maneColor);
  }
}
