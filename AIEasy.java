package tictactoe;

import java.util.Locale;
import java.util.Random;

public class AIEasy extends Player {
    protected final Random random;
    protected final AILevels level;

    public AIEasy(char symbol, AILevels level) {
        this.symbol = symbol;
        this.level = level;
        random = new Random();
    }

    @Override
    public String getClassName() {
        return "AI";
    }

    public void printAILevel() {
        System.out.println("Making move level \"" + this.level.name().toLowerCase(Locale.ROOT) + "\"");
    }

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
