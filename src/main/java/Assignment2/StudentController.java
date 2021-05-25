package Assignment2;

import java.util.Scanner;

public class StudentController {

  private static void startScreen() {
    System.out.println("Choose any one among the following");
    System.out.println("1. Add User details.");
    System.out.println("2. Display User details.");
    System.out.println("3. Delete User details");
    System.out.println("4. Save Us");
    System.out.println("5. Exit");
  }

  static void addUserDetail(StudentService studentService, Scanner sc) {
    String name, address, courses, age, rollno;
    System.out.println("Enter Full Name of the student");
    name = sc.nextLine().trim();
    System.out.println("Enter Age of the student");
    age = sc.nextLine().trim();
    System.out.println("Enter Address of the student");
    address = sc.nextLine().trim();
    System.out.println("Enter Roll No of the student");
    rollno = sc.nextLine().trim();
    System.out.println("Enter Courses opt by student");
    courses = sc.nextLine().trim();
    // add data in studentList
    studentService.processInput(name, rollno, address, age, courses);
  }

  private static boolean validatePreferenceInput(String SortChoiceString, String OrderString) {
    if (SortChoiceString.isEmpty() || !SortChoiceString.matches("[1-3]*")
        || Integer.parseInt(SortChoiceString) > 3) {
      System.out.println("Invalid choice");
      return false;
    }
    if (OrderString.isEmpty() || !OrderString.matches("[1-2]*")
        || Integer.parseInt(OrderString) > 2) {
      System.out.println("Invalid choice");
      return false;
    }
    return true;
  }

  private static void changePreference(StudentService studentService, Scanner sc) {

    System.out.println("Do you want to change sort preference? (y/n)");
    String input = sc.nextLine().trim();
    // validating input
    if (input.length() != 1 || (input.charAt(0) != 'y' && input.charAt(0) != 'n')) {
      System.out.println("invalid Input");
      return;
    }
    if (input.charAt(0) == 'y') {
      System.out.println("Choose any one among the following");
      System.out.println("1. Name");
      System.out.println("2. Age");
      System.out.println("3. RollNo");
      String SortChoiceString = sc.nextLine().trim();
      // validating input

      System.out.println("Choose the order of sorting");
      System.out.println("1. Ascending");
      System.out.println("2. Descending");
      String OrderString = sc.nextLine().trim();
      // validating input

      if (!validatePreferenceInput(SortChoiceString, OrderString)) {
        return;
      }

      int choosen = Integer.parseInt(SortChoiceString);
      boolean isAscending = (Integer.parseInt(OrderString) == 1);
      switch (choosen) {
        case 1:
          studentService.sortPreference(StudentService.sortOption.NAME, isAscending);
          break;
        case 2:
          studentService.sortPreference(StudentService.sortOption.AGE, isAscending);
          break;
        case 3:
          studentService.sortPreference(StudentService.sortOption.ROLL_NO, isAscending);
          break;
      }
    }
  }

  public static void main(String[] args)  {
    boolean ProgramEnd = false;
    StudentService studentService = new StudentService();
    studentService.getState(); // get data from repo
    while (!ProgramEnd) { // loop till user select exit
      startScreen();
      Scanner sc = new Scanner(System.in);
      String ChoiceString = sc.nextLine().trim();
      // validating input
      if (ChoiceString.isEmpty() || !ChoiceString.matches("[0-9]*")
          || Integer.parseInt(ChoiceString) > 5) {
        System.out.println("Invalid choice");
        continue;
      }
      int choice = Integer.parseInt(ChoiceString);
      switch (choice) {
        case 1: { // add data
          addUserDetail(studentService, sc);
        }
        break;
        case 2: { // Display data
          studentService.showData();
          if (!studentService.studentList.isEmpty()) {
            changePreference(studentService, sc);
          } else {
            System.out.println("No data to display.");
          }
        }
        break;
        case 3: { // delete data by rollNo
          String rollno;
          System.out.println("Enter Roll No of the student");
          rollno = sc.nextLine().trim();
          studentService.deleteStudent(rollno);
        }
        break;
        case 4:// save data to repo
          studentService.saveState();
          break;
        case 5: // Exit
          if (studentService.isStateChanged) {
            System.out.println("Do you want to save the changes? (y/n)");
            String input = sc.nextLine().trim();
            if (input.length() != 1 || (input.charAt(0) != 'y' && input.charAt(0) != 'n')) {
              System.out.println("invalid Input");
              continue;
            }
            if (input.charAt(0) == 'y') {
              studentService.saveState();
            }
          }
          studentService.exit();
          ProgramEnd = true;
          break;
        default:
          System.out.println("Invalid Input");
      }
    }
  }
}
