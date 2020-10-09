package battle;

import item.FootWear;
import item.HandGear;
import item.HeadGear;
import item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Represents a character of the game.
 * Characters go into battle with some degree of attack and defense capabilities
 * (represented as numerical values). These values can be modified by “wearing” different
 * articles of clothing to either increase performance (attack)
 * or minimize damage inflicted by other characters (defense).
 * */
public class Character implements CharacterInterface {

  private final int characterID;
  private final int defaultAttackPower;
  private final int defaultDefenseStrength;
  private final List<Item> itemList;


  /**
   * Constructor for the class.
   *
   * @param characterID ID to recognize a character
   * @param defaultAttackPower default attack power of the character
   * @param defaultDefenseStrength default defense strength of the character
   * @throws IllegalArgumentException if attack power or defense strength is negative
   * */
  public Character(int characterID, int defaultAttackPower, int defaultDefenseStrength)
      throws IllegalArgumentException {

    if (defaultAttackPower < 0  || defaultDefenseStrength < 0 || characterID < 0) {
      throw new IllegalArgumentException("Attack power, defense strength"
              + " or character ID cannot be negative.");
    }
    this.characterID = characterID;
    this.defaultAttackPower = defaultAttackPower;
    this.defaultDefenseStrength = defaultDefenseStrength;
    this.itemList = new ArrayList<>();
  }

  /** Getter for defaultAttackPower.
   * @return defaultAttackPower
   * */
  @Override
  public int getDefaultAttackPower() {
    return defaultAttackPower;
  }

  /** Getter for defaultDefenseStrength.
   * @return defaultAttackPower
   * */
  @Override
  public int getDefaultDefenseStrength() {
    return defaultDefenseStrength;
  }

  /** Getter for characterID.
   * @return characterID
   * */
  @Override
  public int getCharacterID() {
    return characterID;
  }

  @Override
  public int getTotalAttackPower() {
    int total = defaultAttackPower;
    for (Item item: itemList) {
      total += item.getAttackPower();
    }
    return total;
  }

  @Override
  public int getTotalDefensePower() {
    int total = defaultDefenseStrength;
    for (Item item: itemList) {
      total += item.getDefenseStrength();
    }
    return total;
  }

  /** Getter for the list of gears worn by the character.
   * @return itemList
   * */
  @Override
  public List<Item> getCharacterItemList() {
    return itemList;
  }

  private int countItemsOfType(Class<? extends Item> itemType) {
    int count = 0;
    for (Item item : itemList) {

      if (itemType.equals(item.getClass())) {
        count++;
      }
    }
    return count;
  }


  @Override
  public Character addItem(Item item) throws IllegalArgumentException {
    if (( countItemsOfType(HeadGear.class) >= 1 && item instanceof HeadGear )
            || (countItemsOfType(HandGear.class) >= 2 && item instanceof HandGear)
            || (countItemsOfType(FootWear.class) >= 2 && item instanceof FootWear) ) {
      throw new IllegalArgumentException("Character does not have space for this item");
    }
    this.itemList.add(item);
    return this;

  }

  @Override
  public Character addItemAndCombine(Item newItem, Item oldItem) throws IllegalArgumentException {
    if (oldItem.isCombined()) {
      throw new IllegalArgumentException("Cannot combine with a combined item.");
    }
    this.itemList.remove(oldItem);
    this.itemList.add(oldItem.getCombinedItem(newItem));
    return this;
  }

  /**
   * Package - private class - checks whether character has any item that he can combine the
   * provided item with.
   * */
  Item canCombineItem(Item newItem) {
    for (Item item : itemList) {
      try {
        item.getCombinedItem(newItem);
        return item;
      } catch (Exception ignored) {
      }
    }
    return null;
  }



  @Override
  public Character removeItem(Item item) throws IllegalArgumentException {
    if (!this.itemList.contains(item)) {
      throw new IllegalArgumentException("Item not worn by the character.");
    }
    this.itemList.remove(item);
    return this;
  }

  @Override
  public String toString() {
    return String.format(" Character ID: %d,  Attack power = %d, "
                    + "Defense Strength =  %d",
            this.getCharacterID(), this.getDefaultAttackPower(), this.getDefaultDefenseStrength());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Character)) {
      return false;
    }

    Character that = (Character) o;

    return (this.getCharacterID() == that.getCharacterID()) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(characterID, defaultAttackPower, defaultDefenseStrength, itemList);
  }
}

