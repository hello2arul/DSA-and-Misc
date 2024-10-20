package Strings;

/*
 * https://leetcode.com/problems/word-search/
 */
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                } 
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int idx) {
        if(idx >= word.length())    return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[i].length || 
            word.charAt(idx) != board[i][j]) {
                return false;
        }
        char ch = board[i][j];
        board[i][j] = '.';
        boolean res = dfs(board, i + 1, j, word, idx + 1) || dfs(board, i, j + 1, word, idx + 1)
                    || dfs(board, i - 1, j, word, idx + 1) || dfs(board, i, j - 1, word, idx + 1);
        board[i][j] = ch;

        return res;
    }
}
