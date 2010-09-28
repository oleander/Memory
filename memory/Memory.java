import javax.swing.*;

class Memory extends JFrame {
  private Players players = null;
  private Cards cards = null;
  
  public Memory(){
    /* Skapar ett default-spel, 10x10-bricka */
    this.cards = new Cards(10,100,this);
    
    this.players = new Players(5);
    
    this.add(cards);
    this.add(players);
    
    this.setTitle("Memory");
    this.setVisible(true);
  }
  
  /**
  *
  *
  */
  public void hasScored(){
    this.players.currentPlayer().incScore();
  }
  
  
  /**
  *
  *
  */
  public void nextPlayer(){
    this.players.nextPlayer();
  }
  
}