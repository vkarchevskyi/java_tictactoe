package tictactoe;

class AIHard extends AIMedium {

    public AIHard(char symbol, AILevels level) {
        super(symbol, level);
    }

    /**
     * This is the minimax function. It considers all
     * the possible ways the game can go and returns
     * the value of the board
     * @param board - game board with the symbols
     * @param depth - depth of searching
     * @param isMax - variable which defines if this player a maximizer or a minimizer
    * */
    private int minimax(char[][] board, int depth, boolean isMax) {
        boolean win = Controller.winning(board, symbol);
        boolean lose = Controller.winning(board, enemySymbol);

        if (win) { // If Maximizer has won the game
            return 10; // return his/her evaluated score
        } else if (lose) { // If Minimizer has won the game
            return -10; // return his/her evaluated score
        } else if (Controller.getSpaceNumber() == 0) { // If there are no more moves and no winner
            return 0; // then it is a tie
        }

        int best;
        if (isMax) { // If this a maximizer's move
            best = -1000;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == Board.EMPTY_SYMBOL) {
                        // Make the move
                        board[i][j] = this.symbol;
                        Controller.decreaseSpaceNumbers();

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, false));

                        // Undo the move
                        board[i][j] = Board.EMPTY_SYMBOL;
                        Controller.increaseSpaceNumbers();
                    }
                }
            }
        }
        else { // If this a minimizer's move
            best = 1000;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == Board.EMPTY_SYMBOL) {
                        // Make the move
                        board[i][j] = this.enemySymbol;
                        Controller.decreaseSpaceNumbers();

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, true));

                        // Undo the move
                        board[i][j] = Board.EMPTY_SYMBOL;
                        Controller.increaseSpaceNumbers();
                    }
                }
            }
        }
        return best;
    }

    /**
     * This function will return the best possible move for the AI
     * @param board - game board with the symbols
     * */
    private Move findBestMove(char[][] board) {
        int bestVal = -1000;
        Move bestMove = null;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == Board.EMPTY_SYMBOL) {
                    // Make the move
                    board[i][j] = this.symbol;
                    Controller.decreaseSpaceNumbers();

                    // compute evaluation function for this move
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = Board.EMPTY_SYMBOL;
                    Controller.increaseSpaceNumbers();

                    // If the value of the current move is
                    // more than the best value, then update best
                    if (moveVal > bestVal) {
                        bestMove = new Move(i, j);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     * Make the best move according to minimax algorithm
     * @param board - game board with the symbols
     * */
    @Override
    public void makeMove(char[][] board) {
        Move bestMove = findBestMove(board);
        board[bestMove.row][bestMove.column] = this.symbol;
    }
}
