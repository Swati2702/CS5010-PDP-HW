package algo;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


//Referred from https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

/**
 * Modified Kruskal Algorithm to construct Maze.
 * */
public class ModifiedKruskal {

  private final HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> parent = new HashMap<>();
  private final HashMap<Pair<Integer, Integer>, Integer> rank = new HashMap<>();

  private Pair<Integer, Integer> find( Pair<Integer, Integer> i) {

    if (parent.get(i) == i) {
      return i;
    }

    return find( parent.get(i));
  }

  private void union( Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
    Pair<Integer, Integer> xroot = find( x);
    Pair<Integer, Integer> yroot = find(y);

    if (rank.get(xroot) < rank.get(yroot) ) {
      parent.put(xroot, yroot);
    }
    else if (rank.get(xroot) > rank.get(yroot)) {
      parent.put(yroot, xroot);
    }
    else {
      parent.put(yroot, xroot);
      rank.put(xroot, rank.get(xroot) + 1);
    }
  }


  /**
   * Modified Kruskal Algo to create a maze from a mazegraph.
   * @param graph : a graph which you want to convert into a maze.
   * @param numberOfRemainingWalls : the number of walls that should remain after
   *                              the maze is complete
   * @throws IllegalArgumentException : if number of remaining walls not possible
   *                      with the given graph
   * */
  public void createMazeWithKruskal(MazeGraph graph, int numberOfRemainingWalls)
          throws IllegalArgumentException {
    ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> result = new ArrayList<>();
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> adjList =  graph.getEdges();

    for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> temp : adjList) {
      parent.put(temp.fst, temp.fst);
      rank.put(temp.fst, 0);
      parent.put(temp.snd, temp.snd);
      rank.put(temp.snd, 0);
    }

    int e = 0;
    int j = 0;
    while ( e < graph.getNumberOfNodes() - 1) {
      Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> temp = adjList.get(j++);

      Pair<Integer, Integer> u = temp.fst;
      Pair<Integer, Integer> v = temp.snd;

      Pair<Integer, Integer> x = find(u);
      Pair<Integer, Integer> y = find(v);

      if (x != y) {
        e += 1;
        result.add(temp);
        union( x, y);
      }
    }

    ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> savedEdges = new ArrayList<>();
    for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair : adjList) {
      if (!result.contains(pair)) {
        savedEdges.add(pair);
      }
    }

    if (savedEdges.size() < numberOfRemainingWalls ) {
      throw new IllegalArgumentException("Number of remaining walls not possible with the input.");
    }

    int numberOfSavedEdges = savedEdges.size();
    Iterator<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> iterator = savedEdges.iterator();
    while ( numberOfSavedEdges != numberOfRemainingWalls && iterator.hasNext()) {
      result.add(iterator.next());
      numberOfSavedEdges--;
    }

    graph.createMazeGraphFromEdges(result);
  }
}
