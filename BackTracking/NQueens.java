package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/n-queens/description/
public class NQueens {
    List<List<String>> res;
    
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] row: board)   Arrays.fill(row, '.');
        solve(board, 0, 0, n);
        return res;        
    }
    
    private boolean solve(char[][] board, int row, int col, int n) {
        if(n == 0) {
            copyToList(board);
            return true;
        }
        for(int j = col; j < board[0].length; j++) {
            if(isValid(board, row, j)) {
                board[row][j] = 'Q';
                solve(board, row + 1, col, n - 1);
                board[row][j] = '.';
            }
        }
        return false;
    }   
    
    private boolean isValid(char[][] board, int row, int col) {
        for(int i = 0; i < row; i++) {
            if(board[i][col] == 'Q')    return false;
            for(int j = 0; j < board[i].length; j++) {
                //check diagonals
                if(Math.abs(i - row) == Math.abs(j - col) && board[i][j] == 'Q')
                    return  false;
            }            
        }
        return true;
    }
    
    private void copyToList(char[][] board) {
        List<String> subRes = new ArrayList<>();
        for(char[] row: board)  subRes.add(String.valueOf(row));
        res.add(subRes);
    }
}
