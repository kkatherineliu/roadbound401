// *** imports *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** IncreaseDamage.java
  * A pile of rocks/ammunition (found on the grass rows of the map)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 1, 2021
  */

public class IncreaseDamage extends Ability {
  
  /**
   * IncreaseDamage
   * This constructor ensures each Increase Damage ability is created with the necessary attributes
   * @param x The top left x-coordinate of the abilities's skin
   * @param y The top left y-coordinate of the abilities's skin
   */
  IncreaseDamage(int x, int y) {
    super(x,y,ROW_HEIGHT-40,ROW_HEIGHT-40);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the abilities's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("rock_pile.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Rock Pile Image.");
    }
  }
}