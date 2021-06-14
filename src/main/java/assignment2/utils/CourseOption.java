package assignment2.utils;

import assignment2.StudentService;

public class CourseOption {
  public static enum Option {//global krna hai
    A,
    B,
    C,
    D,
    E,
    F;

    public static boolean contains(String test) {
      for (Option c : Option.values()) {
        if (c.name().equals(test)) return true;
      }
      return false;
    }
  }
}
