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
    private ReversiDiskType currentType;
    private ReversiPlayer playerA;
    private ReversiPlayer playerB;

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
        if ( n < SIZE_MIN || n > SIZE_MAX ) {
            System.out.println( "Invalid size, set to default (size = 8)" );
            n = 8;
        }
        size = n;
        board = new ReversiDiskType[size+1][size+1];
        for( int i = 0; i <= size; i++ ) {
            for ( int j = 0; j <= size; j++ )
            board[i][j] = ReversiDiskType.EMPTY;
        }
        board[size/2][size/2] = ReversiDiskType.X;
        board[size/2 + 1][size/2 + 1] = ReversiDiskType.X;
        board[size/2 + 1][size/2] = ReversiDiskType.O;
        board[size/2][size/2 + 1] = ReversiDiskType.O;
        this.playerA = playerA;
        this.playerB = playerB;
        diskCountA = 2;
        diskCountB = 2;

        int iRandom = (int)( 1 + Math.random() * 10 );
        if( iRandom % 2 == 1 ) {
              currentPlayer = playerA;
              currentType = ReversiDiskType.X;
        } else {
              currentPlayer = playerB;
              currentType = ReversiDiskType.O;
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

    /**
     * A method that return a reference to the current player.
     * @return ReversiPlayer - the current ReversiPlayer reference
     */
    public ReversiPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * A method that return the size of the board
     */
    public int getBoardSize(){
    	return size;
    }

    /**
     * A method used to swtich the current player
     */
    public void gotoNextPlayer() {
        currentPlayer = ( currentPlayer == playerA ) ? playerB : playerA;
        currentType = ( currentType == ReversiDiskType.X ) ? ReversiDiskType.O : ReversiDiskType.X;
    }

    /**
     * A method to check if the board has not place for next turn
     * @return true if the board is full
     */
    private boolean isBoardFull() {
        return (diskCountA+diskCountB) == (size*size);
    }

    /**
     * update the two diskCount
     */
    private void updateDiskCounts() {
        int x=0;
        int o=0;

        for(int i=1;i<=size;i++) {
        	for(int j=1;j<=size;j++) {
        		if(board[i][j]==ReversiDiskType.X) {
        			x++;
        		}else if ( board[i][j] == ReversiDiskType.O ){
        			o++;

        		}
        	}
        }
        diskCountA=x;
        diskCountB=o;
    }

    /**
     * to place the next disk
     * @param position
     * @param diskType
     */
    public boolean placeDisk( String position, ReversiDiskType diskType ) {
        //TODO: validate position first
        //      then check which player to go
        //      place the disk
        //      reverse disk if any
    	if ( !isValidPosition(position) || !isFlipable(position, diskType) ) return false;
        flip( position, diskType );
        board[parseX(position)][parseY(position)] = diskType;
    	updateDiskCounts();
    	gotoNextPlayer();
    	return true;
    }

    /**
     * A method to check if the game is over
     * it will need to check if the board is full or one side has no disk on the board
     * and then update the winner if true
     * @return true if the game is over
     */
    public boolean isOver() {
        //check if the game is finished and update the winner
    	if(diskCountA==0||diskCountB==0||isBoardFull())
    		return true;

        return false;
    }

    public ReversiDiskType getCurrentType() {
        return currentType;
    }

    /**
     * @return the ReversiPlayer reference to the winner
     */
    public ReversiPlayer getWinner() {
      if(diskCountA == diskCountB){
        return null;
      }else if(diskCountA > diskCountB){
        return playerA;
      }else{
        return playerB;
      }
    }

    /**
     * to check if a given position is valid for placing next disk
     * @param position - the position String in the format of "a1", "A1", "1a" or "1A"
     * @return true if the position is valid
     */
    public boolean isValidPosition( String position ) {
        if ( position == null ) return false;
        int x;
        int y;
        try{
          x = parseX( position );
          y = parseY( position );
        }catch(Exception e){
          return false;
        }
        return x >= 1 && x <= size
            && y >= 1 && y <= size
            && board[x][y] == ReversiDiskType.EMPTY;
    }

    public boolean isFlipable( String position, ReversiDiskType type ) {
    	int x = parseX(position);
    	int y = parseY(position);
    	// right
    	for ( int i = 1; y+i <= size; i++ ) {
            if ( board[x][y+i] == ReversiDiskType.EMPTY ) break;
            if ( board[x][y+i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// left
    	for ( int i = 1; y-i >= 1; i++ ) {
    		if ( board[x][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x][y-i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// up
    	for ( int i = 1; x-i >= 1; i++ ) {
    		if ( board[x-i][y] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// down
    	for ( int i = 1; x+i <= size; i++ ) {
    		if ( board[x+i][y] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// left up
    	for ( int i = 1; x-i >= 1 && y-i >= 1; i++ ) {
    		if ( board[x-i][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y-i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// right up
    	for ( int i = 1; x-i >= 1 && y+i <= size; i++ ) {
    		if ( board[x-i][y+i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y+i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// left down
    	for ( int i = 1; x+i <= size && y-i >= 1; i++ ) {
    		if ( board[x+i][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y-i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	// right down
    	for ( int i = 1; x+i <= size && y+i <= size; i++ ) {
    		if ( board[x+i][y+i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y+i] == type ) {
                if ( i > 1 ) {
                    return true;
                } else {
                    break;
                }
            }
    	}
    	return false;
    }

    private void flip( String position, ReversiDiskType type ) {
    	int x = parseX(position);
    	int y = parseY(position);
    	// right
    	for ( int i = 1; y+i <= size; i++ ) {
            if ( board[x][y+i] == ReversiDiskType.EMPTY ) break;
            if ( board[x][y+i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x][y+j] = type;
                    }
                }
                break;
            }
    	}
    	// left
    	for ( int i = 1; y-i >= 1; i++ ) {
    		if ( board[x][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x][y-i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x][y-j] = type;
                    }
                }
                break;
            }
    	}
    	// up
    	for ( int i = 1; x-i >= 1; i++ ) {
    		if ( board[x-i][y] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x-j][y] = type;
                    }
                }
                break;
            }
    	}
    	// down
    	for ( int i = 1; x+i <= size; i++ ) {
    		if ( board[x+i][y] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x+j][y] = type;
                    }
                }
                break;
            }
    	}
    	// left up
    	for ( int i = 1; x-i >= 1 && y-i >= 1; i++ ) {
    		if ( board[x-i][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y-i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x-j][y-j] = type;
                    }
                }
                break;
            }
    	}
    	// right up
    	for ( int i = 1; x-i >= 1 && y+i <= size; i++ ) {
    		if ( board[x-i][y+i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x-i][y+i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x-j][y+j] = type;
                    }
                }
                break;
            }
    	}
    	// left down
    	for ( int i = 1; x+i <= size && y-i >= 1; i++ ) {
    		if ( board[x+i][y-i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y-i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x+j][y-j] = type;
                    }
                }
                break;
            }
    	}
    	// right down
    	for ( int i = 1; x+i <= size && y+i <= size; i++ ) {
    		if ( board[x+i][y+i] == ReversiDiskType.EMPTY ) break;
    		if ( board[x+i][y+i] == type ) {
                if ( i > 1 ) {
                    for ( int j = 1; j < i; j++ ) {
                        board[x+j][y+j] = type;
                    }
                }
                break;
            }
    	}
    }

    public boolean hasNextMove( ReversiDiskType type ) {
        for ( int i = 1; i <= size; i++ ) {
            for ( int j = 1; j <= size; j++ ) {
                if ( board[i][j] == ReversiDiskType.EMPTY && isFlipable(""+i+(char)(j+'a'-1), type) ) return true; 
            }
        }
        return false;
    }

    /**
     * a method to parse the x coordination of the disk
     * @param position - the given position String such as "a1", "1C"
     * @return the x coordination
     */
    private int parseX( String position ) throws IllegalArgumentException {
      Pattern p = Pattern.compile( "\\d{1,}" );
      Matcher m = p.matcher( position );
      if ( m.find() ) {
          return Integer.parseInt(m.group(0));
      }
      throw new IllegalArgumentException();
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
            return (int) (m.group(0).toLowerCase().charAt(0) - 'a') + 1;
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
    // public static void main(String[] args ) {
    //   ReversiBoard test = new ReversiBoard( 8, new ReversiHumanPlayer( "aaaa" ), new ReversiHumanPlayer( "1111" ) );
    //   System.out.println(test.parseX( "a1" ));
    //   System.out.println(test.parseY( "a1" ));
    // }

}
