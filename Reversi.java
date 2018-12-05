import java.util.Scanner;

public class Reversi {

    public static void main( String[] args ) {

        Scanner keyboard = new Scanner( System.in );
        System.out.println( "Welcome to Reversi!" );

        game:
        while ( true ) {
            System.out.print( "Input the size of the game board (even integer between 2-26): " );
            int size = keyboard.nextInt();
            keyboard.nextLine();
            if(size%2 == 1){
              size++;
              System.out.println("You typed in an odd integer, round up to the next even number: " + size);
            }
            System.out.print("You wanna play with your friend?(y) or with computer?(n): ");
            String modeSelect;;
            while ( !(modeSelect = keyboard.next()).equals( "y" ) && !modeSelect.equals( "n" ) ) {
                keyboard.nextLine();
                System.out.print( "No a valaid answer, input again: " );
            }
            keyboard.nextLine();
            ReversiBoard board = new ReversiBoard( 2, new ReversiHumanPlayer( "name1" ), new ReversiHumanPlayer( "name2" ) );
            if(modeSelect.equals("y")){
                System.out.print( "Enter the name of player X: " );
                String name1 = keyboard.nextLine();
                System.out.print( "Enter the name of player O: " );
                String name2 = keyboard.nextLine();
                board = new ReversiBoard( size, new ReversiHumanPlayer( name1 ), new ReversiHumanPlayer( name2 ) );
            }else{
                String name1 = "You";
                String name2 = "Computer";
                board = new ReversiBoard( size, new ReversiHumanPlayer( name1 ), new ReversiComputerPlayer( name2 ) );
            }
            System.out.println("\n==========================================================\n");
            int loopcounter = 0;
            while( !board.isOver() || loopcounter > 2 ) {
                board.drawBoard();
                if ( board.hasNextMove( board.getCurrentType() ) ) {
                    while ( !board.placeDisk( board.getCurrentPlayer().getMove(board, board.getCurrentType()),
                                          board.getCurrentType() ) ) {
                        System.out.println( "Invalid move, try again" );
                    }
                    loopcounter = 0;
                } else {
                    System.out.println( board.getCurrentPlayer().getName() + " is unable to put the next disk" );
                    board.gotoNextPlayer();
                    loopcounter++;
                }
                System.out.println("\n==========================================================\n");
            }
            board.drawBoard();
            if(board.getWinner() == null){
              System.out.println("Game is a tie!");
            }else{
              System.out.println( "Game Over! " + board.getWinner().getName() + " wins!" );
            }
            
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
