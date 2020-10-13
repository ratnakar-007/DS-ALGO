package javaDsAlgoCN.TicTacToe;

public class Board {
    private char[][] board;
    private int boardSize = 3;

    char p1Symbol;
    char p2Symbol;

    int count;

    public Board(char p1Symbol, char p2Symbol) {
        board = new char[3][3];
        for (int i = 0 ; i < boardSize ; i++) {
            for (int j = 0 ; j < boardSize ; j++) {
                board[i][j] = ' ';
            }
        }

        this.p1Symbol = p1Symbol;
        this.p2Symbol = p2Symbol;
    }
}
