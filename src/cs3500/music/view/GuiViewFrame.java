package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Pitch;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {
  public static final int NOTESIZE = 20;
  static int currBeat;
  private final JFrame outerFrame = new JFrame("Music Editor");
  private final JPanel base = new JPanel(new BorderLayout());
  private final JScrollPane scroller = new JScrollPane(base);
  private IMusicEditorModel model;

  public Dimension getPreferredSize() {
    return new Dimension(100, 100);
  }

  @Override public void display(IMusicEditorModel model) {
    this.model = model;
    this.initialize();
  }

  private void initialize() {
    GuiViewGrid grid_with_notes = new GuiViewGrid(this.model);
    Box pitchesPanel = placePitches();
    Box beatsBox = placeBeats();

    this.base.add(pitchesPanel, BorderLayout.WEST);
    this.base.add(beatsBox, BorderLayout.NORTH);
    this.base.add(grid_with_notes, BorderLayout.CENTER);
    this.scroller.getHorizontalScrollBar().setUnitIncrement(16);
    this.scroller.getVerticalScrollBar().setUnitIncrement(16);
    this.outerFrame.add(scroller);

    this.outerFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.outerFrame.setPreferredSize(
      new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()), 500));
    this.outerFrame.pack();
    this.outerFrame.setVisible(true);
  }

  /**
   * makes the pitches according to the model
   *
   * @return a box with the pitches in it
   */
  private Box placePitches() {
    Box pitch_box = Box.createVerticalBox();
    for (int i = this.model.getHighestNoteInt(); i >= this.model.getLowestNoteInt(); i--) {
      JLabel label = new JLabel(Pitch.toStringFromint(i % 12) + i / 12);
      pitch_box.add(Box.createVerticalStrut(NOTESIZE / 5));
      pitch_box.add(label);
    }
    return pitch_box;
  }


  private Box placeBeats() {
    Box times = Box.createHorizontalBox();
    times.add(Box.createHorizontalStrut((NOTESIZE * 2) - 16));
    for (int i = 0; i <= this.model.getLastBeatInt(); i++) {
      if (i % 16 == 0) {
        Box container = Box.createHorizontalBox();
        container.setMinimumSize(new Dimension(NOTESIZE * 4, NOTESIZE));
        container.setMaximumSize(new Dimension(NOTESIZE * 4, NOTESIZE));
        container.setPreferredSize(new Dimension(NOTESIZE * 4, NOTESIZE));
        JLabel label = new JLabel(Integer.toString(i));
        container.add(label);
        times.add(container);
        i += 3;
      } else {
        times.add(Box.createHorizontalStrut(NOTESIZE));
      }
    }
    return times;
  }

  @Override public void moveUp() {
    this.scroller.getVerticalScrollBar().setValue(this.scroller.getVerticalScrollBar().getValue() -
      this.scroller.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void moveDown() {
    this.scroller.getVerticalScrollBar().setValue(this.scroller.getVerticalScrollBar().getValue() +
      this.scroller.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void moveRight() {
    this.scroller.getHorizontalScrollBar().setValue(this.scroller.getHorizontalScrollBar().getValue() +
      this.scroller.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void moveLeft() {
    this.scroller.getHorizontalScrollBar().setValue(this.scroller.getHorizontalScrollBar().getValue() -
      this.scroller.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void goToStart() {
    this.scroller.getHorizontalScrollBar().setValue(0);
  }

  @Override
  public void goToEnd() {
    this.scroller.getHorizontalScrollBar().setValue(this.model.getLastBeatInt() * NOTESIZE);
  }

  @Override public void pause() {
  }

  @Override
  public void addKeyListener(KeyListener k) {
    this.outerFrame.addKeyListener(k);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    this.base.addMouseListener(m);
  }

  @Override
  public void removeMouseListener(MouseListener m) {
    this.base.removeMouseListener(m);
  }


  @Override
  public void update(int currBeat) {
    GuiViewFrame.currBeat = currBeat;
    if (GuiViewFrame.currBeat * GuiViewFrame.NOTESIZE >= this.outerFrame.getBounds().getSize().getWidth()
      - GuiViewFrame.NOTESIZE) {
      this.scroller.getHorizontalScrollBar().setValue(currBeat * GuiViewFrame.currBeat);
    }
  }
}


