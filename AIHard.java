package tictactoe;

class AIHard extends AIMedium {

    public AIHard(char symbol, AILevels level) {
        super(symbol, level);
    }

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
    private int minimax(char[][] board, int depth, boolean isMax) {
        boolean win = Controller.winning(board, symbol);
        boolean lose = Controller.winning(board, enemySymbol);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (win) {
            return 10;
        }
        else if (lose) {
            return -10;
            // If Minimizer has won the game
            // return his/her evaluated score
        } else if (Controller.getSpaceNumber() == 0) {
            return 0;
            // If there are no more moves and
            // no winner then it is a tie
        }

        // If this maximizer's move
        int best;
        if (isMax) {
            best = -1000;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == Board.EMPTY_SYMBOL) {
                        // Make the move
                        board[i][j] = this.symbol;
                        Controller.spaceNumber--;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(board,
                                depth + 1, false));

                        // Undo the move
                        board[i][j] = Board.EMPTY_SYMBOL;
                        Controller.spaceNumber++;
                    }
                }
            }
        }
        // If this minimizer's move
        else {
            best = 1000;
            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == Board.EMPTY_SYMBOL) {
                        // Make the move
                        board[i][j] = this.enemySymbol;
                        Controller.spaceNumber--;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(board,
                                depth + 1, true));

                        // Undo the move
                        board[i][j] = Board.EMPTY_SYMBOL;
                        Controller.spaceNumber++;
                    }
                }
            }
        }
        return best;
    }

    // This will return the best possible
    // move for the player
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
                    Controller.spaceNumber--;

                    // compute evaluation function for this
                    // move.
                    //boolean isMax = this.symbol == Board.O_SYMBOL;
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = Board.EMPTY_SYMBOL;
                    Controller.spaceNumber++;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove = new Move(i, j);
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
    
    @Override
    public void makeMove(char[][] board) {
        Move bestMove = findBestMove(board);
        board[bestMove.row][bestMove.column] = this.symbol;
    }
}
