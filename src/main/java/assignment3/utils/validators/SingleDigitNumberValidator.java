package assignment3.utils.validators;

import assignment3.exceptions.InvalidException;
import assignment3.exceptions.InvalidInputException;

public class SingleDigitNumberValidator {
  public static void isValid(String number) throws InvalidException {
    // validating number single digit [1-9]
    if (number.isEmpty() || !number.matches("[1-9]")) {
      throw new InvalidInputException("Invalid number format");
    }
  }
}
