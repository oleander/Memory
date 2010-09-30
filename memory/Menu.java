package memory;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

/**
* Menyn för applikationen
*/
class Menu extends JMenuBar implements ActionListener {
  Memory creator;
  
  /**
  * Konstruktor, no shit
  * @param, none
  * @return none
  */
  public Menu(Memory creator) {
    this.creator = creator;
    
    JMenu menu         = new JMenu("Game"); 
    
    /* Innehållet i menyn */
    JMenuItem itemNew  = new JMenuItem("New");
    JMenuItem itemQuit = new JMenuItem("Quit");
    
    itemQuit.addActionListener(this); 
    itemNew.addActionListener(this);
    
    menu.add(itemNew);  
    menu.add(itemQuit);
    this.add(menu);
  }
  
  /**
  * Kickas i gång när någon väljer ett alternativ i menyn
  * @return none
  * @param e, innehållande objektet som blev klickat på i menyn
  */
  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Quit")){
	    System.exit(0);
	  } else {
	    newGame();
	  }
  }
  
  private void newGame() {
    NewGame newGame = new NewGame(creator);
  }
}