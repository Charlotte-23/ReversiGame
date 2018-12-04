/**
 * ReversiHumanPlayer
 */
public class ReversiHumanPlayer extends ReversiPlayer {

    public ReversiHumanPlayer( String name ) {
        super( name );
    }

    public String getMove( ReversiBoard board, ReversiDiskType diskType ) {
        String validInputA = "abcdefghi";
        String validInputB = "123456789";
        // TODO:
        Scanner s = new Scanner(System.in);
        for (;;) {
            System.out.Println("where do you wanna place your disk? (a1-i9)");
            String move = s.nextLine();
            if (validInputA.indexOf(ans.charAt(0)) != -1 &&
                validInputB.indexOf(ans.charAt(1)) != -1) {
                return move.subString(0,2);
            }
        }
    } 
}
