package assignment2.sort_package;

import assignment2.StudentModel;
import assignment2.enums.SortType;
import java.util.ArrayList;
import java.util.Collections;

public abstract class SortByModel {

  SortType sortType;

  SortByModel(final SortType sortType) {this.sortType = sortType;}

  public abstract void sort(final ArrayList<StudentModel> studentList);

  public void reverse(final ArrayList<StudentModel> studentList) {
    Collections.reverse(studentList);
  }

  public SortType getSortType() {
    return this.sortType;
  }

}
