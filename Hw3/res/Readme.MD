**How to run Program:**


Run Sample run 1 with command:

java -jar Hw3.jar -1 

This creates a perfect maze of size (3,3) with starting point (0, 0 ) and goal point (2,2)
It states the current location, gold count, available moves and status of each cell in the maze
( whether it has gold or thief or is blank/empty) at each step. It navigates through the 
entire maze and visits each node. 

Run Sample run 2 with command:

java -jar Hw3.jar -2

This creates a wrapping room  maze of size (3,3) with starting point (0, 0 ) and goal point (2,2)
It states the current location, gold count, available moves and status of each cell in the maze
( whether it has gold or thief or is blank/empty) at each step. It navigates through the 
entire maze and visits each node. You will be able to see that it can wrap around with its moves. 

Run Interactive mode with command:

java -jar HW3.jar -3

All the parts of the program are complete.

List of features:
1. We can retrieve number of rows, columns, remaining walls of the maze

2. We can retrieve if maze is wrapped or not.

3. We can retrieve the starting location and goal location of the maze.

4. The maze can produce the player's location and gold count

20% of locations (at random) have gold coins in them that the player can pick up
10% of locations (at random) have a thief that takes 10% of the player's gold coins

5. The maze can produce the possible moves of the player (North, South, East or West) from their
   current location
   
6. The player can make a move by specifying a direction

7.  The player is  able to collect gold such that a player "collects" gold by entering a room that has gold which is then removed from the room
   a player "loses" 10% of their gold by entering a room with a thief

8. Since thief is located in 10% of the cells, maze should be big enough to get at least 
one gold/thief.

9. Specific directions available are available in Direction Enum

10. Types of available nodes are in NodeType enum.

Each room with gold has only one gold coin which the player can pick up. 
When a thief is encountered in a room, the gold becomes 90% of original value. This is in float.
Remaining walls counts only internal walls and not boundary walls in case of perfect
and unwrapped room maze. If room is wrapped, then boundary edges are also included in the count
of the remaining walls.


I have an interface for Maze 
I have an abstract class which inherits the interface 
Perfect maze and Room Maze extend Abstract Maze and Wrapping Room Maze extends 
room maze as wrapping room maze is a room maze but when edges are wrapped.

I have a player which keeps track of the current location in the maze and
is able to get the current location and current gold count of the player in the maze.

 
 **References:**
 
 https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

 