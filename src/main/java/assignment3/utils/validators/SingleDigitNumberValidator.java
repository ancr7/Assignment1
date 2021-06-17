package assignment3.utils.validators;

import assignment3.exceptions.InvalidException;
import assignment3.exceptions.InvalidInputException;
import assignment3.utils.Constants;

public class SingleDigitNumberValidator {

  public static void isValid(final String number) throws InvalidException {
    // validating number single digit [1-9]
    if (number.isEmpty() || !number.matches("[1-9]")) {
      throw new InvalidInputException(Constants.INVALID_NUMBER);
    }
  }
}
