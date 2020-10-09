package battle;

/**
 * This interface represents a battle.
 * You can choose two characters, dress them from the list of items available for them
 * and wage a battle between them.
 * */

public interface BattleInterface {

  /**
   * Get character 1 of the battle.
   * */
  Character getCharacter1();

  /**
   * Get character 2 of the battle.
   * */
  Character getCharacter2();

  /**
   * Logic for dressing characters.
   * Itemlist is sorted first based on the highest strength, and
   * Higher attack strength is chosen over higher defense strength.
   * Then from the sorted list of items, character sees each item
   * First preference is given to items which character can combine with one of his already present
   * items. If he cannot combine then he adds it as a single item
   * */
  void dressCharacters();


  /** Returns the character who won the battle.
   *  The winner is determined by who has less damage after a
   * battle. Damage is calculated by one's opponent's attack power minus
   * that character's defense points.
   * @return  winner character
   * */
  Character getWinner();

}
