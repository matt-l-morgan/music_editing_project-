package cs3500.music.controller;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.CompositeView;
import cs3500.music.view.GuiView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Created by mattmorgan on 3/30/16.
 */
public class SwingController {
  private final Timer timer = new Timer();
  private boolean paused = false, rPressed = false, ePressed = false, editing = false,
    adding = false;
  private int currentBeat;
  private AbstractNote editNote;
  private AbstractNote addNote;
  private MusicEditorModel model;
  private GuiView view;



  /**
   * A Runnable lambda for pausing
   */
  private final Runnable pause = () -> {
    this.view.pause();
    this.paused = !this.paused;
  };

  /**
   * lambda for enacting the moveUp function from the view
   */
  private final Runnable moveUp = () -> this.view.moveUp();
  /**
   * lambda for enacting the moveDown function from the view
   */
  private final Runnable moveDown = () -> this.view.moveDown();
  /**
   * A Runnable lambda for scrolling to the right on a piece of music using the arrow key
   */
  private final Runnable moveRight = () -> this.view.moveRight();
  /**
   * lambda for enacting the moveLeft function from the view
   */
  private final Runnable moveLeft = () -> this.view.moveLeft();
  /**
   * lambda for enacting the goToStart function from the view
   */
  private final Runnable goToStart = () -> this.view.goToStart();
  /**
   * lambda for enacting the goToEnd function from the view
   */
  private final Runnable goToEnd = () -> this.view.goToEnd();

  /**
   * lambda for when the r key is pressed
   */
  private final Runnable rPress = () -> {
    this.ePressed = false;
    this.rPressed = !this.rPressed;
    if (this.rPressed) {
      this.view.removeMouseListener(this.mouseHandler);
      this.view.removeMouseListener(this.mouseHandlerEditMode);
      this.view.addMouseListener(this.mouseHandlerRemoveMode);
    } else {
      this.view.removeMouseListener(this.mouseHandlerRemoveMode);
      this.view.addMouseListener(this.mouseHandler);
    }
  };
  /**
   * A Runnable lambda for when e is pressed
   */
  private final Runnable ePress = () -> {
    this.rPressed = false;
    this.ePressed = !this.ePressed;
    if (this.ePressed) {
      this.view.removeMouseListener(this.mouseHandlerRemoveMode);
      this.view.removeMouseListener(this.mouseHandler);
      this.view.addMouseListener(this.mouseHandlerEditMode);
    } else {
      this.view.removeMouseListener(this.mouseHandlerEditMode);
      this.view.addMouseListener(this.mouseHandler);
    }
  };

  /**
   * lambda for adding a note to a peice of music
   */
  private final Runnable createNote = () -> {
    this.adding = !this.adding;
    int clickedYCoordinate = this.mouseHandler.getCurrMouseEvent().getY();
    int start = (this.mouseHandler.getCurrMouseEvent().getX() / 20) - 1;
    int playableVal = -clickedYCoordinate / 20 +
      this.model.getHighestNoteInt() + 1;
    int xPos = this.mouseHandler.getCurrMouseEvent().getX() / 20;
    if (clickedYCoordinate > 20
      && xPos <= this.model.getLastBeatInt()
      && clickedYCoordinate <= (this.model.getHighestNoteInt()
      - this.model.getLowestNoteInt() + 2) * 20)
    {
      if (this.adding) {
        this.addNote = new Note(Pitch.toPitch(playableVal % 12), playableVal / 12, 100,
          start, 100, 1);
      } else if (xPos - this.addNote.getStartbeat() > 0) {
        this.model.addNote(new Note(Pitch.toPitch(this.addNote.getPandoValue() % 12),
          this.addNote.getPandoValue() / 12,
          xPos - this.addNote.getStartbeat(),
          this.addNote.getStartbeat(),
          this.addNote.getVolume(),
          this.addNote.getInstrument()));
      }
    }
  };
  /**
   * lambda for removing a note from a piece
   */
  private final Runnable removeNote = () -> {
    int beat = (this.mouseHandlerRemoveMode.getCurrMouseEvent().getX() / 20) - 1;
    int playableVal = -this.mouseHandlerRemoveMode.getCurrMouseEvent().getY() / 20 +
      this.model.getHighestNoteInt() + 1;
    AbstractNote p = this.thisNote(beat, playableVal);
    if (p != null && this.currentBeat < this.model.getLastBeatInt()) {
      this.model.removeNote(p);
    }
  };
  /**
   * lambda for editing a note
   */
  private final Runnable editNotes = () -> {
    this.editing = !this.editing;
    int beat = (this.mouseHandlerEditMode.getCurrMouseEvent().getX() / 20) - 1;
    int playableVal = -this.mouseHandlerEditMode.getCurrMouseEvent().getY() / 20 +
      this.model.getHighestNoteInt() + 1;
    if (this.editing) {
      this.editNote = this.thisNote(beat, playableVal);
      if (this.editNote != null && this.currentBeat < this.model.getLastBeatInt()) {
        this.model.removeNote(this.editNote);
      }
    } else {
      if (this.editNote != null && this.currentBeat < this.model.getLastBeatInt()) {
        this.model.addNote(new Note(Pitch.toPitch(playableVal % 12), playableVal / 12,
          this.editNote.getDuration(),beat, this.editNote.getVolume(),
          this.editNote.getInstrument()));
      }
    }
  };

