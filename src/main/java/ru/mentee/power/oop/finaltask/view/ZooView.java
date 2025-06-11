package ru.mentee.power.oop.finaltask.view;

import ru.mentee.power.oop.finaltask.model.animal.Animal;

public class ZooView {
  public void showWelcome(){
    System.out.println("Добро пожаловать в OOP Safari!");
  }

  public void showAnimalInfo(Animal animal){
    animal.displayInfo();
    animal.performMove();
    animal.performSound();
    System.out.println();
  }

  public void showMessage(String message){
    System.out.println(message);
  }

  public void showGoodBye(){
    System.out.println("\nЭкскурсия по OOP Safari завершена!");
  }
}
