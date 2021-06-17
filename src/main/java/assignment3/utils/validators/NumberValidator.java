package assignment3.utils.validators;


import assignment3.exceptions.InvalidException;
import assignment3.exceptions.InvalidInputException;
import assignment3.utils.Constants;

public class NumberValidator {

  public static void isValid(final String number) throws InvalidException {
    // validating number
    if (number.isEmpty() || !number.matches("[0-9]*")) {
      throw new InvalidInputException(Constants.INVALID_NUMBER);
    }
  }
}
