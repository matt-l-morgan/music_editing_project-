package cs3500.music.model;

/**
 * Created by mattmorgan on 3/2/16.
 */


import cs3500.music.util.CompositionBuilder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of the IMUSICEDITOR
 */
public class MusicEditorModel implements IMusicEditorModel {
  /**
   * an Hashmap of the notes where the key is
   * the beat and the set is the notes at that beat
   */
  private HashMap<Integer, HashSet<AbstractNote>> notes = new HashMap<>();
  private int lowestNoteInt = 131;
  private int highestNoteInt;
  private int lastBeatInt;
  private int tempo;

  /**
   * zero argument constructor of a MusicEditorModel
   */
  public MusicEditorModel(){
    this.tempo = 5000;
  }

  /**
   * 2 argument constructor of a MusicEditorModel, used in the builder
   */
  private MusicEditorModel(ArrayList<AbstractNote> notes, int tempo){
    notes.forEach(this::addNote);
    this.tempo = tempo;
  }


  /**
   * give the tempo of this piece of music
   *
   * @return the int tempo of this music
   */
  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  /**
   * getter for lowestnote
   * @return int this.lowestNoteInt
   */
  @Override
  public int getLowestNoteInt() {
    return this.lowestNoteInt;
  }

  /**
   * getter for lowestnote
   * @return int this.highestnote
   */
  @Override
  public int getHighestNoteInt() {
    return this.highestNoteInt;
  }

  /**
   * getter for the last beat of this model
   * @return this.lastbeatInt
   */
  @Override
  public int getLastBeatInt() {
    return this.lastBeatInt;
  }

  /**
   * getter for the notes of this model
   * @return the HashMap<Integer, HashSet<Note>>
   */
  @Override
  public HashMap<Integer, HashSet<AbstractNote>> getNotes(){
    return this.notes;
  }

  /**
   * adds the given models notes to the end of this, models notes
   * @param m the given model who's notes we will play
   */
  @Override
  public void playConsecutively(IMusicEditorModel m) {
    int endingbeat = this.getLastBeatInt() + 1;
    HashSet<AbstractNote> song2Notes = new HashSet<AbstractNote>();
    for (int i = 0; i < m.getLastBeatInt(); i++){
      Collection<AbstractNote> note_at_beat = m.getNotesAtBeat(i);
      song2Notes.addAll(note_at_beat);
    }
    Iterator song2it = song2Notes.iterator();

    while (song2it.hasNext()){
      Note note = (Note)song2it.next();
      note.changeStartbeat(endingbeat + note.getStartbeat());
      this.addNote(note);
    }
  }

  /**
   * plays this piece of music at the same time as the given model's notes
   * @param m the given model who's notes we will play
   */
  @Override
  public void playSimultaneously(IMusicEditorModel m) {
    for (int i = 0; i < m.getLastBeatInt(); i++) {
      m.getNotesAtBeat(i).forEach(this::addNote);
    }
  }

  /**
   * updates the highest, lowest, and last note when adding a new
   * note
   * @param note the note to be compared
   */
  public void renewEdges(AbstractNote note){
    int noteIndex = note.getPandoValue();
    if (noteIndex < this.getLowestNoteInt()) {
      this.lowestNoteInt = noteIndex;
    }
    if (noteIndex > this.getHighestNoteInt()) {
      this.highestNoteInt = noteIndex;
    }
    if (note.getDuration() + note.getStartbeat() > this.getLastBeatInt()) {
      this.lastBeatInt = note.getDuration() + note.getStartbeat();
    }
  }

  /**
   * adds a note to this.notes
   * @param note the note to be added
   */
  @Override
  public void addNote(AbstractNote note) {
    if (!this.notes.containsKey(note.getStartbeat())) {
      this.notes.put(note.getStartbeat(), new HashSet<AbstractNote>());
    }
    this.notes.get(note.getStartbeat()).add(note);
    this.renewEdges(note);
  }

  /**
   * removes a note from this.notes
   * @param note the note to be removed
   * @throws IllegalArgumentException if the note is not in this.notes
   */
  @Override
  public void removeNote(AbstractNote note) throws IllegalArgumentException{
    if (this.notes.containsKey(note.getStartbeat()) &&
      this.notes.get(note.getStartbeat()).contains(note)){

      this.lastBeatInt = 0;
      this.highestNoteInt = -1;
      this.lowestNoteInt = 10000;
      this.notes.get(note.getStartbeat()).remove(note);

      for (HashSet<AbstractNote> this_set : this.notes.values()) {
        this_set.forEach(this::renewEdges);
      }
    }
    else {
      throw new IllegalArgumentException("no such note!");
    }
  }

  /**
   * method that displays the model Via string
   * @return the string representation of the model
   * Helper is create row
   */
  @Override
  public String displaySong() {
    int space = Integer.toString(this.getLastBeatInt()).length();
    StringBuilder result = new StringBuilder(String.format("%" + space + "s", new Object[]{""}));

    int i;
    for(i = this.getLowestNoteInt(); i <= this.getHighestNoteInt(); ++i) {
      result.append(String.format
        ("%4s", new Object[]{Pitch.toStringFromint(i % 12) + i / 12}));
    }

    for(i = 0; i < this.getLastBeatInt(); ++i) {
      result.append(String.format("\n%" + space + "d ",
        new Object[]{Integer.valueOf(i)})).append(this.createRow(i));
    }
    return result.toString();
  }



