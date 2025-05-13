package exceptionsDesign.zoo;

public class AnimalOperationException extends ZooException{

  public AnimalOperationException(String message) {
    super(message);
  }

  public AnimalOperationException(String message, Throwable cause) {
    super(message, cause);
  }
}
