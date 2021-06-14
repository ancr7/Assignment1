package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;

public class YesOrNoValidate {

  public static void isValid(String input) throws InvalidException {
    if (input.length() != 1 || (input.charAt(0) != 'y' && input.charAt(0) != 'n')) {
      throw new InvalidInputException(Constants.INVALID_INPUT);
    }
  }
}
