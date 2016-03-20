Matthew Morgan

For my Model of a Music Editor, the Interface IMusicEditorModel includes functions that:
- add notes
- remove notes
- alter notes
- combines 2 models consecutively
- combines 2 models simultaneously
- gets the notes at the model
- get notes at a giver beat
- getters for the highest note, lowest note, and last beat
- Prints the Console view of the the model.

in the implementation: MusicEditorModel
I used a HashMap to store the Notes of the song. the index is the beatnumber
and at each beat number with a note there is a HashSet of notes that are played at that note.
a Music Editor has the fields:
private HashMap<Integer, HashSet<Note>> notes;
   int lowestNoteInt;
   int highestNoteInt;
   int lastBeatInt;

a Note has:
- enum Pitch has values for all 12 notes in an octave
- octave is an int between 0 and 9
- Duration is an int > 0

MusicEditorModelTest tests the methods of the model


CHANGES MADE TO MODEL FOR HW6:
we made an abstract class for Note called Abstractnote. This allows us to made the IMusicEditorModel
more abstract and remove our own concrete classes for the methods parameters.



