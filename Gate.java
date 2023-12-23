// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;

/** Gate.java
  * A gate (found on the field rows of the map)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 1, 2021
  */

public class Gate extends GroundGameObjects {
  /*
   * CLASS VARIABLE
   */
  private boolean open;
  private BufferedImage skin2;
  
  /**
   * Gate
   * This constructor ensures each Gate  is created with the necessary attributes
   * @param x The top left x-coordinate of the gate's skin
   * @param y The top left y-coordinate of the gate 's skin
   */
  Gate(int x, int y) {
    super(x,y,ROW_WIDTH,ROW_HEIGHT/5);
    open = false;
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the gate's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("gate.png"));
      skin2 = ImageIO.read(new File("gate_faint.png"));
    } catch(Exception e) { 
      System.out.println("Error Loading Gate Image.");
    }
  }
  
  /**
   * draw
   * This method displays the gate onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    if (!open) {
      g.drawImage(skin, getX(), getY(), getWidth(), getHeight(), null);
    } else {
      g.drawImage(skin2, getX(), getY(), getWidth(), getHeight(), null);
    }
  }
  
  /**
   * getOpen
   * This method extracts and returns the private variable "open" 
   * @return If the gate is open and the player can pass through
   */ 
  public boolean getOpen() {
    return open;
  }
  
  /**
   * setOpen
   * This method changes the private variable "direction" 
   * @param open true if the gate is open and the player can pass through
   */ 
  public void setOpen(boolean open) {
    this.open = open;
  }
}