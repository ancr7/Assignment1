package Assignment_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FamilyTreeService {

  private final HashMap<Integer, HashSet<Integer>> Edge;
  private final HashMap<Integer, HashSet<Integer>> ReverseEdge;
  private final HashMap<Integer, FamilyTreeModel> idToObject;

  public FamilyTreeService() {
    Edge = new HashMap<>();
    ReverseEdge = new HashMap<>();
    idToObject = new HashMap<>();
  }

  // validating Node Exist or not
  void validateEdgeNodeId(int NodeId) {
    if (!Edge.containsKey(NodeId)) throw new RuntimeException("Input Node not found.");
  }

  // validating Parent Exist or not
  void validateEdgeParentId(int ParentId) {
    if (!Edge.containsKey(ParentId)) throw new RuntimeException("Parent Node not found.");
  }

  void validateReverseEdgeNodeId(int NodeId) {
    if (!ReverseEdge.containsKey(NodeId)) throw new RuntimeException("Input Node not found.");
  }

  // method to get immediate parent
  List<Integer> getImmediateParent(int NodeId) {
    validateReverseEdgeNodeId(NodeId);
    var parentSet = ReverseEdge.get(NodeId);
    return new ArrayList<>(parentSet);
  }

  // method to get immediate childrens
  List<Integer> getImmediateChildren(int NodeId) {
    validateEdgeNodeId(NodeId);
    var childrenSet = Edge.get(NodeId);
    return new ArrayList<>(childrenSet);
  }

  // helper method for finding Ancestors/Descendants
  void getAncestorsOrDescendantsHelper(int NodeId, boolean isDescendant,
                                       ArrayList<Integer> NodeList) {
    for (var value : (isDescendant ? Edge : ReverseEdge).get(NodeId)) {
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
  boolean hasCycle() {
    HashMap<Integer, Integer> inDegree = new HashMap<Integer, Integer>();
    for (HashSet<Integer> value : Edge.values()) {
      for (int a : value) {
        inDegree.put(a, inDegree.getOrDefault(a, 0) + 1);
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    int ElementCount = 0;
    for (var value : Edge.keySet()) {
      if (!inDegree.containsKey(value)) inDegree.put(value, 0);
      if (inDegree.get(value) == 0) {
        queue.add(value);
        System.out.println(value);
        ElementCount += 1;
      }
    }
    while (!queue.isEmpty()) {
      var front = queue.poll();
      for (int a : Edge.get(front)) {
        inDegree.put(a, inDegree.get(a) - 1);
      }
      for (int a : Edge.get(front)) {
        if (inDegree.getOrDefault(a, 0) == 0) {
          queue.add(a);
          ElementCount += 1;
        }
      }
    }
    System.out.println(ElementCount);
    return ElementCount != inDegree.size();
  }

  // method to add edge
  void addEdge(int ParentId, int NodeId) {
    validateEdgeNodeId(NodeId);
    validateEdgeParentId(ParentId);
    Edge.get(ParentId).add(NodeId);
    ReverseEdge.get(NodeId).add(ParentId);
    if (hasCycle()) {
      deleteEdge(ParentId, NodeId);
      System.out.println("Adding this edge will create Cycle");
      throw new RuntimeException("Adding this edge will create Cycle");
    }
  }

  // method to delete edge
  void deleteEdge(int ParentId, int NodeId) {
    validateEdgeParentId(ParentId);
    validateEdgeNodeId(NodeId);
    if (!Edge.get(ParentId).contains(NodeId)) {
      throw new RuntimeException("No Edge Found");
    }
    Edge.get(ParentId).remove(NodeId);
    ReverseEdge.get(NodeId).remove(ParentId);
  }
  // method to delete a node
  void deleteNode(int NodeId) {
    validateEdgeNodeId(NodeId);
    Edge.remove(NodeId);
    for (HashSet<Integer> value : ReverseEdge.values()) {
      value.remove(NodeId);
    }
  }
  // method to add a node
  void addNode(int NodeId, String NodeName, String AdditionalInfo) {
    if (Edge.containsKey(NodeId) || ReverseEdge.containsKey(NodeId))
      throw new RuntimeException("Node already present.");
    FamilyTreeModel model = new FamilyTreeModel();
    model.setNodeId(NodeId);
    model.setNodeName(NodeName);
    model.setAdditionalInfo(AdditionalInfo);
    idToObject.put(NodeId, model);
    Edge.put(NodeId, new HashSet<>());
    ReverseEdge.put(NodeId, new HashSet<>());
  }
}
