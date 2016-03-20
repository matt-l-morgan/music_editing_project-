package cs3500.music.model;

/**
 * a class to represent a note.
 * a note has a pitch and an octave
 */
public class Note extends AbstractNote {
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
}
