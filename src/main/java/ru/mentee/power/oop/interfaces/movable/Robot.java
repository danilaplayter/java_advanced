package ru.mentee.power.oop.interfaces.movable;

/**
 * Класс для определения поведения и характеристик робота.
 */
public class Robot implements Movable {

  private String serialNumber;

  public Robot(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  @Override
  public void move() {
    System.out.println("Робот " + serialNumber + " шагает");
  }

  @Override
  public void stop() {
    System.out.println("Робот " + serialNumber + " прекращает движение!");
  }

  public String getSerialNumber() {
    return serialNumber;
  }
}