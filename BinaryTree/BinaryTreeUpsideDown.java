package Tree;



/*
 * 1. The original left child becomes the new root
 * 2. root -> right child
 * 3. right -> left child
 * 
 *    x             y
 * y     z  ->   z     x
 * 
 * 
 */
public class BinaryTreeUpsideDown {
    static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("%s\n%s   %s\n", this.val, this.left, this.right);
        }   
    }


    private Node dfs(Node node) {
        if (node == null || node.left == null)
            return node;
        Node newRoot = dfs(node.left);
        node.left.left = node.right;
        node.left.right = node;
        node.left = null;
        node.right = null;

        return newRoot;
    }



    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        System.out.println(root);
        ;
        System.out.println(new BinaryTreeUpsideDown().dfs(root));

    } 
}
