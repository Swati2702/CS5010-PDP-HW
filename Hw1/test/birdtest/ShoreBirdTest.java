package birdtest;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import bird.Bird;
import bird.BirdType;
import bird.FoodItem;
import bird.ShoreBird;
import bird.Parrot;

/**
 * Shore Bird Category Tests.
 * */
public class ShoreBirdTest {
  private Bird shoreBird;
  private FoodItem[] foodItems;

  @Before
  public void setShoreBird() {
    foodItems = new FoodItem[]{FoodItem.FRUIT, FoodItem.AQUATIC_INVERTEBRATES};
    shoreBird = new ShoreBird(1,false,2,
            BirdType.HORNED_PUFFIN, Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    shoreBird = new ShoreBird(5,false,2,
            BirdType.AFRICAN_JACANA, Arrays.asList(foodItems));
    assertEquals("Shore Bird ( ID: 5, Number of wings = 2, "
            + "Extinct = false, Bird Type = AFRICAN_JACANA , "
            + "Water bodies = [FRESHWATER, SALTWATER, OCEAN, WETLANDS] )", shoreBird.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    shoreBird = new ShoreBird(5,false,-2,
            BirdType.EMU, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdType() {
    shoreBird = new ShoreBird(5,false,2,
            BirdType.ROSE_RING_PARAKEET, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    shoreBird = new ShoreBird(-1,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
  }


  @Test
  public void testGetBirdType() {
    assertEquals(BirdType.HORNED_PUFFIN, shoreBird.getBirdType());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("They live near water sources including wetlands, "
            + "freshwater and saltwater shorelands, even the ocean",
            shoreBird.getCharacteristics());
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[FRUIT, AQUATIC_INVERTEBRATES]",
            shoreBird.getFoodItemList().toString());
  }


  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    shoreBird.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    shoreBird.getFavSaying();
  }

  @Test
  public void testExceptionThrownForCallingGetWaterBodies() {

    assertEquals("[FRESHWATER, SALTWATER, OCEAN, WETLANDS]",
            shoreBird.getWaterBodies().toString());
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird shoreBird2 = new ShoreBird(6,false,1,
            BirdType.HORNED_PUFFIN, Arrays.asList(foodItems));
    assertEquals(0, shoreBird.compareTo(shoreBird2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, shoreBird.compareTo(parrot));
  }

}

