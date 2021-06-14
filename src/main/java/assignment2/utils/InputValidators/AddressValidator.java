package assignment2.utils.InputValidators;

import assignment2.exceptions.InvalidException;
import assignment2.exceptions.InvalidFormatException;

public class AddressValidator {

  public static void isAddressValid(String address) throws InvalidException {
    // check address empty
    if (address.isEmpty()) {
      throw new InvalidFormatException("Invalid address");
    }
  }
}
