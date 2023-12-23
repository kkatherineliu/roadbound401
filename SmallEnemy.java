// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** SmallEnemy.java
  * A small enemy -- deal less damage but move faster
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class SmallEnemy extends Enemy {
  /**
   * SmallEnemy
   * This constructor ensures each SmallEnemy is created with the necessary attributes
   * @param x The top left x-coordinate of the small enemy's skin
   * @param y The top left y-coordinate of the small enemy's skin
   * @param direction The direction the small enemy moves toward ("left" or "right")
   */
  SmallEnemy(int x, int y, String direction) {
    super(x,y,direction, 15);
    loadImage();
    setSpeed(5); 
    setHealth(30);
  }  
  
  /**
   * loadImage
   * This method loads the images corresponding to the big enemy's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("SmallEnemy_Right.png")); 
      skin2 = ImageIO.read(new File("SmallEnemy_Left.png"));
      
      for (int i = 0; i < cols; i++) {
        for (int j = 0; j<2; j++) {
          spritesRight[i*2+j] = skin.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
          spritesLeft[i*2+j] = skin2.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        }
      }
    } catch(Exception e) { 
      System.out.println("Error Loading Images.");
    }
  }
}