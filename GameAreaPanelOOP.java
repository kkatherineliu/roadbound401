// *** imports *** //
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.sound.sampled.Clip;

/** GameAreaPanelOOP.java
  * Main game panel
  * Updates game object statuses
  * Draws every aspect of the game onto the screen
  * Moves the game objects and screen
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 30, 2021
  */
class GameAreaPanelOOP extends JPanel {
  
  /*
   * CLASS VARIABLES
   */
  public GameFrameOOP gameFrame;
  public static Player player;
  public static Projectile[] rocks = new Projectile[50];
  public int currentRock;
  public Grass grass;
  public static int screenSpeed = 1;
  public static ArrayList<Row> allRows = new ArrayList<>(); 
  public static ArrayList<Row> rowsPassed = new ArrayList<>();
  public int currentRowNum = 0;
  public int passedRows;
  
  // *** Sounds *** //
  public static Sounds sound = new Sounds();
  public static Clip gameOver;
  public static Clip collectAbility;
  
  /**
   * GameAreaPanelOOP
   * This constructor starts the game loop and initializes all of the key components
   * (sets up game objects, listeners, JPanel requirements)
   * @param gameFrame The GameFrameOOP this Panel exists on
   */
  GameAreaPanelOOP(GameFrameOOP gameFrame) {
    this.gameFrame = gameFrame;
    
    /*
     * INITIALIZING GAME OBJECTS
     */
    gameOver = sound.load(1);
    collectAbility = sound.load(4);
    
    grass = new Grass(0,0);
    grass.setX(-(grass.getWidth()-grass.SCREEN_WIDTH)/2);
    grass.setY(grass.SCREEN_HEIGHT-grass.getHeight());
    allRows.add(grass);
    
    player = new Player();
    
    for (int i = 0; i<rocks.length; i++) {
      rocks[i] = new Projectile(player);
    }
    
    // *** Listeners *** //
    PlayerKeyListener keyListener = new PlayerKeyListener(player, rocks, currentRock);
    this.addKeyListener(keyListener);
    
    // *** JPanel *** //
    this.setFocusable(true);
    this.requestFocusInWindow();
    
    // *** Starting game loop *** //
    Thread t = new Thread(new Runnable() { public void run() { animate(); }}); 
    t.start();
    
  }
  
