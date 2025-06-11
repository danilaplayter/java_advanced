package ru.mentee.power.oop.finaltask.model.behavior.impl;

import ru.mentee.power.oop.finaltask.model.behavior.MoveBehavior;

public class WalkBehavior implements MoveBehavior {

  @Override
  public void move() {
    System.out.println("Идет...");
  }
}

