package maze;

import algo.Direction;
import algo.MazeGraph;
import algo.ModifiedKruskal;
import algo.NodeType;
import com.sun.tools.javac.util.Pair;

import java.util.EnumSet;
import java.util.Map;

/**
 * Abstract maze class to be extended by all kinds of mazes.
 * Implements maze interface.
 *
 */
public class AbstractMaze implements Maze {
  protected final int numberOfRows;
  protected final int numberOfColumns;
  protected final boolean isWrapped;
  protected final int numberOfRemainingWalls;
  protected final MazeGraph mazeGraph;
  protected  Pair<Integer, Integer> startingPoint;
  protected  Pair<Integer, Integer> goalPoint;
  protected final Player player;

  /**
   * Constructor for room mazes.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @param numberOfRemainingWalls : number of remaining walls in the room maze - specified by user
   * @param isWrapped : tells if room is wrapping room maze or not
   * @throws IllegalArgumentException : if any arguments are invalid
   */
  protected AbstractMaze(int numberOfRows, int numberOfColumns, boolean isWrapped,
                         int numberOfRemainingWalls)
          throws IllegalArgumentException {
    if (numberOfRows <= 0 || numberOfColumns <= 0 || numberOfRemainingWalls < 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.isWrapped = isWrapped;
    if ((isWrapped && numberOfRemainingWalls > calculateNumberOfRemainingWallsForWrappedMaze())
            || (!isWrapped
            && numberOfRemainingWalls > calculateNumberOfRemainingWallsForUnwrappedMaze())) {
      throw new IllegalArgumentException(" Number of remaining walls not possible.");
    }
    this.numberOfRemainingWalls = numberOfRemainingWalls;
    this.mazeGraph = new MazeGraph(numberOfRows, numberOfColumns, isWrapped);
    this.player = new Player();
    createMaze();
  }

  /**
   * Constructor for perfect maze.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @throws IllegalArgumentException : if any arguments are invalid
   */
  protected AbstractMaze(int numberOfRows, int numberOfColumns)
          throws IllegalArgumentException {
    if (numberOfRows <= 0 || numberOfColumns <= 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.isWrapped = false;
    this.numberOfRemainingWalls = calculateNumberOfRemainingWallsForUnwrappedMaze();
    this.mazeGraph = new MazeGraph(numberOfRows, numberOfColumns, false);
    this.player = new Player();
    createMaze();
  }

  private void createMaze() {
    ModifiedKruskal modifiedKruskal = new ModifiedKruskal();
    modifiedKruskal.createMazeWithKruskal(mazeGraph, numberOfRemainingWalls);
    mazeGraph.assignGold(GOLDLOCATIONPERCENTAGE);
    mazeGraph.assignThief(THIEFLOCATIONPERCENTAGE);
  }

  private int calculateNumberOfRemainingWallsForUnwrappedMaze() {
    int totalEdges = 2 * numberOfRows * numberOfColumns  - numberOfRows
            - numberOfColumns ;
    return totalEdges - (numberOfRows * numberOfColumns)  + 1;
  }

  private int calculateNumberOfRemainingWallsForWrappedMaze() {
    int totalEdges = 2 * (numberOfRows + 1) * (numberOfColumns + 1 )  - (numberOfRows + 1)
            - (numberOfColumns + 1 ) ;
    return totalEdges - (numberOfRows * numberOfColumns)  + 1;
  }



  @Override
  public int getNumberOfRows() {
    return numberOfRows;
  }

  @Override
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  @Override
  public boolean isWrapped() {
    return isWrapped;
  }

  @Override
  public int getNumberOfRemainingWalls() {
    return numberOfRemainingWalls;
  }

  @Override
  public void setStartingPoint(int row, int column) {
    if (row < 0 || row >= numberOfRows || column < 0 || column >= numberOfColumns) {
      throw new IllegalArgumentException("Starting point invalid.");
    }
    startingPoint = new Pair<>(row, column);
    player.setCurrentLocation(startingPoint);
  }

  @Override
  public void setGoalPoint(int row, int column) throws IllegalArgumentException {
    if (row < 0 || row >= numberOfRows || column < 0 || column >= numberOfColumns) {
      throw new IllegalArgumentException("Goal point invalid.");
    }
    Pair<Integer, Integer> temp = new Pair<>(row, column);
    if (temp.equals(startingPoint)) {
      throw  new IllegalArgumentException("Cannot be same as starting point.");
    }
    goalPoint = temp;
  }

  @Override
  public Pair<Integer, Integer> getStartingPoint() {
    return startingPoint;
  }

  @Override
  public Pair<Integer, Integer> getGoalPoint() {
    return goalPoint;
  }


  @Override
  public EnumSet<Direction> getPossibleMoves() {
    return mazeGraph.getPossibleMoves(player.getCurrentLocation());
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public Map<Pair<Integer, Integer>, NodeType> getNodeTypeList() {
    return mazeGraph.getNodeTypeList();
  }

  @Override
  public void makeMove(Direction direction) throws IllegalArgumentException {

    if (!getPossibleMoves().contains(direction)) {
      throw new IllegalArgumentException("Invalid move.");
    }

    Pair<Integer, Integer> currentLocation = player.getCurrentLocation();
    Pair<Integer, Integer> newLocation = null;

    switch (direction) {
      case EAST:
        newLocation = new Pair<>(currentLocation.fst,
                (currentLocation.snd + 1 + numberOfColumns) % numberOfColumns);
        break;
      case WEST:
        newLocation = new Pair<>(currentLocation.fst,
                (currentLocation.snd - 1 + numberOfColumns) % numberOfColumns);
        break;
      case NORTH:
        newLocation = new Pair<>((currentLocation.fst - 1 + numberOfRows)
                % numberOfRows, currentLocation.snd);
        break;
      case SOUTH:
        newLocation = new Pair<>((currentLocation.fst + 1 + numberOfRows)
                % numberOfRows, currentLocation.snd);
        break;
      default: throw new IllegalArgumentException("Invalid action.");
    }
    Map<Pair<Integer, Integer>, NodeType> nodeTypeMap = mazeGraph.getNodeTypeList();
    NodeType nodeType = nodeTypeMap.get(newLocation);
    if (nodeType.equals(NodeType.GOLD)) {
      player.increaseGoldCoinsCount();
      nodeTypeMap.replace(newLocation, NodeType.BLANK);
    }
    else if (nodeType.equals(NodeType.THIEF)) {
      player.decreaseGoldCoinsCount(player.getGoldCount() * THIEFLOSSFACTOR);
    }

    player.setCurrentLocation(newLocation);
  }

  @Override
  public String toString() {
    return "AbstractMaze{"
            + "numberOfRows=" + numberOfRows
            + ", numberOfColumns=" + numberOfColumns
            +  ", isWrapped=" + isWrapped
            +  ", numberOfRemainingWalls=" + numberOfRemainingWalls
            + ", mazeGraph=" + mazeGraph.getMazeGraphAdjacencyList().toString()
            + ", startingPoint=" + startingPoint + ", goalPoint=" + goalPoint + '}';
  }
}

