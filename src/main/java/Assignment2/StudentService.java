package Assignment2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class StudentService {

  ArrayList<StudentModel> studentList;
  boolean isStateChanged;
  sortOption currentType;
  boolean isAscending;
  HashSet<Integer> rollNoInRepo;

  enum sortOption {
    NAME,
    ROLL_NO,
    AGE,
  }

  StudentService() {
    studentList = new ArrayList<StudentModel>();
    isStateChanged = false;
    isAscending = true;
    currentType = sortOption.NAME;
    rollNoInRepo = new HashSet<Integer>();
  }

  boolean validInput(String name, String rollNo, String address, String age, String courses) {
    // check name empty
    if (name.isEmpty()) {
      System.out.println("Invalid name");
      return false;
    }
    // check rollNo empty
    if (rollNo.isEmpty()) {
      System.out.println("Invalid rollNo");
      return false;
    }
    // check address empty
    if (address.isEmpty()) {
      System.out.println("Invalid address");
      return false;
    }
    // check age empty
    if (age.isEmpty()) {
      System.out.println("Invalid age");
      return false;
    }
    // check courses empty
    if (courses.isEmpty()) {
      System.out.println("Invalid courses");
      return false;
    }
    // validating name
    if (!name.matches("^[ A-Za-z]+$")) {
      System.out.println("Invalid name");
      return false;
    }
    // validating rollNo
    if (!rollNo.matches("[0-9]*")) {
      System.out.println("Invalid quantity");
      return false;
    }
    // validating age
    if (!age.matches("[0-9]*")) {
      System.out.println("Invalid quantity");
      return false;
    }
    // validating course
    String[] course = courses.split(" ");
    HashSet<String> uniqueCourses = new HashSet<String>();
    for (String i : course) {
      if (i.length() > 1 || !i.matches("^[ A-F]+$")) {
        System.out.println("Invalid course type");
        return false;
      }
      if (uniqueCourses.contains(i)) { // for repeated course
        System.out.println("Course can't be repeated");
        return false;
      }
      uniqueCourses.add(i);
    }
    if (uniqueCourses.size() < 4) {// Minimum 4 courses required
      System.out.println("Minimum 4 courses required");
      return false;
    }
    return true;
  }

  void addStudent(StudentModel model) {
    // add to studentList
    studentList.add(model);
    // isStateChanged = 1
    isStateChanged = true;
    // add rollNo in Database
    rollNoInRepo.add(model.rollNo);
  }

  void processInput(String name, String rollNo, String address, String age, String courses) {
    // check valid
    if (!validInput(name, rollNo, address, age, courses)) return;
    if (rollNoInRepo.contains(Integer.parseInt(rollNo))) {
      System.out.println("Roll no Already exist in Database");
      return;
    }
    // make model object
    StudentModel model = new StudentModel();
    model.name = name;
    model.rollNo = Integer.parseInt(rollNo);
    model.address = address;
    model.age = Integer.parseInt(age);
    String[] SplitCourse = courses.split(" ");
    int N = SplitCourse.length;
    model.courses = courses.replaceAll(" ", "").toCharArray();
    // addStudent(model)
    addStudent(model);
  }

  void sortStudentList() {
    // check if state changed
    if (!isStateChanged) return;
    // if yes then sort
    if (currentType == sortOption.NAME) {
      if (isAscending) studentList
          .sort(Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo));
      else studentList.sort(
          Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo)
              .reversed());
    }
    if (currentType == sortOption.AGE) {
      if (isAscending) studentList.sort(Comparator.comparing(StudentModel::getAge));
      else studentList.sort(Comparator.comparing(StudentModel::getAge).reversed());
    }
    if (currentType == sortOption.ROLL_NO) {
      if (isAscending) studentList.sort(Comparator.comparing(StudentModel::getRollNo));
      else studentList.sort(Comparator.comparing(StudentModel::getRollNo).reversed());
    }
    isStateChanged = false;
  }

  void sortPreference(sortOption sortType, boolean isAscending) {
    // same then nothing
    if (sortType == currentType && isAscending == this.isAscending) return;
    currentType = sortType;
    this.isAscending = isAscending;
    isStateChanged = true;
  }

  void showData() {
    // sort before display
    sortStudentList();
    // print data in studentList
    for (StudentModel student : studentList) {
      student.print();
    }
  }

  void deleteStudent(String rollNo) {
    // validating input
    if (rollNo.isEmpty() || !rollNo.matches("[0-9]*")) {
      System.out.println("Invalid quantity");
      return;
    }
    // checking if rollNo present in studentList or not
    if (!rollNoInRepo.contains(Integer.parseInt(rollNo))) {
      System.out.println("RollNo not present");
      return;
    }
    // delete data from studentList
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).rollNo == Integer.parseInt(rollNo)) {
        studentList.remove(i);
        rollNoInRepo.remove(Integer.parseInt(rollNo));
        isStateChanged = true;
        break;
      }
    }
  }

  void saveState() {
    // check if stateChanged then sort
    sortStudentList();
    // make repo object and write
    try {
      Repo repo = new Repo();
      repo.write(studentList);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    // save studentList in repo
  }

  void getState() {
    try {
      // make repo object
      Repo repo = new Repo();
      // get studentList from repo
      studentList = repo.read();
      // filling rollNo from repo
      for (StudentModel model : studentList) {
        rollNoInRepo.add(model.rollNo);
      }
    } catch (Exception e) {
      System.out.println("error in list");
    }
  }

  void exit() {
  }

}
