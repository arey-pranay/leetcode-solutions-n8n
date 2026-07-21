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
The problem is to implement a basic calculator that can parse a string expression and evaluate it. We solve this by using a recursive approach with a stack to keep track of the operators and operands.

## Intuition
The key insight here is that we can use the operator precedence rules to guide our parsing process. By evaluating subexpressions within parentheses first, we ensure that we get the correct order of operations.

## Algorithm

1. Initialize an index pointer at the beginning of the string.
2. As we iterate through the string, check each character and apply the following rules:
	* If it's a digit, multiply it by 10 with the current value (to handle multi-digit numbers).
	* If it's an opening parenthesis, recursively call `calculate` on the substring within the parentheses.
	* If it's a closing parenthesis, return the total plus the sign times the current value.
	* If it's an operator (+ or -), update the total with the sign times the current value, reset the current value to 0, and update the sign accordingly.
3. After processing the entire string, return the final total.

## Concept to Remember
• **Operator precedence**: The rules governing the order in which operators are applied (e.g., parentheses first, then multiplication and division, then addition and subtraction).
• **Recursive function calls**: Using a recursive approach to break down complex problems into simpler subproblems.
• **Stack data structure**: Using a stack to keep track of operators and operands.

## Common Mistakes
• **Incorrect operator precedence handling**: Failing to apply the correct rules for operator order (e.g., not evaluating parentheses first).
• **Inconsistent sign handling**: Incorrectly updating the sign value when encountering operators.
• **Overcomplicating the solution**: Using unnecessary data structures or logic, such as separate variables for each operand.

## Complexity Analysis
- Time: O(n) - where n is the length of the input string. This is because we only iterate through the string once.
- Space: O(n) - in the worst case, we may need to recursively call `calculate` on the entire substring within parentheses.

## Commented Code

```javascript
class Solution {
    int index = 0;

    public int calculate(String s) {
        // Initialize total and current value to 0.
        int total = 0;
        int curr = 0; // Current number being processed.
        int sign = 1; // Sign of the current number (positive or negative).

        while(index < s.length()) {
            char c = s.charAt(index); // Get the current character.

            index++; // Move to the next character.

            if(c >= '0' && c <= '9') { // If it's a digit, multiply by 10 with the current value.
                curr = (curr * 10) + (c - '0');
            } else if(c == '(') {
                // If it's an opening parenthesis, recursively call calculate on the substring within parentheses.
                curr = calculate(s);
            } else if(c == ')') {
                // Return the total plus the sign times the current value (when closing parenthesis encountered).
                return total + (sign * curr);
            } else if(c == '+' || c == '-') {
                // Update the total with the sign times the current value and reset the current value to 0.
                total += sign * curr;
                curr = 0;
                sign = c == '+' ? 1 : -1; // Update the sign accordingly.
            }
        }

        // After processing the entire string, return the final total plus the sign times the current value.
        return total + (sign * curr);
    }
}
```

## Interview Tips
• **Pay attention to edge cases**: Ensure you handle corner cases like empty strings or malformed input correctly.
• **Use a consistent approach**: Stick to the recursive approach and avoid overcomplicating the solution with unnecessary logic.
• **Test your solution thoroughly**: Verify that your code works for different inputs and edge cases.

## Revision Checklist
- [ ] Understand operator precedence rules and apply them correctly.
- [ ] Use a recursive function call to handle subexpressions within parentheses.
- [ ] Keep track of signs and update the total accordingly.
- [ ] Test the solution with various inputs, including edge cases.
