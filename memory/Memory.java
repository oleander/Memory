package memory;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;

class Memory extends JFrame {
  private static int defaultPlayers  = 2;
  private static int defaultCards    = 100;
  private static int defaultRows     = 10;
 
  private Players players;
  private Cards cards;
  
  /**
   * Skapar ett spel med angivet antal spelare, kort och rader
   * @param numOfPlayers Antalet spelare
   * @param numOfCards Antalet kort
   * @param numOfRows Antalet rader
   */
  public Memory(int numOfPlayers, int numOfCards, int numOfRows){
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfCards, numOfRows, this);
    buildView();
  }
  
  /**
   * Defaultkonstruktorn skapar ett spel med två spelare, 100 kort och 10 rader
   */
  public Memory() {
    this(defaultPlayers, defaultCards, defaultRows);
  }
  
  /**
   * Inkrementerar den aktiva spelarens poängställning.
   */
  public void hasScored(){
    players.getCurrentPlayer().incScore(); 
  }
  
  private void buildView() {
    this.setLayout(new BorderLayout());
    
    /* Skapar en huvudpanel som kommer att innhålla alla andra paneler */
    JPanel innerPanel = new JPanel();
    innerPanel.setLayout(new BorderLayout());
      
    innerPanel.add(this.cards, BorderLayout.SOUTH);
    innerPanel.add(this.players, BorderLayout.NORTH);
    
    this.add(innerPanel);
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
  
  /**
   * Körs när spelet är slut. Visar en dialog som meddelar vem eller vilka som vann. 
   */
  public void gameEnded(){
    ArrayList<Player> winners = players.getWinners();
    if (winners.size() == 1) {
      JOptionPane.showMessageDialog(null, "Game ended!\nThe winner is: " + players.getWinners().get(0).getName());
    } else {
      String message = "Game ended in a draw!\nThe winners are:";
      for (Player p : winners) {
        message += ("\n" + p.getName()); 
      }
      JOptionPane.showMessageDialog(null, message);
      }
    
    NewGame newGame = new NewGame(this);
  }
  
  public void initialize(int numOfPlayers, int numOfCards, int numOfRows){
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfCards, numOfRows, this);
  }
}