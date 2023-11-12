
//https://leetcode.com/problems/search-suggestions-system/
class SearchSuggestions {
    class TrieNode {
        HashMap<Character, TrieNode> children;
        TreeSet<String> suggestions;
        int wordCount;
        
        TrieNode() {
            children = new HashMap<>();
            suggestions = new TreeSet<>();
            wordCount = 0;
        }        
    }
    
    TrieNode root;
    SearchSuggestions() { root = new TrieNode(); }
    
    public void insert(String s) {
        TrieNode cur = root;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            TrieNode node = cur.children.get(ch);
            if(node == null) {
                node = new TrieNode();
                cur.children.put(ch, node);
            }

            node.suggestions.add(s);
            if(node.suggestions.size() > 3) {
                node.suggestions.pollLast();
            }
            cur = node;
        }
        cur.wordCount++;
    }
    
    public List<List<String>> search(String word) {
        List<List<String>> res = new ArrayList<>();
        TrieNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = cur != null ? cur.children.get(ch) : null;
            res.add(node != null ? new ArrayList<>(node.suggestions): new ArrayList<>());
            cur = node;
        }
        return res;
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for(String product: products) {
            insert(product);
        }
        return search(searchWord);
    }
}