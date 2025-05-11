package ru.mentee.power.oop.interfaces.movable;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для демонстрации работы интерфейса Movable и классов Car и Robot.
 */
public class MovableDemo {

  /**
   * Метод для демонстрации.
   */
  public static void main(String[] args) {
    Movable.describeInterface();

    List<Movable> movables = new ArrayList<>();

    movables.add(new Car("Audi"));
    movables.add(new Robot("123456"));

    System.out.println("\n--- Демонстрация движения и остановки ---");

    for (Movable movable : movables) {
      movable.move();
      movable.stop();
      System.out.println("------");
    }
  }
}