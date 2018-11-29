import java.util.Scanner;

public class Reversi {

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

        String playerLetter = inputPlayerLetter();
        String computerLetter = "";

        if (playerLetter.equals("X")) {
            computerLetter = "O";
        } else {
            computerLetter = "X";
        }

        drawBoard(board);

    }

}