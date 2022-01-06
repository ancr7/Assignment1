package assignment2.exceptions;

public class InvalidInputException extends InvalidException {
  // Error when input is wrong
  public InvalidInputException(final String errorMessage) {
    super(errorMessage);
  }
}
