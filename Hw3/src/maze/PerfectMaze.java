package maze;


/**
 * A perfect maze is the simplest type of maze for a computer to generate and solve.
 * It is defined as a maze which has one and only one path from any point in the maze
 * to any other point in the maze.
 * This means that the maze has no inaccessible sections, no circular paths, no open areas.
 * */
public class PerfectMaze extends AbstractMaze {

  /**
   * Constructor for Perfect Room Maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @throws IllegalArgumentException if any parameter is negative
   * */
  public PerfectMaze(int numberOfRows, int numberOfColumns) {
    super(numberOfRows, numberOfColumns);
  }


}
