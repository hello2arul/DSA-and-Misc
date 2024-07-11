

import java.util.*;


/*
 You are given a complete, balanced M-Ary Tree and must support Q queries. There are 3 kinds of queries. 
 Return true or false depending on whether the query was successful.

Lock(v, id) - Lock vertex v for user - id
Unlock(v, id) - If vertex v is locked by the same id, unlock it.
Upgrade(v, id) - If v is unlocked and has at least one locked vertex in it"s subtree and 
every locked vertex in the subtree of v is locked by id, unlock them and lock v instead.
Further, here are some additional constraints

A vertex cannot be locked if it has any locked ancestors or descendants, by any ID.
When a vertex is upgraded, it"s locked descendants are automatically unlocked.
An upgrade operation is not possible if the vertex is already locked or has any locked ancestors
An unlock operation is only possible if the vertex is already locked and locked by the same id

Input: N = 7, M = 2, nodes = ["World", "Asia", "Africa", "China", "India", "SouthAfrica", "Egypt"],  
queries =  ["1 China 9", "1 India 9", "3 Asia 9", "2 India 9", "2 Asia 9"]
Output: true true true false true

Input: N = 3, M = 2, nodes = ["World", "China", "India"],  
queries =  ["3 India 1", "1 World 9"]
Output: false true
 */


public class TreeOfSpace {

    class Node {
        String name;
        boolean isLocked;
        int lockedBy;
        Node parent;
        List<Node> children;
        // instead of iterating through every descendant and see if they are locked, 
        // store lockedDescendants at every node
        // int lockedDescendants;
        List<Node> lockedDescendants;

        Node() {
            this.name = null;
            this.lockedBy = -1;
            this.children = new ArrayList<>();
            this.lockedDescendants = new ArrayList<>();
            this.isLocked = false;
            this.parent = null;
        }

        Node(String name, Node parent) {
            this();
            this.name = name;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return  this.parent != null ? this.parent.name: "null1" + ", " + this.isLocked + ": ";
        }
    }

    Node root;
    Map<String, Node> nodes;

    public TreeOfSpace() {
        this.root = new Node();
        this.nodes = new HashMap<>();
    }

    public void buildTree(String[] nodes, int m) {
        this.root = new Node(nodes[0], null);
        this.nodes.put(nodes[0], root);
        int k = nodes.length;  
        int idx = 1, i = 1;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node cur = q.poll();
            this.nodes.put(cur.name, cur);

            for (i = idx; i < Math.min(idx + m, k); i++) {
                Node child = new Node(nodes[i], cur);
                cur.children.add(child);
                q.offer(child);
            }
            idx = i;
        }
    }

    /*
     * 1. Do not lock if it's already or has any locked descendants or ancestors
     * 2. update all its parents
     */
    public boolean lock(String name, int id) {
        Node current = this.nodes.get(name);
        if (current.isLocked || !current.lockedDescendants.isEmpty()) {
            return false;
        }
        Node parent = current.parent;
        while (parent != null) {
            if (parent.isLocked)
                return false;
            parent = parent.parent;
        }
        updateParents(current, true);
        current.isLocked = true;
        current.lockedBy = id;
        return true;
    }

    /*
     * 1. Don"t unlock if it"s not locked or locked by a different id
     * 2. update all its parents
     */
    public boolean unlock(String name, int id) {
        Node current = nodes.get(name);
        System.out.println(name + ", " + id + "," + current.isLocked);
        if (!current.isLocked || current.lockedBy != id) {
            return false;
        }
        updateParents(current, false);
        current.isLocked = false;
        current.lockedBy = -1;
        return true;
    }

    /*
     * 1. Don"t upgrade if it"s locked or doesn"t have any locked descendants
     * 2. If all descendants are not locked by the same id, return false
     * 3. If any ancestors are locked, return false
     * 4. unlock all descendants and lock the current
     */
    public boolean upgradeLock(String name, int id) {
        Node current = nodes.get(name);
        if (current.isLocked || current.lockedDescendants.isEmpty()) {
            return false;
        }
        for (Node descendant: current.lockedDescendants) {
            if (descendant.lockedBy != id)
                return false;
        }
        Node parent = current.parent;
        while (parent != null) {
            if (parent.isLocked)
                return false;
            parent = parent.parent;
        } 
        lock(name, id);
        return true;
    }

    // runs in O(H)
    private void updateParents(Node current, boolean isLocked) {
        Node parent = current.parent;
        while (parent != null) {
            if (isLocked)
                parent.lockedDescendants.add(current);
            else
                parent.lockedDescendants.remove(current);
            parent = parent.parent;
        }
    }

    public static void main(String[] args) {
        int N = 7, M = 2;
        String[] nodes = {"World", "Asia", "Africa", "China", "India", "SouthAfrica", "Egypt"};  
        String[] queries = {"1 China 9", "1 India 9", "3 Asia 9", "2 India 9", "2 Asia 9"};
        //Expected output: true true true false true
        // int N = 3, M = 2; 
        // String[] nodes = {"World", "China", "India"};  
        // String[] queries =  {"3 India 1", "1 World 9"};
        //expected: Output: false true

        TreeOfSpace tree = new TreeOfSpace();
        tree.buildTree(nodes, M);

        int operation = 0;

        for (String query: queries) {
            String[] cur = query.split("\s+");
            System.out.println(Arrays.toString(cur));

             
            switch (cur[0]) {
                case "1":
                    System.out.println(tree.lock(cur[1], Integer.parseInt(cur[2])));
                    break;
                case "2":
                    System.out.println(tree.unlock(cur[1], Integer.parseInt(cur[2])));
                    break;
                case "3":
                    System.out.println(tree.upgradeLock(cur[1], Integer.parseInt(cur[2])));
                    break;
            }
            System.out.println(tree.nodes);
        }

    }
}
