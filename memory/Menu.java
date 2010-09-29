import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;

class Menu extends JMenuBar implements ActionListener {
  /* Några riktiga kommentarer kommer så småningom */
  public Menu(){    
    JMenu menu = new JMenu("Game"); 
    JMenuItem itemNew = new JMenuItem("New");
    JMenuItem itemQuit = new JMenuItem("Quit");
    
    itemQuit.addActionListener(this); 
    itemNew.addActionListener(this);
    
    menu.add(itemNew);  
    menu.add(itemQuit);
    this.add(menu);
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Quit")){
	    System.exit(0);
	  } else {
	    System.out.println("Du har nu skapat ett ny spel, eller?");
	  }
  }
}