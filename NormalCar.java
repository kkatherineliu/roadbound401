// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

/** NormalCar.java
  * A normal car -- the medium size and medium speed vehicle
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class NormalCar extends Vehicle {
  
  /**
   * NormalCar
   * This constructor ensures each NormalCar is created with the necessary attributes
   * @param x The top left x-coordinate of the normal car's skin
   * @param y The top left y-coordinate of the normal car's skin
   * @param direction The direction the normal car moves toward ("left" or "right")
   */
  NormalCar(int x, int y, String direction) {
    super(x,y,120,direction);
    setSpeed(4);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the specific normal car's appearance
   */
  public void loadImage() {
    try {
      Random rand = new Random();
      
      if (rand.nextInt(2) == 0) { //two different types of normal car skins
        skin = ImageIO.read(new File("greencar_" + getDirection() +".png"));
      } else {
        skin = ImageIO.read(new File("taxi_" + getDirection() + ".png"));
      }
      
    } catch(Exception e) { 
      System.out.println("Error Loading Normal Car Image.");
    }
  }
}