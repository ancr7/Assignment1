package Assignment2;

import static Assignment2.StudentService.sortOption.*;
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
    AGE
  }

  enum courseOption {
    A,
    B,
    C,
    D,
    E,
    F;
    public static boolean contains(String test) {
      for (StudentService.courseOption c : StudentService.courseOption.values()) {
        if (c.name().equals(test)) return true;
      }
      return false;
    }
  }

  StudentService() {
    studentList = new ArrayList<>();
    isStateChanged = false;
    isAscending = true;
    currentType = NAME;
    rollNoInRepo = new HashSet<>();
  }

  boolean validInput(String name, String rollNo, String address, String age, String courses) {

    // check address empty
    if (address.isEmpty()) {
      System.out.println("Invalid address format");
      return false;
    }
    // validating name
    if (name.isEmpty() || !name.matches("^[ A-Za-z]+$")) {
      System.out.println("Invalid name format");
      return false;
    }
    // validating rollNo
    if (rollNo.isEmpty() || !rollNo.matches("[0-9]*")) {
      System.out.println("Invalid rollNo format");
      return false;
    }
    // validating age
    if (age.isEmpty() || !age.matches("[0-9]*")) {
      System.out.println("Invalid age format");
      return false;
    }
    if (Integer.parseInt(age) == 0) {
      System.out.println("Age cannot be zero");
      return false;
    }
    // validating course
    String[] course = courses.split(" ");
    HashSet<String> uniqueCourses = new HashSet<>();
    for (String i : course) {
      if (!StudentService.courseOption.contains(i)) {
        System.out.println("Invalid type");
        return false;
      }
      if (uniqueCourses.contains(i)) { // for repeated course
        System.out.println("Course can't be repeated");
        return false;
      }
      uniqueCourses.add(i);
    }
    if (courses.isEmpty() || uniqueCourses.size() < 4) {// Minimum 4 courses required
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
    String[] course = courses.split(" ");
    ArrayList<courseOption> extractedCourse = new ArrayList<>();
    for (String i : course) {
      extractedCourse.add(StudentService.courseOption.valueOf(i));
    }
    model.courses =extractedCourse.toArray(new courseOption[0]);
    // addStudent
    addStudent(model);
  }

  void sortStudentList() {
    // check if state changed
    if (!isStateChanged) return;
    // if yes then sort
    switch (currentType) {
      case NAME: {
        if (isAscending) {
          studentList.sort(
              Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo));
        } else {
          studentList.sort(
              Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo)
                  .reversed());
        }
      }
      break;
      case AGE: {
        if (isAscending) {
          studentList.sort(Comparator.comparing(StudentModel::getAge));
        } else {
          studentList.sort(Comparator.comparing(StudentModel::getAge).reversed());
        }
      }
      break;
      case ROLL_NO: {
        if (isAscending) {
          studentList.sort(Comparator.comparing(StudentModel::getRollNo));
        } else {
          studentList.sort(Comparator.comparing(StudentModel::getRollNo).reversed());
        }
      }
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
    Repo repo = Repo.getInstance();
    try {
      repo.write(studentList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    // save studentList in repo
  }

  void getState() {
    try {
      // make repo object
      Repo repo = Repo.getInstance();
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
