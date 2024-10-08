package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.tree.TreeNode;

/*
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/ 
 * 1. dfs -> fails since it has a weird ordering
 */
public class VerticalTraversal {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null)
            return res;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        verticalTraversal(root, map, 0);

        for(Integer key: map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    private void verticalTraversal(TreeNode root, Map<Integer, List<Integer>> map,
    int key) {
        if(root == null)
            return;
        if(!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(root.val);

        verticalTraversal(root.left, map, key - 1);
        verticalTraversal(root.right, map, key + 1);
    }
}
