package memory;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
/**
 * Representerar en spelare. Håller reda på poängställningen. 
 * Spelaren kan vara aktiv eller inaktiv
 * @author jesper
 */
public class Player extends JButton implements ActionListener{
  private int score;
  private String name;
  
  private Color activeColor   = Color.red;
  private Color inactiveColor = Color.gray;
  private EtchedBorder border = new EtchedBorder(EtchedBorder.LOWERED);
  
  /**
   * @param name Spelarens namn
   */
  
  public Player(String name) {
    this.name   = name;
    this.score  = 0;
    this.setBorder(border);
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
  }
  
  public void actionPerformed(ActionEvent e) {
    
  }
}
