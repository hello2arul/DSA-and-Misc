package Tree;

/*
 * can be done in bfs I guess
 * This is dfs inorder
 */
public class ConvertToDLL {
    class Node {
        Node left;
        Node right;
    }
    Node prev;

    public Node covertToDLL(Node root) {
        if(root == null) return null;
        Node head = covertToDLL(root.left);
        if(prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        covertToDLL(root.right);
        return head;
    }
}
