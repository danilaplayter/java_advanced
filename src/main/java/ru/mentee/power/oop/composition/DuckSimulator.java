package ru.mentee.power.oop.composition;

import ru.mentee.power.oop.composition.behavior.impl.FlyRocketPowered;
import ru.mentee.power.oop.composition.duck.*;

public class DuckSimulator {
  public static void main(String[] args) {
    System.out.println("--- Симулятор Уток Запущен ---");

    // Mallard Duck
    Duck mallard = new MallardDuck();
    mallard.display();
    mallard.performQuack();
    mallard.performFly();
    mallard.swim();

    // Rubber Duck
    Duck rubberDuck = new RubberDuck();
    rubberDuck.display();
    rubberDuck.performQuack();
    rubberDuck.performFly();
    rubberDuck.swim();

    // Decoy Duck
    Duck decoyDuck = new DecoyDuck();
    decoyDuck.display();
    decoyDuck.performQuack();
    decoyDuck.performFly();
    decoyDuck.swim();

    // Model Duck with dynamic behavior change
    Duck modelDuck = new ModelDuck();
    modelDuck.display();
    modelDuck.performFly();

    // Change behavior at runtime
    modelDuck.setFlyBehavior(new FlyRocketPowered());
    modelDuck.performFly();

    System.out.println("\n--- Симулятор Уток Завершен ---");
  }
}