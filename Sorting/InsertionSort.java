

class InsertionSort {
    //O(n^2)
    //best case: already sorted O(n)
    //have a key, keep sorting a portion
    public void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = key;
        } 
    }
}