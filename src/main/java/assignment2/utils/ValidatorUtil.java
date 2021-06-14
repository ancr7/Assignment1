package assignment2.utils;

import assignment2.exceptions.InvalidException;
import assignment2.utils.InputValidators.AddressValidator;
import assignment2.utils.InputValidators.AgeValidator;
import assignment2.utils.InputValidators.CoursesValidator;
import assignment2.utils.InputValidators.NameValidator;
import assignment2.utils.InputValidators.RollNoValidator;

public class ValidatorUtil {

  public static void validInput(String name, String rollNo, String address, String age,
                                   String courses) throws InvalidException {
    NameValidator.isNameValid(name);
    RollNoValidator.isRollNoValid(rollNo);
    AddressValidator.isAddressValid(address);
    AgeValidator.isAgeValid(age);
    CoursesValidator.isCoursesValid(courses);
  }

}
