/**
 * ReversiPlayerManager
 */
public class ReversiPlayerManager {

    //define flag constant and argument index
    final static String DELIMS = "[ ,]";
    final static int INITIAL_FLAG = 0, ARGV = 1;
    final static int SOL_ARGV = 1, SINGLE_CHAR = 1;
    final static boolean DESC = true, ASC = false;
    final static int USER_NAME = 1, FAM_NAME = 2, GIVEN_NAME = 3;
    final static int STONE_TOTAL = 1, STONE_BOUND = 2, PLAYER_1 = 3, PLAYER_2 = 4;
    final static int ADV_PLAYER_1 = 2, ADV_PLAYER_2 = 3;

    public void addPlayer( String[] para ) {
        if ( !addPlayer( new ReversiHumanPlayer( para[USER_NAME], para[FAM_NAME], para[GIVEN_NAME] ) ) ) {
            System.out.println( "The player already exists." );
        }
    }

    public void addComputerPlayer( String[] para ) {
        if ( !addPlayer( new ReversiComputerPlayer( para[USER_NAME], para[FAM_NAME], para[GIVEN_NAME] ) ) ) {
            System.out.println( "The player already exists." );
        }
    }

    public void editPlayer( String[] para ) {
        if ( hasPlayer( para[USER_NAME] ) ) {
            getPlayer( para[USER_NAME] ).setFullName( para[FAM_NAME], para[GIVEN_NAME] );
        }
        else {
            System.out.println( "The player does not exist." );
        }
    }

    public void removePlayer( String[] para ) {
        //check single argument or multiple
        if ( para.length == SOL_ARGV ) {
            System.out.println( "Are you sure you want to remove all players? (y/n)" );
            String flag = keyboard.next();
            keyboard.nextLine();

            //check if flag is y or n
            while ( flag.length() != SINGLE_CHAR
                    || ( flag.charAt(0) != 'y' && flag.charAt(0) != 'n' ) ) {
                System.out.println( "Invalid input, are you sure you want to remove all playes? (y/n)" );
                flag = keyboard.next();
                keyboard.nextLine();
            }
            if ( flag.charAt(0) == 'y' ) {
                removePlayer();
            }
        }
        else if( !removePlayer( para[USER_NAME] ) ) {
            System.out.println( "The player does not exist." );
        }
    }

    public void resetStats( String[] para ) {
        if ( para.length == SOL_ARGV ) {
            System.out.println( "Are you sure you want to reset all player statistics? (y/n)" );
            String flag = keyboard.next();
            keyboard.nextLine();

            while ( flag.length() != SINGLE_CHAR
                    || ( flag.charAt(0) != 'y' && flag.charAt(0) != 'n' ) ) {
                System.out.println( "Invalid input, are you sure you want to reset all players statistics? (y/n)" );
                flag = keyboard.next();
                keyboard.nextLine();
            }
            if ( flag.charAt(0) == 'y' ) {
                resetStats();
            }
        }
        else if( !resetStats(para[USER_NAME]) ) {
            System.out.println( "The player does not exist." );
        }
    }

    public void displayerPlayer( String[] para ) {
        if ( para.length == SOL_ARGV ) {
            display();
        }
        else if( !display( para[USER_NAME] ) ) {
            System.out.println( "The player does not exist." );
        }
    }

    public void ranking( String[] para ) {
        if ( para.length == SOL_ARGV || para[ARGV].equals( "desc" ) ) {
            getRanking( DESC );
        }
        else if( para[ARGV].equals( "asc" ) ) {
            getRanking( ASC );
        }
    }

    public void startGame( String[] para ) {
        //check player validity
        if ( hasPlayer( para[PLAYER_1] )
                && hasPlayer( para[PLAYER_2] ) ) {
            // parse paramters here
            ReversiBoard board = new ReversiBoard( n, playerIdA, playerIdB );

            System.out.println();
            board.printBeginMessage();
            //main playing loop
            while ( board.getCurrentStone() > 0 ) {
                board.printNextTurn();

                //get number of stone removed
                int numberRemoval = board.getCurrentPlayer().removeStone( keyboard, board );
                //perform remove
                board.removeStone( numberRemoval );
                //switch player
                board.nextPlayer();
            }

            System.out.println( "\nGame Over" );
            //update game records after game finished
            board.printWinner();
            board.recordGame();
        }
        else {
            System.out.println( "One of the players does not exist." );
        }
    }

    // a main method for testing purpose
    public static void main( String[] args ) {
        
    }
}