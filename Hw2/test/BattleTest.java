import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import battle.Battle;
import battle.Character;
import item.FootWear;
import item.HandGear;
import item.HeadGear;
import item.Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Tests for Battle class.
 */

public class BattleTest {

  Character character1;
  Character character2;
  List<Item> itemList;
  Battle battle;
  java.io.ByteArrayOutputStream out;

  @Before
  public void setCharacters() {
    character1 = new Character(1, 20, 10);
    character2 = new Character(2, 15, 5);
    itemList = new ArrayList<>();
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
    battle  = new Battle(character1, character2, itemList);
    out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
  }

  @Test
  public void testConstructorSuccess() {
    battle = new Battle(character1, character2, itemList);

    assertNotNull(battle.toString());
  }

  @Test
  public void testBattleWinner() {
    assertEquals(character1.toString(), battle.getWinner().toString());
  }

  @Test
  public void testGetCharacter1() {
    assertEquals(character1.toString(), battle.getCharacter1().toString());
  }

  @Test
  public void testGetCharacter2() {
    assertEquals(character2.toString(), battle.getCharacter2().toString());
  }

  @Test
  public void testDressCharacters() {
    assertNotNull(character1.getCharacterItemList());
    assertNotNull(character2.getCharacterItemList());
  }





}
