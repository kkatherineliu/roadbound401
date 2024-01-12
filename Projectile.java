// *** imports *** // 
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Rectangle;

/** Projectile.java
  * A projectile -- the object the player uses for attacks
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 31, 2021
  */

public class Projectile extends MovingGameObjects { 
  
  /*
   * CLASS VARIABLES
   */
  private Player player;
  private int damage;
  private ArrayList<Row> rows = GameAreaPanelOOP.getAllRows();
  private boolean visible = false;
  
  /**
   * Projectile
   * This constructor ensures the projectile is coordinated with the player
   * @param player The Player that will use the projectile to attack
   */
  Projectile(Player player) {
    super(player.getX(), player.getY(), ROW_HEIGHT/4, player.getDirection());
    this.player = player;
    setHeight(ROW_HEIGHT/4);
    loadImage();
    setSpeed(8);
    damage = 10;
  }
  
  /**
   * attack
   * This method shoots projectiles in the direction the Player faces
   * It also calls all the actions required depending on collison
   * (gaining health, harming enemies etc.)
   */
  public void attack() {
    
    // *** shoots projectile in the direction the player is currently facing *** //
    if (getDirection().equals("left")) {
      moveLeft(getSpeed());
    } else if (getDirection().equals("right")) {
      moveRight(getSpeed());
    } else {
      visible = false;
    }
    
    // *** removes the train/warning sign that was hit *** //
    GameObject object = collide();
    
    for (int i = 0; i<rows.size(); i++) {
      if (rows.get(i) instanceof Railroad) {
        ArrayList<GameObject> collidedObject = rows.get(i).elements;
        if ((object instanceof Warning) || (object instanceof Train)) {
          // increases player health
          collidedObject.clear();
          player.setHealth(player.getHealth()+20);
        }
        
        // *** lowers enemy health *** //
      } else if (rows.get(i) instanceof Field) {
        if (object instanceof Enemy) {
          // decreases player health
          Enemy enemy = (Enemy) (object);
          enemy.setHealth(enemy.getHealth()-damage);
        }
      }
    }
  }
  
  
  
  /**
   * collide
   * This method checks if the projectile has collided with anything
   * @return The object that has been hit
   */
  public GameObject collide() {
    if (visible) {
      Rectangle projectile = new Rectangle(getX(), getY(), getWidth(), getHeight());
      
      for (int i = 0; i<rows.size(); i++) {
        ArrayList<GameObject> elements = rows.get(i).elements;
        for (int j = 0; j<elements.size(); j++) {
          Rectangle collidedObject = new Rectangle(elements.get(j).getX(), elements.get(j).getY(), 
                                                   elements.get(j).getWidth(), elements.get(j).getHeight());
          
          if (projectile.intersects(collidedObject)) {
            visible = false;
            return elements.get(j);
          }
        }
      }
    }
    return null;
  }
  
  /**
   * loadImage
   * This method loads the image corresponding to projectile's appearance
   */
  public void loadImage() {
    try {
      skin = ImageIO.read(new File("rock.png"));
      
    } catch(Exception e) { 
      System.out.println("Error Loading Projectile Image.");
    }
  }
  
  /**
   * draw
   * This method displays the series of projectiles
   * @param g The object used to draw elements to the screen
   */ 
  public void draw(Graphics g) {
    if (visible) {
      g.drawImage(skin, getX(), getY(), getWidth(), getHeight(), null);
    }
  }
  
  /**
   * getVisible
   * This method extracts and returns the private variable "visible" 
   * @return true If the bullet should be visible
   */ 
  public boolean getVisible() {
    return visible;
  }
  
  /**
   * setVisible
   * This method changes the private variable "visible" 
   * @param visible If the projectile is visible
   */ 
  public void setVisible(boolean visible) {
    this.visible = visible;
  }
  
  /**
   * getDamage
   * This method extracts and returns the private variable "damage" 
   * @return The attack damage of the projectile
   */ 
  public int getDamage() {
    return damage;
  }
  
  /**
   * setDamage
   * This method changes the private variable "damage" 
   * @param damage The new damage value for the projectile
   */ 
  public void setDamage(int damage) {
    this.damage = damage;
  }
}
