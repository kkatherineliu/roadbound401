// *** imports *** //
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/** StartingFrameOOP.java
  * The starting frame displayed before the game
  * Allows user to choose to start game or view instructions
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 30, 2021
  */

class StartingFrameOOP extends JFrame { 
  /*
   * CLASS VARIABLES
   */
  private JFrame thisFrame;
  public static Sounds sound = new Sounds();
  
  /**
   * StartingFrameOOP
   * This constructor automatically creates and displays all elements on the start menu
   */
  StartingFrameOOP() { 
    // *** creates a JFrame *** //
    super("Start Screen");
    this.thisFrame = this;
    
    // *** configures the window // ***
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1260,800);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setResizable (false); 
    
    // *** creates a Panel to display things *** // 
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    
    // *** adding background *** //
    ImageIcon icon = new ImageIcon("roadMainPage.png");
    JLabel label = new JLabel();
    label.setIcon(icon);
    mainPanel.add(label);
    this.getContentPane().add(mainPanel);
    
    // *** adds the main panel to the frame *** //
    this.add(mainPanel);
    
    // *** ensures the user will access this frame *** //
    this.setVisible(true);
    this.requestFocusInWindow();
    
    // *** listeners *** //
    StartMouseListener mouseListener = new StartMouseListener(this);
    mainPanel.addMouseListener(mouseListener);
  }
  
  // *** main method to begin the game from the start menu *** //
  public static void main(String[] args) { 
    new StartingFrameOOP();
    sound.play();
  }
}