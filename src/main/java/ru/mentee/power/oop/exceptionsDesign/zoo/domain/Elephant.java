package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import exceptionsDesign.zoo.UnsupportedFoodException;

class Elephant extends Animal {
  public Elephant(String name, int age, String food) {
    super(name, age, food);
  }

  public Elephant(String name, int age) {
    super(name, age);
  }

  @Override
  public void eat(String foodType) throws UnsupportedFoodException {
    if ("фрукты".equalsIgnoreCase(foodType) || "овощи".equalsIgnoreCase(foodType)) {
      System.out.println(getName() + " ест " + foodType);
    } else {
      throw new UnsupportedFoodException(name, food, "Слоны едят только фрукты и овощи");
    }
  }

  @Override
  public void displayInfo() {
    System.out.println("Слон по имени " + getName() + ", возраст: " + getAge() + " лет");
  }
}