import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;

/**
 * Representerar en spelare. Håller reda på poängställningen. 
 * Spelaren kan vara aktiv eller inaktiv
 */
public class Player extends JButton implements ActionListener, Comparable {
  private int score;
  private String name;
  
  /* Grafisk egenskaper */
  private Color activeColor   = Color.red;
  private Color inactiveColor = Color.gray;
  private EtchedBorder border = new EtchedBorder(EtchedBorder.LOWERED);
  
  /**
   * @param name Spelarens namn
   */
  public Player(String name) {
    this.name = name;
    score = 0;
    updateText();
    setBackground(inactiveColor);
    setBorder(border);
    this.addActionListener(this);
  }
  
  /**
  * Skapar en kopia på en spelare
  * @return En kopia på en spelare
  */
  public Player clone(){
    Player player = new Player(this.name);
    player.score = this.score;
    player.setBackground(this.getBackground());
    player.updateText();
    return player;
  }
  
  /**
  * Är spelaren aktiv?
  */
  public boolean isActive(){
    return this.getBackground() == this.activeColor;
  }
  
  /**
   * Sätter spelaren som aktiv. Ändrar bakgrundsfärgen för att visa det.
   */
  public void setActive() {
    this.setBackground(activeColor);
  }
  
  /**
   * Sätter spelare som inaktiv. Ändrar bakgrundsfärgen för att visa det.
   */
  public void setInactive() {
    this.setBackground(inactiveColor);
  }
  
  /**
   * Lägger till ett poäng för spelaren.
   */
  public void incScore() {
    this.score++;
    this.updateText();
  }
  
  /**
   * Ändrar spelarens namn
   * @param name Spelarens nya namn
   */
  public void setName(String name) {
    this.name = name;
    updateText();
  }
  
  /* Uppdaterar texten på spelarknappen */
  private void updateText() {
    this.setText(name + " - " + score + " points");
  }
  
  /**
   * När användaren klickar på knappen visas en ändra namn-dialog.
   */
  public void actionPerformed(ActionEvent e) {
    String name = JOptionPane.showInputDialog("Enter a new name:\n");
    if (name != null) {
      this.setName(name);
    }
  }
  
  /**
   * @return Spelarens poäng.
   */
  public int getScore() {
    return this.score;
  }
  
  /**
   * @return Spelarens namn
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Jämför två Player. Om argumentet inte är en player slängs en exception.
   * @param o Objektet som man vill jämföra this med.
   * @return en int som jämför objekten
   */
  public int compareTo(Object o) {
    int result;
    if (o instanceof Player) {
      Player other = (Player) o;
      if (this.getScore() == other.getScore()) {
        result = 0;
      } else {
      result = this.getScore() > other.getScore() ? 1 : -1;
      }
      return result;
    } else {
      throw new ClassCastException();
    }
  }
}
