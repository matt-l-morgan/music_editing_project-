package cs3500.music.tests;


import cs3500.music.controller.SwingController;
import cs3500.music.model.AbstractNote;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.*;
import org.junit.Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;

/**
 * Created by mattmorgan on 4/6/16.
 */
public class SwingControllerTest {

  MusicEditorModel m;
  Note c2note;
  Note a12note;
  Note f1note;
  Note g2note;
  SwingController c;
  GuiViewFrame gui;
  MidiViewImpl midi;
  CompositeView composite;
  KeyListener keyListener;
  MouseListener mouseListener;


  /**
   * Initialize the testing data
   */
  private void init() {
    MusicEditorModel model = new MusicEditorModel();

    c2note = new Note(Pitch.Csharp, 2, 7, 9, 3, 100);
    a12note = new Note(Pitch.A, 5, 12, 13, 1, 100);
    f1note = new Note(Pitch.Fsharp, 1, 1, 100);
    g2note = new Note(Pitch.G, 2, 3, 2);


    model.addNote(c2note);
    model.addNote(a12note);
    model.addNote(f1note);
    model.addNote(g2note);

    m = model;
    gui = new GuiViewFrame();
    midi = new MidiViewImpl();
    composite = new CompositeView(gui, midi);
    c = new SwingController();
    keyListener = c.keyboardHandler;
    mouseListener = c.mouseHandler;
    composite.addKeyListener(keyListener);
    composite.addMouseListener(mouseListener);
  }

  @Test
  public void testPause() {
    init();
    c.init(m, gui);
    /**
     * controller should not by paused in the beginning
     */
    assertEquals(c.isPaused(), false);

    // should pause on space key
    keyListener.keyPressed(
      new KeyEvent(new Component() {}, 0, 10, InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0));
    keyListener.keyReleased(
      new KeyEvent(new Component() {}, 0, 10, InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0));
    assertEquals(c.isPaused(), true);

    // should start playing again
    // should pause on space key
    keyListener.keyPressed(
      new KeyEvent(new Component() {}, 0, 10, InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0));
    keyListener.keyReleased(
      new KeyEvent(new Component() {}, 0, 10, InputEvent.BUTTON1_MASK, KeyEvent.VK_SPACE, ' ', 0));
    assertEquals(c.isPaused(), false);

  }

  /**
   * test using the up and down keys to scroll vertically
   */
  @Test
  public void testMoveUpAndDown(){
    init();
    c.init(m, gui);
    assertEquals(gui.getScroller().getVerticalScrollBar().getValue(), 0);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_DOWN, ' ', 0));
    assertEquals(gui.getScroller().getVerticalScrollBar().getValue(), 16);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_UP, ' ', 0));
    assertEquals(gui.getScroller().getVerticalScrollBar().getValue(), 0);

  }

  /**
   * test using the right and left keys to scroll horizontally
   */
  @Test
  public void testMoveRightandLeft(){
    init();
    c.init(m, gui);
    assertEquals(gui.getScroller().getHorizontalScrollBar().getValue(), 0);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_RIGHT, ' ', 0));
    assertEquals(gui.getScroller().getHorizontalScrollBar().getValue(), 16);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_LEFT, ' ', 0));
    assertEquals(gui.getScroller().getVerticalScrollBar().getValue(), 0);
  }


  /**
   * test the mouse actions of adding, removing, and editing notes
   */
  @Test
  public void testMouseAction() {
    init();
    c.init(m, gui);
    /**
     * there should not be a note at beat 1000
     */
    HashSet<AbstractNote> expected = new HashSet<AbstractNote>() {
    };
    assertEquals(m.getNotesAtBeat(1000), expected);
    AbstractNote a5note = new Note(Pitch.A, 5, 1, 0, 100, 1);

    /**
     * Add note at A5, startbeat 0
     */
    mouseListener.mousePressed(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    mouseListener.mouseReleased(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    expected.add(a5note);
    AbstractNote there = (AbstractNote) m.getNotesAtBeat(1).toArray()[0];
    System.out.print(there.getOctave());
    assert (m.getNotesAtBeat(1).toArray()[0] == there);

    /**
     * test removing the same note we put
     */
    mouseListener = c.mouseHandlerRemoveMode;
    mouseListener.mousePressed(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    mouseListener.mouseReleased(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    assertEquals(m.getNotesAtBeat(1), new HashSet<AbstractNote>());

    /**
     * adds that note back and moves it elsewhere
     */
    mouseListener = c.mouseHandler;
    mouseListener.mousePressed(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    mouseListener.mouseReleased(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));

    mouseListener = c.mouseHandlerEditMode;
    mouseListener.mousePressed(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 30, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    mouseListener.mouseReleased(new MouseEvent(new Component() {
    }, 0, 10, InputEvent.BUTTON1_MASK, 25, 50, MouseEvent.BUTTON1, false, MouseEvent.BUTTON1));
    AbstractNote new_posn = (Note) m.getNotesAtBeat(1).toArray()[0];
    /**
     * test that this note's pitch was moved to Gsharp instead of A
     */
    assertEquals(new_posn.getPitch(), Pitch.Gsharp);

  }

  /***
   * test the gotostart and gotoend Runnables
   */
  @Test
  public void testGoToStartAndEnd(){
    init();
    c.init(m, gui);
    assertEquals(gui.getScroller().getHorizontalScrollBar().getValue(), 0);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_END, ' ', 0));
    assertEquals(gui.getScroller().getHorizontalScrollBar().getValue(), 803);
    keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 10,
      InputEvent.BUTTON1_MASK, KeyEvent.VK_HOME, ' ', 0));
    assertEquals(gui.getScroller().getVerticalScrollBar().getValue(), 0);
  }




}
