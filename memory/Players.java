package memory;

import javax.swing.*;
import java.util.ArrayList;

class Players extends JPanel {
  
  /* Innehåller en lista med alla nuvarande spelare */
  private ArrayList<Player> players = new ArrayList<Player>();
  
  /* Anger i nummerordningen 0 till n vilken spelare som spelar just nu 
     By default är det alltid första spelaren
  */
  private int currentPlayer = 0;
  
  /**
  * Konstruktor
  * @param amountOfPayers, antalet personer som ska spela spelet
           Räkningen börjar från 1
  * @return none
  */
  public Players(int amountOfPayers){
    Player player = null;
    
    for (int i = 1; i <= amountOfPayers; i++) {
      player = new Player("Player " + "(" + Integer.toString(i) + ")");
      players.add(player);
    }
  }
  
  /**
   * Returnerar aktiv spelare
   * @return Spelaren som är aktiv nu
   */
  public Player getCurrentPlayer() {
    return players.get(currentPlayer);
  }
  
  /**
  * Hoppar till nästa spelare, om det finns något
  * Om vi är i slutet av listan så hoppar vi till början igen
  * @param none
  * @return none
  */
  public void nextPlayer(){
    this.currentPlayer =  this.players.size() - 1 > this.currentPlayer ? this.currentPlayer + 1 : 0;
  }
}