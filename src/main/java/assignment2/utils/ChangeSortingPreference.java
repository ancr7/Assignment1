package assignment2.utils;

import assignment2.GetSortFactory;
import assignment2.StudentService;
import assignment2.controllers.StudentController;
import assignment2.enums.SortType;
import assignment2.exceptions.InvalidException;
import assignment2.sort_package.SortByModel;

public class ChangeSortingPreference {

  public static void changePreference(final StudentService studentService) throws Exception {
    if (studentService.studentList.isEmpty()) {
      throw new InvalidException(Constants.NO_DATA);
    }
    IOUtils.getOutput(Constants.CHANGE_SORT_PREFERENCE);
    // validating input
    char choice = IOUtils.getYesNoInput();
    if (choice == Constants.Y) {
      ShowOptions.showSortMenu();
      String SortChosenOption = IOUtils.getInput();
      // validating input
      ShowOptions.showSortPreferenceMenu();

      String OrderString = IOUtils.getInput();
      // validating input

      StudentController.validatePreferenceInput(SortChosenOption, OrderString);

      int chosen = Integer.parseInt(SortChosenOption);
//      boolean isAscending = (Integer.parseInt(OrderString) == 1);
      SortType sortType = SortType.stringToSortType(OrderString);

      SortByModel sortByModel = null;

      sortByModel = GetSortFactory.getFactory(chosen, sortType);
      studentService.sortPreference(sortByModel, sortType);
    }
  }
}
