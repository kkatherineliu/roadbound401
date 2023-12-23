// *** imports *** // 
import java.awt.Color;
import java.util.Random;

/** Grass.java
  * An class representing all the grass on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class Grass extends Row {
  /**
   * Grass
   * This constructor ensures each Grass row is created with the necessary attributes
   * including trees and abilities
   * @param x The top left x-coordinate of the grass' row
   * @param y The top left y-coordinate of the grass' row
   */
  Grass(int x, int y) {
    super(x,y,new Color(150, 224, 114), "still");
    treePosition();
    abilityPosition();
  }
  
  /**
   * TreePosition
   * This method calculates where the trees should be placed per grass row
   */
  public void treePosition(){ 
    Random rand = new Random(); 
    int treeOne, treeTwo, treeThree;
    
    // tree on left side
    treeOne = rand.nextInt(4);
    Tree firstTree = new Tree(EXTRA_SPACE+treeOne*(ROW_WIDTH/14), getY());
    elements.add(firstTree);
    
    // tree in the middle area
    for (int i = 1; i < rand.nextInt(3); i++) {
      treeTwo = rand.nextInt(6);
      Tree secondTree = new Tree((treeTwo+4)*(ROW_WIDTH/14), getY());
      elements.add(secondTree);
    }
    
    // tree on the right side
    treeThree = rand.nextInt(4);
    Tree thirdTree = new Tree((treeThree+10)*(ROW_WIDTH/14)-EXTRA_SPACE, getY());
    elements.add(thirdTree);
  }
  
  /**
   * abilityPosition
   * This method calculates where the ability should be placed 
   */
  public void abilityPosition() { 
    Random rand = new Random(); 
    
    // potion on the ground
    if (rand.nextInt(10) == 0) {
      int potionSpot = rand.nextInt(14);
      int potionX = EXTRA_SPACE + potionSpot*(ROW_WIDTH/14);
      if (emptySpot(potionX)) {
        HealthPotion potion = new HealthPotion(potionX, getY()+15);
        elements.add(potion);
      }
    }
    
    // ammunition on the ground
    if (rand.nextInt(20) == 0) {
      int rockPileSpot = rand.nextInt(14);
      int rockPileX = EXTRA_SPACE + rockPileSpot*(ROW_WIDTH/14);
      if (emptySpot(rockPileX)) {
        IncreaseDamage ammunition = new IncreaseDamage(rockPileX, getY()+20);
        elements.add(ammunition);
      }
    }
    
    // invincibility star on the ground
    if (rand.nextInt(40) == 0) { 
      int starSpot = rand.nextInt(14);
      int starX = EXTRA_SPACE + starSpot*(ROW_WIDTH/14);
      if (emptySpot(starX)) {
        Invincibility star = new Invincibility(starX, getY()+20);
        elements.add(star);
      }
    }
  }
  
  /**
   * emptySpot
   * This method determines if the current spot is empty (no trees)
   * @param x The x coordinate of the ability
   * @return true if there is no tree there
   */
  public boolean emptySpot(int x) { 
    for (int i = 0; i<elements.size(); i++) {
      if ((x >= elements.get(i).getX()) && (x <= elements.get(i).getX() + elements.get(i).getWidth())) {
        return false;
      }
    }
    return true;
  }
}