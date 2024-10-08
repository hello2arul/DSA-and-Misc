package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/binary-tree-right-side-view
public class RightView {
     public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                TreeNode cur = q.poll();
                if(cur != null) {
                    if(size == 0)   res.add(cur.val);
                    if(cur.left != null)    q.offer(cur.left);
                    if(cur.right != null)   q.offer(cur.right);
                }
            }
        }
        return res;
    }

    public void rightSideViewdfs(TreeNode node, int level, List<Integer> res) {
        if(node == null)    return;
        if(level == res.size()) {
            res.add(node.val);
        }

        rightSideViewdfs(node.right, level + 1, res);
        rightSideViewdfs(node.left, level + 1, res);
    }
}
