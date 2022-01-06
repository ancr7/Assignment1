package assignment3;
import assignment3.exceptions.InvalidException;
import assignment3.utils.CheckCycleInGraph;
import assignment3.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class FamilyTreeService {

  public final HashMap<Integer, HashSet<Integer>> edgeList;
  public final HashMap<Integer, HashSet<Integer>> reverseEdge;
  public final HashMap<Integer, FamilyTreeModel> idToObject;

  public FamilyTreeService() {
    edgeList = new HashMap<>();
    reverseEdge = new HashMap<>();
    idToObject = new HashMap<>();
  }

  // validating Node Exist or not
  void validateEdgeNodeId(int nodeId) throws InvalidException {
    if (!edgeList.containsKey(nodeId)) throw new InvalidException(Constants.INPUT_NODE_NOT_FOUND);
  }

  // validating Parent Exist or not
  void validateEdgeParentId(int parentId) throws InvalidException {
    if (!edgeList.containsKey(parentId))
      throw new InvalidException(Constants.PARENT_NODE_NOT_FOUND);
  }

  void validateReverseEdgeNodeId(int nodeId) throws InvalidException {
    if (!reverseEdge.containsKey(nodeId))
      throw new InvalidException(Constants.INPUT_NODE_NOT_FOUND);
  }

  // method to get immediate parent
  List<Integer> getImmediateParent(int nodeId) throws InvalidException {
    validateReverseEdgeNodeId(nodeId);
    var parentSet = reverseEdge.get(nodeId);
    return new ArrayList<>(parentSet);
  }

  // method to get immediate childrens
  List<Integer> getImmediateChildren(int nodeId) throws InvalidException {
    validateEdgeNodeId(nodeId);
    var childrenSet = edgeList.get(nodeId);
    return new ArrayList<>(childrenSet);
  }

  // helper method for finding Ancestors/Descendants
  void getAncestorsOrDescendantsHelper(int nodeId, boolean isDescendant,
                                       ArrayList<Integer> nodeList) {
    for (var value : (isDescendant ? edgeList : reverseEdge).get(nodeId)) {
      nodeList.add(value);
      getAncestorsOrDescendantsHelper(value, isDescendant, nodeList);
    }
  }

  // method to get all Ancestors
  List<Integer> getAncestors(int nodeId) throws InvalidException {
    validateEdgeNodeId(nodeId);
    ArrayList<Integer> AncestorsList = new ArrayList<>();
    getAncestorsOrDescendantsHelper(nodeId, false, AncestorsList);
    return AncestorsList;
  }

  // method to get all Descendants
  List<Integer> getDescendants(int nodeId) throws InvalidException {
    validateEdgeNodeId(nodeId);
    ArrayList<Integer> DescendantsList = new ArrayList<>();
    getAncestorsOrDescendantsHelper(nodeId, true, DescendantsList);
    return DescendantsList;
  }


  // method to add edge
  void addEdge(int parentId, int nodeId) throws InvalidException {
    validateEdgeNodeId(nodeId);
    validateEdgeParentId(parentId);
    edgeList.get(parentId).add(nodeId);
    reverseEdge.get(nodeId).add(parentId);
    if (CheckCycleInGraph.hasCycle(edgeList)) {
      deleteEdge(parentId, nodeId);
      throw new InvalidException(Constants.ADDING_EDGE_MAKES_CYCLE);
    }
  }

  // method to delete edge
  void deleteEdge(int parentId, int nodeId) throws InvalidException {
    validateEdgeParentId(parentId);
    validateEdgeNodeId(nodeId);
    if (!edgeList.get(parentId).contains(nodeId)) {
      throw new InvalidException(Constants.NO_EDGE_FOUND);
    }
    edgeList.get(parentId).remove(nodeId);
    reverseEdge.get(nodeId).remove(parentId);
  }

  // method to delete a node
  void deleteNode(int nodeId) throws InvalidException {
    validateEdgeNodeId(nodeId);
    edgeList.remove(nodeId);
    for (HashSet<Integer> value : reverseEdge.values()) {
      value.remove(nodeId);
    }
  }

  // method to add a node
  void addNode(int nodeId, String nodeName, String additionalInfo) throws InvalidException {
    if (edgeList.containsKey(nodeId) || reverseEdge.containsKey(nodeId))
      throw new InvalidException(Constants.NODE_ALREADY_PRESENT);
    FamilyTreeModel model = new FamilyTreeModel();
    model.setNodeId(nodeId);
    model.setNodeName(nodeName);
    model.setAdditionalInfo(additionalInfo);
    idToObject.put(nodeId, model);
    edgeList.put(nodeId, new HashSet<>());
    reverseEdge.put(nodeId, new HashSet<>());
  }
}
