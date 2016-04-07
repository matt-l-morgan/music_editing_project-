package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import cs3500.music.model.IMusicEditorModel;

/**
 * Represents a composite view with gui and midid
 */
public final class CompositeView implements GuiView {
  private final GuiViewFrame guiFrame;
  private final MusicView midi;
  private IMusicEditorModel m;
  public boolean paused = false;

  /**
   * Constructor for a composite view
   * @param guiFrame the gui view used
   * @param midi midi view used
   */
  public CompositeView(GuiViewFrame guiFrame, MidiViewImpl midi) {
    this.guiFrame = guiFrame;
    this.midi = midi;
  }

  @Override
  public void display(IMusicEditorModel m) {
    this.m = m;
    this.guiFrame.display(m);
  }




  /**
   * passes the up movemnet to the gui view
   */
  public void moveUp() {
    this.guiFrame.moveUp();
  }

  /**
   * passes the down movemnet to the gui view
   */
  public void moveDown() {
    this.guiFrame.moveDown();
  }

  /**
   * passes the right movemnet to the gui view
   */
  public void moveRight() {
    this.guiFrame.moveRight();
  }

  /**
   * passes the left movemnet to the gui view
   */
  public void moveLeft() {
    this.guiFrame.moveLeft();
  }

  /**
   * passes the go to start command to the gui view
   */
  public void goToStart() {
    this.guiFrame.goToStart();
  }

  /**
   * passes the end movement to the gui view
   */
  public void goToEnd() {
    this.guiFrame.goToEnd();
  }

  /**
   *updates for ever note at a beat
   */
  public void update(int currBeat) {
    if (!this.paused) {
      this.guiFrame.update(currBeat);
      this.midi.display(m);
    }
  }

  /**
   * gets the current beat
   * @return
   */
  public int getCurrentBeat(){
    return GuiViewFrame.beat;
  }

  /**
   * pauses and un pauses
   */
  public void pause() {
    this.paused = !this.paused;
  }


  /**
   * Adds a key Listener
   */
  public void addKeyListener(KeyListener k) {
    this.guiFrame.addKeyListener(k);
  }

  /**
   * Adds a Mouselistener
   */
  public void addMouseListener(MouseListener m) {
    this.guiFrame.addMouseListener(m);
  }

  /**
   * Removes a Mouse listener
   */
  public void removeMouseListener(MouseListener m) {
    this.guiFrame.removeMouseListener(m);
  }
}

