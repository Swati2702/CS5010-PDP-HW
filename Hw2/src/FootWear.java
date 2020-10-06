/**
 * Represents a FootWear. A non combined FootWear has only defense Strength or attack power.
 * But a combined footwear can have both.
 * */

public class FootWear extends AbstractItem {

  /**
   * Constructor for hand gear.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param power : power of the gear - can be attack or defense - told by @param which
   * @param which : tells whether the power is an attack power or defense strength
   * @throws IllegalArgumentException if  power of the gear is negative  or
   *                adjective or noun of the gear is not a valid name
   * */
  public FootWear(String nameAdj, String nameNoun, int power, String which) {
    super(nameAdj, nameNoun, power, which);
  }

  /**
   * Package-private constructor.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param attackPower : attack power of the gear
   * @param defensePower : defense strength of the gear
   * @param isCombined : tells whether the item is a combination of two gears or not
   * @throws IllegalArgumentException if  attack power of the gear or defense strength
   *        of the gear is negative  or  adjective or noun of the gear is not a valid name
   * */
  FootWear(String nameAdj, String nameNoun, int attackPower, int defensePower, boolean isCombined) {
    super(nameAdj, nameNoun, attackPower, defensePower, isCombined);
  }


}
