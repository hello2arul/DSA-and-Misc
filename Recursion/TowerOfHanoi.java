//https://en.wikipedia.org/wiki/Tower_of_Hanoi

public class TowerOfHanoi {
    public void towerOfHanoi(int n, char A, char B, char C) {
        if(n == 1) {
            System.out.println("Move 1 from " + A + " to " + C);
            return;
        }

        towerOfHanoi(n - 1, A, C, B);
        System.out.println("Move " + n + " from " + A + " to " + C);
        towerOfHanoi(n - 1, B, A, C);
    }
    public static void main(String[] args) {
        new TowerOfHanoi().towerOfHanoi(3, 'A', 'B', 'C');
    }
}
