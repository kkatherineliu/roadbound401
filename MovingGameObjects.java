// *** imports *** // 
import java.awt.image.BufferedImage;
import java.awt.Graphics;

/** MovingGameObject.java
  * An abstract class representing all moving objects on the map
  * (living characters and vehicles)
  * Included common variables
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

abstract class MovingGameObjects extends GameObject {
  
  /*
   * CLASS VARIABLES
   */
  private int speed; 
  private String direction;
  public BufferedImage skin;
  
  /**
   * MovingGameObject
   * This constructor ensures each MovingGameObject is created with the necessary attributes
   * Each character will be slightly shorter than the full row height
   * @param x The top left x-coordinate of the moving game object's skin
   * @param y The top left y-coordinate of the moving game object's skin
   * @param width The total width of the moving game object
   * @param direction The direction the moving game object is moving ("left", "right", "forward", "backward")
   */
  MovingGameObjects(int x, int y, int width, String direction) {
    super(x,y,width,ROW_HEIGHT-8);
    this.direction = direction;
    loadImage();
  }
  
  /**
   * loadImage
   * This method will be used to load the image corresponding to the specific moving game object's appearance
   */
  abstract void loadImage();
  
  /**
   * draw
   * This method is from the Drawable interface and displays the MovingGameObject onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    g.drawImage(skin, getX(), getY(), getWidth(), getHeight(), null);
  }
  
  /**
   * getSpeed
   * This method extracts and returns the private variable "speed" 
   * @return The speed of the moving game object
   */ 
  public int getSpeed() {
    return speed;
  }
  /**
   * setSpeed
   * This method changes the private variable "speed" 
   * @param speed The assigned speed for the moving game object
   */ 
  public void setSpeed(int speed) {
    this.speed = speed;
  }
  
  /**
   * getDirection
   * This method extracts and returns the private variable "direction" 
   * @return The direction of the moving game object ("left", "right", "forward", "backward")
   */ 
  public String getDirection() {
    return direction;
  }
  /**
   * setDirection
   * This method changes the private variable "direction" 
   * @param direction The new assigned direction for the moving game object ("left", "right", "forward", "backward")
   */ 
  public void setDirection(String direction) {
    this.direction = direction;
  }
}