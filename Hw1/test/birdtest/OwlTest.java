package birdtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import bird.Bird;
import bird.BirdType;
import bird.Owl;
import bird.FoodItem;
import bird.Parrot;

/**
 * Owl Bird Category Tests.
 * */
public class OwlTest {
  private Bird owl;
  private FoodItem[] foodItems;

  @Before
  public void setOwl() {
    foodItems = new FoodItem[]{FoodItem.NUTS, FoodItem.VEGETATION};
    owl = new Owl(1,false,2, Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    foodItems = new FoodItem[]{FoodItem.FISH, FoodItem.LARVAE};
    owl = new Owl(5,false,2, Arrays.asList(foodItems));
    assertEquals("Owl ( ID: 5, Number of wings = 2, Extinct = false )",
            owl.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    owl = new Owl(5,false,-2, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    owl = new Owl(-1,false,2, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalCallerException.class)
  public void testGetBirdType() {
    owl.getBirdType();
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[NUTS, VEGETATION]",
            owl.getFoodItemList().toString());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("Distinguished by the facial disks that frame the eyes and bill.",
            owl.getCharacteristics());
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    owl.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    owl.getFavSaying();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetWaterBodies() {
    owl.getWaterBodies();
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird owl2 = new Owl(6,false,1, Arrays.asList(foodItems));
    assertEquals(0, owl.compareTo(owl2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, owl.compareTo(parrot));
  }

}

