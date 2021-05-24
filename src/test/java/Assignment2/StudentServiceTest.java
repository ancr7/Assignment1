package Assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

  @Test
  void checkIfNameEmpty() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("", "12", "abs scs", "12", "A B C D"));
  }

  @Test
  void checkIfRollNoEmpty() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "", "abs scs", "12", "A B C D"));
  }

  @Test
  void checkIfAddressEmpty() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "", "12", "A B C D"));
  }

  @Test
  void checkIfAgeEmpty() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "abs scs", "", "A B C D"));
  }

  @Test
  void checkIfCourseEmpty() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "abs scs", "12", ""));
  }

  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc2", "12", "x y z", "21", "A B C D"));
  }

  @Test
  @DisplayName("When rollNo format is wrong")
  void checkInputRollNo() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "2F", "x y z", "21", "A B C D"));
  }

  @Test
  @DisplayName("When Age format is wrong")
  void checkInputAge() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "x y z", "2A", "A B C D"));
  }

  @Test
  @DisplayName("When Course format is wrong")
  void checkInputCourse() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "x y z", "21", "A B C 1 2 34"));
  }

  @Test
  void checkIfRepeatCourse() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "abs scs", "12", "A A C D"));
  }

  @Test
  void checkIfAtleast4Course() {
    StudentService studentService = new StudentService();
    assertEquals(false, studentService.validInput("abc", "12", "abs scs", "12", "A C D"));
  }

  @Test
  void checkIfRollNoAlreadyExistWhenAddingStudent() {
    StudentService studentService = new StudentService();
    studentService.processInput("abc", "12", "x y z", "12", "A B C D");
    studentService.processInput("def", "12", "m n o", "22", "C D E F");
    assertEquals(1, studentService.studentList.size());
  }

  @Test
  void checkAddStudent() {
    StudentModel model = new StudentModel();
    model.setName("abc");
    model.setAddress("x y z");
    model.setAge(21);
    model.setRollNo(12);
    model.setCourses(new char[]{'A', 'B', 'C'});
    StudentService studentService = new StudentService();
    studentService.addStudent(model);
    assertEquals(1, studentService.studentList.size());
    assertEquals("abc", studentService.studentList.get(0).name);
    assertEquals("x y z", studentService.studentList.get(0).address);
    assertEquals(21, studentService.studentList.get(0).age);
    assertEquals(12, studentService.studentList.get(0).rollNo);
    assertArrayEquals(new char[]{'A', 'B', 'C'}, studentService.studentList.get(0).courses);
    assertEquals(true, studentService.isStateChanged);
  }

  @Test
  void checkSortStudentListNameAscending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.NAME;
    studentService.isAscending = true;
    studentService.sortStudentList();
    assertEquals("abc", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortStudentListNameDescending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.NAME;
    studentService.isAscending = false;
    studentService.sortStudentList();
    assertEquals("def", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortStudentListAgeAscending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.AGE;
    studentService.isAscending = true;
    studentService.sortStudentList();
    assertEquals("def", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortStudentListAgeDescending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.AGE;
    studentService.isAscending = false;
    studentService.sortStudentList();
    assertEquals("abc", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortStudentListRollNoAscending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.ROLL_NO;
    studentService.isAscending = true;
    studentService.sortStudentList();
    assertEquals("abc", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortStudentListRollNoDescending() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.ROLL_NO;
    studentService.isAscending = false;
    studentService.sortStudentList();
    assertEquals("def", studentService.studentList.get(0).name);
  }

  @Test
  void checkShowData() {
    StudentService studentService = new StudentService();
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.currentType = StudentService.sortOption.ROLL_NO;
    studentService.isAscending = false;
    studentService.showData();
    assertEquals("def", studentService.studentList.get(0).name);
  }

  @Test
  void checkSortPreference() {
    StudentService studentService = new StudentService();
    studentService.sortPreference(StudentService.sortOption.ROLL_NO, true);
    assertEquals(true, studentService.isStateChanged);
  }

  @Test
  void checkDeleteStudent() {
    StudentService studentService = new StudentService();
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.deleteStudent("13");
    assertEquals(1, studentService.studentList.size());
    System.out.println(studentService.studentList.get(0).name);
    assertEquals("abc", studentService.studentList.get(0).name);
  }

  @Test
  void checkDeleteStudentWhenRollNoEmpty() {
    StudentService studentService = new StudentService();
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.deleteStudent("");
    assertEquals(2, studentService.studentList.size());
  }

  @Test
  void checkDeleteStudentWhenRollFormatIsWrong() {
    StudentService studentService = new StudentService();
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.deleteStudent("3ds");
    assertEquals(2, studentService.studentList.size());
  }

  @Test
  void checkDeleteStudentWhenRollNoIsIncorrect() {
    StudentService studentService = new StudentService();
    studentService.processInput("abc", "12", "x y z", "21", "A B C D");
    studentService.processInput("def", "13", "x y z", "13", "B C D E");
    studentService.deleteStudent("35");
    assertEquals(2, studentService.studentList.size());
  }
}