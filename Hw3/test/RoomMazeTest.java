import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import algo.Direction;
import algo.NodeType;
import maze.Player;
import maze.RoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the room maze.
 * */
public class RoomMazeTest {

  RoomMaze roomMaze;

  @Before
  public void setMazeGraph() {
    roomMaze = new RoomMaze(2, 2, 1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRows() {
    roomMaze = new RoomMaze(-1, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionColumns() {
    roomMaze = new RoomMaze(1, -2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRemainingWalls() {
    roomMaze = new RoomMaze(1, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRemainingWallsInvalid() {
    roomMaze = new RoomMaze(2, 2, 2);
  }

  @Test
  public void testRoomMazeGraphConstruction() {
    assertEquals("AbstractMaze{numberOfRows=2, numberOfColumns=2, isWrapped=false, "
            + "numberOfRemainingWalls=1, mazeGraph={Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]="
            + "[Pair[0,0], Pair[1,1]], Pair[1,0]=[Pair[0,0]], Pair[1,1]=[Pair[0,1]]}, "
            + "startingPoint=null, goalPoint=null}", roomMaze.toString());
  }

  @Test
  public void testGetNumberOfRowsRoomMaze() {
    assertEquals(2, roomMaze.getNumberOfRows());
  }

  @Test
  public void testGetNumberOfColumnsRoomMaze() {
    assertEquals(2, roomMaze.getNumberOfColumns());
  }


  @Test
  public void testisWrappedRoomMaze() {
    assertEquals(false, roomMaze.isWrapped());
  }

  @Test
  public void testGetNumberOfRemainingWallsRoomMaze() {
    assertEquals(1, roomMaze.getNumberOfRemainingWalls());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointRoomMazeRow() {
    roomMaze.setStartingPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointRoomMazeRow2() {
    roomMaze.setStartingPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointRoomMazeColumn() {
    roomMaze.setStartingPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointRoomMazeColumn2() {
    roomMaze.setStartingPoint(0,-2);
  }

  @Test
  public void testGetStartingPointRoomMaze() {
    roomMaze.setStartingPoint(0,1);
    assertEquals("Pair[0,1]", roomMaze.getStartingPoint().toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointRoomMazeRow() {
    roomMaze.setGoalPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointRoomMazeRow2() {
    roomMaze.setGoalPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointRoomMazeColumn() {
    roomMaze.setGoalPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointRoomMazeColumn2() {
    roomMaze.setGoalPoint(0,-2);
  }

  @Test
  public void testGetGoalPointRoomMaze() {
    roomMaze.setGoalPoint(0,1);
    assertEquals("Pair[0,1]", roomMaze.getGoalPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointSameAsStartingPoint() {
    roomMaze.setStartingPoint(0,1);
    roomMaze.setGoalPoint(0,1);
  }

  @Test
  public void testGetPossibleMoves() {
    roomMaze.setStartingPoint(1,1);
    assertEquals("[NORTH]", roomMaze.getPossibleMoves().toString());
  }

  @Test
  public void testGetNodeTypeList() {
    roomMaze = new RoomMaze(3,3, 1);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
            + "Pair[2,2]=BLANK}" , roomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testGoldSetupInMaze() {
    roomMaze = new RoomMaze(3,3, 1);
    int goldCount = 0;
    for (NodeType nodeType: roomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.GOLD)) {
        goldCount++;
      }
    }
    int expectedGoldCount = (int)(0.20 * 3 * 3);
    assertEquals(expectedGoldCount, goldCount);
  }

  @Test
  public void testThiefSetupInMaze() {
    roomMaze = new RoomMaze(4,4, 3);
    int thiefCount = 0;
    for (NodeType nodeType: roomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.THIEF)) {
        thiefCount++;
      }
    }
    int expectedThiefCount = (int)(0.10 * 4 * 4);
    assertEquals(expectedThiefCount, thiefCount);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testMakeMoveDisallowsInvalidDirections() {
    roomMaze.setStartingPoint(1,1);
    roomMaze.makeMove(Direction.EAST);
  }

  @Test
  public void testGetCurrentLocationOfPLayer() {
    Player player = roomMaze.getPlayer();
    roomMaze.setStartingPoint(0,0);
    assertEquals("Pair[0,0]", player.getCurrentLocation().toString());
    roomMaze.makeMove(Direction.EAST);
    assertEquals("Pair[0,1]", player.getCurrentLocation().toString());
  }


  @Test
  public void testGetGoldRemovedFromRoomAfterCollection() {
    roomMaze = new RoomMaze(3,3, 3);
    Player player = roomMaze.getPlayer();
    roomMaze.setStartingPoint(1,0);
    assertEquals("GOLD",
            roomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals("BLANK",
            roomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());

  }

  @Test
  public void testGetGoldCountOfPLayer() {
    Player player = roomMaze.getPlayer();
    roomMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    roomMaze.makeMove(Direction.EAST);
    assertEquals(0, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterCollectingGold() {
    roomMaze = new RoomMaze(3,3, 3);
    Player player = roomMaze.getPlayer();
    roomMaze.setStartingPoint(1,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    assertEquals("GOLD",
            roomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals(1, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterRobbedByThief() {
    roomMaze = new RoomMaze(2,5, 3);
    Player player = roomMaze.getPlayer();

    roomMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(),0.01);

    assertEquals("GOLD",
            roomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    roomMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(), 0.01);

    assertEquals("BLANK",
            roomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    roomMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(),0.01);

    assertEquals("THIEF",
            roomMaze.getNodeTypeList().get(new Pair<>(0,3)).toString());
    roomMaze.makeMove(Direction.EAST);
    assertEquals(0.9, player.getGoldCount(),0.01);
  }



}
