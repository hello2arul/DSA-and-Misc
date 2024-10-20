package String;

/*
 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/

 Given two positive integers n and k, the binary string Sn is formed as follows:

S1 = "0"
Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
Where + denotes the concatenation operation, reverse(x) returns the reversed string x,
 and invert(x) inverts all the bits in x (0 changes to 1 and 1 changes to 0).

For example, the first four strings in the above sequence are:

S1 = "0"
S2 = "011"
S3 = "0111001"
S4 = "011100110110001"
Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
 */
public class FindKthBitInNthBinaryString {
    public char findKthBit(int n, int k) {
        StringBuilder res = new StringBuilder();
        res.append('0');

        for (int i = 1; i < n; i++) {
            StringBuilder cur = new StringBuilder(res);
            cur.append("1").append(flip(res));
            res = cur;
        }
        return res.charAt(k - 1);
    }

    private String flip(StringBuilder res) {
        StringBuilder flip = new StringBuilder();
        for (int i = res.length() - 1; i >= 0; i--) {
            flip.append(res.charAt(i) == '1' ? '0' : '1');
        }
        return flip.toString();
    }

    // O(logn)
    public char findKthBitDivideAndConquer(int n, int k) {
        if (n == 1)
            return '0';
        int len = (1 << n) - 1;
        int mid = len / 2 + 1;
        if (k == mid)
            return '1';
        if (k < mid)
            return findKthBit(n - 1, k);
        // find the bit in secondhalf and invert it
        return findKthBit(n - 1, len - k + 1) == '0' ? '1' : '0';
    }
}
