package cs3500.music.model;

import java.util.Collection;

/**
 * Created by mattmorgan on 3/2/16.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * an interface for a music editor
 */
public interface IMusicEditorModel {


  /**
   * an add note function for the music player
   */
  public void addNote(Note note);

  /**
   * a remove note object for the music player
   */
  public void removeNote(Note note);

  /**
   * a display song fuction for the music player
   * @return a string represention of the notes for this
   * peice of music
   */
  public String displaySong();

  /**
   * mutations a note's duration
   * @param note the note to be changed
   */
  public void changeNoteDuration(Note note, int duration);

  /**
   * mutates a note's pitch
   * @param note the note to be changed
   */
  public void changeNotePitch(Note note, Note.Pitch pitch);

  /**
   * change's a note's octave
   * @param note the note to be changed
   */
  public void changeNoteOctave(Note note, int octave);

  /**
   * changes the starting beat of a note
   */
  public void changeNoteStartBeat(Note note, int startBeat);

  /**
   * gives the integer representation of the lowest note
   * @return the lowest note's pandovalue
   */
  public int getLowestNoteInt();

  /**
   * giver the integer representation of the highest value
   * @return the highest note's pandovalue
   */
  public int getHighestNoteInt();

  /**
   * getter for lowestBeat
   * @return int this.lowestNoteInt
   */
  public int getLastBeatInt();

  /**
   * getter for the notes of this model
   * @return the HashMap<Integer, HashSet<Note>>
   */
  public HashMap<Integer, HashSet<Note>> getNotes();

  /**
   * adds the given models notes to the end of this, models notes
   * @param m the given model who's notes we will play
   */
  public void playConsecutively(IMusicEditorModel m);

  /**
   * plays this piece of music at the same time as the given model's notes
   * @param m the given model who's notes we will play
   */
  public void playSimultaneously(IMusicEditorModel m);

  /**
   * Gets all the notes at a given beat
   * @param beat the beat to take notes from
   * @return a Collection of notes at that beat
   * @throws IllegalArgumentException if beat is less than 0
   */
  public Collection<Note> getNotesAtBeat(int beat);


}
