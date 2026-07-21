# Basic Calculator

**Difficulty:** Hard  
**Language:** Javascript  
**Tags:** `Math` `String` `Stack` `Recursion`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (javascript)

```javascript
class Solution {
    int index = 0;
    public int calculate(String s) {
        int total=0, curr=0, sign = 1;
        while(index < s.length()){
            char c =  s.charAt(index);
            index++;
            if(c>='0' && c <= '9')  curr = (curr*10) + (c-'0');
            else if(c=='(') curr = calculate(s);
            else if(c==')') return total + (sign*curr); // kyonki function call hogya hai naye open bracket p
            else if(c=='+' || c=='-'){
                total += sign*curr;
                curr=0;
                sign = c=='+' ? 1 : -1;
            } 
        }
        return total + (sign*curr);
    }
}
```

---

---
## Quick Revision
Evaluate an arithmetic expression with parentheses, plus, and minus operators.
The solution uses a recursive approach to handle nested expressions.

## Intuition
The key insight is that the expression can be broken down into smaller subexpressions enclosed in parentheses. By recursively evaluating these subexpressions, we can simplify the main expression.

## Algorithm
1. Initialize `total` and `curr` variables to 0.
2. Iterate through the input string `s`.
3. If the current character is a digit, multiply `curr` by 10 and add the digit's value.
4. If the current character is an opening parenthesis, recursively call `calculate(s)` and store the result in `curr`.
5. If the current character is a closing parenthesis, return `total + (sign*curr)`.
6. If the current character is a plus or minus operator, update `total` and `sign`, and reset `curr` to 0.
7. After iterating through the entire string, return `total + (sign*curr)`.

## Concept to Remember
• **Operator Precedence**: The order in which operators are evaluated matters.
• **Recursive Subproblems**: Breaking down a problem into smaller subproblems helps solve it more efficiently.
• **Contextual Evaluation**: Understanding the context of each character's value is crucial for accurate evaluation.

## Common Mistakes
• Failing to handle parentheses correctly, leading to incorrect operator precedence.
• Not resetting `curr` after encountering an operator, causing incorrect calculations.
• Incorrectly updating `total` and `sign`, resulting in wrong intermediate results.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the input string once, where n is the length of the string.
- Space: O(n) - reason: In the worst case, we need to store the entire recursive call stack.

## Commented Code
```javascript
class Solution {
    int index = 0;

    public int calculate(String s) {
        // Initialize total and curr variables
        int total = 0, curr = 0, sign = 1;
        
        // Iterate through the input string
        while (index < s.length()) {
            char c = s.charAt(index);
            index++;
            
            // Handle digit
            if (c >= '0' && c <= '9') 
                curr = (curr * 10) + (c - '0');
            else if (c == '(') 
                // Recursively evaluate subexpression
                curr = calculate(s);
            else if (c == ')') 
                return total + (sign * curr); // Evaluate current expression and add result to total
            else if (c == '+' || c == '-') {
                // Update total and sign, reset curr
                total += sign * curr;
                curr = 0;
                sign = c == '+' ? 1 : -1;
            }
        }
        
        return total + (sign * curr); // Final evaluation of the expression
    }
}
```

## Interview Tips
• Practice breaking down complex problems into smaller subproblems.
• Pay attention to edge cases and corner scenarios.
• Use recursive approaches when applicable, but be mindful of potential stack overflow issues.

## Revision Checklist
- [ ] Review operator precedence rules.
- [ ] Test edge cases with multiple parentheses.
- [ ] Implement the solution from scratch without peeking at existing code.

## Similar Problems
• Evaluate Reverse Polish Notation (LeetCode 772)
• Basic Calculator II (LeetCode 1023)
• Expression Add Operators (LeetCode 608)

## Tags
`Array`, `Hash Map`, `String`, `Recursion`
