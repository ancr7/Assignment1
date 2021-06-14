package assignment2;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.sort_package.SortByModel;
import assignment2.sort_package.SortByNameImpl;
import assignment2.utils.InputOutputUtil;
import assignment2.utils.InputValidators.RollNoValidator;
import assignment2.utils.ProcessInputUtil;
import assignment2.utils.ValidatorUtil;
import repo.Repo;
import java.util.ArrayList;
import java.util.HashSet;

public class StudentService {

  public ArrayList<StudentModel> studentList;
  public boolean isStateChanged;
  SortByModel currentType;
  boolean isAscending;
  HashSet<Integer> rollNoInRepo;


  public StudentService() throws InvalidException {
    studentList = new ArrayList<>();
    isStateChanged = false;
    isAscending = true;
    currentType = new SortByNameImpl(true);
    rollNoInRepo = new HashSet<>();
    getState();// get data from repo
  }

  void addStudent(StudentModel model) {
    // add to studentList
    studentList.add(model);
    // isStateChanged = 1
    isStateChanged = true;
    // add rollNo in Database
    rollNoInRepo.add(model.rollNo);
  }

  public void processInput(String name, String rollNo, String address, String age, String courses)
      throws InvalidException {
    // check valid
    ValidatorUtil.validInput(name, rollNo, address, age, courses);

    if (rollNoInRepo.contains(Integer.parseInt(rollNo))) {
      throw new InvalidInputException("Roll no Already exist in Database");
    }
    // make model object
    StudentModel model = ProcessInputUtil.ProcessInput(name, rollNo, address, age, courses);
    addStudent(model);
  }

  void sortStudentList() {
    // check if state changed
    if (!isStateChanged) return;

    currentType.sort(studentList);

    isStateChanged = false;
  }

  public void sortPreference(SortByModel sortType, boolean isAscending) {
    // same then nothing
    if (sortType.getClass() == currentType.getClass() && isAscending == this.isAscending) return;
    currentType = sortType;
    this.isAscending = isAscending;
    isStateChanged = true;
  }

  public void showData() {
    // sort before display
    sortStudentList();
    // print data in studentList
    for (StudentModel student : studentList) {
      student.print();
    }
  }

  public void deleteStudent(String rollNo) throws InvalidException {
    // validating input
    RollNoValidator.isRollNoValid(rollNo);
    // delete data from studentList
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).rollNo == Integer.parseInt(rollNo)) {
        studentList.remove(i);
        rollNoInRepo.remove(Integer.parseInt(rollNo));
        isStateChanged = true;
        return;
      }
    }
    throw new InvalidInputException("Roll no not found");
  }


  public void saveState() throws Exception {
    // check if stateChanged then sort
    sortStudentList();
    // make repo object and write
    Repo repo = Repo.getInstance();
    try {
      repo.write(studentList);
    } catch (Exception e) {
      throw new InvalidInputException("Error writing Database");
    }
    // save studentList in repo
  }

  public void getState() throws InvalidException {
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
      throw new InvalidInputException("Error fetching Database");
    }
  }


}
/*
 * sort ✅
 * controller ✅
 * enum ✅
 * exception ✅
 * name validator ✅
 * parse/IO ✅
 */