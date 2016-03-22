package cs3500.music.model;

/**
 * Created by mattmorgan on 3/20/16.
 */
/**
 * enum to represent a pitch, there are 12 different pitches
 * they also have string repreresentations.
 */
public enum Pitch {
    C("C"), Csharp("C♯"), D("D"), Dsharp("D♯"), E("E"), F("F"), Fsharp("F♯"),
    G("G"), Gsharp("G♯"), A("A"), Asharp("A♯"), B("B");
    /**
     * string representation fo a pitch
     */
    private final String asString;

    /**
     * constuctor for a pitch
     *
     * @param asString string representation of a pitch
     */
    Pitch(String asString) {
      this.asString = asString;
    }


    public static String toStringFromint(int pitch) {
      switch (pitch) {
        case 0:
          return "C";
        case 1:
          return "C#";
        case 2:
          return "D";
        case 3:
          return "D#";
        case 4:
          return "E";
        case 5:
          return "F";
        case 6:
          return "F#";
        case 7:
          return "G";
        case 8:
          return "G#";
        case 9:
          return "A";
        case 10:
          return "A#";
        case 11:
          return "B";
        default:
          throw new IllegalArgumentException("Not a valid pitch.");
      }
    }

  public static Pitch toPitch(int pitch) {
    switch (pitch) {
      case 0:
        return C;
      case 1:
        return CSHARP;
      case 2:
        return D;
      case 3:
        return DSHARP;
      case 4:
        return E;
      case 5:
        return F;
      case 6:
        return FSHARP;
      case 7:
        return G;
      case 8:
        return GSHARP;
      case 9:
        return A;
      case 10:
        return ASHARP;
      case 11:
        return B;
      default:
        throw new IllegalArgumentException("Not a valid pitch.");
    }
  }
}
