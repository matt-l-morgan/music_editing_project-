package cs3500.music.tests;

import cs3500.music.controller.MouseHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by mattmorgan on 4/6/16.
 */
public class MouseHandlerTest {
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final ByteArrayOutputStream end = new ByteArrayOutputStream();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(out));
    System.setErr(new PrintStream(end));
  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
  }

  MouseEvent click = new MouseEvent(new Component() {
  }, 0, 10, InputEvent.BUTTON1_MASK, 0, 0, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1);

  Runnable clicked = () -> System.out.print("left");



  @Test
  public void testClick() {
    MouseHandler mh = new MouseHandler.Builder().addMouseClicked(MouseEvent.BUTTON1,
      clicked).build();
    mh.mouseClicked(click);
    assertEquals("left", out.toString());
  }

  @Test
  public void testPress() {
    MouseHandler mh = new MouseHandler.Builder().addMousePressed(MouseEvent.BUTTON1,
      clicked).build();
    mh.mousePressed(click);
    assertEquals("left", out.toString());
  }

  @Test
  public void testEnter() {
    MouseHandler mh = new MouseHandler.Builder().addMouseEntered(MouseEvent.BUTTON1, clicked).
      build();
    mh.mouseEntered(click);
    assertEquals("left", out.toString());
  }

  @Test
  public void testRelease() {
    MouseHandler mh = new MouseHandler.Builder().addMouseReleased(MouseEvent.BUTTON1,
      clicked).build();
    mh.mouseReleased(click);
    assertEquals("left", out.toString());
  }


}
