**How to run Program:**

1. Run text mode
java -jar Program.jar --text
This allows you to create the maze by yourself and then proceed to play the game.

2. Run gui mode
   java -jar Program.jar --gui


All the parts of the program are complete.
List of modified features:

1. Added a new controller GUIController to handle the GUI. This will act as an intermediate 
between GUI view and the model.

2. GUI controller creates the maze.

Modified the method to make a move and shoot arrow, so that controller can use the string output 
from that to be able to decide actions for the view.

3. The driver calls the RunGUI which renders a window where you can enter the maze configuration
and then play the game. 

4. You can play the game either by clicking on the adjacent cells of the player using the mouse
or the arrow keys.

5. Each player gets one move and the players can play turn by turn until either of them wins or 
both are dead. 

6. You can restart the game by hitting R button on the keyboard. 
If you select Yes on the dialogbox, it will create a new random game , and if you select No,
it will restart the game with the same configuration.

7. You can shoot an arrow by hitting S button on the keyboard. you can then enter the direction and
distance in the popup.

8. A player cannot enter the same location as another player. 

9. red dots for wumpus nearby and yellow dots for pit nearby


Previous features: 

1. We can retrieve number of rows, columns, remaining walls of the maze

3. We can retrieve the starting location and wumpus location of the maze.

4. The maze can produce the player's location and gold count

20% of locations (at random) have gold coins in them that the player can pick up
10% of locations (at random) have a thief that takes 10% of the player's gold coins

5. The maze can produce the possible moves of the player (North, South, East or West) from their
   current location
   
6. The player can make a move by specifying a direction

9. Specific directions available are available in Direction Enum

10. Types of available nodes are in NodeType enum.

11.functionality for moving in the maze and shooting arrows. 

12. A player that encounters a superbat has a chance of being whisked away and dropped in 
        some other random cave in the maze. 
        
13. A player that encounters the Wumpus is eaten and the player dies.

14. A blue border around the cell shows the turn of which player it is.

I have an interface for Maze 
I have an abstract class which inherits the interface 
Perfect maze and Room Maze extend Abstract Maze and Wrapping Room Maze extends 
room maze as wrapping room maze is a room maze but when edges are wrapped.

I have a player which keeps track of the current location in the maze and
is able to get the current location and current arrow count, and status of the player in the maze.

 
 **References:**
 
https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

https://github.ccs.neu.edu/ccf/ttt-controller-starter/blob/master/

 