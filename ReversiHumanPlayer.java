import java.util.Scanner;

/**
 * ReversiHumanPlayer
 */
public class ReversiHumanPlayer extends ReversiPlayer {

    public ReversiHumanPlayer( String name ) {
        super( name );
    }

    public String getMove( ReversiBoard board, ReversiDiskType diskType ) {
        Scanner s = new Scanner(System.in);
        System.out.println( board.getCurrentPlayer().getName() + ", playing " + board.getCurrentType() );
        System.out.print("Where do you wanna place your disk? (e.g. a1, 1a):");
        String move = s.nextLine().substring(0,2);
        return move;
    }
}
