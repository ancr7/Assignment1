package assignment2;

import assignment2.enums.SortType;
import assignment2.exceptions.InvalidInputException;
import assignment2.sort_package.SortByAgeImpl;
import assignment2.sort_package.SortByModel;
import assignment2.sort_package.SortByNameImpl;
import assignment2.sort_package.SortByRollNoImpl;
import assignment2.utils.Constants;

public class GetSortFactory {
  public static SortByModel getFactory(final int choice,final SortType sortType) throws Exception {
    switch (choice) {
        case 1:  return new SortByNameImpl(sortType);
        case 2:  return new SortByAgeImpl(sortType);
        case 3:  return new SortByRollNoImpl(sortType);
    }
    throw new InvalidInputException(Constants.INVALID_CHOICE);
  }
}
