import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import controller.ConsoleController;
import model.maze.AbstractMaze;
import model.maze.RoomMaze;
import model.maze.WrappingRoomMaze;

/**
* Driver class to run the program.
* */
public class Driver {

  /**
   * Main class which creates the configuration and hands
   * over control to controller after initialization.
   * */

  static Scanner scanner = new Scanner(System.in);

  /**
   * Driver program to run the maze.
   * @param args : 1 -  for Maze to be configured from file  2 -  for interactive maze creation
   * */

  public static void main(String... args) throws IOException {

    Driver driver = new Driver();
    if (args.length != 0 && args[0].equals("-1")) {
      scanner = new Scanner(new FileInputStream("MazeConfig.txt"));
    }
    driver.run();
  }

  void run() throws IOException {

    System.out.println("Hello!");
    System.out.println("Enter type of maze - \n 1 - Room Maze \n 2 - Wrapping Room Maze");
    final int type = scanner.nextInt();
    System.out.println("Enter rows: ");
    int rows = scanner.nextInt();
    System.out.println("Enter columns: ");
    int cols = scanner.nextInt();
    System.out.println("Enter remaining walls for maze: ");
    int remainingWalls = scanner.nextInt();
    System.out.println("Enter starting row of player: ");
    int playerRow = scanner.nextInt();
    System.out.println("Enter starting column of player: ");
    int playerCol = scanner.nextInt();
    System.out.println("Enter starting row of wumpus: ");
    int wumpusRow = scanner.nextInt();
    System.out.println("Enter starting column of wumpus: ");
    int wumpusCol = scanner.nextInt();
    System.out.println("Enter percentage of pits in the maze: ");
    int pitPercent = scanner.nextInt();
    System.out.println("Enter percentage of bats in the maze: ");
    int batPercent = scanner.nextInt();
    System.out.println("Enter number of arrows for player: ");
    int arrows = scanner.nextInt();

    AbstractMaze maze;

    switch (type) {
      case 1:
        try {
          maze = new RoomMaze(rows, cols, remainingWalls, playerRow, playerCol, wumpusRow,
                  wumpusCol, pitPercent, batPercent, arrows);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid arguments.");
          return;
        }
        break;

      case 2:
        try {
          maze = new WrappingRoomMaze(rows, cols, remainingWalls, playerRow, playerCol, wumpusRow,
                  wumpusCol, pitPercent, batPercent, arrows);
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid arguments.");
          return;
        }
        break;
      default:
        System.out.println("Invalid maze type.");
        return;
    }
    new ConsoleController(new InputStreamReader(System.in), System.out,  maze).playGame();

  }
}
