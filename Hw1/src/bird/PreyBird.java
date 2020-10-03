package bird;

import java.util.EnumSet;
import java.util.List;

/**
 * PreyBird Bird Category.
 * */

public final class PreyBird extends AbstractBird {
  private static final String characteristics = "Sharp, hooked beaks with visible nostrils";
  private final BirdType birdType;
  private static final EnumSet<BirdType> PreyBirdTypes = EnumSet.of(BirdType.HAWK,
          BirdType.EAGLE, BirdType.OSPREY );

  /**
   * Constructs a Flightless bird.
   * @param birdID User should provide unique ID of the bird
   * @param extinct tells whether bird is extinct or not
   * @param noOfWings  the number of wings of the bird
   * @param birdType the type of a Bird. Type is the subcategory of Bird Category.
   *                 Should be one of the types from {@code FlightlessBirdTypes}
   * @param foodItemList list of preferences of food items ( 2-4 ) for a bird. Items
   *                    must be from the list in {@code FoodItem}
   * @throws IllegalArgumentException if number of wings is negative
   * @throws IllegalArgumentException if bird type is not one of the
   *            types from {@code PreyBirdTypes}
   * @throws IllegalArgumentException if foodItemList contains a food item not from FoodItem enum.
   *
   * */
  public PreyBird(int birdID, Boolean extinct, int noOfWings,
                  BirdType birdType, List<FoodItem> foodItemList)
          throws IllegalArgumentException {
    super(birdID, extinct, noOfWings, foodItemList);
    if (!PreyBirdTypes.contains(birdType)) {
      throw new IllegalArgumentException("Bird Type is not a type of Prey bird.");
    }
    this.birdType = birdType;
  }


  @Override
  public String getCharacteristics() {
    return characteristics;
  }

  @Override
  public BirdType getBirdType() {
    return birdType;
  }

  @Override
  public String toString() {

    return String.format("Prey Bird ( ID: %d, Number of wings = %d, "
                    + "Extinct = %b, Bird Type = %s )",
            this.getBirdID(), this.getNoOfWings(), this.getIsExtinct(), this.getBirdType());

  }


}
