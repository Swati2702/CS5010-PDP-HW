package birdtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import bird.Bird;
import bird.BirdType;
import bird.PreyBird;
import bird.FoodItem;
import bird.Parrot;

/**
 * Prey Bird Category Tests.
 * */
public class PreyBirdTest {
  private Bird preyBird;
  private FoodItem[] foodItems;

  @Before
  public void setPreyBird() {
    foodItems = new FoodItem[]{FoodItem.EGGS, FoodItem.AQUATIC_INVERTEBRATES};
    preyBird = new PreyBird(1,false,2, BirdType.HAWK,
            Arrays.asList(foodItems));
  }

  @Test
  public void testConstructorSuccess() {
    preyBird = new PreyBird(5,false,2, BirdType.EAGLE,
            Arrays.asList(foodItems));
    assertEquals("Prey Bird ( ID: 5, Number of wings = 2, "
            + "Extinct = false, Bird Type = EAGLE )", preyBird.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsNegativeWings() {
    preyBird = new PreyBird(5,false,-2, BirdType.EMU,
            Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdType() {
    preyBird = new PreyBird(5,false,2,
            BirdType.ROSE_RING_PARAKEET, Arrays.asList(foodItems));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDisallowsIllegalBirdID() {
    preyBird = new PreyBird(-1,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
  }

  @Test
  public void testGetFoodItemList() {
    assertEquals("[EGGS, AQUATIC_INVERTEBRATES]",
            preyBird.getFoodItemList().toString());
  }

  @Test
  public void testGetBirdType() {
    assertEquals(BirdType.HAWK, preyBird.getBirdType());
  }

  @Test
  public void testGetCharacteristics() {
    assertEquals("Sharp, hooked beaks with visible nostrils",
            preyBird.getCharacteristics());
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetNoOfWordsInVocab() {
    preyBird.getNoOfWordsInVocab();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetFavSaying() {
    preyBird.getFavSaying();
  }

  @Test(expected = IllegalCallerException.class)
  public void testExceptionThrownForCallingGetWaterBodies() {
    preyBird.getWaterBodies();
  }

  @Test
  public void testCompareToWorksForSameCategory() {
    Bird preyBird2 = new PreyBird(6,false,1,
            BirdType.OSPREY, Arrays.asList(foodItems));
    assertEquals(0, preyBird.compareTo(preyBird2));
  }

  @Test
  public void testCompareToWorksForDifferentCategory() {
    Bird parrot = new Parrot(1, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
            100, "Wow", Arrays.asList(foodItems));
    assertNotEquals(0, preyBird.compareTo(parrot));
  }

}

