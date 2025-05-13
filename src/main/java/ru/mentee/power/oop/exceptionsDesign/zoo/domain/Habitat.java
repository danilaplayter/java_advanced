package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

import java.util.ArrayList;
import java.util.List;

class Habitat {
  private String type;
  private List<Animal> animalsInHabitat = new ArrayList<>();
  private int capacity;

  public Habitat(String type, int capacity) {
    this.type = type;
    this.capacity = capacity;
  }

  public String getType() {
    return type;
  }

  public void addAnimal(Animal animal) throws IncompatibleHabitatException, HabitatFullException {
    if (animalsInHabitat.size() >= capacity) {
      throw new HabitatFullException("Вольер " + type + " переполнен");
    }

    if (animal instanceof Lion && !"Саванна".equalsIgnoreCase(type)) {
      throw new IncompatibleHabitatException("Львы могут жить только в саванне");
    }

    if (animal instanceof Monkey && !"Джунгли".equalsIgnoreCase(type)) {
      throw new IncompatibleHabitatException("Обезьяны могут жить только в джунглях");
    }

    if (animal instanceof Elephant && !"Саванна".equalsIgnoreCase(type)) {
      throw new IncompatibleHabitatException("Слоны могут жить только в саванне");
    }

    if (animal instanceof Penguin && !"Антарктида".equalsIgnoreCase(type)) {
      throw new IncompatibleHabitatException("Пингвины могут жить только в Антарктиде");
    }

    animalsInHabitat.add(animal);
    System.out.println(animal.getName() + " помещен в вольер " + type);
  }

  public List<Animal> getAnimalsInHabitat() {
    return new ArrayList<>(animalsInHabitat);
  }
}