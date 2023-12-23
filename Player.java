// *** imports *** // 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;

/** Player.java
  * A player -- the character the user can control
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */

public class Player extends LivingCharacter {
  
  /*
   * CLASS VARIABLES
   */
  private BufferedImage skin3;
  private int backCols = 4;
  private BufferedImage[] spritesBack = new BufferedImage[backCols*2]; 
  private int currentSpriteBack = 0;
  private ArrayList<Row> rowsPassed = GameAreaPanelOOP.rowsPassed;
  private ArrayList<Row> rows = GameAreaPanelOOP.getAllRows();
  private boolean invincible;
  
  /**
   * Player
   * This constructor ensures the Player is created with the necessary attributes
   * and centered in the screen
   */
  Player() {
    super(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, "forward");
    setX(SCREEN_WIDTH/2 - getWidth()/2);
    setSpeed(8);
    setHealth(100);
    invincible = false;
    loadImage();
  }
  
  /**
   * loadImage
   * This method loads the images corresponding to the players's orientation
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("Player_Right.png"));
      skin2 = ImageIO.read(new File("Player_Left.png"));
      skin3 = ImageIO.read(new File("Player_Back.png"));
      
      // loading sprites for left and right movement
      for (int i = 0; i < cols; i++) {
        spritesRight[i*2] = skin.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        spritesRight[i*2+1] = skin.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        spritesLeft[i*2] = skin2.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        spritesLeft[i*2+1] = skin2.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
      }
      
      // loading sprites for forwards and backward movement
      for (int i = 0; i < backCols; i++) {
        spritesBack[i*2] = skin3.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
        spritesBack[i*2+1] = skin3.getSubimage(i * IMAGE_WIDTH,0,IMAGE_WIDTH,IMAGE_HEIGHT);
      }
    } catch(Exception e) { 
      System.out.println("Error Loading Images.");
    }
  }
  
  /**
   * outOfBounds
   * This method determines if the player touches the bottom boundary
   * @return true if the player is too close/past the bottom boundary
   */
  public boolean outOfBounds() {
    if (getY() + getHeight() > SCREEN_HEIGHT) {
      return true;
    }
    return false;
  }
  
  /**
   * moveUp
   * This method moves the player upwards
   * Changes the direction and sprite displayed accordingly
   * @param move The distance (in pixels) the moving game object travels upwards
   */
  public void moveUp(int move) {
    setY(getY()-move);
    currentSpriteBack = resetSprite(currentSpriteBack, spritesBack);
  } 
  
  /**
   * moveDown
   * This method moves the player downards
   * Changes the direction and sprite displayed accordingly
   * @param move The distance (in pixels) the moving game object travels downards
   */
  public void moveDown(int move) {
    setY(getY()+move);
    if (move%getSpeed() == 0)  {
      currentSpriteBack = resetSprite(currentSpriteBack, spritesBack);
    }
  }
  
  /**
   * moveLeft
   * This method moves the player to the left
   * Changes the direction and sprite displayed accordingly
   * @param move The distance (in pixels) the moving game object travels to the left
   */
  public void moveLeft(int move) {
    setX(getX()-move);
    if (move%getSpeed() == 0)  {
      currentSpriteLeft = resetSprite(currentSpriteLeft, spritesLeft);
    }
  }
  
  /**
   * moveRight
   * This method moves the player to the right
   * Changes the direction and sprite displayed accordinglys
   * @param move The distance (in pixels) the moving game object travels to the right
   */
  public void moveRight(int move) {
    setX(getX()+move);
    if (move%getSpeed() == 0) {
      currentSpriteRight = resetSprite(currentSpriteRight, spritesRight);
    }
  }
  
  /**
   * draw
   * This method displays the Player onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    if (getDirection().equals("left")) {
      g.drawImage(spritesLeft[currentSpriteLeft], getX(), getY(), getWidth(), getHeight(), null);
    } else if (getDirection().equals("right")) {
      g.drawImage(spritesRight[currentSpriteRight], getX(), getY(), getWidth(), getHeight(), null);
    } else if ((getDirection().equals("forward"))) {
      g.drawImage(spritesBack[currentSpriteBack], getX(), getY(), getWidth(), getHeight(), null);
    } else if (getDirection().equals("backward")) {
      g.drawImage(spritesBack[currentSpriteBack], getX(), getY(), getWidth(), getHeight(), null);
    }
    
    if (invincible) {
      g.setColor(Color.WHITE);
      g.drawRect(getX()+getWidth()/6, getY(), getWidth()-getWidth()/3, getHeight());
    }
    
  }
  
  /**
   * collide
   * This method checks if the Player has collided with anything
   * It takes into account the "empty space" surrounding the sprite images
   * @return The object that the Player collided with/overlaps
   */
  public GameObject collide() {
    if (!invincible) {
      Rectangle playerSpace = new Rectangle(getX()+getWidth()/6, getY(), getWidth()-getWidth()/3, getHeight());
      
      for (int i = 0; i<rows.size(); i++) {
        ArrayList<GameObject> elements = rows.get(i).elements;
        for (int j = 0; j<elements.size(); j++) {
          Rectangle collidedObject;
          if ((elements.get(j) instanceof Vehicle) && (!(elements.get(j) instanceof WaterBush))) {
            collidedObject = new Rectangle(elements.get(j).getX(), elements.get(j).getY()+10, 
                                           elements.get(j).getWidth(), elements.get(j).getHeight()-20);
          } else {
            collidedObject = new Rectangle(elements.get(j).getX(), elements.get(j).getY(), 
                                           elements.get(j).getWidth(), elements.get(j).getHeight());
          }
          if (playerSpace.intersects(collidedObject)) {
            return elements.get(j);
          }
        }
      }
    }
    return null;
  }
  
  /**
   * passRow
   * This method adds to the rowsPassed arraylist once the player has passed it
   */
  public void passRow() {
    for (int i = 0; i<rows.size(); i++) {
      Row row = rows.get(i);
      if (getY()+getHeight() < row.getY()) {
        if (!(rowsPassed.contains(row))) {
          rowsPassed.add(row);
        }
      }
    }
  }
  
  /**
   * getInvincible
   * This method extracts and returns the private variable "invincible" 
   * @return true if the player is invincible/immune to collision
   */ 
  public boolean getInvincible() {
    return invincible;
  }
  
  /**
   * setInvincible
   * This method changes the private variable "invincible" 
   * @param invincible If the player is invincible or not
   */ 
  public void setInvincible(boolean invincible) {
    this.invincible = invincible;
  }
}
