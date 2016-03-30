package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * Created by mattmorgan on 3/30/16.
 */
public class MouseHandler implements MouseListener {
  private final Map<Integer, Runnable> clicked, pressed, released;

  /**
   * constuctor
   * @param clicked map for when mouse is clocked
   * @param pressed map for when mouse is pressed
   * @param released map for when mous is release
   */
  private  MouseHandler(Map<Integer, Runnable> clicked, Map<Integer, Runnable> pressed,
    Map<Integer, Runnable> released){
    this.clicked = clicked;
    this.pressed = pressed;
    this.released = released;
  }

  @Override public void mouseClicked(MouseEvent e) {

  }

  @Override public void mousePressed(MouseEvent e) {

  }

  @Override public void mouseReleased(MouseEvent e) {

  }

  @Override public void mouseEntered(MouseEvent e) {

  }

  @Override public void mouseExited(MouseEvent e) {

  }

}
