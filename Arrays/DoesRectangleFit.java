package Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * (0, x, y) -> create a rectangle with x * y
 * (1, x, y) -> check if the previously created rectangles fit in this, can also
 *              rotate the prior rectangles into y * x
 * TLE
 * asked in Uber
 */

class Rectangle {
    int width;
    int height;

    Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    boolean fitsIn(Rectangle other) {
        // Check both orientations
        return (this.width <= other.width && this.height <= other.height) ||
               (this.height <= other.width && this.width <= other.height);
    }

    @Override
    public String toString() {
        return width + " x " + height;
    }
}

public class DoesRectangleFit {
    private List<Rectangle> rectangles = new ArrayList<>();

    public void createRectangle(int width, int height) {
        rectangles.add(new Rectangle(width, height));
        System.out.println("Created rectangle: " + width + " x " + height);
    }

    public void checkFit(int width, int height) {
        Rectangle newRect = new Rectangle(width, height);
        boolean fits = false;

        for (Rectangle rect : rectangles) {
            if (newRect.fitsIn(rect)) {
                fits = true;
                System.out.println("Rectangle " + newRect + " fits in " + rect);
            }
        }

        if (!fits) {
            System.out.println("None of the previously created rectangles fit in the new rectangle.");
        }
    }

    public static void main(String[] args) {
        RectangleManager manager = new RectangleManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            System.out.println("0, x, y - Create a rectangle with x * y");
            System.out.println("1, x, y - Check if previously created rectangles fit in this one");
            System.out.println("2 - Exit");

            String input = scanner.nextLine();
            String[] parts = input.split(",\\s*");
            if (parts.length < 3) {
                System.out.println("Invalid command format.");
                continue;
            }

            int command;
            try {
                command = Integer.parseInt(parts[0]);
                int width = Integer.parseInt(parts[1]);
                int height = Integer.parseInt(parts[2]);

                if (command == 2) {
                    break;
                } else if (command == 0) {
                    manager.createRectangle(width, height);
                } else if (command == 1) {
                    manager.checkFit(width, height);
                } else {
                    System.out.println("Invalid command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format.");
            }
        }

        scanner.close();
    }
}
