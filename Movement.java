/** Movement.java
  * Interface for all objects that move in a 2D perspective
  * Can be used for characters, shapes, images, text etc. as long as they are defined by at least one point
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public interface Movement {
   
  /**
   * moveUp
   * This method shifts an object upwards based on it's current speed
   * @param move The distance (in pixels) the object travels upwards
   */
  void moveUp(int move);
  
  /**
   * moveDown
   * This method shifts an object downwards based on it's current speed
   * @param move The distance (in pixels) the object travels downwards
   */
  void moveDown(int move);
  
  /**
   * moveLeft
   * This method shifts an object to the left based on it's current speed
   * @param move The distance (in pixels) the object travels to the left
   */
  void moveLeft(int move);
  
  /**
   * moveRight
   * This method shifts an object to the right based on it's current speed
   * @param move The distance (in pixels) the object travels to the right
   */
  void moveRight(int move);
  
}


