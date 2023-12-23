// *** imports *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** Invincibility.java
  * An item granting invincibility (found on the grass rows of the map)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 1, 2021
  */

public class Invincibility extends Ability {
  
  /**
   * Invincibility
   * This constructor ensures each Invincibility ability is created with the necessary attributes
   * @param x The top left x-coordinate of the invincibility item's skin
   * @param y The top left y-coordinate of the invincibility item's skin
   */
  Invincibility(int x, int y) {
    super(x,y,ROW_HEIGHT-40,ROW_HEIGHT-40);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the ability's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("star.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Star Image.");
    }
  }
}