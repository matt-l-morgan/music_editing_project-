package cs3500.music.tests;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;

import javax.sound.midi.*;
import java.util.List;

/**
 * Mocks a MIDI Synthesizer for testing purposes
 */
public class MockSynthesizer implements Synthesizer{


  public MockSynthesizer() {
  }

  public MockSynthesizer getSynthesizer() { return this;
  }

  public void send() {

  }


  public void playNote(int begin, int end, int instrument, int volume, int pitch, int tempo) {
    /**MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, instrument, pitch, volume);
     MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, instrument, pitch, volume);
     this.receiver.send(start, begin * tempo);
     System.out.print(pitch + "\n");
     this.receiver.send(stop, end * tempo); //this line is wrong*/
  }


  @Override public int getMaxPolyphony() {
    return 0;
  }

  @Override public long getLatency() {
    return 0;
  }

  @Override public MidiChannel[] getChannels() {
    return new MidiChannel[0];
  }

  @Override public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  @Override public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  @Override public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  @Override public void unloadInstrument(Instrument instrument) {

  }

  @Override public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  @Override public Soundbank getDefaultSoundbank() {
    return null;
  }

  @Override public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  @Override public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  @Override public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  @Override public void unloadAllInstruments(Soundbank soundbank) {

  }

  @Override public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  @Override public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }

  @Override public Info getDeviceInfo() {
    return null;
  }

  @Override public void open() throws MidiUnavailableException {
  }

  @Override public void close() {

  }

  @Override public boolean isOpen() {
    return false;
  }

  @Override public long getMicrosecondPosition() {
    return 0;
  }

  @Override public int getMaxReceivers() {
    return 0;
  }

  @Override public int getMaxTransmitters() {
    return 0;
  }

  /**
   * returns a new midi Receiver
   * @return MockReciever
   * @throws MidiUnavailableException if unavailable
   */
  @Override public Receiver getReceiver() throws MidiUnavailableException {
    return new MockReceiver();
  }

  @Override public List<Receiver> getReceivers() {
    return null;
  }

  @Override public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override public List<Transmitter> getTransmitters() {
    return null;
  }
}
