package ru.mentee.power.oop.finaltask.model.behavior.impl;

import ru.mentee.power.oop.finaltask.model.behavior.SoundBehavior;

public class ChirpBehavior implements SoundBehavior {

  @Override
  public void sound() {
    System.out.println("Щебечет...");
  }
}
