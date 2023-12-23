// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** WaterBush.java
  * A bush on water -- the only object that protects the Player from falling into the water
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class WaterBush extends Vehicle {
  
  /**
   * WaterBush
   * This constructor ensures each WaterBush is created with the necessary attributes
   * @param x The top left x-coordinate of the water bush's skin
   * @param y The top left y-coordinate of the water bush's skin
   * @param direction The direction the water bush moves toward ("left" or "right")
   * @param speed The speed of the water bush (higher = faster)
   */
  WaterBush(int x, int y, String direction, int speed) {
    super(x,y,250,direction);
    setHeight(ROW_HEIGHT);
    setSpeed(speed);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the water bush's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("longbush.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Log Image.");
    }
  }
}