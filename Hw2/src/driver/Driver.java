package driver;

import battle.Battle;
import battle.Character;
import item.FootWear;
import item.HandGear;
import item.HeadGear;
import item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a battle.
 * You can choose two characters, dress them from the list of items available for them
 * and wage a battle between them.
 */
public class Driver {

  /**
   * Main function  - Battle. We create two characters here, give them a list of gears/items from
   * which they can be dressed. Each player takes turn selecting the item and selects  the item
   * according to the following criteria - Which items can be combined, Which items have the highest
   * strength in each category, Higher attack strength is chosen over higher defense strength.
   * Finally the winner of the battle is chosen.  The winner is determined by who has less damage
   * after a battle. Damage is calculated by one's opponent's attack power minus that character's
   * defense points.
   */
  public static void main(String... args) {
    Driver battle = new Driver();
    battle.run();

  }

  void run() {

    //create characters
    Character character1 = new Character(1, 20, 10);
    Character character2 = new Character(2, 15, 5);
    System.out.println(character1.toString());
    System.out.println(character2.toString());

    List<Item> itemList = getListOfItems();

    System.out.println("List of items to pick from:");
    for (Item item : itemList) {
      System.out.println(item.toString());
    }

    Battle battle = new Battle(character1, character2, itemList);

    //dress characters
    System.out.println("Dressing characters........... ");
    battle.dressCharacters();


    System.out.println("Character 1 assigned item list: ");
    for (Item item : character1.getCharacterItemList())
      System.out.println(item);

    System.out.println("Character 2 assigned item list: ");
    for (Item item : character2.getCharacterItemList())
      System.out.println(item);

    System.out.println("Winner is: Character ID -  " +  battle.getWinner().getCharacterID());

  }

  List<Item> getListOfItems() {

    //creates list of 10 items
    List<Item> itemList = new ArrayList<>();
    itemList.add(new HeadGear("Sleepy", "Hat", 10));
    itemList.add(new HeadGear("Hard", "Helmet", 2));
    itemList.add(new HeadGear("Ferocious", "Visor", 18));
    itemList.add(new HandGear("Lovely", "Gloves", 5));
    itemList.add(new HandGear("Seething", "Sword", 7));
    itemList.add(new HandGear("Scrapy", "Shield", 9));
    itemList.add(new FootWear("Bulky", "Boots", 10, "Defense"));
    itemList.add(new FootWear("Slippery", "Sneakers", 17, "Attack"));
    itemList.add(new FootWear("Healthy", "Hoverboard", 17, "Attack"));
    itemList.add(new FootWear("Happy", "Hoverboard", 21, "Defense"));

    return itemList;
  }
}
