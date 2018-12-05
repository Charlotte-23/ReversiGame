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
        while ( true ) {
            System.out.print("where do you wanna place your disk? (e.g. a1, 1a):");
            String move = s.nextLine().substring(0,2);
            try {
                if ( board.isValidPosition(move) ) {
                    return move;
                }
            } catch (Exception e) {
            } finally {
                System.out.print( "Not a valid move, input again: " );
            }
        }
    } 
}
