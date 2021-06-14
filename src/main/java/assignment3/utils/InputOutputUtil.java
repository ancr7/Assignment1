package assignment3.utils;

import java.util.Scanner;

public class InputOutputUtil {

  private static final Scanner sc = new Scanner(System.in);

  public static String input() {
    return sc.nextLine().trim();
  }

  public static String getNodeId() {
    System.out.println(Constants.ENTER_NODE_ID);
    return sc.nextLine().trim();
  }

  public static String getParentId() {
    System.out.println(Constants.ENTER_PARENT_ID);
    return sc.nextLine().trim();
  }

  public static String getChildId() {
    System.out.println(Constants.ENTER_CHILD_ID);
    return sc.nextLine().trim();
  }
  public static String getNodeName() {
    System.out.println(Constants.ENTER_NODE_NAME);
    return sc.nextLine().trim();
  }
  public static String getAdditionalInfo() {
    System.out.println(Constants.ENTER_ADDITIONAL_INFO);
    return sc.nextLine().trim();
  }
}
