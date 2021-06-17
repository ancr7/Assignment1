package assignment3.utils;

import assignment3.enums.MenuOption;

public class ShowOptions {

  // Print Input Options
  public static void printStartOptions() {
    for (MenuOption option : MenuOption.values()) {
      IOUtils.getOutput(option.getValue());
    }
  }
}
