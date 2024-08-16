package Design;

/**
 * https://leetcode.com/problems/design-browser-history/description/
 * also two stack works
 */
class BrowserHistory {
    class ListNode {
        ListNode prev;
        ListNode next;
        String val;

        public ListNode(String val) {
            this.val = val;
        }
    }

    private ListNode cur;

    public BrowserHistory(String homepage) {
        cur = new ListNode(homepage);
    }
    
    public void visit(String url) {
        ListNode newNode = new ListNode(url);
        cur.next = newNode;
        newNode.prev = cur;
        cur = newNode;
    }
    
    public String back(int steps) {
        while (cur.prev != null && steps > 0) {
            cur = cur.prev;
            steps--;
        }
        return cur.val;
    }
    
    public String forward(int steps) {
        while (cur.next != null && steps > 0) {
            cur = cur.next;
            steps--;
        }
        return cur.val;
    }
}
