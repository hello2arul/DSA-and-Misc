package BackTracking;

public class SudokuSolver {
    public boolean solveSudoku(char[][] board) {
        int[] empty = findEmpty(board);
        int row = empty[0], col = empty[1];
        if(row == -1)   return true;

        for(char i = '1'; i <= '9'; i++) {
            if(isValid(board, row, col, i)) {
                board[row][col] = i;
                if(solveSudoku(board))
                    return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char ch) {
        //assuming no of rows = no of cols
        for(int j = 0; j < board.length; j++) {
            if(j != col && board[row][j] == ch) return false;
            if(j != row && board[j][col] == ch) return false;
        }
        int boxX = (row / 3) * 3;
        int boxY = (col / 3) * 3;
        for(int i = boxX; i < boxX + 3; i++) {
            for(int j = boxY; j < boxY + 3; j++) {
                if(board[i][j] == ch) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] findEmpty(char[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == '.')
                    return new int[] {i, j};
            }
        }
        return new int[] {-1, -1};
    }
}
