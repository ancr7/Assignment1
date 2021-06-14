package assignment2.sort_package;

import assignment2.StudentModel;
import java.util.ArrayList;
import java.util.Collections;

public abstract class SortByModel {

  boolean isAscending;

  SortByModel(Boolean isAscending) {this.isAscending = isAscending;}

  public abstract void sort(ArrayList<StudentModel> studentList);

  public void reverse(ArrayList<StudentModel> studentList) {
    Collections.reverse(studentList);
  }

  public boolean getIsAscending() {
    return this.isAscending;
  }

}
