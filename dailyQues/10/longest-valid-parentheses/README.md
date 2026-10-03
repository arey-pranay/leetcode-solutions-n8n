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
This problem can be solved efficiently using a two-pass approach with counters.

## Intuition
The core idea is that a valid parentheses substring must have an equal number of opening and closing parentheses. However, simply counting them isn't enough; the order matters. A substring like ")(" has equal counts but is invalid.

The first pass (left-to-right) handles cases where valid substrings might be "balanced" or have an excess of opening parentheses at the end (e.g., "(()"). If we encounter more closing parentheses than opening ones, it means the current sequence is broken, and we reset our counts. When `open == close`, we've found a potentially valid substring, and its length is `2 * open` (or `2 * close`).

The second pass (right-to-left) is crucial to catch valid substrings that might be missed in the first pass due to an excess of opening parentheses at the beginning (e.g., "())"). In this pass, we count closing parentheses first. If `close < open` (meaning more opening than closing from the right), the sequence is broken, and we reset. Again, when `open == close`, we update the maximum length. This two-pass approach ensures we cover all valid possibilities.

## Algorithm
1. Initialize `open` and `close` counters to 0.
2. Initialize `max_length` to 0.
3. **First Pass (Left-to-Right):**
    a. Iterate through the string from left to right.
    b. If the current character is '(', increment `open`.
    c. If the current character is ')', increment `close`.
    d. If `open == close`, update `max_length = max(max_length, 2 * open)`.
    e. If `close > open`, reset `open = 0` and `close = 0` (invalid sequence).
4. Reset `open` and `close` counters to 0.
5. **Second Pass (Right-to-Left):**
    a. Iterate through the string from right to left.
    b. If the current character is ')', increment `close`.
    c. If the current character is '(', increment `open`.
    d. If `open == close`, update `max_length = max(max_length, 2 * open)`.
    e. If `open > close`, reset `open = 0` and `close = 0` (invalid sequence from the right).
6. Return `max_length`.

## Concept to Remember
*   **Parentheses Matching:** Understanding the rules of well-formed parentheses, where each opening parenthesis must have a corresponding closing one in the correct order.
*   **Two-Pointer/Sliding Window (Implicit):** While not a traditional sliding window, the two-pass approach effectively scans segments of the string and resets when validity is broken, similar to how a window might be adjusted.
*   **Greedy Approach:** At each step, we make the locally optimal choice (updating max length when balanced) hoping it leads to a globally optimal solution.

## Common Mistakes
*   **Only one pass:** A single left-to-right pass might miss valid substrings that are preceded by unmatched opening parentheses (e.g., "(()").
*   **Incorrect reset condition:** Resetting counters at the wrong condition (e.g., `open > close` in the left-to-right pass) can lead to incorrect results.
*   **Not handling edge cases:** Empty strings or strings with only one type of parenthesis should be considered.
*   **Integer overflow:** For very long strings, though unlikely with typical LeetCode constraints, it's good to be mindful of potential overflows if intermediate calculations were more complex.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the string twice, each pass taking linear time with respect to the length of the string (n).
- Space: O(1) - reason: We only use a few constant extra variables (`open`, `close`, `max_length`, `n`) regardless of the input string size.

## Commented Code
```java
class Solution {
    public int longestValidParentheses(String s) {
      // Initialize counters for open and close parentheses.
      int open = 0;
      int close = 0;
      // Initialize max_length to store the longest valid parentheses substring found.
      int max = 0;
      // Get the length of the input string.
      int n = s.length();

      // First pass: Iterate from left to right.
      for(int i = 0; i < n; i++){
        // If the current character is an opening parenthesis, increment the open counter.
        if(s.charAt(i) == '(') {
            open++;
        }
        // If the current character is a closing parenthesis, increment the close counter.
        else {
            close++;
            // If the number of open and close parentheses are equal, we have a valid substring.
            // Update max_length with the length of this valid substring (2 * open).
            if(open == close) {
                max = Math.max(max, 2 * open);
            }
        }
        // If the number of closing parentheses exceeds the number of opening parentheses,
        // the current sequence is invalid. Reset both counters to start a new potential sequence.
        if(close > open) {
            open = 0;
            close = 0;
        }
      }

      // Reset counters for the second pass.
      open = 0;
      close = 0;

      // Second pass: Iterate from right to left.
      // This pass is necessary to catch valid substrings that might be missed in the first pass
      // due to an excess of opening parentheses at the beginning (e.g., "(()").
      for(int i = n - 1; i >= 0; i--){
        // If the current character is a closing parenthesis, increment the close counter.
        if(s.charAt(i) == ')') {
            close++;
        }
        // If the current character is an opening parenthesis, increment the open counter.
        else {
            open++;
            // If the number of open and close parentheses are equal, we have a valid substring.
            // Update max_length with the length of this valid substring (2 * open).
            if(open == close) {
                max = Math.max(max, 2 * open);
            }
        }
        // If the number of opening parentheses exceeds the number of closing parentheses (from the right),
        // the current sequence is invalid. Reset both counters to start a new potential sequence.
        if(open > close) { // Note: condition is open > close here for right-to-left pass
            open = 0;
            close = 0;
        }
      }
      // Return the maximum length of a valid parentheses substring found.
      return max;
    }
}
```

## Interview Tips
*   **Explain the two-pass logic:** Clearly articulate why a single pass isn't sufficient and how the second pass complements the first.
*   **Walk through examples:** Use examples like "(()", ")()())", and "()(()" to demonstrate how your algorithm handles different scenarios.
*   **Discuss alternative approaches (briefly):** Mention that a stack-based approach is also common and can solve this problem, but highlight the space complexity difference.
*   **Focus on clarity:** Ensure your variable names are descriptive and your logic is easy to follow.

## Revision Checklist
- [ ] Understand the definition of a "valid parentheses" substring.
- [ ] Recognize the need for balanced counts and correct order.
- [ ] Implement the left-to-right pass correctly, including the reset condition.
- [ ] Implement the right-to-left pass correctly, including its specific reset condition.
- [ ] Ensure `max_length` is updated correctly in both passes.
- [ ] Verify edge cases (empty string, all same characters).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Valid Parentheses (LeetCode 20)
*   Minimum Remove to Make Valid Parentheses (LeetCode 1249)
*   Score of Parentheses (LeetCode 856)

## Tags
`String` `Dynamic Programming` `Two Pointers`
