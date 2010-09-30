import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

/**
* Menyn för applikationen
*/
class Menu extends JMenuBar implements ActionListener {
  
  /**
  * Konstruktor, no shit
  * @param, none
  * @return none
  */
  public Menu(Memory memory){
    JMenu menu = new JMenu("Game"); 
    
    /* Innehållet i menyn */
    JMenuItem itemNew  = new JMenuItem("New");
    JMenuItem itemQuit = new JMenuItem("Quit");
    
    Save itemSave = new Save("Save", memory);
    Load itemLoad = new Load("Load", memory);
    
    itemQuit.addActionListener(this); 
    itemNew.addActionListener(this);
    
    menu.add(itemNew);  
    menu.add(itemQuit);
    menu.add(itemSave);
    menu.add(itemLoad);
    
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
	  } else if (e.getActionCommand().equals("Save")) {
	    System.out.println("Spara");
	  } else {
	    System.out.println("Du har nu skapat ett ny spel, eller?");
	  }
  }
}