package BFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
You are given an m x n grid grid where:

'.' is an empty cell.
'#' is a wall.
'@' is the starting point.
Lowercase letters represent keys.
Uppercase letters represent locks.

https://leetcode.com/problems/shortest-path-to-get-all-keys/

Input: grid = ["@.a..","###.#","b.A.B"]
Output: 8
Explanation: Note that the goal is to obtain all the keys not to open all the locks.
TODO: fix this
 */
public class ShortestPathToGetAllKeys {
    class Index {
        int i;
        int j;
        int level;
        Set<Character> keys;

        Index(int i, int j, int level, Set<Character> keys) {
            this.i = i;
            this.j = j;
            this.level = level;
            this.keys = keys;

        }
    }

    public int shortestPathAllKeys(String[] grid) {
        Queue<Index> q = new LinkedList<>();
        int target = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length()];

        for (int i = 0; i < grid.length; i++) {
            String s = grid[i];
            for (int j = 0; j < s.length(); j++) {
                char ch = s.charAt(j);

                if (ch == '@') {
                    q.offer(new Index(i, j, 0, new HashSet<>()));
                    visited[i][j] = true;
                } else if (isKey(ch)) {
                    target++;
                }
            }
        }

        int depth = 1;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Index cur = q.poll();
                int i = cur.i, j = cur.j, level = cur.level;
                Set<Character> keys = cur.keys;

                for (int[] dir: dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    Set<Character> newKeys = new HashSet<>(keys);


                    if (isValidDir(grid, x, y, visited)) {
                        char ch = grid[x].charAt(y);

                        if (Character.isLetter(ch) && Character.isUpperCase(ch)) {
                            if (newKeys.contains(Character.toLowerCase(ch))) {
                                visited[x][y] = true;
                                q.offer(new Index(x, y, level + 1, newKeys));
                            }
                        } else {
                            visited[x][y] = true;
                            q.offer(new Index(x, y, level + 1, newKeys));
                        }
                        if (isKey(ch)) {
                            newKeys.add(ch);
                            // System.out.println(keys);
                            // System.out.println(target);
                            if (newKeys.size() == target)
                                return level;
                        }
                    }
                }
            }
            depth++;
        }
        return target == 0 ? depth : -1;
    }

    private boolean isKey(char ch) {
        return Character.isLetter(ch) && Character.isLowerCase(ch);
    }

    private boolean isValidDir(String[] grid, int i, int j, boolean[][] visited) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length() &&
               grid[i].charAt(j) != '#' && !visited[i][j];
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPathToGetAllKeys().shortestPathAllKeys(
            new String[] {"@...a",".###A","b.BCc"}));
    }
}
