package cs3500.music.tests;

import cs3500.music.controller.KeyboardHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by mattmorgan on 4/6/16.
 */
public class KeyboardHandlerTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void makeStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void clearStream() {
    System.setOut(null);
    System.setErr(null);
  }

  KeyEvent moveUp = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_UP, ' ', 0);

  KeyEvent moveDown = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_DOWN, ' ', 0);

  KeyEvent moveLeft = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_LEFT, ' ', 0);

  KeyEvent moveRight = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_RIGHT, ' ', 0);

  KeyEvent home = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_HOME, ' ', 0);

  KeyEvent end = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_END, ' ', 0);

  KeyEvent r = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_R, ' ', 0);

  KeyEvent e = new KeyEvent(new Component() {}, 0, 10,
    InputEvent.BUTTON1_MASK, KeyEvent.VK_E, ' ', 0);

  private Runnable up= () -> System.out.print("up");

  private Runnable down = () -> System.out.print("down");

  private Runnable left = () -> System.out.print("left");

  private Runnable right = () -> System.out.print("right");

  private Runnable keyhome = () -> System.out.print("home");

  private Runnable keyend = () -> System.out.print("end");

  private Runnable rkey = () -> System.out.print("r");

  private Runnable ekey = () -> System.out.print("e");

  @Test
  public void testUp() {
    KeyboardHandler kb = new KeyboardHandler.Builder()
      .addKeyPressed(KeyEvent.VK_UP, up).build();
    kb.keyPressed(moveUp);
    assertEquals("up", outContent.toString());
  }

  @Test
  public void testTyped() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyTyped(KeyEvent.VK_E, ekey)
      .build();
    kb.keyTyped(e);
    assertEquals("e", outContent.toString());
  }

  @Test
  public void testDown() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_DOWN, down)
      .build();
    kb.keyPressed(moveDown);
    assertEquals("down", outContent.toString());
  }

  @Test
  public void testLeft() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_LEFT, left)
      .build();
    kb.keyPressed(moveLeft);
    assertEquals("left", outContent.toString());
  }

  @Test
  public void testRight() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_RIGHT, right)
      .build();
    kb.keyPressed(moveRight);
    assertEquals("right", outContent.toString());
  }

  @Test
  public void testHome() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_HOME, keyhome)
      .build();
    kb.keyPressed(home);
    assertEquals("home", outContent.toString());
  }

  @Test
  public void testEnd() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_END, keyend)
      .build();
    kb.keyPressed(end);
    assertEquals("end", outContent.toString());
  }

  @Test
  public void testR() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_R, rkey).build();
    kb.keyPressed(r);
    assertEquals("r", outContent.toString());
  }

  @Test
  public void testE() {
    KeyboardHandler kb = new KeyboardHandler.Builder().addKeyPressed(KeyEvent.VK_E, ekey).build();
    kb.keyPressed(e);
    assertEquals("e", outContent.toString());
  }

}
