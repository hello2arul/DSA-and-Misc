
public class Misc {
    public static void swap(int[] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr.length) {
            throw new RuntimeException("Invalid indexes passed");
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
