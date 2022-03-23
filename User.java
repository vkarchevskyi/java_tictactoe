package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {
    private final char symbol;
    private final Scanner scanner;

    public User(char symbol, Scanner scanner) {
        this.symbol = symbol;
        this.scanner = scanner;
    }

    @Override
    public String getClassName() {
        return "User";
    }

    @Override
    public void makeMove(char[][] board) {
        do {
            int height, width;
            System.out.print("Enter the coordinates: ");
            try {
                height = scanner.nextInt();
                width = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
                continue;
            }
            if (height < 1 || height > Board.HEIGHT || width < 1 || width > Board.WIDTH) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
            else if (board[height - 1][width - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[height - 1][width - 1] = this.symbol;
                break;
            }
        } while (true);
    }
}
