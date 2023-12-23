// *** imports *** // 
import java.awt.Color;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import javax.sound.sampled.Clip;

/** Railroad.java
  * A class representing all the railroads on the map
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

public class Railroad extends Row {
  /*
   * CLASS VARIABLES
   */
  private Train train;
  private Warning warning; 
  private int delay = 500;
  private Random rand = new Random();
  public static Sounds sound = GameAreaPanelOOP.sound;
  public static Clip warningSound;
  
  /**
   * Railroad
   * This constructor ensures each Railroad is created with the necessary attributes (including it's train)
   * @param x The top left x-coordinate of the railroad's row
   * @param y The top left y-coordinate of the railroad's row
   * @param direction The direction the train on the railroad ("left", "right")
   */
  Railroad(int x, int y, String direction) {
    super(x,y,new Color(50,50,50), direction);
    warningSound = sound.load(2);
    placeTrain();
  }
  
  /**
   * placeTrain
   * This method ensures the next train is placed after the warning signs 
   */
  public void placeTrain() {
    TimerTask task = new TimerTask() { // creating task to add a train
      public void run() {
        if (elements.size()>0) {
          elements.add(nextTrain());
        }
      }
    };
    
    TimerTask warningTask = new TimerTask() { // creating task to add a warning sign
      public void run() {
        elements.add(nextWarning());
        if ((getY()>0) && (getY()+getHeight()<1260)) {
          sound.play(warningSound);
        }
      }
    };
    int seconds = rand.nextInt(10) + 10; //calculating when the next trian should be placed
    Timer timer = new Timer();
    timer.schedule(warningTask, seconds*1000);
    timer.scheduleAtFixedRate(task, delay, (seconds*1000)+2000); 
    //placing the train 2 seconds after the warning sign
  }

  
  /**
   * nextTrain
   * This method calculates where the next train should be placed
   * @return The train that will be drawn
   */
  public Train nextTrain(){
    int xCoordinate = SCREEN_WIDTH+100;
    if (getDirection().equals("left")) { // creating new trains based on direction
      train = new Train(xCoordinate,getY(),getDirection());

    } else if (getDirection().equals("right")){
      train = new Train(xCoordinate,getY(),getDirection());
      train.setX(-(train.getWidth()+100));
    }
    return train;
  }

  /**
  * nextWarning
  * This method calculates where the next warning should be placed
  * @return The warning that will be drawn
  */
  public Warning nextWarning(){ // creating warning signs based on direction
    if (getDirection().equals("left")) {
      warning = new Warning(SCREEN_WIDTH-EXTRA_SPACE-SCREEN_WIDTH/14, getY(), SCREEN_WIDTH/14, SCREEN_WIDTH/14);

    } else if (getDirection().equals("right")) {
      warning = new Warning(EXTRA_SPACE, getY(), SCREEN_WIDTH/14, SCREEN_WIDTH/14);
    }
    return warning;
  }
  
  /**
   * draw
   * This method displays the Railroad and its elements onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) { 
     // draws extra space on both ends
    Color spaceColour = new Color(230, 232, 237);
    g.setColor(spaceColour);
    g.fillRect(getX(), getY(), EXTRA_SPACE, getHeight());
    g.fillRect(getX()+ROW_WIDTH, getY(), EXTRA_SPACE, getHeight());

    // draws main row with train tracks
    g.setColor(getColour());
    g.fillRect(getX()+EXTRA_SPACE,getY(),ROW_WIDTH,getHeight());
    g.setColor(Color.WHITE);
    for (int i = getX()+EXTRA_SPACE; i<getX() + getWidth()-EXTRA_SPACE; i+=25) {
      g.fillRect(i,getY()+10,5,getHeight()-20);
    }
    
    // draws all the row's elements
    for (int i = 0; i<elements.size(); i++) {
      elements.get(i).draw(g);
    }
  }
}