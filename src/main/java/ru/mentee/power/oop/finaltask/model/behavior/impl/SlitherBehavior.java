package ru.mentee.power.oop.finaltask.model.behavior.impl;

import ru.mentee.power.oop.finaltask.model.behavior.MoveBehavior;

public class SlitherBehavior implements MoveBehavior {

  @Override
  public void move() {
    System.out.println("Ползет...");
  }

}
