package memory;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

/**
 * Ett kort i ett memoryspel
 */
public class Card extends JButton {
  // Logiska egenskaper
  private int id;
	private String value;
	private State state;
	
	// Grafisk egenskaper
	private EtchedBorder border  = new EtchedBorder();
	private Color invisibleColor = Color.white;
	private Color downColor      = Color.darkGray;
	private Color upColor        = Color.gray;
  
	/**
	 * Skapar ett kort med ansiktet neråt
	 * @param id - ett unikt id
	 */
  public Card (int id, String value) {
    this.id = id;
    this.value = value;
    state = State.DOWN;
    this.setBorder(border);
  }
  
  /**
   * @return id kortets unika id
   */
  public int getId() {
    return id;
  }
  
  /**
   * Vänder på kortet. Gör inget om det redan är osynligt.
   */
  public void flip() {
    if (state == State.UP) {
    	state = State.DOWN;
    	this.setBackground(downColor);
    	this.setText("");
    }
    if (state == State.DOWN) {
    	state = State.UP;
    	this.setBackground(upColor);
    	this.setText(value);
    }
  }
  
  /**
   * Gör kortet osynligt
   */
  public void remove() {
	  this.state = State.INVISIBLE;
	  this.setBackground(invisibleColor);
	  this.setText("");
  }
  
  public boolean equals(Object a){
    return true;
  }
  
  /**
   * Beskriver tillstånden som ett kort kan befinna sig i
   */
  public enum State {
    /**
     * Kortet har ansiktet uppåt
     */
	  UP, 
	  /**
	   * Kortet har ansiktet neråt
	   */
	  DOWN, 
	  /**
	   * Kortet är osynligt
	   */
	  INVISIBLE
  }
}