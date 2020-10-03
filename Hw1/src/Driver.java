import bird.Bird;
import bird.BirdType;
import bird.FlightlessBird;
import bird.FoodItem;
import bird.Owl;
import bird.Parrot;
import bird.ShoreBird;
import conservatory.Conservatory;

import java.util.Arrays;

/**
 * Driver Class to show sample run.
 * */
public class Driver {
  /**
   * Main function for jar to run.
   */
  public static void main(String... args) {

    if (args.length != 0 && args[0].equals("-1")) {
      Conservatory c1 = new Conservatory();
      FoodItem[] foodItems = new FoodItem[]{FoodItem.AQUATIC_INVERTEBRATES, FoodItem.BERRIES};
      Bird flightlessBird1 = new FlightlessBird(5, false, 2,
              BirdType.EMU, Arrays.asList(foodItems));
      try {
        c1.assignBirdToAviary(1, flightlessBird1);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      c1.addAviaryToConservatory();

      Bird parrot1 = new Parrot(1, false, 2, BirdType.GRAY_PARROT,
              100, "omg", Arrays.asList(foodItems));

      c1.assignBirdToAviary(0, parrot1);
      c1.assignBirdToAviary(0, flightlessBird1);


      Bird owl2 = new Owl(10, false, 1, Arrays.asList(foodItems));

      try {
        c1.assignBirdToAviary(1, owl2);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      c1.addAviaryToConservatory();

      Bird flightlessBird2 = new FlightlessBird(6, false, 2,
              BirdType.EMU, Arrays.asList(foodItems));
      try {
        c1.assignBirdToAviary(1, flightlessBird2);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      Bird owl1 = new Owl(3, false, 1, Arrays.asList(foodItems));
      c1.assignBirdToAviary(1, owl1);

      c1.addAviaryToConservatory();

      c1.assignBirdToAviary(2, owl1);
      Bird parrot2 = new Parrot(2, false, 2, BirdType.ROSE_RING_PARAKEET,
              101, "I am Swati", Arrays.asList(foodItems));
      c1.assignBirdToAviary(2, parrot2);
      Bird parrot3 = new Parrot(8, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
              100, "hi", Arrays.asList(foodItems));
      c1.assignBirdToAviary(2, parrot3);
      Bird parrot4 = new Parrot(9, Boolean.FALSE, 2, BirdType.ROSE_RING_PARAKEET,
              101, "I am great", Arrays.asList(foodItems));
      c1.assignBirdToAviary(2, parrot4);
      c1.assignBirdToAviary(2, owl2);
      Bird owl3 = new Owl(90, false, 1, Arrays.asList(foodItems));

      try {
        c1.assignBirdToAviary(2, owl3);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }


      Bird shoreBird1 = new ShoreBird(4, true, 0,
              BirdType.HORNED_PUFFIN, Arrays.asList(foodItems));

      try {
        c1.assignBirdToAviary(1, shoreBird1);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      c1.showBirdIndex();

      if (!c1.lookUpBird(100)) {
        System.out.println(" Bird " + 100 + " not found in Conservatory.");

      }

    } else if (args.length == 0 || args[0].equals("-2")) {

      Conservatory c1 = new Conservatory();
      FoodItem[] foodItems = new FoodItem[]{FoodItem.AQUATIC_INVERTEBRATES, FoodItem.BERRIES};
      Bird flightlessBird1 = new FlightlessBird(5, false, 2,
              BirdType.EMU, Arrays.asList(foodItems));
      c1.addAviaryToConservatory();
      c1.assignBirdToAviary(0, flightlessBird1);

      c1.addAviaryToConservatory();
      Bird owl1 = new Owl(3, false, 1, Arrays.asList(foodItems));
      c1.assignBirdToAviary(1, owl1);

      Bird parrot2 = new Parrot(2, false, 2, BirdType.ROSE_RING_PARAKEET,
              101, "Hello World", Arrays.asList(foodItems));
      c1.assignBirdToAviary(1, parrot2);
      Bird parrot3 = new Parrot(8, Boolean.FALSE, 2, BirdType.GRAY_PARROT,
              100, "Hi", Arrays.asList(foodItems));
      c1.assignBirdToAviary(1, parrot3);

      c1.showAviaryDirectory();
      c1.displayFoodNeeds();
      c1.lookUpBird(3);
      c1.showAviaryDescription(1);
    }

  }
}
