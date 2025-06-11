package ru.mentee.power.oop.finaltask.controller;

import java.util.ArrayList;
import java.util.List;
import ru.mentee.power.oop.finaltask.model.animal.Animal;
import ru.mentee.power.oop.finaltask.model.factory.AnimalFactory;
import ru.mentee.power.oop.finaltask.model.factory.EagleFactory;
import ru.mentee.power.oop.finaltask.model.factory.LionFactory;
import ru.mentee.power.oop.finaltask.model.factory.SnakeFactory;
import ru.mentee.power.oop.finaltask.model.behavior.impl.ChirpBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.impl.FlyBehavior;
import ru.mentee.power.oop.finaltask.model.behavior.impl.RoarBehavior;
import ru.mentee.power.oop.finaltask.view.ZooView;

public class ZooController {
  private final ZooView view;
  private final List<Animal> animals = new ArrayList<>();

  public ZooController(ZooView view) {
    this.view = view;
  }

  public void createAnimals(){
    AnimalFactory eagleFactory = new EagleFactory(2.5);
    AnimalFactory lionFactory = new LionFactory("жёлтый");
    AnimalFactory snakeFactory = new SnakeFactory(false);
    animals.add(lionFactory.createAnimal("Гоша", 18));
    animals.add(eagleFactory.createAnimal("Гриша", 22));
    animals.add(snakeFactory.createAnimal("Саша", 18));
  }

  public void startSafari() {
    view.showWelcome();

    createAnimals();

    view.showMessage("----- Обход вольеров -----");
    for (Animal animal : animals) {
      view.showAnimalInfo(animal);
    }

  }

  public void performBehaviorChanges(){
    Animal lion = animals.get(0);
    Animal eagle = animals.get(1);
    Animal snake = animals.get(2);

    view.showMessage("\n----- Эксперимент с поведением -----");

    view.showMessage("\nЛев научился летать: ");
    lion.setMoveBehavior(new FlyBehavior());
    view.showAnimalInfo(lion);

    view.showMessage("\nОрёл начал рычать: ");
    eagle.setSoundBehavior(new RoarBehavior());
    view.showAnimalInfo(eagle);

    view.showMessage("\nЗмея научилась летать и щебетать");
    snake.setSoundBehavior(new ChirpBehavior());
    snake.setMoveBehavior(new FlyBehavior());
    view.showAnimalInfo(snake);

  }

}
