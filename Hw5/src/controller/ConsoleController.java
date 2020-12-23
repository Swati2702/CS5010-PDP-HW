package controller;

import java.io.IOException;
import java.util.Scanner;

import model.algo.Direction;
import model.maze.Maze;
import model.maze.PlayerStatus;


/**
 * Controller that takes input through console and displays the outcome on the console itself.
 * */
public class ConsoleController implements GameController {
  private final Appendable out;
  private final Scanner scan;
  private final Maze maze;

  /**
   * Constructor for controller.
   * */
  public ConsoleController(Readable in, Appendable out, Maze maze) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (maze == null) {
      throw new IllegalArgumentException("Model can't be null");
    }
    this.out = out;
    scan = new Scanner(in);
    this.maze = maze;
  }

  @Override
  public void playGame() throws IOException {

    try {
      while (maze.getPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
        out.append("\n\nYou are in cave (").append(String.valueOf(maze.getPlayer()
                .getCurrentLocation().fst)).append(",")
                .append(String.valueOf(maze.getPlayer().getCurrentLocation().snd)).append(")");
        out.append("\nTunnels lead to the ");
        for (Direction direction : maze.getPossibleMoves()) {
          out.append(direction.toString()).append(" ");
        }
        out.append("\n\nShoot or Move (S-M)?");
        String choice = scan.next();
        switch (choice) {
          case "S":
          case "Shoot":
          case "SHOOT":
            out.append("\nNo. of caves?");
            int noCave = scan.nextInt();
            out.append("\nToward cave?");
            String direction = scan.next();
            switch (direction) {
              case "N":
              case "NORTH":
                out.append(maze.shootArrow(Direction.NORTH, noCave));
                break;
              case "S":
              case "SOUTH":
                out.append(maze.shootArrow(Direction.SOUTH, noCave));
                break;
              case "E":
              case "EAST":
                out.append(maze.shootArrow(Direction.EAST, noCave));
                break;
              case "W":
              case "WEST":
                out.append(maze.shootArrow(Direction.WEST, noCave));
                break;
              default: out.append("\nInvalid Shooting direction. Try Again!");
            }
            break;
          case "M":
          case "Move":
          case "MOVE":
            out.append("\nWhere to?");
            direction = scan.next();
            switch (direction) {
              case "N":
              case "NORTH":
                out.append(maze.makeMove(Direction.NORTH));
                break;
              case "S":
              case "SOUTH":
                out.append(maze.makeMove(Direction.SOUTH));
                break;
              case "E":
              case "EAST":
                out.append(maze.makeMove(Direction.EAST));
                break;
              case "W":
              case "WEST":
                out.append(maze.makeMove(Direction.WEST));
                break;
              default: out.append("\nInvalid move. Try Again!");
            }
            break;
          default: out.append("\nInvalid input.  Try Again!");
        }
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }
}
