/**
 * ReversiComputerPlayer
 */
public class ReversiComputerPlayer extends ReversiPlayer {

    public ReversiComputerPlayer( String name ) {
        super( name );
    }

    public String getMove( ReversiBoard board, ReversiDiskType diskType ) {
        String move = null;
        // TODO:
        StringBuilder positionsList = new StringBuilder();
        String positionPointer = null;
        for(int x = 1; x <= board.getBoardSize(); x++){
        	for(int y = 1; y <= board.getBoardSize(); y++){
        		positionPointer = parseMove(x, y);
        		if(board.isValidPosition(positionPointer) && board.isFlipable(positionPointer, diskType)){
        			positionsList.append(positionPointer);
        		}
        	}
        }
        int listSize = positionsList.length() / 2;
        int chosenMove = (int)( 1 + Math.random() * listSize );
        if(positionsList.length() == 0){
        	System.out.println("\nComputer didn't make move");
        }else{
        	move = positionsList.substring(0, 2);
        	System.out.println("\nComputer chose to move at " + move);
        }
        return move;
    }

    private String parseMove(int x,int y){
    	StringBuilder output = new StringBuilder();
    	char xPos = (char) ('a' + x - 1);
    	output.append(xPos);
    	output.append(y);
    	return output.toString();
    }
    
}