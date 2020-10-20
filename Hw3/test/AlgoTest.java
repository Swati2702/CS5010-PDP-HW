import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import algo.MazeGraph;
import algo.ModifiedKruskal;

import static org.junit.Assert.assertEquals;

/**
 * Tests for algorithms.
 * */
public class AlgoTest {

  MazeGraph perfectMaze;
  MazeGraph roomMaze;
  MazeGraph wrappingRoomMaze;
  ModifiedKruskal modifiedKruskal;

  @Before
  public void setMazeGraph() {
    perfectMaze = new MazeGraph(2, 2, false);
    roomMaze = new MazeGraph(2, 2, false);
    wrappingRoomMaze = new MazeGraph(2, 2, true);
    modifiedKruskal = new ModifiedKruskal();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPerfectMazeGraphConstructionThrowsExceptionRows() {
    perfectMaze = new MazeGraph(-1, 2, false);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPerfectMazeGraphConstructionThrowsExceptionColumns() {
    perfectMaze = new MazeGraph(1, -2, false);
  }

  @Test
  public void testPerfectMazeGraphConstruction() {
    assertEquals("MazeGraph{rows=2, columns=2, isWrapped=false, adjList={Pair[0,0]="
            + "[Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[1,1], Pair[0,0]], Pair[1,0]=[Pair[1,1], "
            + "Pair[0,0]], Pair[1,1]=[Pair[0,1], Pair[1,0]]}}", perfectMaze.toString());
  }

  @Test
  public void testRoomMazeGraphConstruction() {
    assertEquals("MazeGraph{rows=2, columns=2, isWrapped=false, adjList={Pair[0,0]="
            + "[Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[1,1], Pair[0,0]], Pair[1,0]=[Pair[1,1], "
            + "Pair[0,0]], Pair[1,1]=[Pair[0,1], Pair[1,0]]}}", roomMaze.toString());
  }

  @Test
  public void testWrappingRoomMazeGraphConstruction() {
    assertEquals("MazeGraph{rows=2, columns=2, isWrapped=true, adjList={Pair[0,0]="
            + "[Pair[1,0], Pair[0,1], Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[1,1], Pair[0,0], "
            + "Pair[1,1], Pair[0,0]], Pair[1,0]=[Pair[0,0], Pair[1,1], Pair[0,0], Pair[1,1]], "
            + "Pair[1,1]=[Pair[0,1], Pair[1,0], Pair[0,1], Pair[1,0]]}}",
            wrappingRoomMaze.toString());
  }

  @Test
  public void testModifiedKruskalPerfectMazeConstruction() {
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    assertEquals("{Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[0,0], Pair[1,1]], "
            + "Pair[1,0]=[Pair[0,0]], Pair[1,1]=[Pair[0,1]]}" ,
            perfectMaze.getMazeGraphAdjacencyList().toString());
  }

  @Test
  public void testModifiedKruskalRoomMazeConstruction() {
    modifiedKruskal.createMazeWithKruskal(roomMaze, 1);
    assertEquals("{Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[0,0], Pair[1,1]], "
                    + "Pair[1,0]=[Pair[0,0]], Pair[1,1]=[Pair[0,1]]}" ,
            roomMaze.getMazeGraphAdjacencyList().toString());
  }

  @Test
  public void testModifiedKruskalWrappingRoomMazeConstruction() {
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 1);
    assertEquals("{Pair[0,0]=[Pair[1,0], Pair[0,1]], Pair[0,1]=[Pair[0,0], Pair[1,1]], "
            + "Pair[1,0]=[Pair[0,0], Pair[1,1]], Pair[1,1]=[Pair[0,1], Pair[1,0]]}" ,
            wrappingRoomMaze.getMazeGraphAdjacencyList().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifiedKruskalWrappingRoomMazeConstructionThrowsException() {
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignGoldThrowsException() {
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    perfectMaze.assignGold(-20);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignGoldThrowsException2() {
    modifiedKruskal.createMazeWithKruskal(roomMaze, 1);
    roomMaze.assignGold(-20);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testAssignGoldThrowsException3() {
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 1);
    wrappingRoomMaze.assignGold(-20);

  }

  @Test
  public void testAssignGoldPerfectMaze() {
    perfectMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    perfectMaze.assignGold(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
            + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, Pair[2,2]=BLANK}",
            perfectMaze.getNodeTypeList().toString());
  }

  @Test
  public void testAssignGoldRoomMaze() {
    roomMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(roomMaze, 2);
    roomMaze.assignGold(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
            + "Pair[2,2]=BLANK}",
            roomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testAssignGoldWrappingRoomMaze() {
    wrappingRoomMaze = new MazeGraph(3, 3, true);
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 2);
    wrappingRoomMaze.assignGold(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=GOLD, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
                    + "Pair[2,2]=BLANK}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testAssignThiefPerfectMaze() {
    perfectMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    perfectMaze.assignThief(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=THIEF, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
            + "Pair[2,2]=BLANK}",
            perfectMaze.getNodeTypeList().toString());
  }

  @Test
  public void testAssignThiefRoomMaze() {
    roomMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(roomMaze, 2);
    roomMaze.assignThief(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=THIEF, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
                    + "Pair[2,2]=BLANK}",
            roomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testAssignThiefWrappingRoomMaze() {
    wrappingRoomMaze = new MazeGraph(3, 3, true);
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 2);
    wrappingRoomMaze.assignThief(20);
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[0,2]=BLANK, "
                    + "Pair[1,1]=BLANK, Pair[2,0]=THIEF, Pair[1,2]=BLANK, Pair[2,1]=BLANK, "
                    + "Pair[2,2]=BLANK}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPossibleMovesThrowsExceptionForInvalidRow() {
    Pair<Integer, Integer> loc = new Pair<>(4,2);
    perfectMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    perfectMaze.getPossibleMoves(loc);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPossibleMovesThrowsExceptionForInvalidColumn() {
    Pair<Integer, Integer> loc = new Pair<>(1,5);
    perfectMaze = new MazeGraph(3, 3, false);
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);
    perfectMaze.getPossibleMoves(loc);
  }

  @Test
  public void testGetPossibleMovesPerfectMaze() {
    Pair<Integer, Integer> loc = new Pair<>(1,0);
    modifiedKruskal.createMazeWithKruskal(perfectMaze, 1);

    assertEquals("[NORTH]", perfectMaze.getPossibleMoves(loc).toString());
  }

  @Test
  public void testGetPossibleMovesRoomMaze() {
    Pair<Integer, Integer> loc = new Pair<>(1,0);
    modifiedKruskal.createMazeWithKruskal(roomMaze, 1);

    assertEquals("[NORTH]", roomMaze.getPossibleMoves(loc).toString());
  }

  @Test
  public void testGetPossibleMovesWrappingRoomMaze() {
    Pair<Integer, Integer> loc = new Pair<>(1,1);
    wrappingRoomMaze = new MazeGraph(2, 2, true);
    modifiedKruskal.createMazeWithKruskal(wrappingRoomMaze, 1);

    assertEquals("[NORTH, SOUTH, EAST, WEST]",
            wrappingRoomMaze.getPossibleMoves(loc).toString());
  }

  @Test
  public void testGetNumberOfNodesPerfectMaze() {
    assertEquals(4, perfectMaze.getNumberOfNodes());
  }

  @Test
  public void testGetNumberOfNodesRoomMaze() {
    assertEquals(4, roomMaze.getNumberOfNodes());
  }

  @Test
  public void testGetNumberOfNodesWrappingRoomMaze() {
    assertEquals(4, wrappingRoomMaze.getNumberOfNodes());
  }

  @Test
  public void testGetNodeTypeListWrappingRoomMaze() {
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[1,1]=BLANK}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testGetNodeTypeListRoomMaze() {
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[1,1]=BLANK}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

  @Test
  public void testGetNodeTypeListPerfectMaze() {
    assertEquals("{Pair[0,0]=BLANK, Pair[0,1]=BLANK, Pair[1,0]=BLANK, Pair[1,1]=BLANK}",
            wrappingRoomMaze.getNodeTypeList().toString());
  }

}