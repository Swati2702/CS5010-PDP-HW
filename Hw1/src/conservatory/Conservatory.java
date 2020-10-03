package conservatory;

import bird.Bird;
import bird.FlightlessBird;
import bird.FoodItem;
import bird.PreyBird;
import bird.WaterFowl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Conservatory allows to rescue new birds and bring them into the conservatory.
 * Birds are kept in aviaries which should be created in the conservatory.
 * */

public class Conservatory {

  public List<Aviary> aviaries = new ArrayList<>();

  /**
   * Adds an aviary to the conservatory.
   * @throws IllegalStateException if number of aviaries exceed 20
   * */
  public void addAviaryToConservatory() throws IllegalStateException {

    if (aviaries.size() >= 20) {
      throw new IllegalStateException("Cannot add aviary to conservatory. Maximum limit is 20. ");
    }
    Aviary newAviary = new Aviary();
    aviaries.add(newAviary);
  }

  /**
   * Assigns a bird to a particular aviary.
   * @param aviaryIndex the index of the aviary in which you want to add the bird
   * @param bird the bird which you want to assign in aviaryIndex aviary
   * @return true if bird was added successfully
   * @throws IllegalArgumentException if aviary given is invalid
   * */

  public boolean assignBirdToAviary(int aviaryIndex, Bird bird) throws IllegalArgumentException {

    if (aviaryIndex < 0) {
      throw new IllegalArgumentException(" Aviary index cannot be negative.");
    }

    if (aviaryIndex >= aviaries.size()) {
      throw new IllegalArgumentException(" Aviary " + aviaryIndex + " not constructed yet.");
    }


    Aviary selectedAviary = aviaries.get(aviaryIndex);
    boolean assignFlag;



    if (lookUpBird(bird.getBirdID())) {
      throw new IllegalArgumentException("Cannot add a bird which is already"
              + " present in the Conservatory.");
    }

    if (selectedAviary.birds.size() >= 5) {
      throw new IllegalArgumentException(" Cannot add bird to aviary, aviary "
              + aviaryIndex + " is full. ");
    }

    if (bird.getIsExtinct()) {
      throw new IllegalArgumentException(" Cannot add bird to aviary, bird "
              + bird.getBirdID() + " is extinct. ");
    }

    if (selectedAviary.birds.size() == 0) {
      assignFlag = true;
    }
    else if (bird instanceof FlightlessBird) {
      assignFlag =  selectedAviary.checkAviaryForBirdCategory(bird.getClass());
    }
    else if (bird instanceof PreyBird) {
      assignFlag =  selectedAviary.checkAviaryForBirdCategory(bird.getClass());
    }
    else if (bird instanceof WaterFowl) {
      assignFlag =  selectedAviary.checkAviaryForBirdCategory(bird.getClass());
    }
    else {
      assignFlag = !(selectedAviary.checkAviaryForBirdCategory(FlightlessBird.class)
              || selectedAviary.checkAviaryForBirdCategory(PreyBird.class)
                || selectedAviary.checkAviaryForBirdCategory(WaterFowl.class) );
    }

    if (assignFlag) {
      aviaries.get(aviaryIndex).birds.add(bird);
      System.out.println("Bird " + bird.getBirdID()
              + " added successfully to Aviary " + aviaryIndex);
      return true;
    }

    System.out.println("Bird " + bird.getBirdID() + " was not added to aviary " + aviaryIndex);
    return false;
  }

  /**
   * Calculates the food needs of the entire conservatory and displays it.
   * */
  public void displayFoodNeeds() {
    HashMap<FoodItem,Integer> foodQuantities = new HashMap<>();

    for (Aviary selectedAviary : aviaries) {
      for (int birdIndex = 0; birdIndex < selectedAviary.birds.size(); birdIndex++) {
        Bird selectedBird = selectedAviary.birds.get(birdIndex);
        List<FoodItem> foodItemList = selectedBird.getFoodItemList();
        for (FoodItem foodItem : foodItemList) {
          Integer currentFoodQuantity = foodQuantities.getOrDefault(foodItem, 0);
          foodQuantities.put(foodItem, currentFoodQuantity + 1);
        }
      }
    }
    System.out.println("\n Displaying food quantities required for each type "
            + "by the entire conservatory.");

    for (FoodItem foodItem : foodQuantities.keySet()) {
      System.out.println(" Food item: " + foodItem + "\t Quantity: "
              + foodQuantities.get(foodItem));

    }
  }

  /**
   * Allows you to lookup a particular bird in the conservatory.
   * @return true if bird is found
   * @throws IllegalArgumentException if bird ID is negative
   * */

