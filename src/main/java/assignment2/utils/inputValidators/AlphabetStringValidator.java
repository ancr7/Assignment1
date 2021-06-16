package assignment2.utils.inputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidFormatException;
import assignment2.utils.Constants;

public class AlphabetStringValidator {
  public static void isValid(final String string) throws InvalidException {
    // check string consist nly Alphabets
    if (string.isEmpty() || !string.matches(Constants.ALPHABET_REGEX)) {
      throw new InvalidFormatException(Constants.INVALID_NAME);
    }
  }
}
