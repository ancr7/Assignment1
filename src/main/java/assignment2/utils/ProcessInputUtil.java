package assignment2.utils;

import assignment2.StudentModel;
import assignment2.StudentService;
import java.util.ArrayList;

public class ProcessInputUtil {
  public static StudentModel ProcessInput(String name, String rollNo, String address,
                                              String age, String courses) {
    // make model object
    StudentModel model = new StudentModel();
    model.setName(name);
    model.setRollNo(Integer.parseInt(rollNo));
    model.setAddress(address);
    model.setAge(Integer.parseInt(age));
    String[] course = courses.split(" ");
    ArrayList<CourseOption.Option> extractedCourse = new ArrayList<>();
    for (String i : course) {
      extractedCourse.add(CourseOption.Option.valueOf(i));
    }
    model.setCourses(extractedCourse.toArray(new CourseOption.Option[0]));
    return model;
  }
}
