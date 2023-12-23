// *** imports for displaying graphics *** // 
import javax.imageio.ImageIO;
import java.io.File;

/** BigEnemy.java
  * A big enemy -- deal more damage but moves slower
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class BigEnemy extends Enemy {
  
  /**
   * BigEnemy
   * This constructor ensures each BigEnemy is created with the necessary attributes
   * @param x The top left x-coordinate of the big enemy's skin
   * @param y The top left y-coordinate of the big enemy's skin
   * @param direction The direction the big enemy moves toward ("left" or "right")
   */
  BigEnemy(int x, int y, String direction) {
    super(x,y,direction,30);
    loadImage();
    setSpeed(3); 
    setHealth(50);
  } 
  
  /**
   * loadImage
   * This method loads the images corresponding to the big enemy's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("BigEnemy_Right.png")); 
      skin2 = ImageIO.read(new File("BigEnemy_Left.png"));
      
      for (int i = 0; i < cols; i++) {//Looping through the sprite sheet
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