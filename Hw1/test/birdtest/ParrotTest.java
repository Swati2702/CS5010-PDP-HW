package birdtest;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import bird.Bird;
import bird.BirdType;
import bird.FlightlessBird;
import bird.FoodItem;
import bird.Parrot;

/**
 * Parrot Bird Category Tests.
 * */

public class ParrotTest {
  private Bird parrot;
  private FoodItem[] foodItems;

  @Before
  public void setParrot() {
    foodItems = new FoodItem[]{FoodItem.NUTS, FoodItem.VEGETATION};
    parrot = new Parrot(1,false,2, BirdType.ROSE_RING_PARAKEET,
            100, "OMG", Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            102, "Hello", Arrays.asList(foodItems));
    assertEquals("Parrot ( ID: 5, Number of wings = 2, Extinct = false, "
            + "Bird Type = GRAY_PARROT, Number of words in vocabulary = 102,"
            + " Favorite saying = Hello )", parrot.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    parrot = new Parrot(5,false,-2, BirdType.GRAY_PARROT,
            102, "Hello", Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeNoOfWords() {
    parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            -102, "Hello", Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNullSaying() {
    parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            -102, null, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsEmptySaying() {
    parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            -102, "", Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdType() {
    parrot = new Parrot(5,false,2, BirdType.EMU,
            102, "Hello", Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    parrot = new Parrot(-5,false,2, BirdType.GRAY_PARROT,
            102, "Hello", Arrays.asList(foodItems));
  }

  @Test
  public void testGetBirdType() {
    assertEquals(BirdType.ROSE_RING_PARAKEET, parrot.getBirdType());
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[NUTS, VEGETATION]",
            parrot.getFoodItemList().toString());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("Short, curved beak and are known for their "
                    + "intelligence and ability to mimic sounds.",
            parrot.getCharacteristics());
  }

  @Test
  public void testGetNoOfWordsInVocab() {
    assertEquals(100, parrot.getNoOfWordsInVocab());
  }

  @Test
  public void testGetFavSaying() {
    assertEquals("OMG",parrot.getFavSaying());
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetWaterBodies() {
    parrot.getWaterBodies();
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird parrot2 = new Parrot(5,false,0, BirdType.SULFUR_CRESTED_COCATOO,
            102, "Hey Babe", Arrays.asList(foodItems));
    assertEquals(0, parrot.compareTo(parrot2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird flightlessBird = new FlightlessBird(6,false,1,
            BirdType.MOA, Arrays.asList(foodItems));
    assertNotEquals(0, parrot.compareTo(flightlessBird));
  }

}

