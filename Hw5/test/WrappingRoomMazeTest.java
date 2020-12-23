import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import model.algo.Direction;
import model.algo.NodeType;
import model.maze.AbstractMaze;
import model.maze.PlayerStatus;
import model.maze.WrappingRoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the wrapping room maze wumpus game.
 * */
public class WrappingRoomMazeTest {


  AbstractMaze wrappingRoomMaze;

  @Before
  public void setMazeGraph() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 1, 1,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRows() {

    wrappingRoomMaze = new WrappingRoomMaze(-1,3, 1,0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionColumns() {
    wrappingRoomMaze = new WrappingRoomMaze(2,-3, 1, 0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRemainingWalls() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, -1, 0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionRemainingWallsInvalid() {
    wrappingRoomMaze = new WrappingRoomMaze(2,2, 2, 0, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPitPercent() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 101, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPitPercent2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, -1, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionBatPercent() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 20, 101, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionBatPercent2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 20, -1, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionArrows() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, -3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPlayerRow() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 4, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPlayerRow2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, -2, 0,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPlayerColumn() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, -1,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionPlayerColumn2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 5,
            0,2, 20, 10, 3 );
  }

  @Test
  public void testGetStartingPointWrappingRoomMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    assertEquals("Pair[0,0]", wrappingRoomMaze.getStartingPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionWumpusRow() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            4,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionWumpusRow2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            -1,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionWumpusColumn() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,-1, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWrappingRoomMazeGraphConstructionThrowsExceptionWumpusColumn2() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,5, 20, 10, 3 );
  }

  @Test
  public void testGetGoalPointWrappingRoomMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1,0, 0,
            0,2, 20, 10, 3 );
    assertEquals("Pair[0,2]", wrappingRoomMaze.getGoalPoint().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGoalPointSameAsStartingPoint() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,0, 20, 10, 3 );
  }


  @Test
  public void testWrappingRoomMazeGraphConstruction() {
    assertEquals("AbstractMaze{numberOfRows=2, numberOfColumns=3, isWrapped=false, "
                    + "numberOfRemainingWalls=1, mazeGraph={Pair[0,0]=[Pair[1,0], Pair[0,1]],"
                    +
            " Pair[0,1]="
                    + "[Pair[0,0], Pair[1,1], Pair[0,2]], Pair[1,0]=[Pair[0,0], Pair[1,1]], "
            + "Pair[0,2]="
                    + "[Pair[0,1], Pair[1,2]], Pair[1,1]=[Pair[0,1], Pair[1,0]], "
            + "Pair[1,2]=[Pair[0,2]]}, "
                    + "startingPoint=Pair[1,1], goalPoint=Pair[0,2], "
            + "pitPercent=20.0, batPercent=10.0}",
            wrappingRoomMaze.toString());
  }


  @Test
  public void testGetNumberOfRowsWrappingRoomMaze() {
    assertEquals(2, wrappingRoomMaze.getNumberOfRows());
  }

  @Test
  public void testGetNumberOfColumnsWrappingRoomMaze() {
    assertEquals(3, wrappingRoomMaze.getNumberOfColumns());
  }

  @Test
  public void testGetNumberOfRemainingWallsWrappingRoomMaze() {
    assertEquals(1, wrappingRoomMaze.getNumberOfRemainingWalls());
  }

  @Test
  public void testGetPossibleMoves() {
    assertEquals("[NORTH, SOUTH, WEST]", wrappingRoomMaze.getPossibleMoves().toString());
  }

  @Test
  public void testGetNodeTypeList() {
    wrappingRoomMaze = new WrappingRoomMaze(2,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[1,2]=PIT}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testPitSetupInMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(3,3, 1, 0, 0,
            0,2, 20, 10, 3 );
    int pitCount = 0;
    for (NodeType nodeType: wrappingRoomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.PIT)) {
        pitCount++;
      }
    }
    int expectedGoldCount = (int)(0.20 * 3 * 3);
    assertEquals(expectedGoldCount, pitCount);
  }

  @Test
  public void testBatSetupInMaze() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 0,
            0,2, 20, 10, 3 );
    int batCount = 0;
    for (NodeType nodeType: wrappingRoomMaze.getNodeTypeList().values()) {
      if (nodeType.equals(NodeType.BAT)) {
        batCount++;
      }
    }
    int expectedThiefCount = (int)(0.10 * 4 * 4);
    assertEquals(expectedThiefCount, batCount);
  }

  @Test(expected =  IllegalArgumentException.class)
  public void testMakeMoveDisallowsInvalidDirections() {
    wrappingRoomMaze.makeMove(Direction.EAST);
  }

  @Test
  public void testGetCurrentLocationOfPLayer() {
    assertEquals("Pair[1,1]", wrappingRoomMaze.getPlayer().getCurrentLocation().toString());
  }


  @Test
  public void testGetArrowCountOfPLayer() {
    assertEquals(3, wrappingRoomMaze.getPlayer().getArrowCount());
  }

  @Test
  public void testGetArrowCountOfPlayerDecreasesAfterShooting() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    wrappingRoomMaze.shootArrow(Direction.EAST,2);
    assertEquals(0, wrappingRoomMaze.getPlayer().getArrowCount());
  }



  @Test
  public void testGetNumberOfPits() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 0,
            0,2, 30, 10, 3 );
    assertEquals(4, wrappingRoomMaze.getNumberOfPits());
  }

  @Test
  public void testGetNumberOfBats() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 0,
            0,2, 20, 40, 3 );
    assertEquals(6, wrappingRoomMaze.getNumberOfBats());
  }

  @Test
  public void testMakeMoveFromCaveToCave() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 0,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(new Pair<>(1, 1), wrappingRoomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveFromCaveToTunnelTransportToTheNextCave() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 3, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(new Pair<>(2, 1), wrappingRoomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveToCaveWithBatTransportsToAnotherLocation() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.SOUTH);
    assertEquals(new Pair<>(1, 1), wrappingRoomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveToCaveWithBatDoesNotTransportToAnotherLocation() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.SOUTH);
    assertEquals(new Pair<>(0, 2), wrappingRoomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testPlayerDiesInPit() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(PlayerStatus.DEAD, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test
  public void testPlayerDiesIfEntersRoomWithWumpus() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.makeMove(Direction.EAST);
    assertEquals(PlayerStatus.DEAD, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test
  public void testPlayerDiesWhenArrowCountBecomesZero() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    wrappingRoomMaze.shootArrow(Direction.EAST, 2);
    assertEquals(PlayerStatus.DEAD, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test
  public void testArrowPassesThroughTunnelWithoutBeingIncludedInTheDistance() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 0,
            0,1, 20, 10, 2);
    wrappingRoomMaze.shootArrow(Direction.NORTH, 1);
    assertEquals(PlayerStatus.WINNER, wrappingRoomMaze.getPlayer().getStatus());
  }


  @Test
  public void testPlayerWinsByShootingWumpus() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.shootArrow(Direction.EAST,1);
    assertEquals(PlayerStatus.WINNER, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootingarrowThrowsExceptionInvalidDistance() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    wrappingRoomMaze.shootArrow(Direction.EAST,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootingarrowThrowsExceptionNoArrows() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    wrappingRoomMaze.shootArrow(Direction.EAST,2);
    wrappingRoomMaze.shootArrow(Direction.EAST,2);
  }

  @Test
  public void testShootingarrowDoesNotKillWumpusIfDistanceNotExact() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 5 );
    wrappingRoomMaze.shootArrow(Direction.EAST,2);
    assertEquals(PlayerStatus.ALIVE, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test
  public void testFeelsDraftFromAdjacentPit() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 0,
            0,2, 20, 10, 5 );
    assertEquals("You feel a draft ", wrappingRoomMaze.makeMove(Direction.EAST));
  }

  @Test
  public void testDoesNotFeelsDraftIfNoAdjacentPit() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 5 );
    assertEquals("", wrappingRoomMaze.makeMove(Direction.WEST));
  }

  @Test
  public void testSmellsWumpusFromAdjacentWumpus() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1, 1,
            0,2, 20, 10, 5 );
    assertEquals("You smell a Wumpus! ", wrappingRoomMaze.makeMove(Direction.NORTH));
  }

  @Test
  public void testDoesNotSmellsWumpusIfNoAdjacentWumpus() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 3, 1,
            0,2, 20, 10, 5 );
    assertEquals("", wrappingRoomMaze.makeMove(Direction.NORTH));
  }

  @Test
  public void testArrowTravelsInStraightLineInCave() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 3, 1,
            1,1, 20, 10, 5 );
    assertEquals("Hee hee hee, you got the wumpus!\nNext time you won't be so lucky",
            wrappingRoomMaze.shootArrow(Direction.NORTH, 2));
    assertEquals(PlayerStatus.WINNER, wrappingRoomMaze.getPlayer().getStatus());
  }

  @Test
  public void testArrowTravelsInCrookedMannerInTunnel() {
    wrappingRoomMaze = new WrappingRoomMaze(4,4, 1, 1,0,
            0,1, 20, 10, 5 );
    assertEquals("Hee hee hee, you got the wumpus!\nNext time you won't be so lucky",
            wrappingRoomMaze.shootArrow(Direction.NORTH, 1));
    assertEquals(PlayerStatus.WINNER, wrappingRoomMaze.getPlayer().getStatus());
  }








}