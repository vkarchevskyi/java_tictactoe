package tictactoe;

import java.util.Locale;
import java.util.Scanner;

public class Controller {

    // add enum to difficult level
    public static int spaceNumber;
    private char[][] board;
    private Player player1 = null;
    private Player player2 = null;

    public Controller() {
        initBoard();
    }

    public void initBoard() {
        board = new char[Board.WIDTH][Board.HEIGHT];
        spaceNumber = Board.WIDTH * Board.HEIGHT;
        for (int i = 0; i < Board.HEIGHT; i++)
            for (int j = 0; j < Board.WIDTH; j++) {
                board[i][j] = ' ';
            }
    }


    public void printBoard() {
        // print upper edge
        final String EDGE = "---------";
        System.out.println(EDGE);
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                int index = i * Board.WIDTH + j;
                System.out.print(index % 3 == 0 ? "| " : ""); // print left board
                System.out.print(board[i][j] + " "); // print
                System.out.print(index % 3 == 2 ? "|\n" : ""); // print right board
            }
        }
        // print bottom edge
        System.out.println(EDGE);
    }

    public static int getSpaceNumber() {
        return spaceNumber;
    }

    private boolean isGameOver() {

        boolean xWins = winning(this.board, Board.X_SYMBOL);
        boolean oWins = winning(this.board, Board.O_SYMBOL);

        // print results
        if (xWins && oWins) {
            System.out.println("Impossible");
        } else if (xWins) {
            System.out.println("X wins");
        } else if (oWins) {
            System.out.println("O wins");
        } else {
            if (spaceNumber == 0) {
                System.out.println("Draw");
            } else {
                return false;// Game not finished
            }
        }
        return true;
    }

    public static boolean winning(char[][] board, char symbol) {
        return  (board[0][0] == symbol && board[1][0] == symbol && board[2][0] == symbol) ||
                (board[0][1] == symbol && board[1][1] == symbol && board[2][1] == symbol) ||
                (board[0][2] == symbol && board[1][2] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[0][1] == symbol && board[0][2] == symbol) ||
                (board[1][0] == symbol && board[1][1] == symbol && board[1][2] == symbol) ||
                (board[2][0] == symbol && board[2][1] == symbol && board[2][2] == symbol) ||
                (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
                (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private void restartGame(Scanner scanner) {
        if ("User".equals(this.player1.getClassName()) || "User".equals(this.player2.getClassName())){
            scanner.nextLine();
        }
        System.out.println();
        this.player1 = null;
        this.player2 = null;
        initBoard();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command: ");
            String[] input = scanner.nextLine().toUpperCase(Locale.ROOT).split(" +");
            if ("EXIT".equals(input[0])) {
                break;
            } else if (input.length == 3) {
                if ("START".equals(input[0])) {
                    String playerInput1 = input[1];
                    String playerInput2 = input[2];
                    switch (playerInput1) {
                        case "EASY" -> player1 = new AIEasy(Board.X_SYMBOL, AILevels.EASY);
                        case "MEDIUM" -> player1 = new AIMedium(Board.X_SYMBOL, AILevels.MEDIUM);
                        case "HARD" -> player1 = new AIHard(Board.X_SYMBOL, AILevels.HARD);
                        case "USER" -> player1 = new User(Board.X_SYMBOL, scanner);
                    }
                    switch (playerInput2) {
                        case "EASY" -> player2 = new AIEasy(Board.O_SYMBOL, AILevels.EASY);
                        case "MEDIUM" -> player2 = new AIMedium(Board.O_SYMBOL, AILevels.MEDIUM);
                        case "HARD" -> player2 = new AIHard(Board.O_SYMBOL, AILevels.HARD);
                        case "USER" -> player2 = new User(Board.O_SYMBOL, scanner);
                    }
                }
            }
            if (player1 != null && player2 != null) {
                printBoard();
                do {
                    player1.makeMove(board);
                    spaceNumber--;
                    printBoard();
                    if (isGameOver()) {
                        break;
                    }
                    player2.makeMove(board);
                    spaceNumber--;
                    printBoard();
                } while(!isGameOver());
                restartGame(scanner);
                continue;
            }
            System.out.println("Bad parameters!");
        }
        scanner.close();
    }
}
