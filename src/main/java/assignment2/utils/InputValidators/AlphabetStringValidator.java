package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidFormatException;

public class AlphabetStringValidator {

  public static void isValid(String string) throws InvalidException {
    if (string.isEmpty() || !string.matches("^[ A-Za-z]+$")) {
      throw new InvalidFormatException("Invalid name");
    }
  }
}
