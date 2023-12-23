// *** imports *** //
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/** EndMouseListener.java
  * This allows the user to interact with the end game screen
  * by pressing within the rectangle that dictates a button
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 10, 2021
  */
class EndMouseListener implements MouseListener {
  /*
   * CLASS VARIABLE
   */
  private JFrame frame;
  
  /**
   * EndMouseListener
   * This constructor ensures the input relates to the given frame 
   * @param frame The frame that should be watched
   */
  EndMouseListener(JFrame frame) {
    this.frame = frame;
  }
  
  /**
   * mouseClicked
   * This method checks when the mouse is clicked
   * and if the area falls within the section of a button
   * If so, the consequent frame is opened 
   * @param e The mouse click
   */
  public void mouseClicked(MouseEvent e) {
    Sounds sound = new Sounds();
    
    // mapping the dimensions of the play again button
    if ((e.getX() > 260) && (e.getX() < 260+291)) {
      if ((e.getY() > 682) && (e.getY() < 682+81)) {
        sound.play();
        new GameFrameOOP();
        frame.dispose();
      }
    }
    
    // mapping the dimensions of the main menu button
    if ((e.getX() > 727) && (e.getX() < 727+291)) {
      if ((e.getY() > 680) && (e.getY() < 680+81)) {
        sound.play();
        new StartingFrameOOP();
        frame.dispose();
      }
    }
  }
  
  /**
   * mousePressed
   * This method checks when the mouse is pressed
   * and executes commands accordingly
   * @param e The mouse press
   */
  public void mousePressed(MouseEvent e) {
  }
  
  /**
   * mouseReleased
   * This method checks when the mouse is released
   * and executes commands accordingly
   * @param e The mouse release
   */
  public void mouseReleased(MouseEvent e) {
  }
  
  /**
   * mouseEntered
   * This method checks when the mouse enters
   * and executes commands accordingly
   * @param e The mouse entrance
   */
  public void mouseEntered(MouseEvent e) {
  }
  
  /**
   * mouseExited
   * This method checks when the mouse exits
   * and executes commands accordingly
   * @param e The mouse exit
   */
  public void mouseExited(MouseEvent e) {
  }
} 

