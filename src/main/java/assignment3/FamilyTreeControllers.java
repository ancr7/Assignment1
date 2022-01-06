package assignment3;

import assignment3.utils.IOUtils;
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
        String ChoiceString = IOUtils.input();
        // validating Menu option choice
        SingleDigitNumberValidator.isValid(ChoiceString);
        int choice = Integer.parseInt(ChoiceString);
        switch (choice) {
          case 1: {
            String nodeId = IOUtils.getNodeId();
            NumberValidator.isValid(nodeId);
            IOUtils.getOutput(familyTreeService.getImmediateParent(Integer.parseInt(nodeId)));
            break;
          }
          case 2: {
            String nodeId = IOUtils.getNodeId();
            NumberValidator.isValid(nodeId);
            IOUtils.getOutput(familyTreeService.getImmediateChildren(Integer.parseInt(nodeId)));
            break;
          }
          case 3: {
            String nodeId = IOUtils.getNodeId();
            NumberValidator.isValid(nodeId);
            IOUtils.getOutput(familyTreeService.getAncestors(Integer.parseInt(nodeId)));
            break;
          }
          case 4: {
            String nodeId = IOUtils.getNodeId();
            NumberValidator.isValid(nodeId);
            IOUtils.getOutput(familyTreeService.getDescendants(Integer.parseInt(nodeId)));
            break;
          }
          case 5: {
            String parentId = IOUtils.getParentId();
            String childId = IOUtils.getChildId();
            NumberValidator.isValid(parentId);
            NumberValidator.isValid(childId);
            familyTreeService.deleteEdge(Integer.parseInt(parentId), Integer.parseInt(childId));
            break;
          }
          case 6: {
            String nodeId = IOUtils.getNodeId();
            NumberValidator.isValid(nodeId);
            familyTreeService.deleteNode(Integer.parseInt(nodeId));
            break;
          }
          case 7: {
            String parentId = IOUtils.getParentId();
            String childId = IOUtils.getChildId();
            NumberValidator.isValid(parentId);
            NumberValidator.isValid(childId);
            familyTreeService.addEdge(Integer.parseInt(parentId), Integer.parseInt(childId));
            break;
          }
          case 8: {
            String nodeId = IOUtils.getNodeId();
            String nodeName = IOUtils.getNodeName();
            String additionalInfo = IOUtils.getAdditionalInfo();
            NumberValidator.isValid(nodeId);
            familyTreeService.addNode(Integer.parseInt(nodeId), nodeName, additionalInfo);
            break;
          }
          case 9: {
            exit = true;
            break;
          }
        }
      }
    } catch (Exception e) {
      IOUtils.getOutput(e.getMessage());
    }
  }
}
