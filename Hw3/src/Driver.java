import com.sun.tools.javac.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import algo.Direction;
import maze.AbstractMaze;
import maze.PerfectMaze;
import maze.RoomMaze;
import maze.WrappingRoomMaze;

/**
 * Driver class to run the program.
 * */
public class Driver {

  static Scanner scanner = new Scanner(System.in);

  /**
   * Driver program to run the maze.
   * @param args : 1 -  for Sample run 1 ,  2 - for sample run 2, 3 - for interactive mode
   * */
  public static void main(String... args) throws FileNotFoundException {
    Driver driver = new Driver();
    if (args.length != 0 && args[0].equals("-1")) {
      scanner = new Scanner(new FileInputStream("Run1.txt"));
    }
    else if (args.length != 0 && args[0].equals("-2")) {
      scanner = new Scanner(new FileInputStream("Run2.txt"));
    }

    driver.run();


  }

  void run() {

    System.out.println("Hello!");
    System.out.println("Enter type of maze - \n 1 - Perfect Maze \n 2 - Room Maze");
    System.out.println(" 3  - Wrapped Room Maze");
    int type = scanner.nextInt();
    System.out.println("Enter rows: ");
    int rows = scanner.nextInt();
    System.out.println("Enter columns: ");
    int cols = scanner.nextInt();
    int remainingWalls = 0;
    if (type == 2 || type == 3) {
      System.out.println("Enter remaining walls for maze: ");
      remainingWalls = scanner.nextInt();
    }
    AbstractMaze maze;

    switch (type) {
      case 1:
        try {
          maze = new PerfectMaze(rows, cols);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid arguments.");
          return;
        }
        break;
      case 2:
        try {
          maze = new RoomMaze(rows, cols, remainingWalls);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid arguments.");
          return;
        }
        break;

      case 3:
        try {
          maze = new WrappingRoomMaze(rows, cols, remainingWalls);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid arguments.");
          return;
        }
        break;
      default:
        System.out.println("Invalid maze type.");
        return;
    }

    System.out.println("Enter start position of maze ");
    System.out.println("Enter row: ");
    int startRow = scanner.nextInt();
    System.out.println("Enter column: ");
    int startCol = scanner.nextInt();
    try {
      maze.setStartingPoint(startRow, startCol);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid arguments.");
      return;
    }

    System.out.println("Enter goal position of maze ");
    System.out.println("Enter row: ");
    startRow = scanner.nextInt();
    System.out.println("Enter column: ");
    startCol = scanner.nextInt();
    try {
      maze.setGoalPoint(startRow, startCol);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid arguments.");
      return;
    }


    while (true) {
      System.out.println("Current location: " + maze.getPlayer().getCurrentLocation().fst
              + "," + maze.getPlayer().getCurrentLocation().snd);
      System.out.println("Gold count: " + maze.getPlayer().getGoldCount());

      System.out.println("Node information:  ");
      for (Pair<Integer, Integer> loc : maze.getNodeTypeList().keySet()) {
        System.out.println(loc.fst + "," + loc.snd + " : " + maze.getNodeTypeList().get(loc));
      }
      System.out.println("Available moves: ");
      for (Direction direction : maze.getPossibleMoves()) {
        System.out.println(direction);
      }
      System.out.println("Select a move from available moves.");
      String direction = scanner.next();
      switch (direction) {
        case "NORTH":
          maze.makeMove(Direction.NORTH);
          break;
        case "SOUTH":
          maze.makeMove(Direction.SOUTH);
          break;
        case "EAST":
          maze.makeMove(Direction.EAST);
          break;
        case "WEST":
          maze.makeMove(Direction.WEST);
          break;
        default: System.out.println("Invalid move. ");
      }

      if (maze.getPlayer().getCurrentLocation().toString().equals(maze.getGoalPoint().toString())) {
        System.out.println("Goal reached! ");
        break;
      }

    }
  }
}
