package assignment2.sort_package;

import assignment2.StudentModel;
import assignment2.enums.SortType;
import java.util.ArrayList;
import java.util.Comparator;

public class SortByAgeImpl extends SortByModel {

  public SortByAgeImpl(final SortType sortType) { super(sortType); }

  public void sort(ArrayList<StudentModel> studentList) {
    studentList.sort(Comparator.comparing(StudentModel::getAge));//Ascending
    if (super.sortType == SortType.DESCENDING) {//Descending
      reverse(studentList);
    }
  }
}
