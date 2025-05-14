package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import ru.mentee.power.zoo.UnsupportedFoodException;

abstract class Animal {
  protected String name;
  protected int age;
  protected String food;

  protected Animal(String name, int age, String food) {
    this.name = name;
    this.age = age;
    this.food = food;
  }

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public abstract void eat(String foodType) throws UnsupportedFoodException;

  public void performEat(String foodType) {
    try {
      eat(foodType);
    } catch (UnsupportedFoodException e) {
      System.out.println(getName() + " не может есть " + foodType + ": " + e.getMessage());
    }
  }

  public abstract void displayInfo();
}