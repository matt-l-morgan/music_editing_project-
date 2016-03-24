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
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;

/**MockReceiver logs every call to send() in a StringBuilder for testing the MIDI view
 */
public class MockReceiver implements Receiver {

  public MockReceiver() {
  }

  public MockReceiver getReceiver() {
    return this;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    byte[] mess;
    mess = message.getMessage();

    ShortMessage msg = (ShortMessage) message;

    if (MidiViewImpl.result.equals(new StringBuilder(""))) {
      MidiViewImpl.result.append("Tempo");
      MidiViewImpl.result.append(timeStamp / mess[0]);
    }
    if (mess[0] == -111) {
      MidiViewImpl.result.append("note volume: " + msg.getData2() + "\n");

    }
  }

  @Override
  public void close() {
  }
}
