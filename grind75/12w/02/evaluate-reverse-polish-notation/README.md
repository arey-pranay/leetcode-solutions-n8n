# Evaluate Reverse Polish Notation

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Math` `Stack`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for(String s : tokens){
            switch(s){
                case "+":
                    st.push(st.pop()+st.pop());
                    break;
                case "-":
                    st.push(-(st.pop()-st.pop()));
                    break;
                case "*":
                    st.push(st.pop()*st.pop());
                    break;
                case "/":
                    int a=st.pop();int b=st.pop(); st.push(b/a);
                    break;
                default:
                    st.push(Integer.valueOf(s));
                    break;
            }
        }
        return st.pop();
    }
}
```

---

---
## Quick Revision
This problem asks to evaluate an arithmetic expression given in Reverse Polish Notation (RPN).
We can solve this using a stack to keep track of operands and perform operations as we encounter them.

## Intuition
Reverse Polish Notation (RPN), also known as postfix notation, places operators after their operands. This structure naturally lends itself to a stack-based evaluation. When we encounter a number, we push it onto the stack. When we encounter an operator, we know that the last two numbers pushed onto the stack are its operands. We pop these two operands, perform the operation, and push the result back onto the stack. By the end, the single remaining element on the stack will be the final result.

## Algorithm
1. Initialize an empty stack to store integers (operands).
2. Iterate through each token in the input array `tokens`.
3. For each token:
    a. If the token is an operator (`+`, `-`, `*`, `/`):
        i. Pop the top two elements from the stack. Let the first popped element be `operand2` and the second popped element be `operand1`.
        ii. Perform the operation specified by the token:
            - If `+`: `result = operand1 + operand2`
            - If `-`: `result = operand1 - operand2`
            - If `*`: `result = operand1 * operand2`
            - If `/`: `result = operand1 / operand2` (integer division, truncating towards zero). Note the order of operands for subtraction and division is crucial.
        iii. Push the `result` back onto the stack.
    b. If the token is a number:
        i. Convert the token string to an integer.
        ii. Push the integer onto the stack.
4. After processing all tokens, the stack will contain a single element, which is the final evaluated result. Pop and return this element.

## Concept to Remember
*   **Stacks**: LIFO (Last-In, First-Out) data structure, essential for processing RPN.
*   **Reverse Polish Notation (RPN)**: An arithmetic expression format where operators follow their operands.
*   **Operator Precedence/Associativity**: Not directly applicable here as RPN inherently defines evaluation order, but understanding how RPN avoids these issues is key.
*   **Integer Division**: Understanding how division truncates towards zero in Java is important for the `/` operator.

## Common Mistakes
*   **Incorrect Operand Order**: For subtraction and division, popping `a` then `b` means `b` was pushed before `a`. The operation should be `b op a`, not `a op b`.
*   **Handling Division by Zero**: While not explicitly stated in the problem constraints for this specific LeetCode problem, in a real-world scenario, division by zero would need to be handled.
*   **Integer Overflow**: For very large intermediate results, integer overflow could occur. The problem constraints usually mitigate this, but it's a general consideration.
*   **Type Conversion Errors**: Incorrectly converting string tokens to integers.
*   **Forgetting to Push Results**: Not pushing the result of an operation back onto the stack, leading to insufficient operands for subsequent operations.

## Complexity Analysis
- Time: O(N) - reason: We iterate through each of the N tokens exactly once. Stack operations (push, pop) take O(1) time.
- Space: O(N) - reason: In the worst case, if all tokens are numbers, the stack could store all N tokens before any operations are performed.

## Commented Code
```java
class Solution {
    public int evalRPN(String[] tokens) {
        // Initialize a stack to store operands (numbers).
        Stack<Integer> st = new Stack<>();

        // Iterate through each token in the input array.
        for(String s : tokens){
            // Use a switch statement to handle different token types.
            switch(s){
                // If the token is the addition operator.
                case "+":
                    // Pop the top two operands, add them, and push the result.
                    // The second popped element is the first operand in standard notation.
                    st.push(st.pop()+st.pop());
                    break;
                // If the token is the subtraction operator.
                case "-":
                    // Pop the top two operands. The second popped element is the first operand.
                    // The expression is (second_popped - first_popped).
                    // We negate the result of (first_popped - second_popped) to achieve this.
                    st.push(-(st.pop()-st.pop()));
                    break;
                // If the token is the multiplication operator.
                case "*":
                    // Pop the top two operands, multiply them, and push the result.
                    st.push(st.pop()*st.pop());
                    break;
                // If the token is the division operator.
                case "/":
                    // Pop the top two operands. The second popped element is the dividend.
                    int a=st.pop(); // This is the divisor (second operand in standard notation).
                    int b=st.pop(); // This is the dividend (first operand in standard notation).
                    // Perform integer division and push the result.
                    st.push(b/a);
                    break;
                // If the token is not an operator, it must be a number.
                default:
                    // Convert the string token to an integer and push it onto the stack.
                    st.push(Integer.valueOf(s));
                    break;
            }
        }
        // After processing all tokens, the final result is the only element left on the stack.
        return st.pop();
    }
}
```

## Interview Tips
*   **Clarify Division Behavior**: Ask the interviewer about the expected behavior for division, specifically regarding truncation (towards zero, towards negative infinity) and division by zero. The problem statement usually implies standard integer division.
*   **Trace with an Example**: Be prepared to walk through an example like `["2", "1", "+", "3", "*"]` step-by-step, showing how the stack changes.
*   **Explain the Stack's Role**: Clearly articulate why a stack is the appropriate data structure for evaluating RPN.
*   **Consider Edge Cases**: Think about cases like a single number as input, or an expression with only two numbers and one operator.

## Revision Checklist
- [ ] Understand RPN structure.
- [ ] Implement stack operations correctly.
- [ ] Handle operand order for subtraction and division.
- [ ] Correctly convert string numbers to integers.
- [ ] Analyze time and space complexity.
- [ ] Test with various examples.

## Similar Problems
*   Basic Calculator
*   Basic Calculator II
*   Expression Add Operators
*   Min Stack

## Tags
`Array` `Stack` `Math` `String`
