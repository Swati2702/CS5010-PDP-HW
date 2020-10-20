package algo;

import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Class to create a graph structure for the maze.
 * */
public class MazeGraph {

  private final int rows;
  private final int columns;
  private final boolean isWrapped;
  private final Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> adjList;
  private final Map<Pair<Integer, Integer>, NodeType> nodeTypeList;
  private final Random generator = new Random(32);

  /**
   * Creates a graph structure for the maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * */
  public MazeGraph(int numberOfRows, int numberOfColumns, boolean isWrapped) {
    if (numberOfColumns < 0 || numberOfRows < 0) {
      throw  new IllegalArgumentException("Invalid arguments.");
    }
    rows = numberOfRows;
    columns = numberOfColumns;
    adjList = new HashMap<>();
    this.isWrapped = isWrapped;
    nodeTypeList = new HashMap<>();
    constructGraph();
  }

  private void constructGraph() {

    Pair<Integer, Integer> temp;
    for (int i = 0; i < rows ; i++ ) {

      for (int j = 0; j < columns; j++) {

        List<Pair<Integer, Integer>> nodeList = new ArrayList<>();
        if (isWrapped || i + 1  < rows) {
          temp = new Pair<>((i + 1 + rows) % rows , j);
          nodeList.add(temp);
        }

        if (isWrapped || j + 1 < columns) {
          temp = new Pair<>(i, (j + 1 + columns) % columns);
          nodeList.add(temp);
        }

        if (isWrapped || i - 1 >= 0) {
          temp = new Pair<>((i - 1 + rows ) % rows, j);
          nodeList.add(temp);
        }

        if (isWrapped || j - 1 >= 0) {
          temp = new Pair<>(i, (j -  1 + columns ) % columns);
          nodeList.add(temp);
        }

        Pair<Integer, Integer> node = new Pair<>(i, j);
        adjList.put(node, nodeList);

        nodeTypeList.put(node, NodeType.BLANK);
      }
    }
  }

  /**
   * Assigns goldPercent% of locations (at random) gold coins in them that the player can pick.
   * @param goldPercent : % of locations in maze that have gold coins in them
   *                   that the player can pick
   * */
  public void assignGold(double goldPercent) {
    if (goldPercent < 0.0 || goldPercent > 100.0) {
      throw  new IllegalArgumentException("gold percent invalid.");
    }

    int goldCount = (int)(adjList.size() * goldPercent) / 100;
    List<Pair<Integer, Integer>> list = new ArrayList<>(nodeTypeList.keySet());
    while (goldCount != 0) {
      Pair<Integer, Integer> temp = list.get(generator.nextInt(list.size()));
      if (nodeTypeList.get(temp) == NodeType.BLANK) {
        nodeTypeList.put(temp, NodeType.GOLD);
        goldCount--;
      }
    }
  }

  /**
   * Assigns thiefPercent% of locations (at random)  a thief that takes
   * some of the player's gold coins.
   * @param thiefPercent : % of locations in maze that have a thief that takes
   *                  some of the player's gold coins
   * */

  public void assignThief(double thiefPercent) {

    if (thiefPercent < 0 || thiefPercent > 100.0) {
      throw  new IllegalArgumentException("thief percent invalid.");
    }

    int thiefCount = (int)(adjList.size() * thiefPercent) / 100;
    List<Pair<Integer, Integer>> list = new ArrayList<>(nodeTypeList.keySet());
    while (thiefCount != 0) {
      Pair<Integer, Integer> temp = list.get(generator.nextInt(list.size()));
      if (nodeTypeList.get(temp) == NodeType.BLANK) {
        nodeTypeList.put(temp, NodeType.THIEF);
        thiefCount--;
      }
    }
  }

  /**
   * Produce the list of  possible moves of the player (North, South, East or West) from their
   * current location.
   * @param location : location from which possible moves of the player are to be determined
   * @throws IllegalArgumentException : if location is not in maze
   * */

  public EnumSet<Direction> getPossibleMoves(Pair<Integer, Integer> location) {
    if (location.fst >= rows || location.fst < 0 || location.snd >= columns || location.snd < 0 ) {
      throw new IllegalArgumentException("Invalid location.");
    }
    List<Pair<Integer, Integer>> list = adjList.get(location);
    EnumSet<Direction> directions = EnumSet.noneOf(Direction.class);
    Pair<Integer, Integer> node;

    //North
    if (isWrapped) {
      node = new Pair<>((location.fst - 1 + rows) % rows, location.snd);
    }
    else {
      node = new Pair<>((location.fst - 1 ), location.snd);
    }
    if (list.contains(node)) {
      directions.add(Direction.NORTH);
    }

    //South
    if (isWrapped) {
      node = new Pair<>((location.fst + 1 + rows) % rows, location.snd);
    }
    else {
      node = new Pair<>((location.fst + 1), location.snd);
    }
    if (list.contains(node)) {
      directions.add(Direction.SOUTH);
    }

    //East
    if (isWrapped) {
      node = new Pair<>(location.fst, (location.snd + 1 + columns) % columns);
    }
    else {
      node = new Pair<>(location.fst, (location.snd + 1));
    }
    if (list.contains(node)) {
      directions.add(Direction.EAST);
    }


    //West
    if (isWrapped) {
      node = new Pair<>(location.fst, (location.snd - 1 + columns) % columns);
    }
    else {
      node = new Pair<>(location.fst, (location.snd - 1) );
    }
    if (list.contains(node)) {
      directions.add(Direction.WEST);
    }

    return directions;
  }

  /**
   * Gets the number of nodes in the mazegraph.
   * */
  public int getNumberOfNodes() {
    return adjList.size();
  }

  void createMazeGraphFromEdges(ArrayList<Pair<Pair<Integer, Integer>,
          Pair<Integer, Integer>>> edges) {
    adjList.replaceAll((p, v) -> new ArrayList<>());
    for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair : edges) {
      List<Pair<Integer, Integer>> temp = adjList.get(pair.fst);
      temp.add(pair.snd);
      adjList.put(pair.fst, temp);
      List<Pair<Integer, Integer>> temp2 = adjList.get(pair.snd);
      temp2.add(pair.fst);
      adjList.put(pair.snd, temp2);
    }
  }

  List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> getEdges() {
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges = new ArrayList<>();
    HashSet<Pair<Integer, Integer>> visited = new HashSet<>();
    for (Pair<Integer, Integer> pair : adjList.keySet() ) {
      visited.add(pair);
      for (Pair<Integer, Integer> pair1 : adjList.get(pair)) {
        if (!visited.contains(pair1)) {
          edges.add(new Pair<>(pair, pair1));
        }
      }
    }
    return edges;
  }

  /**
   * Gets the adjacency list of the mazegraph.
   * */
  public Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> getMazeGraphAdjacencyList() {
    return adjList;
  }

  /**
   * Gets the node type for each node of the mazegraph.
   * */
  public Map<Pair<Integer, Integer>, NodeType> getNodeTypeList() {
    return nodeTypeList;
  }

  @Override
  public String toString() {
    return "MazeGraph{" + "rows=" + rows + ", columns=" + columns
            + ", isWrapped=" + isWrapped + ", adjList=" + adjList.toString() + '}';
  }
}
