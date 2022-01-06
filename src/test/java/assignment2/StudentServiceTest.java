package assignment2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import assignment2.enums.CourseOption;
import assignment2.enums.SortType;
import assignment2.exceptions.InvalidException;
import assignment2.sort_package.SortByAgeImpl;
import assignment2.sort_package.SortByNameImpl;
import assignment2.sort_package.SortByRollNoImpl;
import assignment2.utils.Constants;
import assignment2.utils.ValidatorUtil;
import assignment2.utils.inputValidators.CustomValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

  StudentService studentService;

  @BeforeEach
  @Test
  void intializeService() {
    try {
      studentService = new StudentService();
    } catch (InvalidException e) {
      e.printStackTrace();
    }
  }

  @Test
  void checkIfNameEmpty() {
    try {
      ValidatorUtil.validInput("", "12", "abs scs", "12", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid name format", e.getMessage());
    }
  }

  @Test
  void checkIfRollNoEmpty() {
    try {
      ValidatorUtil.validInput("abc", "", "abs scs", "12", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  void checkIfAddressEmpty() {
    try {
      ValidatorUtil.validInput("abc", "12", "", "12", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid address format", e.getMessage());
    }
  }

  @Test
  void checkIfAgeEmpty() {
    try {
      ValidatorUtil.validInput("abc", "12", "abs scs", "", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  void checkIfAgeZero() {
    try {
      ValidatorUtil.validInput("abc", "12", "abs scs", "0", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Age cannot be zero", e.getMessage());
    }
  }


  @Test
  void checkIfCourseEmpty() {
    try {
      ValidatorUtil.validInput("abc", "12", "abs scs", "12", "");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid courses", e.getMessage());
    }
  }

  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    try {
      ValidatorUtil.validInput("abc2", "12", "x y z", "21", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid name format", e.getMessage());
    }
  }

  @Test
  @DisplayName("When rollNo format is wrong")
  void checkInputRollNo() {
    try {
      ValidatorUtil.validInput("abc", "2F", "x y z", "21", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  @DisplayName("When Age format is wrong")
  void checkInputAge() {
    try {
      ValidatorUtil.validInput("abc", "12", "x y z", "2A", "A B C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  @DisplayName("When Course format is wrong")
  void checkInputCourse() {
    try {
      ValidatorUtil.validInput("abc", "12", "x y z", "21", "A B C 1 2 34");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid courses", e.getMessage());
    }
  }

  @Test
  void checkIfRepeatCourse() {
    try {
      ValidatorUtil.validInput("abc", "12", "abs scs", "12", "A A C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Course can't be repeated", e.getMessage());
    }
  }

  @Test
  void checkIfAtleast4Course() {
    try {
      ValidatorUtil.validInput("abc", "12", "abs scs", "12", "A C D");
      assert false;
    } catch (Exception e) {
      assertEquals("Minimum 4 courses required", e.getMessage());
    }
  }

  @Test
  void checkIfRollNoAlreadyExistWhenAddingStudent() {
    try {
      studentService.processInput("abc", "12", "x y z", "12", "A B C D");
      studentService.processInput("def", "12", "m n o", "22", "C D E F");
      assert false;
    } catch (Exception e) {
      assertEquals("Roll no Already exist in Database", e.getMessage());
    }
  }

  @Test
  void checkAddStudent() {
    StudentModel model = new StudentModel();
    model.setName("abc");
    model.setAddress("x y z");
    model.setAge(21);
    model.setRollNo(12);
    model.setCourses(
        new CourseOption[]{CourseOption.A, CourseOption.B, CourseOption.C, CourseOption.E});

    StudentService studentService = null;
    try {
      studentService = new StudentService();
    } catch (InvalidException e) {
      assert false;
    }
    int previousSize = studentService.studentList.size();
    studentService.addStudent(model);

    assertEquals(previousSize + 1, studentService.studentList.size());
    assertEquals("abc", studentService.studentList.get(previousSize).name);
    assertEquals("x y z", studentService.studentList.get(previousSize).address);
    assertEquals(21, studentService.studentList.get(previousSize).age);
    assertEquals(12, studentService.studentList.get(previousSize).rollNo);
    assertArrayEquals(
        new CourseOption[]{CourseOption.A, CourseOption.B, CourseOption.C, CourseOption.E},
        studentService.studentList.get(previousSize).courses);
    assertTrue(studentService.isStateChanged);
  }

  @Test
  void checkSortStudentListNameAscending() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("AAA", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByNameImpl(SortType.ASCENDING);
      studentService.appliedSortType = SortType.ASCENDING;
      studentService.sortStudentList();
      assertEquals("AAA", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkSortStudentListNameDescending() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByNameImpl(SortType.DESCENDING);
      studentService.appliedSortType = SortType.DESCENDING;
      studentService.sortStudentList();
      assertEquals("def", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkSortStudentListAgeAscending() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByAgeImpl(SortType.ASCENDING);
      studentService.appliedSortType = SortType.ASCENDING;
      studentService.sortStudentList();
      assertEquals("def", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkSortStudentListAgeDescending() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByAgeImpl(SortType.DESCENDING);
      studentService.appliedSortType = SortType.DESCENDING;
      studentService.sortStudentList();
      assertEquals("abc", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkSortStudentListRollNoAscending() {
    try {
//      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByRollNoImpl(SortType.DESCENDING);
//      studentService.sortStudentList();
//      assertEquals("abc", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkSortStudentListRollNoDescending() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByRollNoImpl(SortType.DESCENDING);
      studentService.appliedSortType = SortType.DESCENDING;
      studentService.sortStudentList();
      assertEquals("Ayush Nishad", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkShowData() {
    try {
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.appliedSort = new SortByRollNoImpl(SortType.DESCENDING);
      studentService.appliedSortType = SortType.DESCENDING;
      studentService.showData();
//      assertEquals("Ayush Nishad", studentService.studentList.get(0).name);
    } catch (Exception e) {
      assert false;
    }

  }

  @Test
  void checkSortPreference() {
    studentService.sortPreference(new SortByRollNoImpl(SortType.DESCENDING), SortType.DESCENDING);
    assertTrue(studentService.isStateChanged);
  }

  @Test
  void checkDeleteStudent() {
    try {
      int previousSize = studentService.studentList.size();
      studentService.processInput("zzzzz", "12", "x y z", "21", "A B C D");
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.deleteStudent("13");
      assertEquals(previousSize + 1, studentService.studentList.size());
      assertEquals("zzzzz", studentService.studentList.get(previousSize).name);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkDeleteStudentWhenRollNoEmpty() {
    try {
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.deleteStudent("");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  void checkDeleteStudentWhenRollFormatIsWrong() {
    try {
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.deleteStudent("3ds");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format", e.getMessage());
    }
  }

  @Test
  void checkDeleteStudentWhenRollNoIsIncorrect() {
    try {
      studentService.processInput("abc", "12", "x y z", "21", "A B C D");
      studentService.processInput("def", "13", "x y z", "13", "B C D E");
      studentService.deleteStudent("35");
      assert false;
    } catch (Exception e) {
      assertEquals("Roll no not found", e.getMessage());
    }
  }

  @Test
  void checkSortingChoiceValidator() {
    try {
      CustomValidator.isSortingChoiceValid(("2"));
      CustomValidator.isSortingChoiceValid((""));
      assert false;
    } catch (Exception e) {
      assertEquals(Constants.INVALID_CHOICE, e.getMessage());
    }
  }

  @Test
  void checkSortingOrderValidator() {
    try {
      CustomValidator.isSortingOrderValid("2");
      CustomValidator.isSortingOrderValid("");
      assert false;
    } catch (Exception e) {
      assertEquals(Constants.INVALID_CHOICE, e.getMessage());
    }
  }

  @Test
  void checkSaveState() {
    try {
      studentService.saveState();
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkStartScreenChoosenOptionValidator() {
    try {
      CustomValidator.isStartScreenChosenOptionValid("2");
      CustomValidator.isStartScreenChosenOptionValid("");
      assert false;
    } catch (Exception e) {
      assertEquals(Constants.INVALID_CHOICE, e.getMessage());
    }
  }

  @Test
  void checkYesOrNoValidate() {
    try {
      CustomValidator.isYesOrNoValid("y");
      CustomValidator.isYesOrNoValid("n");
      CustomValidator.isYesOrNoValid("Yes");
      assert false;
    } catch (Exception e) {
      assertEquals(Constants.INVALID_INPUT, e.getMessage());
    }
  }

  @Test
  void checkGetSortFactoryWhenInputIs1() {
    try {
      assertEquals(SortByNameImpl.class,
          GetSortFactory.getFactory(1, SortType.ASCENDING).getClass());
      assertEquals(SortByNameImpl.class,
          GetSortFactory.getFactory(1, SortType.DESCENDING).getClass());

      assertEquals(SortType.ASCENDING,
          GetSortFactory.getFactory(1, SortType.ASCENDING).getSortType());
      assertEquals(SortType.DESCENDING,
          GetSortFactory.getFactory(1, SortType.DESCENDING).getSortType());

    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkGetSortFactoryWhenInputIs2() {
    try {
      assertEquals(SortByAgeImpl.class,
          GetSortFactory.getFactory(2, SortType.ASCENDING).getClass());
      assertEquals(SortByAgeImpl.class,
          GetSortFactory.getFactory(2, SortType.DESCENDING).getClass());

      assertEquals(SortType.ASCENDING,
          GetSortFactory.getFactory(2, SortType.ASCENDING).getSortType());
      assertEquals(SortType.DESCENDING,
          GetSortFactory.getFactory(2, SortType.DESCENDING).getSortType());

    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void checkGetSortFactoryWhenInputIs3() {
    try {
      assertEquals(SortByRollNoImpl.class,
          GetSortFactory.getFactory(3, SortType.ASCENDING).getClass());
      assertEquals(SortByRollNoImpl.class,
          GetSortFactory.getFactory(3, SortType.DESCENDING).getClass());

      assertEquals(SortType.ASCENDING,
          GetSortFactory.getFactory(3, SortType.ASCENDING).getSortType());
      assertEquals(SortType.DESCENDING,
          GetSortFactory.getFactory(3, SortType.DESCENDING).getSortType());

    } catch (Exception e) {
      assert false;
    }
  }
}