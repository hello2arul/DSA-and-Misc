package Sorting;

/**
 * partition elements <= pivot on the left and the rest on the right
 */
public class LomutoPartition {
    public int lomutoPartition(int[] arr, int l, int h) {
        int pivot = arr[h];
        int i = l - 1;

        for(int j = l; j <= h - 1; j++) {
            if(arr[j] < pivot) {
                Misc.swap(arr, ++i, j);
            }
        }
        Misc.swap(arr, i + 1, h);
        return i + 1;
    }

    
}

