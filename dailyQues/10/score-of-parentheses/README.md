# Score Of Parentheses

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Bracket Sequences`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int i=0;
    public int scoreOfParentheses(String s) {
            return func(s);
        }
        public int func(String s){
            if (i == s.length() || s.charAt(i) == ')') return 0;
            int score=0;
            i++;
            if (s.charAt(i) == ')') {i++; score =1;}
            else { score = 2*func(s); i++; }
            return score + func(s);
        }
}

// class Solution {
//     public int scoreOfParentheses(String s) {
//         Stack<Integer> st = new Stack<>();
//         st.push(0);

//         for (char c : s.toCharArray()) {
//             if (c == '(') {
//                 st.push(0);
//             } else {
//                 int v = st.pop();
//                 int score = (v == 0) ? 1 : 2 * v;
//                 st.push(st.pop() + score);
//             }
//         }

//         return st.pop();
//     }
// }
```

---

---
## Quick Revision
This problem asks to calculate the score of a balanced parentheses string based on specific rules.
The solution involves using a stack to keep track of scores at different nesting levels.

## Intuition
The core idea is that the score of a balanced parentheses string `S` can be broken down recursively.
If `S` is `(A)`, its score is `2 * score(A)`.
If `S` is `AB`, its score is `score(A) + score(B)`.
The base case is `()` which has a score of 1.
A stack naturally helps manage these nested structures and their accumulated scores. When we encounter an opening parenthesis `(`, we push a new score accumulator onto the stack. When we see a closing parenthesis `)`, we pop the current score, calculate its value (either 1 for `()` or `2 * inner_score` for `(A)`), and add it to the score of the parent level (which is now at the top of the stack).

## Algorithm
1. Initialize an empty stack `st` and push `0` onto it. This `0` represents the score of the outermost level.
2. Iterate through each character `c` in the input string `s`.
3. If `c` is an opening parenthesis `(`:
    a. Push `0` onto the stack. This signifies the start of a new nested level, and its initial score is `0`.
4. If `c` is a closing parenthesis `)`:
    a. Pop the top element `v` from the stack. This `v` is the score accumulated within the just-closed parenthesis pair.
    b. Calculate the score for this pair:
        i. If `v` is `0`, it means the pair was `()`, so its score is `1`.
        ii. If `v` is not `0`, it means the pair was `(A)` where `A` had a score of `v`, so its score is `2 * v`.
    c. Pop the next element from the stack (this is the score of the parent level).
    d. Add the calculated score to the parent level's score.
    e. Push the updated parent level score back onto the stack.
5. After iterating through all characters, the final score will be the only element left on the stack. Pop and return it.

## Concept to Remember
*   **Stack Data Structure:** Essential for managing nested structures and LIFO (Last-In, First-Out) operations.
*   **Recursion/Decomposition:** Understanding how to break down a complex problem into smaller, self-similar subproblems.
*   **Balanced Parentheses:** Recognizing patterns and properties of well-formed parenthesis strings.

## Common Mistakes
*   **Incorrectly handling the base case `()`:** Forgetting that `()` scores 1 and not 0.
*   **Mismanaging stack operations:** Pushing/popping at the wrong times or with incorrect values.
*   **Incorrectly applying the `2 * v` rule:** Not realizing `v` represents the *inner* score.
*   **Off-by-one errors:** Especially when dealing with indices or loop boundaries if not using a character-by-character iteration.
*   **Not initializing the stack correctly:** The initial `0` is crucial for the outermost level.

## Complexity Analysis
*   **Time:** O(N) - The algorithm iterates through the input string `s` once. Stack operations (push, pop) take O(1) time.
*   **Space:** O(N) - In the worst case (e.g., "((((...))))"), the stack can grow up to the depth of the nesting, which can be proportional to the length of the string.

## Commented Code
```java
import java.util.Stack; // Import the Stack class for using stack data structure

class Solution {
    public int scoreOfParentheses(String s) { // Main method to calculate the score of the parentheses string
        Stack<Integer> st = new Stack<>(); // Initialize a stack to store scores at different nesting levels
        st.push(0); // Push an initial score of 0 for the outermost level

        for (char c : s.toCharArray()) { // Iterate through each character of the input string s
            if (c == '(') { // If the current character is an opening parenthesis
                st.push(0); // Push 0 onto the stack, representing the start of a new nested score calculation
            } else { // If the current character is a closing parenthesis
                int v = st.pop(); // Pop the score accumulated within the just-closed parenthesis pair
                // Calculate the score for this pair:
                // If v is 0, it means the pair was "()", so its score is 1.
                // Otherwise, it means the pair was "(A)" where A had a score of v, so its score is 2 * v.
                int score = (v == 0) ? 1 : 2 * v;
                // Pop the score of the parent level from the stack
                // Add the calculated score of the current pair to the parent level's score
                // Push the updated parent level score back onto the stack
                st.push(st.pop() + score);
            }
        }

        return st.pop(); // After processing all characters, the final score is the only element left on the stack. Pop and return it.
    }
}
```

## Interview Tips
*   **Explain the stack's role:** Clearly articulate how the stack helps manage nested scores and the LIFO principle.
*   **Walk through an example:** Use a string like `(()(()))` to demonstrate the stack's state changes step-by-step.
*   **Discuss the two rules:** Emphasize how the code handles `()` (score 1) and `(A)` (score `2 * score(A)`) distinctly.
*   **Consider alternative approaches (if time permits):** Briefly mention if you can think of a way without a stack (e.g., using depth and a single pass, though the stack is generally cleaner).

## Revision Checklist
- [ ] Understand the scoring rules: `()` = 1, `(A)` = 2*A, `AB` = A+B.
- [ ] Recognize the need for a stack to handle nesting.
- [ ] Implement the logic for `(`: push a new score context.
- [ ] Implement the logic for `)`: pop, calculate score, add to parent.
- [ ] Handle the base case `()` correctly (score 1).
- [ ] Handle the recursive case `(A)` correctly (score `2 * inner_score`).
- [ ] Ensure correct stack initialization and final result retrieval.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 20: Valid Parentheses
*   LeetCode 32: Longest Valid Parentheses
*   LeetCode 1021: Remove Outermost Parentheses

## Tags
`Stack` `Recursion` `String` `Depth-First Search`
