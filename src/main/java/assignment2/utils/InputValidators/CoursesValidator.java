package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.CourseOption;
import java.util.HashSet;

public class CoursesValidator {

  public static void isCoursesValid(String courses) throws InvalidException {
    // validating course
    String[] course = courses.split(" ");
    HashSet<String> uniqueCourses = new HashSet<>();
    for (String i : course) {
      if (!CourseOption.Option.contains(i)) {
        throw new InvalidInputException("Invalid courses");
      }
      if (uniqueCourses.contains(i)) { // for repeated course
        throw new InvalidInputException("Course can't be repeated");
      }
      uniqueCourses.add(i);
    }
    if (courses.isEmpty() || uniqueCourses.size() < 4) {// Minimum 4 courses required
      throw new InvalidInputException("Minimum 4 courses required");
    }
  }
}
