// *** imports for displaying graphics *** // 
import java.awt.image.BufferedImage;
import java.awt.Graphics;

/** GroundGameObjects.java
  * An class representing all objects on the groun in the map 
  * (trees, gates, abilities)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

abstract class GroundGameObjects extends GameObject {
  
  /*
   * CLASS VARIABLES
   */ 
  public BufferedImage skin;
  
  /**
   * GroundGameObjects
   * This constructor ensures each Ground Game Object is created with the necessary attributes
   * @param x The top left x-coordinate of the object's skin/shape
   * @param y The top left y-coordinate of the object's skin/shape
   * @param width The total width of the object
   * @param height The total height of the object
   */
  GroundGameObjects(int x, int y, int width, int height) {
    super(x,y,width,height);
    loadImage();
  }
  
  /**
   * loadImage
   * This method will be used to load the image corresponding to the specific object's appearance
   */
  abstract void loadImage();
  
  /**
   * draw
   * This method is from the Drawable interface and displays the Barrier onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    g.drawImage(skin, getX(), getY(), getWidth(), getHeight(), null);
  }
}
  
