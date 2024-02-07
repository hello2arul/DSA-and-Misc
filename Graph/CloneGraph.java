import java.util.HashMap;

import org.w3c.dom.Node;
/*
 * 
 * https://leetcode.com/problems/clone-graph/description/
 */
public class CloneGraph {
     HashMap<Node, Node> map = new HashMap<>();
    
    public Node cloneGraph(Node node) {
        if(node == null)    return node;
        if(!map.containsKey(node)) {
            map.put(node, new Node(node.val));
            
            for(Node neigh: node.neighbors) {
                map.get(node).neighbors.add(cloneGraph(neigh));
            }
        }
        return map.get(node);
    }
}
