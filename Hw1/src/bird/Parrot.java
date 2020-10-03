package bird;

import java.util.EnumSet;
import java.util.List;

/**
 * Parrot Bird Category.
 * */

public final class Parrot extends AbstractBird {

  private final int noOfWordsInVocab;
  private final String favSaying;
  private static final String characteristics = "Short, curved beak and are known "
          + "for their intelligence and ability to mimic sounds.";
  private final BirdType birdType;
  private static final EnumSet<BirdType> ParrotTypes = EnumSet.of(BirdType.GRAY_PARROT,
          BirdType.ROSE_RING_PARAKEET, BirdType.SULFUR_CRESTED_COCATOO );

  /**
   * Constructs a Parrot.
   * @param birdID User should provide unique ID of the bird
   * @param extinct tells whether bird is extinct or not
   * @param noOfWings  the number of wings of the bird
   * @param birdType the type of a Bird. Type is the subcategory of Bird Category.
   *                 Should be one of the types from {@code ParrotTypes}
   * @param foodItemList list of preferences of food items ( 2-4 ) for a bird. Items
   *                    must be from the list in {@code FoodItem}
   * @param noOfWordsInVocab number of words in parrot's vocabulary
   * @param favSaying favorite saying of the parrot, cannot be empty or null.
   * @throws IllegalArgumentException if number of wings is negative
   * @throws IllegalArgumentException if bird type is not one of the
   *            types from {@code ParrotTypes}
   * @throws IllegalArgumentException if foodItemList contains a food item not from FoodItem enum.
   * @throws IllegalArgumentException if noOfWordsInVocab is negative
   * @throws IllegalArgumentException if favSaying is empty or null
   * */
  public Parrot(int birdID, Boolean extinct, int noOfWings, BirdType birdType,
         int noOfWordsInVocab, String favSaying, List<FoodItem> foodItemList)
          throws IllegalArgumentException {
    super(birdID, extinct, noOfWings, foodItemList);
    if (noOfWordsInVocab < 0 ) {
      throw new IllegalArgumentException(" Number of words in vocab cannot be negative.");
    }
    if (favSaying == null || favSaying.length() == 0 ) {
      throw new IllegalArgumentException(" Not a valid favorite saying.");
    }
    if (!ParrotTypes.contains(birdType)) {
      throw new IllegalArgumentException("Bird Type is not a type of Parrot.");
    }
    this.birdType = birdType;
    this.favSaying = favSaying;
    this.noOfWordsInVocab = noOfWordsInVocab;
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
  public String getFavSaying()  {
    return favSaying;
  }


  @Override
  public int getNoOfWordsInVocab()  {
    return noOfWordsInVocab;
  }

  @Override
  public String toString() {
    return String.format("Parrot ( ID: %d, Number of wings = %d, "
                    + "Extinct = %b, Bird Type = %s, "
                    + "Number of words in vocabulary = %d, "
                    +  "Favorite saying = %s )",
                    this.getBirdID(), this.getNoOfWings(), this.getIsExtinct(),
            this.getBirdType(), this.getNoOfWordsInVocab(), this.getFavSaying());

  }

}
