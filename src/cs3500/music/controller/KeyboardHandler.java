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
    if (this.whenTyped.get(e.getKeyCode()) != null) {
      this.whenTyped.get(e.getKeyCode()).run();
    }
  }

  @Override public void keyPressed(KeyEvent e) {
    if (this.whenPressed.get(e.getKeyCode()) != null) {
      this.whenPressed.get(e.getKeyCode()).run();
    }
  }

  @Override public void keyReleased(KeyEvent e) {
    if (this.whenReleased.get(e.getKeyCode()) != null) {
      this.whenReleased.get(e.getKeyCode()).run();
    }
  }
}
