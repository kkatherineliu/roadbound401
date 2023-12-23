// *** imports for displaying graphics *** // 
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/** Row.java
  * An abstract class representing all the rows on the map 
  * (road, water, grass, railroad and field)
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 May 25, 2021
  */

abstract class Row extends GameObject {
  
  /*
   * CLASS VARIABLES
   */
  private Color colour;
  private String direction;
  private Random rand = new Random();
  public static int rowCount = 0;
  public static int fieldCount = 0;
  private int consecutiveLanes = 4;
  private static Row lastRow;
  public ArrayList<GameObject> elements = new ArrayList<>();
  public static ArrayList<Row> rows;
  
  /**
   * Rows
   * This constructor ensures each Row is created with the necessary attributes
   * All the rows will be the same width and height, with empty spaces at the two sides
   * @param x The top left x-coordinate of the rectangle drawn to represent each row (space included)
   * @param y The top left y-coordinate of the rectangle drawn to represent each row
   * @param colour The colour of the row 
   * @param direction The direction the elements of the row move towards ("left", "right", "still")
   */
  Row(int x, int y, Color colour, String direction) {
    super(x,y,ROW_WIDTH+EXTRA_SPACE*2,ROW_HEIGHT);
    this.colour = colour;
    this.direction = direction;
    rows = GameAreaPanelOOP.getAllRows();
  }
  
  /**
   * nextRow
   * This method determines which row should be next (road, water, grass, field or railroad)
   * and adds it to the master list of all rows
   */
  public void nextRow() {
    int randomRow = rand.nextInt(10);
    
    // increasing the difficulty to once they pass more rows
    if (rowCount>60) {
      consecutiveLanes = rowCount/12;
    }
    
    lastRow = rows.get(rows.size()-1);
    if (lastRow.getY()>0) {
      // adding a set of rows
      for (int i = 0; i < 5; i++) {
        int lanes = 0;
        while (lanes==0) {
          lanes = rand.nextInt(consecutiveLanes);
        }
        
        // adds road
        if (i == 0) { 
          for (int j = 1; j<= lanes; j++) {
            rowCount++; // counting roads passed
            fieldCount++; //counting roads passed for generating field (reset later)
            String direction;
            
            if (rand.nextInt(2) == 0) { // generating road direction
              direction = "left";
            } else {
              direction = "right";
            }
            
            Road road = new Road(lastRow.getX(), lastRow.getY()-ROW_HEIGHT, direction);
            // creating new road
            if (uniqueRow(road)) {
              rows.add(road);
              lastRow = road;
            }
          }
          
          // adds grass
        } else if (i == 1) { 
          for (int j = 1; j<=lanes/2; j++) {
            rowCount++; // counting roads passed
            fieldCount++; //counting roads passed for generating field (reset later)
            Grass grass = new Grass(lastRow.getX(), lastRow.getY()-ROW_HEIGHT);
            // creating new grass
            if (uniqueRow(grass)) {
              rows.add(grass);
              lastRow = grass;
            }
          }
          
          // adds water
        } else if (i == 2) { 
          if (randomRow%3 == 0) {
            for (int j = 1; j<=lanes; j++) {
              rowCount++; //counting roads passed
              fieldCount++; //counting roads passed for generating field (reset later)
              String direction;
              
              if (rand.nextInt(2) == 0) { // generating water direction
                direction = "left";
              } else {
                direction = "right";
              }
              Water water = new Water(lastRow.getX(), lastRow.getY()-ROW_HEIGHT, direction);
              //creating new water
              if (uniqueRow(water)) {
                rows.add(water);
                lastRow = water;
              }
            }
          }
          
          // adds railroad
        } else if (i == 3) { 
          if (randomRow%4 == 0) {
            String direction;
            if (rand.nextInt(2) == 0) { // generating railroad direction
              direction = "left";
            } else {
              direction = "right";
            }
            Railroad railroad = new Railroad(lastRow.getX(), lastRow.getY()-ROW_HEIGHT, direction);
            //creating new railroad
            if (uniqueRow(railroad)) {
              rows.add(railroad);
              lastRow = railroad;
            }
          }
          
          // adds field
        } else if (i == 4) {
          if (fieldCount>20) {
            rowCount += 5; //since a field is 5 rows add 5 to row counting variable
            Field field;
            Grass grassBottom = new Grass(lastRow.getX(), lastRow.getY()-ROW_HEIGHT);
            if (uniqueRow(grassBottom)) {
              rows.add(grassBottom);
              lastRow = grassBottom;
            }
            
            for (int j = 0; j<3; j++) {
              String direction;
              if (rand.nextInt(2) == 0) { // generating direction for all three field rows
                direction = "left";
              } else {
                direction = "right";
              }
              field = new Field(lastRow.getX(), lastRow.getY()-ROW_HEIGHT, direction);
              //creating new field
              if (uniqueRow(field)) {
                rows.add(field);
                lastRow = field;
              }
            }
            Grass grassTop = new Grass(lastRow.getX(), lastRow.getY()-ROW_HEIGHT);
            if (uniqueRow(grassTop)) {
              rows.add(grassTop);
              lastRow = grassTop;
            }
            fieldCount = 0;
          }
        } 
      }
    }
  }
  