  /**
   * animate
   * This method is the main game loop, it updates the state of the game objects
   */
  public void animate() { 
    do { 
      
      /*
       * RUNNING THE GAME "INFINITELY"
       * routine tasks
       */
      
      // *** row generation *** //
      // starts the game with 5 "safe" rows of grass
      if (allRows.size() < 5) {
        for (int i = 0; i<5; i++) {
          Grass grassStart = new Grass(allRows.get(allRows.size()-1).getX(), 
                                       allRows.get(allRows.size()-1).getY()-grass.ROW_HEIGHT);
          allRows.add(grassStart);
        }
        
        // generates the next set of rows 
      } else if ((allRows.get(allRows.size()-1).getY()>0)) {
        allRows.get(allRows.size()-1).nextRow();
      }
      
      // *** removing rows and contained elements *** //
      for (int i = 0; i<allRows.size(); i++) {
        if (allRows.get(i).outOfBounds()) {
          allRows.remove(i);
        }
        for (int j = 0; j<allRows.get(i).elements.size(); j++) {
          if (allRows.get(i).elements.get(j) instanceof Enemy) {
            if (((Enemy) (allRows.get(i).elements.get(j))).getHealth()<=0) {
              allRows.get(i).elements.remove(j);
            }
          } else if (allRows.get(i).elements.get(j).outOfBounds()) {
            allRows.get(i).elements.remove(j);
          }
        }
      }
      
      // *** keeping track of the total rows passed *** //
      player.passRow();
      passedRows = rowsPassed.size()-4;
      if (rowsPassed.size()-currentRowNum>=10) {
        player.setInvincible(false);
      }
      
      // moves everything on screen down
      moveScreen("down", screenSpeed);
      
      /*
       * MOVING GAME OBJECTS 
       * moving them independantly from the screen
       */
      
      // *** moving based on their direction and speed *** //
      for (int i = 0; i<allRows.size(); i++) {
        Row row = allRows.get(i);
        for (int j = 0; j<row.elements.size(); j++) {
          if (row.elements.get(j) instanceof MovingGameObjects) {
            MovingGameObjects object = (MovingGameObjects) (row.elements.get(j));
            if (object.getDirection().equals("left")) {
              object.moveLeft(object.getSpeed());
            } else if (object.getDirection().equals("right")) {
              object.moveRight(object.getSpeed());
            }
            
            // flips direction of enemy when it reaches the edge
            if (row.elements.get(j) instanceof Enemy) {
              Enemy enemy = (Enemy) (row.elements.get(j));
              if (enemy.outOfBounds()) {
                if (enemy.getDirection().equals("right")) {
                  enemy.setDirection("left");
                  enemy.moveLeft(enemy.getWidth());
                } else if (enemy.getDirection().equals("left")) {
                  enemy.setDirection("right");
                  enemy.moveRight(enemy.getWidth());
                }
                enemy.loadImage();
              }
            }
          } 
        }
      }
      
      // *** opening the gate *** //
      for (int i = 0; i<allRows.size(); i++) {
        if (allRows.get(i) instanceof Field) {
          ((Field) (allRows.get(i))).gateAccess();
        }
      }
      
      /**
       * COLLISON
       * checking if there is collison
       * updating GameObject statuses
       */
      
      GameObject collidedObject = player.collide();
      
      for (int i = 0; i<allRows.size(); i++) {
        Rectangle playerSpace = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle rowSpace = new Rectangle(allRows.get(i).getX(), allRows.get(i).getY(), 
                                           allRows.get(i).getWidth(), allRows.get(i).getHeight());
        
        if (playerSpace.intersects(rowSpace)) {
          
          // *** increasing player stats and removing ability *** //
          if (allRows.get(i) instanceof Grass) {
            if (collidedObject instanceof Ability) {
              sound.play(collectAbility); //playing ability sound
              allRows.get(i).elements.remove(collidedObject);
              if (collidedObject instanceof HealthPotion) {
                player.setHealth(player.getHealth()+10); //adding health 
              } else if (collidedObject instanceof Invincibility) {
                player.setInvincible(true); //making the player invincible for a while
                currentRowNum = rowsPassed.size();
              } else if (collidedObject instanceof IncreaseDamage) {
                for (int j = 0; j<rocks.length; j++) {
                  rocks[j].setDamage(rocks[j].getDamage()+5); //making the damage +5 more powerful
                }
              }
            }
            
            // *** lowering player health and removing enemy *** //
          } else if (allRows.get(i) instanceof Field) {
            if (collidedObject instanceof Enemy) {
              player.setHealth(player.getHealth()-((Enemy)(collidedObject)).getAttackDamage());
              allRows.get(i).elements.remove(collidedObject);
            }
            
          } else if (allRows.get(i) instanceof Water) {
            if (collidedObject instanceof WaterBush) {
              
              // *** moving the player along the log *** //
              WaterBush log = (WaterBush)(collidedObject);
              
              if (log.getDirection().equals("left")) {
                player.moveLeft(log.getSpeed()/2); 
                moveScreen("right", log.getSpeed()/2);
                if (allRows.get(i).getX()>=0) {
                  player.moveLeft(log.getSpeed()/2);
                }
                if (player.getX() <= player.EXTRA_SPACE) {
                  player.setHealth(0);
                }
              } else {
                player.moveRight(log.getSpeed()/2); 
                moveScreen("left", log.getSpeed()/2);
                if (allRows.get(i).getX()+allRows.get(i).getWidth() <= 1260) {
                  player.moveRight(log.getSpeed()/2);
                }
                if (player.getX() >= (player.SCREEN_WIDTH - player.EXTRA_SPACE)) {
                  player.setHealth(0);
                }
              }
              
              // *** end game scenario: falling into water *** //
            } else {
              if (!(player.getInvincible())) {
                player.setHealth(0);
              }
            }
            
            // *** losing health for vehicle collision *** //
          } else if ((allRows.get(i) instanceof Road) || (allRows.get(i) instanceof Railroad)) {
            if ((collidedObject instanceof Vehicle) && (!(collidedObject instanceof WaterBush))) {
              player.setHealth(player.getHealth()-1);
            }
          }
        }
      }
      
      // *** delaying for processing time *** //
      try { 
        Thread.sleep(25);
      } 
      catch (Exception exc) {
        System.out.println("Thread Error");
      }
      
      // *** requesting for repaint *** //
      this.repaint();
      
    } while ((player.getHealth()>0) && (!(player.outOfBounds())));
    sound.close(); 
    sound.play(gameOver);
    
    // *** closes sounds *** //
    collectAbility.close();
    PlayerKeyListener.shoot.close();
    if (Railroad.warningSound != null) {
      Railroad.warningSound.close();
    }
    
    // *** opening end game page *** //
    new EndFrame(passedRows, player.getHealth());
    gameFrame.dispose();
    
    // *** resets variables *** //
    allRows.clear();
    rowsPassed.clear();
    Row.rowCount = 0;
    Row.fieldCount = 0;
    
    // *** closes sounds *** //
    try { 
      Thread.sleep(2000);
    } 
    catch (Exception exc) {
      System.out.println("Thread Error");
    }
    gameOver.close();
  }
  
