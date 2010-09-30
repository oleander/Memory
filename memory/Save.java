import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.io.*;

class Save extends JMenuItem implements ActionListener {
  
  private Memory memory = null;
  
  public Save(String name, Memory memory){
    this.setText(name);
    this.memory = memory;
    this.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e){
    this.memory.save();
  }
  
}