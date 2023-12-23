// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** Train.java
  * A train -- the vehicle that speeds through and spans the entire row
  * This also flashes a warning before the train arrives
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class Train extends Vehicle {
  
  /**
   * Train
   * This constructor ensures each Train is created with the necessary attributes
   * @param x The top left x-coordinate of the train's skin
   * @param y The top left y-coordinate of the train's skin
   * @param direction The direction the train moves toward ("left" or "right")
   */
  Train(int x, int y, String direction) {
    super(x,y,ROW_WIDTH,direction);
    setSpeed(10);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to train's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("train.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Train Image.");
    }
  }
}