package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;

public class SortingOrderValidator {
  public static void isValid(String sortOrder)throws InvalidException {
    if (sortOrder.isEmpty() || !sortOrder.matches("[1-2]")) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }
}
