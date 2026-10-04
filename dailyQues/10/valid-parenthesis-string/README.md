# Valid Parenthesis String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Dynamic Programming` `Stack` `Greedy` `Bracket Sequences`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean checkValidString(String s) {
        int l = 0, h = 0;

        for (int i = 0; i < s.length(); i++) {
            l += s.charAt(i) == '(' ? 1 : -1;
            h += s.charAt(i) == ')' ? -1 : 1;

            if (h < 0) return false;

            l = Math.max(l, 0);
        }

        return l == 0;
    }
}
// brilliant : https://leetcode.com/problems/valid-parenthesis-string/solutions/8554358/solution-by-la_castille-lkwy
```

---

---
## Quick Revision
Checks if a string containing '(', ')', and '*' is valid, where '*' can be treated as '(', ')', or an empty string.
Solves by tracking the possible range of open parentheses counts.

## Intuition
The core idea is that a '*' can be flexible. At any point while scanning the string, the number of open parentheses must be non-negative. If we encounter a ')', we must have a preceding '(' or '*' to match it. If we encounter a '(', we increase the count. A '*' can either increase, decrease, or do nothing to the open parenthesis count.

To handle the flexibility of '*', we can maintain a range of possible open parenthesis counts. Let `l` be the minimum possible number of open parentheses and `h` be the maximum possible number of open parentheses.

When we see '(':
- `l` increases by 1 (minimum open count increases).
- `h` increases by 1 (maximum open count increases).

When we see ')':
- `l` decreases by 1 (minimum open count decreases).
- `h` decreases by 1 (maximum open count decreases).

When we see '*':
- `l` decreases by 1 (it *could* be a closing parenthesis).
- `h` increases by 1 (it *could* be an opening parenthesis).

Crucially, the minimum count `l` can never go below zero. If it does, it means even with all '*' acting as empty strings, we still have too many closing parentheses. So, we reset `l` to 0 if it becomes negative.

If at any point the maximum count `h` becomes negative, it means we have encountered more closing parentheses than we can possibly match, even if all '*' were treated as opening parentheses. In this case, the string is invalid.

Finally, for the string to be valid, after processing the entire string, the minimum number of open parentheses `l` must be exactly 0. This ensures that all opening parentheses have been matched.

## Algorithm
1. Initialize two integer variables: `l` (minimum open parentheses count) to 0 and `h` (maximum open parentheses count) to 0.
2. Iterate through each character `c` of the input string `s`.
3. If `c` is '(':
    - Increment `l` by 1.
    - Increment `h` by 1.
4. If `c` is ')':
    - Decrement `l` by 1.
    - Decrement `h` by 1.
5. If `c` is '*':
    - Decrement `l` by 1 (treat '*' as ')').
    - Increment `h` by 1 (treat '*' as '(').
6. After updating `l` and `h` for the current character, check if `h` is less than 0. If it is, return `false` immediately, as the string is invalid.
7. After updating `l` and `h`, ensure `l` is not negative. Set `l = Math.max(l, 0)`. This is because a '*' can also be an empty string, so the minimum count cannot be less than zero.
8. After the loop finishes, check if `l` is equal to 0. If it is, return `true`; otherwise, return `false`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices at each step to reach a global optimum.
*   **Range Tracking:** Maintaining a dynamic range of possible states (open parenthesis counts) to account for ambiguity.
*   **State Management:** Carefully updating state variables based on character types and constraints.

## Common Mistakes
*   Forgetting to reset `l` to `0` when it becomes negative. This is crucial because '*' can act as an empty string.
*   Not checking `h < 0` inside the loop. This check is vital to prune invalid paths early.
*   Confusing the roles of `l` and `h` or misinterpreting what they represent. `l` is the *minimum* possible open count, `h` is the *maximum*.
*   Assuming '*' can only be '(' or ')', forgetting it can also be an empty string.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the string once.
- Space: O(1) - reason: We only use a few constant extra variables (`l`, `h`, `i`).

## Commented Code
```java
class Solution {
    public boolean checkValidString(String s) {
        // Initialize 'l' to track the minimum possible number of open parentheses.
        // This assumes '*' can be treated as ')' or an empty string.
        int l = 0;
        // Initialize 'h' to track the maximum possible number of open parentheses.
        // This assumes '*' can be treated as '(' or an empty string.
        int h = 0;

        // Iterate through each character of the input string.
        for (int i = 0; i < s.length(); i++) {
            // Get the current character.
            char currentChar = s.charAt(i);

            // If the character is an opening parenthesis '(':
            if (currentChar == '(') {
                // Increment both minimum and maximum open counts.
                l++;
                h++;
            }
            // If the character is a closing parenthesis ')':
            else if (currentChar == ')') {
                // Decrement both minimum and maximum open counts.
                l--;
                h--;
            }
            // If the character is a wildcard '*':
            else { // currentChar == '*'
                // For minimum count, '*' can act as a closing parenthesis, so decrement 'l'.
                l--;
                // For maximum count, '*' can act as an opening parenthesis, so increment 'h'.
                h++;
            }

            // If the maximum possible open count 'h' drops below zero,
            // it means we have encountered more closing parentheses than can be matched,
            // even if all '*' were treated as opening parentheses. The string is invalid.
            if (h < 0) {
                return false;
            }

            // The minimum open count 'l' cannot be less than zero.
            // If it drops below zero, it means we had more closing parentheses than opening ones,
            // but '*' could have been an empty string to compensate. So, reset 'l' to 0.
            l = Math.max(l, 0);
        }

        // After iterating through the entire string, for the string to be valid,
        // the minimum possible number of open parentheses must be exactly zero.
        // This ensures that all opening parentheses have been matched.
        return l == 0;
    }
}
```

## Interview Tips
*   Explain the intuition behind tracking a range (`l` and `h`) clearly. Emphasize why `h < 0` is an immediate `false` and why `l` needs to be `max(l, 0)`.
*   Walk through an example like `(*)` or `(*))` to demonstrate how `l` and `h` change.
*   Be prepared to discuss alternative approaches (like recursion with memoization or a stack-based approach for simpler cases) and why this greedy range-tracking method is efficient.
*   If asked about edge cases, consider empty strings, strings with only '*', strings with only '(' or ')', and strings with unbalanced parentheses.

## Revision Checklist
- [ ] Understand the role of '(', ')', and '*'.
- [ ] Grasp the concept of tracking a range of open parenthesis counts.
- [ ] Implement the logic for updating `l` and `h` for each character type.
- [ ] Correctly handle the `h < 0` condition.
- [ ] Correctly handle the `l = Math.max(l, 0)` condition.
- [ ] Ensure the final check `l == 0` is understood.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Valid Parentheses (LeetCode 20)
*   Minimum Remove to Make Valid Parentheses (LeetCode 1249)
*   Longest Valid Parentheses (LeetCode 32)

## Tags
`String` `Dynamic Programming` `Greedy` `Stack`
