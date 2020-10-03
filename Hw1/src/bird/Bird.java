package bird;

import java.util.List;

/**
 * Birds interface which represents a Bird and its related functionalities.
 * */
public interface Bird extends Comparable<Bird> {

  /**
   * Gets Bird ID of a Bird.
   *
   * @return Bird ID
   * */
  int getBirdID();

  /**
   * Gets Bird characteristics of a Bird.
   *
   * @return Bird characteristics description
   * */
  String getCharacteristics();

  /**
   * Gets extinction status of a Bird.
   *
   * @return Bird extinction status
   * */
  Boolean getIsExtinct();

  /**
   * Gets Type of a Bird. Type is the subcategory of Bird Category.
   *
   * @return Bird type
   * */
  BirdType getBirdType();

  /**
   * Gets number of wings of a Bird.
   *
   * @return number of wings of bird
   * */
  int getNoOfWings();

  /**
   * Gets food preferences of a Bird.
   * It is a list of enum {@code FoodItem}.
   *
   * @return list of food preferences
   * */
  List<FoodItem> getFoodItemList();

  /**
   * Gets number of words in vocabulary of a bird.
   * Applicable only for parrot in this scenario.
   *
   * @return no of words in vocabulary
   * @throws IllegalCallerException if an invalid bird calls this
   * */
  int getNoOfWordsInVocab() throws IllegalCallerException;

  /**
   * Gets favorite saying of a bird.
   * Applicable only for parrot in this scenario.
   *
   * @return favorite saying
   * @throws IllegalCallerException if an invalid bird calls this
   * */
  String getFavSaying() throws  IllegalCallerException;

  /**
   * Gets list of water bodies associated with bird.
   * Applicable only for Shore birds and water fowl in this scenario.
   *
   * @return list of water bodies. List here should be a list of enum WaterBody
   * @throws IllegalCallerException if an invalid bird calls this
   * */

  List<WaterBody> getWaterBodies() throws  IllegalCallerException;

}
