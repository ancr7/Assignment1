package Assignment2;
import static org.junit.jupiter.api.Assertions.*;

import Assignment1.AssignmentService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentServiceTest {
  @Test
  void checkIfInputEmpty()
  {
    StudentService studentService = new StudentService();
    assertEquals(false,studentService.validInput("","","","",""));
  }
  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc2","12","x y z","21","A B C D"));
  }
  @Test
  @DisplayName("When rollNo format is wrong")
  void checkInputRollNo() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc","2F","x y z","21","A B C D"));
  }
  @Test
  @DisplayName("When Age format is wrong")
  void checkInputAge() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc","12","x y z","2A","A B C D"));
  }
  @Test
  @DisplayName("When Course format is wrong")
  void checkInputCourse() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc","12","x y z","21","A B C 1 2 34"));
  }
  @Test
  void checkAddStudent() {
    StudentModel model;
    model.setName("abc");
    model.setAddress("x y z");
    model.setAge(21);
    model.setRollNo(12);
    model.setCourse(new char[]{'A','B','C'});
    StudentService studentService = new StudentService();
    studentService.addStudent(model);
    assertEquals(1,studentService.studentList.size());
    assertEquals("abc",studentService.studentList.get(0).name);
    assertEquals("x y z",studentService.studentList.get(0).address);
    assertEquals(21,studentService.studentList.get(0).age);
    assertEquals(12,studentService.studentList.get(0).rollNo);
    assertArrayEquals(new char[]{'A', 'B', 'C'},studentService.studentList.get(0).courses);
    assertEquals(true,studentService.isStateChanged);
  }
  @Test
  void checkSortStudentList()
  {
    StudentService studentService = new StudentService();
    studentService.processInput("abc","12","x y z","21","A B C");
    studentService.processInput("def","13","x y z","13","B C D");
    studentService.sortStudentList(StudentService.sortOption.AGE,true);
    assertEquals("def",studentService.studentList.get(0).name);
  }
  @Test
  void checkSortPreference()
  {
    StudentService studentService = new StudentService();
    studentService.sortPreference(StudentService.sortOption.ROLL_NO,true);
    assertEquals(false,studentService.isStateChanged);
  }
  @Test
  void checkDeleteStudent()
  {
    StudentService studentService = new StudentService();
    studentService.processInput("abc","12","x y z","21","A B C");
    studentService.processInput("def","13","x y z","13","B C D");
    studentService.deleteStudent(13);
    assertEquals(1,studentService.studentList.size());
    assertEquals("def",studentService.studentList.get(0).name);
  }
}