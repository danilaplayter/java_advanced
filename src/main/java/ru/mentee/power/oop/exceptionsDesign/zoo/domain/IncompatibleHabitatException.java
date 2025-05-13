package ru.mentee.power.oop.exceptionsDesign.zoo.domain;

public class IncompatibleHabitatException extends Exception {
  public IncompatibleHabitatException() {
    super();
  }

  public IncompatibleHabitatException(String message) {
    super(message);
  }

  public IncompatibleHabitatException(String message, Throwable cause) {
    super(message, cause);
  }
}