package bird;

import java.util.EnumSet;
import java.util.List;

abstract class AbstractBird implements Bird {

  private final int birdID;
  private final boolean extinct;
  private final int noOfWings;
  private final List<FoodItem> foodItemList;
  EnumSet<FoodItem> foodItemEnumSet = EnumSet.allOf(FoodItem.class);

  protected AbstractBird(int birdID, Boolean extinct, int noOfWings, List<FoodItem> foodItemList)
          throws IllegalArgumentException {
    if ( birdID < 0 ) {
      throw new IllegalArgumentException(" Bird ID cannot be negative.");
    }

    if ( noOfWings < 0 ) {
      throw new IllegalArgumentException(" Number of wings cannot be negative.");
    }

    if (foodItemList.size() < 2 || foodItemList.size() > 4) {
      throw new IllegalArgumentException(" Number of food preferences not between 2-4.");
    }

    for (FoodItem foodItem: foodItemList) {
      if (!foodItemEnumSet.contains(foodItem)) {
        throw new IllegalArgumentException(" Food preference " + foodItem
                + " not from the given list.");
      }
    }
    this.birdID = birdID;
    this.extinct = extinct;
    this.noOfWings = noOfWings;
    this.foodItemList = foodItemList;
  }

  @Override
  public int getBirdID() {
    return birdID;
  }

  @Override
  public BirdType getBirdType() throws IllegalCallerException {
    throw new IllegalCallerException("Bird Type does not exist for this bird.");
  }

  @Override
  public Boolean getIsExtinct() {
    return extinct;
  }

  @Override
  public int getNoOfWings() {
    return noOfWings;
  }

  @Override
  public List<FoodItem> getFoodItemList() {
    return foodItemList;
  }


  @Override
  public int getNoOfWordsInVocab() throws IllegalCallerException {
    throw new IllegalCallerException("Method used for Incorrect bird type. "
            + "Try calling this method for Parrot.");
  }

  @Override
  public String getFavSaying() throws IllegalCallerException {
    throw new IllegalCallerException("Method used for Incorrect bird type. "
            + "Try calling this method for Parrot.");
  }

  @Override
  public List<WaterBody> getWaterBodies() throws IllegalCallerException {
    throw new IllegalCallerException("Method used for Incorrect bird type. "
            + "Try calling this method for Water Fowl or Shore Bird.");
  }

  @Override
  public int compareTo(Bird thatBird) {
    return this.getClass().toString().compareTo(thatBird.getClass().toString());
  }

}
