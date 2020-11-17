package controller;

import java.io.IOException;
import java.util.Scanner;

import model.maze.Maze;


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
//    Scanner scan = new Scanner(this.rd);
//    int a = scan.nextInt();
//    int b = scan.nextInt();
//    //System.out.println(model.add(a, b));
//    this.out.append(String.valueOf(model.add(a, b))).append("\n");
  }
}
