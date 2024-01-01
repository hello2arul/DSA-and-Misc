package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree
public class NodesAtDistanceK {
 class TreeNode {
    int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    Map<TreeNode, TreeNode> parentMap = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildNeighbours(root, null);
        List<Integer> res = new ArraList<>();
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> seen = new HashSet<>();
        q.offer(target);
        seen.add(target);
        int depth = 0;
        TreeNode cur = target;

        while(!q.isEmpty()) {
            if(depth == k) {
                while(!q.isEmpty())
                    res.add(q.poll().val);
                return res;
            }
            int size = q.size();
            while(size-- > 0) {
                cur = q.poll();                
                TreeNode parentNode = parentMap.get(cur);
                validateAndAdd(parentNode, q, seen);
                validateAndAdd(cur.left, q, seen);
                validateAndAdd(cur.right, q, seen);
            }
            depth++;
        }
        return res;
    }

    private void validateAndAdd(TreeNode node, Queue<TreeNode> q, Set<TreeNode> seen) {
        if(node != null & !seen.contains(node)) {
            q.offer(node);
            seen.add(node);
        }
    }

    private void buildNeighbours(TreeNode node, TreeNode parent) {
        if(node != null) {
            parentMap.put(node, parent);
            buildNeighbours(node.left, node);
            buildNeighbours(node.right, node);
        }
    }
}
