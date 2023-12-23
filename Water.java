// *** imports *** // 
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/** Water.java
  * An class representing all the water on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class Water extends Row {
  /*
   * CLASS VARIABLE 
   */
  private int seconds;
  private Random rand = new Random(); 
  private int logType = rand.nextInt(3);
  private int delay = 1200;
  
  /**
   * Water
   * This constructor ensures each Water row is created with the necessary attributes
   * including the water bushes in the water
   * @param x The top left x-coordinate of the water's row
   * @param y The top left y-coordinate of the water's row
   * @param direction The direction the bushes in the water moves toward ("left" or "right")
   */
  Water(int x, int y, String direction) {
    super(x,y,new Color(71, 179, 237), direction);
    placeWaterBush();
  }
  
  /**
   * placeWaterBush
   * This method ensures the next waterbush is placed after a certain amount of time
   */
  public void placeWaterBush() {
    TimerTask task = new TimerTask() { // creating the task
      public void run() {
        elements.add(nextWaterBush());
      }
    };

    if (logType == 0) { // depending on the log type the speed of when it generates changes
      seconds = rand.nextInt(2) + 2; 
    } else if  (logType == 1) {
      seconds = rand.nextInt(3) + 2;
    } else if  (logType == 2) {
      seconds = rand.nextInt(2) + 3;
    }

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(task, delay, seconds * 1000);
    // running the task 
  } 
  
  /**
   * nextWaterBush
   * This method calculates when the next waterbush should be placed
   * @return The next waterbush that will be drawn
   */
  public WaterBush nextWaterBush(){
    int xCoordinate = 0;
    
    if (getDirection().equals("left")) {
      xCoordinate = SCREEN_WIDTH;
    }
   
    //creating the different types of logs/water bushes
    if (logType == 0) { // slow log 
      WaterBush waterBush = new WaterBush (xCoordinate, getY(), getDirection(), 2);
      if (getDirection().equals("right")) {
        waterBush.setX(-waterBush.getWidth());
      }
      return waterBush;
      
    } else if (logType ==  1) { // normal log 
      WaterBush waterBush = new WaterBush (xCoordinate, getY(), getDirection(), 3);
      if (getDirection().equals("right")) {
        waterBush.setX(-waterBush.getWidth());
      }
      return waterBush;

    } else if (logType == 2) { //fast log
      WaterBush waterBush = new WaterBush (xCoordinate, getY(), getDirection(), 4);
      if (getDirection().equals("right")) {
        waterBush.setX(-waterBush.getWidth());
      }
      return waterBush;
      
    }
    return null;
  }
}