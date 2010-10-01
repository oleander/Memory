import  sun.audio.*;
import  java.io.*;
import  java.awt.Toolkit;

/**
* Spelar upp ljud för applikationen
*/
public class Sound {
  
  /**
  * Spelar upp ett fel-ljud
  */
  public static void error(){
    Toolkit.getDefaultToolkit().beep();
  }
  
  /**
  * Spelar upp ett OK-ljud
  * Om ljudet inte finns så spelas error() upp istället
  */
  public static void okey(){
    Sound.play("tada");
  }
  
  /**
  * Spelar upp ett vinnar-ljud
  * Om ljudet inte finns så spelas error() upp istället
  */
  public static void winner(){
    Sound.play("hallelujah");
  }
  
  /* Spelar upp ingående ljudsträng */
  private static void play(String media){
    try{
      InputStream in = new FileInputStream("audio/" + media + ".wav");
      AudioStream as = new AudioStream(in);         
      AudioPlayer.player.start(as);
    } catch(Exception e) {
      /* Om det är något som inte fungerar så spelar error-ljudet upp i stället */
      Sound.error();
    }
  }
}