  /**
   * moveDown
   * This method moves the row and elements contained downwards to mimic a moving screen
   * @param move The distance (in pixels) the row travels downwards
   */
  public void moveDown(int move) {
    setY(getY()+move);
    for (int i = 0; i<elements.size(); i++) {
      elements.get(i).moveDown(move);
    }
  }
  
  /**
   * moveLeft
   * This method moves the row and elements contained to the left to mimic a moving screen
   * @param move The distance (in pixels) the row travels to the left
   */
  public void moveLeft(int move) {
    setX(getX()-move);
    for (int i = 0; i<elements.size(); i++) {
      elements.get(i).moveLeft(move);
    }
  }
  
  /**
   * moveRight
   * This method moves the row and elements contained to the right to mimic a moving screen
   * @param move The distance (in pixels) the row travels to the right
   */
  public void moveRight(int move) {
    setX(getX()+move);
    for (int i = 0; i<elements.size(); i++) {
      elements.get(i).moveRight(move);
    }
  }
  
  /**
   * draw
   * This method displays the Row and its elements onto the screen
   * @param g The object used to draw to the screen
   */ 
  public void draw(Graphics g) {
    
    // draws extra space on both ends
    Color spaceColour = new Color(230, 232, 237);
    g.setColor(spaceColour);
    g.fillRect(getX(), getY(), EXTRA_SPACE, getHeight());
    g.fillRect(getX()+ROW_WIDTH, getY(), EXTRA_SPACE, getHeight());
    
    // draws main row
    g.setColor(colour);
    g.fillRect(getX()+EXTRA_SPACE,getY(),ROW_WIDTH,getHeight());
    
    // draws all the row's elements
    for (int i = 0; i<elements.size(); i++) {
      elements.get(i).draw(g);
    }
  }
  
  /**
   * uniqueRow
   * This method checks if the row will overlap an existing row
   * @param row The row that will be compared 
   * @return false if the row overlaps another row, true otherwise
   */
  public boolean uniqueRow(Row row) {
    for (int k = 0; k<rows.size(); k++) {
      if (row.getY() == rows.get(k).getY()) {
        return false;
      } else if (Math.abs(row.getY() - rows.get(k).getY()) < 80) {
        return false;
      }
    } return true;
  }
  
  /**
   * getColour
   * This method extracts and returns the private variable "colour" 
   * @return The colour of the row
   */ 
  public Color getColour() {
    return colour;
  }
  /**
   * setColour
   * This method changes the private variable "colour" 
   * @param colour The new colour of the row
   */ 
  public void setColour(Color colour) {
    this.colour = colour;
  }
  
  /**
   * getDirection
   * This method extracts and returns the private variable "direction" 
   * @return The direction of the row ("left", "right", "still")
   */ 
  public String getDirection() {
    return direction;
  }
  
  /**
   * setDirection
   * This method changes the private variable "direction" 
   * @param direction The new direction of the row ("left", "right", "still")
   */ 
  public void setDirection(String direction) {
    this.direction = direction;
  }
}