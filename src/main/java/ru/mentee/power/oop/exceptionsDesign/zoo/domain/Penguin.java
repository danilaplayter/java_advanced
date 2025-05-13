package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import exceptionsDesign.zoo.UnsupportedFoodException;

class Penguin extends Animal {
  public Penguin(String name, int age, String food) {
    super(name, age, food);
  }

  public Penguin(String name, int age) {
    super(name, age);
  }

  @Override
  public void eat(String foodType) throws UnsupportedFoodException {
    if ("рыба".equalsIgnoreCase(foodType)) {
      System.out.println(getName() + " ест " + foodType);
    } else {
      throw new UnsupportedFoodException(name, " едят только ", food);
    }
  }

  @Override
  public void displayInfo() {
    System.out.println("Пингвин по имени " + getName() + ", возраст: " + getAge() + " лет");
  }
}