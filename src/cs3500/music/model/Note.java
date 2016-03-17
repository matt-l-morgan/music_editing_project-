package cs3500.music.model;

/**
 * a class to represent a note.
 * a note has a pitch and an octave
 */
public class Note {

  /**
   * enum to represent a pitch, there are 12 different pitches
   * they also have string repreresentations.
   */
  public enum Pitch {
    C("C"), Csharp("C♯"), D("D"), Dsharp("D♯"), E("E"), F("F"), Fsharp("F♯"),
    G("G"), Gsharp("G♯"), A("A"), Asharp("A♯"), B("B");
    /**
     * string representation fo a pitch
     */
    private final String asString;

    /**
     * constuctor for a pitch
     *
     * @param asString string representation of a pitch
     */
    Pitch(String asString) {
      this.asString = asString;
    }


    public static String toStringFromint(int pitch) {
      switch (pitch) {
        case 0:
          return "C";
        case 1:
          return "C#";
        case 2:
          return "D";
        case 3:
          return "D#";
        case 4:
          return "E";
        case 5:
          return "F";
        case 6:
          return "F#";
        case 7:
          return "G";
        case 8:
          return "G#";
        case 9:
          return "A";
        case 10:
          return "A#";
        case 11:
          return "B";
        default:
          throw new IllegalArgumentException("Not a valid pitch.");
      }
    }
  }


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
   * constructor for a note
   * @param pitch pitch of the note
   * @param octave the octave of the note
   * @param duration duration of the note
   */
  public Note(Pitch pitch, int octave, int duration, int startbeat)
    throws IllegalArgumentException {
    if (octave >= 0 && octave < 10 && duration > 0 && startbeat >= 0) {
      this.pitch = pitch;
      this.octave = octave;
      this.duration = duration;
      this.pandoValue = 12 * octave + pitch.ordinal();
      this.startbeat = startbeat;
    }
    else {
      throw new IllegalArgumentException("no such note!");
    }
  }


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
