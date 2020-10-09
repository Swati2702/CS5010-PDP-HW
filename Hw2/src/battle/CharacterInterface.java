package battle;

import item.Item;

import java.util.List;


/**
 * This interface defines a Character in the game and its associated methods.
 * */

public interface CharacterInterface {


  /** Getter for defaultAttackPower.
   * @return defaultAttackPower
   * */
  int getDefaultAttackPower();


  /** Getter for defaultDefenseStrength.
   * @return defaultAttackPower
   * */
  int getDefaultDefenseStrength();

  /** Getter for characterID.
   * @return characterID
   * */
  int getCharacterID();

  /** Getter for total AttackPower - defaultAttackPower plus attack power of all items assigned.
   * @return total AttackPower
   * */
  int getTotalAttackPower();

  /** Getter for total DefenseStrength - defaultDefenseStrength plus
   * DefenseStrength of all items assigned.
   * @return total DefenseStrength
   * */
  int getTotalDefensePower();

  /** Getter for the list of gears worn by the character.
   * @return itemList
   * */
  List<Item> getCharacterItemList();

  /** Adds item to  the list of gears worn by the character.
   * @return Character with item added
   * @throws IllegalArgumentException if cannot add that item to character
   * */
  Character addItem(Item item) throws IllegalArgumentException;

  /** Combines item with already worn item by the character.
   * @return Character with item combined
   * @throws IllegalArgumentException if cannot add that item to character
   * */
  Character addItemAndCombine(Item newItem, Item oldItem) throws IllegalArgumentException;

  /** Removes item to  the list of gears worn by the character.
   * @return Character with item removed
   * @throws IllegalArgumentException if item not worn by character
   * */
  Character removeItem(Item item) throws  IllegalArgumentException;



}
