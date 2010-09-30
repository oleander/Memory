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
  private String colRowLabelText  = "Choose the number of rows and columns:";
  private String cardLabelText    = "Choose the number of cards you want to use:";
  private int numOfCards;
  private int rows;
  private Memory creator;
  private JFrame window           = new JFrame(windowTitle);
  private JPanel wrapper          = new JPanel();
  private JPanel sliderPanel1     = new JPanel();
  private JPanel sliderPanel2     = new JPanel();
  private JPanel sliderAndLabel1  = new JPanel();
  private JPanel sliderAndLabel2  = new JPanel();
  private JPanel buttonPanel      = new JPanel();
  private JSlider cardSlider      = new JSlider();
  private JSlider colRowSlider    = new JSlider();
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
    cardSlider.setMaximum(maxCards);
    buildView();
    addListeners();
  }
  
  private void buildView(){
    window.add(wrapper);
    
    wrapper.setLayout(new GridLayout(3, 1, 5, 5));
    wrapper.add(sliderPanel1);
    wrapper.add(sliderPanel2);
    wrapper.add(buttonPanel);
    
    initButton.setText(initButtonText);
    buttonPanel.add(initButton);
    
    setColRowResolution();
    updateCardLabel();
    updateColRowLabel();
    
    colRowLabel.setText(colRowLabelText);
    cardLabel.setText(cardLabelText);
    
    colRowLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    sliderPanel1.setLayout(new BoxLayout(sliderPanel1, BoxLayout.Y_AXIS));
    sliderPanel1.setBorder(new EtchedBorder());
    sliderPanel1.add(cardLabel);
    sliderPanel1.add(sliderAndLabel1);
    
    sliderPanel2.setLayout(new BoxLayout(sliderPanel2, BoxLayout.Y_AXIS));
    sliderPanel2.add(colRowLabel);
    sliderPanel2.add(sliderAndLabel2);
    sliderPanel2.setBorder(new EtchedBorder());
    
    sliderAndLabel1.setLayout(flow);
    sliderAndLabel1.add(placeHolder);
    sliderAndLabel1.add(cardSlider);
    sliderAndLabel1.add(cardDisplay);
    
    sliderAndLabel2.setLayout(flow);
    sliderAndLabel2.add(rowDisplay);
    sliderAndLabel2.add(colRowSlider);
    sliderAndLabel2.add(colDisplay);
    
    window.pack();
    window.setVisible(true);
  }
  
  private void addListeners() {
    cardSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        setColRowResolution();
        updateColRowLabel();
        updateCardLabel();
        
      }
    });
    colRowSlider.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        updateColRowLabel();
      }
    });
    initButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //creator.initialize();
      }
    });
  }
  
  private void setColRowResolution() {
    colRowSlider.setMaximum(cardSlider.getValue());
  }
  
  private void updateCardLabel() {
    cardDisplay.setText(Integer.toString(cardSlider.getValue()));
  }
  
  private void updateColRowLabel() {
    int columns;
    int rows;
    int value = colRowSlider.getValue();
    ArrayList<Integer> ticks = colRowCombinations(colRowSlider.getMaximum());
    
    if (value <= ticks.get(0) || value >= ticks.get(ticks.size() - 1)) {
      columns = value <= ticks.get(0) ? ticks.get(0) : ticks.get(ticks.size() - 1);
    } else {
      for (Integer i : ticks) {
        if 
      }
    }
  }
  
  private ArrayList<Integer> colRowCombinations (int n) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    for (int i = 1; i < n; i++) {
      if (n % i == 0) {
        result.add(i);
      }
    }
    return result;
  }
  
}
