package ru.mentee.power.oop.polymorphism.shape;

/**
 * Класс для описания треугольника через наследование от Shape.
 */
public class Triangle extends Shape {

  private double sideA;
  private double sideB;
  private double sideC;

  /**
   * Конструктор с валидацией.
   */
  public Triangle(String color, double sideA, double sideB, double sideC) {
    super(color);
    if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
      throw new IllegalArgumentException("All sides must be positive");
    }
    if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
      throw new IllegalArgumentException("Invalid triangle sides");
    }
    this.sideA = sideA;
    this.sideB = sideB;
    this.sideC = sideC;
  }

  @Override
  public double getArea() {
    double s = getPerimeter() / 2;
    return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
  }

  @Override
  public double getPerimeter() {
    return sideA + sideB + sideC;
  }

  @Override
  public void displayInfo() {
    System.out.println("Type: Triangle");
    super.displayInfo();
    System.out.printf("Sides: %.2f, %.2f, %.2f%n", sideA, sideB, sideC);
  }
}