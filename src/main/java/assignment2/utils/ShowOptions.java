package assignment2.utils;

import assignment2.StudentService;
import assignment2.exceptions.InvalidException;

public class ShowOptions {

  public static void mainMenuOption() {
    InputOutputUtil.output(Constants.CHOOSE_ANY_ONE_OPTION);
    InputOutputUtil.output(Constants.ADD_USER_DETAILS);
    InputOutputUtil.output(Constants.DISPLAY_USER_DETAILS);
    InputOutputUtil.output(Constants.DELETE_USER_DETAIL);
    InputOutputUtil.output(Constants.SAVE);
    InputOutputUtil.output(Constants.EXIT);
  }

  public static void addStudentDetailOption(StudentService studentService)
      throws InvalidException {
    InputOutputUtil.output(Constants.ENTER_FULL_NAME);
    String name = InputOutputUtil.input();
    InputOutputUtil.output(Constants.ENTER_AGE);
    String age = InputOutputUtil.input();
    InputOutputUtil.output(Constants.ENTER_ADDRESS);
    String address = InputOutputUtil.input();
    InputOutputUtil.output(Constants.ENTER_ROLLNO);
    String rollNo = InputOutputUtil.input();
    InputOutputUtil.output(Constants.ENTER_COURSES);
    String courses = InputOutputUtil.input();
    // add data in studentList
    studentService.processInput(name, rollNo, address, age, courses);
  }

  public static void sortOption() {
    InputOutputUtil.output(Constants.CHOOSE_ANY_ONE_OPTION);
    InputOutputUtil.output(Constants.NAME);
    InputOutputUtil.output(Constants.AGE);
    InputOutputUtil.output(Constants.ROLLNO);
  }

  public static void sortPreferenceOption() {
    InputOutputUtil.output(Constants.CHOOSE_ORDER_OF_SORTING);
    InputOutputUtil.output(Constants.ASCENDING);
    InputOutputUtil.output(Constants.DESCENDING);
  }
}
