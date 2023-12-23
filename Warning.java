// *** imports for displaying graphics *** // 
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;

/** Warning.java
  * An class representing all warning signs in the map 
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 4, 2021
  */

class Warning extends GameObject {
  
  /*
   * CLASS VARIABLES
   */ 
  public BufferedImage skin;
  
  /**
   * Warning 
   * This constructor ensures each Warning is created with the necessary attributes
   * @param x The top left x-coordinate of the warnings's skin/shape
   * @param y The top left y-coordinate of the warnings's skin/shape
   * @param width The total width of the warning sign
   * @param height The total height of the warning sign
   */
  Warning(int x, int y, int width, int height) {
    super(x,y,width,height);
    loadImage();
  }
  
  /**
   * loadImage
   * This method will be used to load the image corresponding to the specific warnings's appearance
   */
  public void loadImage(){
    try {
      skin = ImageIO.read(new File("warning_sign.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Warning Image.");
    }
  }
  
  /**
   * draw
   * This method is from the Drawable interface and displays the Warning onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    g.drawImage(skin, getX(), getY(), getWidth(), getHeight(), null);
  }
}
  
