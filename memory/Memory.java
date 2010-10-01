import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.File;

/**
* Innehåller ramen för spelet
*/
class Memory extends JFrame {
  private static int defaultPlayers  = 2;
  private static int defaultCards    = 20;
  private static int defaultRows     = 4;
  private static Dimension minSize  = new Dimension(500, 0);
  private Toolkit toolkit = Toolkit.getDefaultToolkit();
  private Dimension screenSize = toolkit.getScreenSize();
 
  // Maxantalet kort
  public int maxCards;
  
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
    // Kalkylerar maxantalet kort
    this.maxCards = this.calcMaxCards();
    
    // Om önskat antal kort är större än maxantalet kort används maxantalet kort
    int n = numOfCards < maxCards ? numOfCards : maxCards;
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfRows, n, this);
    this.setLocation((screenSize.width/2 - this.getWidth()/2), (screenSize.height/2 - this.getHeight()/2));
    this.setMinimumSize(minSize);
    buildView();
  }
  
  /**
   * Defaultkonstruktorn skapar ett spel med två spelare, 100 kort och 10 rader
   */
  public Memory() {
    this(defaultPlayers,defaultCards, defaultRows);
  }
  
  /**
   * Inkrementerar den aktiva spelarens poängställning.
   */
  public void hasScored(){
    players.currentPlayer().incScore(); 
  }
  
  /**
  * Bygger upp menyn och sätter alla default-värden
  * @param none
  * @return none
  */
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
  
  /**
  * Laddar in korten och alla spelare från externa filer
  * Uppdaterar sedan vyn och packar om ramen
  */
  public void load(){
    this.cards.load();
    this.players.load();
    this.validate();
    this.pack();
  }
  
  /**
   * Sparar det nuvarande speltillståndet.
   */
  public void save(){
    this.cards.save();
    this.players.save();
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
    NewGame newGame = new NewGame(this, maxCards);
  }
  
  /* Startar ett nytt spel */
  protected void initialize(int numOfPlayers, int numOfCards, int numOfRows){
    // Tar bort alla nuvarande fönster på spelplanen
    this.innerPanel.removeAll();
    
    // Lägger till nya spelare och kort
    this.players = new Players(numOfPlayers);
    this.cards   = new Cards(numOfRows, numOfCards, this);
    
    // lägger till de nya panelerna och uppdaterar
    this.innerPanel.add(players, BorderLayout.SOUTH);
    this.innerPanel.add(cards, BorderLayout.CENTER);
    this.updateView();
  }
  
  /* Packar om och uppdaterar vyn */
  private void updateView() {
    this.validate();
    this.pack();
  }
  
  /* Räknar ut maxantalet kort som vi kan använda*/
  private int calcMaxCards() {
    File dir = new File("images");
    int nImages = dir.list().length;
    
    return nImages < defaultCards ? defaultCards : nImages;
  }
}