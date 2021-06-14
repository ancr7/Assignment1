package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;

public class NumberValidator {

  public static void isValid(String number) throws InvalidException {
    // validating number
    if (number.isEmpty() || !number.matches("[0-9]*")) {
      throw new InvalidInputException("Invalid number format");
    }
  }
}
