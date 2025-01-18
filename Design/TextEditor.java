package Design;

import java.util.Stack;

/**
 * https://leetcode.com/problems/design-a-text-editor
 * TextEditor() Initializes the object with empty text.
 * void addText(string text) Appends text to where the cursor is. The cursor
 * ends to the right of text.
 * int deleteText(int k) Deletes k characters to the left of the cursor. Returns
 * the number of characters actually deleted.
 * string cursorLeft(int k) Moves the cursor to the left k times. Returns the
 * last min(10, len) characters to the left of the cursor,
 * where len is the number of characters to the left of the cursor.
 * string cursorRight(int k) Moves the cursor to the right k times. Returns the
 * last min(10, len) characters to the left of the cursor,
 * where len is the number of characters to the left of the cursor.
 */

class TextEditor {
    Stack<Character> left;
    Stack<Character> right;

    public TextEditor() {
        left = new Stack<>();
        right = new Stack<>();
    }

    public void addText(String text) {
        for (int i = 0; i < text.length(); i++) {
            left.push(text.charAt(i));
        }
    }

    public int deleteText(int k) {
        int deleted = 0;
        while (!left.isEmpty() && k > 0) {
            left.pop();
            k--;
            deleted++;
        }
        return deleted;
    }

    public String cursorLeft(int k) {
        while (!left.isEmpty() && k > 0) {
            right.push(left.pop());
            k--;
        }
        return getLastTen();
    }

    public String cursorRight(int k) {
        while (!right.isEmpty() && k > 0) {
            left.push(right.pop());
            k--;
        }
        return getLastTen();
    }

    private String getLastTen() {
        StringBuilder res = new StringBuilder();
        int k = 10;

        while (!left.isEmpty() && k > 0) {
            res.append(left.pop());
            k--;
        }
        res.reverse();

        for (int i = 0; i < res.length(); i++) {
            left.push(res.charAt(i));
        }
        return res.toString();
    }
}