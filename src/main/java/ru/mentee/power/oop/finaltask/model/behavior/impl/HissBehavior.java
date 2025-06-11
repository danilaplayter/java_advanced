package ru.mentee.power.oop.finaltask.model.behavior.impl;

import ru.mentee.power.oop.finaltask.model.behavior.SoundBehavior;

public class HissBehavior implements SoundBehavior {

  @Override
  public void sound() {
    System.out.println("Шипит...");
  }
}
