/** Ability.java
  * An abstract class representing all abilities on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 9, 2021
  */

abstract class Ability extends GroundGameObjects {
  
  /**
   * Ability
   * This constructor ensures each Ability is created with the necessary attributes
   * @param x The top left x-coordinate of the ability's skin
   * @param y The top left y-coordinate of the ability's skin
   * @param width The total width of the ability
   * @param height The total height of the ability
   */
  Ability(int x, int y, int width, int height) {
    super(x,y,width,height);
  }
}