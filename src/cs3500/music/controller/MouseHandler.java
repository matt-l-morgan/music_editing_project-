package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a way to handle input from the mouse
 */
public final class MouseHandler implements MouseListener {
  private final Map<Integer, Runnable> clicked, pressed, released, entered,
    exited;
  private MouseEvent event;

  /**
   * Constructor for a mouse handler
   *
   * @param clicked  the map for mouse clicks
   * @param pressed  map for mouse presses
   * @param released map for mouse releases
   * @param entered  map for mouse enters
   * @param exited  map for mouse exits
   */
  private MouseHandler(Map<Integer, Runnable> clicked, Map<Integer, Runnable> pressed,
    Map<Integer, Runnable> released, Map<Integer, Runnable> entered,
    Map<Integer, Runnable> exited) {
    this.clicked = clicked;
    this.pressed = pressed;
    this.released = released;
    this.entered = entered;
    this.exited = exited;
  }

  /**
   * Gets the current mouse event
   */
  public MouseEvent getCurrMouseEvent() {
    return this.event;
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    this.event = event;
    if (this.clicked.get(event.getButton()) != null) {
      this.clicked.get(event.getButton()).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent event) {
    this.event = event;
    if (this.pressed.get(event.getButton()) != null) {
      this.pressed.get(event.getButton()).run();
    }
  }

  @Override
  public void mouseReleased(MouseEvent event) {
    this.event = event;
    if (this.released.get(event.getButton()) != null) {
      this.released.get(event.getButton()).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent event) {
    this.event = event;
    if (this.entered.get(event.getButton()) != null) {
      this.entered.get(event.getButton()).run();
    }
  }

  @Override
  public void mouseExited(MouseEvent event) {
    this.event = event;
    if (this.exited.get(event.getButton()) != null) {
      this.exited.get(event.getButton()).run();
    }
  }

  /**
   * Represents a builder for this mouse handler
   */
  public static final class Builder {
    private final Map<Integer, Runnable> clicked = new HashMap<>();
    private final Map<Integer, Runnable> pressed = new HashMap<>();
    private final Map<Integer, Runnable> released = new HashMap<>();
    private final Map<Integer, Runnable> entered = new HashMap<>();
    private final Map<Integer, Runnable> exited = new HashMap<>();

    /**
     * builds the mouse handler with the builder's fields
     */
    public MouseHandler build() {
      return new MouseHandler(this.clicked, this.pressed, this.released,
        this.entered, this.exited);
    }

    /**
     * Adds a clicked mouse lambda for a specific button
     */
    public Builder addMouseClicked(int b, Runnable r) {
      this.clicked.put(b, r);
      return this;
    }

    /**
     * Adds a pressed mouse lambda for a specific button
     */
    public Builder addMousePressed(int b, Runnable r) {
      this.pressed.put(b, r);
      return this;
    }

    /**
     * Adds a released mouse lambda for a specific button
     */
    public Builder addMouseReleased(int b, Runnable r) {
      this.released.put(b, r);
      return this;
    }

    /**
     * Adds a entered mouse lamba for a specific button
     */
    public Builder addMouseEntered(int b, Runnable r) {
      this.entered.put(b, r);
      return this;
    }

  }
}
