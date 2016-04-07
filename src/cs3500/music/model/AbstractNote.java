package cs3500.music.model;

/**
 * Created by mattmorgan on 3/20/16.
 */
public abstract class AbstractNote {

  /**
   * pitch of this note
   */
  protected Pitch pitch;

  /**
   * octave of this note
   * INVARIANT: octave is >= 0 and < 10
   */
  protected int octave;

  /**
   * duration of the note
   * INVARIANT: duration > 0
   */
  protected int duration;

  /**
   * give a value for the pitch and octave
   */
  protected int pandoValue;

  /**
   * the beat where this note starts
   * INVARIANT: startbeat is >= 0
   */
  protected int startbeat;

  protected int instrument;

  protected int volume;

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
   * @return the duration of this Abstract note.
   */
  public int getDuration(){
    return this.duration;
  }

  /**
   * getting for startbeat
   * @return the startbeat for this note
   */
  public int getStartbeat() {
    return this.startbeat;
  }

  /**
   * getter for pandovalue
   * @return the pandovalue of the this note
   */
  public int getPandoValue(){
    return this.pandoValue;
  }

  /**
   * Getter for this instument
   * @return the instument for htis abstractnote
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * Getter for this volume
   * @return the volume for this abstractnote
   */
  public int getVolume() {
    return this.volume;
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

  /**
   * constuctor for an abstract note
   * @param pitch
   * @param octave
   * @param duration
   * @param startbeat
   * @throws IllegalArgumentException
   */
  public AbstractNote(Pitch pitch, int octave, int duration, int startbeat)
    throws IllegalArgumentException {
    if (octave >= 0 && octave < 10 && duration > 0 && startbeat >= 0) {
      this.pitch = pitch;
      this.octave = octave;
      this.duration = duration;
      this.pandoValue = 12 * octave + pitch.ordinal();
      this.startbeat = startbeat;
      this.volume = 0;
      this.instrument = 0;
    }
    else {
      throw new IllegalArgumentException("no such note!");
    }
  }

  /**
   * constuctor for abstract note
   * @param pitch
   * @param octave
   * @param duration
   * @param startbeat
   * @param volume
   * @param instrument
   * @throws IllegalArgumentException
   */
  public AbstractNote(Pitch pitch, int octave, int duration,
    int startbeat, int volume, int instrument)
    throws IllegalArgumentException {
    if (octave >= 0 && octave < 10 && duration > 0 && startbeat >= 0 && volume >= 0 &&
      instrument >= 0 && instrument < 128) {
      this.pitch = pitch;
      this.octave = octave;
      this.duration = duration;
      this.pandoValue = 12 * octave + pitch.ordinal();
      this.startbeat = startbeat;
      this.volume = volume;
      this.instrument = instrument;
    }
    else {
      throw new IllegalArgumentException("no such note!");
    }
  }

}
