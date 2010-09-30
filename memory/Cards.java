package memory;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Collections;

class Cards extends JPanel implements ActionListener {
  
  /* En lista med kort för den nyvarande spelplanen */
  private ArrayList<Card> cards = new ArrayList<Card>();
  
  /* Antalet kort */
  private int numOfCards;
  
  /* Innehåller det nuvarande kortet för ett spel */
  private Card activeCard = null;
  
  /* Hur länge ska vi sova? , i ms */
  private int sleepTime = 3000;
  
  /* Innehåller huvudramen för spelet */
  private Memory creator = null;
  
  /* Layout för kortarean*/
  private GridLayout layout;
  
  /* Antalet osynliga kort */
  private int invisible;
  
  /**
  * Konstruktor
  * @paras rows antalet rader som spelplanen ska innehålla
  * @param numCards antalet kort spelplanen ska innehålla
  * @param creator  huvudframen för spelplanen
  * @return none
  */
  
  public Cards(int rows, int numOfCards, Memory creator){
    this.creator    = creator;
    this.numOfCards = numOfCards;
    this.invisible  = 0;
    
    char digit;
    Card card = null;
    
    /* Här börjar alla roliga tecken */
    int startValue = 161;
    
    for (int i = startValue; i < (startValue + numOfCards/2); i++) {
      
      /* Översätter nuvarande siffran till en char */
      digit = (char) i;
      
      /* Skickar med ett unikt värde i form av en tal 
         Sätter texten på knappen, i det här fallet så används ASCII-koden för {i} som namn
      */
      card = new Card(i, Character.toString(digit));
      
      /* Lägger till kortet två gånger i listan, 
         eftersom varje bild måste finnas med just två gånger 
      */
      card.addActionListener(this);
      this.cards.add(card); this.cards.add(card);
    }
    
    /* Blandar listan*/
    Collections.shuffle(this.cards);
    
    /* Skapar ett rutnät för korten */
    
    layout = new GridLayout(rows, (numOfCards/rows), 2, 2);
    
    /* lägger till kort till vyn */
    for (Card c : cards) {
      this.add(c);
    }
    
    /* Lägger till vår nyligen genererade lista i huvudramen */
    // creator.add(this, BorderLayout.CENTER);
  }
  
  /**
  * Låter den nuvarande tråden sova ett par sekunder
  * Exakt hur många anges i sleepTime-var
  * @param none
  * @return none
  */
  private void sleep(){
    try {
      Thread.currentThread().sleep(this.sleepTime);
    } catch (Exception e){}
  }
  
  /**
  * Sätter antalet sekunder applikationen ska sova efter ett kort har vänds
  * @param time, anger hur många sekunder applikationen ska sova efter ett kort har vänds
  * @return none
  *
  */
  public void setSleepTime(int time){
    this.sleepTime = time*1000;
  }
  
  /**
  * @param e, nuvarnade klickade objektet
  * @return none
  */
  public void actionPerformed(ActionEvent e){
    /* Varför måste man göra så här?! */
    Card card = (Card) e.getSource();

    /* Vänder på kortet de klickade kortet */
    card.flip();
    
    /* Är detta de förta kortet som klickas på? */
    if(this.activeCard == null){
      this.activeCard = card;
      return;
    }
    
    /* Sover i angivet antal sekunder */
    this.sleep();
    
    /* Har första kortet samma värde som det nuvarande?
     * Då har spelaren fått ett poäng och paret görs osynligt.
     * */
    if(this.activeCard.equals(card)){
      creator.hasScored();
      card.remove();
      this.activeCard.remove();
      this.invisible += 2;
    } else {
      creator.nextPlayer();
    }
    
    /* Om alla kort är osynliga är spelet slut. */
    
    if (this.invisible == this.numOfCards) {
      creator.gameEnded();
    }
    
    /* Nollställer de aktiva kortet för nästa omgång */
    this.activeCard = null;
  }
}
