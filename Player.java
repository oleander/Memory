import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class Player extends JButton {
  private int score;
  private String name;
  private Color activeColor   = Color.red;
  private Color inactiveColor = Color.gray;
  private EtchedBorder border = new EtchedBorder();
  
  public Player(String name) {
    this.name   = name;
    this.score  = 0;
    this.setBorder(border);
  }
  
  public void setActive() {
    this.setBackground(activeColor);
  }
  
  public void setInactive() {
    this.setBackground(inactiveColor);
  }
  
  public void incScore() {
    this.score++;
  }
}
