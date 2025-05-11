package ru.mentee.power.oop.interfaces.movable;

/**
 * Класс для описания машины.
 */
public class Car implements Movable {

  private String model;

  public Car(String model) {
    this.model = model;
  }

  @Override
  public void move() {
    System.out.println("Машина " + model + " едет по дороге");
  }

  public String getModel() {
    return model;
  }

}
