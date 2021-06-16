package assignment3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import assignment2.GetSortFactory;
import assignment2.enums.SortType;
import assignment2.sort_package.SortByModel;
import assignment3.utils.validators.NumberValidator;
import assignment3.utils.validators.SingleDigitNumberValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FamilyTreeServiceTest {

  FamilyTreeService familyTreeService;

  @BeforeEach
  void intializeService() {familyTreeService = new FamilyTreeService();}

  @Test
  void getImmediateParentWhenParentIsAbsent() {
    try {
      familyTreeService.addNode(1, "", "");
      List<Integer> parent = familyTreeService.getImmediateParent(1);
      assertTrue(parent.isEmpty());
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void getImmediateParentWhenInputNodeIsAbsent() {
    try {
      familyTreeService.addNode(1, "", "");
      List<Integer> parent = familyTreeService.getImmediateParent(5);
      assert (false);
    } catch (Exception e) {
      assertEquals("Input Node not found.", e.getMessage());
    }
  }

  @Test
  void getImmediateParent() throws Exception {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    familyTreeService.addEdge(1, 2);
    List<Integer> parent = familyTreeService.getImmediateParent(2);
    assertEquals(Collections.singletonList(1), parent);
  }

  @Test
  void getImmediateChildrenWhenInputNodeIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = new ArrayList<Integer>();
    try {
      parent = familyTreeService.getImmediateChildren(2);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void getImmediateChildrenWhenChildIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = familyTreeService.getImmediateChildren(1);
    assertEquals(true, parent.isEmpty());
  }

  @Test
  void getImmediateChildren() {
    try {
      familyTreeService.addNode(1, "", "");
      familyTreeService.addNode(2, "", "");
      familyTreeService.addEdge(1, 2);
      List<Integer> parent = familyTreeService.getImmediateChildren(1);
      assertEquals(Collections.singletonList(2), parent);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void getAncestorsWhenAncestorsIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = familyTreeService.getAncestors(1);
    assertEquals(true, parent.isEmpty());
  }

  @Test
  void getAncestorsWhenInputNodeIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = new ArrayList<Integer>();
    try {
      parent = familyTreeService.getAncestors(2);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void getAncestors() {
    try {
      familyTreeService.addNode(1, "", "");
      familyTreeService.addNode(2, "", "");
      familyTreeService.addNode(3, "", "");
      familyTreeService.addEdge(1, 3);
      familyTreeService.addEdge(2, 3);
      List<Integer> parent = familyTreeService.getAncestors(3);
      assertEquals(Arrays.asList(1, 2), parent);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void getDescendantsWhenDescendantsIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = familyTreeService.getDescendants(1);
    assertEquals(true, parent.isEmpty());
  }

  @Test
  void getDescendantsWhenInputNodeIsAbsent() {
    familyTreeService.addNode(1, "", "");
    List<Integer> parent = new ArrayList<Integer>();
    try {
      parent = familyTreeService.getDescendants(2);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void getDescendants() {
    try {
      familyTreeService.addNode(1, "", "");
      familyTreeService.addNode(2, "", "");
      familyTreeService.addNode(3, "", "");
      familyTreeService.addEdge(1, 2);
      familyTreeService.addEdge(1, 3);
      List<Integer> parent = familyTreeService.getDescendants(1);
      assertEquals(Arrays.asList(2, 3), parent);
    } catch (Exception e) {
      assert false;
    }
  }

  @Test
  void addEdgeWhenCycleForms() {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    familyTreeService.addNode(3, "", "");
    try {
      familyTreeService.addEdge(1, 2);
      familyTreeService.addEdge(2, 3);
      familyTreeService.addEdge(3, 1);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Adding this edge will create Cycle"));
    }
  }

  @Test
  void deleteEdgeWhenNodeNotPresent() {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    try {
      familyTreeService.deleteEdge(1, 5);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void deleteEdgeWhenEdgeNotPresent() {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    try {
      familyTreeService.deleteEdge(1, 2);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("No Edge Found"));
    }
  }

  @Test
  void addNodeAlreadyPresent() {
    try {
      familyTreeService.addNode(1, "", "");
      familyTreeService.addNode(1, "", "");
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Node already present."));
    }
  }

  @Test
  void deleteNodeWhenNodeNotPresent() {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    try {
      familyTreeService.deleteNode(3);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void deleteNodeWhenNodeIsPresent() {
    familyTreeService.addNode(1, "", "");
    familyTreeService.addNode(2, "", "");
    try {
      familyTreeService.deleteNode(1);
      familyTreeService.validateEdgeNodeId(1);
      assert (false);
    } catch (Exception e) {
      assertTrue(e.getMessage().contains("Input Node not found."));
    }
  }

  @Test
  void checkSingleDigitNumberValidator() {
    try {
      SingleDigitNumberValidator.isValid("2");
    } catch (Exception e) {
      assert false;
    }
  }
  @Test
  void checkSingleDigitNumberValidatorError() {
    try {
      SingleDigitNumberValidator.isValid("23");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format",e.getMessage());
    }
  }
  @Test
  void checkNumberValidator() {
    try {
      NumberValidator.isValid("2");
    } catch (Exception e) {
      assert false;
    }
  }
  @Test
  void checkNumberValidatorError() {
    try {
      NumberValidator.isValid("2A");
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid number format",e.getMessage());
    }
  }
  @Test
  void checkGetSortFactoryInvalidChoice() {
    try {
      SortByModel model = GetSortFactory.getFactory(4, SortType.ASCENDING);
      assert false;
    } catch (Exception e) {
      assertEquals("Invalid choice",e.getMessage());
    }
  }
}