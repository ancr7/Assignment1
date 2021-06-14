package assignment3;

import assignment3.utils.CheckCycleInGraph;
import assignment2.exceptions.InvalidException;
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
  void validateEdgeNodeId(int NodeId) {
    if (!edgeList.containsKey(NodeId)) throw new RuntimeException("Input Node not found.");
  }

  // validating Parent Exist or not
  void validateEdgeParentId(int ParentId) {
    if (!edgeList.containsKey(ParentId)) throw new RuntimeException("Parent Node not found.");
  }

  void validateReverseEdgeNodeId(int NodeId) {
    if (!reverseEdge.containsKey(NodeId)) throw new RuntimeException("Input Node not found.");
  }

  // method to get immediate parent
  List<Integer> getImmediateParent(int NodeId) {
    validateReverseEdgeNodeId(NodeId);
    var parentSet = reverseEdge.get(NodeId);
    return new ArrayList<>(parentSet);
  }

  // method to get immediate childrens
  List<Integer> getImmediateChildren(int NodeId) {
    validateEdgeNodeId(NodeId);
    var childrenSet = edgeList.get(NodeId);
    return new ArrayList<>(childrenSet);
  }

  // helper method for finding Ancestors/Descendants
  void getAncestorsOrDescendantsHelper(int NodeId, boolean isDescendant,
                                       ArrayList<Integer> NodeList) {
    for (var value : (isDescendant ? edgeList : reverseEdge).get(NodeId)) {
      NodeList.add(value);
      getAncestorsOrDescendantsHelper(value, isDescendant, NodeList);
    }
  }

  // method to get all Ancestors
  List<Integer> getAncestors(int NodeId) {
    validateEdgeNodeId(NodeId);
    ArrayList<Integer> AncestorsList = new ArrayList<>();
    getAncestorsOrDescendantsHelper(NodeId, false, AncestorsList);
    return AncestorsList;
  }

  // method to get all Descendants
  List<Integer> getDescendants(int NodeId) {
    validateEdgeNodeId(NodeId);
    ArrayList<Integer> DescendantsList = new ArrayList<>();
    getAncestorsOrDescendantsHelper(NodeId, true, DescendantsList);
    return DescendantsList;
  }

  // Topological sort to check graph has cyclic dependency or not.


  // method to add edge
  void addEdge(int ParentId, int NodeId) throws InvalidException{
    validateEdgeNodeId(NodeId);
    validateEdgeParentId(ParentId);
    edgeList.get(ParentId).add(NodeId);
    reverseEdge.get(NodeId).add(ParentId);
    if (CheckCycleInGraph.hasCycle(edgeList)) {
      deleteEdge(ParentId, NodeId);
      throw new InvalidException("Adding this edge will create Cycle");
    }
  }

  // method to delete edge
  void deleteEdge(int ParentId, int NodeId) {
    validateEdgeParentId(ParentId);
    validateEdgeNodeId(NodeId);
    if (!edgeList.get(ParentId).contains(NodeId)) {
      throw new RuntimeException("No Edge Found");
    }
    edgeList.get(ParentId).remove(NodeId);
    reverseEdge.get(NodeId).remove(ParentId);
  }

  // method to delete a node
  void deleteNode(int NodeId) {
    validateEdgeNodeId(NodeId);
    edgeList.remove(NodeId);
    for (HashSet<Integer> value : reverseEdge.values()) {
      value.remove(NodeId);
    }
  }

  // method to add a node
  void addNode(int NodeId, String NodeName, String AdditionalInfo) {
    if (edgeList.containsKey(NodeId) || reverseEdge.containsKey(NodeId))
      throw new RuntimeException("Node already present.");
    FamilyTreeModel model = new FamilyTreeModel();
    model.setNodeId(NodeId);
    model.setNodeName(NodeName);
    model.setAdditionalInfo(AdditionalInfo);
    idToObject.put(NodeId, model);
    edgeList.put(NodeId, new HashSet<>());
    reverseEdge.put(NodeId, new HashSet<>());
  }
}
