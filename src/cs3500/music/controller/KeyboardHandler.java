package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Created by mattmorgan on 3/30/16.
 */
public class KeyboardHandler implements KeyListener {
  private final Map<Integer, Runnable> whenTyped, whenPressed, whenReleased;

  private KeyboardHandler(Map<Integer, Runnable> whenTyped, Map<Integer, Runnable> whenPressed,
    Map<Integer, Runnable> whenReleased) {
    this.whenTyped = whenTyped;
    this.whenPressed = whenPressed;
    this.whenReleased = whenReleased;
  }

  @Override public void keyTyped(KeyEvent e) {

  }

  @Override public void keyPressed(KeyEvent e) {

  }

  @Override public void keyReleased(KeyEvent e) {

  }
}
