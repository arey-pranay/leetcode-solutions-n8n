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
    int idx=0; // this index traverse the string in one pass, between different level of recursion
    public int calculate(String s) {
       int res = 0, num = 0, sign = 1;
        while (idx < s.length()) {
            char c = s.charAt(idx++);
            if (c >= '0' && c <= '9') num = num * 10 + c - '0';
            else if (c == '(') num = calculate(s); // ( is start of a new sub-problem, Let recursion solve the sub-problem
            else if (c == ')') return res + (sign * num);
            else if (c == '+' || c == '-') { // only when we meet a new sign, we know a while number has been read
                res = res + (sign * num);
                num = 0;
                sign = c == '-' ? -1 : 1;
            }
        }
        return res + (sign * num); // last number is not processed yet
    }
}

// class Solution {
//     public int calculate(String s) {
//         int n = s.length();
//         Stack<String> st = new Stack<>();
//         for(int i=0;i<n;i++){
//             char c= s.charAt(i);
//             switch(c){
//                 case ' ':        // space gayi gadhhe me 
//                     break;
//                 case '(':
//                     st.push(String.valueOf(c));
//                     break;
//                 case ')':
//                     StringBuilder sb = new StringBuilder("");  
//                     while(!st.peek().equals("(")) sb.insert(0,st.pop());
                    
//                     st.pop();
//                     String num2 = String.valueOf(calcNum(sb.toString()));
//                     if(st.isEmpty()) st.push(num2);
//                     else if(st.peek().equals("+")){
//                         st.pop(); 
//                         st.push(String.valueOf( (Integer.parseInt(num2) + (Integer.parseInt(st.pop())))));
//                     }
//                     else if(st.peek().equals("-")){
//                         st.pop();
//                         if(st.isEmpty() || st.peek().equals("(")) st.push(String.valueOf(-Integer.parseInt(num2)) );
//                         else st.push( String.valueOf( Integer.parseInt(st.pop()) - Integer.parseInt(num2)) );
//                     }
//                     else st.push(String.valueOf(num2));
                    
//                     break;
//                 default:
//                     if(Character.isDigit(c)){
//                         StringBuilder sb2 = new StringBuilder();
//                         while(i < s.length() && Character.isDigit(s.charAt(i))) sb2.append(s.charAt(i++));
//                         i--;
//                         String num = sb2.toString();
//                         if(st.isEmpty()) st.push(num);
//                         else if(st.peek().equals("+")){
//                             st.pop(); 
//                             st.push(String.valueOf( (Integer.parseInt(num) + (Integer.parseInt(st.pop())))));
//                         }
//                         else if(st.peek().equals("-")){
//                             st.pop();
//                             if(st.isEmpty() || st.peek().equals("(")) st.push("-"+num);
//                             else st.push( String.valueOf( Integer.parseInt(st.pop()) - Integer.parseInt(num)) );
//                         }
//                         else st.push(String.valueOf(num));
//                     }else st.push(String.valueOf(c));
//                     break;   
//             }         
//         }
//         StringBuilder temp = new StringBuilder("");        
//         while(!st.isEmpty()) temp.insert(0,st.pop());
//         return calcNum(temp.toString());
//     }
// public int calcNum(String s){
//     int ans = 0;
//     char op = ' ';

//     for(int i=0;i<s.length();i++){
//         if(s.charAt(i)=='-'){
//             if(i==0 || s.charAt(i-1)=='+' || s.charAt(i-1)=='-'){
//                 StringBuilder sb = new StringBuilder("-");
//                 i++;
//                 while(i<s.length() && Character.isDigit(s.charAt(i))) sb.append(s.charAt(i++));
//                 i--;

//                 int curr = Integer.parseInt(sb.toString());

//                 if(op=='-') ans -= curr;
//                 else if(op=='+') ans += curr;
//                 else ans = curr;
//             }
//             else{
//                 op='-';
//             }
//         }
//         else if(s.charAt(i)=='+'){
//             op='+';
//         }
//         else{
//             StringBuilder sb = new StringBuilder();

//             while(i<s.length() && Character.isDigit(s.charAt(i)))
//                 sb.append(s.charAt(i++));

//             i--;

//             int curr = Integer.parseInt(sb.toString());

//             if(op=='-') ans -= curr;
//             else if(op=='+') ans += curr;
//             else ans = curr;
//         }
//     }

//     return ans;
// }
// }

// // "2-4-(8+2-6+(8+4-(1)+8-10))"


