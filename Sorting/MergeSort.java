//https://leetcode.com/problems/sort-an-array/
public class MergeSort {
    /*
     * 1. Divide and conquer
     * 2. O(nlogn) O(n)
     */
    public void mergeSort(int[] arr, int l, int r) {
        if(r > l) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);   
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int[] left = new int[m - l + 1];
        int[] right = new int[r - m];

        for(int i = 0; i < left.length; i++) {
            left[i] = arr[l + i];
        }
        for(int j = 0; j < right.length; j++) {
            right[j] = arr[m + j + 1];
        }

        int i = 0, j = 0, k = l;
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while(i < left.length) {
            arr[k++] = left[i++];
        }
        while(j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        
    }
}
