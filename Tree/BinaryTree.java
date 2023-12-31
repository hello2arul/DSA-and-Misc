import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
    class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("val=%s, left=%s, right=%s", val, left, right);
        }
    }

    Node root;

    public int getNoOfNodes(Node root) {
        if(root == null)    return 0;
        return 1 + getNoOfNodes(root.left) + getNoOfNodes(root.right);
    }

    public int getMaxVal(Node root) {
        if(root == null)    return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(getMaxVal(root.left), getMaxVal(root.right)));
    }

    public int getHeight(Node root) {
        if(root == null)    return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    public List<Integer> iterativeInOrder(Node root) {
        List<Integer> inOrder = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        Node cur = root;
        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                inOrder.add(cur.val);
                cur = cur.right;   
            }
        }

        return inOrder;
    }

    public List<Integer> iterativePreOrder() {
        List<Integer> preOrder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                stack.push(cur);
                preOrder.add(cur.val);
                cur = cur.left;

            } else {
                cur = stack.pop();
                cur = cur.right;
            }
        }
        return preOrder;
    }

    public List<Integer> iterativePostOrder() {
        LinkedList<Integer> postOrder = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                stack.push(cur);
                postOrder.addFirst(cur.val);
                cur = cur.right;
            } else {
                cur = stack.pop();
                cur = cur.left;
            }
        }
        return postOrder;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        
        tree.root =  tree.new Node(0);
        tree.root.left = tree.new Node(1);
        tree.root.right = tree.new Node(2);
        System.out.println(tree.iterativeInOrder(tree.root));
        
    }
}
