import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.io.*;

/**
* Ett valt i menyn
* Läser inställningarna från en fil
*/
public class Load extends JMenuItem implements ActionListener {
  
  private Memory memory = null;
  
  /**
  * Laddar in inställningarna från en extern fil
  * Se {memory.load()} för mer information
  * @param name, Texten som ska placeras på knappen i menyn
  * @param memory, Ett memory-objekt
  */
  public Load(String name, Memory memory){
    this.setText(name);
    this.memory = memory;
    this.addActionListener(this);
  }
  
  /**
  * Kickas igång när någon klickat på en knapp
  * Kallar i sin tur på memorys load-metod
  * @param e, Knappen som klickas på
  */
  public void actionPerformed(ActionEvent e){
    this.memory.load();
  }
}