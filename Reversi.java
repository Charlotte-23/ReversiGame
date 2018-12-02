import java.util.Scanner;

public class Reversi {

    public static void main( String[] args ) {

        Scanner keyboard = new Scanner( System.in );
        System.out.println( "Welcome to Reversi!" );

        game:
        while ( true ) {
            ReversiBoard board = new ReversiBoard( 8, new ReversiHumanPlayer( "testname1" ), new ReversiComputerPlayer( "testname2" ) );
            board.drawBoard();

            // TODO: game logic here

            System.out.print( "Play again? (y/n): " );
            String flag;
            while ( !(flag = keyboard.next()).equals( "y" ) && !flag.equals( "n" ) ) {
                keyboard.nextLine();
                System.out.print( "No a valaid answer, input again: " );
            }
            if ( flag.equals( "n" ) ) {
                break game;
            }
        }
    }
}