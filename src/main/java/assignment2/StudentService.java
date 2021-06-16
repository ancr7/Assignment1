package assignment2;

import assignment2.enums.SortType;
import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.sort_package.SortByModel;
import assignment2.sort_package.SortByNameImpl;
import assignment2.utils.Constants;
import assignment2.utils.ProcessInputUtil;
import assignment2.utils.ValidatorUtil;
import assignment2.utils.inputValidators.NumberValidator;
import repo.Repo;
import java.util.ArrayList;
import java.util.HashSet;

public class StudentService {

  public ArrayList<StudentModel> studentList;
  public boolean isStateChanged;
  SortByModel appliedSort;
  SortType appliedSortType;
  HashSet<Integer> rollNoInRepo;


  public StudentService() throws InvalidException {
    studentList = new ArrayList<>();
    isStateChanged = false;
    appliedSort = new SortByNameImpl(SortType.ASCENDING);
    appliedSortType = SortType.ASCENDING;
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

  public void processInput(final String name, final String rollNo, final String address,
                           final String age, final String courses) throws InvalidException {
    // check valid
    ValidatorUtil.validInput(name, rollNo, address, age, courses);

    if (rollNoInRepo.contains(Integer.parseInt(rollNo))) {
      throw new InvalidInputException(Constants.ROLL_NO_ALREADY_EXIST);
    }
    // make model object
    StudentModel model = ProcessInputUtil.processInput(name, rollNo, address, age, courses);
    addStudent(model);
  }

  void sortStudentList() {

    // check if state changed
    if (!isStateChanged) return;

    // sort the list
    appliedSort.sort(studentList);

    isStateChanged = false;
  }

  public void sortPreference(final SortByModel sortType, final SortType appliedSortType) {
    // same then nothing
    if (sortType.getClass() == appliedSort.getClass() && appliedSortType == this.appliedSortType) return;

    appliedSort = sortType;
    this.appliedSortType = appliedSortType;
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

  public void deleteStudent(final String rollNo) throws InvalidException {
    // validating input
    NumberValidator.isValid(rollNo);
    // delete data from studentList
    for (int i = 0; i < studentList.size(); i++) {
      if (studentList.get(i).rollNo == Integer.parseInt(rollNo)) {
        studentList.remove(i);
        rollNoInRepo.remove(Integer.parseInt(rollNo));
        isStateChanged = true;
        return;
      }
    }
    throw new InvalidInputException(Constants.ROLLNO_NOT_FOUND);
  }


  public void saveState() throws Exception {
    // check if stateChanged then sort
    sortStudentList();
    // make repo object and write
    Repo repo = Repo.getInstance();
    try {
      repo.write(studentList);
    } catch (Exception e) {
      throw new InvalidInputException(Constants.ERROR_WRITING_DATABASE);
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
      throw new InvalidInputException(Constants.ERROR_FETCHING_DATABASE);
    }
  }
}