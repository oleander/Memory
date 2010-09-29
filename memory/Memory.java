

import java.awt.BorderLayout;

import javax.swing.*;

class Memory extends JFrame {
  private int defaultPlayers  = 2;
  private int defaultCards    = 100;
  private int defaultRows     = 10;
 
  private Players players;
  private Cards cards;
  
  /**
   * Defaultkonstruktorn skapar ett spel med två spelare, 100 kort och 10 rader
   */
  public Memory() {
    players = new Players(defaultPlayers);
    cards   = new Cards(defaultCards, defaultRows, this);
    buildView();
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
    //this.add(cards, BorderLayout.CENTER);
    this.add(this.players, BorderLayout.SOUTH);
    this.setTitle("Memory");
    this.setVisible(true);
    this.setSize(800,800);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  }
  
  /**
   * Flyttar spelturen till nästa spelare
   */
  public void nextPlayer(){
    players.nextPlayer();
  }
}