  public boolean lookUpBird(int birdID) throws IllegalArgumentException {
    if (birdID < 0 ) {
      throw new IllegalArgumentException("Bird ID cannot be negative.");
    }

    for (int aviaryIndex = 0; aviaryIndex < aviaries.size(); aviaryIndex++) {
      Aviary selectedAviary = aviaries.get(aviaryIndex);
      for (int birdIndex = 0; birdIndex < selectedAviary.birds.size(); birdIndex++) {
        Bird selectedBird = selectedAviary.birds.get(birdIndex);
        if (selectedBird.getBirdID() == birdID) {
          System.out.println("Bird " + birdID + " present in Aviary " + aviaryIndex);
          return true;
        }
      }
    }

    return false;

  }

  /**
   * Displays a “directory” that lists all the aviaries by location and the birds they house.
   * */
  public void showAviaryDirectory() throws IllegalCallerException {
    if (aviaries.size() == 0) {
      throw new IllegalCallerException("Conservatory is empty.");
    }

    System.out.println("\n Displaying Aviary Directory:");

    for (int aviaryIndex = 0; aviaryIndex < aviaries.size(); aviaryIndex++) {
      Aviary selectedAviary = aviaries.get(aviaryIndex);

      for (int birdIndex = 0; birdIndex < selectedAviary.birds.size(); birdIndex++) {
        Bird selectedBird = selectedAviary.birds.get(birdIndex);
        System.out.println("Aviary index: " + aviaryIndex + "\t Bird ID: "
                + selectedBird.getBirdID()
                + "\t Bird Category: " + selectedBird.getClass());
      }
    }
  }

  /**
   * Produce an index that lists all birds in the conservatory in
   * alphabetical order by their category and shows their aviary location.
   * */
  public void showBirdIndex() {
    if (aviaries.size() == 0) {
      throw new IllegalCallerException("Conservatory is empty.");
    }


    List<Map.Entry<?,?>> birdList = new ArrayList<>();
    for (int aviaryIndex = 0; aviaryIndex < aviaries.size(); aviaryIndex++) {
      Aviary selectedAviary = aviaries.get(aviaryIndex);
      for (Bird bird: selectedAviary.birds) {
        Map.Entry<Integer, Integer> birdIdAviaryIndex =
                new AbstractMap.SimpleImmutableEntry<>(bird.getBirdID(), aviaryIndex);
        Map.Entry<String,Map.Entry<Integer,Integer>> birdAviaryTuple =
                new AbstractMap.SimpleImmutableEntry<>(bird.getClass().getName(),
                        birdIdAviaryIndex);
        birdList.add(birdAviaryTuple);
      }
    }
    birdList.sort(Comparator.comparing(o -> (String)o.getKey()));

    System.out.println("\n Displaying Bird Index sorted by the category of bird. ");
    for (Map.Entry<?,?> birdAviary : birdList) {
      System.out.println("Bird Category: " + birdAviary.getKey()
              + "\t Bird ID:" + ((Map.Entry)birdAviary.getValue()).getKey()
                      + "\t Aviary ID:" + ((Map.Entry)birdAviary.getValue()).getValue());

    }
  }

  /**
   * Display text for any given aviary that gives a description of the birds
   * it houses and any interesting information that it may have about that animal.
   * */
  public void showAviaryDescription(int aviaryIndex) throws IllegalArgumentException {

    if (aviaryIndex < 0) {
      throw new IllegalArgumentException(" Aviary index cannot be negative.");
    }

    if (aviaryIndex >= aviaries.size()) {
      throw new IllegalArgumentException(" Aviary " + aviaryIndex + " does not exist.");
    }
    Aviary selectedAviary = aviaries.get(aviaryIndex);

    String description;

    for (Bird bird : selectedAviary.birds) {
      description = "\n Bird ID: " + bird.getBirdID()
              + "\t Number of wings: " + bird.getNoOfWings()
              + "\t Extinction Status: " + bird.getIsExtinct()
              + "\n Characteristics: " + bird.getCharacteristics()
              + "\n Bird Category: " + bird.getClass()
              + "\n Preferred Food items: " + bird.getFoodItemList();
      try {
        description += "\n Bird type: " + bird.getBirdType();
      } catch (Exception e) {
        System.out.print("");
      }
      try {
        description += "\n Number of words in vocab of bird: " + bird.getNoOfWordsInVocab();
      } catch (Exception e) {
        System.out.print("");
      }

      try {
        description += "\n Favorite saying: " + bird.getFavSaying();
      } catch (Exception e) {
        System.out.print("");
      }

      try {
        description += "\n Live by the following water bodies:  " + bird.getWaterBodies();
      } catch (Exception e) {
        System.out.print("");
      }
      System.out.println(description);
    }
  }
}
