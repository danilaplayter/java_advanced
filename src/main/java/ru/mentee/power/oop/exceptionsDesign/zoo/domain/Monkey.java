package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import exceptionsDesign.zoo.UnsupportedFoodException;

class Monkey extends Animal {
  public Monkey(String name, int age, String food) {
    super(name, age, food);
  }

  public Monkey(String name, int age) {
    super(name, age);
  }

  @Override
  public void eat(String foodType) throws UnsupportedFoodException {
    if ("бананы".equalsIgnoreCase(foodType)) {
      System.out.println(getName() + " ест " + foodType);
    } else {
      throw new UnsupportedFoodException(name, " едят только ", food);
    }
  }

  @Override
  public void displayInfo() {
    System.out.println("Обезьяна по имени " + getName() + ", возраст: " + getAge() + " лет");
  }
}