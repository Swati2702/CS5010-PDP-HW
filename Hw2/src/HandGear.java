/**
 * Represents a Hand gear. A hand gear has only attack Strength.
 * */

public class HandGear extends AbstractItem {

  /**
   * Constructor for hand gear.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param attackPower : attack power of the gear
   * @throws IllegalArgumentException if attack power of the gear is negative  or
   *                adjective or noun of the gear is not a valid name
   * */
  public HandGear(String nameAdj, String nameNoun, int attackPower) {
    super(nameAdj, nameNoun, attackPower, ATTACK);
  }

  /**
   * Package private  Constructor for hand gear.
   * Will be called by FootWears or combined gears since they can have both powers.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param attackPower : attack power of the gear
   * @param isCombined : tells whether the item is a combination of two gears or not
   * @throws IllegalArgumentException if attack power of the gear is negative or
   *                adjective or noun of the gear is not a valid name
   * */
  HandGear(String nameAdj, String nameNoun, int attackPower, boolean isCombined) {
    super(nameAdj, nameNoun, attackPower, 0, isCombined);
  }
}