```

---

---
## Quick Revision
This problem asks to evaluate a string expression containing integers, '+', '-', '(', ')', and spaces. The solution uses recursion to handle nested parentheses.

## Intuition
The core idea is to process the expression character by character. When we encounter a digit, we build the current number. When we see a '+', '-', or ')', it signifies the end of a number, and we should apply the previous sign to the accumulated number and add it to the result. Parentheses indicate a sub-expression that needs to be evaluated independently. Recursion is a natural fit for handling these nested sub-expressions.

## Algorithm
1. Initialize `res` (result), `num` (current number), and `sign` (current sign, 1 for positive, -1 for negative) to 0, 0, and 1 respectively.
2. Iterate through the input string `s` using an index `idx`.
3. If the current character `c` is a digit:
    a. Update `num` by `num = num * 10 + (c - '0')`.
4. If `c` is '(':
    a. Increment `idx` to move past '('.
    b. Recursively call `calculate(s)` to evaluate the sub-expression within the parentheses.
    c. Assign the result of the recursive call to `num`.
5. If `c` is ')':
    a. Add the last `num` (multiplied by its `sign`) to `res`.
    b. Return `res`. This signifies the end of a sub-expression.
6. If `c` is '+' or '-':
    a. Add the current `num` (multiplied by its `sign`) to `res`.
    b. Reset `num` to 0.
    c. Update `sign` to -1 if `c` is '-' or 1 if `c` is '+'.
7. After the loop finishes, add the last `num` (multiplied by its `sign`) to `res`.
8. Return `res`.

## Concept to Remember
*   **Recursion:** Essential for handling nested structures like parentheses.
*   **State Management:** Keeping track of the current number, sign, and accumulated result is crucial.
*   **String Parsing:** Iterating through the string and identifying different token types (digits, operators, parentheses).
*   **Operator Precedence (Implicit):** The recursive approach naturally handles the precedence of operations within parentheses.

## Common Mistakes
*   **Incorrectly handling the last number:** Forgetting to add the final `num` to `res` after the loop.
*   **Off-by-one errors with index `idx`:** Not incrementing `idx` correctly or incrementing it too much.
*   **Mismanaging the `sign`:** Not resetting `sign` or applying it incorrectly when encountering operators.
*   **Not handling spaces:** The provided solution implicitly skips spaces by only acting on digits, operators, and parentheses. A more robust solution might explicitly check for and skip spaces.
*   **Stack Overflow for deeply nested parentheses:** While not a direct mistake in logic, extremely deep nesting could lead to stack overflow.

## Complexity Analysis
- Time: O(N) - reason: Each character in the string is visited and processed at most a constant number of times. The recursion depth is at most N/2 (for cases like `((((...))))`).
- Space: O(N) - reason: In the worst case, the recursion depth can be proportional to the length of the string (e.g., `((((...))))`), leading to O(N) space complexity for the call stack.

## Commented Code
```java
class Solution {
    int idx = 0; // This index traverses the string in one pass, between different levels of recursion. It's a class member to maintain state across recursive calls.

    public int calculate(String s) {
        int res = 0; // 'res' stores the accumulated result of the current expression/sub-expression.
        int num = 0; // 'num' stores the current number being parsed.
        int sign = 1; // 'sign' stores the sign of the current number (1 for positive, -1 for negative).

        while (idx < s.length()) { // Loop through the string character by character.
            char c = s.charAt(idx++); // Get the current character and advance the index.

            if (c >= '0' && c <= '9') { // If the character is a digit:
                num = num * 10 + c - '0'; // Build the current number by appending the digit.
            } else if (c == '(') { // If the character is an opening parenthesis:
                // This signifies the start of a sub-expression.
                // Recursively call calculate to solve this sub-problem.
                // The result of the sub-expression becomes the current 'num'.
                num = calculate(s);
            } else if (c == ')') { // If the character is a closing parenthesis:
                // This signifies the end of the current sub-expression.
                // Add the last parsed number (with its sign) to the result.
                res = res + (sign * num);
                // Return the result of this sub-expression. The caller (parent recursion level) will use this value.
                return res;
            } else if (c == '+' || c == '-') { // If the character is an operator ('+' or '-'):
                // This means a complete number has just been parsed (or a sub-expression evaluated).
                // Add the previously parsed number ('num') with its 'sign' to the 'res'.
                res = res + (sign * num);
                // Reset 'num' to 0 to start parsing the next number.
                num = 0;
                // Update the 'sign' for the next number based on the current operator.
                sign = (c == '-') ? -1 : 1;
            }
            // Spaces are implicitly skipped because they don't fall into any of the above conditions.
        }
        // After the loop finishes, there might be a pending number that hasn't been added to 'res' yet.
        // Add the last number (with its sign) to the final result.
        return res + (sign * num);
    }
}
```

## Interview Tips
*   **Explain the recursive approach:** Clearly articulate why recursion is suitable for handling nested parentheses.
*   **Trace an example:** Walk through a simple example like "1 + (2 - 3)" or a more complex one to demonstrate your understanding of the state changes (`res`, `num`, `sign`).
*   **Discuss edge cases:** Mention how you handle empty strings, strings with only numbers, or strings with only parentheses (though the problem constraints might limit these).
*   **Clarify the global index:** Explain why a class-level `idx` is used instead of passing it as a parameter in the recursive calls, and how it ensures a single pass.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the role of parentheses in changing the order of operations.
- [ ] Recognize that recursion is a natural fit for nested structures.
- [ ] Implement the logic for parsing numbers.
- [ ] Implement the logic for handling '+' and '-' operators.
- [ ] Implement the recursive call for '(' and the return for ')'.
- [ ] Ensure the final number is added to the result.
- [ ] Consider space and time complexity.
- [ ] Practice tracing examples.

## Similar Problems
*   Basic Calculator II (LeetCode 224)
*   Basic Calculator III (LeetCode 772)
*   Expression Add Operators (LeetCode 282)
*   Evaluate Reverse Polish Notation (LeetCode 150)

## Tags
`Recursion` `String` `Stack`
