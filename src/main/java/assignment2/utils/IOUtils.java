package assignment2.utils;

import assignment2.exceptions.InvalidException;
import assignment2.utils.inputValidators.CustomValidator;
import java.util.Scanner;

public class IOUtils {

  private static final Scanner sc = new Scanner(System.in);

  public static String getInput() { return sc.nextLine().trim(); }

  public static void getOutput(final String Text) {
    System.out.println(Text);
  }

  public static char getYesNoInput() throws InvalidException {
    String input = getInput();
    CustomValidator.isYesOrNoValid(input);
    return input.charAt(0);
  }

  public static char getSaveAndExitInput() throws InvalidException {
    getOutput(Constants.SAVE_CHANGES);
    return getYesNoInput();
  }
}
