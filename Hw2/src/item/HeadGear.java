package item;

/**
 * Represents a Headgear. A headgear has only defense Strength.
 * */
public class HeadGear extends AbstractItem {

  /**
   * Constructor for head gear.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param defenseStrength : defense strength of the gear
   * @throws IllegalArgumentException if defense strength is negative  or
   *                adjective or noun of the gear is not a valid name
   * */
  public HeadGear(String nameAdj, String nameNoun, int defenseStrength) {
    super(nameAdj, nameNoun, defenseStrength,  DEFENSE);
  }

  /**
   * Package private  Constructor for head gear.
   * Will be called by FootWears or combined gears since they can have both powers.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param defenseStrength : defense strength of the gear
   * @param isCombined : tells whether the item is a combination of two gears or not
   * @throws IllegalArgumentException if defenseStrength is negative or
   *                adjective or noun of the gear is not a valid name
   * */

  HeadGear(String nameAdj, String nameNoun, int defenseStrength, boolean isCombined) {
    super(nameAdj, nameNoun, 0, defenseStrength, isCombined);
  }


}
