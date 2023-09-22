
/*
 * O(n^2)
 * uses partition naive, Lomuto, Hoarse
 */
public class QuickSort {
    private LomutoPartition lomutoPartition = new LomutoPartition();

    public void quickSort(int[] arr, int l, int h) {
        if(l < h) {
            int p = lomutoPartition.lomutoPartition(arr, l, h);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, h);
        }
    }
}
