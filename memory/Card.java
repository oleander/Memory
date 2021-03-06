import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.*;
import java.io.*;
import java.awt.Dimension;

/**
* Ett kort i ett memoryspel
*/
public class Card extends JButton implements ActionListener, Serializable{
  
  /* Logiska egenskaper */
  private int id;
	private ImageIcon value;
	private State state;
	
	/* Grafisk egenskaper */
	private EtchedBorder border  = new EtchedBorder();
	private Color invisibleColor = Color.white;
	private Color downColor      = Color.darkGray;
	private Color upColor        = Color.gray;
	
	/* Visar när kortet inte är flippat */
	private ImageIcon blank      = new ImageIcon("blank.png");
	
	/* Visas när kortet är borttaget m.h.a. remove() */
  private ImageIcon empty      = new ImageIcon("empty.png");
  
	/**
	* Skapar ett kort med ansiktet neråt
	* @param id - ett unikt id
	*/
  public Card (int id, ImageIcon value) {
    this.setIcon(blank);
    this.id = id;
    this.value = value;
    this.state = State.DOWN;
    this.setBorder(border);
    this.setBackground(this.downColor);
    this.setPreferredSize(new Dimension(value.getIconWidth(), value.getIconHeight()));
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
    	this.setIcon(blank);
    } else if (this.state == State.DOWN) {
    	this.state = State.UP;
    	this.setBackground(this.upColor);
    	this.setIcon(this.value);
    }
    
    this.repaint();
    this.validate();
  }
  
  /**
   * Gör kortet osynligt
   */
  public void remove() {
	  this.state = State.INVISIBLE;
	  this.setBackground(invisibleColor);
	  this.setIcon(empty);
  }
  
  /**
  * Kontrollerar om två kort är lika
  * @return true om ingående kort har samma värde
  * @param Ett kort av klassen Card
  */
  public boolean equals(Card card){
    return this.id == card.id;
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
  * @return Returnerar true om kortet är up, en bild visas alltså
  */
  public boolean isUp() {
    return this.state == State.UP;
  }
  
  /**
  * Är kortet inaktiverat ?
  * @return Returnerar true om kortet är osynligt
  */
  public boolean isInvisible() {
    return this.state == State.INVISIBLE;
  }
  
  /**
  * Skapar en klon av kortet
  * @return ett objekt av typen Card
  */
  public Card clone(){
    Card card = new Card(this.id, this.value);
    
    card.state = this.state;
    card.setBorder(this.border);
    
    if(this.state == State.INVISIBLE){
      card.setBackground(this.invisibleColor);
      card.setIcon(blank);
    } else {
      card.setBackground(this.downColor);
    }
    
    return card;
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
   
   Cards cards = (Cards) this.getParent();
   
   /* Aktiverar alla kort igen, så att dom går att klicka på */
   cards.setFreezed(false);
   
   /* Stannar timern, så att inte denna metoden körs igen */
   timer.stop();
   
   /* Om kortet är inaktiverat så ska kortet 
      inte flippas utan plockas bort */
   if(this.isInvisible()){
     this.remove();
     return;
   }
   
   /* Flippar tillbaka kortet */
   this.flip();
  }
  
  /**
  * Sätter det nuvarande kortet som osynligt
  */
  public void setInvisible(){
    this.state = State.INVISIBLE;
  }
}