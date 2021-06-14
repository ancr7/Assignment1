package assignment2.exceptions;

public class InvalidFormatException extends InvalidException {

  public InvalidFormatException(String errorMessage) {
    super(errorMessage + " format");
  }

}
