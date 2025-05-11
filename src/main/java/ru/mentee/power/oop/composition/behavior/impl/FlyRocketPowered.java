package ru.mentee.power.oop.composition.behavior.impl;

import ru.mentee.power.oop.composition.behavior.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
  @Override
  public void fly() {
    System.out.println("Я лечу на ракете!!!");
  }
}