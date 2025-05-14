package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import ru.mentee.power.zoo.UnsupportedFoodException;

class Lion extends Animal {

  public Lion(String name, int age, String food) {
    super(name, age, food);
  }

  public Lion(String name, int age) {
    super(name, age);
  }

  @Override
  public void eat(String foodType) throws UnsupportedFoodException {
    if ("мясо".equalsIgnoreCase(foodType)) {
      System.out.println(getName() + " ест " + foodType);
    } else {
      throw new UnsupportedFoodException(name, " едят только ", food);
    }
  }

  @Override
  public void displayInfo() {
    System.out.println("Лев по имени " + getName() + ", возраст: " + getAge() + " лет");
  }
}