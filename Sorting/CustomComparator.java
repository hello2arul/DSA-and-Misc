package Sorting;

import java.util.Arrays;
import java.util.Comparator;

class Point {
    int x;
    int y;

    Point(int i, int j) {
        this.x = i;
        this.y = j;
    }

    @Override
    public String toString() {
        return String.format("x: {%d}, y: {%d}", this.x, this.y);
    }
}

class PointComparable implements Comparable<PointComparable> {
    int x;
    int y;

    PointComparable(int i, int j) {
        this.x = i;
        this.y = j;
    }

    @Override
    public String toString() {
        return String.format("x: {%d}, y: {%d}", this.x, this.y);
    }
    @Override
    public int compareTo(PointComparable o) {
        return this.x - o.y;
    }
}

class CustomCompartor implements Comparator<Point> {
    @Override
    public int compare(Point a, Point b) {
        return a.x - b.x; // sorting based on x value
    }
}

class Main {
    public static void main(String[] args) {
        Point[] arr = {new Point(8, 1), new Point(5, 0)};
        Arrays.sort(arr, 0, 2, new CustomCompartor());
        System.out.println(Arrays.toString(arr));
        PointComparable[] arr2 = {new PointComparable(8, 1), new PointComparable(5, 0)};
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}