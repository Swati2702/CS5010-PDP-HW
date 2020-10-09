package battle;

import item.Item;

import java.util.Collections;
import java.util.List;



/**
 * This class represents a battle.
 * You can choose two characters, dress them from the list of items available for them
 * and wage a battle between them.
 * */

public class Battle implements BattleInterface {

  private final Character character1;
  private final Character character2;
  private final List<Item> itemList;

  /**
   * Constructor for battle class.
   *
   * @param character1 : character 1 of the battle
   * @param character2 : character 2 of the battle
   * @throws IllegalArgumentException : if number of items is not equal to 10
   */
  public Battle(Character character1, Character character2, List<Item> itemList) {
    if (itemList.size() != 10) {
      throw new IllegalArgumentException(" Item list size should be 10.");
    }
    this.character1 = character1;
    this.character2 = character2;
    this.itemList = itemList;
  }

  @Override
  public Character getCharacter1() {
    return character1;
  }

  @Override
  public Character getCharacter2() {
    return character2;
  }

  @Override
  public void dressCharacters() {
    //sort based on the given criteria
    Collections.sort(itemList);
    double random = Math.random();
    int chosenCharacter;
    if (random < 0.5) {
      chosenCharacter = 0;
    }
    else {
      chosenCharacter = 1;
    }

    Character[] characters = new Character[2];
    characters[0] = character1;
    characters[1] = character2;

    int trials = 0;

    while (itemList.size() > 0 && trials < 30) {
      boolean combined = false;

      for (Item item : itemList) {
        Item toBeCombinedItem = characters[chosenCharacter].canCombineItem(item);
        if (null != toBeCombinedItem) {
          characters[chosenCharacter].addItemAndCombine(item, toBeCombinedItem);
          itemList.remove(item);
          combined = true;
          break;
        }
      }

      if (!combined) {
        for (Item item : itemList) {
          try {
            characters[chosenCharacter].addItem(item);
            itemList.remove(item);
            break;
          } catch (Exception ignored) {

          }
        }
      }
      chosenCharacter = 1 - chosenCharacter; //flip character turn
      trials++;
    }
  }


  @Override
  public Character getWinner() {

    int damage1 = character2.getTotalAttackPower() - character1.getTotalDefensePower();
    int damage2 = character1.getTotalAttackPower() - character2.getTotalDefensePower();
    return damage1 < damage2 ? character1 : character2;

  }

  @Override
  public String toString() {
    return String.format(" Character 1: %s,  Character 2: %s, Itemlist: %s ",
            character1.toString(), character2.toString(), itemList.toString() );
  }

}