**How to run Program:**


Run Sample run 1 with command:


java -jar HW1.jar -1 


Run Sample run 2 with command:

java -jar HW1.jar -2

All the parts of the program are complete.

**Design Changes:** 

1. removed setCharacter() from Battle class, as I am making a constructor in the Battle class
 which takes the characters and a list of choices. 

2. Removed specific variables for each of the items in the character class, and instead
kept a list of items which will store all the items picked up by the character, whether it
be combined or individual items.

3. Introduced character ID in character class which is a unique identifier for the character.
Also introduced a getter for this.

4. Introduced getTotalAttackPower() and getTotalDefensePower() methods in Character so
that user can get the total power for the character with the items that he has picked.

5. Introduced addItem() and removeItem() which will allow character to add/remove an item 
to/from his list.
 And addItemAndCombine which will allow charcter to pick up an item and combine with an 
 already present item with the character.
 
6. introduced isCombined variable for Item which will tell if an item is 
combined or not. Introduced getter for this method as well. 

7. Renamed combineItems() to getCombinedItem().

 **References:**
 
 
 https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/
 
 
