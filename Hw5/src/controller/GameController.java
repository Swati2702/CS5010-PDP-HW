package controller;

import java.io.IOException;

/**
 * Represents a Controller for Hunt the wumpus: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface GameController {
  /**
   * Execute a single game of Hunt the wumpus given a  Model. When the game is over,
   * the playGame method ends.
   */
  void playGame() throws IOException;
}
