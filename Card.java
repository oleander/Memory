import javax.swing.JButton;

/**
 * Ett kort i ett memoryspel
 * @param id ett unikt id som möjliggör identifikation av kort med samma innehåll
 * @param value innehållet i kortet
 */
 

class Card extends JButton {
  private int id;
  private String value;
  private boolean 
  
  public Card (int id) {
    this.id = id;
  }
  
  public int getId() {
    return id;
  }
  
  public void flip() {
    
  }
}