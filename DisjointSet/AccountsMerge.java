package DisjointSet;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DisjointSet.DisjointSet;

/*
https://leetcode.com/problems/accounts-merge
similar Google question: https://leetcode.com/discuss/interview-question/6437651/google-interview-l3-question

Given a list of accounts where each element accounts[i] is a list of strings, 
where the first element accounts[i][0] is a name, and the rest of the elements are emails 
representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person 
if there is some common email to both accounts. Note that even if two accounts have the same name,
they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely 
have the same name.

After merging the accounts, return the accounts in the following format: 
the first element of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.

 

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],
["John","johnnybravo@mail.com"]]

Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], 
['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
*/
public class AccountsMerge {

    // Here, N is the number of accounts and K is the maximum length of an account.
    // Time complexity: O(NKlogNK)
    // Space complexity: O(NK)
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        Map<String, Integer> emailGroup = new HashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> account = accounts.get(i);

            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);

                if (emailGroup.containsKey(email)) {
                    ds.union(i, emailGroup.get(email));
                } else {
                    emailGroup.put(email, i);
                }
            }
        }

        Map<Integer, List<String>> rootToEmail = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for (String email : emailGroup.keySet()) {
            int root = ds.find(emailGroup.get(email));
            rootToEmail.putIfAbsent(root, new ArrayList<>());
            rootToEmail.get(root).add(email);
        }

        for (Map.Entry<Integer, List<String>> entry : rootToEmail.entrySet()) {
            String name = accounts.get(entry.getKey()).get(0);
            List<String> emails = entry.getValue();
            Collections.sort(emails);
            emails.add(0, name);
            res.add(emails);
        }

        return res;
    }
}
