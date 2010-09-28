import javax.swing.*;

class Memory extends JFrame {
  
  public Memory(){
    
  }
  
  public void hasScored(){
    
  }
  
  public void nextPlayer(){
  }
  
  public static void main(String[] args){
    Memory memory  = new Memory();
    Cards cards   = new Cards(10,100,memory);
    
    memory.add(cards);
    memory.setVisible(true);
  }
}