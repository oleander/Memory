import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.Random;

/**
* Håller alla kort på plats av klassen Card
*/
public class Cards extends JPanel implements ActionListener, Serializable {
  
  /* En lista med kort för den nyvarande spelplanen */
  private ArrayList<Card> cards = new ArrayList<Card>();
  
  /* Antalet kort */
  private int numOfCards;
  
  /* Innehåller det nuvarande kortet för ett spel */
  private Card activeCard = null;
  
  /* Hur länge ska vi sova? , i ms */
  private int sleepTime = 2000;
  
  /* Innehåller huvudramen för spelet */
  private Memory creator = null;
  
  /* Grafiska egenskaper */
  private GridLayout layout;
  private EmptyBorder border = new EmptyBorder(20, 32, 20, 32);
  
  /* Anger hurvida kortleken är fryst eller ej 
     Från början så ska det alltid gå att klicka på ett kort
     Där av default-värdet false
  */
  private boolean freezed = false;
  
  /* Antalet osynliga kort */
  private int invisible;
  
  /* Antalet rader i applikationen */
  private int rows = 0;
  
  /* Antalet kort i applikationen */
  private int numCards = 0;
  
  /* Relativ sökväg till bild-mappen */
  private String path = "images/";
  
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
    this.rows = rows;
    this.numOfCards = numOfCards;
    
    Card card = null;
    Card card2 = null;
    
    ArrayList<String> images = this.images();
    
    for (int i = 0; i < numOfCards/2; i++) {
      card = new Card(i, new ImageIcon(images.get(i)));
      card2 = new Card(i, new ImageIcon(images.get(i)));
      
      this.cards.add(card); this.cards.add(card2);
    }
    
    /* Blandar listan */
    Collections.shuffle(this.cards);
    
    /* Lägger till kort till vyn */
    this.update();
  }
  
  /**
  * Returnerar en lista med bilder
  * @return En lista med länkar till bilder, alltså inte bilderna i sig
  */
  private ArrayList<String> images(){
    File dir = new File(path);
    ArrayList<String> images = new ArrayList<String>();
    
    for(String image : dir.list()){
      /* Kontrollerar så att filen har en filtypen png
         MIME-typen skulle även kunna kollas, om tiden fanns */
      if(image.endsWith(".png")){
        images.add(path + image);
      }
    }
    return images;
  }
  
  /**
  * Uppdaterar vyn med nya kort
  * Körs oftast när nya spel laddas in eller startas om
  * @param none
  * @return none
  */
  public void update(){
    /* Plockar bort alla gamla element från listan */
  	this.removeAll();
  	
  	for (Card c : this.cards) {
      c.addActionListener(this);
      c.setPreferredSize(new Dimension(35,35));
      c.validate();
      this.add(c);
      if(c.isInvisible()){
        this.invisible++;
      }
    }
    
    this.setLayout(new GridLayout(this.rows, this.numOfCards/this.rows));
    this.setBorder(border);
    this.validate();
  }
  
  /**
  * Kolla om det går att klicka på några kort i spelet
  * @return Är alla kort i spelet frysta ?
  */
  public boolean isFreezed(){
    return this.freezed;
  }
  
  /**
  * @param var, sätter kortleken till ett bestämt läge
                Antingen så är alla kort låsta, eller så är alla kort olåsta.
  */
  public void setFreezed(boolean var){
    this.freezed = var;
  }
  
  /**
  * Klonar listan med kort
  * @return En lista med objekt från klassen Card
  * @param none
  */
  public ArrayList<Card> clone(){
    ArrayList<Card> cards = new ArrayList<Card>();
    for(Card card : this.cards){
      cards.add(card.clone());
    }
    return cards;
  }
  
  /**
  * Sparar undan kort-delen av spelet i en fil
  * @param none
  * @return none
  */
  public void save(){
    try{
      FileOutputStream fos = new FileOutputStream("cards.memory");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(this.clone());
      oos.close();
    }
    catch(Exception error){
      error.printStackTrace();
    }
  }
  
  /**
  * Laddar in ett spel
  * @return none
  * @param none
  */
  public void load(){
    try{
      FileInputStream fis = new FileInputStream("cards.memory");
      ObjectInputStream ois = new ObjectInputStream(fis);
      Object obj = ois.readObject();
      ois.close();
      
      if (obj instanceof ArrayList) {
      	this.cards = (ArrayList<Card>) obj;
        this.update();
      }
      
    } catch(Exception error){
      error.printStackTrace();
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
    
    /* Om kortleken är fryst så ska man inte kunna klicka på några kort */
    if(this.isFreezed()){
      return;
    }
    
    /* Varför måste man göra så här?! */
    Card card = (Card) e.getSource();
    
    /* Är kortet redan vänt? */
    if(card.isUp() || card.isInvisible()){
      return;
    }
    
    /* Vänder på kortet de klickade kortet */
    card.flip();
    
    /* Är detta de förta kortet som klickas på? */
    if(this.activeCard == null){
      this.activeCard = card;
      return;
    }
    
    this.setFreezed(true);
    
    /* Har första kortet samma värde som det nuvarande ?
     * Då har spelaren fått ett poäng och paret görs osynligt.
     */
    if(this.activeCard.equals(card)){
      /* Ger poäng till användaren */
      creator.hasScored();
      
      /* Ta bort båda korten */
      card.setInvisible();
      this.activeCard.setInvisible();
      
      /* Registrera antalet osynliga kort */
      this.invisible += 2;
      
      /* Spelar upp ett ok-ljud */
      Sound.okey();
      
    } else {
      
      /* Byter till nästa spelare */
      creator.nextPlayer();
      
      /* Spelar upp ett fel-ljud */
      Sound.error();
    }
    
    /* Om alla kort är osynliga är spelet slut. */
    if (this.invisible == this.numOfCards) {
      
      /* Spelar upp ett ljud som indikerar på att någon har vunnit */
      Sound.winner();
      
      /* Meddelar att spelet är avslutat */
      creator.gameEnded();
      
      return;
    }
    
    /* Vänder tillbaka båda korten, om några sekunder {this.sleepTime}... */
    this.sleep(card);
    this.sleep(this.activeCard);
    
    /* Nollställer de aktiva kortet för nästa omgång */
    this.activeCard = null;
  }
}