// *** imports *** //
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.sound.sampled.Clip;

/** PlayerKeyListener.java
  * This allows the player to move based on keyboard input
  * by detecting which keys are pressed and running the appropriate method
  * This also moves the map to roughly around the player
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 30, 2021
  */

class PlayerKeyListener implements KeyListener {
  
  /*
   * CLASS VARIABLES
   * reference to items effected by keyboard press
   */
  private Player player;
  private Projectile[] rocks;
  private int currentRock;
  private ArrayList<Row> rows = GameAreaPanelOOP.getAllRows();
  public static Sounds sound = GameAreaPanelOOP.sound;
  public static Clip shoot;
  
  /**
   * PlayerKeyListener
   * This constructor ensures the listener is based on the identified Player
   * @param player The player that will be affected
   * @param rocks The projectiles that the will shot (also based on the same player)
   * @param currentRock The index of the current rock 
   */
  PlayerKeyListener (Player player, Projectile[] rocks, int currentRock) {
    this.player = player;
    this.rocks = rocks;
    this.currentRock = currentRock;
    shoot = sound.load(3);
  }
  
  /**
   * keyTyped
   * This method moves the screen and player based on keyboard input WASD
   * @param e The key that has been typed
   */
  public void keyTyped(KeyEvent e) {
    Row row = rows.get(0);
    
    // *** move left *** //
    if ((e.getKeyChar() == 'a') && (!treeBlocking("left"))) {  
      GameAreaPanelOOP.moveScreen("right", player.getSpeed());
      
      // moves the player if it's not at the edge of the row's extra space (map limit)
      if (player.getX() > row.EXTRA_SPACE) {
        player.setDirection("left");
        if (row.getX()+row.getWidth() <= row.SCREEN_WIDTH) {
          player.moveLeft(player.getSpeed()*3); 
        } else {
          player.moveLeft(player.getSpeed()); 
        }
      }
      
      // *** move down *** //
    }  if ((e.getKeyChar() == 's') && (!treeBlocking("down"))) {
      player.setDirection("backward");
      player.moveDown(player.getSpeed()*3);
      
      // *** move right *** //
    }  if ((e.getKeyChar() == 'd') && (!treeBlocking("right"))) {
      GameAreaPanelOOP.moveScreen("left", player.getSpeed()); 
      
      // moves the player if it's not at the edge of the row's extra space (map limit)  
      if (player.getX() + player.getWidth() < row.SCREEN_WIDTH-row.EXTRA_SPACE) {
        player.setDirection("right");
        if (row.getX() >= 0) {
          player.moveRight(player.getSpeed()*3);
        } else {
          player.moveRight(player.getSpeed());
        }
      }
      
      
      // *** move up *** //
    } if ((e.getKeyChar() == 'w') && (!treeBlocking("up"))) {
      player.setDirection("forward");
      
      // moves the player if the gate isn't closed
      if (player.collide() instanceof Gate) {
        if (((Gate)(player.collide())).getOpen()) { 
          player.moveUp(player.getSpeed()*3); 
        }
      } else {
        player.moveUp(player.getSpeed()*3); 
      }
    }
  }
  
  /**
   * keyReleased
   * This method detects when a key has been released and exeuctes commands accordingly
   * Specifically for shooting projectiles by pressing the space bar
   * @param e The key that has been released
   */
  public void keyReleased(KeyEvent e) { 
    if (e.getKeyCode() == 32) {
      Sounds sound = new Sounds();
      
      rocks[currentRock].setDirection(player.getDirection());
      rocks[currentRock].setX(player.getX());
      rocks[currentRock].setY(player.getY() + player.getHeight()/2);
      rocks[currentRock].setVisible(true);
      sound.play(shoot);
      
      if (currentRock == rocks.length - 1) {
        currentRock = 0;
      } else {
        currentRock ++;
      }
      
    }
  }
  
  /**
   * keyPressed
   * This method detects when a key has been pressed and exeuctes commands accordingly
   * @param e The key that has been pressed
   */
  public void keyPressed(KeyEvent e) {
  }
  
  /**
   * treeBlocking
   * This method detects where a tree is (relative to the player)
   * @param direction The direction to be checked ("left", "right", "up", "down")
   * @return true if the tree is in that position, false otherwise
   */
  public boolean treeBlocking(String direction) {
    GameObject collidedObject = player.collide();
    
    if (collidedObject instanceof Tree) {
      if (direction.equals("left")) {
        if (collidedObject.getX() < player.getX()) {
          return true;
        } 
      } else if (direction.equals("right")) {
        if (collidedObject.getX() > player.getX()) {
          return true;
        }
      } else if (direction.equals("up")) {
        if (collidedObject.getY() < player.getY()) {
          return true;
        }
      } else if (direction.equals("down")) {
        if (collidedObject.getY() > player.getY())  {
          return true;
        }
      }
    }
    return false;
  }
}
