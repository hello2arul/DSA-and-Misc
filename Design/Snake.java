package Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Snake - https://leetcode.ca/all/353.html
 
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class Snake {

    private int width;
    private int height;
    private int[][] food;
    private Deque<int[]> snake;
    private int score;
    private int foodIdx;

    public Snake(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        this.score = 0;
        this.foodIdx = 0;
        this.snake = new LinkedList<>();
        snake.offerFirst(new int[] {0, 0});
    }

    public int move(String direction) {
        int[] head = snake.peekFirst();
        int newRow = head[0];
        int newCol = head[1];

        switch (direction) {
            case "U":
                newRow--;        
                break;
            case "D":
                newRow++;
                break;
            case "L":
                newCol--;
                break;
            case "R":
                newCol++;
                break;
            default:
                break;
        }

        if (newRow < 0 || newCol < 0 || newRow >= height || newCol >= width)
            return -1;
        
        for (int[] segment : snake) {
            if (segment[0] == newRow && segment[1] == newCol)
                return -1;
        }

        if (foodIdx < food.length && food[foodIdx][0] == newRow && food[foodIdx][1] == newCol) {
            foodIdx++;
            score++;
        } else {
            snake.pollLast();
        }
        snake.offerFirst(new int[] {newRow, newCol});
        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                boolean isSnakeSegment = false;
                for (int[] segment : snake) {
                    if (segment[0] == row && segment[1] == col) {
                        isSnakeSegment = true;
                        break;
                    }
                }
                if (isSnakeSegment) {
                    sb.append("S "); // Represents the snake
                } else if (foodIdx < food.length && food[foodIdx][0] == row && food[foodIdx][1] == col) {
                    sb.append("F "); // Represents the food
                } else {
                    sb.append(". "); // Represents empty space
                }
            }
            sb.append("\n");
        }
        sb.append("Score: ").append(score).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] food = {{1, 2}, {0, 1}};
        Snake game = new Snake(3, 2, food);
        System.out.println(game);
        game.move("R");
        System.out.println(game);
        game.move("D");
        System.out.println(game);
        game.move("R");
        System.out.println(game);
        game.move("U");
        System.out.println(game);
        game.move("L");
        System.out.println(game);
        game.move("L"); // Game Over
        System.out.println(game);
    }
}