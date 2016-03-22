package cs3500.music.tests;

import org.junit.Test;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;
import cs3500.music.view.MusicView;
import cs3500.music.view.MusicViewCreator;

import static org.junit.Assert.*;

/**
 * Created by Walker on 3/21/2016.
 */
public class MusicViewTest {
  @Test
  public void testDisplay() {
    return;
  }

  @Test
  public void testFactory() {
    assertEquals(true, MusicViewCreator.create("console") instanceof ConsoleView);
    assertEquals(true, MusicViewCreator.create("gui") instanceof GuiViewFrame);
    assertEquals(true, MusicViewCreator.create("midi") instanceof MidiViewImpl);
  }
}