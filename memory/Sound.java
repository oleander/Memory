import  sun.audio.*;
import  java.io.*;
import  java.awt.Toolkit;

/**
* Spelar upp ljud för applikationen
*/
class Sound {
  
  /**
  * Spelar upp ett fel-ljud
  */
  public static void error(){
    Toolkit.getDefaultToolkit().beep();
  }
  
  /**
  * Spelar upp ett OK-ljud
  * Om OK-ljudet inte finns så spelas error() upp istället
  */
  public static void okey(){
    try{
      InputStream in = new FileInputStream("audio/tada.wav");
      AudioStream as = new AudioStream(in);         
      AudioPlayer.player.start(as);
    } catch(Exception e) {
      /* Om det är något som inte fungerar så spelar error-ljudet upp i stället */
      Sound.error();
    }
  }
}