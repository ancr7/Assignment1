package assignment2.utils;

import assignment2.exceptions.InvalidException;
import assignment2.utils.inputValidators.AlphabetStringValidator;
import assignment2.utils.inputValidators.CustomValidator;
import assignment2.utils.inputValidators.NumberValidator;

public class ValidatorUtil {

  public static void validInput(final String name, final String rollNo, final String address, final String age,
                                final String courses) throws InvalidException {

    // Validating Input
    AlphabetStringValidator.isValid(name);
    NumberValidator.isValid(rollNo);
    CustomValidator.isEmpty(address);
    CustomValidator.isAgeValid(age);
    CustomValidator.isCoursesValid(courses);
  }
}
