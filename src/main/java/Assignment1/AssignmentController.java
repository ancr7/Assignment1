package Assignment1;

import java.util.Scanner;

public class AssignmentController {

  private static AssignmentService service;

  public static void main(String args[]) {

    // 1. read data from the console
    Scanner input = new Scanner(System.in);
    String inputString = input.nextLine();
    inputString = inputString.trim();

    // 2. call service method to process the input (service.processInput();)
    ItemModel model = new ItemModel();
    service = new AssignmentService(model);

    double total = service.processInput(inputString);

    // 3. print output in the console return from service method
    if (total >= 0) System.out.println(total);

  }
}
