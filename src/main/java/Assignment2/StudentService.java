package Assignment2;

import java.util.ArrayList;

public class StudentService {

  ArrayList<StudentModel> studentList;
  boolean isStateChanged;
  sortOption currentType;
  boolean isAscending;

  enum sortOption {
    NAME,
    ROLL_NO,
    AGE,
    ADDRESS;
  }

  StudentService() {
    studentList = new ArrayList<StudentModel>();
    isStateChanged = false;
    isAscending = true;
    currentType = sortOption.NAME;
  }

  boolean validInput(String name, String rollNo, String address, String age, String courses) {
    return true;
  }

  void addStudent(StudentModel model) {
    // add to studentList
    // isStateChanged = 1
  }

  void processInput(String name, String rollNo, String address, String age, String courses) {
    // check valid
    // make model object
    // addStudent(model)
    // isStateChanged = 1
    /*
        String output = new String(encryptText);
        output = output.replaceAll(" ", "");
        return output;
    */
  }

  void sortStudentList(sortOption sortType, boolean isAscending) {
    // check if state changed
    // if yes then sort
    // isStateChanged = 0
    // currentState = sortType
  }

  void sortPreference(sortOption sortType, boolean isAscending) {
    // same then nothing
    // currentType = sortType
    // this.isAscending = isAscending
    // isStateChanged = 1
  }

  void showData() {
    // print data in studentList
  }

  void deleteStudent(int rollNo) {
    // delete data from studentList
    // isStateChanged = 1
  }

  void saveState() {
    // check if stateChanged then sort
    // make repo object
    // save studentList in repo
  }

  void getState() {
    // make repo object
    // get studentList from repo
  }

  void exit() {
    // check if isStateChanged
    // yes then promt to notify save
    // close the program
  }

}
