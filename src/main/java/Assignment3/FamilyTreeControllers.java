package Assignment_3;

import java.util.Scanner;

public class FamilyTreeControllers {

  // Print Input Options
  static void printOptions() {
    System.out.println("Enter Choice no.");
    System.out.println("1. Get the immediate parents of a node.");
    System.out.println("2. Get the immediate children of a node.");
    System.out.println("3. Get the ancestors of a node.");
    System.out.println("4. Get the descendants of a node.");
    System.out.println("5. Delete dependency from a tree.");
    System.out.println("6. Delete a node from a tree.");
    System.out.println("7. Add a new dependency to a tree.");
    System.out.println("8. Add a new node to tree.");
    System.out.println("9. Exit");
  }

  // Validate Input NodeId Format
  static boolean validateInputNodeId(String Input) {
    if (Input.isEmpty() || !Input.matches("[0-9]*")) {
      System.out.println("Invalid Input");
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    // Exit flag
    boolean exit = false;
    FamilyTreeService familyTreeService = new FamilyTreeService();
    try {
      while (!exit) {
        printOptions();
        Scanner sc = new Scanner(System.in);
        String ChoiceString = sc.nextLine().trim();
        // validating Menu option choice
        if (ChoiceString.length() != 1 || !ChoiceString.matches("[1-9]*")) {
          System.out.println("Invalid choice");
          continue;
        }
        int choice = Integer.parseInt(ChoiceString);
        switch (choice) {
          case 1: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            System.out.println(familyTreeService.getImmediateParent(Integer.parseInt(NodeId)));
            break;
          }
          case 2: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            System.out.println(familyTreeService.getImmediateChildren(Integer.parseInt(NodeId)));
            break;
          }
          case 3: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            System.out.println(familyTreeService.getAncestors(Integer.parseInt(NodeId)));
            break;
          }
          case 4: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            System.out.println(familyTreeService.getDescendants(Integer.parseInt(NodeId)));
            break;
          }
          case 5: {
            System.out.println("Enter Parent id");
            String ParentId = sc.nextLine().trim();
            System.out.println("Enter Child id");
            String ChildId = sc.nextLine().trim();
            if (!validateInputNodeId(ParentId) || !validateInputNodeId(ChildId)) break;
            familyTreeService.deleteEdge(Integer.parseInt(ParentId), Integer.parseInt(ChildId));
            break;
          }
          case 6: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            familyTreeService.deleteNode(Integer.parseInt(NodeId));
            break;
          }
          case 7: {
            System.out.println("Enter parent id");
            String ParentId = sc.nextLine().trim();
            System.out.println("Enter child Name");
            String ChildId = sc.nextLine().trim();
            if (!validateInputNodeId(ParentId) || !validateInputNodeId(ChildId)) break;
            familyTreeService.addEdge(Integer.parseInt(ParentId), Integer.parseInt(ChildId));
            break;
          }
          case 8: {
            System.out.println("Enter Node id");
            String NodeId = sc.nextLine().trim();
            System.out.println("Enter Node Name");
            String NodeName = sc.nextLine().trim();
            System.out.println("Enter Additional info");
            String AdditionalInfo = sc.nextLine().trim();
            if (!validateInputNodeId(NodeId)) break;
            familyTreeService.addNode(Integer.parseInt(NodeId), NodeName, AdditionalInfo);
            break;
          }
          case 9: {
            exit = true;
            break;
          }
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
