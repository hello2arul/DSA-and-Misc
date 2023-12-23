package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://practice.geeksforgeeks.org/problems/rat-in-a-maze-problem/1
public class RatInAMaze {
    public static ArrayList<String> findPath(int[][] m, int n) {
        ArrayList<String> res = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        Map<String, int[]> dirs = new HashMap<>() {{
            put("R", new int[]{0, 1});
            put("L", new int[]{0, -1});
            put("D", new int[]{1, 0});
            put("U", new int[]{-1, 0});
        }};
        dfs(m, res, visited, n, "", 0, 0, dirs);
        return res;
    }
    
    private static void dfs(int[][] m, List<String> res, boolean[][] visited, int n,
                       String cur, int i, int j, Map<String, int[]> dirs) {
        if(i == n - 1 && j == n - 1) {
            if(m[i][j] == 1) {
                res.add(cur);
            }
            return;
        }
        if(i >= n || j >= n || m[i][j] != 1 || visited[i][j])  return;
        visited[i][j] = true;
        for(String key: dirs.keySet()) {
            int[] dir = dirs.get(key);
            if(isValidDir(m, i, j, dir, n)) {
                dfs(m, res, visited, n, cur + key, i + dir[0], j + dir[1], dirs);
            }
        }
        visited[i][j] = false;
   }

    private static boolean isValidDir(int[][] m, int i, int j, int[] dir, int n) {
        return i >= 0 && j >= 0 && i < n && j < n && i + dir[0] < n && i + dir[0] >= 0 &&
               j + dir[1] >= 0 && j + dir[1] < n && m[i + dir[0]][j + dir[1]] == 1;
    }
}
