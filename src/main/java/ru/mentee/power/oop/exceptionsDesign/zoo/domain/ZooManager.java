package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import ru.mentee.power.zoo.AnimalNotFoundException;
import ru.mentee.power.zoo.ZooException;
import java.util.ArrayList;
import java.util.List;

public class ZooManager {

  private List<Animal> allAnimals = new ArrayList<>();
  private List<Habitat> allHabitats = new ArrayList<>();

  public ZooManager() {
    allHabitats.add(new Habitat("Саванна", 5));
    allHabitats.add(new Habitat("Джунгли", 10));
  }

  public void addAnimalToZoo(Animal animal) {
    allAnimals.add(animal);
    System.out.println("Животное " + animal.getName() + " добавлено в зоопарк");
  }

    public Animal findAnimalByName(String name) throws AnimalNotFoundException {
    for(Animal animal : allAnimals){
      if(animal.getName().equalsIgnoreCase(name)){
          return animal;
      }
    }
    throw new AnimalNotFoundException("Не найдено: " + name);
  }

  public Habitat findHabitatByType(String type) throws HabitatFullException {
    for (Habitat h : allHabitats) {
      if (h.getType().equalsIgnoreCase(type)) {
        return h;
      }
    }
    throw new HabitatFullException("Не найдено: " + type);
  }

  public void placeAnimalInHabitat(String animalName, String habitatType) throws ZooException {
    try {
      Animal animal = findAnimalByName(animalName);
      Habitat habitat = findHabitatByType(habitatType);

      try {
        habitat.addAnimal(animal);
        System.out.println("Животное " + animalName + " успешно помещено в вольер " + habitatType);
      } catch (IncompatibleHabitatException e) {
        throw new ZooException("Не удалось разместить животное: " + e.getMessage(), e);
      }
    } catch (AnimalNotFoundException | HabitatFullException e) {
      throw new ZooException("Ошибка при размещении животного: " + e.getMessage(), e);
    }
  }

  public void feedAnimal(String animalName, String foodType) {
    try {
      Animal animal = findAnimalByName(animalName);
      animal.performEat(foodType);
    } catch (AnimalNotFoundException e) {
      System.out.println("Ошибка кормления: " + e.getMessage());
    }
  }

  public void listAllAnimals() {
    System.out.println("\nВсе животные в зоопарке:");
    for (Animal animal : allAnimals) {
      animal.displayInfo();
    }
  }

  public void listHabitatsAndAnimals() {
    System.out.println("\nСостояние вольеров:");
    for (Habitat habitat : allHabitats) {
      System.out.println("Вольер " + habitat.getType() + ":");
      for (Animal animal : habitat.getAnimalsInHabitat()) {
        System.out.println("  - " + animal.getName());
      }
      if (habitat.getAnimalsInHabitat().isEmpty()) {
        System.out.println("  (пусто)");
      }
    }
  }
}

