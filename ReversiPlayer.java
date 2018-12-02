/**
 * ReversiPlayer
 */
public abstract class ReversiPlayer {
    private String name;

    public ReversiPlayer( String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getMove( ReversiBoard board, ReversiDiskType diskType );
    
}