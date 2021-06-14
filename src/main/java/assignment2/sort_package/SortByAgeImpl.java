package assignment2.sort_package;

import assignment2.StudentModel;
import java.util.ArrayList;
import java.util.Comparator;

public class SortByAgeImpl extends SortByModel{

  public SortByAgeImpl(boolean isAscending) { super(isAscending); }

  public void sort(ArrayList<StudentModel> studentList){
    studentList.sort(Comparator.comparing(StudentModel::getAge));
    if (!super.isAscending) {
      reverse(studentList);
    }
  }
}
