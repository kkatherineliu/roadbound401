// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** Truck.java
  * A truck -- the longest and slowest vehicle
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class Truck extends Vehicle {
  
  /**
   * Truck
   * This constructor ensures each Truck is created with the necessary attributes
   * @param x The top left x-coordinate of the truck's skin
   * @param y The top left y-coordinate of the truck's skin
   * @param direction The direction the truck moves toward ("left" or "right")
   */
  Truck(int x, int y, String direction) {
    super(x,y,200,direction);
    setSpeed(3);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the truck's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("truck_" + getDirection() + ".png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Truck Image.");
    }
  }
}