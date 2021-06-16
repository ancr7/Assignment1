package assignment2.utils;

import assignment2.StudentModel;
import assignment2.enums.CourseOption;
import java.util.ArrayList;

public class ProcessInputUtil {
  public static StudentModel processInput(final String name, final String rollNo, final String address,
                                          final String age, final String courses) {
    // make model object
    StudentModel model = new StudentModel();
    // set value in model object
    model.setName(name);
    model.setRollNo(Integer.parseInt(rollNo));
    model.setAddress(address);
    model.setAge(Integer.parseInt(age));
    String[] course = courses.split(Constants.SINGLE_WHITESPACE);
    ArrayList<CourseOption> extractedCourse = new ArrayList<>();
    for (String i : course) {
      extractedCourse.add(CourseOption.valueOf(i));
    }
    model.setCourses(extractedCourse.toArray(new CourseOption[0]));
    return model;
  }
}
