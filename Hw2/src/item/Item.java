package item;

/**
 * This interface defines a gear/item and its associated methods.
 * */

public interface Item extends Comparable<Item> {

  String ATTACK = "Attack";
  String DEFENSE = "Defense";

  /**
   * Gets the name of the item.
   * */
  String getName();

  /**
   * Gets the attacking power of the item.
   * */
  int getAttackPower();

  /**
   * Gets the Defense Strength of the item.
   * */
  int getDefenseStrength();

  /**
   * Tells whether the item is a combination item or not.
   * */
  boolean isCombined();


  /**
   * Combine two items of same type into one by combining the powers and name.
   * */
  Item getCombinedItem(Item item) throws IllegalArgumentException;

}
