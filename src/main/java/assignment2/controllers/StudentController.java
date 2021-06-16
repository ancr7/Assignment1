package assignment2.controllers;

import assignment2.StudentService;
import assignment2.exceptions.InvalidException;
import assignment2.utils.ChangeSortingPreference;
import assignment2.utils.Constants;
import assignment2.utils.IOUtils;
import assignment2.utils.ShowOptions;
import assignment2.utils.inputValidators.CustomValidator;
import assignment3.exceptions.InvalidInputException;

public class StudentController {

  public static void saveAndExit(final StudentService studentService) throws Exception {
    // save and Exir
    if (studentService.isStateChanged) {
      char option = IOUtils.getSaveAndExitInput();
      if (option == Constants.Y) { // if user presses Y
        studentService.saveState();
      }
    }
  }

  static void addUserDetail(final StudentService studentService) throws Exception {
    // add user to list
    ShowOptions.addStudentDetail(studentService);
  }

  public static void validatePreferenceInput(final String sortChosenOption,
                                             final String orderString) throws InvalidException {
    // sort according to input
    CustomValidator.isSortingChoiceValid(sortChosenOption);
    CustomValidator.isSortingOrderValid(orderString);
  }

  public static void main(String[] args) {
    boolean ProgramEnd = false;
    try {
      StudentService studentService = new StudentService();

      while (!ProgramEnd) { // loop till user select exit
        ShowOptions.showMainMenu();
        String ChosenOption = IOUtils.getInput();
        // validating input
        CustomValidator.isStartScreenChosenOptionValid(ChosenOption);
        int choice = Integer.parseInt(ChosenOption);
        switch (choice) {
          case 1: { // add data
            addUserDetail(studentService);
            break;
          }
          case 2: { // Display data
            studentService.showData();
            ChangeSortingPreference.changePreference(studentService);
            break;
          }
          case 3: { // delete data by rollNo
            IOUtils.getOutput(Constants.ENTER_ROLLNO);
            String rollNo = IOUtils.getInput();
            studentService.deleteStudent(rollNo);
            break;
          }
          case 4: {// save data to repo
            studentService.saveState();
            break;
          }
          case 5: {// Exit
            saveAndExit(studentService);
            ProgramEnd = true;
            break;
          }
          default:
            throw new InvalidInputException(Constants.INVALID_INPUT);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
