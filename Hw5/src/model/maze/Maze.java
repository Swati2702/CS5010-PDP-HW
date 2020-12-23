package model.maze;

import com.sun.tools.javac.util.Pair;

import java.util.EnumSet;
import java.util.Map;

import model.algo.Direction;
import model.algo.NodeType;




/**
 * Interface for Maze.
 * This interface represents a maze . It provides methods to access properties like
 * number of rows, number of columns, number of remaining walls, whether maze is wrapped or not.
 * It also provides methods to set  and get starting point, goal point in the maze,
 * get possible moves from current location and also make a specific move in a direction.
 *
 * */
public interface Maze {


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
   * Gets number of remaining walls in the maze.
   * @return  number of remaining walls in the maze.
   * */
  int getNumberOfRemainingWalls();

  /**
   * Gets number of bottomless pits in the maze.
   * @return  number of  bottomless pits in the maze.
   * */
  int getNumberOfPits();

  /**
   * Gets number of Superbats in the maze.
   * @return  number of  Superbats in the maze.
   * */
  int getNumberOfBats();

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
   * Make a particular specified move for the player.
   * @param direction : the direction in which the move is to be made
   * @throws IllegalArgumentException if cannot make move in given direction
   * */
  String makeMove(Direction direction) throws IllegalArgumentException;

  /**
   * Method to shoot an arrow in the given direction.
   * @param direction : the direction in which the arrow is to be shot
   * @param distance : the number of caves the arrow will traverse (limit 5)
   * @throws IllegalArgumentException if arrows exhausted or distance illegal
   * */
  String shootArrow(Direction direction, int distance) throws IllegalArgumentException;


  /**
   * Method to visualize the maze.
   * */
  String visualize();

  /**
   * Method to set seed for random maze generation.
   * */
  void setSeed(int seed);

}
