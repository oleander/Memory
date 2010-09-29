package memory;

import java.awt.BorderLayout;

import javax.swing.*;

class Memory extends JFrame {
  private static int defaultPlayers  = 2;
  private static int defaultCards    = 100;
  private static int defaultRows     = 10;
 
  private Players players;
  private Cards cards;
  
  /**
   * Defaultkonstruktorn skapar ett spel med två spelare, 100 kort och 10 rader
   */
  public Memory() {
    this(defaultPlayers, defaultCards, defaultRows);
  }
  
  /**
   * Skapar ett spel med angivet antal spelare, kort och rader
   * @param numOfPlayers Antalet spelare
   * @param numOfCards Antalet kort
   * @param numOfRows Antalet rader
   */
  public Memory(int numOfPlayers, int numOfCards, int numOfRows){
    players = new Players(numOfPlayers);
    cards   = new Cards(numOfCards, numOfRows, this);
    buildView();
  }
  
  /**
   * Inkrementerar den aktiva spelarens poängställning.
   */
  public void hasScored(){
    players.getCurrentPlayer().incScore(); 
  }
  
  private void buildView() {
    this.setLayout(new BorderLayout());
    //this.add(cards, BorderLayout.SOUTH);
    this.add(players, BorderLayout.SOUTH);
    
    this.setTitle("Memory");
    this.setVisible(true);
  }
  
  /**
   * Flyttar spelturen till nästa spelare
   */
  public void nextPlayer(){
    players.nextPlayer();
  }
}