package assignment2.enums;

public enum CourseOption { // Course Option
  A,
  B,
  C,
  D,
  E,
  F;
  public static boolean contains(String test) {
    for (CourseOption element : CourseOption.values()) {
      if (element.name().equals(test)) return true;
    }
    return false;
  }
}

