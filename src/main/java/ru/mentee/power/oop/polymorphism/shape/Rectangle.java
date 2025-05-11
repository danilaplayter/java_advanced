package ru.mentee.power.oop.polymorphism.shape;

/**
 * Класс для описания прямоугольника через наследование от Shape.
 */
public class Rectangle extends Shape {

  private double width;
  private double height;

  /**
   * Конструктор с валидацией.
   */
  public Rectangle(String color, double width, double height) {
    super(color);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
    this.width = width;
    this.height = height;
  }

  @Override
  public double getArea() {
    return width * height;
  }

  @Override
  public double getPerimeter() {
    return 2 * (width + height);
  }

  @Override
  public void displayInfo() {
    System.out.println("Type: Rectangle");
    super.displayInfo();
    System.out.printf("Width: %.2f, Height: %.2f%n", width, height);
  }
}