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
  private int sleepTime = 1000;
  
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
    this.setLayout(new GridLayout(rows, numOfCards/rows));
    
    char digit;
    Card card = null;
    Card card2 = null;
    /* Här börjar alla roliga tecken */
    int startValue = 161;
    for (int i = startValue; i < (startValue + numOfCards/2); i++) {
      
      /* Översätter nuvarande siffran till en char */
      digit = (char) i;
      
      /* En något otorr kod 
         Behöver städas upp vid tillfälle */
      
      /* Skickar med ett unikt värde i form av en tal 
         Sätter texten på knappen, i det här fallet så används ASCII-koden för {i} som namn */
      card = new Card(i*i, Character.toString(digit));
      card2 = new Card(i*i, Character.toString(digit));
      
      /* Lägger till kortet två gånger i listan, 
         eftersom varje bild måste finnas med just två gånger */
      card.addActionListener(this);
      card2.addActionListener(this);
      
      card.setPreferredSize(new Dimension(35,35));
      card2.setPreferredSize(new Dimension(35,35));
      
      this.cards.add(card); this.cards.add(card2);
    }
    
    /* Blandar listan */
    Collections.shuffle(this.cards);
    
    /* Lägger till kort till vyn */
    for (Card c : this.cards) {
      this.add(c);
    }
  }
  
  /**
  * Exakt hur många anges i sleepTime-var
  * @param none
  * @return none
  */
  private void sleep(Card card){
    Timer timer = new Timer(this.sleepTime, card);
    timer.start();
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
    
    /* Är kortet redan vänt? */
    if(card.isUp()){
      return;
    }

    /* Vänder på kortet de klickade kortet */
    card.flip();
    
    /* Är detta de förta kortet som klickas på? */
    if(this.activeCard == null){
      this.activeCard = card;
      return;
    }
    
    /* Har första kortet samma värde som det nuvarande ?
     * Då har spelaren fått ett poäng och paret görs osynligt.
     */
    if(this.activeCard.equals(card)){
      /* Ger poäng till användaren */
      creator.hasScored();
      
      /* Ta bort båda korten */
      card.remove();
      this.activeCard.remove();
      
      /* Registrera antalet osynliga kort */
      this.invisible += 2;
    } else {
      
      /* Vänder tillbaka båda korten, om några sekunder... */
      this.sleep(card);
      this.sleep(this.activeCard);
      
      /* Byter till nästa spelare */
      creator.nextPlayer();
    }
    
    /* Om alla kort är osynliga är spelet slut. */
    
    if (this.invisible == this.numOfCards) {
      creator.gameEnded();
      return;
    }
    
    /* Nollställer de aktiva kortet för nästa omgång */
    this.activeCard = null;    
  }
}
