package cs3500.music.view;

/**
 * Factory class for creating models based on specified type
 */
public class MusicViewCreator {

  /**Create a whistmodel of the desired type*/
  public static MusicView create(String modelType) {

    if (modelType.equals("midi")) {
      return new MidiViewImpl();
    }
    if (modelType.equals("console")) {
      return new ConsoleView();
    }
    if (modelType.equals("gui")) {
      return new GuiViewFrame();
    }
    else throw new IllegalArgumentException("modelType not recognized");
  }
}
