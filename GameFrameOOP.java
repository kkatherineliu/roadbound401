// *** imports *** //
import javax.swing.JFrame;

/** GameFrameOOP.java
  * The frame that holds the game elements
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 30, 2021
  */
class GameFrameOOP extends JFrame { 
  
  /*
   * CLASS VARIABLE
   * Game Screen
   */
  static GameAreaPanelOOP gamePanel;
  
  /**
   * GameFrameOOP
   * This constructor automatically creates the frame dimensions 
   * and sets up the game panel (which contains graphics)
   */
  GameFrameOOP() { 
    super("RoadBound 401");  
    
    // *** sets the frame to full screen *** //
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1260,800);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    
    // *** sets up the game panel (where we put our graphics) *** //
    gamePanel = new GameAreaPanelOOP(this); 
    this.add(gamePanel); 
    
    // *** ensures the user will access this frame *** //
    this.setFocusable(false);
    this.setVisible(true);    
  } 
}




