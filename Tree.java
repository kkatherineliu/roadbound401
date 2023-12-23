// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** Tree.java
  * A tree (found on the grass rows of the map)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 1, 2021
  */

public class Tree extends GroundGameObjects {
  
  /**
   * Tree
   * This constructor ensures each Tree is created with the necessary attributes
   * @param x The top left x-coordinate of the tree's skin
   * @param y The top left y-coordinate of the tree's skin
   */
  Tree(int x, int y) {
    super(x,y,SCREEN_WIDTH/14,SCREEN_HEIGHT/10);
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to the tree's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("tree.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Tree Image.");
    }
  }
}