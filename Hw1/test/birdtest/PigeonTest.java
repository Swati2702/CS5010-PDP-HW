package birdtest;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import bird.Bird;
import bird.BirdType;
import bird.FoodItem;
import bird.Pigeon;
import bird.Parrot;

/**
 * Pigeon Bird Category Tests.
 * */

public class PigeonTest {
  private Bird pigeon;
  private FoodItem[] foodItems;

  @Before
  public void setPigeon() {
    foodItems = new FoodItem[]{FoodItem.EGGS, FoodItem.AQUATIC_INVERTEBRATES};
    pigeon = new Pigeon(1,false,2, Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    pigeon = new Pigeon(5,false,2, Arrays.asList(foodItems));
    assertEquals("Pigeon ( ID: 5, Number of wings = 2, Extinct = false )",
            pigeon.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    pigeon = new Pigeon(5,false,-2, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    pigeon = new Pigeon(-1,false,2, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalCallerException.class)
  public void testGetBirdType() {
    pigeon.getBirdType();
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[EGGS, AQUATIC_INVERTEBRATES]",
            pigeon.getFoodItemList().toString());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("Also known as doves and are known for feeding their young  'bird milk' "
                    + "very similar to the milk of mammals. They are found all over the world.",
            pigeon.getCharacteristics());
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    pigeon.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    pigeon.getFavSaying();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetWaterBodies() {
    pigeon.getWaterBodies();
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird pigeon2 = new Pigeon(6,false,1, Arrays.asList(foodItems));
    assertEquals(0, pigeon.compareTo(pigeon2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, pigeon.compareTo(parrot));
  }

}

