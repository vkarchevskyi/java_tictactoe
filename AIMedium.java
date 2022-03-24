package tictactoe;

public class AIMedium extends AIEasy {

    protected char enemySymbol; // Symbol which has an opposite player

    /**
     * Call the AIEasy constructor to initialize inherited fields
     * and initialize enemySymbol variable
     * @param symbol - player's symbol
     * @param level - level of AI
     * */
    public AIMedium(char symbol, AILevels level) {
        super(symbol, level); // Calls AIEasy constructor
        this.enemySymbol = (this.symbol == Board.X_SYMBOL) ? Board.O_SYMBOL : Board.X_SYMBOL; // choose an opposite side symbol
    }

    /**
     * Find the best position for move
     * @param board - game board with the symbols
     * @param symbol - game symbol of one of sides
     * */
    private Move findPos(char[][] board, char symbol) {
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = symbol; // temporary change symbol
                    if (Controller.winning(board, symbol)) {
                        board[i][j] = Board.EMPTY_SYMBOL; // return to the initial symbol
                        return new Move(i, j); // if any side can win by doing this move, return this coordinate
                    }
                    board[i][j] = Board.EMPTY_SYMBOL; // return to the initial symbol
                }
            }
        }
        return null;
    }

    /**
     * Make a move.
     * If function findPos(...) return a position for a win move,
     * makeMove(...) will do it.
     * If function findPos(...) return null in this case,
     * makeMove will call findPos(...) with opposite symbol to find a move,
     * which can lead to loss.
     * In other cases this function make a random move.
     * @param board - game board with the symbols
     * */
    @Override
    public void makeMove(char[][] board) {
        // search the move to win
        Move winPos = findPos(board, this.symbol);
        if (winPos != null) {
            board[winPos.row][winPos.column] = this.symbol; // change symbol
            printAILevel();
            return;
        }

        // search the move to prevent a loss
        Move losePos = findPos(board, enemySymbol);
        if (losePos != null) {
            board[losePos.row][losePos.column] = this.symbol; // change symbol
            printAILevel();
            return;
        }

        // In other case make a random move
        super.makeMove(board);
    }
}
