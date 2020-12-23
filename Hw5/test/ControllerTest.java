import org.junit.Test;

import java.io.InputStreamReader;

import controller.ConsoleController;
import controller.GameController;
import model.algo.Direction;
import model.maze.Maze;
import model.maze.PlayerStatus;
import model.maze.RoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for Controller.
 * */
public class ControllerTest {
  GameController controller;
  Maze maze;

  @Test(expected = IllegalArgumentException.class)
  public void constructorThrowsExceptionIfReadableNull() {
    controller = new ConsoleController(null, System.out, new RoomMaze(2, 3, 1, 1, 1,
            0, 2, 20, 10, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorThrowsExceptionIfAppendableNull() {
    controller = new ConsoleController(new InputStreamReader(System.in),
            null, new RoomMaze(2, 3,
            1, 1, 1,
            0, 2, 20, 10, 3));
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorThrowsExceptionIfMazeIsNull() {
    controller = new ConsoleController(new InputStreamReader(System.in), System.out, null);
  }


  @Test
  public void testShootingarrowDoesNotKillWumpusIfDistanceNotExact() {
    maze = new RoomMaze(4,
            4,
            3,
            1,
            1,
            0,
            2,
            20,
            10,
            5 );
    System.out.println(maze.visualize());
    maze.shootArrow(Direction.EAST,2);
    assertEquals(PlayerStatus.ALIVE, maze.getPlayer().getStatus());
  }

  @Test
  public void testFeelsDraftFromAdjacentPit() {
    controller = new ConsoleController(new InputStreamReader(System.in), System.out, null);

    maze = new RoomMaze(4,4, 1, 1, 0,
            0,2, 20, 10, 5 );
    assertEquals("You feel a draft ", maze.makeMove(Direction.EAST));
  }

  @Test
  public void testDoesNotFeelsDraftIfNoAdjacentPit() {
    controller = new ConsoleController(new InputStreamReader(System.in), System.out, null);

    maze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 5 );
    assertEquals("", maze.makeMove(Direction.WEST));
  }

  @Test
  public void testSmellsWumpusFromAdjacentWumpus() {
    controller = new ConsoleController(new InputStreamReader(System.in), System.out, null);

    maze = new RoomMaze(4,4, 1, 1, 1,
            0,2, 20, 10, 5 );
    assertEquals("You smell a Wumpus! ", maze.makeMove(Direction.NORTH));
  }

  @Test
  public void testDoesNotSmellsWumpusIfNoAdjacentWumpus() {
    controller = new ConsoleController(new InputStreamReader(System.in), System.out, null);

    maze = new RoomMaze(4,4, 1, 3, 1,
            0,2, 20, 10, 5 );
    assertEquals("", maze.makeMove(Direction.NORTH));
  }

  @Test
  public void testArrowTravelsInStraightLineInCave() {
    maze = new RoomMaze(4,4, 1, 3, 1,
            1,1, 20, 10, 5 );
    assertEquals("ee Hhee hee, you got the wumpus!\nNext time you won't be so lucky",
            maze.shootArrow(Direction.NORTH, 2));
    assertEquals(PlayerStatus.WINNER, maze.getPlayer().getStatus());
  }

  @Test
  public void testArrowTravelsInCrookedMannerInTunnel() {
    maze = new RoomMaze(4,4, 1, 1,0,
            0,1, 20, 10, 5 );
    assertEquals("Hee hee hee, you got the wumpus!\nNext time you won't be so lucky",
            maze.shootArrow(Direction.NORTH, 1));
    assertEquals(PlayerStatus.WINNER, maze.getPlayer().getStatus());
  }


}
