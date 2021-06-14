package assignment2.utils;

import assignment2.GetSortFactory;
import assignment2.StudentService;
import assignment2.controllers.StudentController;
import assignment2.exceptions.InvalidException;
import assignment2.sort_package.SortByModel;

public class ChangeSortingPreference {

  public static void changePreference(StudentService studentService) throws Exception {
    if (studentService.studentList.isEmpty()) {
      throw new InvalidException(Constants.NO_DATA);
    }
    InputOutputUtil.output(Constants.CHANGE_SORT_PREFERENCE);
    // validating input
    char choice = InputOutputUtil.getYesNoInput();
    if (choice == 'y') {
      ShowOptions.sortOption();
      String SortChosenOption = InputOutputUtil.input();
      // validating input
      ShowOptions.sortPreferenceOption();

      String OrderString = InputOutputUtil.input();
      // validating input

      StudentController.validatePreferenceInput(SortChosenOption, OrderString);

      int chosen = Integer.parseInt(SortChosenOption);
      boolean isAscending = (Integer.parseInt(OrderString) == 1);
      SortByModel sortByModel = null;

      sortByModel = GetSortFactory.getFactory(chosen, isAscending);
      studentService.sortPreference(sortByModel, isAscending);
    }
  }
}
