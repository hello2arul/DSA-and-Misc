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
        List<Integer> res = new ArrayList<>(k);
        buildNeighbours(root, null);
        TreeNode cur = target;
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.offer(target);
        int level = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                cur = q.poll();
                visited.add(cur);
                if(level == k)  res.add(cur.val);
                validateAndAdd(cur, q, visited);
            }
            level++;
        }
        return res;
    }

    private void validateAndAdd(TreeNode node, Queue<TreeNode> q, Set<TreeNode> visited) {
        if(node != null) {
            q.offer(node);
            if(node.left != null && !visited.contains(node.left))   q.offer(node.left);
            if(node.right != null  && !visited.contains(node.right))  q.offer(node.right);
            TreeNode parent = parentMap.get(node);
            if(parent != null && !visited.contains(parent)) q.offer(parent);
        }
    }

    private void buildNeighbours(TreeNode cur, TreeNode parent) {
        if(cur != null) {
            parentMap.put(cur, parent);
            buildNeighbours(cur.left, cur);
            buildNeighbours(cur.right, cur);
        }
    }
}
