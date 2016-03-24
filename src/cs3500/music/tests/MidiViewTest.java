package cs3500.music.tests;

import cs3500.music.model.IMusicEditorModel;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.MidiViewImpl;
import org.junit.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by mattmorgan on 3/23/16.
 */


/**
 * tests the midiView using mocks
 */
public class MidiViewTest {

  /**
   * tests the midiView representations of mary had a little lamb using a mock Sythesizer and
   * mock Reciever. The most receiver builds a string with a line for every note that lists it's
   * volume.
   *
   * We made an expected string using each note's volume and the stringbuild output from the
   * mock run was correct
   */
  @Test
  public void testMidiViewMaryLittleLamb(){
    MidiViewImpl.result = new StringBuilder();
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.Builder();
    try {
      MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    MidiViewImpl view = new MidiViewImpl(new StringBuilder());
    view.display(builder.build());
    StringBuilder result = MidiViewImpl.result;
    Assert.assertEquals(new StringBuilder("note volume: 72\n" + "note volume: 70\n"
        + "note volume: 72\n" + "note volume: 71\n" + "note volume: 79\n" + "note volume: 79\n"
        + "note volume: 85\n" + "note volume: 78\n" + "note volume: 74\n" + "note volume: 77\n"
        + "note volume: 75\n" + "note volume: 77\n" + "note volume: 75\n" + "note volume: 82\n"
        + "note volume: 79\n" + "note volume: 84\n" + "note volume: 75\n" + "note volume: 78\n"
        + "note volume: 73\n" + "note volume: 69\n" + "note volume: 71\n" + "note volume: 80\n"
        + "note volume: 79\n" + "note volume: 84\n" + "note volume: 76\n" + "note volume: 74\n"
        + "note volume: 77\n" + "note volume: 78\n" + "note volume: 75\n" + "note volume: 74\n"
        + "note volume: 81" + "note volume: 70\n" + "note volume: 73\n" + "note volume: 72\n")
      .toString(),
      MidiViewImpl.result.toString());
  }

  @Test
  public void testMidiViewMystery1(){
    MidiViewImpl.result = new StringBuilder();
    CompositionBuilder<IMusicEditorModel> builder = new MusicEditorModel.Builder();
    try {
      MusicReader.parseFile(new FileReader("mystery-1.txt"), builder);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    MidiViewImpl view = new MidiViewImpl(new StringBuilder());
    view.display(builder.build());
    StringBuilder result = MidiViewImpl.result;
    Assert.assertEquals(new StringBuilder("note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n" + "note volume: 64\n"
        + "note volume: 64\n")
        .toString(),
      MidiViewImpl.result.toString());
  }

}
