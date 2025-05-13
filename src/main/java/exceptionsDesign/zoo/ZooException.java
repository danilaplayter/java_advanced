package exceptionsDesign.zoo;

public class ZooException extends Exception {

  String message;
  Throwable cause;

  public ZooException(String message) {
    this.message = message;
  }

  public ZooException(String message, Throwable cause) {
    super(message);
    this.cause = cause;
  }
}
