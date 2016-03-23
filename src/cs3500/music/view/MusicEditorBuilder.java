package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.util.CompositionBuilder;

/**
 * Builds a CompositionBuilder for MusicViews, to be parsed by MusicReader
 *
 * Describes how to build MusicView note by note
 */
public final class MusicEditorBuilder implements MusicView {
  @Override
  public void display(IMusicEditorModel model) {

  }

  public static final class Builder implements CompositionBuilder<MusicView> {
    @Override
    public MusicView build() {
      return null;
    }

    @Override
    public CompositionBuilder<MusicView> setTempo(int tempo) {
      return null;
    }

    @Override
    public CompositionBuilder<MusicView> addNote(int start, int end, int instrument,
      int pitch, int volume) {
      return null;
    }
  }

}
