import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import algo.Direction;
import algo.NodeType;
import maze.Maze;
import maze.PerfectMaze;
import maze.Player;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the perfect maze.
 * */
public class PerfectMazeTest {


  PerfectMaze perfectMaze;
  Maze maze;

  @Before
  public void setMazeGraph() {
    maze = new PerfectMaze(2,3);
    perfectMaze = new PerfectMaze(2, 2 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPerfectMazeGraphConstructionThrowsExceptionRows() {

    perfectMaze = new PerfectMaze(-1, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPerfectMazeGraphConstructionThrowsExceptionColumns() {
    perfectMaze = new PerfectMaze(1, -2);
  }


  @Test
  public void testPerfectMazeGraphConstruction() {
    assertEquals("AbstractMaze{numberOfRows=2, numberOfColumns=2, isWrapped=false, "
            + "numberOfRemainingWalls=1, mazeGraph={Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]="
            + "[Pair[0,0], Pair[1,1]], Pair[1,0]=[Pair[0,0]], Pair[1,1]=[Pair[0,1]]}, "
            + "startingPoint=null, goalPoint=null}", perfectMaze.toString());
  }


  @Test
  public void testGetNumberOfRowsPerfectMaze() {
    assertEquals(2, perfectMaze.getNumberOfRows());
  }

  @Test
  public void testGetNumberOfColumnsPerfectMaze() {
    assertEquals(2, perfectMaze.getNumberOfColumns());
  }

  @Test
  public void testisWrappedPerfectMaze() {
    assertEquals(false, perfectMaze.isWrapped());
  }

  @Test
  public void testGetNumberOfRemainingWallsPerfectMaze() {
    assertEquals(1, perfectMaze.getNumberOfRemainingWalls());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointPerfectMazeRow() {
    perfectMaze.setStartingPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointPerfectMazeRow2() {
    perfectMaze.setStartingPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointPerfectMazeColumn() {
    perfectMaze.setStartingPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetStartingPointPerfectMazeColumn2() {
    perfectMaze.setStartingPoint(0,-2);
  }

  @Test
  public void testGetStartingPointPerfectMaze() {
    perfectMaze.setStartingPoint(0,1);
    assertEquals("Pair[0,1]", perfectMaze.getStartingPoint().toString());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointPerfectMazeRow() {
    perfectMaze.setGoalPoint(4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointPerfectMazeRow2() {
    perfectMaze.setGoalPoint(-4,0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointPerfectMazeColumn() {
    perfectMaze.setGoalPoint(0,5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointPerfectMazeColumn2() {
    perfectMaze.setGoalPoint(0,-2);
  }

  @Test
  public void testGetGoalPointPerfectMaze() {
    perfectMaze.setGoalPoint(0,1);
    assertEquals("Pair[0,1]", perfectMaze.getGoalPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointSameAsStartingPoint() {
    perfectMaze.setStartingPoint(0,1);
    perfectMaze.setGoalPoint(0,1);
  }

  @Test
  public void testGetPossibleMoves() {
    perfectMaze.setStartingPoint(1,1);
    assertEquals("[NORTH]", perfectMaze.getPossibleMoves().toString());
  }

  @Test
  public void testGetNodeTypeList() {
    perfectMaze = new PerfectMaze(3,3);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
            + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, Pair[2,2]=BLANK}",
            perfectMaze.getNodeTypeList().toString());
  }

  @Test
  public void testGoldSetupInMaze() {
    perfectMaze = new PerfectMaze(3,3);
    int goldCount = 0;
    for (NodeType nodeType: perfectMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.GOLD)) {
        goldCount++;
      }
    }
    int expectedGoldCount = (int)(0.20 * 3 * 3);
    assertEquals(expectedGoldCount, goldCount);
  }

  @Test
  public void testThiefSetupInMaze() {
    perfectMaze = new PerfectMaze(4,4);
    int thiefCount = 0;
    for (NodeType nodeType: perfectMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.THIEF)) {
        thiefCount++;
      }
    }
    int expectedThiefCount = (int)(0.10 * 4 * 4);
    assertEquals(expectedThiefCount, thiefCount);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testMakeMoveDisallowsInvalidDirections() {
    perfectMaze.setStartingPoint(1,1);
    perfectMaze.makeMove(Direction.EAST);
  }

  @Test
  public void testGetCurrentLocationOfPLayer() {
    Player player = perfectMaze.getPlayer();
    perfectMaze.setStartingPoint(0,0);
    assertEquals("Pair[0,0]", player.getCurrentLocation().toString());
    perfectMaze.makeMove(Direction.EAST);
    assertEquals("Pair[0,1]", player.getCurrentLocation().toString());
  }


  @Test
  public void testGetGoldRemovedFromRoomAfterCollection() {
    perfectMaze = new PerfectMaze(3,3);
    Player player = perfectMaze.getPlayer();
    perfectMaze.setStartingPoint(1,0);
    assertEquals("GOLD",
            perfectMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    perfectMaze.makeMove(Direction.SOUTH);
    assertEquals("BLANK",
            perfectMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());

  }

  @Test
  public void testGetGoldCountOfPLayer() {
    Player player = perfectMaze.getPlayer();
    perfectMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    perfectMaze.makeMove(Direction.EAST);
    assertEquals(0, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterCollectingGold() {
    perfectMaze = new PerfectMaze(3,3);
    Player player = perfectMaze.getPlayer();
    perfectMaze.setStartingPoint(1,0);
    assertEquals(0, player.getGoldCount(), 0.01);
    assertEquals("GOLD",
            perfectMaze.getNodeTypeList().get(new Pair<>(2,0)).toString());
    perfectMaze.makeMove(Direction.SOUTH);
    assertEquals(1, player.getGoldCount(), 0.01);
  }

  @Test
  public void testGetGoldCountOfPLayerAfterRobbedByThief() {
    perfectMaze = new PerfectMaze(2,5);
    Player player = perfectMaze.getPlayer();

    perfectMaze.setStartingPoint(0,0);
    assertEquals(0, player.getGoldCount(),0.01);

    assertEquals("GOLD",
            perfectMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    perfectMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(), 0.01);

    assertEquals("BLANK",
            perfectMaze.getNodeTypeList().get(new Pair<>(0,1)).toString());
    perfectMaze.makeMove(Direction.EAST);
    assertEquals(1, player.getGoldCount(),0.01);

    assertEquals("THIEF",
            perfectMaze.getNodeTypeList().get(new Pair<>(0,3)).toString());
    perfectMaze.makeMove(Direction.EAST);
    assertEquals(0.9, player.getGoldCount(),0.01);
  }




}