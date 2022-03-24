package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {
    private final char symbol; //
    private final Scanner scanner; //

    /**
     * @param symbol - player's symbol
     * @param scanner - instance of Scanner class
     * */
    public User(char symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    /**
     * Return class type
     * */
    @Override
    public String getClassName() {
        return "User";
    }

    /**
     * Function makes a user input based move
     * @param board - game board with the symbols
     * */
    @Override
    public void makeMove(char[][] board) {
        do {
            Move move;
            System.out.print("Enter the coordinates: ");
            try {
                move = new Move(scanner.nextInt(), scanner.nextInt());
            } catch (InputMismatchException e) { // Exception for Character/String
                System.out.println("You should enter numbers!");
                scanner.nextLine(); // Function to get rid of "infinity exception"
                continue;
            }
            if (move.row < 1 || move.row > Board.HEIGHT || move.column < 1 || move.column > Board.WIDTH) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if (board[move.row - 1][move.column - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[move.row - 1][move.column - 1] = this.symbol;
                break;
            }
        } while (true);
    }
}
