package assignment2.utils;

import assignment2.StudentService;
import assignment2.enums.MenuOption;
import assignment2.enums.SortType;
import assignment2.exceptions.InvalidException;

public class ShowOptions {

  public static void showMainMenu() {
    IOUtils.getOutput(Constants.CHOOSE_ANY_ONE_OPTION);
    for (MenuOption option : MenuOption.values()) {
      IOUtils.getOutput(option.getValue());
    }
  }

  public static void addStudentDetail(final StudentService studentService) throws InvalidException {
    IOUtils.getOutput(Constants.ENTER_FULL_NAME);
    String name = IOUtils.getInput();
    IOUtils.getOutput(Constants.ENTER_AGE);
    String age = IOUtils.getInput();
    IOUtils.getOutput(Constants.ENTER_ADDRESS);
    String address = IOUtils.getInput();
    IOUtils.getOutput(Constants.ENTER_ROLLNO);
    String rollNo = IOUtils.getInput();
    IOUtils.getOutput(Constants.ENTER_COURSES);
    String courses = IOUtils.getInput();
    // add data in studentList
    studentService.processInput(name, rollNo, address, age, courses);
  }

  public static void showSortMenu() {
    IOUtils.getOutput(Constants.CHOOSE_ANY_ONE_OPTION);
    IOUtils.getOutput(Constants.NAME);
    IOUtils.getOutput(Constants.AGE);
    IOUtils.getOutput(Constants.ROLLNO);
  }

  public static void showSortPreferenceMenu() {
    IOUtils.getOutput(Constants.CHOOSE_ORDER_OF_SORTING);
    for (SortType option : SortType.values()) {
      IOUtils.getOutput(option.getValue());
    }
  }
}
