import javax.swing.JButton;

/**
 * Ett kort i ett memoryspel
 * @param id ett unikt id som möjliggör identifikation av kort med samma innehåll
 * @param value innehållet i kortet
 */
 
public class Card extends JButton {
  private int id;
  private String value;
  private State state;
  
  /**
   * Skapar ett kort med ansiktet neråt
   * @param id ett unikt id
   */
  
  public Card (int id) {
    this.id = id;
    state = State.DOWN;
  }
  
  
  
  public int getId() {
    return id;
  }
  
  public void flip() {
    if (state == State.UP) {
    	state = State.DOWN;
    	this.setText("");
    }
    if (state == State.DOWN) {
    	state = State.UP;
    	this.setText(value);
    }
  }
  
  public enum State {
	  UP, DOWN, INVISIBLE
  }
}