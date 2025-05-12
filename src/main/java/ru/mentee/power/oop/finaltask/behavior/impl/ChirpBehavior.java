package ru.mentee.power.oop.finaltask.behavior.impl;

import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;

public class ChirpBehavior implements SoundBehavior {

  @Override
  public void sound() {
    System.out.println("Щебечет...");
  }
}
