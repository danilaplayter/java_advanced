package ru.mentee.power.zoo;

public class FeedingException extends ZooException{

  public FeedingException(String message) {
    super(message);
  }

  public FeedingException(String message, Throwable cause) {
    super(message, cause);
  }
}
