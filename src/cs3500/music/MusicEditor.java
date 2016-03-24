package cs3500.music;


import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;
import cs3500.music.model.*;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


/**
 * the main class that displays the given the music file in view type
 */
public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    /*Complete the main() method, so that running your program with arguments "mary.txt" and
    "console" will produce the text output above, and running it with arguments "mystery-1.txt"
     and "midi" will play the first mystery file via MIDI.
     */
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.Builder();
    MusicReader.parseFile(new FileReader(args[0]), builder);

    if (args[1].equals("midi") ) {
      MusicView midi_view = MusicViewCreator.create("midi");
      midi_view.display(builder.build());
    }
    else if (args[1].equals("console")){
      MusicView console_view = MusicViewCreator.create("console");
      console_view.display(builder.build());
    }
    else if (args[1].equals("gui")) {
      MusicView gui_view = MusicViewCreator.create("gui");
      gui_view.display(builder.build());
    }
    else {
      throw new IllegalArgumentException("invalid view type");
    }
  }
}
