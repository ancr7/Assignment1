package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;

public class SortingChoiceValidator {
  public static void isValid(String sortChoiceString) throws InvalidException {
    if (sortChoiceString.isEmpty() || !sortChoiceString.matches("[1-3]")) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }
}
