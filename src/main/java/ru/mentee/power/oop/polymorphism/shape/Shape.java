package ru.mentee.power.oop.polymorphism.shape;

import java.util.Objects;

/**
 * Абстрактный класс "шаблон" для других классов-фигур.
 */
public abstract class Shape {

  private String color;

  protected Shape(String color) {
    this.color = Objects.requireNonNull(color, "Color cannot be null");
  }

  public String getColor() {
    return color;
  }

  public abstract double getArea();

  public abstract double getPerimeter();

  public void displayInfo() {
    System.out.println("Color: " + color);
  }
}