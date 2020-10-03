package bird;

import java.util.List;

/**
 * Owl Bird Category.
 * */
public final class Owl extends AbstractBird {
  private static final String characteristics = "Distinguished by the "
                  + "facial disks that frame the eyes and bill.";

  /**
   * Constructs a Owl.
   * @param birdID User should provide unique ID of the bird
   * @param extinct tells whether bird is extinct or not
   * @param noOfWings  the number of wings of the bird
   * @param foodItemList list of preferences of food items ( 2-4 ) for a bird. Items
   *                    must be from the list in {@code FoodItem}
   * @throws IllegalArgumentException if number of wings is negative
   * @throws IllegalArgumentException if foodItemList contains a food item not from FoodItem enum.
   *
   * */
  public Owl(int birdID, Boolean extinct, int noOfWings, List<FoodItem> foodItemList) {
    super(birdID, extinct, noOfWings, foodItemList);
  }


  @Override
  public String getCharacteristics() {
    return characteristics;
  }

  @Override
  public String toString() {
    return String.format("Owl ( ID: %d, Number of wings = %d, "
                    + "Extinct = %b )",
            this.getBirdID(), this.getNoOfWings(), this.getIsExtinct());

  }

}
