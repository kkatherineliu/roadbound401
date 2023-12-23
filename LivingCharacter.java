// *** imports graphics *** // 
import java.awt.image.BufferedImage;

/** LivingCharacter.java
  * An abstract class representing all living characters on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

abstract class LivingCharacter extends MovingGameObjects {
  
  /*
   * CLASS VARIABLES
   */
  private int health;
  
  // the following relate to drawing and displaying each sprite of a living character
  public BufferedImage skin2;
  public int cols = 6;
  public BufferedImage[] spritesLeft = new BufferedImage[cols*2];
  public BufferedImage[] spritesRight = new BufferedImage[cols*2];
  public int currentSpriteLeft = 0;
  public int currentSpriteRight = 0;
  public final int IMAGE_WIDTH = 32;
  public final int IMAGE_HEIGHT = 29;
  
  /**
   * LivingCharacter
   * This constructor ensures each LivingCharacter is created with the necessary attributes
   * @param x The top left x-coordinate of the living character's skin
   * @param y The top left y-coordinate of the living character's skin
   * @param direction The direction the living character moves toward ("left" or "right")
   */
  LivingCharacter(int x, int y, String direction) {
    super(x,y,ROW_HEIGHT-20,direction);
    setHeight(ROW_HEIGHT-25);
  }
  
  /**
   * resetSprite
   * This method ensures the Living Character's animation is continuous and avoids array out of bounds
   * @param currentSprite The index of the current image being used
   * @param sprites The array of all sprites
   */
  public int resetSprite(int currentSprite, BufferedImage[] sprites) {
    if (currentSprite == sprites.length - 1) {
      currentSprite = 0;
    } else {
      currentSprite ++;
    }
    return currentSprite;
  }
  
  /**
   * getHealth
   * This method extracts and returns the private variable "health" 
   * @return The health of the living character
   */ 
  public int getHealth() {
    return health;
  }
  
  /**
   * setHealth
   * This method changes the private variable "health" 
   * @param health The new health value for the living character
   */ 
  public void setHealth(int health) {
    this.health = health;
    if (this.health>100) {
      this.health = 100;
    }
  }
}