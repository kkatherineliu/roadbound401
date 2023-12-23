// *** imports *** //
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

/** InstructionFrame.java
  * The instructione frame displayed when the "how to play" button is clicked on the starting frame
  * Allows user to view instructions
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 10, 2021
  */

class InstructionFrame extends JFrame { 
  /*
   * CLASS VARIABLE
   */
  private JFrame thisFrame;
  
  /**
   * InstructionFrame
   * This constructor automatically creates and displays all elements on the instruction frame
   */
  InstructionFrame() { 
    // *** creates a JFrame *** //
    super("Instructions");
    this.thisFrame = this;
    
    // *** configures the window // ***
    this.setSize(1260,800);    
    this.setLocationRelativeTo(null);
    this.setResizable (false); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // *** creates a Panel to display things *** // 
    JPanel instructionPanel = new JPanel();
    instructionPanel.setLayout(new BorderLayout());
    
    // *** adds background *** //
    ImageIcon icon = new ImageIcon("instructionPage.png");
    JLabel label = new JLabel();
    label.setIcon(icon);
    instructionPanel.add(label);
    this.getContentPane().add(instructionPanel);
    
    // *** listeners *** //
    RulesMouseListener mouseListener = new RulesMouseListener(this);
    instructionPanel.addMouseListener(mouseListener);
    
    // *** adds the main panel to the frame *** //
    this.add(instructionPanel);
    
    // *** ensures the user will access this frame *** //
    this.setVisible(true);
    this.requestFocusInWindow();
  }
}