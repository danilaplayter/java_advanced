package ru.mentee.power.oop.finaltask.model.animal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.mentee.power.oop.finaltask.model.behavior.MoveBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.SoundBehavior;

@AllArgsConstructor
@Getter
@Setter
public abstract class Animal {

  private final String name;
  private int age;

  protected MoveBehavior moveBehavior;
  protected SoundBehavior soundBehavior;

  public abstract void displayInfo();

  public void performMove() {
    moveBehavior.move();
  }

  public void performSound() {
    soundBehavior.sound();
  }

}