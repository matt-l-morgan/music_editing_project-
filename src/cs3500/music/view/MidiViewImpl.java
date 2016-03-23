package cs3500.music.view;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import javax.sound.midi.*;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MusicView {
  private Synthesizer synth; //TODO: may have introduced variant making non-final, but it compiles
  private Receiver receiver;

  public MidiViewImpl() {
    Synthesizer synth = null;
    Receiver rec = null;
    try {
      synth = MidiSystem.getSynthesizer();
      rec = synth.getReceiver();
      synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = synth;
    this.receiver = rec;
  }
  /*
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   *  <li>{@link MidiSystem#getSynthesizer()}</li>
   *  <li>{@link Synthesizer}
   *    <ul>
   *      <li>{@link Synthesizer#open()}</li>
   *      <li>{@link Synthesizer#getReceiver()}</li>
   *      <li>{@link Synthesizer#getChannels()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link Receiver}
   *    <ul>
   *      <li>{@link Receiver#send(MidiMessage, long)}</li>
   *      <li>{@link Receiver#close()}</li>
   *    </ul>
   *  </li>
   *  <li>{@link MidiMessage}</li>
   *  <li>{@link ShortMessage}</li>
   *  <li>{@link MidiChannel}
   *    <ul>
   *      <li>{@link MidiChannel#getProgram()}</li>
   *      <li>{@link MidiChannel#programChange(int)}</li>
   *    </ul>
   *  </li>
   * </ul>
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   *   </a>
   */

  /*the correct notes are stored in the model, so we could convert them back to midi
  and send them to the synth, or we could just call the midi song directly somehow.

  Then send notes to receiver with playNote and  use Thread.sleep(time to play song in ms)
  to keep the stream open long enough to play the track

  1. convert notes in model back to midi
  2. .send each note to receiver
  3. set start and stops / handle tempo (???)
  4. use Thread.sleep(# milliseconds: based on model.getHighestNote() and tempo)
   to keep the stream open long enough for the notes to actually play back
  5. close the stream

  Can we start by sending a single note and getting it to playback? OK cool
  What is that note?

  It's not quite clear how to put instrument, pitch, and volume into shortmessage as data byte

  But we can start by displaying: for note in model.getNotes,
  playNote(start, duration, instrument, pitch, volume)
   */

  public void playNote(int begin, int end, int instrument, int volume, int pitch)
          throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
    this.receiver.send(start, begin * 200000);
    System.out.print(pitch + "\n");
    this.receiver.send(stop, synth.getMicrosecondPosition() + (end - begin) * 200000); //200000 should be tempo
  }

  /**converts from our representation of notes to the MIDI representation.
   * TODO: Opposite of current code
   * Not sure how yet, because not sure what shape the final shape of the output will be
   * */

  /**play each note in the piece*/
  //TODO: there should probably be a single point of control for logic that converts to/from MIDI/internal representation
  @Override public void display(IMusicEditorModel model) {
      for (int i = 0; i < model.getHighestNoteInt(); i++) {
        for (AbstractNote note : model.getNotesAtBeat(i)) {
          if (note.getStartbeat() == i) {
            try {
              playNote(note.getStartbeat(),
                      note.getDuration() + note.getStartbeat(),
                      note.getInstrument(),
                      note.getVolume(),
                      note.getPitch().ordinal() + (note.getOctave() + 1) * 12);
            } catch (InvalidMidiDataException e) {

              e.printStackTrace();
            }
          }
        }
      }

    try {
      Thread.sleep(20000); //TODO: math...f(tempo, model.getHighestNoteInt())
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    this.receiver.close(); // Only call this once you're done playing *all* notes
  }
}
