package ru.mentee.power.zoo;

public class AnimalNotFoundException extends AnimalOperationException {

  private final String animalIdentifier;

  public AnimalNotFoundException(String animalIdentifier) {
    super("");
    this.animalIdentifier = animalIdentifier;
  }

  public String getAnimalIdentifier() {
    return animalIdentifier;
  }
}