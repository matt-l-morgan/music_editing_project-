package cs3500.music;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    /*Complete the main() method, so that running your program with arguments "mary.txt" and
    "console" will produce the text output above, and running it with arguments "mystery-1.txt"
     and "midi" will play the first mystery file via MIDI.
     */

    MusicEditorModel model1 = new MusicEditorModel();
    Readable readable1 = new FileReader("mary-little-lamb.txt");
    MusicView view1 = MusicViewCreator.create("console");
    view1.display(model1);
  }
}
