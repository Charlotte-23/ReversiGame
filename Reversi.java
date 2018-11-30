import java.util.Scanner;

public class Reversi {

    //enum command set
    enum COMMAND {
        addplayer, addcomputerplayer, removeplayer, editplayer, resetstats,
        displayplayer, rankings, startgame, exit, nosuchcommand };
    
    //This method ask the user which letter to choose and return the corresponding letter,
    //will keep asking unless typing in "X" or "O".
    public static String inputPlayerLetter() {
        char letter = 0;
        while ((letter != 'X') && (letter != 'O')) {
            System.out.println("Do you want to be X or O?");
            Scanner keyboard = new Scanner(System.in);
            letter = keyboard.next().charAt(0);
            letter = Character.toUpperCase(letter);
        }
        return Character.toString(letter);
    }

    /**
     * A command parser parses from a parameter list
     * @param para - String to be parsed
     * @return COMMAND enum
     */
    public static COMMAND parseCommand( String[] para ) {
        COMMAND command = COMMAND.nosuchcommand;
        try {
            command = COMMAND.valueOf( para[INITIAL_FLAG] );
        }
        catch (IllegalArgumentException e) {
            System.out.printf( "'%s' is not a valid command.\n", para[INITIAL_FLAG] );
        }
        return command;
    }

    public static void main( String[] args ) {

        Scanner keyboard = new Scanner( System.in );
        System.out.println( "Welcome to Reversi!" );
        
        //find players.dat
        File dataFile = new File( "players.dat" );
        //if not exists, create one
        if ( !dataFile.exists() ) {
            try {
                dataFile.createNewFile();
            } catch ( IOException e ) {
                System.err.println( e.getMessage() );
            }
        }
        //create a corresponding data-storage object to store all players.
        ReversiPlayerManager manager = new ReversiPlayerManager( dataFile );

        while ( true ) {
            System.out.print( "\n$" );
            String para[] = keyboard.nextLine().split( DELIMS );
            COMMAND command = parseCommand( para );
            
            try {
                switch ( command ) {

                    case addplayer:
                        manager.addPlayer( para );
                        break;

                    case addaiplayer:
                        manager.addComputerPlayer( para );
                        break;
    
                    case removeplayer:
                        manager.removePlayer( para );
                        break;
    
                    case editplayer:
                        manager.editPlayer( para );
                        break;
    
                    case resetstats:
                        manager.resetStats( para );
                        break;
    
                    case displayplayer:
                        manager.displayerPlayer( para );
                        break;
    
                    case rankings:
                        manager.ranking( para );
                        break;
    
                    case startgame:
                        manager.startGame( para );
                        break;

                    case help:
                        printHelp();
                        break;
    
                    case exit:
                        System.out.println();
                        keyboard.close();
                        manager.saveGame( dataFile );
                        System.exit(0);
                        break;
                        
                    case nosuchcommand:
                    default:
                        break;
                }
            }
            catch ( ArrayIndexOutOfBoundsException e ) {
                System.out.println( "Incorrect number of arguments supplied to command." );
            }

        }

    }

}