  /**
   * creates a row to add to the consol display
   * HELPER for DisplaySong
   * @param beat the beat to make a row up
   * @return a string for that row
   */
  private String createRow(int beat) {
    Collection<AbstractNote> notesAtBeat = this.getNotesAtBeat(beat);
    int buffer = (4 * (this.getHighestNoteInt() - this.getLowestNoteInt())) + 2;
    StringBuilder finished = new StringBuilder(String.format("%" + buffer + "s", ""));

    for (AbstractNote a : notesAtBeat) {
      Note n = (Note) a;
      int index = (4 * (n.getPandoValue() - this.getLowestNoteInt())) + 1;
      if (n.getStartbeat() == beat) {
        finished.setCharAt(index, 'X');
      } else if (beat > n.getStartbeat() && beat < n.getStartbeat() + n.getDuration()  &&
        finished.charAt(index) != 88) {
        finished.setCharAt(index, '|');
      }
    }

    return finished.toString();
  }

  /**
   * Gets all the notes at a given beat
   * @param beat the beat to take notes from
   * @return a Collection of notes at that beat
   * @throws IllegalArgumentException if beat is less than 0
   */
  public Collection<AbstractNote> getNotesAtBeat(int beat) throws IllegalArgumentException {
    if(beat < 0) {
      throw new IllegalArgumentException("Not a valid beat.");
    } else {
      HashSet<AbstractNote> finished = new HashSet<AbstractNote>();

      for (HashSet<AbstractNote> set : this.notes.values()) {
        HashSet<AbstractNote> new_set = set.stream()
          .filter(n -> (n.getStartbeat() <= beat && n.getStartbeat() + n.getDuration() >= beat)).
            collect(Collectors.toCollection(HashSet<AbstractNote>::new));
        finished.addAll(new_set);
      }
      return Collections.unmodifiableSet(finished);
    }
  }

  /**
   * alters the given beat by changing it's duratoin
   * @param note note to be changed
   * @param duration and int for the new duration
   * @throws IllegalArgumentException if the given note is not in this.notes
   * or the duration is invalid
   */
  @Override
  public void changeNoteDuration(AbstractNote note, int duration) {
    if(this.notes.containsKey(note.getStartbeat()) &&
      this.notes.get(note.getStartbeat()).contains(note) &&
      duration > 0){
      this.notes.get(note.getStartbeat()).remove(note);
      note.changeDuration(duration);
      this.notes.get(note.getStartbeat()).add(note);
      this.renewEdges(note);
    }
    else {
      throw new IllegalArgumentException("no such note, or invalid duration");
    }
  }

  /**
   * alters the given beat by changing it's pitch
   * @param note note to be changed
   * @param pitch the new pitch for the given note
   * @throws IllegalArgumentException if the given note is not in this.notes
   */
  @Override
  public void changeNotePitch(AbstractNote note, Pitch pitch) {
    if(this.notes.containsKey(note.getStartbeat()) &&
      this.notes.get(note.getStartbeat()).contains(note)) {
      this.notes.get(note.getStartbeat()).remove(note);
      note.changePitch(pitch);
      this.notes.get(note.getStartbeat()).add(note);
      this.renewEdges(note);
    }
    else {
      throw new IllegalArgumentException("no such note");
    }
  }

  /**
   * alters the given beat by changing it's octave
   * @param note note to be changed
   * @param octave an int for the new octave
   * @throws IllegalArgumentException if the given note is not in this.notes
   * or the octave is invalid
   */
  @Override
  public void changeNoteOctave(AbstractNote note, int octave) {
    if(this.notes.containsKey(note.getStartbeat()) &&
      this.notes.get(note.getStartbeat()).contains(note) &&
      octave >= 0 && octave < 10) {
      this.notes.get(note.getStartbeat()).remove(note);
      note.changeOctave(octave);
      this.notes.get(note.getStartbeat()).add(note);
      this.renewEdges(note);
    }
    else {
      throw new IllegalArgumentException("invalid input");
    }
  }

  /**
   * alters the given beat by changing it's starting note
   * @param note note to be changed
   * @param startBeat an int for the new startingbeat
   * @throws IllegalArgumentException if the given note is not in this.notes
   * or the given int is <0
   */
  @Override public void changeNoteStartBeat(AbstractNote note, int startBeat) {
    if(this.notes.containsKey(note.getStartbeat()) &&
      this.notes.get(note.getStartbeat()).contains(note) &&
      startBeat >= 0) {
      this.notes.get(note.getStartbeat()).remove(note);
      note.changeStartbeat(startBeat);
      if (this.notes.containsKey(note.getStartbeat())) {
        this.notes.get(note.getStartbeat()).add(note);
      } else {
        this.notes.put(note.getStartbeat(), new HashSet<AbstractNote>());
        this.notes.get(note.getStartbeat()).add(note);
      }
      this.renewEdges(note);
    }
    else {
      throw new IllegalArgumentException("invalid input");
    }
  }

  public static final class Builder implements CompositionBuilder<IMusicEditorModel>{
    private ArrayList<AbstractNote> notes = new ArrayList<AbstractNote>();
    private int tempo;

    @Override public IMusicEditorModel build() {
      return new MusicEditorModel(notes,tempo);
    }

    @Override public CompositionBuilder<IMusicEditorModel> setTempo(int tempo) {
      if (tempo < 1) {
        throw new IllegalArgumentException("integer not a valid tempo");
      }
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<IMusicEditorModel> addNote(int start, int end, int instrument,
      int pitch, int volume) {
      if (end <= start || pitch > 127 || instrument < 0 || instrument > 127) {
        throw new IllegalArgumentException("Invalid note.");
      }
      this.notes.add(new Note(Pitch.toPitch(pitch % 12), (pitch / 12), (end - start), start,
        volume, instrument));
      return this;
    }
  }
}

