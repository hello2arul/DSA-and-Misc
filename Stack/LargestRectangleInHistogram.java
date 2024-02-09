
/*
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleInHistogram {
    //O(n^2)
    public int largestRectangleArea(int[] heights) {
        int res = 0;

        for(int i = 0; i < heights.length; i++) {
            int cur = heights[i];
            for(int j = i - 1; j >= 0 && heights[j] >= heights[i]; j--) {
                cur += heights[i];
            }

            for(int j = i + 1; j < heights.length && heights[j] >= heights[i]; j++) {
                cur += heights[i];
            }

            res = Math.max(res, cur);
        }
        return res;
    }

    //O(n)
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];

        //these will hold the index at which height becomes smaller than current
        left[0] = -1;
        right[len - 1] = len;

        for(int i = 1; i < len; i++) {
            int j = i - 1;
            while(j >= 0 && heights[j] >= heights[i]) {
                j = left[j];
            }
            left[i] = j;
        }

        for(int i = len - 1; i >= 0; i--) {
            int j = i + 1;
            while(j < len && heights[j] >= heights[i]) {
                j = right[j];
            }
            right[i] = j;
        }

        for(int i = 0; i < heights.length; i++) {
            res = Math.max(res, (right[i] - left[i] - 1) * heights[i]);
        }
        return res;
    }
}
