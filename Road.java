// *** imports *** // 
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/** Road.java
  * An class representing all the roads on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class Road extends Row {
  /*
   * CLASS VARIABLE
   */
  private Random rand = new Random(); 
  private int seconds;
  private int vehicleType = rand.nextInt(3);
  private int delay;
  
  /**
   * Road
   * This constructor ensures each Road is created with the necessary attributes
   * including the collection of vehicles on the Road
   * @param x The top left x-coordinate of the road's row
   * @param y The top left y-coordinate of the road's row
   * @param direction The direction the vehicles on the road moves toward ("left" or "right")
   */
  Road(int x, int y, String direction) {
    super(x,y,new Color(73,80,87), direction);
    placeCar();
  }
  
  /**
   * placeCar
   * This method ensures the next vehicle is placed after a certain amount of time
   */
  public void placeCar() {
    TimerTask task = new TimerTask() { // creating a task to place vehciles onto the screen
      public void run() {
        elements.add(nextVehicle());
      }
    };
    //** generating the seconds and delay for each car *//
    //small car
    if (vehicleType == 0) {
      seconds = rand.nextInt(2) + 2;
      delay = rand.nextInt(3) + 1;
      // normal car
    } else if (vehicleType == 1) { 
      seconds = rand.nextInt(3) + 2;
      delay = rand.nextInt(3) + 1;
      //truck
    } else if (vehicleType == 2) {
      seconds = rand.nextInt(3) + 3;
      delay = rand.nextInt(3) + 2;
    }
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(task, delay * 1000, seconds * 1000);
    // actually running the task depending on the generated seconds and delay
  } 
  
  /**
   * nextVehicle
   * This method calculates what vehicle should be placed next (small car, normal car or truck).
   * @return The vehicle which will be drawn next  
   */
  public Vehicle nextVehicle() {
    int xCoordinate = 0;
    
    if (getDirection().equals("left")) {
      xCoordinate = SCREEN_WIDTH;
    }
    //** creating cars based on generated number*//
    //small car
    if (vehicleType == 0) {
      SmallCar smallCar = new SmallCar (xCoordinate, getY(), getDirection());
      if (getDirection().equals("right")) {
        smallCar.setX(-smallCar.getWidth());
      }
      return smallCar;
      
      // normal car
    } else if (vehicleType == 1) {
      NormalCar normalCar = new NormalCar (xCoordinate, getY(), getDirection());
      if (getDirection().equals("right")) {
        normalCar.setX(-normalCar.getWidth());
      }
      return normalCar;
      
      //truck
    } else if (vehicleType == 2) {
      Truck truck = new Truck(xCoordinate, getY(), getDirection());
      if (getDirection().equals("right")) {
        truck.setX(-truck.getWidth());
      }
      return truck;
    }
    return null;
  }
}