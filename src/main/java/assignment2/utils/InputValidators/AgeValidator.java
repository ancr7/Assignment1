package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;

public class AgeValidator {

  public static void isAgeValid(String age) throws InvalidException {
    // validating age
    NumberValidator.isValid(age);
    if (Integer.parseInt(age) == 0) {
      throw new InvalidInputException("Age cannot be zero");
    }
  }
}
