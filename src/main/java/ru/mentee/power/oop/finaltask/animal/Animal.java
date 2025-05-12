package ru.mentee.power.oop.finaltask.animal;

import ru.mentee.power.oop.finaltask.behavior.MoveBehavior;
import ru.mentee.power.oop.finaltask.behavior.SoundBehavior;

public abstract class Animal {

  private final String name;
  private int age;

  protected MoveBehavior moveBehavior;
  protected SoundBehavior soundBehavior;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public abstract void displayInfo();

  public void performMove() {
    moveBehavior.move();
  }

  public void performSound() {
    soundBehavior.sound();
  }

  public void setMoveBehavior(MoveBehavior mb) {
    this.moveBehavior = mb;
  }

  public void setSoundBehavior(SoundBehavior sb) {
    this.soundBehavior = sb;
  }

  public MoveBehavior getMoveBehavior() {
    return moveBehavior;
  }

  public SoundBehavior getSoundBehavior() {
    return soundBehavior;
  }
}