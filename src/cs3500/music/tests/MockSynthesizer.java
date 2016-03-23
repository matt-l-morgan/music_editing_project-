package cs3500.music.tests;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.view.MusicView;

/**
 * Mocks a MIDI Synthesizer for testing purposes
 */
public class MockSynthesizer implements MusicView {
  private final IMusicEditorModel model;
  StringBuilder sb;

  public MockSynthesizer(IMusicEditorModel model) {
    this.model = model;
    sb = new StringBuilder();
  }

  public MockSynthesizer getSynthesizer() {
    return this;
  }

  public void send() {
    sb.append()
  }

  @Override
  public void display(IMusicEditorModel model) {

  }

  public void playNote(int begin, int end, int instrument, int volume, int pitch, int tempo) {
    /**MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
     MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
     this.receiver.send(start, begin * tempo);
     System.out.print(pitch + "\n");
     this.receiver.send(stop, end * tempo); //this line is wrong*/
  }

}