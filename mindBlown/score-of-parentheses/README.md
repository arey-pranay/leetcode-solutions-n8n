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
Given a balanced parentheses string, calculate its score based on specific rules.
Solve using recursion or a stack to track nested scores.

## Intuition
The problem defines a recursive structure for scoring parentheses. An empty pair `()` has a score of 1. A nested pair `(A)` has a score of `2 * score(A)`. Adjacent pairs `AB` have a score of `score(A) + score(B)`. This structure naturally lends itself to a recursive solution where we process the string segment by segment. Alternatively, a stack can maintain the current score at each nesting level.

## Algorithm
1. **Recursive Approach (using a global index):**
    a. Initialize a global index `i` to 0.
    b. Define a recursive function `calculateScore(s)`:
        i. If the current index `i` is out of bounds or the character at `s[i]` is ')', return 0.
        ii. Increment `i`.
        iii. If `s[i]` is ')', it means we encountered an empty pair `()`. Increment `i` and return 1.
        iv. If `s[i]` is '(', it means we have a nested structure `(A)`. Recursively call `calculateScore(s)` to get the score of `A`, multiply it by 2, and store it in `currentScore`. Increment `i`.
        v. After processing a pair (either `()` or `(A)`), recursively call `calculateScore(s)` again to handle adjacent structures `AB` and add its score to `currentScore`.
        vi. Return `currentScore`.
    c. The main function calls `calculateScore(s)`.

2. **Stack Approach:**
    a. Initialize a stack and push 0 onto it. This 0 represents the score of the outermost level.
    b. Iterate through the input string `s` character by character.
    c. If the character is '(':
        i. Push 0 onto the stack. This signifies starting a new nested level with an initial score of 0.
    d. If the character is ')':
        i. Pop the top element `v` from the stack. This `v` is the score of the just-completed inner expression.
        ii. Calculate the score for this closing parenthesis: if `v` is 0 (meaning it was an empty `()`), the score is 1. Otherwise, it's `2 * v` (for `(A)`).
        iii. Pop the next element from the stack (which is the score of the parent level). Add the calculated score to it.
        iv. Push the updated parent level score back onto the stack.
    e. After iterating through the entire string, the final score will be the only element left on the stack. Pop and return it.

## Concept to Remember
*   **Recursion:** Understanding how to break down a problem into smaller, self-similar subproblems.
*   **Stack Data Structure:** Using a stack to manage nested structures and their corresponding states (scores in this case).
*   **String Parsing:** Iterating through a string and making decisions based on character values.
*   **Balanced Parentheses:** The inherent property of the input string that simplifies processing.

## Common Mistakes
*   **Incorrectly handling the base case `()`:** Forgetting that `()` has a score of 1 and not 0.
*   **Mismanaging the global index in recursion:** Not incrementing the index correctly after processing a character or a sub-expression, leading to infinite loops or incorrect parsing.
*   **Stack Underflow/Overflow:** Not initializing the stack correctly or not handling the popping and pushing logic precisely, especially when dealing with adjacent expressions.
*   **Confusing `2 * v` with `v + 1`:** The rule is `2 * score(A)` for `(A)`, not `score(A) + 1`.

## Complexity Analysis
*   **Time: O(N)** - reason: Both the recursive and stack-based solutions iterate through the string once. Each character is processed a constant number of times.
*   **Space: O(N)** - reason: In the recursive approach, the space complexity is determined by the maximum depth of the recursion stack, which can be up to N/2 for a string like "((...))". In the stack-based approach, the stack can grow up to N/2 elements in the worst case.

## Commented Code
```java
class Solution {
    // Global index to keep track of the current position in the string during recursion.
    int i = 0;

    // Public method to initiate the scoring process.
    public int scoreOfParentheses(String s) {
        // Call the recursive helper function to calculate the score.
        return func(s);
    }

    // Recursive helper function to calculate the score of a balanced parentheses substring.
    public int func(String s) {
        // Base case: If we've reached the end of the string or encountered a closing parenthesis, return 0.
        if (i == s.length() || s.charAt(i) == ')') {
            return 0;
        }

        // Initialize the score for the current segment.
        int score = 0;
        // Move the index past the opening parenthesis.
        i++;

        // Check if the current character is a closing parenthesis.
        if (s.charAt(i) == ')') {
            // If it's ')', it means we have an empty pair "()". Its score is 1.
            // Move the index past the closing parenthesis.
            i++;
            score = 1;
        } else {
            // If it's not ')', it means we have a nested expression "(A)".
            // Recursively call func to get the score of the inner expression A.
            score = 2 * func(s);
            // After processing the inner expression, the index 'i' will be pointing to the closing parenthesis of "(A)".
            // Move the index past this closing parenthesis.
            i++;
        }

        // After processing either "()" or "(A)", we need to check for adjacent expressions "AB".
        // Recursively call func again to get the score of the next adjacent expression and add it to the current score.
        return score + func(s);
    }
}

// Stack-based solution (alternative and often preferred for interviews)
// class Solution {
//     public int scoreOfParentheses(String s) {
//         // Initialize a stack to store scores at different nesting levels.
//         Stack<Integer> st = new Stack<>();
//         // Push 0 to represent the score of the outermost level.
//         st.push(0);

//         // Iterate through each character of the input string.
//         for (char c : s.toCharArray()) {
//             // If the character is an opening parenthesis, push 0 onto the stack.
//             // This signifies starting a new nested level with an initial score of 0.
//             if (c == '(') {
//                 st.push(0);
//             } else {
//                 // If the character is a closing parenthesis, we need to calculate the score of the just-completed inner expression.
//                 // Pop the top element, which is the score 'v' of the inner expression.
//                 int v = st.pop();
//                 // Calculate the score for this closing parenthesis.
//                 // If v is 0, it means the inner expression was "()", so its score is 1.
//                 // Otherwise, it was "(A)", and its score is 2 * v.
//                 int score = (v == 0) ? 1 : 2 * v;
//                 // Pop the next element from the stack (which is the score of the parent level).
//                 // Add the calculated score to the parent level's score.
//                 st.push(st.pop() + score);
//             }
//         }

//         // After processing all characters, the final score of the entire string will be the only element left on the stack.
//         // Pop and return this final score.
//         return st.pop();
//     }
// }
```

## Interview Tips
*   **Clarify the rules:** Ensure you fully understand the scoring rules for `()`, `(A)`, and `AB`.
*   **Discuss both approaches:** Be prepared to explain both the recursive and stack-based solutions. The stack approach is often more intuitive for interviewers to follow and less prone to index errors.
*   **Trace an example:** Walk through a simple example like `(()(()))` with your chosen approach to demonstrate your understanding.
*   **Consider edge cases:** Think about empty strings (though the problem statement implies non-empty balanced strings) or strings with only one pair.

## Revision Checklist
- [ ] Understand the scoring rules: `()` = 1, `(A)` = 2 * score(A), `AB` = score(A) + score(B).
- [ ] Implement the recursive solution, paying close attention to index management.
- [ ] Implement the stack-based solution, correctly handling pushes and pops.
- [ ] Analyze time and space complexity for both approaches.
- [ ] Practice tracing examples with both methods.

## Similar Problems
*   [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
*   [32. Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses/)
*   [921. Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/)

## Tags
`Recursion` `Stack` `String`
