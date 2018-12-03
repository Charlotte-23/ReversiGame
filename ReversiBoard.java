import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ReversiBoard
 */
public class ReversiBoard {
    private static final int SIZE_MAX = 26;
    private static final int SIZE_MIN = 2;

    // Box-drawing Unicodes
    private static final char TABLE_LU = '\u250C';
    private static final char TABLE_RU = '\u2510';
    private static final char TABLE_LD = '\u2514';
    private static final char TABLE_RD = '\u2518';
    private static final char TABLE_LR = '\u251C';
    private static final char TABLE_RL = '\u2524';
    private static final char TABLE_UD = '\u252C';
    private static final char TABLE_DU = '\u2534';
    private static final char TABLE_CROSS = '\u253C';
    private static final char TABLE_V = '\u2502';
    private static final char TABLE_H = '\u2500';

    private ReversiDiskType[][] board;
    private int size;
    private ReversiPlayer currentPlayer;
    private ReversiPlayer playerA;
    private ReversiPlayer playerB;
    private ReversiPlayer winner;

    private int diskCountA;
    private int diskCountB;
    
    /**
     * the constructor that initializes a valid game board of size n
     * @param n - the size of the board
     * @param playerIdA - the id of player A
     * @param playerIdB - the id of player B
     * @throws IllegalArgumentException if the input is smaller than minimum or larger than maximum
     */
    public ReversiBoard( int n, ReversiPlayer playerA, ReversiPlayer playerB ) throws IllegalArgumentException {
        if ( n < SIZE_MIN && n > SIZE_MAX ) {
            throw new IllegalArgumentException( "invalid game board size." );
        }
        size = n;
        board = new ReversiDiskType[size+1][size+1];
        for( int i = 0; i <= size; i++ ) {
            for ( int j = 0; j <= size; j++ )
            board[i][j] = ReversiDiskType.EMPTY;
        }
        this.playerA = playerA;
        this.playerB = playerB;
        
        
       
       
        
        
        int iRandom = (int)(1+Math.random()*10);
        if(iRandom%2==1) {
        	  currentPlayer = playerA;
        }else {
        	  currentPlayer = playerB;
        }
    }

    /**
     * a method that draws the current board
     * Haoran's code was moved here
     * Board position (i, j), i is the # of row (numeric) and j is the # of column (alphabetic)
     */
    public void drawBoard() {
        System.out.print( "  " );
        for ( int i = 0; i < size; i++ ) {
            System.out.print( "   " + (char)('a' + i) );
        }
        System.out.println();
        System.out.println( "   " + TABLE_LU 
                                 + repeat( ( "" + repeat( TABLE_H, 3 ) + TABLE_UD ), size-1 ) 
                                 + repeat( TABLE_H, 3 ) + TABLE_RU );
        for ( int i = 1; i <= size; i++ ) {
            System.out.printf( "%2d %c", i, TABLE_V );
            for ( int j = 1; j <= size; j++ ) {
                System.out.print( " " + board[i][j] + " " + TABLE_V );
            }
            System.out.printf( " %d", i );
            System.out.println();
            if ( i < size ) {
                System.out.println( "   " + TABLE_LR 
                                         + repeat( ( "" + repeat( TABLE_H, 3 ) + TABLE_CROSS ), size-1 ) 
                                         + repeat( TABLE_H, 3 ) + TABLE_RL );
            } else {
                System.out.println( "   " + TABLE_LD 
                                         + repeat( ( "" + repeat( TABLE_H, 3 ) + TABLE_DU ), size-1 ) 
                                         + repeat( TABLE_H, 3 ) + TABLE_RD );
            }
        }
        System.out.print( "  " );
        for ( int i = 0; i < size; i++ ) {
            System.out.print( "   " + (char)('a' + i) );
        }
        System.out.println();
    }

    public ReversiPlayer getCurrentPlayer() {
        gotoNextPlayer();
        return currentPlayer;
    }

    private void gotoNextPlayer() {
        currentPlayer = ( currentPlayer == playerA ) ? playerB : playerA;
    }

    private boolean isBoardFull() {
        // TODO:
    	
        return false;
    }

    private void updateDiskCounts() {
        // TODO:
    }

    /**
     * to place the next disk
     * @param position
     * @param diskType
     */
    public void placeDisk( String position, ReversiDiskType diskType ) {
        //TODO: validate position first
        //      then check which player to go
        //      place the disk
        //      reverse disk if any
    }

    public boolean isOver() {
        //TODO: check if the game is finished and update the winner
        return false;
    }

    public ReversiPlayer winner() {
        return winner;
    }

    /**
     * to check if a given position is valid for placing next disk
     * @param position - the position String in the format of "a1", "A1", "1a" or "1A"
     * @return true if the position is valid
     */
    private boolean isValidPosition( String position ) {
        int x = parseX( position );
        int y = parseY( position );
        return x >= SIZE_MIN && x <= size
            && y >= SIZE_MIN && y <= size
            && board[x][y] == ReversiDiskType.EMPTY;
    }

    /**
     * a method to parse the x coordination of the disk
     * @param position - the given position String such as "a1", "1C"
     * @return the x coordination
     */
    private int parseX( String position ) {
        return Integer.parseInt( position );
    }

    /**
     * a method to parse the y coordination of the disk
     * @param position - the given position String such as "a1", "1C"
     * @return the y coordination
     */
    private int parseY( String position ) throws IllegalArgumentException {
        Pattern p = Pattern.compile( "[A-z]{1}" );
        Matcher m = p.matcher( position );
        if ( m.find() ) {
            return (int) (m.group(0).toLowerCase().charAt(0) - 'a');
        }
        throw new IllegalArgumentException();
    }

    /**
     * A hepler method to repeat a String by n times
     * @param str - the String to be repeated
     * @param  - the number of repetition
     * @return the repeated String
     */
    private String repeat( String str, int n ) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < n; i++ ) {
            sb.append( str );
        }
        return sb.toString();
    }

    /**
     * A hepler method to repeat a char by n times
     * @param chr - the char to be repeated
     * @param  - the number of repetition
     * @return the repeated String
     */
    private String repeat( char chr, int n ) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < n; i++ ) {
            sb.append( chr );
        }
        return sb.toString();
    }

}