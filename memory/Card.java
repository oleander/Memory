import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.*;


/**
* Ett kort i ett memoryspel
*/
public class Card extends JButton implements ActionListener {
  
  /* Logiska egenskaper */
  private int id;
	private String value;
	private State state;
	
	/* Grafisk egenskaper */
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
    this.setBackground(this.downColor);
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
    if (this.state == State.UP) {
    	this.state = State.DOWN;
    	this.setBackground(this.downColor);
    	this.setText("");
    } else if (this.state == State.DOWN) {
    	this.state = State.UP;
    	this.setBackground(this.upColor);
    	this.setText(this.value);
    }
    
    this.repaint();
  }
  
  /**
   * Gör kortet osynligt
   */
  public void remove() {
	  this.state = State.INVISIBLE;
	  this.setBackground(invisibleColor);
  }
  
  /**
  * Kontrollerar om två kort är lika
  * @return true om ingående kort har samma värde, alltså text som instansen
  * @param Ett kort av klassen Card
  */
  public boolean equals(Card card){
    return this.value.equals(card.value);
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
  
  /**
  * Är kortet uppåt?
  * @return Retunerar true om kortet är up, en bild visas alltså
  */
  public boolean isUp(){
    return this.state == State.UP;
  }
  
  /**
  * Översätter knappen till en sträng, i debug-syfte
  * @return En sträng-version av knappen, vilket är texten på knappen
  */
  public String toString(){
    return this.getText();
  }
  
  /**
  * Kickas i gång efter {sleepTime} ms, se sleep-metoden i cards-klassen
  * @param e, eventet som nyss inträffade
  */
  public void actionPerformed(ActionEvent e){
    
    /* Konverterar ingående objekt till ett kort, Java-style */
   Timer timer = (Timer) e.getSource();
   
   /* Stannar timern, så att inte denna metoden körs igen */
   timer.stop();
   
   /* Flippar tillbaka kortet */
   this.flip();
  }
}