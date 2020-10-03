package conservatory;

import bird.Bird;

import java.util.ArrayList;
import java.util.List;

/**
 * Aviary holds five birds at maximum.
 * Flightless birds, birds of prey, and waterfowl should not be mixed with other bird types.
 * */

public class Aviary {

  /**
   * Package-private list of birds in a particular aviary.
   */
  List<Bird> birds = new ArrayList<>();

  public List<Bird> getBirds() {
    return birds;
  }

  boolean checkAviaryForBirdCategory(Class<? extends Bird> birdCategory) {
    boolean flag;
    for (Bird bird : birds) {
      flag = birdCategory.equals(bird.getClass());
      if (!flag) {
        return false;
      }
    }

    return true;
  }
}
