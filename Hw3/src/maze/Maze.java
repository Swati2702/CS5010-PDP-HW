package maze;

import com.sun.tools.javac.util.Pair;

import java.util.EnumSet;
import java.util.Map;

import algo.Direction;
import algo.NodeType;


/**
 * Interface for Maze.
 * This interface represents a maze . It provides methods to access properties like
 * number of rows, number of columns, number of remaining walls, whether maze is wrapped or not.
 * It also provides methods to set  and get starting point, goal point in the maze,
 * get possible moves from current location and also make a specific move in a direction.
 *
 * */
public interface Maze {

  int GOLDCOUNTPERROOM = 1;
  double THIEFLOSSFACTOR = 0.1;
  double GOLDLOCATIONPERCENTAGE = 20.0;
  double THIEFLOCATIONPERCENTAGE = 10.0;

  /**
   * Gets number of rows in the maze.
   * @return number of rows in the maze
   * */
  int getNumberOfRows();

  /**
   * Gets number of columns in the maze.
   * @return number of columns in the maze
   * */
  int getNumberOfColumns();

  /**
   * Tells if the maze is a wrapped maze or not.
   * @return if maze is wrapped or not
   * */
  boolean isWrapped();

  /**
   * Gets number of remaining walls in the maze.
   * @return  number of remaining walls in the maze.
   * */
  int getNumberOfRemainingWalls();

  /**
   * Specify the starting point of the maze.
   * @throws IllegalArgumentException if start point is invalid
   * */
  void setStartingPoint(int row, int column) throws IllegalArgumentException;

  /**
   * specify the goal location of the maze.
   * @throws IllegalArgumentException if goal point is same as starting point or point is invalid
   * */
  void setGoalPoint(int row, int column) throws IllegalArgumentException;

  /**
   * Get the starting point of the maze.
   * @return starting point of the maze
   * */
  Pair<Integer, Integer> getStartingPoint();

  /**
   * Get the goal location of the maze.
   * @return goal location of the maze
   * */
  Pair<Integer, Integer> getGoalPoint();

  /**
   * Get the current moves of the player.
   * @return the possible moves of the player from current location
   * */
  EnumSet<Direction> getPossibleMoves();

  /** Get the  player of the game.
   * @return the player object of the maze
   * */
  Player getPlayer();

  /** Get the  node type for each node in the graph.
   * @return a map of the node type for each node( gold/thief/blank)
   * */
  Map<Pair<Integer, Integer>, NodeType> getNodeTypeList();

  /**
   * Make a particular specified move for the player.
   * @throws IllegalArgumentException if cannot make move in given direction
   * */
  void makeMove(Direction direction) throws IllegalArgumentException;

}
