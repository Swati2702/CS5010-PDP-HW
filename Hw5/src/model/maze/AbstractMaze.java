package model.maze;

import com.sun.tools.javac.util.Pair;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.algo.Direction;
import model.algo.MazeGraph;
import model.algo.ModifiedKruskal;
import model.algo.NodeType;



/**
 * Abstract maze class to be extended by all kinds of mazes.
 * Implements maze interface.
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
  protected final double pitPercent;
  protected final double batPercent;
  private final Random generator = new Random(32);


  /**
   * Constructor for room mazes.
   * @param numberOfRows : number of rows in the maze
   * @param numberOfColumns : number of columns in the maze
   * @param numberOfRemainingWalls : number of remaining walls in the room maze - specified by user
   * @param isWrapped : tells if room is wrapping room maze or not
   * @param playerRow : starting row of the player
   * @param playerColumn : starting column of the player
   * @param wumpusRow : starting row of the wumpus
   * @param wumpusColumn : starting column of the wumpus
   * @param batPercent : percentage of bats in the maze
   * @param pitPercent : percentage of pits in the maze
   * @throws IllegalArgumentException : if any arguments are invalid
   */
  protected AbstractMaze(int numberOfRows, int numberOfColumns, boolean isWrapped,
                         int numberOfRemainingWalls, int playerRow, int playerColumn, int wumpusRow,
                         int wumpusColumn, double pitPercent, double batPercent, int numberOfArrows)
          throws IllegalArgumentException {
    checkValidRowColsArrows(numberOfRows, numberOfColumns, numberOfArrows);
    this.numberOfRows = numberOfRows;
    this.numberOfColumns = numberOfColumns;
    this.isWrapped = isWrapped;
    checkValidWalls(isWrapped, numberOfRemainingWalls);
    this.numberOfRemainingWalls = numberOfRemainingWalls;
    this.player = new Player(numberOfArrows);
    checkValidPositionInMaze(playerRow, playerColumn);
    startingPoint = new Pair<>(playerRow, playerColumn);
    player.setCurrentLocation(startingPoint);
    checkValidPositionInMaze(wumpusRow, wumpusColumn);
    checkValidGoal(wumpusRow, wumpusColumn);
    goalPoint = new Pair<>(wumpusRow, wumpusColumn);
    this.pitPercent = pitPercent;
    this.batPercent = batPercent;
    this.mazeGraph = new MazeGraph(numberOfRows, numberOfColumns, isWrapped);
    createMaze();
  }

  private void createMaze() {
    ModifiedKruskal modifiedKruskal = new ModifiedKruskal();
    modifiedKruskal.createMazeWithKruskal(mazeGraph, numberOfRemainingWalls);
    mazeGraph.assignNodeType(pitPercent, NodeType.PIT);
    mazeGraph.assignNodeType(batPercent, NodeType.BAT);
  }

  private void checkValidRowColsArrows(int numberOfRows, int numberOfColumns, int numberOfArrows)
          throws IllegalArgumentException {
    if (numberOfRows <= 0 || numberOfColumns <= 0 || numberOfArrows < 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
  }

  private void checkValidWalls(boolean isWrapped, int numberOfRemainingWalls)
          throws IllegalArgumentException {
    if (numberOfRemainingWalls < 0
            || (isWrapped
            && numberOfRemainingWalls > calculateNumberOfRemainingWallsForWrappedMaze())
            || (!isWrapped
            && numberOfRemainingWalls > calculateNumberOfRemainingWallsForUnwrappedMaze())) {
      throw new IllegalArgumentException(" Number of remaining walls not possible.");
    }
  }

  private void checkValidPositionInMaze(int row, int column) throws IllegalArgumentException {
    if (row < 0 || row >= numberOfRows || column < 0
            || column >= numberOfColumns) {
      throw new IllegalArgumentException("Location invalid.");
    }
  }

  private void checkValidGoal(int row, int column) throws IllegalArgumentException {
    Pair<Integer, Integer> temp = new Pair<>(row, column);
    if (temp.equals(startingPoint)) {
      throw  new IllegalArgumentException("Cannot be same as starting point.");
    }
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
  public int getNumberOfPits() {
    return  (int)(mazeGraph.getMazeGraphAdjacencyList().size() * pitPercent) / 100;
  }

  @Override
  public int getNumberOfBats() {
    return  (int)(mazeGraph.getMazeGraphAdjacencyList().size() * batPercent) / 100;
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

  private static final Map<Direction, Direction> directionComplement = new HashMap<>();

  private static void makeComplementDictionary() {

    directionComplement.put(Direction.NORTH, Direction.SOUTH);
    directionComplement.put(Direction.SOUTH, Direction.NORTH);
    directionComplement.put(Direction.EAST, Direction.WEST);
    directionComplement.put(Direction.WEST, Direction.EAST);

  }

  /**
   * Returns the supposed new location if we assumed that there are only caves, no tunnels.
   * */
  private Pair<Integer, Integer>  makeMoveHelper(Pair<Integer, Integer> currentLocation,
                                                 Direction direction) {
    Pair<Integer, Integer> newLocation;

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
    return newLocation;
  }

  @Override
  public void makeMove(Direction direction) throws IllegalArgumentException {

    if (!getPossibleMoves().contains(direction)) {
      throw new IllegalArgumentException("Invalid move.");
    }
    makeComplementDictionary();
    Pair<Integer, Integer> newLocation  = makeMoveHelper(player.getCurrentLocation(), direction);

    //check if new location is a tunnel, then follow along it to get to the leading cave.
    if (mazeGraph.getPossibleMoves(newLocation).size() == 2) {
      EnumSet<Direction> moves =  mazeGraph.getPossibleMoves(newLocation);
      for (Direction move : moves) {
        if (!move.equals(directionComplement.get(direction))) {
          //move to the other door of the tunnel from which we did not enter
          makeMove(move);
        }
      }
    }
    else {
      setFinalLocation(newLocation);
    }
  }

  private Pair<Integer, Integer>  getActualLocation(Pair<Integer, Integer> currentLocation,
                                                    Direction direction)
          throws IllegalArgumentException {

    if (!getPossibleMoves().contains(direction)) {
      throw new IllegalArgumentException("Invalid move.");
    }
    Pair<Integer, Integer> newLocation = makeMoveHelper(currentLocation, direction);
    makeComplementDictionary();
    if (mazeGraph.getPossibleMoves(newLocation).size() == 2) {
      EnumSet<Direction> moves =  mazeGraph.getPossibleMoves(newLocation);
      for (Direction move : moves) {
        if (!move.equals(directionComplement.get(direction))) {
          return getActualLocation(newLocation, move);
        }
      }
    }
    return newLocation;
  }

  private void setFinalLocation(Pair<Integer, Integer> startPoint) {
    Map<Pair<Integer, Integer>, NodeType> nodeTypeMap = mazeGraph.getNodeTypeList();
    NodeType nodeType = nodeTypeMap.get(startPoint);

    switch (nodeType) {
      case PIT:
        //If pit is found, player dies
        player.setStatus(PlayerStatus.DEAD);
        player.setCurrentLocation(startPoint);
        break;

      case BAT:
        //transport by bat 50% of the time
        if (Math.random() < 0.5) {
          System.out.println("Snatch -- you are grabbed by superbats and ...");
          //transport
          int r = generator.nextInt(numberOfRows);
          int c = generator.nextInt(numberOfColumns);
          Pair<Integer, Integer> newLocation = new Pair<>(r, c);
          while (mazeGraph.getPossibleMoves(newLocation).size() == 2) {
            r = generator.nextInt(numberOfRows);
            c = generator.nextInt(numberOfColumns);
            newLocation = new Pair<>(r, c);
          }
          setFinalLocation(newLocation);
        }
        else {
          System.out.println("Whoa -- you successfully duck superbats that try to grab you");
          //land in the cave
          player.setCurrentLocation(startPoint);
        }
        break;
      default:
        // If transported point has wumpus
        if (startPoint.equals(getGoalPoint())) {
          System.out.println("Chomp, chomp, chomp, thanks for feeding the Wumpus!");
          System.out.println("Better luck next time");
          player.setStatus(PlayerStatus.DEAD);
        }
        player.setCurrentLocation(startPoint);
    }
    //If player is still alive, we check for nearby warnings
    if (player.getStatus() == PlayerStatus.ALIVE) {
      EnumSet<Direction> moves = getPossibleMoves();
      for (Direction move: moves) {
        Pair<Integer, Integer> loc = getActualLocation(player.getCurrentLocation(), move);
        if (nodeTypeMap.get(loc) == NodeType.PIT) {
          System.out.println("You feel a draft");
        }
        else if (loc.equals(getGoalPoint())) {
          System.out.println("You smell a Wumpus!");
        }
      }
    }
  }


  @Override
  public String toString() {
    return "AbstractMaze{"
            + "numberOfRows=" + numberOfRows
            + ", numberOfColumns=" + numberOfColumns
            +  ", isWrapped=" + isWrapped
            +  ", numberOfRemainingWalls=" + numberOfRemainingWalls
            + ", mazeGraph=" + mazeGraph.getMazeGraphAdjacencyList().toString()
            + ", startingPoint=" + startingPoint + ", goalPoint=" + goalPoint
            + ", pitPercent=" + pitPercent
            + ", batPercent=" + batPercent
            + '}';
  }

  @Override
  public void shootArrow(Direction direction, int distance) throws IllegalArgumentException,
          IllegalStateException {
    if (distance < 0 || player.getArrowCount() == 0) {
      throw new IllegalArgumentException("Invalid arguments.");
    }
    if (!getPossibleMoves().contains(direction)) {
      player.decreaseArrowCount();
      if (player.getArrowCount() == 0) {
        player.setStatus(PlayerStatus.DEAD);
      }
      return;
    }
    shootArrowHelper(direction, distance, player.getCurrentLocation());
  }


  void shootArrowHelper(Direction direction, int distance, Pair<Integer, Integer> currentLocation) {
    if (distance == 0 && getGoalPoint().equals(currentLocation)) {
      System.out.println("Hee hee hee, you got the wumpus!");
      System.out.println("Next time you won't be so lucky");
      player.setStatus(PlayerStatus.WINNER);
      player.decreaseArrowCount();
      return;
    }
    if (distance == 0 || !getPossibleMoves().contains(direction)) {
      player.decreaseArrowCount();
      return;
    }

    Pair<Integer, Integer> newLocation = makeMoveHelper(currentLocation, direction);
    makeComplementDictionary();
    if (mazeGraph.getPossibleMoves(newLocation).size() == 2) {
      EnumSet<Direction> moves =  mazeGraph.getPossibleMoves(newLocation);
      for (Direction move : moves) {
        if (!move.equals(directionComplement.get(direction))) {
          shootArrowHelper(move, distance, newLocation);
        }
      }
    }
    else {
      distance--;
      shootArrowHelper(direction, distance, newLocation);
    }

  }

  @Override
  public void visualize() {
    for (int i = 0; i < numberOfRows; i++) {
      for (int j = 0; j < numberOfColumns; j++) {

        NodeType nodeType = mazeGraph.getNodeTypeList().get(new Pair<>(i, j));
        switch (nodeType) {
          case PIT:
          case BAT:
            if (getGoalPoint().equals(new Pair<>(i, j))) {
              System.out.print("WUMPUS ");
            }
            else {
              System.out.print(nodeType + " ");
            }

            break;
          case BLANK:
            if (mazeGraph.getMazeGraphAdjacencyList().get(new Pair<>(i, j)).size() == 2) {
              System.out.print("TUNNEL ");
            } else {
              if (getGoalPoint().equals(new Pair<>(i, j))) {
                System.out.print("WUMPUS ");
              }
              else {
                System.out.print("CAVE ");
              }
            }
        }
      }
      System.out.println();
    }
  }

  @Override
  public void setSeed(int seed) {
    mazeGraph.setSeed(seed);
  }
}

