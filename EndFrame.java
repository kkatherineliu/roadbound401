// *** imports *** //
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;

/** EndFrame.java
  * The end frame displays when the character dies
  * Allows user to view stats, start over, or visit the main frame
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 10, 2021
  */

class EndFrame extends JFrame { 
  /*
   * CLASS VARIABLES
   */
  private int rows;
  private int health;
  private JFrame thisFrame;
  
  /**
   * EndFrame
   * This constructor automatically creates and displays all elements on the end screen
   * @param rows The total number of rows the player has passed
   * @param health The total health remaining of the player
   */
  EndFrame(int rows, int health) { 
    
    // *** initializes variables *** //
    super("Game Over");
    this.thisFrame = this;
    this.rows = rows;
    this.health = health;
    
    // *** configures the window // ***
    this.setSize(1260,800);    
    this.setLocationRelativeTo(null);
    this.setResizable (false); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // *** creates a Panel to display things *** // 
    JPanel endPanel = new JPanel();
    endPanel.setLayout(new BorderLayout());
    
    // *** adding background *** //
    ImageIcon icon = new ImageIcon("roadEndPage.png");
    JLabel label = new JLabel();
    label.setIcon(icon);
    endPanel.add(label);
    this.getContentPane().add(endPanel);
    
    // *** listeners *** //
    EndMouseListener mouseListener = new EndMouseListener(this);
    endPanel.addMouseListener(mouseListener);
    
    // *** adds the main panel to the frame *** //
    this.add(endPanel);
    
    // *** ensures the user will access this frame *** //
    this.setVisible(true);
    this.requestFocusInWindow();
  }
  
  /**
   * paint
   * This method displays the player's stats onto the screen
   * @param g The object used to draw to the screen
   */
  public void paint(Graphics g) {
    super.paint(g);
    g.setColor(Color.BLACK);
    g.setFont(new Font("Segoe", Font.BOLD, 50)); //Setting font, text style, font size
    g.drawString("Rows: " + rows, 520, 350);
    g.drawString("Health: " + health + "/100", 430, 450);
  }
}