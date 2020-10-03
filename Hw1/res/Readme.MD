**How to run Program:**


Run Sample run 1 with command:


java -jar HW1.jar -1 


Run Sample run 2 with command:

java -jar HW1.jar -2

All the parts of the program are complete.

**Design Changes:** 

1. Moved methods from concrete Bird classes to the Interface
 because that should be the public face which is visible to the outside world. 
 So all the methods related to bird is in the interface having a default implementation
 there which is overridden in the Abstract Bird Class and then in Concrete class if required. 
 
2. Removed AbstractWaterBirds because of the above reason only. I was using it to introduce 
a method which would be called only by Waterfowl and shorebirds. But I introduced this method 
in the interface itself. So this was no longer required. 

3. Changed the field waterBody from string to enum as it should be able to take only specific
values for which I then introduced an Enum.

4. Added a method addAviaryToConservatory to Conservatory class. I had first thought I would make 
 a size 20 aviaries list for my conservatory as soon as I make a conservatory. Then I would not have
 needed this method. But then it would have led to wasting memory, and so I decided to add aviaries
 as needed. And for that I introduced this method.
 
5. showAviaryDescription method  moved from aviary to conservatory class so
 that I can access it using object of conservatory itself. Otherwise I would need to have an
 object of Aviary to access it. This design looked more convenient from the 
 perspective of the user. So I chose this. 
 
 **References:**
 
 
 https://www.geeksforgeeks.org/initialize-an-arraylist-in-java/
 
 
 https://stackoverflow.com/questions/3110547/java-how-to-create-new-entry-key-value
