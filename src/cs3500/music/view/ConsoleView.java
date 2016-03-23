package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;

/**
 * Created by mattmorgan on 3/20/16.
 */
public class ConsoleView implements MusicView {
  /**pretty prints the music piece to the console*/
  @Override public void display(IMusicEditorModel model) {
    System.out.print(model.displaySong());
  }
}
