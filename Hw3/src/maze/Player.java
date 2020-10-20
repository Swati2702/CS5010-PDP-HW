package maze;

import com.sun.tools.javac.util.Pair;


/**
 * This class represents player in the maze.
 * It tells current location and current gold coins count of the player.
 * It provides methods to access the current location  and current gold coins count of the player.
 * */
public class Player {

  private Pair<Integer, Integer> currentLocation;
  private double goldCoinsCount;

  Player() {
    this.goldCoinsCount = 0.0;
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
  public double getGoldCount() {
    return goldCoinsCount;
  }

  void increaseGoldCoinsCount() {
    this.goldCoinsCount += Maze.GOLDCOUNTPERROOM;
  }

  void decreaseGoldCoinsCount(double count) {
    this.goldCoinsCount -= count ;
  }

}
