import javax.swing.*;
import java.io.*;
import java.awt.GridLayout;
import java.util.ArrayList;

class Players extends JPanel {
  
  /* Innehåller en lista med alla nuvarande spelare */
  private ArrayList<Player> players = new ArrayList<Player>();
  
  /* Anger i nummerordningen 0 till n vilken spelare som spelar just nu 
     By default är det alltid första spelaren
  */
  private int currentPlayer = 0;
  private GridLayout layout;
  
  /**
  * Konstruktor
  * @param amountOfPayers, antalet personer som ska spela spelet
           Räkningen börjar från 1
  * @return none
  */
  public Players(int amountOfPayers){
    layout = new GridLayout(1, amountOfPayers, 2, 2);
    this.setLayout(layout);
    Player player = null;
    
    for (int i = 1; i <= amountOfPayers; i++) {
      player = new Player("Player " + "(" + Integer.toString(i) + ")");
      this.players.add(player);
      this.add(player);
    }
    
    /* Sätter första spelaren som aktiv */
    this.currentPlayer().setActive();
  }
  
  /**
   * Returnerar aktiv spelare
   * @return Aktiv spelare
   */
  public Player getCurrentPlayer(){
    return players.get(currentPlayer);
  }
  
  /**
  * Laddar in ett spel
  * @return none
  * @param none
  */
  public void load(){
    try{
      FileInputStream fis = new FileInputStream("players.memory");
      ObjectInputStream ois = new ObjectInputStream(fis);
      Object obj = ois.readObject();
      ois.close();
      
      if (obj instanceof ArrayList) {
        this.removeAll();
      	
      	this.players = (ArrayList<Player>) obj;
        for(Player player : this.players){
          this.add(player);
        }
      }
      
    } catch(Exception error){
      error.printStackTrace();
    }
  }
  
  /**
  * Sparar undan player-delen av spelet i en fil
  * @param none
  * @return none
  */
  public void save(){
    try{
      FileOutputStream fos = new FileOutputStream("players.memory");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(this.clone());
      System.out.println("Sparar!");
      oos.close();
    }
    catch(Exception error){
      error.printStackTrace();
    }
  }
  
  /**
  * Gör en kopia på alla spelare
  * @param none
  * @return En lista med spelare
  */
  public ArrayList<Player> clone(){
    ArrayList<Player> players = new ArrayList<Player>();
    for (Player player : this.players) {
      players.add(player.clone());
    }
    
    return players;
  }
  
  /**
  * Hoppar till nästa spelare, om det finns något
  * Om vi är i slutet av listan så hoppar vi till början igen
  * @param none
  * @return none
  */
  public void nextPlayer(){
    this.currentPlayer().setInactive();
    this.currentPlayer =  this.players.size() - 1 > this.currentPlayer ? this.currentPlayer + 1 : 0;
    this.currentPlayer().setActive();
  }
  
  /**
  * Retunerar den nuvarnade spelare
  * @param none
  * @return Nuvarande spelaren
  */
  public Player currentPlayer(){
    return this.players.get(this.currentPlayer);
  }
}