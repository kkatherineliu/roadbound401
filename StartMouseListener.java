// *** imports *** //
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

/** StartMouseListener.java
  * This allows the user to interact with the starting screen
  * by pressing within the rectangle that dictates a button
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 10, 2021
  */
class StartMouseListener implements MouseListener {
  /*
   * CLASS VARIABLE
   */
  private JFrame frame;
  
  /**
   * StartMouseListener
   * This constructor ensures the input relates to the given frame 
   * @param frame The frame that should be watched
   */
  StartMouseListener(JFrame frame) {
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
    
    // how to play button
    if ((e.getX() > 160) && (e.getX() < 160+395)) {
      if ((e.getY() > 490) && (e.getY() < 490+120)) {
        new InstructionFrame();
        frame.dispose();
      }
    }
    
    // start button
    if ((e.getX() > 700) && (e.getX() < 700+395)) {
      if ((e.getY() > 488) && (e.getY() < 488+120)) {
        new GameFrameOOP();
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

