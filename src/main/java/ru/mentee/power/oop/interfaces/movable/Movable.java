package ru.mentee.power.oop.interfaces.movable;

/**
 * Интерфейс для определения поведения движущихся объектов.
 */
public interface Movable {

  void move();

  default void stop() {
    System.out.println("Остановка объекта");
  }

  static void describeInterface() {
    System.out.println("Интерфейс Movable определяет способность двигаться.");
  }
}