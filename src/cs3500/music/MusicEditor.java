package cs3500.music;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    /*Complete the main() method, so that running your program with arguments "mary.txt" and
    "console" will produce the text output above, and running it with arguments "mystery-1.txt"
     and "midi" will play the first mystery file via MIDI.
     */

    MusicView view1 = MusicViewCreator.create(System.getenv("viewType"));
    //readable...
    //not done
  }
}
