import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import bird.Bird;
import bird.BirdType;
import bird.FlightlessBird;
import bird.FoodItem;
import bird.Owl;
import bird.Parrot;
import bird.Pigeon;
import bird.PreyBird;
import bird.WaterFowl;
import conservatory.Conservatory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Conservatory and Aviary related tests.
 * */
public class ConservatoryTest {
  private Conservatory conservatory;
  java.io.ByteArrayOutputStream out;
  private FoodItem[] foodItems;

  @Before
  public void setup() {
    conservatory = new Conservatory();
    foodItems = new FoodItem[]{FoodItem.SEEDS, FoodItem.SMALL_MAMMALS};
    out = new java.io.ByteArrayOutputStream();
    System.setOut(new java.io.PrintStream(out));
  }

  @Test
  public void testAddAviaryToConservatory() throws IllegalStateException {
    conservatory.addAviaryToConservatory();
    assertEquals(1, conservatory.aviaries.size());
  }

  @Test(expected = IllegalStateException.class)
  public void testDisallowAddAviaryToConservatoryWhenSizeExceedsLimit()
          throws IllegalStateException {
    for (int i = 0; i < 20; i++) {
      conservatory.addAviaryToConservatory();
    }
    conservatory.addAviaryToConservatory();
  }

  /**
   * Checking assignment of bird to aviary is done successfully by checking the size
   * of the bird list for that particular aviary.
   * */
  @Test
  public void testAssignBirdToAviarySuccessfully() throws IllegalArgumentException {
    FoodItem[] foodItems = new FoodItem[]{FoodItem.SEEDS, FoodItem.SMALL_MAMMALS};
    Bird owl = new Owl(3, false, 1, Arrays.asList(foodItems) );
    conservatory.addAviaryToConservatory();
    conservatory.assignBirdToAviary(0, owl);
    assertEquals(1, conservatory.aviaries.get(0).getBirds().size()) ;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdToAviaryDisallowsNegativeAviaryIndex() {
    Bird owl = new Owl(3, false, 1, Arrays.asList(foodItems));
    conservatory.assignBirdToAviary(-1, owl);
  }

  /**
   * Aviary 2 is not created yet. Test tries to assign bird to aviary 2.
   * */
  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdToAviaryDisallowsIllegalAviaryIndex() {
    Bird owl = new Owl(3, false, 1, Arrays.asList(foodItems));
    conservatory.assignBirdToAviary(2, owl);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdToAviaryWhichIsAlreadyPresentInConservatory() {
    Bird owl = new Owl(3, false, 1, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.assignBirdToAviary(0, owl);
    conservatory.addAviaryToConservatory();
    conservatory.assignBirdToAviary(1, owl);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdToFullAviary() {
    Bird owl1 = new Owl(1, false, 1, Arrays.asList(foodItems));
    Bird owl2 = new Owl(2, false, 1, Arrays.asList(foodItems));
    Bird owl3 = new Owl(3, false, 1, Arrays.asList(foodItems));
    Bird owl4 = new Owl(4, false, 1, Arrays.asList(foodItems));
    Bird owl5 = new Owl(5, false, 1, Arrays.asList(foodItems));
    Bird owl6 = new Owl(5, false, 1, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.assignBirdToAviary(0, owl1);
    conservatory.assignBirdToAviary(0, owl2);
    conservatory.assignBirdToAviary(0, owl3);
    conservatory.assignBirdToAviary(0, owl4);
    conservatory.assignBirdToAviary(0, owl5);
    conservatory.assignBirdToAviary(0, owl6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignBirdToAviaryDisallowsExtinctBird() {
    Bird owl = new Owl(3, true, 2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.assignBirdToAviary(0, owl);
  }

  /**
   * Owl added first to Aviary 0.
   * Then Flightless Bird is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */
  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird flightlessBird = new FlightlessBird(5,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertFalse(conservatory.assignBirdToAviary(0, flightlessBird));
  }

  /**
   * Flightless Bird added first to Aviary 0.
   * Then Owl is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary2() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird flightlessBird = new FlightlessBird(5,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, flightlessBird));
    assertFalse(conservatory.assignBirdToAviary(0, owl));
  }

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary3() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird parrot = new Parrot(5,false,2, BirdType.ROSE_RING_PARAKEET,
                    102, "Hello", Arrays.asList(foodItems));
    Bird flightlessBird = new FlightlessBird(7,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertTrue(conservatory.assignBirdToAviary(0, parrot));
    assertFalse(conservatory.assignBirdToAviary(0, flightlessBird));
  }

  /**
   * Owl added first to Aviary 0.
   * Then Prey Bird is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */
  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary4() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird preyBird = new PreyBird(5,false,2,
            BirdType.EAGLE, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertFalse(conservatory.assignBirdToAviary(0, preyBird));
  }

  /**
   * Prey Bird added first to Aviary 0.
   * Then Owl is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary5() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird preyBird = new PreyBird(5,false,2,
            BirdType.EAGLE, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, preyBird));
    assertFalse(conservatory.assignBirdToAviary(0, owl));
  }

  /**
   * Owl added first to Aviary 0.
   * Then Water Fowl is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */
  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary6() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird waterFowl = new WaterFowl(1,false,2,
            BirdType.DUCK, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertFalse(conservatory.assignBirdToAviary(0, waterFowl));
  }

  /**
   * Water Fowl added first to Aviary 0.
   * Then Owl is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary7() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird waterFowl = new WaterFowl(1,false,2,
            BirdType.DUCK, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, waterFowl));
    assertFalse(conservatory.assignBirdToAviary(0, owl));
  }

  /**
   * Water Fowl added first to Aviary 0.
   * Then Prey Bird is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary8() {
    Bird preyBird = new PreyBird(5,false,2,
            BirdType.EAGLE, Arrays.asList(foodItems));
    Bird waterFowl = new WaterFowl(1,false,2,
            BirdType.DUCK, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, waterFowl));
    assertFalse(conservatory.assignBirdToAviary(0, preyBird));
  }


  /**
   * Flightless Bird added first to Aviary 0.
   * Then Prey Bird is being added to same aviary.
   * This should not be allowed. This test checks that.
   * */

  @Test
  public void testAssignIncompatibleBirdTypesInSameAviary9() {
    Bird preyBird = new PreyBird(5,false,2,
            BirdType.EAGLE, Arrays.asList(foodItems));
    Bird flightlessBird = new FlightlessBird(7,false,2,
            BirdType.EMU, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, flightlessBird));
    assertFalse(conservatory.assignBirdToAviary(0, preyBird));
  }


  @Test
  public void testAssignCompatibleBirdTypesInSameAviary() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    Bird parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            102, "Hello", Arrays.asList(foodItems));
    Bird pigeon = new Pigeon(7,false,2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertTrue(conservatory.assignBirdToAviary(0, parrot));
    assertTrue(conservatory.assignBirdToAviary(0, pigeon));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLookUpBirdDisallowsNegativeBirdID() {
    conservatory.lookUpBird(-1);
  }

  @Test
  public void testLookUpBird() {
    Bird owl = new Owl(3, false, 2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl));
    assertTrue(conservatory.lookUpBird(3));
    assertEquals("Bird 3 added successfully to Aviary 0\r\n"
            + "Bird 3 present in Aviary 0\r\n", out.toString());
  }

  @Test(expected = IllegalCallerException.class)
  public void testShowBirdIndexThrowsExceptionWhenNoAviariesHaveBeenCreated() {
    conservatory.showBirdIndex();
  }

  @Test
  public void testShowBirdIndex() {
    Bird owl2 = new Owl(2, false, 1, Arrays.asList(foodItems));
    Bird owl3 = new Owl(3, false, 1,Arrays.asList(foodItems));
    Bird parrot = new Parrot(5,false,2, BirdType.GRAY_PARROT,
            102, "Hello", Arrays.asList(foodItems));
    Bird pigeon = new Pigeon(7,false,2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.addAviaryToConservatory();
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl2));
    assertTrue(conservatory.assignBirdToAviary(1, owl3));
    assertTrue(conservatory.assignBirdToAviary(1, parrot));
    assertTrue(conservatory.assignBirdToAviary(2, pigeon));
    conservatory.showBirdIndex();
    assertEquals("Bird 2 added successfully to Aviary 0\r\n"
            + "Bird 3 added successfully to Aviary 1\r\n"
            + "Bird 5 added successfully to Aviary 1\r\n"
            + "Bird 7 added successfully to Aviary 2\r\n"
            + "\n" +  " Displaying Bird Index sorted by the category of bird. \r\n"
            + "Bird Category: bird.Owl\t Bird ID:2\t Aviary ID:0\r\n"
            + "Bird Category: bird.Owl\t Bird ID:3\t Aviary ID:1\r\n"
            + "Bird Category: bird.Parrot\t Bird ID:5\t Aviary ID:1\r\n"
            + "Bird Category: bird.Pigeon\t Bird ID:7\t Aviary ID:2\r\n", out.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowAviaryDescriptionThrowsExceptionForNegativeAviaryID() {
    conservatory.showAviaryDescription(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShowAviaryDescriptionDisallowsInvalidAviaryID() {
    conservatory.addAviaryToConservatory();
    conservatory.showAviaryDescription(2);
  }

  @Test
  public void testShowAviaryDescription() {
    Bird owl2 = new Owl(2, false, 1, Arrays.asList(foodItems));
    Bird owl3 = new Owl(3, false, 1, Arrays.asList(foodItems));
    Bird pigeon = new Pigeon(7,false,2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl2));
    assertTrue(conservatory.assignBirdToAviary(0, owl3));
    assertTrue(conservatory.assignBirdToAviary(0, pigeon));
    conservatory.showAviaryDescription(0);
    assertTrue(out.toString().startsWith("Bird 2 added successfully"));
  }

  @Test(expected = IllegalCallerException.class)
  public void testShowAviaryDirectoryThrowsExceptionWhenNoAviariesHaveBeenCreated() {
    conservatory.showAviaryDirectory();
  }

  @Test
  public void testShowAviaryDirectory() {
    Bird owl2 = new Owl(2, false, 1, Arrays.asList(foodItems));
    Bird owl3 = new Owl(3, false, 1, Arrays.asList(foodItems));
    Bird pigeon = new Pigeon(7,false,2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl2));
    assertTrue(conservatory.assignBirdToAviary(0, owl3));
    assertTrue(conservatory.assignBirdToAviary(1, pigeon));
    conservatory.showAviaryDirectory();
    assertEquals("Bird 2 added successfully to Aviary 0\r\n"
            + "Bird 3 added successfully to Aviary 0\r\n"
            + "Bird 7 added successfully to Aviary 1\r\n"
            + "\n" +  " Displaying Aviary Directory:\r\n"
            + "Aviary index: 0\t Bird ID: 2\t Bird Category: class bird.Owl\r\n"
            + "Aviary index: 0\t Bird ID: 3\t Bird Category: class bird.Owl\r\n"
            + "Aviary index: 1\t Bird ID: 7\t Bird Category: class bird.Pigeon\r\n",
            out.toString());
  }

  @Test
  public void testDisplayFoodNeeds() {
    Bird owl2 = new Owl(2, false, 1, Arrays.asList(foodItems));
    Bird owl3 = new Owl(3, false, 1, Arrays.asList(foodItems));
    Bird pigeon = new Pigeon(7,false,2, Arrays.asList(foodItems));
    conservatory.addAviaryToConservatory();
    conservatory.addAviaryToConservatory();
    assertTrue(conservatory.assignBirdToAviary(0, owl2));
    assertTrue(conservatory.assignBirdToAviary(0, owl3));
    assertTrue(conservatory.assignBirdToAviary(1, pigeon));
    conservatory.displayFoodNeeds();
    assertTrue( out.toString().startsWith("Bird 2 added successfully"));
  }
}
