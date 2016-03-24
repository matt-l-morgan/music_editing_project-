package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;

/**
 * Created by mattmorgan on 3/20/16.
 */


/**
 * interface for a Msuic view that has 1 method (display)
 */
public interface MusicView {
  /**
   * diplays the information in the model
   * @param model the model to be displayed
   */
  void display(IMusicEditorModel model);
}
