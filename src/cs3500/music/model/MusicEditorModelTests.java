package cs3500.music.model;

/**
 * Created by mattmorgan on 3/17/16.
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by mattmorgan on 3/3/16.
 */
public class MusicEditorModelTests {

  @Test
  public void testGetLowestNoteInt()  {
    MusicEditorModel m = new MusicEditorModel();
    assertEquals(m.getLowestNoteInt(), 500);
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    assertEquals(m.getLowestNoteInt(), 13);
  }

  @Test
  public void testGetHighestNoteInt()  {
    MusicEditorModel m = new MusicEditorModel();
    assertEquals(m.getHighestNoteInt(), -1);
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    assertEquals(m.getHighestNoteInt(), 23);
  }

  @Test
  public void testGetLastBeatInt()  {
    MusicEditorModel m = new MusicEditorModel();
    assertEquals(m.getLastBeatInt(), 0);
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    assertEquals(m.getLastBeatInt(), 10);
  }

  @Test
  public void testGetNotes() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testPlayConsecutively() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    MusicEditorModel m2 = new MusicEditorModel();
    m2.addNote(d);
    m.playConsecutively(m2);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    notes.put(11, new HashSet<>());
    notes.get(11).add(d);
    assertEquals(m.getNotes(), notes);
    m2.addNote(g);
    notes.put(20, new HashSet<>());
    notes.get(20).add(g);
    m.playConsecutively(m2);
    assertEquals(m.getNotes(), notes);


  }

  @Test
  public void testPlaySimultaneously()  {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    MusicEditorModel m2 = new MusicEditorModel();
    m2.addNote(d);
    m2.addNote(g);
    m2.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    notes.get(0).add(d);
    notes.get(5).add(g);
    m.playSimultaneously(m2);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testRenewEdges()  {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 3);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 2 , 7, 7);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    assertEquals(m.getLowestNoteInt(), 13);
    assertEquals(m.getHighestNoteInt(), 23);
    assertEquals(m.getLastBeatInt(), 10);
    m.renewEdges(d);
    m.renewEdges(g);
    assertEquals(m.getLowestNoteInt(),13);
    assertEquals(m.getLastBeatInt(),14);
    assertEquals(m.getHighestNoteInt(),32);
  }

  @Test
  public void testAddNote() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    m.addNote(d);
    m.addNote(g);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    notes.get(0).add(d);
    notes.get(5).add(g);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testRemoveNote() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    m.addNote(d);
    m.addNote(g);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    notes.get(0).add(d);
    notes.get(5).add(g);
    assertEquals(m.getNotes(), notes);
    m.removeNote(a1);
    m.removeNote(csharp1);
    notes.get(2).clear();
    notes.get(0).remove(a1);
    assertEquals(m.getNotes(), notes);
  }

  @Test(expected = Exception.class)
  public void testRemoveNoteNotThere() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    assertEquals(m.getNotes(), notes);
    m.removeNote(d);
  }

  @org.junit.Test
  public void testDisplaySong(){
    MusicEditorModel m = new MusicEditorModel();
    assertEquals(m.displaySong(), " ");
    m.addNote(new Note(Note.Pitch.A, 1, 1, 0));
    m.addNote(new Note(Note.Pitch.Csharp, 1, 4, 2));
    m.addNote(new Note(Note.Pitch.B, 1, 5, 5));
    assertEquals(m.displaySong(), "   C#1  D1 D#1  E1  F1 F#1  G1 G#1  A1 A#1  B1\n"
      + " 0                                  X        \n"
      + " 1                                           \n"
      + " 2  X                                        \n"
      + " 3  |                                        \n"
      + " 4  |                                        \n"
      + " 5  |                                       X\n"
      + " 6                                          |\n"
      + " 7                                          |\n"
      + " 8                                          |\n"
      + " 9                                          |");
    m.addNote(new Note(Note.Pitch.D, 7, 10, 5));
    m.addNote(new Note(Note.Pitch.Asharp, 4, 1, 3));
    m.addNote(new Note(Note.Pitch.Fsharp, 1, 12, 3));
    m.addNote(new Note(Note.Pitch.C, 4, 1, 3));
    m.addNote(new Note(Note.Pitch.Dsharp, 1, 1, 3));
    m.addNote(new Note(Note.Pitch.E, 1, 8, 5));
    m.addNote(new Note(Note.Pitch.F, 1, 1, 3));
    m.addNote(new Note(Note.Pitch.G, 1, 4, 5));
    m.addNote(new Note(Note.Pitch.Gsharp, 1, 2, 3));
    assertEquals(m.displaySong(), "   C#1  D1 D#1  E1  F1 F#1  G1 G#1  A1 A#1  B1  C2 C#2  D2 D#2 "
      + " E2  F2 F#2  G2 G#2  A2 A#2  B2  C3 C#3  D3 D#3  E3  F3 F#3  G3 G#3  A3 A#3  B3  C4 C#4  "
      + "D4 D#4  E4  F4 F#4  G4 G#4  A4 A#4  B4  C5 C#5  D5 D#5  E5  F5 F#5  G5 G#5  A5 A#5  B5  "
      + "C6 C#6  D6 D#6  E6  F6 F#6  G6 G#6  A6 A#6  B6  C7 C#7  D7\n"
      + " 0                                  X                                                    "
      + " "
      + "                                                                                       "
      + "   "
      + "                                                           "
      + "                                                          \n"
      + " 1                                                                                    "
      + "      "
      + "                                                                                        "
      + "                                                           "
      + "                                                          \n"
      + " 2  X                                                                                "
      + "      "
      + "                                                                                     "
      + "      "
      + "                                                         "
      + "                                                          \n"
      + " 3  |       X       X   X       X    "
      + "                                                          "
      + "    "
      + "        "
      + "        "
      + "    "
      + "                 "
      + "        X                                       X     "
      + "                                     "
      + "                                                                      \n"
      + " 4  |                   |       |                                                        "
      + "                                                                                         "
      + "                                                                         "
      + "                                              \n"
      + " 5  |           X       |   X               X                                            "
      + "                                                                                         "
      + "                                                                           "
      + "                                           X\n"
      + " 6              |       |   |               |                                            "
      + "                                                                                          "
      + "                                                                           "
      + "                                          |\n"
      + " 7              |       |   |               |                                             "
      + "                                                                                         "
      + "                                                                            "
      + "                                         |\n"
      + " 8              |       |   |               |                                           "
      + "                                                                                          "
      + "                                                                                          "
      + "                            |\n"

      + " 9              |       |                   |                                             "
      + "                                                                                          "
      + "                                                                                          "
      + "                          |\n"

      + "10              |       |                                                                 "
      + "                                                                                 "
      + "          "

      + "                                                                                        "
      + "                           |\n"
      + "11              |       |                                                                 "
      + "                                                                                          "
      + "                                                                                          "
      + "                          |\n"
      + "12              |       |                                                                 "
      + "                                                                                          "
      + "                                                                                          "
      + "                          |\n"
      + "13                      |                                                                 "
      + "                                                                                          "
      + "                                                                                          "
      + "                          |\n"
      + "14                      |                                                                 "
      + "                                                                                          "
      + "                                                                                          "
      + "                          |");
  }

  @Test
  public void testGetNotesAtBeat()  {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashSet<Note> expected = new HashSet<Note>();
    expected.add(a1);
    expected.add(csharp1);
    assertEquals(m.getNotesAtBeat(2),expected);
    HashSet<Note> expected_b = new HashSet<Note>();
    expected_b.add(b1);
    assertEquals(m.getNotesAtBeat(10),expected_b);
    HashSet<Note> expected_nada = new HashSet<Note>();
    assertEquals(m.getNotesAtBeat(100), expected_nada);
  }

  @Test
  public void testChangeNoteDuration()  {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    m.changeNoteDuration(a1, 7);
    notes.get(0).remove(a1);
    a1.changeDuration(7);
    notes.get(0).add(a1);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testChangeNotePitch() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    m.changeNotePitch(a1, Note.Pitch.D);
    notes.get(0).remove(a1);
    a1.changePitch(Note.Pitch.D);
    notes.get(0).add(a1);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testChangeNoteOctave()  {    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    m.changeNoteOctave(a1, 7);
    notes.get(0).remove(a1);
    a1.changeOctave(7);
    notes.get(0).add(a1);
    assertEquals(m.getNotes(), notes);
  }

  @Test
  public void testChangeNoteStartBeat() {
    MusicEditorModel m = new MusicEditorModel();
    Note a1 = new Note(Note.Pitch.A, 1, 3, 0);
    Note csharp1 = new Note(Note.Pitch.Csharp, 1, 4, 2);
    Note b1 = new Note(Note.Pitch.B, 1, 5, 5);
    Note d = new Note(Note.Pitch.D, 1 , 3, 0);
    Note g = new Note(Note.Pitch.Gsharp, 1 , 3, 5);
    m.addNote(a1);
    m.addNote(csharp1);
    m.addNote(b1);
    HashMap<Integer, HashSet<Note>> notes = new HashMap<Integer, HashSet<Note>>();
    notes.put(0, new HashSet<Note>());
    notes.get(0).add(a1);
    notes.put(2, new HashSet<Note>());
    notes.get(2).add(csharp1);
    notes.put(5, new HashSet<Note>());
    notes.get(5).add(b1);
    m.changeNoteStartBeat(a1, 7);
    notes.get(0).remove(a1);
    a1.changeStartbeat(7);
    notes.put(7, new HashSet<Note>());
    notes.get(7).add(a1);
    assertEquals(m.getNotes(), notes);
  }
}