  /**
   * A keyboard handler that specifies certain keys for music editor
   */
  private final KeyboardHandler keyboardHandler = new KeyboardHandler.Builder()
    .addKeyReleased(KeyEvent.VK_SPACE, this.pause)
    .addKeyPressed(KeyEvent.VK_UP, this.moveUp)
    .addKeyPressed(KeyEvent.VK_DOWN, this.moveDown)
    .addKeyPressed(KeyEvent.VK_RIGHT, this.moveRight)
    .addKeyPressed(KeyEvent.VK_LEFT, this.moveLeft)
    .addKeyPressed(KeyEvent.VK_HOME, this.goToStart)
    .addKeyPressed(KeyEvent.VK_END, this.goToEnd)
    .addKeyPressed(KeyEvent.VK_R, this.rPress)
    .addKeyPressed(KeyEvent.VK_E, this.ePress)
    .build();
  /**
   * A Mouse handler for the mouse actions in the music editor
   */
  private final MouseHandler mouseHandler = new MouseHandler.Builder()
    .addMousePressed(MouseEvent.BUTTON1, this.createNote)
    .addMouseReleased(MouseEvent.BUTTON1, this.createNote)
    .build();
  /**
   * A Mouse handler for handling input from the mouse while editing
   */
  private final MouseHandler mouseHandlerEditMode = new MouseHandler.Builder()
    .addMousePressed(MouseEvent.BUTTON1, this.editNotes)
    .addMouseReleased(MouseEvent.BUTTON1, this.editNotes)
    .build();
  /**
   * A Mousehandler for handling input from the mouse while removing
   */
  private final MouseHandler mouseHandlerRemoveMode = new MouseHandler.Builder()
    .addMousePressed(MouseEvent.BUTTON1, this.removeNote)
    .build();



  /**
   * Updates the timer at a rate of the piece of music's tempo
   */
  private void clock() {
    this.timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!paused) {
          view.update(currentBeat++);
        }
        if (currentBeat >= model.getLastBeatInt()) {
          timer.cancel();
        }
      }
    }, 0, this.model.getTempo() / 1000);
  }

  /**
   * gets the note that was clicked on
   */
  private AbstractNote thisNote(int beat, int noteInt) {
    for (AbstractNote n : this.model.getNotesAtBeat(beat)) {
      if ((n.getStartbeat() == beat) && (n.getPandoValue() == noteInt)) {
        return n;
      }
    }
    return null;
  }



  /**
   * starts this SwingController to a model and a view
   */
  public void init(MusicEditorModel model, GuiView view) {
    this.model = model;
    this.view = view;
    this.view.addKeyListener(this.keyboardHandler);
    this.view.addMouseListener(this.mouseHandler);
    this.view.display(this.model);
    this.clock();
  }



  /**
   * factory for creating a type of view
   */
  public static final class ViewFactory {
    /**
     * Creates a new instance of a view based on string given
     */
    public static GuiView create(String view) throws IllegalArgumentException {
      switch (view) {
        case "gui":
          return new GuiViewFrame();
        case "composite":
          return new CompositeView(new GuiViewFrame(), new MidiViewImpl());
        default:
          throw new IllegalArgumentException("Not a valid type of view.");
      }
    }
  }


}
