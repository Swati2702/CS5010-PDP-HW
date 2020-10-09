package item;


/**
 * Abstract base class for implementations of {@link Item}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Item} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 * */
public abstract class AbstractItem implements Item, Comparable<Item> {

  protected final String nameAdj;
  protected final String nameNoun;
  protected final int attackPower;
  protected final int defenseStrength;
  protected final boolean isCombined;



  private boolean isNotValidName( String name) {

    return (name.isEmpty() || name.isBlank() || !name.matches("[a-zA-Z,\\s]+"));
    //
  }

  /**
   * Constructor for gears which can only have one of attackPower or defenseStrength but not both.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param power : defense strength or attack power of the gear
   * @param which : tells whether the power is an attack power or defense strength
   * @throws IllegalArgumentException if power is negative or which is not valid or
   *                adjective or noun of the gear is not a valid name
   * */
  protected AbstractItem(String nameAdj, String nameNoun, int power,
                         String which)
      throws IllegalArgumentException {

    if (power < 0 || !(which.equalsIgnoreCase(ATTACK) || which.equalsIgnoreCase(DEFENSE))
            || isNotValidName(nameAdj) || isNotValidName(nameNoun)) {
      throw new IllegalArgumentException("Illegal argument.");
    }

    this.nameAdj = nameAdj;
    this.nameNoun = nameNoun;
    this.isCombined = false;
    if (which.equalsIgnoreCase(ATTACK)) {
      this.attackPower = power;
      this.defenseStrength = 0;
    }
    else {
      this.attackPower = 0;
      this.defenseStrength = power;
    }
  }

  /**
   * Constructor for gears which can have both attackPower and defenseStrength.
   * Will be called by FootWears or combined gears since they can have both powers.
   * @param nameAdj : Adjective portion of the name of the gear
   * @param nameNoun : Noun portion of the name of the gear
   * @param defenseStrength : defense strength of the gear
   * @param attackPower : attack power of the gear
   * @param isCombined : tells whether the item is a combination of two gears or not
   * @throws IllegalArgumentException if power is negative or which is not valid or
   *                adjective or noun of the gear is not a valid name
   * */



  protected AbstractItem(String nameAdj, String nameNoun, int attackPower,
                         int defenseStrength, boolean isCombined)
          throws IllegalArgumentException {

    if ( isNotValidName(nameAdj) || isNotValidName(nameNoun)) {
      throw new IllegalArgumentException("Illegal argument.");
    }

    this.nameAdj = nameAdj;
    this.nameNoun = nameNoun;
    this.attackPower = attackPower;
    this.defenseStrength = defenseStrength;
    this.isCombined = isCombined;
  }

  @Override
  public String getName() {
    return nameAdj + " " + nameNoun;
  }

  @Override
  public int getAttackPower() {
    return attackPower;
  }

  @Override
  public int getDefenseStrength() {
    return defenseStrength;
  }

  @Override
  public boolean isCombined() {
    return isCombined;
  }

  @Override
  public Item getCombinedItem(Item item) throws IllegalArgumentException {
    if (!this.getClass().getName().equals(item.getClass().getName()) ) {
      throw  new IllegalArgumentException("Can only combine items of same type.");
    }

    if (this.isCombined || item.isCombined()) {
      throw  new IllegalArgumentException("Cannot combine an already combined gear.");
    }

    switch (item.getClass().getName()) {
      case "item.HeadGear" : return new HeadGear(this.nameAdj + ",", item.getName(),
              this.defenseStrength + item.getDefenseStrength(), true);

      case "item.HandGear": return new HandGear(this.nameAdj + ",", item.getName(),
              this.attackPower + item.getAttackPower() , true);

      case "item.FootWear": return new FootWear(this.nameAdj + ",", item.getName(),
              this.attackPower + item.getAttackPower(),
              this.defenseStrength + item.getDefenseStrength() , true);

      default: throw new IllegalArgumentException("Item not of expected class.");
    }

  }

  @Override
   public String toString() {
    return String.format(" %s : \t( Name: %s, Attack power = %d, "
                    + "Defense Strength =  %d )",
            this.getClass().getName(), this.getName(), this.attackPower, this.defenseStrength);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Item)) {
      return false;
    }

    Item that = (Item) o;

    return (this.toString().equals(that.toString()) ) ;
  }

  @Override
  public int compareTo(Item that) {
    int thisTotalPower = this.getAttackPower() + this.getDefenseStrength();
    int thatTotalPower = that.getAttackPower() + that.getDefenseStrength();
    if (thisTotalPower != thatTotalPower) {
      return thatTotalPower - thisTotalPower;
    }
    else if (that.getAttackPower() != this.getAttackPower()) {
      return that.getAttackPower() - this.getAttackPower();
    }
    else {
      return that.getDefenseStrength() - this.getDefenseStrength();
    }

  }

}
