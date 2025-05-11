package ru.mentee.power.oop.polymorphism.shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для демонстрации.
 */
public class ShapeProcessor {

  /**
   * Основной класс для демонстрации.
   */
  public static void main(String[] args) {
    List<Shape> shapes = new ArrayList<>();
    shapes.add(new Circle("Red", 5.0));
    shapes.add(new Rectangle("Blue", 4.0, 6.0));
    shapes.add(new Triangle("Green", 3.0, 4.0, 5.0));

    processShapes(shapes);
  }

  /**
   * Метод для обработки фигур.
   */
  public static void processShapes(List<Shape> shapes) {
    System.out.println("\n--- Обработка фигур ---");

    for (Shape shape : shapes) {
      shape.displayInfo();
      System.out.printf("Area: %.2f%n", shape.getArea());
      System.out.printf("Perimeter: %.2f%n%n", shape.getPerimeter());
    }
  }
}