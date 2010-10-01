package memory;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.io.*;

/**
* Ett valt i menyn
* Sparar inställningarna till en fil
*/
class Save extends JMenuItem implements ActionListener {
  
  private Memory memory = null;
  
  /**
  * Sparar inställningarna från en extern fil
  * Se {memory.save()} för mer information
  * @param name, Texten som ska placeras på knappen i menyn
  * @param memory, Ett memory-objekt
  */
  public Save(String name, Memory memory){
    this.setText(name);
    this.memory = memory;
    this.addActionListener(this);
  }
  
  /**
  * Kickas igång när någon klickat på en knapp
  * Kallar i sin tur på memorys save-metod
  * @param e, Knappen som klickas på
  */
  public void actionPerformed(ActionEvent e){
    this.memory.save();
  }
  
}