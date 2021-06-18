package assignment2.enums;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidInputException;
import assignment2.utils.Constants;

public enum SortType {
  ASCENDING(Constants.ASCENDING),
  DESCENDING(Constants.DESCENDING);

  private final String value;

  private SortType(final String value) {
    this.value = value;
  }

  public String getValue() { return value; }

  public static SortType stringToSortType(String test) throws InvalidException {
    for (SortType element : SortType.values()) { // If ASCENDING or DESCENDING
      if (element.name().equals(test)) return element;
    }
    throw new InvalidInputException("Invalid option");
  }
}