import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *Tests for items/gears.
 */

public class ItemTest {

  private Item headGear;
  private Item handGear;
  private Item footWear;

  @Before
  public void setItems() {
    headGear = new HeadGear("Sleepy", "Hat", 10);
    handGear = new HandGear("Lovely", "Gloves", 5);
    footWear = new FootWear("Slippery", "Sandals", 10, "Attack");
  }

  /*  ---------Beginning tests for Head gear----------------------- */
  @Test
  public void testConstructorHeadGearSuccess() {
    headGear = new HeadGear("Sleepy", "Hat", 10);
    assertEquals(" HeadGear : \t( Name: Sleepy Hat, Attack power = 0, "
            + "Defense Strength =  10 )", headGear.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsNullAdjName() {
    headGear = new HeadGear("", "Hat", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsNullNounName() {
    headGear = new HeadGear("Spearheaded", "", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsBlankAdjName() {
    headGear = new HeadGear("    ", "Hat", 10);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsBlankNounName() {
    headGear = new HeadGear("Spearheaded", "    ", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsOtherCharactersInAdjName() {
    headGear = new HeadGear("Spearheaded1", "Hat", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsOtherCharactersInNounName() {
    headGear = new HeadGear("Spearheaded", "H*at", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHeadGearDisallowsNegativeDefenseStrength() {
    headGear = new HeadGear("Spearheaded", "Hat", -10);
  }

  @Test
  public void testGetNameHeadGear() {
    assertEquals("Sleepy Hat", headGear.getName());
  }

  @Test
  public void testGetAttackPowerHeadGear() {
    assertEquals(0, headGear.getAttackPower());
  }

  @Test
  public void testGetDefenseStrengthHeadGear() {
    assertEquals(10, headGear.getDefenseStrength());
  }

  @Test
  public void testGetIsCombined() {
    assertEquals(false, headGear.isCombined());
  }


  /*  ---------Beginning tests for Hand gear----------------------- */
  @Test
  public void testConstructorHandGearSuccess() {
    handGear = new HandGear("Sharp", "Shield", 6);
    assertEquals(" HandGear : \t( Name: Sharp Shield, Attack power = 6, "
            + "Defense Strength =  0 )", handGear.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsNullAdjName() {
    handGear = new HandGear("", "Shield", 6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsNullNounName() {
    handGear = new HandGear("Spearheaded", "", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsBlankAdjName() {
    handGear = new HandGear("    ", "Shield", 10);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsBlankNounName() {
    handGear = new HandGear("Spearheaded", "    ", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsOtherCharactersInAdjName() {
    handGear = new HandGear("Spearheaded1", "Shield", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsOtherCharactersInNounName() {
    handGear = new HandGear("Spearheaded", "Shield*", 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorHandGearDisallowsNegativeDefenseStrength() {
    handGear = new HandGear("Spearheaded", "Shield", -10);
  }

  @Test
  public void testGetNameHandGear() {
    assertEquals("Lovely Gloves", handGear.getName());
  }

  @Test
  public void testGetAttackPowerHandGear() {
    assertEquals(5, handGear.getAttackPower());
  }

  @Test
  public void testGetDefenseStrengthHandGear() {
    assertEquals(0, handGear.getDefenseStrength());
  }

  @Test
  public void testGetIsCombinedHandGear() {
    assertEquals(false, handGear.isCombined());
  }




  /*  ---------Beginning tests for Foot wear----------------------- */
  @Test
  public void testConstructorFootWearSuccess() {
    footWear = new FootWear("Seething", "Sandals", 8, "Attack");
    assertEquals(" FootWear : \t( Name: Seething Sandals, Attack power = 8, "
            + "Defense Strength =  0 )", footWear.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsNullAdjName() {
    footWear = new FootWear("", "Boots", 6, "attack");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsNullNounName() {
    footWear = new FootWear("Biting", "", 10, "defense");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsBlankAdjName() {
    footWear = new FootWear("    ", "Boots", 10, "attack");
  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsBlankNounName() {
    footWear = new FootWear("Biting", "    ", 10, "attack");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsOtherCharactersInAdjName() {
    footWear = new FootWear("Bitin22g", "Boots", 10, "attack");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsOtherCharactersInNounName() {
    footWear = new FootWear("Biting", "Boot1s*", 10, "attack");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsNegativeDefenseStrength() {
    footWear = new FootWear("Biting", "Boots", -10, "attack");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorFootWearDisallowsIllegalLastOption() {
    footWear = new FootWear("Biting", "Boots", 10, "kk");
  }


  @Test
  public void testGetNameFootWear() {
    assertEquals("Slippery Sandals", footWear.getName());
  }

  @Test
  public void testGetAttackPowerFootWear() {
    assertEquals(10, footWear.getAttackPower());
  }

  @Test
  public void testGetDefenseStrengthFootWear() {
    assertEquals(0, footWear.getDefenseStrength());
  }


  @Test
  public void testGetIsCombinedFootWear() {
    assertEquals(false, footWear.isCombined());
  }

  /* ------------------ test combine items---------------- */

  @Test
  public void testCombineHeadGearSuccess() {
    Item headGear2 = new HeadGear("Hot", "Helmet", 10);
    Item item = headGear2.getCombinedItem(headGear);
    assertEquals(" HeadGear : \t( Name: Hot, Sleepy Hat, Attack power = 0, "
            +"Defense Strength =  20 )", item.toString());
  }

  @Test
  public void testCombineHandGearSuccess() {
    Item handGear2 = new HandGear("Spicy", "Shield", 2);
    assertEquals(" HandGear : \t( Name: Lovely, Spicy Shield, Attack power = 7, "
            +"Defense Strength =  0 )", handGear.getCombinedItem(handGear2).toString());
  }

  @Test
  public void testCombineFootWearSuccess() {
    Item footWear2 = new FootWear("Sexy", "Shoes", 2, "defense");
    assertEquals(" FootWear : \t( Name: Slippery, Sexy Shoes, Attack power = 10, "
            +"Defense Strength =  2 )", footWear.getCombinedItem(footWear2).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineHandHeadFails() {
    handGear.getCombinedItem(headGear);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineHandHeadFails2() {
    headGear.getCombinedItem(handGear);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineHandFootFails() {
    handGear.getCombinedItem(footWear);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineHandFootFails2() {
    footWear.getCombinedItem(handGear);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCombineHeadFootFails() {
    headGear.getCombinedItem(footWear);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCombineHeadFootFails2() {
    footWear.getCombinedItem(headGear);
  }

  @Test
  public void testGetIsCombinedForCombinedItem() {
    Item headGear2 = new HeadGear("Hot", "Helmet", 10);
    Item item = headGear2.getCombinedItem(headGear);
    assertEquals(true, item.isCombined());
  }

}
