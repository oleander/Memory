package memory;

import javax.swing.*;

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
      players.add(player);
      this.add(player);
    }
  }
  
  /**
   * Returnerar aktiv spelare
   * @return Aktiv spelare
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