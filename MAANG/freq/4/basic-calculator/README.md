# Basic Calculator

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Math` `String` `Stack` `Recursion`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    int index=0;
    public int calculate(String s) {
        int curr = 0;
        int total = 0;
        int sign = 1;
        while(index<s.length()){
            char c= s.charAt(index++);
            if(c==' '){continue;}
            else if(c=='(')curr = calculate(s); 
            else if(c==')') return total + (sign*curr);
            else if(c=='+' || c=='-'){
                total = total + (sign*curr);
                curr=0;
                sign = c=='+' ? 1 : -1;
            }
            else{   
                curr = (curr*10)+ (c-'0');
            }
        }
        return total + (sign*curr);
    }
}
```

---

---
## Quick Revision
This problem asks to evaluate a basic arithmetic expression string containing non-negative integers, '+', '-', '(', ')', and spaces. The solution uses recursion to handle nested parentheses and a state-tracking approach for signs and current numbers.

## Intuition
The core idea is to process the expression character by character, maintaining the current number being built, the running total, and the current sign. When we encounter an opening parenthesis, it signifies a sub-expression that needs to be evaluated independently. Recursion is a natural fit here: we can call the `calculate` function again on the substring within the parentheses. The result of this recursive call becomes the "number" to be added to the current total, respecting the sign preceding the parenthesis. Closing parentheses signal the end of a sub-expression, and we return the calculated value for that sub-expression.

## Algorithm
1. Initialize `curr` (current number being parsed) to 0.
2. Initialize `total` (running sum) to 0.
3. Initialize `sign` (current sign, 1 for positive, -1 for negative) to 1.
4. Iterate through the input string `s` using a global `index`.
5. For each character `c`:
    a. If `c` is a space, skip it.
    b. If `c` is an opening parenthesis '(', recursively call `calculate(s)` to evaluate the sub-expression. The result of this recursive call becomes the new `curr`.
    c. If `c` is a closing parenthesis ')', return the current `total` plus the `sign` multiplied by `curr`. This signifies the end of the current evaluation scope (either the main expression or a sub-expression).
    d. If `c` is a '+' or '-', first add the `sign` multiplied by `curr` to the `total`. Then, reset `curr` to 0 and update `sign` based on the character ('+' for 1, '-' for -1).
    e. If `c` is a digit, update `curr` by multiplying it by 10 and adding the digit's integer value (`c - '0'`).
6. After the loop finishes (end of the string), return `total` plus the `sign` multiplied by the final `curr`.

## Concept to Remember
*   **Recursion:** Essential for handling nested structures like parentheses in expressions.
*   **State Management:** Keeping track of `curr`, `total`, and `sign` is crucial for correct calculation.
*   **Stack-like Behavior (Implicit):** The recursive calls implicitly use the call stack to manage nested expressions, similar to an explicit stack data structure.
*   **Character Parsing:** Converting character digits to their integer values.

## Common Mistakes
*   **Incorrectly handling the global `index`:** Modifying `index` within recursive calls without proper management can lead to out-of-bounds errors or skipping characters.
*   **Forgetting to update `total` before resetting `curr` on encountering operators:** This leads to losing the value of the number just parsed.
*   **Mishandling the sign when encountering parentheses:** The sign before an opening parenthesis must be applied to the result of the sub-expression.
*   **Not returning the correct value on a closing parenthesis:** The `total` accumulated *before* the closing parenthesis needs to be combined with the evaluated sub-expression.
*   **Integer overflow for very large numbers:** While not explicitly stated as a constraint, it's a general consideration for arithmetic problems.

## Complexity Analysis
- Time: O(N) - reason: Each character in the string `s` is visited and processed a constant number of times. The recursive calls effectively traverse the expression tree, and the total work done is proportional to the length of the string.
- Space: O(N) - reason: In the worst case, the recursion depth can be proportional to the number of nested parentheses, which can be up to N/2 (e.g., "((((...))))"). This leads to a space complexity related to the depth of the call stack.

## Commented Code
```java
class Solution {
    // Global index to keep track of the current position in the string across recursive calls.
    int index = 0;

    public int calculate(String s) {
        // curr: stores the current number being parsed.
        int curr = 0;
        // total: stores the running sum of the expression.
        int total = 0;
        // sign: stores the current sign (1 for positive, -1 for negative).
        int sign = 1;

        // Loop through the string until the end of the string is reached.
        while (index < s.length()) {
            // Get the current character and advance the index.
            char c = s.charAt(index++);

            // If the character is a space, ignore it and continue to the next character.
            if (c == ' ') {
                continue;
            }
            // If the character is an opening parenthesis, it signifies a sub-expression.
            // Recursively call calculate to evaluate the sub-expression.
            // The result of the sub-expression becomes the current number 'curr'.
            else if (c == '(') {
                curr = calculate(s);
            }
            // If the character is a closing parenthesis, it marks the end of the current expression (or sub-expression).
            // Return the total accumulated so far, plus the current number 'curr' multiplied by its sign.
            else if (c == ')') {
                return total + (sign * curr);
            }
            // If the character is an operator ('+' or '-').
            else if (c == '+' || c == '-') {
                // First, add the previously parsed number 'curr' (with its sign) to the total.
                total = total + (sign * curr);
                // Reset 'curr' to 0 to start parsing the next number.
                curr = 0;
                // Update the sign based on the current operator.
                sign = (c == '+') ? 1 : -1;
            }
            // If the character is a digit.
            else {
                // Build the current number by multiplying the existing 'curr' by 10 and adding the new digit's value.
                curr = (curr * 10) + (c - '0');
            }
        }
        // After the loop finishes (end of the string), add the last parsed number 'curr' (with its sign) to the total.
        return total + (sign * curr);
    }
}
```

## Interview Tips
*   **Explain the recursive approach clearly:** Emphasize how recursion naturally handles nested parentheses by treating them as independent sub-problems.
*   **Walk through an example with parentheses:** Use an example like "1 + (2 - 3)" to demonstrate how the `index` and `sign` are managed during recursive calls and returns.
*   **Discuss the role of the global `index`:** Explain why a global or passed-by-reference index is necessary to maintain state across recursive calls.
*   **Be prepared to discuss alternative approaches:** Briefly mention how an explicit stack could be used to solve this problem iteratively, especially if asked for an iterative solution.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the need for handling parentheses.
- [ ] Grasp the recursive approach for sub-expressions.
- [ ] Understand how `curr`, `total`, and `sign` are updated.
- [ ] Trace execution with examples involving parentheses.
- [ ] Analyze time and space complexity.
- [ ] Practice writing the code from scratch.

## Similar Problems
*   Basic Calculator II
*   Basic Calculator III
*   Expression Add Operators
*   Different Ways to Add Parentheses

## Tags
`Recursion` `String` `Stack` `Math`
