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
        Stack<Integer> st = new Stack<Integer>();
        for (String s : tokens) {
            switch (s) {
                case "*":
                    st.push(st.pop() *  st.pop());
                    break;
                case "+":
                    st.push(st.pop() + st.pop());
                    break;
                case "-":
                    st.push( - (st.pop() - st.pop()));
                    break;
                case "/":
                    int a = st.pop();
                    int b = st.pop();
                    st.push(b / a);
                    break;
                default:
                    st.push(Integer.valueOf(s));
                    break;
            }
        }
        return st.pop();
    }
}
// -4, 5
// 3 - 3
```

---

---
## Quick Revision
This problem asks to evaluate an arithmetic expression given in Reverse Polish Notation (RPN).
We can solve this using a stack to keep track of operands and perform operations as they appear.

## Intuition
Reverse Polish Notation (RPN), also known as postfix notation, places operators *after* their operands. This structure naturally lends itself to a stack-based evaluation. When we encounter a number, we push it onto the stack. When we encounter an operator, we know that the last two numbers pushed onto the stack are its operands. We pop these two operands, perform the operation, and push the result back onto the stack. This process continues until all tokens are processed, and the final result will be the only element left on the stack.

## Algorithm
1. Initialize an empty stack that will store integers (operands).
2. Iterate through each `token` in the input array `tokens`.
3. For each `token`:
    a. If the `token` is an operator (`+`, `-`, `*`, `/`):
        i. Pop the top two elements from the stack. Let the first popped element be `operand2` and the second popped element be `operand1`.
        ii. Perform the operation:
            - If `+`: `result = operand1 + operand2`
            - If `-`: `result = operand1 - operand2`
            - If `*`: `result = operand1 * operand2`
            - If `/`: `result = operand1 / operand2` (Note: integer division, and the order matters for subtraction and division).
        iii. Push the `result` back onto the stack.
    b. If the `token` is a number:
        i. Convert the `token` string to an integer.
        ii. Push the integer onto the stack.
4. After iterating through all `tokens`, the stack will contain a single element, which is the final result of the RPN expression.
5. Pop and return this final result.

## Concept to Remember
*   **Stacks**: LIFO (Last-In, First-Out) data structure, crucial for processing RPN due to its inherent order of operations.
*   **Reverse Polish Notation (RPN)**: An arithmetic expression format where operators follow their operands, simplifying evaluation without parentheses.
*   **Operator Precedence and Associativity**: While not explicitly tested here due to RPN's structure, understanding these concepts is fundamental to expression evaluation.
*   **Integer Division**: Be mindful of how division is handled, especially in languages like Java where it truncates towards zero.

## Common Mistakes
*   **Incorrect Operand Order**: For subtraction and division, popping `a` then `b` means `b` is the first operand and `a` is the second. Incorrectly using `a - b` or `a / b` instead of `b - a` or `b / a` will lead to wrong results.
*   **Handling Division by Zero**: Although not explicitly required by the problem constraints in LeetCode, in a real-world scenario, division by zero must be handled.
*   **Type Conversion Errors**: Forgetting to convert string numbers to integers before pushing them onto the stack.
*   **Stack Underflow**: Attempting to pop from an empty stack, which can happen if the input RPN expression is malformed.
*   **Integer Overflow/Underflow**: While less common with typical LeetCode constraints, intermediate or final results might exceed the `int` range.

## Complexity Analysis
- Time: O(N) - reason: We iterate through each token in the input array exactly once. Stack operations (push, pop) take O(1) time.
- Space: O(N) - reason: In the worst case, if the RPN expression consists only of numbers, the stack will store all N numbers before any operations are performed.

## Commented Code
```java
class Solution {
    public int evalRPN(String[] tokens) {
        // Initialize a stack to store integer operands.
        Stack<Integer> st = new Stack<Integer>();
        // Iterate through each token in the input array.
        for (String s : tokens) {
            // Use a switch statement to handle different tokens.
            switch (s) {
                // If the token is a multiplication operator.
                case "*":
                    // Pop the top two operands, multiply them, and push the result.
                    // Note: The order of popping matters for subtraction and division.
                    // Here, st.pop() is operand2, and the next st.pop() is operand1.
                    st.push(st.pop() *  st.pop());
                    break;
                // If the token is an addition operator.
                case "+":
                    // Pop the top two operands, add them, and push the result.
                    st.push(st.pop() + st.pop());
                    break;
                // If the token is a subtraction operator.
                case "-":
                    // Pop the top two operands. The first popped is operand2, the second is operand1.
                    // Perform operand1 - operand2 and push the result.
                    // The expression `st.pop() - st.pop()` would be operand2 - operand1.
                    // To get operand1 - operand2, we negate the result of (operand2 - operand1).
                    int operand2 = st.pop(); // This is the second operand in the expression
                    int operand1 = st.pop(); // This is the first operand in the expression
                    st.push(operand1 - operand2); // Correct subtraction order
                    break;
                // If the token is a division operator.
                case "/":
                    // Pop the top two operands. The first popped is operand2, the second is operand1.
                    // Perform operand1 / operand2 and push the result.
                    int divisor = st.pop(); // This is the second operand (divisor)
                    int dividend = st.pop(); // This is the first operand (dividend)
                    st.push(dividend / divisor); // Correct division order for RPN
                    break;
                // If the token is not an operator, it must be a number.
                default:
                    // Convert the string token to an integer and push it onto the stack.
                    st.push(Integer.valueOf(s));
                    break;
            }
        }
        // After processing all tokens, the stack will contain the final result.
        // Pop and return this result.
        return st.pop();
    }
}
```

## Interview Tips
*   **Clarify Edge Cases**: Ask about potential issues like empty input, malformed RPN, division by zero, or integer overflow.
*   **Explain the Stack's Role**: Clearly articulate why a stack is the ideal data structure for evaluating RPN.
*   **Trace an Example**: Walk through a simple RPN expression (e.g., `["2", "1", "+", "3", "*"]`) on a whiteboard or paper to demonstrate your understanding of the algorithm.
*   **Pay Attention to Order**: Emphasize that for subtraction and division, the order of popping operands is critical and must be handled correctly.

## Revision Checklist
- [ ] Understand the definition of Reverse Polish Notation (RPN).
- [ ] Implement stack operations (push, pop).
- [ ] Correctly handle the order of operands for subtraction and division.
- [ ] Convert string numbers to integers.
- [ ] Consider time and space complexity.
- [ ] Test with various RPN expressions, including those with negative numbers and division.

## Similar Problems
*   Basic Calculator
*   Basic Calculator II
*   Expression Add Operators
*   Min Stack

## Tags
`Array` `Stack` `Math` `String`
