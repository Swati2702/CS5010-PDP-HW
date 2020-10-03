package bird;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * ShoreBird Bird Category.
 * */
public final class ShoreBird extends AbstractBird {
  private static final String characteristics = "They live near water sources including wetlands, "
          + "freshwater and saltwater shorelands, even the ocean";
  private static final EnumSet<BirdType> ShoreBirdTypes = EnumSet.of(BirdType.HORNED_PUFFIN,
          BirdType.GREAT_AUK, BirdType.AFRICAN_JACANA );


  private final BirdType birdType;
  private final List<WaterBody> waterBodies = Arrays.asList(WaterBody.FRESHWATER,
          WaterBody.SALTWATER, WaterBody.OCEAN, WaterBody.WETLANDS);

  /**
   * Constructs a Flightless bird.
   * @param birdID User should provide unique ID of the bird
   * @param extinct tells whether bird is extinct or not
   * @param noOfWings  the number of wings of the bird
   * @param birdType the type of a Bird. Type is the subcategory of Bird Category.
   *                 Should be one of the types from {@code ShoreBirdTypes}
   * @param foodItemList list of preferences of food items ( 2-4 ) for a bird. Items
   *                    must be from the list in {@code FoodItem}
   * @throws IllegalArgumentException if number of wings is negative
   * @throws IllegalArgumentException if bird type is not one of the
   *            types from {@code ShoreBirdTypes}
   * @throws IllegalArgumentException if foodItemList contains a food item not from FoodItem enum.
   *
   * */
  public ShoreBird(int birdID, Boolean extinct, int noOfWings, BirdType birdType,
                   List<FoodItem> foodItemList) throws IllegalArgumentException {
    super(birdID, extinct, noOfWings,foodItemList);
    if (!ShoreBirdTypes.contains(birdType)) {
      throw new IllegalArgumentException("Bird Type is not a type of Shore bird.");
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
  public List<WaterBody> getWaterBodies() {
    return this.waterBodies;
  }

  @Override
  public String toString() {

    return String.format("Shore Bird ( ID: %d, Number of wings = %d, "
                    + "Extinct = %b, Bird Type = %s , Water bodies = %s )",
            this.getBirdID(), this.getNoOfWings(), this.getIsExtinct(),
            this.getBirdType(), this.getWaterBodies());

  }

}
