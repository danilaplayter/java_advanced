package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import exceptionsDesign.zoo.ZooException;

public class ZooApplicationDemo {
  public static void main(String[] args) {
    ZooManager zooManager = new ZooManager();

    // Create animals
    Animal simba = new Lion("Симба", 5);
    Animal rafiki = new Monkey("Рафики", 8);
    Animal dumbo = new Elephant("Дамбо", 3);
    Animal skipper = new Penguin("Скиппер", 2);

    // Add animals to zoo
    zooManager.addAnimalToZoo(simba);
    zooManager.addAnimalToZoo(rafiki);
    zooManager.addAnimalToZoo(dumbo);
    zooManager.addAnimalToZoo(skipper);

    // Demonstrate successful operations
    try {
      System.out.println("\nУспешные операции:");
      zooManager.placeAnimalInHabitat("Симба", "Саванна");
      zooManager.placeAnimalInHabitat("Рафики", "Джунгли");
      zooManager.feedAnimal("Симба", "мясо");
      zooManager.feedAnimal("Рафики", "бананы");
    } catch (ZooException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

    // Demonstrate exception cases
    System.out.println("\nТестирование исключений:");

    // Animal not found
    zooManager.feedAnimal("Муфаса", "мясо");

    // Wrong habitat
    try {
      zooManager.placeAnimalInHabitat("Скиппер", "Джунгли");
    } catch (ZooException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

    // Wrong food
    zooManager.feedAnimal("Рафики", "мясо");

    // Habitat full
    try {
      zooManager.placeAnimalInHabitat("Дамбо", "Саванна"); // First time - OK
      Animal scar = new Lion("Шрам", 6);
      zooManager.addAnimalToZoo(scar);
      zooManager.placeAnimalInHabitat("Шрам", "Саванна"); // Should fail - capacity 2
    } catch (ZooException e) {
      System.out.println("Ошибка: " + e.getMessage());
    }

    // Show final state
    zooManager.listAllAnimals();
    zooManager.listHabitatsAndAnimals();
  }

}
