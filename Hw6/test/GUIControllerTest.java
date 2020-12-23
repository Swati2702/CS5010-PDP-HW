import com.sun.tools.javac.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import controller.GUIController;
import controller.GameController;
import model.algo.Direction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * Tests for GUI controller.
 * */
public class GUIControllerTest {
  GameController controller;

  @Before
  public void setUp() {
    controller = new GUIController();
  }

  @Test
  public void testPlayGameTwoPlayer() {
    controller.playGame(5, 5, true,  5, 5,
            5,5,2);
    assertEquals(2, controller.getPlayers().size());
  }

  @Test
  public void testPlayGameSinglePlayer() {
    controller.playGame(5, 5, true, 5, 5,
            5,5,1);
    assertEquals(1, controller.getPlayers().size());
  }

  @Test
  public void testPlayGameSinglePlayerLocation() {
    controller.playGame(5, 5, true,5, 5,
            5,5,1);
    assertNotNull(controller.getCurrentPlayer().getCurrentLocation());
  }

  @Test
  public void testSwitchPlayer() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertEquals(0, controller.getCurrentPlayerNumber());
    controller.switchPlayer();
    assertEquals(1, controller.getCurrentPlayerNumber());
  }

  @Test
  public void testPlayGameTwoPlayerLocation() {
    controller.playGame(5, 5, true,5, 5,
            5,5,2);
    assertNotNull(controller.getCurrentPlayer().getCurrentLocation());
    controller.switchPlayer();
    assertNotNull(controller.getCurrentPlayer().getCurrentLocation());
  }

  @Test
  public void testStopGame() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertFalse(controller.stopGame());
  }

  @Test
  public void testCurrentPlayer() {
    controller.playGame(5, 5, true,5, 5,
            5,5,2);
    assertEquals(0, controller.getCurrentPlayerNumber());
  }


  @Test
  public void testShootArrow() {
    controller.playGame(5, 5, true,5, 5,
            5,5,2);
    assertNotNull( controller.shootArrow(Direction.NORTH, 2));
  }

  @Test
  public void testShootArrowAfterSwitch() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertNotNull( controller.shootArrow(Direction.NORTH, 2));
    controller.switchPlayer();
    assertNotNull( controller.shootArrow(Direction.SOUTH, 2));
  }

  @Test
  public void testMakeMove() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertNotNull( controller.makeMove(Direction.NORTH));
  }

  @Test
  public void testMakeMoveAfterSwitch() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertNotNull( controller.makeMove(Direction.NORTH));
    controller.switchPlayer();
    assertNotNull( controller.makeMove(Direction.NORTH));
  }

  @Test
  public void testGetNextLocation() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    assertEquals("Pair[4,0]",
            controller.getNextLocation(new Pair<>(0,0), Direction.NORTH).toString());
  }

  @Test
  public void testGetPossibleMoves() {
    controller.playGame(5, 5, true,5, 5,
            5,5,2);
    assertEquals( "[NORTH, SOUTH, EAST, WEST]",
            controller.getPossibleMoves(new Pair<>(1, 2)).toString());
  }

  @Test
  public void testResetGame() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    controller.resetGame();
    assertNotNull(controller.getPlayers());
    assertEquals(2, controller.getPlayers().size());
  }

  @Test
  public void testNewGame() {
    controller.playGame(5, 5,true, 5, 5,
            5,5,2);
    controller.newGame();
    assertNotNull(controller.getPlayers());
    assertEquals(2, controller.getPlayers().size());
  }

  @Test(expected = IllegalCallerException.class)
  public void testPlayGameException() throws IOException {
    controller.playGame();
  }



}
