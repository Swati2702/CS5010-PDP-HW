import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import model.algo.Direction;
import model.algo.NodeType;
import model.maze.AbstractMaze;
import model.maze.Player;
import model.maze.RoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the room maze wumpus game.
 * */
public class RoomMazeTest {


  AbstractMaze roomMaze;

  @Before
  public void setMazeGraph() {
    roomMaze = new RoomMaze(2,3, 1, 1, 1,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRows() {

    roomMaze = new RoomMaze(-1,3, 1,0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionColumns() {
    roomMaze = new RoomMaze(2,-3, 1, 0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRemainingWalls() {
    roomMaze = new RoomMaze(2,3, -1, 0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRemainingWallsInvalid() {
    roomMaze = new RoomMaze(2,2, 2, 0, 0,
            0,2, 20, 10, 3 );
  }

  //----------------------------------------------------new

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPitPercent() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 101, 10, 3 );
  }
  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPitPercent2() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, -1, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionBatPercent() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 20, 101, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionBatPercent2() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 20, -1, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionArrows() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, -3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPlayerRow() {
    roomMaze = new RoomMaze(2,3, 1, 4, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPlayerRow2() {
    roomMaze = new RoomMaze(2,3, 1, -2, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPlayerColumn() {
    roomMaze = new RoomMaze(2,3, 1, 0, -1,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionPlayerColumn2() {
    roomMaze = new RoomMaze(2,3, 1, 0, 5,
            0,2, 20, 10, 3 );
  }

  @Test
  public void testGetStartingPointRoomMaze() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    assertEquals("Pair[0,0]", roomMaze.getStartingPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionWumpusRow() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            4,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionWumpusRow2() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            -1,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionWumpusColumn() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,-1, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionWumpusColumn2() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,5, 20, 10, 3 );
  }

  @Test
  public void testGetGoalPointRoomMaze() {
    roomMaze = new RoomMaze(2,3, 1,0, 0,
            0,2, 20, 10, 3 );
    assertEquals("Pair[0,2]", roomMaze.getGoalPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointSameAsStartingPoint() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,0, 20, 10, 3 );
  }



//-----------------------end


  @Test
  public void testRoomMazeGraphConstruction() {
    assertEquals("AbstractMaze{numberOfRows=2, numberOfColumns=3, isWrapped=false, "
            + "numberOfRemainingWalls=1, mazeGraph={Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]="
            + "[Pair[0,0], Pair[1,1], Pair[0,2]], Pair[1,0]=[Pair[0,0], Pair[1,1]], Pair[0,2]="
            + "[Pair[0,1], Pair[1,2]], Pair[1,1]=[Pair[0,1], Pair[1,0]], Pair[1,2]=[Pair[0,2]]}, "
            + "startingPoint=Pair[1,1], goalPoint=Pair[0,2], pitPercent=20.0, batPercent=10.0}",
            roomMaze.toString());
  }


  @Test
  public void testGetNumberOfRowsRoomMaze() {
    assertEquals(2, roomMaze.getNumberOfRows());
  }

  @Test
  public void testGetNumberOfColumnsRoomMaze() {
    assertEquals(3, roomMaze.getNumberOfColumns());
  }


  @Test
  public void testisWrappedRoomMaze() {
    assertEquals(false, roomMaze.isWrapped());
  }

  @Test
  public void testGetNumberOfRemainingWallsRoomMaze() {
    assertEquals(1, roomMaze.getNumberOfRemainingWalls());
  }

  @Test
  public void testGetPossibleMoves() {
    assertEquals("[NORTH, WEST]", roomMaze.getPossibleMoves().toString());
  }

  @Test
  public void testGetNodeTypeList() {
    roomMaze = new RoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[1,2]=PIT}",
            roomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testPitSetupInMaze() {
    roomMaze = new RoomMaze(3,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    int pitCount = 0;
    for (NodeType nodeType: roomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.PIT)) {
        pitCount++;
      }
    }
    int expectedGoldCount = (int)(0.20 * 3 * 3);
    assertEquals(expectedGoldCount, pitCount);
  }

  @Test
  public void testBatSetupInMaze() {
    roomMaze = new RoomMaze(4,4, 1, 0, 0,
            0,2, 20, 10, 3 );
    int batCount = 0;
    for (NodeType nodeType: roomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.BAT)) {
        batCount++;
      }
    }
    int expectedThiefCount = (int)(0.10 * 4 * 4);
    assertEquals(expectedThiefCount, batCount);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testMakeMoveDisallowsInvalidDirections() {
    roomMaze.makeMove(Direction.EAST);
  }

  @Test
  public void testGetCurrentLocationOfPLayer() {
    Player player = roomMaze.getPlayer();
    assertEquals("Pair[0,0]", player.getCurrentLocation().toString());
    roomMaze.makeMove(Direction.EAST);
    assertEquals("Pair[1,1]", player.getCurrentLocation().toString());
  }



//
//  @Test
//  public void testGetArrowCountOfPLayer() {
//    Player player = roomMaze.getPlayer();
//    assertEquals(3, player.getArrowCount());
//    roomMaze.makeMove(Direction.EAST);
//    assertEquals(0, player.getArrowCount());
//  }

  @Test
  public void testGetArrowCountOfPlayerAfterShooting() {
    Player player = roomMaze.getPlayer();
    assertEquals(0, player.getArrowCount());
    assertEquals("GOLD",
            roomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals(1, player.getArrowCount());
  }
//
//  @Test
//  public void testGetGoldCountOfPLayerAfterRobbedByThief() {
//    roomMaze = new RoomMaze(2,5);
//    Player player = roomMaze.getPlayer();
//
//    roomMaze.setStartingPoint(0,0);
//    assertEquals(0, player.getGoldCount(),0.01);
//
//    assertEquals("GOLD",
//            roomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
//    roomMaze.makeMove(Direction.EAST);
//    assertEquals(1, player.getGoldCount(), 0.01);
//
//    assertEquals("BLANK",
//            roomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
//    roomMaze.makeMove(Direction.EAST);
//    assertEquals(1, player.getGoldCount(),0.01);
//
//    assertEquals("THIEF",
//            roomMaze.getNodeTypeList().get(new Pair<>(0,3)).toString());
//    roomMaze.makeMove(Direction.EAST);
//    assertEquals(0.9, player.getGoldCount(),0.01);
//  }


  @Test
  public void test() {
    AbstractMaze maze = new RoomMaze(4,4, 4, 1,
            0,0, 1, 10, 10 , 3);
    //EnumSet roomMaze.getPossibleMoves();
//      maze.makeMove(Direction.NORTH);
//      System.out.println(maze.getPlayer().getCurrentLocation());
//    maze.visualize();
    maze.shootArrow(Direction.NORTH, 1);
    System.out.println(maze.getPlayer().getArrowCount());
  }

  @Test
  public void testGetNumberOfPits() {
    roomMaze = new RoomMaze(4,4, 1, 1, 0,
            0,2, 30, 10, 3 );
    assertEquals(4, roomMaze.getNumberOfPits());
  }

  @Test
  public void testGetNumberOfBats() {
    roomMaze = new RoomMaze(4,4, 1, 1, 0,
            0,2, 20, 40, 3 );
    assertEquals(6, roomMaze.getNumberOfBats());
  }

  @Test
  public void testMakeMoveFromCaveToCave() {
    roomMaze = new RoomMaze(4,4, 1, 1, 0,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.EAST);
    assertEquals(new Pair<>(1, 1), roomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveFromCaveToTunnelTransportToTheNextCave() {
    roomMaze = new RoomMaze(4,4, 1, 3, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.EAST);
    assertEquals(new Pair<>(2, 1), roomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveToCaveWithBat() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals(new Pair<>(1, 1), roomMaze.getPlayer().getCurrentLocation());
  }


}