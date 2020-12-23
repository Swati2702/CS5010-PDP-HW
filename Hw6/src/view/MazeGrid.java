package view;

import com.sun.tools.javac.util.Pair;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.List;

import java.util.EnumSet;
import java.util.Random;


import controller.GUIController;
import model.algo.Direction;
import model.algo.MazeGraph;
import model.algo.NodeType;
import model.maze.PlayerStatus;


/**
 * Maze Grid handles the game page where actual playing in maze occurs.
 * */
public class MazeGrid extends JPanel {
  private final int cols;
  private Border border = BorderFactory.createLineBorder(Color.BLUE, 2);

  /**
   * Creates the mze grid on screen.
   * */
  public MazeGrid(Container contentPane, int rows, int cols, GUIController controller) {
    contentPane.removeAll();
    this.cols = cols;
    GridLayout gridLayout = new GridLayout(rows , cols);
    setLayout(gridLayout);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        JCell jCell = new JCell(i, j);
        add(jCell);

        jCell.addMouseListener(new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {

            Pair<Integer, Integer> curLocation = controller.getPlayerLocation();
            EnumSet<Direction> directions = controller.getPossibleMoves(curLocation);
            String playerImg;
            if (controller.getCurrentPlayerNumber() == 0) {
              playerImg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\player.png";
            }
            else {
              playerImg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\stickman.png";
            }

            for (Direction direction: directions) {
              Pair<Integer, Integer> loc = controller.getNextLocation(curLocation, direction);
              boolean isValidLocation = true;
              if (controller.getPlayers().size() > 1) {
                int otherPlayer = (controller.getCurrentPlayerNumber() + 1 ) % 2;
                if (loc.equals(controller
                        .getPlayers().get(otherPlayer).getCurrentLocation())) {
                  isValidLocation = false;
                }
              }
              if (loc.fst == jCell.getRow() && loc.snd == jCell.getCol() && isValidLocation) {
                String out = controller.makeMove(direction);

                List<Pair<Integer, Integer>> visitList = new ArrayList<>();
                String[] messageList = out.split("\n");
                for (String message: messageList) {
                  if (message.contains("Tunnel:") || message.contains("FinalLocation:")) {
                    String[] coordinates = message.split(":")[1].split(",");
                    visitList.add(new Pair<>(Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1])));
                  }
                }

                JCell cell = getCell(curLocation);
                String imgPath = getCorrectCellImage(directions);
                cell.setCellImage(imgPath);
                cell.setBorder(null);
                cell.repaint();

                for (Pair<Integer, Integer> visitLoc : visitList) {
                  cell = getCell(visitLoc);
                  //wumpus
                  if (visitLoc.equals(controller.getWumpusLocation())) {
                    if (controller.stopGame()) {
                      cell.setCellImage(
                              "hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
                      cell.getGraphics()
                              .drawImage(new ImageIcon(
                                              "hunt-the-wumpus-images\\"
                                                      + "hunt-the-wumpus-images\\wumpus.png").getImage(),
                                      0, 0,cell.getWidth(),cell.getHeight(), null);
                      cell.repaint();
                      JOptionPane.showMessageDialog(null,
                              "Game over - The Wumpus got you!!");
                      contentPane.removeAll();
                      break;
                    }
                    else {
                      JOptionPane.showMessageDialog(null,
                              "Player dead!");
                      break;
                    }
                  }

                  //pit
                  if (controller.getNodeType(visitLoc).equals(NodeType.PIT)) {
                    if (controller.stopGame()) {
                      cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\pit.png");
                      cell.getGraphics()
                              .drawImage(new ImageIcon("hunt-the-wumpus-images\\hunt-the"
                                              + "-wumpus-images\\pit.png").getImage(),
                                      0, 0,cell.getWidth(),cell.getHeight(), null);
                      cell.repaint();
                      JOptionPane.showMessageDialog(null,
                              "Game over - You fell in the pit!!");
                      contentPane.removeAll();
                      break;
                    }
                    else {
                      JOptionPane.showMessageDialog(null,
                              "Player dead!");
                      break;
                    }
                  }

                  //bat
                  if (controller.getNodeType(visitLoc).equals(NodeType.BAT)) {
                    JOptionPane.showMessageDialog(null,
                            "Bats!!! Try and duck them if you can!!");
                  }

                  if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)
                          || controller.stopGame() ) {
                    cell.setCellImage(getCorrectCellImage(controller.getPossibleMoves(visitLoc)));
                    cell.repaint();
                  }
                }

                Pair<Integer, Integer> finalLoc = visitList.get(visitList.size() - 1);
                String bgPath = getCorrectCellImage(controller.getPossibleMoves(finalLoc));
                Image overlayedImage = overlayImages(bgPath, playerImg );
                //bat
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\superbat.png" );
                }

                if (out.contains("Pit:")) {
                  overlayedImage = overlayImages(bgPath, playerImg ,
                          "htw-view-images\\res\\slime-pit-nearby.png");
                  if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                    overlayedImage = overlayImages(bgPath, playerImg,
                            "htw-view-images\\res\\slime-pit-nearby.png",
                            "htw-view-images\\res\\superbat.png");
                  }
                }

