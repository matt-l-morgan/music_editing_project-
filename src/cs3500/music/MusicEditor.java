package cs3500.music;


import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;
import cs3500.music.model.*;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    /*Complete the main() method, so that running your program with arguments "mary.txt" and
    "console" will produce the text output above, and running it with arguments "mystery-1.txt"
     and "midi" will play the first mystery file via MIDI.
     */

    Readable readable1 = new FileReader(args[0]);
    IMusicEditorModel model1 = MusicReader.parseFile(readable1, new MusicEditorModel.Builder());
    MusicView console_view = MusicViewCreator.create("console");
    MusicView midi_view = MusicViewCreator.create("midi");
    MusicView gui_view = MusicViewCreator.create("gui");

    if (args[1].equals("midi")) {
      midi_view.display(model1);
    }
    else if (args[1].equals("console")) {
      console_view.display(model1);
    }
    else if (args[1].equals("gui")) {
      gui_view.display(model1);
    }
  }
}
