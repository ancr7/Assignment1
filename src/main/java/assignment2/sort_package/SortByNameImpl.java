package assignment2.sort_package;

import assignment2.StudentModel;
import java.util.ArrayList;
import java.util.Comparator;

public class SortByNameImpl extends SortByModel {

  public SortByNameImpl(boolean isAscending) { super(isAscending); }

  public void sort(ArrayList<StudentModel> studentList) {
    studentList
        .sort(Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo));
    if (!super.isAscending) {
      reverse(studentList);
    }
  }
}