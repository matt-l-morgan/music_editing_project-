package cs3500.music.tests;

/**
 * Created by Walker on 3/23/2016.
 */

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.view.MusicView;

/**MockReceiver logs every call to send() in a StringBuilder for testing the MIDI view
 */
public class MockReceiver implements Receiver {
  private final IMusicEditorModel model;
  StringBuilder sb;

  public MockReceiver(IMusicEditorModel model) {
    this.model = model;
    sb = new StringBuilder();
  }

  public MockReceiver getReceiver() {
    return this;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {

  }

  @Override
  public void close() {

  }
}
