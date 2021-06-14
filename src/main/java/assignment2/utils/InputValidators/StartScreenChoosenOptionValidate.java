package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;
public class StartScreenChoosenOptionValidate {

  public static void isValid(String ChosenOption) throws InvalidException {
    if (ChosenOption.isEmpty() || !ChosenOption.matches("[1-5]")) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }
}
