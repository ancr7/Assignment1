package assignment3.enums;

import assignment3.utils.Constants;

public enum MenuOption { // Menu options which user will see
  ENTER_CHOICE(Constants.ENTER_CHOICE),
  GET_IMMEDIATE_PARENT(Constants.GET_IMMEDIATE_PARENT),
  GET_IMMEDIATE_CHILD(Constants.GET_IMMEDIATE_CHILD),
  GET_ANCESTORS(Constants.GET_ANCESTORS),
  GET_DESCENDANTS(Constants.GET_DESCENDANTS),
  DELETE_DEPENDENCY(Constants.DELETE_DEPENDENCY),
  DELETE_NODE(Constants.DELETE_NODE),
  ADD_DEPENDENCY(Constants.ADD_DEPENDENCY),
  ADD_NODE(Constants.ADD_NODE),
  EXIT(Constants.EXIT);

  private final String value;

  private MenuOption(final String value) { this.value = value; }

  public String getValue() { return value; }
}