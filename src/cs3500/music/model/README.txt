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
- we made an abstract class for Note called Abstractnote. This allows us to made the
IMusicEditorModel more abstract and remove our own concrete classes for the methods parameters.

- We also made a new test in MusicEditorModel called testEmptyConstructor to test different methods
for an empty piece.

-We also made a new tests file and moved all the model tests there

-We also added volume and Instrument fields to Abstract notes to integrate with midi files

- also added a tempo field to the model

HW6 Views:
- we made a console view that took advantage of the display function in our model
- we tested this view using Output stream

- we mad a gui view using swing. We had one Frame class and one panel class

- we also implemented a mdid view to play the midi file audibly
- we tested this view using mocks for the reciever and synthesizer

H7 Changes:
- we changes the midiview to implement a ticker in order to sync in up with the composite view'

- couldn't quite get the midi view working without the Composite so we commented out those tests

- added a KeyHandler and MouseHandler classes to the controller folder with builder methods

- added a GuiView interface that both GuiViewFrame and Composite view implement

- addmouseHandler and addkeyHandlers methods added to the GuiView

- added a red line that is created on the current beat of the song along with the scrolling of the
screen

- added moveUp, MoveDown, MoveLeft, MoveRight, JumpToStart, JumpToEnd functions in the Guiview

- we added a composite view that takes in a Gui and a Midiview and plays them simultaneously


- COMPOSITE FUNCTIONALITY:
to pause/unpause: press and release the spacebar
to add note: click where you want to place the note, drag how long you want the note to be
to remove a note: press r key then click the head of the note you want to remove
to edit a note's startbeat/pitch: press e key then click and hold the start of the note you wish to
change and release left click where you want to drop the current note.
to jump to start: click home key
to jump to end: click end key

