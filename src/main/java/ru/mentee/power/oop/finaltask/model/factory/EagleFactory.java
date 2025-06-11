package ru.mentee.power.oop.finaltask.model.factory;

import ru.mentee.power.oop.finaltask.model.animal.Animal;
import ru.mentee.power.oop.finaltask.model.animal.Eagle;

public class EagleFactory extends AnimalFactory{

  private final double wingSpan;

  public EagleFactory(double wingSpan){
    this.wingSpan = wingSpan;
  }

  @Override
  public Animal createAnimal(String name, int age) {
    return new Eagle(name, age, wingSpan);
  }
}
