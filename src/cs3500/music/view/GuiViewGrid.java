package cs3500.music.view;

import cs3500.music.model.AbstractNote;
import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mattmorgan on 3/22/16.
 */


/**
 * class for the grid and notes in hte Swing pannel
 */
public class GuiViewGrid extends JPanel {
  private final IMusicEditorModel model;





  public GuiViewGrid(IMusicEditorModel model){
    this.model = model;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawAbstractNotes(g);
    this.paintRedLine(GuiViewFrame.beat, g);
    this.drawGrid(g);
    this.repaint();
  }

  /**
   * Draws the current beat red line
   *
   * @param currBeat the current beat of the piece of music
   * @param g graphics
   */
  private void paintRedLine(int currBeat, Graphics g) {
    if (currBeat >= this.model.getLastBeatInt() - 1) {
      currBeat++;
    }
    g.setColor(Color.RED);
    g.drawLine(currBeat * GuiViewFrame.NOTESIZE, 0, currBeat * GuiViewFrame.NOTESIZE,
      (this.model.getHighestNoteInt() - this.model.getLowestNoteInt() + 1) * GuiViewFrame.NOTESIZE);
  }


  /**
   * makes the grid for notes to go in (is put in frame)
   * @param g graphics
   */
  private void drawGrid(Graphics g) {
    g.setColor(Color.BLACK);
    for (int i = 0; i <= this.model.getHighestNoteInt() - this.model.getLowestNoteInt() + 1;
         i++) {
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      if ((this.model.getHighestNoteInt() - i + 1) % 12 == 0) {
        ((Graphics2D) g).setStroke(new BasicStroke(3));
      }
      g.drawLine(0, i * GuiViewFrame.NOTESIZE, this.model.getLastBeatInt() * GuiViewFrame.NOTESIZE,
        i * GuiViewFrame.NOTESIZE);
    }
    for (int i = 0; i <= this.model.getLastBeatInt(); i++) {
      if (i % 4 == 0 || i == this.model.getLastBeatInt()) {
        g.drawLine(i * GuiViewFrame.NOTESIZE, 0, i * GuiViewFrame.NOTESIZE,
          (this.model.getHighestNoteInt() - this.model.getLowestNoteInt() + 1)
            * GuiViewFrame.NOTESIZE);
      }
    }
  }

  /**
   * Draws all of the notes in the piece of music
   *
   * @param g graphics
   */
  private void drawAbstractNotes(Graphics g) {
    super.paintComponent(g);
    for (int i = 0; i < this.model.getLastBeatInt(); i++) {
      for (AbstractNote p : this.model.getNotesAtBeat(i)) {
        if (p.getStartbeat() == i) {
          this.drawAbstractNote(p, g);
        }

      }
    }
  }

  /**
   * draws one note
   * @param abstractNote the note to draw
   * @param g the graphics
   */
  private void drawAbstractNote(AbstractNote abstractNote, Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(abstractNote.getStartbeat() * GuiViewFrame.NOTESIZE,
      (this.model.getHighestNoteInt() - abstractNote.getPandoValue()) * GuiViewFrame.NOTESIZE,
      GuiViewFrame.NOTESIZE, GuiViewFrame.NOTESIZE);
    g.setColor(Color.YELLOW);
    g.fillRect((abstractNote.getStartbeat() + 1) * GuiViewFrame.NOTESIZE,
      (this.model.getHighestNoteInt() - abstractNote.getPandoValue()) * GuiViewFrame.NOTESIZE,
      (abstractNote.getDuration() - 1) * GuiViewFrame.NOTESIZE, GuiViewFrame.NOTESIZE);
  }
}
