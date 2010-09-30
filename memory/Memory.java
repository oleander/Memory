package memory;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;

class Memory extends JFrame {
  private static int defaultPlayers  = 2;
  private static int defaultCards    = 20;
  private static int defaultRows     = 4;
 
  private Players players;
  private Cards cards;
  private JPanel innerPanel;
  
  /**
   * Skapar ett spel med angivet antal spelare, kort och rader
   * @param numOfPlayers Antalet spelare
   * @param numOfCards Antalet kort
   * @param numOfRows Antalet rader
   */
  public Memory(int numOfPlayers, int numOfCards, int numOfRows){
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfRows, numOfCards, this);
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
    players.currentPlayer().incScore(); 
  }
  
  private void buildView() {
    /* Det är denna raden som gör att menyn placeras längst upp i applikationen i OS X */
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    
    /* Skapar en meny för spelet 
       I OS X visas en OSX-ish variant
    */
    Menu menu = new Menu(this);
    this.setJMenuBar(menu);
    
    this.setLayout(new BorderLayout());
    
    /* Skapar en huvudpanel som kommer att innhålla alla andra paneler */
    this.innerPanel = new JPanel();
    this.innerPanel.setLayout(new BorderLayout());
      
    this.innerPanel.add(this.cards, BorderLayout.CENTER);
    this.innerPanel.add(this.players, BorderLayout.SOUTH);
    
    this.add(innerPanel);
    this.setTitle("Memory");
    this.setVisible(true);
    this.pack();
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  }
  
  /**
   * Flyttar spelturen till nästa spelare
   */
  public void nextPlayer(){
    players.nextPlayer();
  }
  
  /*
   * Körs när spelet är slut. Visar en dialog som meddelar vem eller vilka som vann. 
   */
  protected void gameEnded(){
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
  
  protected void initialize(int numOfPlayers, int numOfCards, int numOfRows){
    this.innerPanel.removeAll();
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfRows, numOfCards, this);
    this.innerPanel.add(players, BorderLayout.SOUTH);
    this.innerPanel.add(cards, BorderLayout.CENTER);
    this.validate();
    this.pack();
  }
}