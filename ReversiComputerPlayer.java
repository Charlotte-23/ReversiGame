import java.util.ArrayList;
import java.util.List;
/**
 * ReversiComputerPlayer
 */
public class ReversiComputerPlayer extends ReversiPlayer {

    public ReversiComputerPlayer( String name ) {
        super( name );
    }

    public String getMove( ReversiBoard board, ReversiDiskType diskType ) {
        String move = null;
        List<String> positionsList = new ArrayList<String>();
        String positionPointer = null;
        for(int x = 1; x <= board.getBoardSize(); x++){
        	for(int y = 1; y <= board.getBoardSize(); y++){
        		positionPointer = parseMove(x, y);
        		if(board.isValidPosition(positionPointer) && board.isFlipable(positionPointer, diskType)){
        			positionsList.add(positionPointer);
        		}
        	}
        }
        int listSize = positionsList.size();
        int chosenMove = (int)( Math.random() * listSize );
        move = positionsList.get(chosenMove);
        System.out.printf("%nComputer playing %s, made move at %s%n", ""+diskType, move);
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