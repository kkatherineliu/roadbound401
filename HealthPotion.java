// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** HealthPotion.java
  * A health potion (found on the grass rows of the map)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 1, 2021
  */

public class HealthPotion extends Ability {
  
  /**
   * HealthPotion
   * This constructor ensures each Health Potion is created with the necessary attributes
   * @param x The top left x-coordinate of the potion's skin
   * @param y The top left y-coordinate of the potion's skin
   */
  HealthPotion(int x, int y) {
    super(x,y,ROW_HEIGHT-40,ROW_HEIGHT-20);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the potion's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("health_potion.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Potion Image.");
    }
  }
}