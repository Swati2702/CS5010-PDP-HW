import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import model.algo.Direction;
import model.algo.NodeType;
import model.maze.AbstractMaze;
import model.maze.PlayerStatus;
import model.maze.RoomMaze;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the room maze wumpus game.
 * */
public class RoomMazeTest {


  AbstractMaze roomMaze;

  @Before
  public void setMazeGraph() {
    roomMaze = new RoomMaze(2,3, 1,
            1, 1,
            0,2, 20, 10, 3 );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRoomMazeGraphConstructionThrowsExceptionRows() {

    roomMaze = new RoomMaze(-1,3, 1,
            0, 0,
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
    assertEquals("Pair[1,1]", roomMaze.getPlayer().getCurrentLocation().toString());
  }




  @Test
  public void testGetArrowCountOfPLayer() {
    assertEquals(3, roomMaze.getPlayer().getArrowCount());
  }

  @Test
  public void testGetArrowCountOfPlayerDecreasesAfterShooting() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    roomMaze.shootArrow(Direction.EAST,2);
    assertEquals(0, roomMaze.getPlayer().getArrowCount());
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
  public void testMakeMoveToCaveWithBatTransportsToAnotherLocation() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals(new Pair<>(1, 1), roomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testMakeMoveToCaveWithBatDoesNotTransportToAnotherLocation() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.SOUTH);
    assertEquals(new Pair<>(0, 2), roomMaze.getPlayer().getCurrentLocation());
  }

  @Test
  public void testPlayerDiesInPit() {
    roomMaze = new RoomMaze(4,4, 1, 1, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.EAST);
    assertEquals(PlayerStatus.DEAD, roomMaze.getPlayer().getStatus());
  }

  @Test
  public void testPlayerDiesIfEntersRoomWithWumpus() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.makeMove(Direction.EAST);
    assertEquals(PlayerStatus.DEAD, roomMaze.getPlayer().getStatus());
  }

  @Test
  public void testPlayerDiesWhenArrowCountBecomesZero() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    roomMaze.shootArrow(Direction.EAST, 2);
    assertEquals(PlayerStatus.DEAD, roomMaze.getPlayer().getStatus());
  }

  @Test
  public void testArrowPassesThroughTunnelWithoutBeingIncludedInTheDistance() {
    roomMaze = new RoomMaze(4,4, 1, 1, 0,
            0,1, 20, 10, 2);
    roomMaze.shootArrow(Direction.NORTH, 1);
    assertEquals(PlayerStatus.WINNER, roomMaze.getPlayer().getStatus());
  }


  @Test
  public void testPlayerWinsByShootingWumpus() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.shootArrow(Direction.EAST,1);
    assertEquals(PlayerStatus.WINNER, roomMaze.getPlayer().getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootingarrowThrowsExceptionInvalidDistance() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 3 );
    roomMaze.shootArrow(Direction.EAST,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testShootingarrowThrowsExceptionNoArrows() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 1 );
    roomMaze.shootArrow(Direction.EAST,2);
    roomMaze.shootArrow(Direction.EAST,2);
  }

  @Test
  public void testShootingarrowDoesNotKillWumpusIfDistanceNotExact() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 5 );
    roomMaze.shootArrow(Direction.EAST,2);
    assertEquals(PlayerStatus.ALIVE, roomMaze.getPlayer().getStatus());
  }

  @Test
  public void testFeelsDraftFromAdjacentPit() {
    roomMaze = new RoomMaze(4,4, 1, 1, 0,
            0,2, 20, 10, 5 );
    assertEquals("You feel a draft ", roomMaze.makeMove(Direction.EAST));
  }

  @Test
  public void testDoesNotFeelsDraftIfNoAdjacentPit() {
    roomMaze = new RoomMaze(4,4, 1, 0, 1,
            0,2, 20, 10, 5 );
    assertEquals("", roomMaze.makeMove(Direction.WEST));
  }

  @Test
  public void testSmellsWumpusFromAdjacentWumpus() {
    roomMaze = new RoomMaze(4,4, 1, 1, 1,
            0,2, 20, 10, 5 );
    assertEquals("You smell a Wumpus! ", roomMaze.makeMove(Direction.NORTH));
  }

  @Test
  public void testDoesNotSmellsWumpusIfNoAdjacentWumpus() {
    roomMaze = new RoomMaze(4,4, 1, 3, 1,
            0,2, 20, 10, 5 );
    assertEquals("", roomMaze.makeMove(Direction.NORTH));
  }

  @Test
  public void testArrowTravelsInStraightLineInCave() {
    roomMaze = new RoomMaze(4,4, 1, 3, 1,
            1,1, 20, 10, 5 );
    assertEquals("Hee hee hee, you got the wumpus!\nNext time you won't be so lucky",
            roomMaze.shootArrow(Direction.NORTH, 2));
    assertEquals(PlayerStatus.WINNER, roomMaze.getPlayer().getStatus());
  }

  @Test
  public void testArrowTravelsInCrookedMannerInTunnel() {
    roomMaze = new RoomMaze(4,4, 1, 1,0,
            0,1, 20, 10, 5 );
    assertEquals("Hee hee hee, you got the wumpus!\nNext time you won't be so lucky",
            roomMaze.shootArrow(Direction.NORTH, 1));
    assertEquals(PlayerStatus.WINNER, roomMaze.getPlayer().getStatus());
  }








}