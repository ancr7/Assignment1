package assignment3;

import assignment3.utils.InputOutputUtil;
import assignment3.utils.ShowOptions;
import assignment3.utils.validators.NumberValidator;
import assignment3.utils.validators.SingleDigitNumberValidator;

public class FamilyTreeControllers {

  public static void main(String[] args) {

    boolean exit = false;    // Exit flag
    FamilyTreeService familyTreeService = new FamilyTreeService();
    try {
      while (!exit) {
        ShowOptions.printStartOptions();
        String ChoiceString = InputOutputUtil.input();
        // validating Menu option choice
        SingleDigitNumberValidator.isValid(ChoiceString);
        int choice = Integer.parseInt(ChoiceString);
        switch (choice) {
          case 1: {
            String NodeId = InputOutputUtil.getNodeId();
            NumberValidator.isValid(NodeId);
            System.out.println(familyTreeService.getImmediateParent(Integer.parseInt(NodeId)));
            break;
          }
          case 2: {
            String NodeId = InputOutputUtil.getNodeId();
            NumberValidator.isValid(NodeId);
            System.out.println(familyTreeService.getImmediateChildren(Integer.parseInt(NodeId)));
            break;
          }
          case 3: {
            String NodeId = InputOutputUtil.getNodeId();
            NumberValidator.isValid(NodeId);
            System.out.println(familyTreeService.getAncestors(Integer.parseInt(NodeId)));
            break;
          }
          case 4: {
            String NodeId = InputOutputUtil.getNodeId();
            NumberValidator.isValid(NodeId);
            System.out.println(familyTreeService.getDescendants(Integer.parseInt(NodeId)));
            break;
          }
          case 5: {
            String ParentId = InputOutputUtil.getParentId();
            String ChildId = InputOutputUtil.getChildId();
            NumberValidator.isValid(ParentId);
            NumberValidator.isValid(ChildId);
            familyTreeService.deleteEdge(Integer.parseInt(ParentId), Integer.parseInt(ChildId));
            break;
          }
          case 6: {
            String NodeId = InputOutputUtil.getNodeId();
            NumberValidator.isValid(NodeId);
            familyTreeService.deleteNode(Integer.parseInt(NodeId));
            break;
          }
          case 7: {
            String ParentId = InputOutputUtil.getParentId();
            String ChildId = InputOutputUtil.getChildId();
            NumberValidator.isValid(ParentId);
            NumberValidator.isValid(ChildId);
            familyTreeService.addEdge(Integer.parseInt(ParentId), Integer.parseInt(ChildId));
            break;
          }
          case 8: {
            String NodeId = InputOutputUtil.getNodeId();
            String NodeName = InputOutputUtil.getNodeName();
            String AdditionalInfo = InputOutputUtil.getAdditionalInfo();
            NumberValidator.isValid(NodeId);
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
