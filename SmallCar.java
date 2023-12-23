// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** SmallCar.java
  * A small car -- the smallest and medium speed vehicle
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class SmallCar extends Vehicle {
  
  /**
   * SmallCar
   * This constructor ensures each SmallCar is created with the necessary attributes
   * @param x The top left x-coordinate of the small car's skin
   * @param y The top left y-coordinate of the small car's skin
   * @param direction The direction the small car moves toward ("left" or "right")
   */
  SmallCar(int x, int y, String direction) {
    super(x,y,90,direction);
    setSpeed(4);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the small car's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("smallcar_" + getDirection() +".png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Small Car Image.");
    }
  }
}