package assignment3.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CheckCycleInGraph {

  public static boolean hasCycle(final HashMap<Integer, HashSet<Integer>> Edge) {
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
    return ElementCount != inDegree.size();
  }
}
