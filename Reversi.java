import java.util.Scanner;

public class Reversi {

    public static void printHelp() {
        // TODO:
    }

    public static void main( String[] args ) {

        Scanner keyboard = new Scanner( System.in );
        System.out.println( "Welcome to Reversi!" );
        while ( true ) {
            ReversiBoard testBoard = new ReversiBoard(12, new ReversiHumanPlayer(), new ReversiComputerPlayer() );
            testBoard.drawBoard();
        }
    }
}