package ru.mentee.power.oop.finaltask.model.factory;

import ru.mentee.power.oop.finaltask.model.animal.Animal;

public abstract class AnimalFactory {
  public abstract Animal createAnimal(String name, int age);
}
