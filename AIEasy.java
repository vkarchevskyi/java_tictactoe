package tictactoe;

import java.util.Locale;
import java.util.Random;

// Base AI class
public class AIEasy extends Player {
    protected final Random random; // Object of random class to make random moves
    protected final AILevels level; // Level of AI

    /**
     * Initialize fields and create a new instance of Random class
     * @param symbol - player's symbol
     * @param level - level of AI
     * */
    public AIEasy(char symbol, AILevels level) {
        this.symbol = symbol;
        this.level = level;
        random = new Random();
    }

    /**
     * Return class type. Subclasses have the same return value.
     * */
    @Override
    public String getClassName() {
        return "AI";
    }

    /**
     * Print AI level which depends on "level" variable
     * */
    public void printAILevel() {
        System.out.println("Making move level \"" + this.level.name().toLowerCase(Locale.ROOT) + "\"");
    }

    /**
     * Make a random move on the board
     * @param board - game board with the symbols
     * */
    @Override
    public void makeMove(char[][] board) {
        do {
            int height = random.nextInt(Board.HEIGHT);
            int width = random.nextInt(Board.WIDTH);
            if (board[height][width] == ' ') {
                printAILevel();
                board[height][width] = this.symbol;
                break;
            }
        } while (true);
    }
}
