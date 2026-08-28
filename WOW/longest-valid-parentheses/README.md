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
The problem is to find the length of the longest valid parentheses substring in a given string. We solve this by iterating through the string twice, once from left to right and once from right to left, to keep track of the maximum valid parentheses length.

## Intuition
The key insight here is that when we encounter a closing parenthesis, we can either start a new valid substring or extend an existing one. We use two pointers, one for the current opening parenthesis and one for the current closing parenthesis, to track the maximum valid length.

## Algorithm
1. Initialize two pointers, `open` and `close`, to keep track of the current opening and closing parentheses respectively.
2. Initialize a variable `max` to store the maximum valid length found so far.
3. Iterate through the string from left to right:
	* If the current character is an opening parenthesis, increment `open`.
	* If the current character is a closing parenthesis:
		+ If `open` is equal to `close`, increment `max` by 2 times the current `open` value and reset `open` and `close` to 0.
		+ If `close` is greater than `open`, reset `open` and `close` to 0.
4. Iterate through the string from right to left:
	* If the current character is a closing parenthesis, increment `close`.
	* If the current character is an opening parenthesis:
		+ If `open` is equal to `close`, increment `max` by 2 times the current `open` value and reset `open` and `close` to 0.
		+ If `close` is less than `open`, reset `open` and `close` to 0.

## Concept to Remember
* We use two passes through the string to avoid having to look ahead and determine the maximum valid length.
* We use a `max` variable to keep track of the maximum valid length found so far.
* We use two pointers, `open` and `close`, to keep track of the current opening and closing parentheses respectively.

## Common Mistakes
* Failing to reset `open` and `close` to 0 when they are no longer valid.
* Not incrementing `max` by 2 times the current `open` value when `open` is equal to `close`.
* Not resetting `open` and `close` to 0 when `close` is greater than `open` or less than `open`.

## Complexity Analysis
- Time: O(n) - We iterate through the string twice, once from left to right and once from right to left.
- Space: O(1) - We use a constant amount of space to store the `max`, `open`, and `close` variables.

## Commented Code
```java
class Solution {
    public int longestValidParentheses(String s) {
        int open = 0, close = 0, max = 0; // Initialize variables to keep track of current and max valid length
        int n = s.length();
        // Iterate through string from left to right
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') { // If current character is an opening parenthesis
                open++; // Increment opening parenthesis count
            } else { // If current character is a closing parenthesis
                close++; // Increment closing parenthesis count
                if (open == close) { // If current opening and closing parenthesis counts are equal
                    max = Math.max(max, 2 * open); // Update max valid length
                }
            }
            if (close > open) { // If current closing parenthesis count is greater than opening parenthesis count
                open = 0; close = 0; // Reset opening and closing parenthesis counts
            }
        }
        // Iterate through string from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') { // If current character is a closing parenthesis
                close++; // Increment closing parenthesis count
            } else { // If current character is an opening parenthesis
                open++; // Increment opening parenthesis count
                if (open == close) { // If current opening and closing parenthesis counts are equal
                    max = Math.max(max, 2 * open); // Update max valid length
                }
            }
            if (close < open) { // If current closing parenthesis count is less than opening parenthesis count
                open = 0; close = 0; // Reset opening and closing parenthesis counts
            }
        }
        return max; // Return max valid length
    }
}
```

## Interview Tips
* Make sure to reset `open` and `close` to 0 when they are no longer valid.
* Pay attention to the edge cases, such as when the string is empty or when there are no valid parentheses.
* Practice explaining the solution to a colleague or interviewer to ensure you understand it thoroughly.

## Revision Checklist
- [ ] Understand the problem and the solution
- [ ] Implement the solution from scratch
- [ ] Test the solution with edge cases and sample inputs
- [ ] Review the solution and the explanation

## Similar Problems
* LeetCode: 32. Longest Valid Parentheses
* LeetCode: 22. Generate Parentheses

## Tags
`Array` `Hash Map` `String`
