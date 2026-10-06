# Minimum Add To Make Parentheses Valid

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Greedy` `Bracket Sequences`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {

    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAddsRequired = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openBrackets++;
            } else {
                // If an open bracket exists, match it with the closing one
                // If not, we need to add an open bracket.
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    minAddsRequired++;
                }
            }
        }

        // Add the remaining open brackets as closing brackets would be required.
        return minAddsRequired + openBrackets;
    }
}
```

---

---
## Quick Revision
Given a string of parentheses, find the minimum number of insertions needed to make it valid.
We can solve this by tracking the balance of open parentheses and the number of required additions.

## Intuition
The core idea is to maintain a count of "unmatched" open parentheses. When we encounter an opening parenthesis `(`, we increment this count. When we see a closing parenthesis `)`, we try to match it with an existing open parenthesis. If there's an open parenthesis available (count > 0), we decrement the count, signifying a successful match. If there are no open parentheses available (count == 0), it means this closing parenthesis is "unmatched" and requires a preceding opening parenthesis to be added. We track these required additions separately. After iterating through the entire string, any remaining unmatched open parentheses also need corresponding closing parentheses to be added.

## Algorithm
1. Initialize two integer variables: `openBrackets` to 0 (to track unmatched open parentheses) and `minAddsRequired` to 0 (to track the total additions needed).
2. Iterate through each character `c` in the input string `s`.
3. If `c` is an opening parenthesis `(`:
    a. Increment `openBrackets`.
4. If `c` is a closing parenthesis `)`:
    a. Check if `openBrackets` is greater than 0.
        i. If yes, decrement `openBrackets` (meaning this closing parenthesis successfully matched an open one).
        ii. If no, increment `minAddsRequired` (meaning this closing parenthesis needs a preceding opening parenthesis to be added).
5. After the loop finishes, the total number of additions required is `minAddsRequired` (for unmatched closing parentheses) plus the remaining `openBrackets` (which need corresponding closing parentheses).
6. Return `minAddsRequired + openBrackets`.

## Concept to Remember
*   **Stack-like Behavior:** Although not explicitly using a stack data structure, the `openBrackets` variable simulates the behavior of a stack by tracking the depth of nested open parentheses.
*   **Greedy Approach:** At each step, we make the locally optimal choice (matching a closing parenthesis if possible) which leads to the globally optimal solution.
*   **Balance Tracking:** The problem fundamentally relies on maintaining a balance or count of opening and closing elements.

## Common Mistakes
*   **Forgetting Remaining Open Brackets:** Not accounting for the `openBrackets` left at the end of the string, which also require additions.
*   **Incorrectly Handling Closing Brackets:** Mismanaging the condition where a closing bracket appears without a preceding open bracket.
*   **Using a Full Stack Data Structure:** While a stack *can* solve this, it's often overkill and less efficient than a simple counter for this specific problem.
*   **Off-by-one Errors:** Incorrectly incrementing or decrementing counters, leading to an incorrect final count.

## Complexity Analysis
- Time: O(N) - The algorithm iterates through the input string `s` once. The length of the string is N.
- Space: O(1) - The algorithm uses a constant amount of extra space for the two integer variables (`openBrackets` and `minAddsRequired`), regardless of the input string's size.

## Commented Code
```java
class Solution {

    public int minAddToMakeValid(String s) {
        // Initialize a counter for unmatched open parentheses.
        int openBrackets = 0;
        // Initialize a counter for the minimum additions required.
        int minAddsRequired = 0;

        // Iterate through each character in the input string.
        for (char c : s.toCharArray()) {
            // If the character is an opening parenthesis, increment the openBrackets count.
            if (c == '(') {
                openBrackets++;
            } else { // If the character is a closing parenthesis.
                // Check if there is an available open parenthesis to match with.
                if (openBrackets > 0) {
                    // If yes, decrement openBrackets, signifying a valid pair.
                    openBrackets--;
                } else {
                    // If no open parenthesis is available, this closing parenthesis is invalid.
                    // We need to add an opening parenthesis to make it valid.
                    minAddsRequired++;
                }
            }
        }

        // After iterating through the string, any remaining openBrackets are unmatched.
        // Each of these requires a closing parenthesis to be added.
        // So, we add the count of remaining openBrackets to our total additions.
        return minAddsRequired + openBrackets;
    }
}
```

## Interview Tips
*   **Explain the Counter Logic:** Clearly articulate why a single counter for `openBrackets` is sufficient and how it mimics stack behavior.
*   **Edge Cases:** Discuss how the algorithm handles empty strings, strings with only opening brackets, and strings with only closing brackets.
*   **Alternative Solutions:** Briefly mention that a stack-based approach is also possible, but highlight why the counter method is more efficient in terms of space.
*   **Walk Through Examples:** Be prepared to trace the algorithm with a few example strings like "())", "(((", "()()", and "(()))(".

## Revision Checklist
- [ ] Understand the problem: minimum insertions for valid parentheses.
- [ ] Implement the counter-based approach.
- [ ] Handle opening parentheses correctly.
- [ ] Handle closing parentheses correctly (matching or requiring addition).
- [ ] Account for remaining unmatched open parentheses.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (empty string, all open, all closed).

## Similar Problems
*   Valid Parentheses (LeetCode 20)
*   Longest Valid Parentheses (LeetCode 32)
*   Score of Parentheses (LeetCode 856)

## Tags
`String` `Stack` `Greedy`
