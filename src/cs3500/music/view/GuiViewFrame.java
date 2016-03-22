package cs3500.music.view;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;

import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements MusicView {
  private final JFrame frame = new JFrame("Music Editor");
  private final JPanel root = new JPanel(new BorderLayout());
  private final JScrollPane scroll = new JScrollPane(root);
  private MusicEditorModel model;
  static int currBeat;



  /*@Override
  public void initialize(){
    this.setVisible(true);
  }*/

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

  @Override
  public void display(IMusicEditorModel model) {

  }
}
