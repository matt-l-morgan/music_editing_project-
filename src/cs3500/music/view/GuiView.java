package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by mattmorgan on 3/30/16.
 */
public interface GuiView extends MusicView{
  /**
   * adds a key listener to the view
   * @param k the listener
   */
  void addKeyListener(KeyListener keyListener);

  /**
   * adds a mouse listener to the view
   * @param m the mouselistener
   */
  void addMouseListener(MouseListener mouseListener);

  /**
   * updates the view based on the given beat
   * @param beat
   */
  void update(int beat);


  /**
   * moves the view up when for the controller to use
   */
  void moveUp();
  /**
   * moves the view down when for the controller to use
   */
  void moveDown();
  /**
   * moves the view right when for the controller to use
   */
  void moveRight();
  /**
   * moves the view left when for the controller to use
   */
  void moveLeft();

  /**
   * moves view tot he start of the piece
   */
  void goToStart();

  /**
   * moves view to the end of the piece
   */
  void goToEnd();

  /**
   * Controls the pause and play of the piece of music
   */
  void pause();

  /**
   * Removes a {@link MouseListener} from the GUI view
   *
   * @param m the {@link MouseListener} to be removed
   */
  void removeMouseListener(MouseListener m);

}
