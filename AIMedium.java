package tictactoe;

public class AIMedium extends AIEasy {

    protected char enemySymbol;

    public AIMedium(char symbol, AILevels level) {
        super(symbol, level);
        this.enemySymbol = (this.symbol == Board.X_SYMBOL) ? Board.O_SYMBOL : Board.X_SYMBOL;
    }

    private Move findPos(char[][] board, char symbol) {
        for (int i = 0; i < Board.HEIGHT; i++) {
            for (int j = 0; j < Board.WIDTH; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = symbol;
                    if (Controller.winning(board, symbol)) {
                        return new Move(i, j);
                    } else {
                        board[i][j] = Board.EMPTY_SYMBOL;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void makeMove(char[][] board) {
        Move winPos = findPos(board, this.symbol); // search player's symbols
        if (winPos != null) {
            board[winPos.row][winPos.column] = this.symbol;
            printAILevel();
            return;
        }

        Move losePos = findPos(board, enemySymbol); // search enemy's symbols
        if (losePos != null) {
            board[losePos.row][losePos.column] = this.symbol;
            printAILevel();
            return;
        }

        super.makeMove(board);
    }
}
