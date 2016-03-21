package cs3500.music;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;

import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    /*Complete the main() method, so that running your program with arguments "mary.txt" and
    "console" will produce the text output above, and running it with arguments "mystery-1.txt"
     and "midi" will play the first mystery file via MIDI.
     */
  }

  /**
   * select and output the proper type of MusicView for file by delegating to viewType's
   * display method via the MusicView interface
   * @param viewType
   * @param file
   */
  public static void createView(String viewType, Readable file) {
    if (viewType.equals("gui")) {
      GuiViewFrame guiViewFrame = new GuiViewFrame();
      //guiViewFrame.display(file);
      return;
    }
    if (viewType.equals("midi")) {
      MidiViewImpl midiView = new MidiViewImpl();
      //midiView.display(file);
      return;
    }
    if (viewType.equals("console")) {
      ConsoleView consoleView = new ConsoleView();
      //consoleView.display(file);
      return;
    }
    // You probably need to connect these views to your model, too...
  }
}
