# Minimum Remove To Make Valid Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        int i=0;
        for(char c : sb.toString().toCharArray()){
            switch(c){
                case '(':
                    st.push(i);
                    break;
                case ')':
                    if(st.isEmpty()) sb.deleteCharAt(i--);
                    else st.pop();
                    break;
                default:
                    break;
            }
            i++;
        }
        while(!st.isEmpty()) sb.deleteCharAt(st.pop());
        return sb.toString();
    }
}

```

---

---
## Quick Revision
Given a string of parentheses and lowercase English characters, remove the minimum number of parentheses to make the string valid.
Use a stack to track open parentheses and a StringBuilder to modify the string in-place.

## Intuition
The core idea is to identify and remove "unmatched" parentheses. An opening parenthesis '(' is unmatched if it doesn't have a corresponding closing parenthesis ')'. A closing parenthesis ')' is unmatched if it appears without a preceding, available opening parenthesis. A stack is a natural fit for this because it allows us to keep track of the most recent unmatched opening parenthesis. When we encounter a closing parenthesis, we check if there's an opening one on the stack. If there is, they form a valid pair, and we pop the opening one. If not, the closing parenthesis is invalid and should be removed. After iterating through the string, any opening parentheses remaining on the stack are also unmatched and need to be removed.

## Algorithm
1. Initialize an empty stack `st` to store the indices of opening parentheses.
2. Create a `StringBuilder` `sb` from the input string `s` for efficient modification.
3. Initialize an index `i` to 0.
4. Iterate through each character `c` of the `StringBuilder`:
    a. If `c` is '(': push its current index `i` onto the stack `st`.
    b. If `c` is ')':
        i. If the stack `st` is empty, it means this closing parenthesis has no matching opening parenthesis. Remove the character at index `i` from `sb` and decrement `i` (because the string has shrunk and the next character is now at the current `i`).
        ii. If the stack `st` is not empty, it means this closing parenthesis has a matching opening parenthesis. Pop the top index from `st` (as this pair is now considered valid).
    c. If `c` is neither '(' nor ')', do nothing.
    d. Increment `i` to move to the next character.
5. After iterating through the entire string, any indices remaining in the stack `st` correspond to unmatched opening parentheses.
6. While the stack `st` is not empty:
    a. Pop an index from `st`.
    b. Remove the character at that index from `sb`.
7. Convert the `StringBuilder` `sb` back to a `String` and return it.

## Concept to Remember
*   **Stack Data Structure:** Essential for tracking nested structures like parentheses, where the last opened must be the first closed.
*   **String Manipulation:** Efficiently modifying strings, often using `StringBuilder` in Java to avoid creating new string objects repeatedly.
*   **Two-Pass Approach (Implicit):** The first pass identifies invalid closing parentheses and potential invalid opening ones. The second pass (implicit via the stack cleanup) removes the remaining invalid opening ones.

## Common Mistakes
*   **Modifying String While Iterating:** Directly modifying the original string or `StringBuilder` without adjusting the index can lead to skipping characters or index out of bounds errors. The `i--` when deleting a ')' is crucial.
*   **Not Handling Remaining Open Parentheses:** Forgetting to clear out any opening parentheses left on the stack after the initial pass.
*   **Incorrect Stack Usage:** Pushing wrong values (e.g., characters instead of indices) or popping at the wrong times.
*   **Inefficient String Concatenation:** Using `+` for string concatenation in a loop, which is O(n^2) in Java. `StringBuilder` is O(n).

## Complexity Analysis
*   **Time:** O(n) - The code iterates through the string twice in the worst case (once to process characters and once to remove remaining open parentheses from the stack). Stack operations (push, pop, isEmpty) are O(1). `StringBuilder` operations (deleteCharAt) are O(n) in the worst case if deleting from the beginning, but amortized O(1) for typical usage patterns here, or O(n) if considering the total cost of deletions. Overall, it's linear with respect to the length of the string.
*   **Space:** O(n) - In the worst case, the stack might store the indices of all opening parentheses if the string consists only of '('. The `StringBuilder` also takes O(n) space.

## Commented Code
```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        // Use a stack to store the indices of opening parentheses.
        Stack<Integer> st = new Stack<>();
        // Use a StringBuilder for efficient modification of the string.
        StringBuilder sb = new StringBuilder(s);
        // Initialize an index counter for iterating through the string.
        int i = 0;
        // Iterate through each character of the StringBuilder.
        for (char c : sb.toString().toCharArray()) { // Convert to char array for iteration, but operate on sb
            // Use a switch statement to handle different character types.
            switch (c) {
                case '(':
                    // If an opening parenthesis is found, push its index onto the stack.
                    st.push(i);
                    break;
                case ')':
                    // If a closing parenthesis is found:
                    if (st.isEmpty()) {
                        // If the stack is empty, this ')' is unmatched.
                        // Remove it from the StringBuilder.
                        sb.deleteCharAt(i);
                        // Decrement 'i' because the string has shrunk, and the next character is now at the current index.
                        i--;
                    } else {
                        // If the stack is not empty, this ')' matches the last '('.
                        // Pop the index of the matching '(' from the stack.
                        st.pop();
                    }
                    break;
                default:
                    // If the character is not a parenthesis, ignore it.
                    break;
            }
            // Increment the index to move to the next character.
            i++;
        }
        // After the first pass, any indices remaining in the stack correspond to unmatched opening parentheses.
        while (!st.isEmpty()) {
            // Pop an index of an unmatched opening parenthesis.
            // Remove the character at that index from the StringBuilder.
            sb.deleteCharAt(st.pop());
        }
        // Convert the modified StringBuilder back to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Explain the Stack's Role:** Clearly articulate why a stack is the appropriate data structure for matching parentheses.
*   **Handle Edge Cases:** Discuss what happens with empty strings, strings with only one type of parenthesis, or strings with no parentheses.
*   **Clarify Index Management:** Emphasize the importance of correctly managing the index `i` when deleting characters from the `StringBuilder` to avoid skipping elements.
*   **Consider Alternatives (Briefly):** You might briefly mention that a two-pass approach without a stack (e.g., first pass to mark invalid ')' and second pass to mark invalid '(') is also possible, but the stack approach is often more intuitive for this problem.

## Revision Checklist
- [ ] Understand the problem statement: minimum removals for valid parentheses.
- [ ] Identify unmatched '(' and ')'.
- [ ] Recognize stack as a suitable data structure for matching.
- [ ] Implement the first pass to handle invalid ')'.
- [ ] Implement the second pass (stack cleanup) to handle invalid '('.
- [ ] Ensure correct index management when modifying `StringBuilder`.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the logic and trade-offs.

## Similar Problems
*   Valid Parentheses (LeetCode 20)
*   Longest Valid Parentheses (LeetCode 32)
*   Remove Invalid Parentheses (LeetCode 301)

## Tags
`Stack` `String` `StringBuilder` `Two Pointers`

## My Notes
amazing code
