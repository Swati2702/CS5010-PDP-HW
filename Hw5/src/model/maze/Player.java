package model.maze;

import com.sun.tools.javac.util.Pair;


/**
 * This class represents player in the maze.
 * It tells current location and current gold coins count of the player.
 * It provides methods to access the current location  and current gold coins count of the player.
 * */
public class Player {

  private Pair<Integer, Integer> currentLocation;
  private int numberOfArrows;
  private PlayerStatus status;

  /** Constructor for player of the maze.
   * @param numberOfArrows : number of arrows that the player starts with
   * */
  Player(int numberOfArrows) {
    this.numberOfArrows = numberOfArrows;
    this.status = PlayerStatus.ALIVE;
  }

  /**
   * Sets the current location of the player.
   * */
  void setCurrentLocation(Pair<Integer, Integer> location) {
    currentLocation = location;

  }

  /**
   * Gets the current location of the player.
   * @return the current location of the player in the maze
   * */
  public Pair<Integer, Integer> getCurrentLocation() {
    return currentLocation;
  }

  /**
   * Gets the current gold count of the player.
   * @return the current gold count of the player.
   * */
  public int getArrowCount() {
    return numberOfArrows;
  }

  /**
   * Decrease arrow count of player by 1.
   * */
  void decreaseArrowCount() throws IllegalStateException {
    if (numberOfArrows == 0) {
      throw new IllegalStateException("Arrows already zero.");
    }
    this.numberOfArrows -= 1 ;
  }

  /**
   * Get status of the player - Alive, dead or winner.
   * @return Player status
   * */
  public PlayerStatus getStatus() {
    return status;
  }

  /**
   * Set status of the player - Alive, dead or winner.
   * */
  void setStatus(PlayerStatus status) {
    this.status = status;
  }


}
