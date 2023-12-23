// *** imports *** // 
import java.awt.Color;

/** Field.java
  * A class representing all the fields on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class Field extends Row {
  /*
   * CLASS VARIABLE
   */
  private SmallEnemy smallEnemy;
  private BigEnemy bigEnemy;
  private Gate gate;
  
  /**
   * Field
   * This constructor ensures each Field is created with the necessary attributes
   * including the gate and enemies
   * @param x The top left x-coordinate of the field 
   * @param y The top left y-coordinate of the field
   * @param direction The direction the elements of the field move towards initially ("left", "right")
   */
  Field(int x, int y, String direction) {
    super(x,y,new Color(134, 219, 48),direction);
    enemyAttack();
    gate = new Gate(x+EXTRA_SPACE,y);
    elements.add(gate);
  }
  
  /**
   * enemyAttack
   * This method creates and adds enemies on the field to attack the player
   */
  public void enemyAttack() {
    if (getDirection().equals("left")) { // creating enemies if the direction is left
      bigEnemy = new BigEnemy(1100, getY(),getDirection());
      smallEnemy = new SmallEnemy(1060,getY(),getDirection());
    } else if (getDirection().equals("right")) { // creating enemies if the direction is right
      bigEnemy = new BigEnemy(200, getY(),getDirection());
      smallEnemy = new SmallEnemy(160,getY(),getDirection());
    }
    elements.add(smallEnemy); 
    elements.add(bigEnemy);
  }
  
  /**
   * gateAccess
   * This method determines when the gate opens and closes (when the player can advance)
   */
  public void gateAccess() {
    if (elements.size() == 1) { //when all enemies are defeated the gate opens
      gate.setOpen(true);
    }
  }
}