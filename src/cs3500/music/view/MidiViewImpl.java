package cs3500.music.view;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.Pitch;
import cs3500.music.tests.MockSynthesizer;

import javax.sound.midi.*;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements MusicView {
  public static StringBuilder result = new StringBuilder("");
  private Synthesizer synth; //TODO: may have introduced variant making non-final, but it compiles
  private Receiver receiver;
  private int currBeat = 0;

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

  public MidiViewImpl(StringBuilder result){
/*    this.synth = new MockSynthesizer();
    try {
      this.receiver = this.synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.result = result;
  }*/
    Synthesizer synth = null;
    Receiver rec = null;
    try {
      synth = new MockSynthesizer();
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
//  public void playNote(int begin, int end, int instrument, int volume, int pitch, int tempo)
//          throws InvalidMidiDataException {
//    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
//    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
//    this.receiver.send(start, begin * tempo);
//    this.receiver.send(stop, end * tempo);
//  }

  public void playNote(AbstractNote a) throws InvalidMidiDataException {
    Instrument[] inst = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(inst[a.getInstrument()].getPatch()
      .getProgram());
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, a.getPandoValue(),
      a.getVolume());
    this.receiver.send(start, a.getStartbeat());
  }

  public void stopNote(AbstractNote a) throws InvalidMidiDataException {
    Instrument[] inst = this.synth.getDefaultSoundbank().getInstruments();
    this.synth.getChannels()[0].programChange(inst[a.getInstrument()].getPatch()
      .getProgram());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, a.getPandoValue(),
      a.getVolume());
    this.receiver.send(stop, a.getStartbeat() + a.getDuration() - 1);
  }
  /**converts from our representation of notes to the MIDI representation.
   * TODO: Opposite of current code
   * Not sure how yet, because not sure what shape the final shape of the output will be
   * */

  /**play each note in the piece*/
  //TODO: there should probably be a single point of control for logic that
  // converts to/from MIDI/internal representation
  @Override
  public void display(IMusicEditorModel model) {
    for (AbstractNote a : model.getNotesAtBeat(this.currBeat)) {
      try {
        if (this.currBeat == a.getStartbeat()) {
          this.playNote(a);
        }
        else if (this.currBeat == a.getDuration() + a.getStartbeat()) {
        this.stopNote(a);
      }
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
    this.currBeat++;
    if (this.currBeat >= model.getLastBeatInt()) {
      this.receiver.close();
    }
//      for (int i = 0; i < model.getLastBeatInt() + 1; i++) {
//        for (AbstractNote note : model.getNotesAtBeat(i)) {
//          if (note.getStartbeat() == i) {
//            try {
//              playNote(note.getStartbeat(),
//                      note.getDuration() + note.getStartbeat(),
//                      note.getInstrument(),
//                      note.getVolume(),
//                      note.getPitch().ordinal() + (note.getOctave() + 1) * 12,
//                      model.getTempo());
//            } catch (InvalidMidiDataException e) {
//
//              e.printStackTrace();
//            }
//          }
//        }
//      }
//
//    try {
//      Thread.sleep((model.getLastBeatInt() + 1) * model.getTempo() / 1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//
//    this.receiver.close(); // Only call this once you're done playing *all* notes
  }


}
