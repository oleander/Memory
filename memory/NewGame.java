package memory;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGame {
  private String windowTitle      = "Create New Game";
  private String initButtonText   = "Start";
  private String playersText      = "Choose the number of players:";
  private String colRowLabelText  = "Choose the number of rows and columns:";
  private String cardLabelText    = "Choose the number of cards you want to use:";
  private int maxCards;
  private int rows;
  private int players;
  
  // Lista med möjliga kolumnantal
  private ArrayList<Integer> ticks;
  
  // Objektets skapare, för callback
  private Memory creator;
  
  // rad- och kolumnvisarnas defaultstorlek
  private Dimension labelSize     = new Dimension(60, 16);
  
  /* Grafiska komponenter */
  private JFrame window           = new JFrame(windowTitle);
  private JPanel wrapper          = new JPanel();
  private JPanel playersPanel     = new JPanel();
  private JPanel sliderPanel1     = new JPanel();
  private JPanel sliderPanel2     = new JPanel();
  private JPanel sliderAndLabel1  = new JPanel();
  private JPanel sliderAndLabel2  = new JPanel();
  private JPanel sliderAndLabel3  = new JPanel();
  private JPanel buttonPanel      = new JPanel();
  private JSlider playerSlider    = new JSlider();
  private JSlider cardSlider      = new JSlider();
  private JSlider colRowSlider    = new JSlider();
  private JLabel playersLabel     = new JLabel();
  private JLabel playersDisplay   = new JLabel();
  private JLabel cardDisplay      = new JLabel();
  private JLabel rowDisplay       = new JLabel();
  private JLabel colDisplay       = new JLabel();
  private JLabel cardLabel        = new JLabel();
  private JLabel colRowLabel      = new JLabel();
  private JLabel placeHolder      = new JLabel();
  private FlowLayout flow         = new FlowLayout();
  private JButton initButton      = new JButton();
  
  public NewGame(Memory creator) {
    this(creator, 100);
  }
  
  public NewGame(Memory creator, int maxCards) {
    this.creator = creator;
    
    // Ett första värde sätts på row för att den inte ska vara tom.
    this.rows    = 50;
    
    // Sätter kortsliderns maxvärde enligt maxantal kort, ser till att slidern bara antar jämna helvärden
    cardSlider.setMaximum(maxCards);
    cardSlider.setMinimum(2);
    cardSlider.setMinorTickSpacing(2);
    cardSlider.setSnapToTicks(true);
    
    playerSlider.setMaximum(10);
    playerSlider.setMinimum(2);
    
    // Bygger vyn och alla lyssnare
    buildView();
    addListeners();
  }
  
  private void buildView(){
    window.add(wrapper);
    
    wrapper.setLayout(new GridLayout(4, 1, 5, 5));
    wrapper.add(playersPanel);
    wrapper.add(sliderPanel1);
    wrapper.add(sliderPanel2);
    wrapper.add(buttonPanel);
    
    initButton.setText(initButtonText);
    buttonPanel.add(initButton);
    
    setMaxAndTicks();
    updateCardLabel();
    updateColRowLabel();
    updatePlayersLabel();
    
    colRowLabel.setText(colRowLabelText);
    cardLabel.setText(cardLabelText);
    playersLabel.setText(playersText);
    
    colRowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    cardDisplay.setPreferredSize(labelSize);
    colDisplay.setPreferredSize(labelSize);
    rowDisplay.setPreferredSize(labelSize);
    placeHolder.setPreferredSize(labelSize);
    playersDisplay.setPreferredSize(labelSize);
    
    rowDisplay.setHorizontalAlignment(JLabel.RIGHT);
    
    playersPanel.setLayout(new BoxLayout (playersPanel, BoxLayout.Y_AXIS));
    playersPanel.setBorder(new EtchedBorder());
    playersPanel.add(playersLabel);
    playersPanel.add(sliderAndLabel3);
    
    sliderPanel1.setLayout(new BoxLayout(sliderPanel1, BoxLayout.Y_AXIS));
    sliderPanel1.setBorder(new EtchedBorder());
    sliderPanel1.add(placeHolder);
    sliderPanel1.add(cardLabel);
    sliderPanel1.add(sliderAndLabel1);
    
    sliderPanel2.setLayout(new BoxLayout(sliderPanel2, BoxLayout.Y_AXIS));
    sliderPanel2.setBorder(new EtchedBorder());
    sliderPanel2.add(colRowLabel);
    sliderPanel2.add(sliderAndLabel2);
    
    sliderAndLabel1.setLayout(flow);
    sliderAndLabel1.add(placeHolder);
    sliderAndLabel1.add(cardSlider);
    sliderAndLabel1.add(cardDisplay);
    
    sliderAndLabel2.setLayout(flow);
    sliderAndLabel2.add(rowDisplay);
    sliderAndLabel2.add(colRowSlider);
    sliderAndLabel2.add(colDisplay);
    
    sliderAndLabel3.setLayout(flow);
    sliderAndLabel3.add(Box.createRigidArea(labelSize));
    sliderAndLabel3.add(playerSlider);
    sliderAndLabel3.add(playersDisplay);
 
    
    window.setMinimumSize(window.getPreferredSize());
    window.pack();
    window.setVisible(true);
  }
  
  private void addListeners() {
    cardSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        setMaxAndTicks();
        updateColRowLabel();
        updateCardLabel();
      }
    });
    colRowSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        updateColRowLabel();
      }
    });
    playerSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        updatePlayersLabel();
      }
    });
    initButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        creator.initialize(players, maxCards, rows);
        closeWindow();
      }
    });
  }
  
  // Överför maxvärdet från kortslider till radkolumnslider, sparar även ner i instansvariabler och beräknar ticks
  private void setMaxAndTicks() {
    this.maxCards = cardSlider.getValue();
    this.ticks = colRowCombinations(maxCards);
    colRowSlider.setMaximum(maxCards);
    colRowSlider.repaint();
  }
  
  
  // Uppdaterar kortvisaren
  private void updateCardLabel() {
    cardDisplay.setText(Integer.toString(cardSlider.getValue()));
  }
  
  // Uppdaterar spelarvisaren
  private void updatePlayersLabel(){
    this.players = playerSlider.getValue();
    playersDisplay.setText(Integer.toString(playerSlider.getValue()));
  }
  
  /* Uppdaterar kolumn- och radlabels med tal som är möjliga kombinationer av rader och kolumner för det givna maxantalet kort.
   * Sparar även ner värdet på rows, så att det är lätt att använda vid initiering av spelet.
   * */
  private void updateColRowLabel() {
    int columns = maxCards;
    int diff = Integer.MAX_VALUE;
    int value = colRowSlider.getValue();
    
    
    /* Om slidervärdet är mindre än det minsta talet i ticks eller större än det största talet, 
     * sätt columns till motsvarande värde i ticks.
    */
    for (int i : ticks) {
      int thisDiff = Math.abs(i - value);
      if (thisDiff <= diff || thisDiff == 0) {
        diff = thisDiff;
        columns = i;
      }
    }
    this.rows    = maxCards/columns;
    
    // Skriver ut rad- och kolumnantal till labels
    colDisplay.setText(Integer.toString(columns));
    rowDisplay.setText(Integer.toString(rows));
  }
  
  /* Skapar en lista med möjliga radantal för ett givet maxantal kort*/
  private ArrayList<Integer> colRowCombinations (int n) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    
    // Kolumnantalet kan vara alla jämna divisorer till maxantalet kort
    for (int i = 1; i <= n; i++) {
      if (n % i == 0) {
        result.add(i);
      }
    }
    return result;
  }
  
  /* Stänger fönstret. */
  private void closeWindow(){
    window.dispose();
  }
  
}
