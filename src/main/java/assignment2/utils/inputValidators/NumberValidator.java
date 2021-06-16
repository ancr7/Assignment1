package assignment2.utils.inputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;

public class NumberValidator {

  public static void isValid(final String number) throws InvalidException {
    // validating number
    if (number.isEmpty() || !number.matches(Constants.NUMBER_REGEX)) {
      throw new InvalidInputException(Constants.INVALID_NUMBER_FORMAT);
    }
  }
}
