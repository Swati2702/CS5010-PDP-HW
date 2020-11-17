package model.maze;

/**
 * A room maze categorizes locations in the maze as either
 * rooms or hallways, where a hallway has exactly two doors while a room has 1, 3 or 4 doors.
 * */
public class RoomMaze extends AbstractMaze {

  /**
   * Constructor for Room Maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @param numberOfRemainingWalls : number of walls that should be left after maze creation
   * @param playerRow : starting row of the player
   * @param playerColumn : starting column of the player
   * @param wumpusRow : starting row of the wumpus
   * @param wumpusColumn : starting column of the wumpus
   * @param batPercent : percentage of bats in the maze
   * @param pitPercent : percentage of pits in the maze
   * @throws IllegalArgumentException : if any arguments are invalid
   */
  public RoomMaze(int numberOfRows, int numberOfColumns, int numberOfRemainingWalls,
           int playerRow, int playerColumn, int wumpusRow,
                  int wumpusColumn, double pitPercent, double batPercent, int numberOfArrows) {
    super(numberOfRows, numberOfColumns, false, numberOfRemainingWalls,
            playerRow, playerColumn, wumpusRow, wumpusColumn,
            pitPercent, batPercent, numberOfArrows);
  }

  /**
   * Protected Constructor for Room Maze -  to be called from wrapping room maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @param numberOfRemainingWalls : number of walls that should be left after maze creation
   * @param playerRow : starting row of the player
   * @param playerColumn : starting column of the player
   * @param wumpusRow : starting row of the wumpus
   * @param wumpusColumn : starting column of the wumpus
   * @param batPercent : percentage of bats in the maze
   * @param pitPercent : percentage of pits in the maze
   * @throws IllegalArgumentException : if any arguments are invalid
   */
  protected RoomMaze(int numberOfRows, int numberOfColumns, boolean isWrapped,
                     int numberOfRemainingWalls, int playerRow, int playerColumn, int wumpusRow,
                     int wumpusColumn, double pitPercent, double batPercent, int numberOfArrows) {
    super(numberOfRows, numberOfColumns, isWrapped, numberOfRemainingWalls,
            playerRow, playerColumn, wumpusRow, wumpusColumn,
            pitPercent, batPercent, numberOfArrows);
  }

}
