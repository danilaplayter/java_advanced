package ru.mentee.power.oop.finaltask;
import ru.mentee.power.oop.finaltask.animal.*;
import ru.mentee.power.oop.finaltask.behavior.impl.*;
import java.util.ArrayList;
import java.util.List;

public class ZooDemo {
  public static void main(String[] args) {
    System.out.println("Добро пожаловать в OOP Safari!");

    List<Animal> animals = new ArrayList<>();

    // Создаем животных
    Lion lion = new Lion("Гоша", 17, "жёлтый");
    Eagle eagle = new Eagle("Гриша", 22, 1.2);
    Snake snake = new Snake("Саша", 10, false);

    animals.add(lion);
    animals.add(eagle);
    animals.add(snake);

    System.out.println("\n--- Обход вольеров ---");

    for(Animal animal : animals) {
      animal.displayInfo();
      animal.performMove();
      animal.performSound();
      System.out.println();
    }

    System.out.println("\n--- Эксперименты с поведением ---");

    System.out.println("\nЛев решил научиться летать:");
    lion.setMoveBehavior(new FlyBehavior());
    lion.displayInfo();
    lion.performMove();

    System.out.println("\nОрел перенял манеру льва рычать:");
    eagle.setSoundBehavior(new RoarBehavior());
    eagle.displayInfo();
    eagle.performSound();

    System.out.println("\nЗмея превратилась в птицу:");
    snake.setMoveBehavior(new WalkBehavior());
    snake.setSoundBehavior(new ChirpBehavior());
    snake.displayInfo();
    snake.performMove();
    snake.performSound();

    System.out.println("\nЭкскурсия по OOP Safari завершена!");

  }
}