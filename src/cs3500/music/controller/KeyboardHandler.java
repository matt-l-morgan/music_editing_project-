package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mattmorgan on 3/30/16.
 */


/**
 * classes that handles the keyboard
 */
public class KeyboardHandler implements KeyListener {
  private final Map<Integer, Runnable> whenTyped, whenPressed, whenReleased;

  /**
   * constuctor of the keyboardHandler
   * @param whenTyped a map when a key is typed
   * @param whenPressed a map for when a key is pressed
   * @param whenReleased a map for when a key is released
   */
  private KeyboardHandler(Map<Integer, Runnable> whenTyped, Map<Integer, Runnable> whenPressed,
    Map<Integer, Runnable> whenReleased) {
    this.whenTyped = whenTyped;
    this.whenPressed = whenPressed;
    this.whenReleased = whenReleased;
  }


  @Override
  public void keyTyped(KeyEvent event) {
    if (this.whenTyped.get(event.getKeyCode()) != null) {
      this.whenTyped.get(event.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent event) {
    if (this.whenPressed.get(event.getKeyCode()) != null) {
      this.whenPressed.get(event.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent event) {
    if (this.whenReleased.get(event.getKeyCode()) != null) {
      this.whenReleased.get(event.getKeyCode()).run();
    }
  }


  /**
   * Represents a builder class for the Key handler
   */
  public static final class Builder {
    private final Map<Integer, Runnable> typed = new HashMap<>();
    private final Map<Integer, Runnable> pressed = new HashMap<>();
    private final Map<Integer, Runnable> released = new HashMap<>();

    /**
     * Constructs an actual handler, given the maps(with lambdas and all) for each type of key
     * handler
     */
    public KeyboardHandler build() {
      return new KeyboardHandler(this.typed, this.pressed, this.released);
    }

    /**
     * Adds a typed key runnable} for a specific key
     */
    public Builder addKeyTyped(int c, Runnable r) {
      this.typed.put(c, r);
      return this;
    }

    /**
     * Adds a pressed key lambda for a specific c
     */
    public Builder addKeyPressed(int c, Runnable r) {
      this.pressed.put(c, r);
      return this;
    }

    /**
     * Adds a released key lambda for a specific c
     */
    public Builder addKeyReleased(int c, Runnable r) {
      this.released.put(c, r);
      return this;
    }
  }
}