                if (out.contains("Wumpus:")) {
                  overlayedImage = overlayImages(bgPath, playerImg ,
                          "htw-view-images\\res\\wumpus-nearby.png");
                  if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                    overlayedImage = overlayImages(bgPath, playerImg,
                            "htw-view-images\\res\\wumpus-nearby.png",
                            "htw-view-images\\res\\superbat.png");
                  }

                }

                if (out.contains("Pit:") && out.contains("Wumpus:")) {
                  overlayedImage = overlayImages(bgPath, playerImg ,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\wumpus-nearby.png");
                  if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                    overlayedImage = overlayImages(bgPath, playerImg,
                            "htw-view-images\\res\\slime-pit-nearby.png",
                            "htw-view-images\\res\\wumpus-nearby.png",
                            "htw-view-images\\res\\superbat.png");

                  }
                }

                if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
                  cell.setCellImage(overlayedImage);
                  cell.repaint();
                }
                JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
                c.setBorder(null);
                controller.switchPlayer();
                c = getCell(controller.getCurrentPlayer().getCurrentLocation());
                c.setBorder(border);

              }
            }
          }

          @Override
          public void mousePressed(MouseEvent e) {
            //Method not used

          }

          @Override
          public void mouseReleased(MouseEvent e) {
            //Method not used

          }

          @Override
          public void mouseEntered(MouseEvent e) {
            //Method not used

          }

          @Override
          public void mouseExited(MouseEvent e) {
            //Method not used

          }
        });
      }
    }

    this.addKeyListener(new KeyListener() {
      @Override
      public void keyTyped(KeyEvent e) {
        //Method not used

      }

      @Override
      public void keyPressed(KeyEvent e) {
        Pair<Integer, Integer> curLocation = controller.getPlayerLocation();
        EnumSet<Direction> directions = controller.getPossibleMoves(curLocation);
        String playerImg;
        if (controller.getCurrentPlayerNumber() == 0) {
          playerImg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\player.png";
        }
        else {
          playerImg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\stickman.png";
        }
        String out;
        int keyCode = e.getKeyCode();
        switch ( keyCode ) {
          case KeyEvent.VK_R:
            int response = JOptionPane.showConfirmDialog(null,
                    "Restart game with new seed?");
            if (response == 0) {
              contentPane.removeAll();
              MazeGraph.SEED = new Random().nextInt();
              controller.newGame();
              new MazeGrid(contentPane,
                      rows,  cols, controller);
              contentPane.repaint();
              contentPane.validate();

            }
            if (response == 1) {
              contentPane.removeAll();
              controller.resetGame();
              new MazeGrid(contentPane,
                      rows,  cols, controller);
              contentPane.repaint();
              contentPane.validate();

            }
            break;

          case KeyEvent.VK_UP:
            // handle up
            if (controller.getPossibleMoves(curLocation).contains(Direction.NORTH)) {
              out = controller.makeMove(Direction.NORTH);
              List<Pair<Integer, Integer>> visitList = new ArrayList<>();
              String[] messageList = out.split("\n");
              for (String message: messageList) {
                if (message.contains("Tunnel:") || message.contains("FinalLocation:")) {
                  String[] coordinates = message.split(":")[1].split(",");
                  visitList.add(new Pair<>(Integer.parseInt(coordinates[0]),
                          Integer.parseInt(coordinates[1])));
                }
              }

              JCell cell = getCell(curLocation);
              String imgPath = getCorrectCellImage(directions);
              cell.setCellImage(imgPath);
              cell.setBorder(null);
              cell.repaint();

              for (Pair<Integer, Integer> visitLoc : visitList) {
                cell = getCell(visitLoc);
                //wumpus
                if (visitLoc.equals(controller.getWumpusLocation())) {
                  if (controller.stopGame()) {
                    cell.setCellImage(
                            "hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon(
                                            "hunt-the-wumpus-images\\"
                                                    + "hunt-the-wumpus-images\\wumpus.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - The Wumpus got you!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //pit
                if (controller.getNodeType(visitLoc).equals(NodeType.PIT)) {
                  if (controller.stopGame()) {
                    cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\pit.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon("hunt-the-wumpus-images\\hunt-the"
                                            + "-wumpus-images\\pit.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - You fell in the pit!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //bat
                if (controller.getNodeType(visitLoc).equals(NodeType.BAT)) {
                  JOptionPane.showMessageDialog(null,
                          "Bats!!! Try and duck them if you can!!");
                }

                //cell .. bat - !controller.getNodeType(visitLoc).equals(NodeType.BAT) &&
                if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)
                        || controller.stopGame() ) {
                  cell.setCellImage(getCorrectCellImage(controller.getPossibleMoves(visitLoc)));
                  cell.repaint();
                }
              }

              Pair<Integer, Integer> finalLoc = visitList.get(visitList.size() - 1);
              String bgPath = getCorrectCellImage(controller.getPossibleMoves(finalLoc));
              Image overlayedImage = overlayImages(bgPath, playerImg );
              //bat
              if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                overlayedImage = overlayImages(bgPath, playerImg,
                        "htw-view-images\\res\\superbat.png" );
              }

              if (out.contains("Pit:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }
              }

              if (out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }

              }

              if (out.contains("Pit:") && out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png",
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");

                }
              }

              if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
                cell.setCellImage(overlayedImage);
                cell.repaint();
              }
              JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(null);
              controller.switchPlayer();
              c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(border);

            }

            break;
          case KeyEvent.VK_DOWN:
            // handle down
            if (controller.getPossibleMoves(curLocation).contains(Direction.SOUTH)) {
              out = controller.makeMove(Direction.SOUTH);

              List<Pair<Integer, Integer>> visitList = new ArrayList<>();
              String[] messageList = out.split("\n");
              for (String message: messageList) {
                if (message.contains("Tunnel:") || message.contains("FinalLocation:")) {
                  String[] coordinates = message.split(":")[1].split(",");
                  visitList.add(new Pair<>(Integer.parseInt(coordinates[0]),
                          Integer.parseInt(coordinates[1])));
                }
              }

              JCell cell = getCell(curLocation);
              String imgPath = getCorrectCellImage(directions);
              cell.setCellImage(imgPath);
              cell.setBorder(null);
              cell.repaint();

              for (Pair<Integer, Integer> visitLoc : visitList) {
                cell = getCell(visitLoc);
                //wumpus
                if (visitLoc.equals(controller.getWumpusLocation())) {
                  if (controller.stopGame()) {
                    cell.setCellImage(
                            "hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon(
                                            "hunt-the-wumpus-images\\"
                                                    + "hunt-the-wumpus-images\\wumpus.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - The Wumpus got you!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //pit
                if (controller.getNodeType(visitLoc).equals(NodeType.PIT)) {
                  if (controller.stopGame()) {
                    cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\pit.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon("hunt-the-wumpus-images\\hunt-the"
                                            + "-wumpus-images\\pit.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - You fell in the pit!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //bat
                if (controller.getNodeType(visitLoc).equals(NodeType.BAT)) {
                  JOptionPane.showMessageDialog(null,
                          "Bats!!! Try and duck them if you can!!");
                }

                //cell .. bat - !controller.getNodeType(visitLoc).equals(NodeType.BAT) &&
                if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)
                        || controller.stopGame() ) {
                  cell.setCellImage(getCorrectCellImage(controller.getPossibleMoves(visitLoc)));
                  cell.repaint();
                }
              }

              Pair<Integer, Integer> finalLoc = visitList.get(visitList.size() - 1);
              String bgPath = getCorrectCellImage(controller.getPossibleMoves(finalLoc));
              Image overlayedImage = overlayImages(bgPath, playerImg );
              //bat
              if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                overlayedImage = overlayImages(bgPath, playerImg,
                        "htw-view-images\\res\\superbat.png" );
              }

              if (out.contains("Pit:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }
              }

              if (out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }

              }

              if (out.contains("Pit:") && out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png",
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");

                }
              }

              if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
                cell.setCellImage(overlayedImage);
                cell.repaint();
              }
              JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(null);
              controller.switchPlayer();
              c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(border);

            }

            break;
          case KeyEvent.VK_LEFT:
            // handle left
            if (controller.getPossibleMoves(curLocation).contains(Direction.WEST)) {
              out = controller.makeMove(Direction.WEST);
              List<Pair<Integer, Integer>> visitList = new ArrayList<>();
              String[] messageList = out.split("\n");
              for (String message: messageList) {
                if (message.contains("Tunnel:") || message.contains("FinalLocation:")) {
                  String[] coordinates = message.split(":")[1].split(",");
                  visitList.add(new Pair<>(Integer.parseInt(coordinates[0]),
                          Integer.parseInt(coordinates[1])));
                }
              }

              JCell cell = getCell(curLocation);
              String imgPath = getCorrectCellImage(directions);
              cell.setCellImage(imgPath);
              cell.setBorder(null);
              cell.repaint();

              for (Pair<Integer, Integer> visitLoc : visitList) {
                cell = getCell(visitLoc);
                //wumpus
                if (visitLoc.equals(controller.getWumpusLocation())) {
                  if (controller.stopGame()) {
                    cell.setCellImage(
                            "hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon(
                                            "hunt-the-wumpus-images\\"
                                                    + "hunt-the-wumpus-images\\wumpus.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - The Wumpus got you!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //pit
                if (controller.getNodeType(visitLoc).equals(NodeType.PIT)) {
                  if (controller.stopGame()) {
                    cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\pit.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon("hunt-the-wumpus-images\\hunt-the"
                                            + "-wumpus-images\\pit.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - You fell in the pit!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //bat
                if (controller.getNodeType(visitLoc).equals(NodeType.BAT)) {
                  JOptionPane.showMessageDialog(null,
                          "Bats!!! Try and duck them if you can!!");
                }

                //cell .. bat - !controller.getNodeType(visitLoc).equals(NodeType.BAT) &&
                if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)
                        || controller.stopGame() ) {
                  cell.setCellImage(getCorrectCellImage(controller.getPossibleMoves(visitLoc)));
                  cell.repaint();
                }
              }

              Pair<Integer, Integer> finalLoc = visitList.get(visitList.size() - 1);
              String bgPath = getCorrectCellImage(controller.getPossibleMoves(finalLoc));
              Image overlayedImage = overlayImages(bgPath, playerImg );
              //bat
              if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                overlayedImage = overlayImages(bgPath, playerImg,
                        "htw-view-images\\res\\superbat.png" );
              }

              if (out.contains("Pit:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }
              }

              if (out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }

              }

              if (out.contains("Pit:") && out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png",
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");

                }
              }

              if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
                cell.setCellImage(overlayedImage);
                cell.repaint();
              }
              JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(null);
              controller.switchPlayer();
              c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(border);
            }

            break;
          case KeyEvent.VK_RIGHT :
            // handle right
            if (controller.getPossibleMoves(curLocation).contains(Direction.EAST)) {
              out = controller.makeMove(Direction.EAST);

              List<Pair<Integer, Integer>> visitList = new ArrayList<>();
              String[] messageList = out.split("\n");
              for (String message: messageList) {
                if (message.contains("Tunnel:") || message.contains("FinalLocation:")) {
                  String[] coordinates = message.split(":")[1].split(",");
                  visitList.add(new Pair<>(Integer.parseInt(coordinates[0]),
                          Integer.parseInt(coordinates[1])));
                }
              }

              JCell cell = getCell(curLocation);
              String imgPath = getCorrectCellImage(directions);
              cell.setCellImage(imgPath);
              cell.setBorder(null);
              cell.repaint();

              for (Pair<Integer, Integer> visitLoc : visitList) {
                cell = getCell(visitLoc);
                //wumpus
                if (visitLoc.equals(controller.getWumpusLocation())) {
                  if (controller.stopGame()) {
                    cell.setCellImage(
                            "hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon(
                                            "hunt-the-wumpus-images\\"
                                                    + "hunt-the-wumpus-images\\wumpus.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - The Wumpus got you!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //pit
                if (controller.getNodeType(visitLoc).equals(NodeType.PIT)) {
                  if (controller.stopGame()) {
                    cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\pit.png");
                    cell.getGraphics()
                            .drawImage(new ImageIcon("hunt-the-wumpus-images\\hunt-the"
                                            + "-wumpus-images\\pit.png").getImage(),
                                    0, 0,cell.getWidth(),cell.getHeight(), null);
                    cell.repaint();
                    JOptionPane.showMessageDialog(null,
                            "Game over - You fell in the pit!!");
                    contentPane.removeAll();
                    break;
                  }
                  else {
                    JOptionPane.showMessageDialog(null,
                            "Player dead!");
                    break;
                  }
                }

                //bat
                if (controller.getNodeType(visitLoc).equals(NodeType.BAT)) {
                  JOptionPane.showMessageDialog(null,
                          "Bats!!! Try and duck them if you can!!");
                }

                //cell .. bat - !controller.getNodeType(visitLoc).equals(NodeType.BAT) &&
                if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)
                        || controller.stopGame() ) {
                  cell.setCellImage(getCorrectCellImage(controller.getPossibleMoves(visitLoc)));
                  cell.repaint();
                }
              }

              Pair<Integer, Integer> finalLoc = visitList.get(visitList.size() - 1);
              String bgPath = getCorrectCellImage(controller.getPossibleMoves(finalLoc));
              Image overlayedImage = overlayImages(bgPath, playerImg );
              //bat
              if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                overlayedImage = overlayImages(bgPath, playerImg,
                        "htw-view-images\\res\\superbat.png" );
              }

              if (out.contains("Pit:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }
              }

              if (out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");
                }

              }

              if (out.contains("Pit:") && out.contains("Wumpus:")) {
                overlayedImage = overlayImages(bgPath, playerImg ,
                        "htw-view-images\\res\\slime-pit-nearby.png",
                        "htw-view-images\\res\\wumpus-nearby.png");
                if (controller.getNodeType(finalLoc).equals(NodeType.BAT)) {
                  overlayedImage = overlayImages(bgPath, playerImg,
                          "htw-view-images\\res\\slime-pit-nearby.png",
                          "htw-view-images\\res\\wumpus-nearby.png",
                          "htw-view-images\\res\\superbat.png");

                }
              }

              if (controller.getCurrentPlayer().getStatus().equals(PlayerStatus.ALIVE)) {
                cell.setCellImage(overlayedImage);
                cell.repaint();
              }
              JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(null);
              controller.switchPlayer();
              c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(border);
            }
            break;
          default:
        }

        if (e.getKeyChar() == 'S' || e.getKeyChar() == 's') {
          String direction = JOptionPane
                  .showInputDialog("Enter direction:\n N/NORTH,\n S/SOUTH,\n E/EAST,\n W/WEST");
          String distance = JOptionPane.showInputDialog("Enter distance:");
          List<Pair<Integer, Integer>> visitList = new ArrayList<>();
          try {
            switch (direction) {
              case "n":
              case "N":
              case "NORTH":
                visitList = controller.shootArrow(Direction.NORTH, Integer.parseInt(distance));
                break;
              case "s":
              case "S":
              case "SOUTH":
                visitList = controller.shootArrow(Direction.SOUTH, Integer.parseInt(distance));
                break;
              case "e":
              case "E":
              case "EAST":
                visitList = controller.shootArrow(Direction.EAST, Integer.parseInt(distance));
                break;
              case "w":
              case "W":
              case "WEST":
                visitList = controller.shootArrow(Direction.WEST, Integer.parseInt(distance));
                break;
              default:
                JOptionPane.showMessageDialog(null,
                        "Invalid input! Try Again!");
            }
          } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Invalid input! Try Again!");
          }

          for (Pair<Integer, Integer> visitLoc : visitList) {
            JCell cell = getCell(visitLoc);
            String path = cell.getCellImage();
            cell.setCellImage("htw-view-images\\res\\target.png");
            cell.getGraphics()
                    .drawImage(new ImageIcon("htw-view-images\\res\\target.png").getImage(),
                            0, 0,cell.getWidth(),cell.getHeight(), null);
            try {
              Thread.sleep(500);
              cell.setCellImage(path);
              cell.repaint();
            } catch (InterruptedException interruptedException) {
              interruptedException.printStackTrace();
            }
            cell.repaint();
            cell.validate();
          }

          if (visitList.size() >= 1 && visitList.get(visitList.size() - 1).equals(controller.getWumpusLocation())) {
            JCell cell = getCell(visitList.get(visitList.size() - 1));
            cell.setCellImage("hunt-the-wumpus-images\\hunt-the-wumpus-images\\wumpus.png");
            cell.getGraphics()
                    .drawImage(new ImageIcon(
                                    "hunt-the-wumpus-images"
                                            + "\\hunt-the-wumpus-images\\wumpus.png")
                                    .getImage(),
                            0, 0,cell.getWidth(),cell.getHeight(), null);
            cell.repaint();
            JOptionPane.showMessageDialog(null,
                    "WINNER!");
            contentPane.removeAll();
          }

          if (controller.getCurrentPlayer().getArrowCount() == 0) {
            JOptionPane.showMessageDialog(null,
                    "Dead- arrows over!!");
            JCell c = getCell(controller.getCurrentPlayer().getCurrentLocation());
            c.setCellImage(getCorrectCellImage(controller
                    .getPossibleMoves(controller.getCurrentPlayer().getCurrentLocation())));
            if (controller.stopGame()) {
              contentPane.removeAll();
            }
            else {
              c.setBorder(null);
              controller.switchPlayer();
              c = getCell(controller.getCurrentPlayer().getCurrentLocation());
              c.setBorder(border);
            }

          }

        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        //Method not used

      }
    });

    String wumpusImg = "htw-view-images\\res\\wumpus-nearby.png";
    String pitImg = "htw-view-images\\res\\slime-pit-nearby.png";
    String imgFg;

    //1 player
    Pair<Integer, Integer> player1loc = controller.getCurrentPlayer().getCurrentLocation();
    int x = player1loc.fst;
    int y = player1loc.snd;
    int index = cols * x + y;
    imgFg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\player.png";
    String imgBg = getCorrectCellImage(controller.getPossibleMoves(player1loc));

    int cond = 1;
    for (Direction direction : controller.getPossibleMoves(player1loc)) {
      String out = controller.makeMove(direction);
      String[] messageList = out.split("\n");
      Pair<Integer, Integer> nextLoc = null;
      for (String message : messageList) {
        if (message.contains("FinalLocation:")) {
          String[] coordinates = message.split(":")[1].split(",");
          nextLoc = new Pair<>(Integer.parseInt(coordinates[0]),
                  Integer.parseInt(coordinates[1]));
        }
      }
      assert nextLoc != null;
      if (nextLoc.equals(controller.getWumpusLocation())) {
        cond *= 2;
      } else if (controller.getNodeType(nextLoc).equals(NodeType.PIT)) {
        cond *= 3;
      }
      controller.getCurrentPlayer().setStatus(PlayerStatus.ALIVE);
      controller.getCurrentPlayer().setCurrentLocation(player1loc);
    }
    JCell cell = ((JCell) (this.getComponents()[index]));
    if (cond == 1) {
      cell.setCellImage(overlayImages(imgBg, imgFg));
    } else if (cond % 6 == 0) {
      cell.setCellImage(overlayImages(imgBg, imgFg, wumpusImg,
              pitImg));
    } else if (cond % 2 == 0) {
      cell.setCellImage(overlayImages(imgBg, imgFg, wumpusImg));
    } else if (cond % 3 == 0) {
      cell.setCellImage(overlayImages(imgBg, imgFg, pitImg));
    }

    if (controller.getCurrentPlayer().getCurrentLocation().equals(player1loc)) {
      cell.setBorder(border);
    }

    //2 player
    if (controller.getPlayers().size() == 2) {
      Pair<Integer, Integer> player2loc = controller.getPlayers().get(1).getCurrentLocation();
      x = player2loc.fst;
      y = player2loc.snd;
      index = cols * x + y;
      imgFg = "hunt-the-wumpus-images\\hunt-the-wumpus-images\\stickman.png";
      imgBg = getCorrectCellImage(controller.getPossibleMoves(player2loc));
      cond = 1;
      for (Direction direction : controller.getPossibleMoves(player2loc)) {
        String out = controller.makeMove(direction);
        String[] messageList = out.split("\n");
        Pair<Integer, Integer> nextLoc = null;
        for (String message : messageList) {
          if (message.contains("FinalLocation:")) {
            String[] coordinates = message.split(":")[1].split(",");
            nextLoc = new Pair<>(Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]));
          }
        }
        assert nextLoc != null;
        if (nextLoc.equals(controller.getWumpusLocation())) {
          cond *= 2;
        } else if (controller.getNodeType(nextLoc).equals(NodeType.PIT)) {
          cond *= 3;
        }
        controller.getPlayers().get(0).setStatus(PlayerStatus.ALIVE);
        controller.getPlayers().get(0).setCurrentLocation(player2loc);
      }
      cell = ((JCell) (this.getComponents()[index]));
      if (cond == 1) {
        cell.setCellImage(overlayImages(imgBg, imgFg));
      } else if (cond % 6 == 0) {
        cell.setCellImage(overlayImages(imgBg, imgFg, wumpusImg,
                pitImg));
      } else if (cond % 2 == 0) {
        cell.setCellImage(overlayImages(imgBg, imgFg, wumpusImg));
      } else if (cond % 3 == 0) {
        cell.setCellImage(overlayImages(imgBg, imgFg, pitImg));
      }
    }
    controller.getPlayers().get(0).setCurrentLocation(player1loc);
    JScrollPane jScrollPane = new JScrollPane(this);
    contentPane.add(jScrollPane);

    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  private JCell getCell(Pair<Integer, Integer> location) {
    int x = location.fst;
    int y = location.snd;
    int index = cols * x + y;
    return ((JCell)(getComponents()[index]));
  }


  private String getCorrectCellImage(EnumSet<Direction> directions) {
    int doors = directions.size();
    List<Direction> directionList = new ArrayList<>();
    directionList.addAll(directions);
    switch (doors) {
      case 1:
        switch (directionList.get(0)) {
          case SOUTH:
            return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\S.png";
          case WEST:
            return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\W.png";
          case EAST:
            return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\E.png";
          case NORTH:
            return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\N.png";
          default:
            return "";
        }
      case 2:
        if (directions.contains(Direction.EAST)) {
          directionList.remove(Direction.EAST);
          switch (directionList.get(0)) {
            case SOUTH:
              return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\SE.png";
            case WEST:
              return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\EW.png";
            case NORTH:
              return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NE.png";
            default:
              return " ";
          }
        }
        if (directions.contains(Direction.WEST)) {
          directionList.remove(Direction.WEST);
          switch (directionList.get(0)) {
            case SOUTH:
              return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\SW.png";
            case NORTH:
              return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NW.png";
            default:
              return " ";
          }
        }
        if (directions.contains(Direction.NORTH) && directions.contains(Direction.SOUTH)) {
          return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NS.png";
        }
        break;
      case 3:
        if (!directions.contains(Direction.NORTH)) {
          return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\SEW.png";
        }
        else if (!directions.contains(Direction.SOUTH)) {
          return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NEW.png";
        }
        else if (!directions.contains(Direction.WEST)) {
          return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NSE.png";
        }
        else if (!directions.contains(Direction.EAST)) {
          return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NSW.png";
        }
        break;
      case 4:
        return "hunt-the-wumpus-images\\hunt-the-wumpus-images\\NSEW.png";
      default: return " ";
    }
    return " ";
  }

  private BufferedImage disposeImageGraphics(ImageIcon img) {
    BufferedImage bfImage = new BufferedImage(img.getIconWidth(), img.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
    Graphics g = bfImage.createGraphics();
    img.paintIcon(null, g, 0,0);
    g.dispose();
    return bfImage;
  }


  private Image overlayImages(String background, String foreground) {
    BufferedImage bufferedBgImage = disposeImageGraphics(new ImageIcon(background));
    BufferedImage bufferedFgImage = disposeImageGraphics(new ImageIcon(foreground));
    Graphics2D graphics2D = bufferedBgImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(bufferedBgImage, 0,0,null);
    graphics2D.drawImage(bufferedFgImage, 0,0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null );
    graphics2D.dispose();
    return bufferedBgImage;
  }

  private Image overlayImages(String background, String foreground, String effect) {
    BufferedImage bufferedBgImage = disposeImageGraphics(new ImageIcon(background));
    BufferedImage bufferedFgImage = disposeImageGraphics(new ImageIcon(foreground));
    BufferedImage bufferedEffectImage = disposeImageGraphics(new ImageIcon(effect));
    Graphics2D graphics2D = bufferedBgImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(bufferedBgImage, 0,0,null);
    graphics2D.drawImage(bufferedFgImage, 0,0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null );
    graphics2D.drawImage(bufferedEffectImage,  (int) (bufferedBgImage.getWidth() * 0.6) ,
            0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null);
    graphics2D.dispose();
    return bufferedBgImage;
  }

  private Image overlayImages(String background, String foreground, String effect1,
                              String effect2) {
    BufferedImage bufferedBgImage = disposeImageGraphics(new ImageIcon(background));
    BufferedImage bufferedFgImage = disposeImageGraphics(new ImageIcon(foreground));
    BufferedImage bufferedEffectImage = disposeImageGraphics(new ImageIcon(effect1));
    BufferedImage bufferedEffect2Image = disposeImageGraphics(new ImageIcon(effect2));
    Graphics2D graphics2D = bufferedBgImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(bufferedBgImage, 0,0,null);
    graphics2D.drawImage(bufferedFgImage, 0,0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null );
    graphics2D.drawImage(bufferedEffectImage,  (int) (bufferedBgImage.getWidth() * 0.6) ,
            0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null);
    graphics2D.drawImage(bufferedEffect2Image, 0 ,
            (int) (bufferedBgImage.getHeight() * 0.6), (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null);

    graphics2D.dispose();
    return bufferedBgImage;
  }

  private Image overlayImages(String background, String foreground, String effect1,
                              String effect2, String effect3) {
    BufferedImage bufferedBgImage = disposeImageGraphics(new ImageIcon(background));
    BufferedImage bufferedFgImage = disposeImageGraphics(new ImageIcon(foreground));
    BufferedImage bufferedEffectImage = disposeImageGraphics(new ImageIcon(effect1));
    BufferedImage bufferedEffect2Image = disposeImageGraphics(new ImageIcon(effect2));
    BufferedImage bufferedEffect3Image = disposeImageGraphics(new ImageIcon(effect3));

    Graphics2D graphics2D = bufferedBgImage.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(bufferedBgImage, 0,0,null);
    graphics2D.drawImage(bufferedFgImage, 0,0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null );
    graphics2D.drawImage(bufferedEffectImage,  (int) (bufferedBgImage.getWidth() * 0.6) ,
            0, (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null);
    graphics2D.drawImage(bufferedEffect2Image, 0 ,
            (int) (bufferedBgImage.getHeight() * 0.6), (int) (bufferedBgImage.getWidth() * 0.4),
            (int)(bufferedBgImage.getHeight() * 0.4), null);
    graphics2D.drawImage(bufferedEffect3Image, (int) (bufferedBgImage.getWidth() * 0.7 ),
            (int) (bufferedBgImage.getHeight() * 0.7), (int) (bufferedBgImage.getWidth() * 0.3),
            (int)(bufferedBgImage.getHeight() * 0.3), null);

    graphics2D.dispose();
    return bufferedBgImage;
  }
}
