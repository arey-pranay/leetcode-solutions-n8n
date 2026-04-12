# Minimum Remove To Make Valid Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(N)  
**Space:** O(N)

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
Use a stack to track open parentheses and a StringBuilder to modify the string in place.

## Intuition
The core idea is to identify and remove invalid parentheses. An opening parenthesis '(' is valid if it has a corresponding closing parenthesis ')' later in the string. A closing parenthesis ')' is valid if it has a corresponding opening parenthesis '(' earlier in the string. We can use a stack to keep track of the indices of opening parentheses. When we encounter a closing parenthesis, if the stack is not empty, it means we found a matching pair, so we pop the stack. If the stack is empty, the closing parenthesis is unmatched and thus invalid, so we remove it. After iterating through the string, any remaining indices in the stack correspond to unmatched opening parentheses, which are also invalid and need to be removed.

## Algorithm
1. Initialize an empty stack `st` to store the indices of opening parentheses.
2. Create a `StringBuilder` `sb` from the input string `s` to allow for efficient character removal.
3. Initialize an index variable `i` to 0.
4. Iterate through each character `c` in the `StringBuilder`.
5. If `c` is '(':
    a. Push the current index `i` onto the stack `st`.
6. If `c` is ')':
    a. If the stack `st` is empty:
        i. This closing parenthesis is unmatched. Remove the character at index `i` from `sb`.
        ii. Decrement `i` because the string has shrunk, and the next character is now at the current `i`.
    b. If the stack `st` is not empty:
        i. This closing parenthesis has a matching opening parenthesis. Pop the top index from `st`.
7. If `c` is neither '(' nor ')', do nothing.
8. Increment `i` after processing each character.
9. After the loop finishes, any indices remaining in the stack `st` correspond to unmatched opening parentheses.
10. While the stack `st` is not empty:
    a. Pop an index from `st`.
    b. Remove the character at that index from `sb`.
11. Return the string representation of `sb`.

## Concept to Remember
*   **Stack Data Structure:** Essential for tracking unmatched opening parentheses and their positions.
*   **String Immutability vs. Mutability:** Understanding that `String` in Java is immutable, necessitating the use of `StringBuilder` for efficient in-place modifications.
*   **Two-Pass Approach (Implicit):** The first pass identifies and removes invalid closing parentheses, and the second pass (implicitly via the stack cleanup) removes invalid opening parentheses.

## Common Mistakes
*   **Modifying String Directly:** Attempting to remove characters from a `String` directly, which is inefficient and incorrect due to immutability.
*   **Incorrect Index Handling:** Forgetting to adjust the index `i` when deleting a character from the `StringBuilder`, leading to skipped characters or out-of-bounds errors.
*   **Not Handling Remaining Open Parentheses:** Failing to clear the stack after the initial iteration, thus leaving unmatched opening parentheses in the result.
*   **Off-by-One Errors:** Mismanaging indices when pushing to or popping from the stack, or when deleting characters.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string once to identify invalid parentheses and potentially a second time (implicitly through stack operations and deletions) to remove them. Stack operations (push, pop, isEmpty) are O(1). StringBuilder deletions can be O(N) in the worst case if done repeatedly at the beginning, but here they are done based on indices, and the total number of deletions is at most N. Thus, the overall time complexity is linear.
- Space: O(N) - reason: In the worst case, the stack can store the indices of all opening parentheses if the string consists only of '(', leading to O(N) space. The `StringBuilder` also uses O(N) space to store the modified string.

## Commented Code
```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        // Use a stack to store indices of opening parentheses.
        Stack<Integer> st = new Stack<>();
        // Use a StringBuilder for efficient modification of the string.
        StringBuilder sb = new StringBuilder(s);
        // Initialize an index counter.
        int i = 0;
        // Iterate through each character of the string using the StringBuilder.
        for (char c : sb.toString().toCharArray()) {
            // Check the current character.
            switch (c) {
                case '(':
                    // If it's an opening parenthesis, push its index onto the stack.
                    st.push(i);
                    break;
                case ')':
                    // If it's a closing parenthesis:
                    if (st.isEmpty()) {
                        // If the stack is empty, this closing parenthesis is unmatched.
                        // Remove it from the StringBuilder.
                        sb.deleteCharAt(i);
                        // Decrement 'i' because the string has shrunk, and the next character is now at the current index.
                        i--;
                    } else {
                        // If the stack is not empty, this closing parenthesis matches an opening one.
                        // Pop the index of the matching opening parenthesis from the stack.
                        st.pop();
                    }
                    break;
                default:
                    // If it's any other character (lowercase English letter), do nothing.
                    break;
            }
            // Increment the index counter for the next character.
            i++;
        }
        // After iterating through the string, any indices left in the stack correspond to unmatched opening parentheses.
        while (!st.isEmpty()) {
            // Pop an index of an unmatched opening parenthesis.
            // Remove the character at that index from the StringBuilder.
            sb.deleteCharAt(st.pop());
        }
        // Convert the StringBuilder back to a String and return it.
        return sb.toString();
    }
}
```

## Interview Tips
*   **Explain Your Stack Logic:** Clearly articulate why a stack is suitable for tracking parentheses and how it helps identify mismatches.
*   **Address String Mutability:** Be prepared to discuss why `StringBuilder` is necessary and how it differs from `String`.
*   **Edge Cases:** Consider cases like an empty string, a string with only parentheses, or a string with no parentheses.
*   **Walk Through an Example:** Use a simple example like `lee(t(c)o)de)` or `a)b(c)d` to demonstrate your algorithm step-by-step.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify invalid parentheses using a stack.
- [ ] Handle unmatched closing parentheses during the first pass.
- [ ] Handle unmatched opening parentheses after the first pass.
- [ ] Use `StringBuilder` for efficient string modifications.
- [ ] Correctly manage indices when deleting characters.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   Valid Parentheses
*   Longest Valid Parentheses
*   Remove Invalid Parentheses

## Tags
`Stack` `String` `StringBuilder` `Two Pointers`

## My Notes
amazing code
