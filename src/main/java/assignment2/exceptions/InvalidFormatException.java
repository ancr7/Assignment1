package assignment2.exceptions;

public class InvalidFormatException extends InvalidException {
  // Error when format is wrong
  public InvalidFormatException(final String errorMessage) {
    super(errorMessage + " format");
  }

}
