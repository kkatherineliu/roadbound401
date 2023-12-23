// *** imports for displaying graphics *** // 
import java.awt.Graphics;

/** Enemy.java
  * An abstract class representing all enemies on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

abstract class Enemy extends LivingCharacter {
  
  /*
   * CLASS VARIABLES
   */
  private int attackDamage;
  
  /**
   * Enemy
   * This constructor ensures each Enemy is created with the necessary attributes
   * @param x The top left x-coordinate of the enemy's skin
   * @param y The top left y-coordinate of the enemy's skin
   * @param direction The direction the enemy moves toward ("left" or "right")
   * @param attackDamage The damage the enemys deals upon impact
   */
  Enemy(int x, int y, String direction, int attackDamage) {
    super(x,y,direction);
    this.attackDamage = attackDamage;
  }  
  
  /**
   * moveLeft
   * This method moves the enemy towards the left
   * and updates the sprite displayed
   * @param move The distance (in pixels) the enemy travels to the left
   */
  public void moveLeft(int move) {
    setX(getX()-move);
    currentSpriteLeft = resetSprite(currentSpriteLeft, spritesLeft);
  }
  
  /**
   * moveRight
   * This method moves the enemy towards the right
   * and updates the sprite displayed
   * @param move The distance (in pixels) the enemy travels to the right
   */
  public void moveRight(int move) {
    setX(getX()+move);
    currentSpriteRight = resetSprite(currentSpriteRight, spritesRight);
  }
  
  /**
   * draw
   * This method displays the Enemy onto the screen
   * based on the direction it is facing
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    if (getDirection().equals("left")) {
      g.drawImage(spritesLeft[currentSpriteLeft], getX(), getY(), getWidth(), getHeight(), null);
    } else {
      g.drawImage(spritesRight[currentSpriteRight], getX(), getY(), getWidth(), getHeight(), null);
    }
  }
  
  /**
   * outOfBounds
   * This method determines if an enemy is out of bounds on the left or right
   * @return true if the enemy is off the screen
   */
  public boolean outOfBounds() {
    if ((getX()+getWidth() <= 0) || (getX() >= SCREEN_WIDTH)) {
      return true;
    }
    return false;
  }
  
  /**
   * getAttackDamage
   * This method extracts and returns the private variable "attackDamage" 
   * @return The attack damage of the enemy
   */ 
  public int getAttackDamage() {
    return attackDamage;
  }
  
  /**
   * setAttackDamage
   * This method changes the private variable "attackDamage" 
   * @param attackDamage The new attack damage value for the enemy
   */ 
  public void setAttackDamage(int attackDamage) {
    this.attackDamage = attackDamage;
  }
}