import org.junit.Before;
import org.junit.Test;

import item.FootWear;
import item.HandGear;
import item.HeadGear;
import item.Item;
import battle.Character;

import static org.junit.Assert.assertEquals;

/**
 *Tests for Character.
 */

public class CharacterTest {
  private Character character;

  @Before
  public void setCharacter() {
    character = new Character(1, 20, 10 );
  }

  @Test
  public void testConstructorSuccess() {
    character = new Character(2, 20, 15 );
    assertEquals(" Character ID: 2,  Attack power = 20, Defense Strength =  15",
            character.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeCharacterID() {
    character = new Character(-2, 20, 15 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeAttackPower() {
    character = new Character(2, -20, 15 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeDefenseStrength() {
    character = new Character(2, 20, -15 );
  }

  @Test
  public void testEqualCharacter() {
    character = new Character(2, 20, 15 );
    Character character1 = new Character(2, 20, 15 );
    assertEquals(character, character1);
  }

  @Test
  public void testGetCharacterID() {
    assertEquals(1, character.getCharacterID());
  }

  @Test
  public void testGetAttackPower() {
    assertEquals(20, character.getDefaultAttackPower());
  }

  @Test
  public void testGetDefenseStrength() {
    assertEquals(10, character.getDefaultDefenseStrength());
  }

  @Test
  public void testGetItemList() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);
    character.addItem(headGear);
    assertEquals(1, character.getCharacterItemList().size());
  }

  @Test
  public void testRemoveItemList() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);
    character.addItem(headGear);
    assertEquals(1, character.getCharacterItemList().size());
    character.removeItem(headGear);
    assertEquals(0, character.getCharacterItemList().size());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testRemoveItemListFailsIfItemNotPresent() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);;
    character.removeItem(headGear);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemToListDisallowsMoreThanTwoHeadGears() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);
    Item headGear2 = new HeadGear("Sleepless", "Hat", 10);
    character.addItem(headGear);
    character.addItem(headGear2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemToListDisallowsMoreThanTwoHandGears() {
    Item handGear = new HandGear("Lovely", "Gloves", 5);
    Item handGear2 = new HandGear("Loving", "Gloves", 6);
    Item handGear3 = new HandGear("Loveless", "Gloves", 7);
    character.addItem(handGear);
    character.addItem(handGear2);
    character.addItem(handGear3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemToListDisallowsMoreThanTwoFootWears() {
    Item footWear = new FootWear("Slippery", "Sandals", 10, "Attack");
    Item footWear2 = new FootWear("Waterproof", "Sandals",
            10, "Defense");
    Item footWear3 = new FootWear("Slippery", "Boots", 20, "Attack");
    character.addItem(footWear);
    character.addItem(footWear2);
    character.addItem(footWear3);
  }

  @Test
  public void testAddItemAndCombineToList() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);
    Item headGear2 = new HeadGear("Sleepless", "Hat", 10);
    character.addItem(headGear);
    assertEquals(1, character.getCharacterItemList().size());
    character.addItemAndCombine(headGear2, headGear );
    assertEquals(1, character.getCharacterItemList().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddItemAndCombineFailsForCombinedItem() {
    Item headGear = new HeadGear("Sleepy", "Hat", 10);
    Item headGear2 = new HeadGear("Sleepless", "Hat", 10);
    Item headGear3 = new HeadGear("Hot", "Helmet", 10);
    Item item = headGear2.getCombinedItem(headGear);
    character.addItem(item);
    character.addItemAndCombine(headGear3, item);
  }






}