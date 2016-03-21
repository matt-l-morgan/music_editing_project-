package cs3500.music.model;

/**
 * a class to represent a note.
 * a note has a pitch and an octave
 */
public class Note extends AbstractNote {

  /**
   * constructor for a note
   * same as the Abstract notes
   */
  public Note(Pitch pitch, int octave, int duration, int startbeat){
    super(pitch, octave, duration, startbeat);
  }

  /**
   * constructor for a note
   * same as the Abstract notes
   */
  public Note(Pitch pitch, int octave, int duration, int startbeat, int volume, int instument){
    super(pitch, octave, duration, startbeat, volume, instument);
  }
}
