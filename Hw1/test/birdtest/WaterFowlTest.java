package birdtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import bird.Bird;
import bird.BirdType;
import bird.WaterFowl;
import bird.FoodItem;
import bird.Parrot;

/**
 * WaterFowl Bird Category Tests.
 * */
public class WaterFowlTest {
  private Bird waterFowl;
  private FoodItem[] foodItems;

  @Before
  public void setWaterFowl() {
    foodItems = new FoodItem[]{FoodItem.OTHER_BIRDS, FoodItem.INSECTS};
    waterFowl = new WaterFowl(1,false,2, BirdType.DUCK,
            Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    waterFowl = new WaterFowl(5,false,2, BirdType.GOOSE,
            Arrays.asList(foodItems));
    assertEquals("Water Fowl ( ID: 5, Number of wings = 2, "
            + "Extinct = false, Bird Type = GOOSE , "
            + "Water bodies = [FRESHWATER, SALTWATER] )", waterFowl.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    waterFowl = new WaterFowl(5,false,-2, BirdType.DUCK,
            Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdType() {
    waterFowl = new WaterFowl(5,false,2, BirdType.EMU,
            Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    waterFowl = new WaterFowl(-1,false,2, BirdType.SWAN,
            Arrays.asList(foodItems));
  }


  @Test
  public void testGetBirdType() {
    assertEquals(BirdType.DUCK, waterFowl.getBirdType());
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[OTHER_BIRDS, INSECTS]",
            waterFowl.getFoodItemList().toString());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("They live near live near water sources (fresh or salt)",
            waterFowl.getCharacteristics());
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    waterFowl.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    waterFowl.getFavSaying();
  }

  @Test
  public void testGetWaterBodies() {

    assertEquals("[FRESHWATER, SALTWATER]",
            waterFowl.getWaterBodies().toString());
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird waterFowl2 = new WaterFowl(6,false,1,
            BirdType.SWAN, Arrays.asList(foodItems));
    assertEquals(0, waterFowl.compareTo(waterFowl2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, waterFowl.compareTo(parrot));
  }

}

