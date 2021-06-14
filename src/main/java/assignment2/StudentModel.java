package assignment2;

import assignment2.utils.CourseOption;
import java.io.Serializable;
import java.util.Arrays;

public class StudentModel implements Serializable {

  String name, address;
  int age, rollNo;
  CourseOption.Option[] courses;

  // getter methods

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public int getRollNo() {
    return rollNo;
  }

  // setter methods

  public void setName(final String name) { this.name = name; }

  public void setAddress(final String address) {
    this.address = address;
  }

  public void setAge(final int age) {
    this.age = age;
  }

  public void setRollNo(final int rollNo) {
    this.rollNo = rollNo;
  }

  public void setCourses(final CourseOption.Option[] courses) {
    this.courses = courses;
  }

  public void print() {
    System.out.println(name+" "+rollNo+" "+age+" "+address+" "+ Arrays.toString(courses));
  }
}
