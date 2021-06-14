package assignment2.sort_package;

import assignment2.StudentModel;
import java.util.ArrayList;
import java.util.Comparator;

public class SortByRollNoImpl extends SortByModel{

  public SortByRollNoImpl(boolean isAscending) { super(isAscending); }

  public void sort(ArrayList<StudentModel> studentList){
    studentList.sort(Comparator.comparing(StudentModel::getRollNo));
    if (!super.isAscending) {
      reverse(studentList);
    }
  }
}
