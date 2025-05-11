package ru.mentee.power.oop.polymorphism.shape;

/**
 * Класс для описания кружа через наследование от Shape.
 */
public class Circle extends Shape {

  private double radius;

  /**
   * Конструктор с валидацией.
   */
  public Circle(String color, double radius) {
    super(color);
    if (radius <= 0) {
      throw new IllegalArgumentException("Radius must be positive");
    }
    this.radius = radius;
  }

  @Override
  public double getArea() {
    return Math.PI * radius * radius;
  }

  @Override
  public double getPerimeter() {
    return 2 * Math.PI * radius;
  }

  @Override
  public void displayInfo() {
    System.out.println("Type: Circle");
    super.displayInfo();
    System.out.printf("Radius: %.2f%n", radius);
  }
}