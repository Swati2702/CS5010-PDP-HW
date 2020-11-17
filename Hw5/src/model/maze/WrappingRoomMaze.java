package model.maze;

/**
 * In this room maze, locations at the
 * top, bottom, left and right can "wrap" to the other side.
 * */
public class WrappingRoomMaze extends RoomMaze {

  /**
   * Constructor for Wrapping Room Maze.
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
  public WrappingRoomMaze(int numberOfRows, int numberOfColumns, int numberOfRemainingWalls,
                          int playerRow, int playerColumn, int wumpusRow,
                          int wumpusColumn, double pitPercent, double batPercent,
                          int numberOfArrows) {
    super(numberOfRows, numberOfColumns, true, numberOfRemainingWalls,
            playerRow, playerColumn, wumpusRow, wumpusColumn,
            pitPercent, batPercent, numberOfArrows);
  }

}
