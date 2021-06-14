package assignment2;

import assignment2.exceptions.InvalidInputException;
import assignment2.sort_package.SortByAgeImpl;
import assignment2.sort_package.SortByModel;
import assignment2.sort_package.SortByNameImpl;
import assignment2.sort_package.SortByRollNoImpl;

public class GetSortFactory {
  public static SortByModel getFactory(int choice,boolean isAscending) throws Exception {
    switch (choice) {
        case 1:  return new SortByNameImpl(isAscending);
        case 2:  return new SortByAgeImpl(isAscending);
        case 3:  return new SortByRollNoImpl(isAscending);
    }
    throw new InvalidInputException("Invalid choice");
  }
}
