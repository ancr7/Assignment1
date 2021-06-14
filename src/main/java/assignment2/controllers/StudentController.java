package assignment2.controllers;

import assignment3.exceptions.InvalidInputException;
import assignment2.StudentService;
import assignment2.exceptions.InvalidException;
import assignment2.utils.ChangeSortingPreference;
import assignment2.utils.Constants;
import assignment2.utils.InputOutputUtil;
import assignment2.utils.InputValidators.SortingChoiceValidator;
import assignment2.utils.InputValidators.SortingOrderValidator;
import assignment2.utils.InputValidators.StartScreenChoosenOptionValidate;
import assignment2.utils.ShowOptions;

public class StudentController {

  public static void saveAndExit(StudentService studentService) throws Exception {
    if (studentService.isStateChanged) {
      char option = InputOutputUtil.getSaveAndExitInput();
      if (option == 'y') { studentService.saveState(); }
    }
  }

  static void addUserDetail(StudentService studentService) throws Exception {
    ShowOptions.addStudentDetailOption(studentService);
  }

  public static void validatePreferenceInput(String SortChosenOption, String OrderString)
      throws InvalidException {
    SortingChoiceValidator.isValid(SortChosenOption);
    SortingOrderValidator.isValid(OrderString);
  }

  public static void main(String[] args) {
    boolean ProgramEnd = false;
    try {
      StudentService studentService = new StudentService();

      while (!ProgramEnd) { // loop till user select exit
        ShowOptions.mainMenuOption();
        String ChosenOption = InputOutputUtil.input();
        // validating input
        StartScreenChoosenOptionValidate.isValid((ChosenOption));
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
            InputOutputUtil.output(Constants.ENTER_ROLLNO);
            String rollNo = InputOutputUtil.input();
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
