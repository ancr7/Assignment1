package assignment3.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CheckCycleInGraph {

  // Topological sort to check graph has cyclic dependency or not.
  public static boolean hasCycle(final HashMap<Integer, HashSet<Integer>> edgeList) {
    HashMap<Integer, Integer> inDegree = new HashMap<Integer, Integer>();
    for (HashSet<Integer> value : edgeList.values()) {
      for (int a : value) {
        inDegree.put(a, inDegree.getOrDefault(a, 0) + 1);
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    int ElementCount = 0;
    for (var value : edgeList.keySet()) {
      if (!inDegree.containsKey(value)) inDegree.put(value, 0);
      if (inDegree.get(value) == 0) {
        queue.add(value);
        ElementCount += 1;
      }
    }
    while (!queue.isEmpty()) {
      var front = queue.poll();
      for (int a : edgeList.get(front)) {
        inDegree.put(a, inDegree.get(a) - 1);
      }
      for (int a : edgeList.get(front)) {
        if (inDegree.getOrDefault(a, 0) == 0) {
          queue.add(a);
          ElementCount += 1;
        }
      }
    }
    return ElementCount != inDegree.size();
  }
}
