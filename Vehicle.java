/** Vehicle.java
  * An abstract class representing all vehicles on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 24, 2021
  */
abstract class Vehicle extends MovingGameObjects {
  
  /**
   * Vehicle
   * This constructor ensures each Vehicle is created with the necessary attributes
   * @param x The top left x-coordinate of the vehicle's skin
   * @param y The top left y-coordinate of the vehicle's skin
   * @param width The total width of the vehicle
   * @param direction The direction the vehicle moves toward ("left" or "right")
   */
  Vehicle(int x, int y, int width, String direction) {
    super(x,y,width,direction);
  }
}