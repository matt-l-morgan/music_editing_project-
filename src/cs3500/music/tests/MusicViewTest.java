package cs3500.music.tests;

import org.junit.Test;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;

import static org.junit.Assert.*;

/**
 * Created by Walker on 3/21/2016.
 */
public class MusicViewTest {
  @Test
  public void testDisplay() {
    return;
  }

  @Test
  public void testFactory() {
    assertEquals(true, MusicViewCreator.create("console") instanceof ConsoleView);
    assertEquals(true, MusicViewCreator.create("gui") instanceof GuiViewFrame);
    assertEquals(true, MusicViewCreator.create("midi") instanceof MidiViewImpl);
  }

  /**
   You must implement a mock MidiDevice to emulate the default MIDI Synthesizer.
   It must implement at minimum the getReceiver() method and return a mock Receiver
   that emulates actual MIDI receivers. This object in turn should log every call to send().
   Your mock classes should share a StringBuilder for use as the log.

   You must implement either a builder or convenience constructors for your MIDI view,
   so that by default the view uses the actual MIDI synthesizer,
   but for testing can be run with your mock instead. If you create a StringBuilder,
   and pass to the mock-synth, you can then read out the contents of the StringBuilder
   to confirm that you’ve played all the right notes.
   */

    /**
    1. make a mock MidiDevice to emulate the default MIDI Synthesizer. OK
    2. implement getReceiver() method and return a mock Receiver. OK
    3. log every call to send() in a StringBuilder. ---
    4. For MidiView, implement builder or convenience constructors so that you can test with mock ---?
    5. Create a StringBuilder, pass it to the mock-synth, then read out the contents ---
    of the StringBuilder to confirm that you've played all the right notes
    */
    @Test
    public void testMidiView() {

    }

  public final class MockSynthesizer implements MusicView {
    StringBuilder sb;

    public MockSynthesizer() {
      sb = new StringBuilder();
    }

    public MockSynthesizer getSynthesizer() {
      return this;
    }

    public void logSend() {

    }

    @Override
    public void display(IMusicEditorModel model) {

    }

  }

  /**MockReceiver logs every call to send() in a StringBuilder for testing the MIDI view
   */
  public final class MockReceiver implements MusicView {
      StringBuilder sb;

      MockReceiver() {
        sb = new StringBuilder();
      }

      public MockReceiver getReceiver() {
        return this;
      }

      /**log send in a StringBuilder*/
      public void logSend() {

      }

    @Override
    public void display(IMusicEditorModel model) {
      /**playNote but instead of *playing the note, add its string representation to sb*/

    }

    public void playNote(int begin, int end, int instrument, int volume, int pitch, int tempo) {
      /**MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
      MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
      this.receiver.send(start, begin * tempo);
      System.out.print(pitch + "\n");
      this.receiver.send(stop, end * tempo); //this line is wrong*/
    }

  }
}

