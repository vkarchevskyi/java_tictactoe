package tictactoe;

public abstract class Player {
    protected char symbol;
    protected abstract void makeMove(char[][] board);
    protected abstract String getClassName();
}
