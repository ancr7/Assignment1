package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;

public class RollNoValidator {
  public static void isRollNoValid(String rollNo) throws InvalidException { // isValid
    // validating rollNo
    NumberValidator.isValid(rollNo);
  }
}