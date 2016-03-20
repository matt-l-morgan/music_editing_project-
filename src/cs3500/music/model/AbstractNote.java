package cs3500.music.model;

import java.net.PortUnreachableException;

/**
 * Created by mattmorgan on 3/20/16.
 */
public abstract class AbstractNote {

  /**
   * pitch of this note
   */
  private Pitch pitch;

  /**
   * octave of this note
   * INVARIANT: octave is >= 0 and < 10
   */
  private int octave;

  /**
   * duration of the note
   * INVARIANT: duration > 0
   */
  private int duration;

  /**
   * give a value for the pitch and octave
   */
  private int pandoValue;

  /**
   * the beat where this note starts
   * INVARIANT: startbeat is >= 0
   */
  private int startbeat;

  /**
   * gives the string representation of a note
   * @return the String of a pitch and octave together
   */
  public String toString(){
    return this.pitch.toString() + this.octave;
  }

  /**
   * getter for pitch
   * @return this note's pitch
   */
  public Pitch getPitch(){
    return this.pitch;
  }

  /**
   * getter for octave
   * @return the octave of the note
   */
  public int getOctave(){
    return this.octave;
  }

  /**
   * getter for duration
   * @return the duration of this note.
   */
  public int getDuration(){
    return this.duration;
  }

  public int getStartbeat() {
    return this.startbeat;
  }

  public int getPandoValue(){
    return this.pandoValue;
  }

  /**
   * mutates a note by changing it's pitch
   * @param pitch the new pitch of this note
   */
  public void changePitch(Pitch pitch){
    this.pitch = pitch;
    this.pandoValue = 12 * octave + pitch.ordinal();
  }

  /**
   * mutates a note by changing it's Octave
   * @param octave the new octave of this note
   */
  public void changeOctave(int octave){
    if (octave >= 0 && octave < 10) {
      this.octave = octave;
      this.pandoValue = 12 * octave + pitch.ordinal();
    }
  }

  /**
   * mutates a note by changing it's Duration
   * @param duration the new Duration of this note
   */
  public void changeDuration(int duration) {
    if (duration > 0) {
      this.duration = duration;
    }
  }

  /**
   * changest the starting beat of the note
   * @param startbeat the new beat to start on.
   */
  public void changeStartbeat(int startbeat) {
    if (startbeat >= 0) {
      this.startbeat = startbeat;
    }
  }

}
