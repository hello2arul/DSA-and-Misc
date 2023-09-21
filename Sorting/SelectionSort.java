public class SelectionSort {
    //find min and swap
    //(O^2)
    public void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[i] < arr[minIdx]) {
                    minIdx = i;
                }
            }
            Misc.swap(arr, i, minIdx);
        }
    }
}
