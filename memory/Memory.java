import java.awt.BorderLayout;
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
    players.getCurrentPlayer().incScore(); 
  }
  
  private void buildView() {
    /* Det är denna raden som gör att meyn placeras längst upp i applikationen i OS X */
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    
    /* Skapar en meny för spelet 
       I OS X visas en OSX-ish variant
    */
    Menu menu = new Menu();
    this.setJMenuBar(menu);
    
    this.setLayout(new BorderLayout());
    
    /* Skapar en huvudpanel som kommer att innhålla alla andra paneler */
    JPanel innerPanel = new JPanel();
    innerPanel.setLayout(new BorderLayout());
      
    innerPanel.add(this.cards, BorderLayout.CENTER);
    innerPanel.add(this.players, BorderLayout.NORTH);
    
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
}