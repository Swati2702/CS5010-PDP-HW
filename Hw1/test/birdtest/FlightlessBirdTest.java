package birdtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;


import bird.Bird;
import bird.BirdType;
import bird.FlightlessBird;
import bird.FoodItem;
import bird.Parrot;

/**
 * Flightless Bird Category Tests.
 * */
public class FlightlessBirdTest {
  private Bird flightlessBird;
  private FoodItem[] foodItems;

  @Before
  public void setFlightlessBird() {
    foodItems = new FoodItem[]{FoodItem.AQUATIC_INVERTEBRATES, FoodItem.BERRIES};
    flightlessBird = new FlightlessBird(1,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {

    foodItems = new FoodItem[]{FoodItem.AQUATIC_INVERTEBRATES, FoodItem.BUDS};
    flightlessBird = new FlightlessBird(5,false,2, BirdType.EMU,
            Arrays.asList(foodItems));
    assertEquals("Flightless Bird ( ID: 5, Number of wings = 2, "
            + "Extinct = false, Bird Type = EMU )", flightlessBird.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    flightlessBird = new FlightlessBird(5,false,-2,
            BirdType.EMU, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdType() {
    flightlessBird = new FlightlessBird(5,false,2,
            BirdType.ROSE_RING_PARAKEET, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    flightlessBird = new FlightlessBird(-1,false,2, BirdType.EMU,
            Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsInvalidNumberOfFoodItems() {
    foodItems = new FoodItem[]{FoodItem.AQUATIC_INVERTEBRATES};
    flightlessBird = new FlightlessBird(1,false,2, BirdType.EMU,
            Arrays.asList(foodItems));
  }

  @Test
  public void testGetBirdType() {
    assertEquals(BirdType.EMU, flightlessBird.getBirdType());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("Live on the ground and have no (or undeveloped) wings",
            flightlessBird.getCharacteristics());
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[AQUATIC_INVERTEBRATES, BERRIES]",
            flightlessBird.getFoodItemList().toString());
  }


  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    flightlessBird.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    flightlessBird.getFavSaying();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetWaterBodies() {
    flightlessBird.getWaterBodies();
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird flightlessBird2 = new FlightlessBird(6,false,1,
            BirdType.MOA, Arrays.asList(foodItems));
    assertEquals(0, flightlessBird.compareTo(flightlessBird2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, flightlessBird.compareTo(parrot));
  }


}

