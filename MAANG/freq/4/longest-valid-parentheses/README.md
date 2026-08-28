# Longest Valid Parentheses

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Stack` `Bracket Sequences`  
**Time:** O(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int longestValidParentheses(String s) {
      int open = 0,close =0,max = 0;
      int n = s.length();
      for(int i=0;i<n;i++){
        if(s.charAt(i)=='(') open++;
        else{
            close++;
            if(open==close) max = Math.max(max,2*open);
        }
        if(close > open) {open=0;close=0;}
      }
      open=close=0;
       for(int i=n-1;i>=0;i--){
        if(s.charAt(i)==')') close++;
        else{
            open++;
            if(open==close) max = Math.max(max,2*open);
        }
        if(close < open) {open=0;close=0;}
      }
      return max;
    }
}
```

---

---
## Quick Revision
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
This problem can be solved efficiently using a two-pass approach with counters, or by using a stack.

## Intuition
The core idea is that a valid parentheses substring must have an equal number of opening and closing parentheses, and at any point while scanning from left to right, the count of closing parentheses should not exceed the count of opening parentheses. The provided solution cleverly uses a two-pass approach to handle cases where the longest valid substring might be unbalanced from one direction.

In the first pass (left-to-right), we count `open` and `close`. If `open == close`, we've found a potentially valid substring of length `2 * open`. If `close > open`, it means the current substring is invalid, so we reset both counters to start fresh. This pass correctly identifies valid substrings that are not preceded by an excess of opening parentheses.

However, this pass might miss valid substrings that are preceded by an excess of opening parentheses (e.g., "(()"). To handle this, a second pass (right-to-left) is performed. In this pass, we count `close` and `open`. If `open == close`, we again update `max`. If `close < open` (which is equivalent to `open > close` when scanning from right to left), the substring is invalid, and we reset. This second pass catches valid substrings that might have been missed in the first pass due to an initial imbalance of opening parentheses.

## Algorithm
1. Initialize `open` and `close` counters to 0.
2. Initialize `max` length to 0.
3. Iterate through the string from left to right (index `i` from 0 to `n-1`):
    a. If `s.charAt(i)` is '(', increment `open`.
    b. If `s.charAt(i)` is ')', increment `close`.
    c. If `open == close`, update `max = Math.max(max, 2 * open)`. This signifies a balanced substring.
    d. If `close > open`, reset `open = 0` and `close = 0`. This indicates an invalid substring, so we start counting again from the next character.
4. Reset `open` and `close` counters to 0.
5. Iterate through the string from right to left (index `i` from `n-1` to 0):
    a. If `s.charAt(i)` is ')', increment `close`.
    b. If `s.charAt(i)` is '(', increment `open`.
    c. If `open == close`, update `max = Math.max(max, 2 * open)`. This signifies a balanced substring.
    d. If `close < open` (which means `open > close` when scanning from right to left), reset `open = 0` and `close = 0`. This indicates an invalid substring, so we start counting again from the next character.
6. Return `max`.

## Concept to Remember
*   **Parentheses Matching:** Understanding the rules of well-formed parentheses, where each opening parenthesis must have a corresponding closing parenthesis in the correct order.
*   **Two-Pointer/Sliding Window (Implicit):** Although not a traditional sliding window, the two-pass approach with resetting counters effectively scans segments of the string.
*   **Greedy Approach:** At each step, we try to extend the current valid substring as much as possible and reset when validity is broken.

## Common Mistakes
*   **Only one pass:** Failing to consider that a single left-to-right pass might miss valid substrings that are preceded by an excess of opening parentheses.
*   **Incorrect reset condition:** Resetting counters at the wrong condition (e.g., `open > close` in the left-to-right pass).
*   **Off-by-one errors:** Incorrectly calculating the length of the valid substring (e.g., using `open + close` instead of `2 * open` when `open == close`).
*   **Not handling empty or single-character strings:** Although the provided code handles these implicitly, it's good to consider edge cases.

## Complexity Analysis
- Time: O(n) - reason The solution involves two separate passes through the string, each taking O(n) time, where n is the length of the string.
- Space: O(1) - reason The solution uses a constant amount of extra space for the `open`, `close`, and `max` variables, regardless of the input string size.

## Commented Code
```java
class Solution {
    public int longestValidParentheses(String s) {
      // Initialize counters for open and close parentheses
      int open = 0, close = 0;
      // Initialize max length of valid parentheses found so far
      int max = 0;
      // Get the length of the input string
      int n = s.length();

      // First pass: Iterate from left to right
      for (int i = 0; i < n; i++) {
        // If the current character is an opening parenthesis, increment open count
        if (s.charAt(i) == '(') {
          open++;
        }
        // If the current character is a closing parenthesis
        else {
          // Increment close count
          close++;
          // If open and close counts are equal, we have a balanced substring
          if (open == close) {
            // Update max length with the length of this balanced substring (2 * open)
            max = Math.max(max, 2 * open);
          }
        }
        // If close count exceeds open count, the current substring is invalid
        if (close > open) {
          // Reset both counters to start a new potential valid substring
          open = 0;
          close = 0;
        }
      }

      // Reset counters for the second pass
      open = 0;
      close = 0;

      // Second pass: Iterate from right to left
      for (int i = n - 1; i >= 0; i--) {
        // If the current character is a closing parenthesis, increment close count
        if (s.charAt(i) == ')') {
          close++;
        }
        // If the current character is an opening parenthesis
        else {
          // Increment open count
          open++;
          // If open and close counts are equal, we have a balanced substring
          if (open == close) {
            // Update max length with the length of this balanced substring (2 * open)
            max = Math.max(max, 2 * open);
          }
        }
        // If close count is less than open count (meaning open > close when scanning from right),
        // the current substring is invalid
        if (close < open) {
          // Reset both counters to start a new potential valid substring
          open = 0;
          close = 0;
        }
      }
      // Return the maximum length of valid parentheses found
      return max;
    }
}
```

## Interview Tips
*   **Explain the two-pass logic:** Clearly articulate why a single pass isn't sufficient and how the second pass addresses the limitations of the first.
*   **Consider edge cases:** Discuss how your solution handles empty strings, strings with only one type of parenthesis, and strings with no valid parentheses.
*   **Alternative approaches:** Be prepared to discuss other solutions, such as using a stack, and their respective trade-offs in terms of time and space complexity.
*   **Walk through an example:** Use a string like `")()())"` or `"(()"` to demonstrate how your algorithm processes the input and updates the `max` length.

## Revision Checklist
- [ ] Understand the definition of a "valid parentheses" substring.
- [ ] Implement the left-to-right pass correctly, including the reset condition.
- [ ] Implement the right-to-left pass correctly, including its reset condition.
- [ ] Ensure `max` is updated correctly when `open == close`.
- [ ] Verify the time and space complexity.
- [ ] Test with various edge cases (empty string, all open, all close, mixed).

## Similar Problems
*   Valid Parentheses (LeetCode 20)
*   Minimum Remove to Make Valid Parentheses (LeetCode 1249)
*   Score of Parentheses (LeetCode 856)

## Tags
`String` `Dynamic Programming` `Stack`
