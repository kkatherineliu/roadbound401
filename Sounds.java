// *** imports *** //
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/** Sounds.java
  * A class that loads and plays the indicated sound effects
  * @author Katherine Liu and Rachel Liu
  * @version 1.0 June 9, 2021
  */

public class Sounds {
  /*
   * CLASS VARIABLES
   */
  public static AudioInputStream audioStreamMusic;
  public static Clip music;
  public static AudioInputStream audioStreamTraffic;
  public static Clip traffic;
  
  /**
   * Sounds
   * This constructor is used to initialize a Sounds object
   * that can later load and play music/sound effects
   */
  Sounds() {
  }
  
  /**
   * play
   * This method loads and plays the background music for the game
   */
  public void play() {
    try {
      File audioFileMusic = new File("background_music.wav");
      audioStreamMusic = AudioSystem.getAudioInputStream(audioFileMusic);
      music = AudioSystem.getClip();
      music.open(audioStreamMusic);
      
      File audioFileTraffic = new File("traffic.wav");
      audioStreamTraffic = AudioSystem.getAudioInputStream(audioFileTraffic);
      traffic = AudioSystem.getClip();
      traffic.open(audioStreamTraffic);
      
      music.start();
      music.loop(Clip.LOOP_CONTINUOUSLY);
      traffic.start();
      traffic.loop(Clip.LOOP_CONTINUOUSLY);
      
    } catch (Exception ex) {
      System.out.println("Error Loading BGM");
    }
  }
  
  /**
   * play
   * This method loads and plays the sound effects for the game
   * @param clip The specific sound effect that should be played
   */
  public void play(Clip clip) {    
    clip.flush();     
    clip.setFramePosition(0);
    clip.start();
  }
  
  /**
   * load
   * This method loads the specified sound effect
   * @param num The number associated with the specific sound effect
   * @return The clip that has been loaded
   */
  public Clip load(int num) {
    File audioFile = new File("");
    Clip clip;
    
    try {
      if (num == 1) {
        audioFile = new File("game_over.wav");
      } else if (num == 2) {
        audioFile = new File("train_warning.wav");
      } else if (num == 3) {
        audioFile = new File("shooting.wav");
      } else if (num == 4) {
        audioFile = new File("ability.wav");
      } else {
        System.out.println("Sorry that sound does not exist");
      }

      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      clip = AudioSystem.getClip();
      clip.open(audioStream);
      return clip;
      
    } catch (Exception ex) {
      System.out.println("Error Loading Sound Effects");
    } 
    return null;
  }
  
  /**
   * listSounds
   * This method displays the numbers associated with each sound
   */
  public void listSounds() {
    System.out.println("The following are formatted as # - sound effect: \n");
    System.out.println("1 - game over sound");
    System.out.println("2 - train incoming");
    System.out.println("3 - shooting and popping rocks");
    System.out.println("4 - collecting abilities");
  }
  
  /**
   * close
   * This method closes the background music
   */
  public void close() {
    music.close();
    traffic.close();
  }
}