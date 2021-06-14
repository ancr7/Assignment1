package assignment2.utils;

import assignment2.exceptions.InvalidException;
import assignment2.utils.InputValidators.YesOrNoValidate;
import java.util.Scanner;

public class InputOutputUtil {

  private static final Scanner sc = new Scanner(System.in);

  public static String input() { return sc.nextLine().trim(); }

  public static void output(String Text) {
    System.out.println(Text);
  }

  public static char getYesNoInput() throws InvalidException {
    String input = InputOutputUtil.input();
    YesOrNoValidate.isValid(input);
    return input.charAt(0);
  }

  public static char getSaveAndExitInput() throws InvalidException {
    InputOutputUtil.output(Constants.SAVE_CHANGES);
    return InputOutputUtil.getYesNoInput();
  }
}
