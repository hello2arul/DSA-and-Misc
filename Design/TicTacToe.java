package Design;

/*
https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game
TODO: https://leetcode.com/problems/valid-tic-tac-toe-state/
*/
public class TicTacToe {
    public String tictactoe(int[][] moves) {
        int n = 3;
        char[][] board = new char[n][n];

        for (int i = 0; i < moves.length; i++) {
            int x = moves[i][0];
            int y = moves[i][1];

            if (i % 2 == 0) {
                board[x][y] = 'X';
                if (isGameOver(board, x, y, 'X'))
                    return "A";
            } else {
                board[x][y] = 'O';
                if (isGameOver(board, x, y, 'O'))
                    return "B";
            }
        }
        return moves.length == n * n ? "Draw" : "Pending";
    }

    private boolean isGameOver(char[][] board, int x, int y, char player) {
        boolean isGameOver = true;

        for (int i = 0; i < board.length; i++) {
            if (board[i][y] != player)
                isGameOver = false;
        }

        if (isGameOver)
            return true;
        isGameOver = true;

        for (int j = 0; j < board.length; j++) {
            if (board[x][j] != player)
                isGameOver = false;
        }

        if (isGameOver)
            return true;

        if (x == y) {
            isGameOver = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][i] != player)
                    isGameOver = false;
            }
        }
        if (isGameOver)
            return true;

        if (y == board.length - x - 1) {
            isGameOver = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][board.length - i - 1] != player)
                    isGameOver = false;
            }
        }
        return isGameOver;
    }
}
