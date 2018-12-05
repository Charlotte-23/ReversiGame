import java.util.Scanner;

public class Reversi {

    // This function prints out the board that it was passed
    public static void drawBoard(String[][] board) {

        for (int j = 1; j < 9; j++) {
            System.out.print(j + "|");
            for (int i = 1; i < 8; i++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println(" " + board[8][j] + " |");
            System.out.println(" |___|___|___|___|___|___|___|___|");
        }

        System.out.println("   a   b   c   d   e   f   g   h");

    }

    //This method ask the user which letter to choose and return the corresponding letter,
    //will keep asking unless typing in "X" or "O".
    public static String inPutPlayerLetter() {
        char letter = 0;
        while ((letter != 'X') && (letter != 'O')) {
            System.out.println("Do you wan to be X or O?");
            Scanner keyboard = new Scanner(System.in);
            letter = keyboard.next().charAt(0);
            letter = Character.toUpperCase(letter);
        }
        return Character.toString(letter);
    }

    //Randomly choose the player who goes first
    public static String whoGoesFirst(){
        return X;
    }

    //fill the letter into the chosen position
    public static void makeMove(String[][] board, String letter, int[] move){

    }

    //Given a board and a player's letter, this method returns true if the letter wins.
    public static boolean isWinner(String[][] board, String letter){
        return false;
    }

    //Let the player type in their move
    public static int[] getPlayerMove(String[][] board){
        return null;
    }

    //Let the computer figure out where to move next
    public static int[] getComputerMove(String[][] board){
        return null;
    }

    //Return true if all the positions in the board is taken
    public static boolean isBoardFull(String[][] board){
        return false;
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Reversi!");

        String[][] board = new String[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = " ";
            }
        }

        board[4][4] = "O";
        board[5][5] = "O";
        board[4][5] = "X";
        board[5][4] = "X";

        String playerLetter = inPutPlayerLetter();
        String computerLetter = "";

        if (playerLetter.equals("X")) {
            computerLetter = "O";
        } else {
            computerLetter = "X";
        }

        drawBoard(board);

    }

}
