package maze;

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
   * @throws IllegalArgumentException if any parameter is negative
   * */
  public RoomMaze(int numberOfRows, int numberOfColumns, int numberOfRemainingWalls) {
    super(numberOfRows, numberOfColumns, false, numberOfRemainingWalls);
  }

  /**
   * Protected Constructor for Room Maze -  to be called from wrapping room maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @param numberOfRemainingWalls : number of walls that should be left after maze creation
   * @throws IllegalArgumentException if any parameter is negative
   * */
  protected RoomMaze(int numberOfRows, int numberOfColumns,boolean isWrapped,
                     int numberOfRemainingWalls) {
    super(numberOfRows, numberOfColumns, isWrapped, numberOfRemainingWalls);
  }
}
