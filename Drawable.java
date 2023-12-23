// *** imports for Graphics ***//
import java.awt.Graphics;

/** Drawable.java
  * Interface for all objects that are drawn to the screen
  * Can be used for characters, shapes, images, text etc. as long as they are defined by at least one point
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 27, 2021
  */

public interface Drawable {
   
  /**
   * draw
   * This method is displays the the element onto the screen
   * @param g The object used to draw to the screen
   */ 
   void draw(Graphics g);
}


