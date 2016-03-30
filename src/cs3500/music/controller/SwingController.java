package cs3500.music.controller;

import cs3500.music.model.MusicEditorModel;
import cs3500.music.view.GuiView;

import java.util.Timer;

/**
 * Created by mattmorgan on 3/30/16.
 */
public class SwingController {
  private final Timer timer = new Timer();
  private MusicEditorModel model;
  private GuiView view;
  private int currBeat;

}
