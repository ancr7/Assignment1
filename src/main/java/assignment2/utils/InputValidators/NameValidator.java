package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;

public class NameValidator {

  public static void isNameValid(String name)throws InvalidException {
    // validating name
    AlphabetStringValidator.isValid(name);
  }
}
