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
This problem asks to evaluate a string expression containing integers, '+', '-', '(', ')', and spaces. The solution uses recursion to handle nested parentheses and a state-tracking approach for signs and current numbers.

## Intuition
The core challenge lies in handling parentheses, which introduce nested sub-expressions that need to be evaluated independently before their results are incorporated into the main expression. Recursion is a natural fit for this, as a sub-expression within parentheses can be treated as a smaller instance of the same problem. For the linear parts of the expression, we need to keep track of the current number being built and the sign preceding it. When we encounter a '+', '-', or ')', we finalize the current number with its sign and add it to the running total.

## Algorithm
1. Initialize `index` to 0 to keep track of the current position in the string `s`.
2. Define a recursive helper function `calculate(String s)`:
   a. Initialize `curr` (current number being parsed) to 0.
   b. Initialize `total` (running sum) to 0.
   c. Initialize `sign` (sign of the current number) to 1 (positive).
   d. Iterate through the string `s` using the global `index`:
      i. Get the current character `c` and increment `index`.
      ii. If `c` is a space, skip it.
      iii. If `c` is '(', recursively call `calculate(s)` to evaluate the sub-expression within parentheses. The result of this recursive call becomes the `curr` value.
      iv. If `c` is ')', it signifies the end of a sub-expression. Return the `total` accumulated so far plus the `curr` value multiplied by its `sign`. This result is then used by the calling recursive instance.
      v. If `c` is '+' or '-', it means the current number `curr` is complete. Add `sign * curr` to `total`. Reset `curr` to 0 and update `sign` based on whether `c` is '+' (sign = 1) or '-' (sign = -1).
      vi. If `c` is a digit, update `curr` by appending the digit: `curr = (curr * 10) + (c - '0')`.
   e. After the loop finishes (end of the string or a sub-expression), return the final `total` plus `sign * curr`.
3. The initial call to `calculate(s)` will return the result of the entire expression.

## Concept to Remember
*   **Recursion:** Essential for handling nested structures like parentheses, breaking down a problem into smaller, self-similar subproblems.
*   **Stack-like Behavior (Implicit):** The recursive calls implicitly use the call stack to manage the state of nested expressions.
*   **State Management:** Keeping track of the current number, the sign, and the accumulated total is crucial for correct evaluation.
*   **Parsing:** Converting character digits into integer values and handling operators.

## Common Mistakes
*   **Incorrectly handling the global `index`:** Modifying `index` within recursive calls without proper management can lead to out-of-bounds errors or skipping characters.
*   **Forgetting to finalize the last number:** If the expression ends with a number, it needs to be added to the total after the loop.
*   **Mismanaging signs:** Incorrectly applying the `sign` to the `curr` value when adding to `total` or when encountering operators.
*   **Not handling spaces:** Forgetting to skip space characters will lead to parsing errors.
*   **Incorrect base case for recursion:** The ')' character acts as the return point for a recursive call, and this return value must be correctly incorporated.

## Complexity Analysis
- Time: O(N) - Each character in the string is visited and processed a constant number of times. The recursion depth can be at most N/2 in the worst case (e.g., "((((...))))"), but each character is still processed once per level of recursion it's part of.
- Space: O(N) - In the worst case, the recursion depth can be proportional to the number of nested parentheses, which can be up to N/2. This leads to O(N) space complexity due to the call stack.

## Commented Code
```java
class Solution {
    // Global index to keep track of the current position in the string across recursive calls.
    int index = 0;

    public int calculate(String s) {
        // curr: stores the current number being parsed.
        int curr = 0;
        // total: stores the accumulated result of the expression so far.
        int total = 0;
        // sign: stores the sign of the current number (1 for positive, -1 for negative).
        int sign = 1;

        // Iterate through the string using the global index.
        while (index < s.length()) {
            // Get the current character and advance the index.
            char c = s.charAt(index++);

            // If the character is a space, skip it and continue to the next iteration.
            if (c == ' ') {
                continue;
            }
            // If the character is an opening parenthesis, it signifies a sub-expression.
            // Recursively call calculate to evaluate this sub-expression.
            // The result of the sub-expression becomes the current number 'curr'.
            else if (c == '(') {
                curr = calculate(s);
            }
            // If the character is a closing parenthesis, it marks the end of a sub-expression.
            // Return the total accumulated so far, plus the current number 'curr' multiplied by its sign.
            // This value will be used by the calling recursive function.
            else if (c == ')') {
                return total + (sign * curr);
            }
            // If the character is a '+' or '-', it means the current number 'curr' is complete.
            // Add the completed number (sign * curr) to the total.
            // Reset 'curr' to 0 to start parsing the next number.
            // Update the 'sign' based on the operator encountered.
            else if (c == '+' || c == '-') {
                total = total + (sign * curr);
                curr = 0;
                sign = (c == '+') ? 1 : -1;
            }
            // If the character is a digit, build the current number.
            // Multiply 'curr' by 10 and add the integer value of the digit.
            else {
                curr = (curr * 10) + (c - '0');
            }
        }
        // After the loop finishes (end of string or sub-expression),
        // add the last parsed number (sign * curr) to the total.
        return total + (sign * curr);
    }
}
```

## Interview Tips
*   **Explain the recursion:** Clearly articulate how recursion handles nested parentheses and why it's a good fit.
*   **Trace an example:** Walk through a simple example like "1 + (2 - 3)" to demonstrate the algorithm's flow, especially the recursive calls and returns.
*   **Discuss the global index:** Explain the necessity and implications of using a global `index` for state management across recursive calls.
*   **Consider edge cases:** Think about empty strings, strings with only numbers, strings with only parentheses, and complex nested structures.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the need for recursion to handle parentheses.
- [ ] Implement the state variables: `curr`, `total`, `sign`.
- [ ] Correctly handle digit parsing.
- [ ] Correctly handle '+' and '-' operators.
- [ ] Correctly handle '(' by making a recursive call.
- [ ] Correctly handle ')' by returning the sub-expression result.
- [ ] Ensure the global `index` is managed properly.
- [ ] Handle the final number after the loop.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Basic Calculator II
*   Basic Calculator III
*   Expression Add Operators
*   Evaluate Reverse Polish Notation

## Tags
`Recursion` `String` `Stack`
