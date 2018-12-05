/**
 * ReversiComputerPlayer
 */
public class ReversiComputerPlayer extends ReversiPlayer {

    public ReversiComputerPlayer( String name ) {
        super( name );
    }

    public String getMove( ReversiBoard board, ReversiDiskType diskType ) {
        String move = null;
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
        int chosenMove = (int)( Math.random() * listSize );
        if(positionsList.length() == 0){
        	System.out.printf("%nComputer playing %s, didn't make move%n", ""+diskType);
        }else{
        	move = positionsList.substring(chosenMove, chosenMove + 2);
        	System.out.printf("%nComputer playing %s,chose to move at %s", ""+diskType, move);
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