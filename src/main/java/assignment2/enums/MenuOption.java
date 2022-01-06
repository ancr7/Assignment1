package assignment2.enums;

import assignment2.utils.Constants;

public enum MenuOption { // Menu options which user will see

  ADD(Constants.ADD_USER_DETAILS),
  DISPLAY(Constants.DISPLAY_USER_DETAILS),
  DELETE(Constants.DELETE_USER_DETAIL),
  SAVE(Constants.SAVE),
  EXIT(Constants.EXIT);

  private final String value;

  private MenuOption(final String value) {
    this.value = value;
  }

  public String getValue() { return value; }
}