  /**
   * paintComponent
   * This method displays all the components to the screen each time it's refreshed
   * @param g The object used to draw to the screen
   */
  public void paintComponent(Graphics g) { 
    super.paintComponent(g); 
    setDoubleBuffered(true); 
    
    /*
     * DRAWING ALL OBJECTS ON THE SCREEN
     */
    
    // draws the rows and contained elements
    for (int i = 0; i<allRows.size(); i++) {
      if (allRows.get(i).getY()>-allRows.get(i).EXTRA_SPACE) {
        allRows.get(i).draw(g);
      }
    }
    
    // draws the bullets
    for (int i=0; i<rocks.length; i++){
      if (rocks[i].getVisible()){
        rocks[i].draw(g);
      }
    }
    
    // draws the player
    player.draw(g);
    
    // draws the player stats 
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.PLAIN, 25)); //setting font, text style and text size
    g.drawString("Rows: " + passedRows, 25, 35);
    g.drawString("Health: " + player.getHealth() + "/100", 25, 75);
    g.drawString("Damage: " + rocks[0].getDamage(), 25, 115);
    
  } 
  
  /**
   * moveScreen
   * This method moves the background to reveal more of the map
   * @param direction The direction the screen should move towards ("left", "right", or "down")
   * @param moveValue The number of pixels the screen should move
   */
  public static void moveScreen(String direction, int moveValue) {
    Row row = allRows.get(0);
    
    if (direction.equals("right")) {
      // checks if the left limit of our map has been reached
      if (row.getX() <= 0) {
        // moves arraylist of rows to the right 
        for (int i = 0; i<allRows.size(); i++) {
          allRows.get(i).moveRight(moveValue);
        }
      }
    } else if (direction.equals("left")) {
      // checks if the limit of our map has been reached
      if (row.getX() + row.getWidth() >= row.SCREEN_WIDTH) {
        // moves arraylist of rows to the left 
        for (int i = 0; i<allRows.size(); i++) {
          allRows.get(i).moveLeft(moveValue);
        }
      }
    } else {
      for (int i = 0; i<allRows.size(); i++) {
        allRows.get(i).moveDown(moveValue);
      }
      
      // moves the player and projectiles
      player.moveDown(moveValue);
      for (int i=0; i<rocks.length; i++) {
        if (rocks[i].getVisible()){
          rocks[i].attack();
          rocks[i].moveDown(screenSpeed);
          if (rocks[i].outOfBounds()) {
            rocks[i].setVisible(false);
          }
        }
      }
    }
  }
  
  /**
   * getAllRows
   * This method extracts and returns the variable "allRows" 
   * @return All the rows being drawn to the screen
   */
  public static ArrayList<Row> getAllRows() {
    return allRows;
  }
}
