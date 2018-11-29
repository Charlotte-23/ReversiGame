/**
 * ReversiPlayer
 */
public abstract class ReversiPlayer {
    private int id;
    private String name;
    private int winCount;
    private int gameCount;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void printStatistic() {
        // TODO:
    }

    public void resetStatistic() {
        // TODO:
    }

    public void recordWining() {
        // TODO:
    }

    public void recordLosing() {
        // TODO:
    }

    public abstract String getMove( ReversiBoard board, ReversiDiskType diskType );
    
}