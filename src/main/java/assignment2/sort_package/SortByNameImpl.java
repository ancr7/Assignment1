package assignment2.sort_package;

import assignment2.StudentModel;
import assignment2.enums.SortType;
import java.util.ArrayList;
import java.util.Comparator;

public class SortByNameImpl extends SortByModel {

  public SortByNameImpl(final SortType sortType) { super(sortType); }

  public void sort(ArrayList<StudentModel> studentList) {
    studentList
        .sort(Comparator.comparing(StudentModel::getName).thenComparing(StudentModel::getRollNo)); //Ascending
    if (super.getSortType() == SortType.DESCENDING) { //Descending
      reverse(studentList);
    }
  }
}
