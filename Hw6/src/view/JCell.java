package view;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * This class represents a JPanel of the mazegrid view.
 * It represents the corresponding row and column of the maze, and the image for that cell.
 * */
public class JCell extends JPanel {
  private final JLabel jLabel;
  private final int row;
  private final int col;
  private String path;

  /**
   * Constructor  for JCell.
   * @param row : row of the maze
   * @param col : column of the maze
   * */
  public JCell(int row, int col) {
    super();
    this.row = row;
    this.col = col;
    this.jLabel = new JLabel(" ");
    this.add(jLabel);
    this.path = "";
    setBackground(new Color(229, 229, 229));
  }

  /**
   * Sets the image of the cell.
   * @param path : the path of the image for the cll
   * */
  public void setCellImage(String path) {
    this.path = path;
    jLabel.setIcon(new ImageIcon(new ImageIcon(path).getImage()
            .getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    this.add(jLabel);

  }

  /**
   * Sets the image of the cell.
   * @param image : the image for the cll
   * */
  public void setCellImage(Image image) {
    jLabel.setIcon(new ImageIcon(image.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
    this.add(jLabel);
  }

  /**
   * Gets the row number.
   * @return row number
   * */
  public int getRow() {
    return row;
  }

  /**
   * Gets the col number.
   * @return col number
   * */
  public int getCol() {
    return col;
  }

  /**
   * Gets the image path for the cell.
   * @return path of the image
   * */
  public String getCellImage() {
    return path;
  }
}
