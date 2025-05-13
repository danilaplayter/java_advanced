package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

public class HabitatFullException extends Exception {
  public HabitatFullException() {
    super();
  }

  public HabitatFullException(String message) {
    super(message);
  }

  public HabitatFullException(String message, Throwable cause) {
    super(message, cause);
  }
}