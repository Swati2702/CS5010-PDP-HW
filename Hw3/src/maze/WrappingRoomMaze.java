package maze;

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
   * @throws IllegalArgumentException if any parameter is negative
   * */
  public WrappingRoomMaze(int numberOfRows, int numberOfColumns, int numberOfRemainingWalls) {
    super(numberOfRows, numberOfColumns, true, numberOfRemainingWalls);
  }

}
