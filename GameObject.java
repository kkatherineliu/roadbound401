/** GameObject.java
  * An abstract class representing all game objects on the map
  * Includes basic information (top left coordinate, width, height)
  * Implements Movement and Drawable interface
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

abstract class GameObject implements Movement, Drawable {
  
  /*
   * CLASS VARIABLES
   */
  private int x, y;
  private int width, height;
  public static final int SCREEN_HEIGHT = 800;
  public static final int SCREEN_WIDTH = 1260;
  public static final int EXTRA_SPACE = 100;
  public static final int ROW_HEIGHT = 80;
  public static final int ROW_WIDTH = 1400;
  
  /**
   * GameObject
   * This constructor ensures each GameObject is created with the necessary attributes
   * @param x The top left x-coordinate of the game object
   * @param y The top left y-coordinate of the game object
   * @param width The total width of the game object
   * @param height The total height of the game object
   */
  GameObject(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /**
   * outOfBounds
   * This method determines if a game object is out of bounds on the left, right, or bottom
   * @return true if the game object is off the screen
   */
  public boolean outOfBounds() {
    int space = (ROW_WIDTH-SCREEN_WIDTH)+EXTRA_SPACE*2;
    if ((x+width < -space) 
          || (x > SCREEN_WIDTH + space) 
          || (y > SCREEN_HEIGHT)) {
      return true;
    }
    return false;
  }
  
  /**
   * moveDown
   * This method moves the game object downwards
   * @param move The distance (in pixels) the game object travels downwards
   */
  public void moveDown(int move) {
    y += move;
  }
  
  /**
   * moveUp
   * This method moves the game object upwards
   * @param move The distance (in pixels) the game object travels upwards
   */
  public void moveUp(int move) {
  } 
  
  /**
   * moveLeft
   * This method moves the moving game object towards the left
   * @param move The distance (in pixels) the game object travels to the left
   */
  public void moveLeft(int move) {
    x-=move;
  }
  
  /**
   * moveRight
   * This method moves the moving game object towards the right
   * @param move The distance (in pixels) the game object travels to the left
   */
  public void moveRight(int move) {
    x+=move;
  }
  
  /**
   * getX
   * This method extracts and returns the private variable "x" 
   * @return The x-coordinate of the top left of the game object 
   */ 
  public int getX() {
    return x;
  }
  /**
   * setX
   * This method changes the private variable "x" 
   * @param x The new x-coordinate for top left of the game object
   */ 
  public void setX(int x) {
    this.x = x;
  }
  
  /**
   * getY
   * This method extracts and returns the private variable "y" 
   * @return The y-coordinate of the top left of the game object
   */ 
  public int getY() {
    return y;
  }
  /**
   * setY
   * This method changes the private variable "y" 
   * @param y The new y-coordinate for the top left of the game object
   */ 
  public void setY(int y) {
    this.y = y;
  }
  
  /**
   * getWidth
   * This method extracts and returns the private variable "width" 
   * @return The width of the game object
   */ 
  public int getWidth() {
    return width;
  }
  /**
   * setWidth
   * This method changes the private variable "width" 
   * @param width The new width of the game object
   */ 
  public void setWidth(int width) {
    this.width = width;
  }
  
  /**
   * getHeight
   * This method extracts and returns the private variable "height" 
   * @return The height of the game object
   */ 
  public int getHeight() {
    return height;
  }
  /**
   * setHeight
   * This method changes the private variable "height" 
   * @param height The new height of the game object
   */ 
  public void setHeight(int height) {
    this.height = height;
  }
}