package assignment2.utils.inputValidators;

import assignment2.enums.CourseOption;
import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidFormatException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;
import java.util.HashSet;

public class CustomValidator {

  public static void isAgeValid(final String age) throws InvalidException {
    // validating age
    NumberValidator.isValid(age);
    if (Integer.parseInt(age) == 0) {
      throw new InvalidInputException(Constants.AGE_CANNOT_BE_ZERO);
    }
  }

  public static void isEmpty(final String address) throws InvalidException {
    // check address empty
    if (address.isEmpty()) {
      throw new InvalidFormatException(Constants.INPUT_EMPTY);
    }
  }

  public static void isSortingChoiceValid(final String sortChoiceString) throws InvalidException {
    // checking sorting choice is valid or not
    if (sortChoiceString.isEmpty() || !sortChoiceString.matches(Constants.ONE_TO_THREE_REGEX)) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }

  public static void isSortingOrderValid(final String sortOrder) throws InvalidException {
    // checking sorting order is valid or not
    if (sortOrder.isEmpty() || !sortOrder.matches(Constants.ONE_TO_TWO_REGEX)) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }

  public static void isStartScreenChosenOptionValid(final String ChosenOption)
    // checking start screen choice is valid or not
      throws InvalidException {
    if (ChosenOption.isEmpty() || !ChosenOption.matches(Constants.ONE_TO_FIVE_REGEX)) {
      throw new InvalidInputException(Constants.INVALID_CHOICE);
    }
  }

  public static void isYesOrNoValid(final String input) throws InvalidException {
    // checking y/n choice is valid or not
    if (input.length() != 1 || (input.charAt(0) != Constants.Y && input.charAt(0) != Constants.N)) {
      throw new InvalidInputException(Constants.INVALID_INPUT);
    }
  }

  public static void isCoursesValid(final String courses) throws InvalidException {
    // validating course
    String[] course = courses.split(Constants.SINGLE_WHITESPACE);
    HashSet<String> uniqueCourses = new HashSet<>();
    for (String i : course) {
      if (!CourseOption.contains(i)) {
        throw new InvalidInputException(Constants.INVALID_COURSES);
      }
      if (uniqueCourses.contains(i)) { // for repeated course
        throw new InvalidInputException(Constants.COURSES_CANNOT_BE_REPEATED);
      }
      uniqueCourses.add(i);
    }
    if (courses.isEmpty() || uniqueCourses.size() < 4) {// Minimum 4 courses required
      throw new InvalidInputException(Constants.MINIMUM_FOUR_COURSE);
    }
  }
}
