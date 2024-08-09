package Arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * asked in Uber
 * TODO: doesn't work
 */
public class RotateNonDiagonalElements {
    public static void rotateClockwiseExcludingDiagonals(int[][] matrix) {
        int n = matrix.length;
        List<Integer> offDiagonalElements = new ArrayList<>();
        
        // Extract off-diagonal elements (excluding both main and secondary diagonals)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && i + j != n - 1) { // Exclude both main and secondary diagonals
                    offDiagonalElements.add(matrix[i][j]);
                }
            }
        }

        // Rotate the list clockwise
        System.err.println(offDiagonalElements);
        Collections.rotate(offDiagonalElements, -1); // Rotate by 1 position
        System.err.println(offDiagonalElements);

        // Place the rotated elements back into the matrix
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && i + j != n - 1) { // Exclude both main and secondary diagonals
                    matrix[i][j] = offDiagonalElements.get(index++);
                }
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        rotateClockwiseExcludingDiagonals(matrix);

        System.out.println("Matrix After Rotation:");
        printMatrix(matrix);
    }
}
