package ru.mentee.power.oop.finaltask.behavior.impl;

import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;

public class RoarBehavior implements SoundBehavior {

  @Override
  public void sound() {
    System.out.println("Рычит...");
  }
}
