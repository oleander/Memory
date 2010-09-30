import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;
import java.io.*;

class Load extends JMenuItem implements ActionListener {
  
  private Memory memory = null;
  
  public Load(String name, Memory memory){
    this.setText(name);
    this.memory = memory;
    this.addActionListener(this);
  }
  
  public void actionPerformed(ActionEvent e){
    this.memory.load();
  }
}