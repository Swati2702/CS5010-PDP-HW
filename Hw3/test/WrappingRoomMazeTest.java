import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import algo.Direction;
import algo.NodeType;
import maze.Player;
import maze.WrappingRoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the wrapping room maze.
 * */
public class WrappingRoomMazeTest {
  WrappingRoomMaze wrappingRoomMaze;

  @Before
  public void setMazeGraph() {
    wrappingRoomMaze = new WrappingRoomMaze(2,2,1);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRows() {
    wrappingRoomMaze = new WrappingRoomMaze(-1, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionColumns() {
    wrappingRoomMaze = new WrappingRoomMaze(1, -2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRemainingWalls() {
    wrappingRoomMaze = new WrappingRoomMaze(1, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRemainingWallsInvalid() {
    wrappingRoomMaze = new WrappingRoomMaze(2, 2, 5);
  }

  @Test
  public void testWrappingRoomMazeGraphConstruction() {
    assertEquals("AbstractMaze{numberOfRows=2, numberOfColumns=2, isWrapped=true, "
            + "numberOfRemainingWalls=1, mazeGraph={Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]="
            + "[Pair[0,0], Pair[1,1]], Pair[1,0]=[Pair[0,0], Pair[1,1]], Pair[1,1]=[Pair[0,1], "
            + "Pair[1,0]]}, startingPoint=null, goalPoint=null}", wrappingRoomMaze.toString());
  }



  @Test
  public void testGetNumberOfRowsWrappingRoomMaze() {
    assertEquals(2, wrappingRoomMaze.getNumberOfRows());
  }

  @Test
  public void testGetNumberOfColumnsWrappingRoomMaze() {
    assertEquals(2, wrappingRoomMaze.getNumberOfColumns());
  }


  @Test
  public void testisWrappedWrappingRoomMaze() {
    assertEquals(true, wrappingRoomMaze.isWrapped());
  }

  @Test
  public void testGetNumberOfRemainingWallsWrappingRoomMaze() {
    assertEquals(1, wrappingRoomMaze.getNumberOfRemainingWalls());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointWrappingRoomMazeRow() {
    wrappingRoomMaze.setStartingPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointWrappingRoomMazeRow2() {
    wrappingRoomMaze.setStartingPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointWrappingRoomMazeColumn() {
    wrappingRoomMaze.setStartingPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointWrappingRoomMazeColumn2() {
    wrappingRoomMaze.setStartingPoint(0,-2);
  }

  @Test
  public void testGetStartingPointWrappingRoomMaze() {
    wrappingRoomMaze.setStartingPoint(0,1);
    assertEquals("Pair[0,1]", wrappingRoomMaze.getStartingPoint().toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointWrappingRoomMazeRow() {
    wrappingRoomMaze.setGoalPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointWrappingRoomMazeRow2() {
    wrappingRoomMaze.setGoalPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointWrappingRoomMazeColumn() {
    wrappingRoomMaze.setGoalPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointWrappingRoomMazeColumn2() {
    wrappingRoomMaze.setGoalPoint(0,-2);
  }

  @Test
  public void testGetGoalPointWrappingRoomMaze() {
    wrappingRoomMaze.setGoalPoint(0,1);
    assertEquals("Pair[0,1]", wrappingRoomMaze.getGoalPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointSameAsStartingPoint() {
    wrappingRoomMaze.setStartingPoint(0,1);
    wrappingRoomMaze.setGoalPoint(0,1);
  }

  @Test
  public void testGetPossibleMoves() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3,3);
    wrappingRoomMaze.setStartingPoint(2,0);
    assertEquals("[NORTH, SOUTH, EAST]", wrappingRoomMaze.getPossibleMoves().toString());
  }

  @Test
  public void testGetNodeTypeList() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3, 1);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
            + "Pair[2,2]=BLANK}"
            , wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testGoldSetupInMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3, 1);
    int goldCount = 0;
    for (NodeType nodeType: wrappingRoomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.GOLD)) {
        goldCount++;
      }
    }
    int expectedGoldCount = (int)(0.20 * 3 * 3);
    assertEquals(expectedGoldCount, goldCount);
  }

  @Test
  public void testThiefSetupInMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 2);
    int thiefCount = 0;
    for (NodeType nodeType: wrappingRoomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.THIEF)) {
        thiefCount++;
      }
    }
    int expectedThiefCount = (int)(0.10 * 4 * 4);
    assertEquals(expectedThiefCount, thiefCount);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testMakeMoveDisallowsInvalidDirections() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3,3);
    wrappingRoomMaze.setStartingPoint(2,0);
    wrappingRoomMaze.makeMove(Direction.WEST);
  }

  @Test
  public void testGetCurrentLocationOfPLayer() {
    Player player = wrappingRoomMaze.getPlayer();
    wrappingRoomMaze.setStartingPoint(0,0);
    assertEquals("Pair[0,0]", player.getCurrentLocation().toString());
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals("Pair[0,1]", player.getCurrentLocation().toString());
  }


  @Test
  public void testGetGoldRemovedFromRoomAfterCollection() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3, 3);
    Player player = wrappingRoomMaze.getPlayer();
    wrappingRoomMaze.setStartingPoint(1,0);
    assertEquals("GOLD",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    wrappingRoomMaze.makeMove(Direction.SOUTH);
    assertEquals("BLANK",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());

  }

  @Test
  public void testGetGoldCountOfPLayer() {
    Player player = wrappingRoomMaze.getPlayer();
    wrappingRoomMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(0, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterCollectingGold() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3, 3);
    Player player = wrappingRoomMaze.getPlayer();
    wrappingRoomMaze.setStartingPoint(1,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    assertEquals("GOLD",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    wrappingRoomMaze.makeMove(Direction.SOUTH);
    assertEquals(1, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterRobbedByThief() {
    wrappingRoomMaze = new WrappingRoomMaze(2,5, 3);
    Player player = wrappingRoomMaze.getPlayer();

    wrappingRoomMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(),0.01);

    assertEquals("GOLD",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(), 0.01);

    assertEquals("BLANK",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(),0.01);

    assertEquals("THIEF",
            wrappingRoomMaze.getNodeTypeList().get(new Pair<>(0,3)).toString());
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(0.9, player.getGoldCount(),0.01);
  }

}
