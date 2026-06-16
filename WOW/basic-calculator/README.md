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
This problem asks to evaluate a mathematical expression string containing non-negative integers, '+', '-', '(', ')', and spaces. The solution involves parsing the string and handling operator precedence and parentheses.

## Intuition
The core idea is to process the expression character by character, maintaining the current result, the current number being parsed, and the sign of the next number. When we encounter an opening parenthesis '(', it signifies a sub-expression that needs to be evaluated independently. Recursion is a natural fit here: we can delegate the evaluation of the sub-expression to a recursive call. When we encounter a closing parenthesis ')', it means the current sub-expression is complete, and we should return its calculated value to the caller.

## Algorithm
1. Initialize `res` (result so far), `num` (current number being parsed), and `sign` (sign of the current number, 1 for positive, -1 for negative) to 0, 0, and 1 respectively.
2. Iterate through the input string `s` using an index `idx`.
3. If the current character `c` is a digit:
    a. Update `num` by appending the digit: `num = num * 10 + c - '0'`.
4. If the current character `c` is an opening parenthesis '(':
    a. Increment `idx` to move past '('.
    b. Recursively call `calculate(s)` to evaluate the sub-expression within the parentheses.
    c. Assign the result of the recursive call to `num`. This effectively treats the evaluated sub-expression as a single number.
5. If the current character `c` is a closing parenthesis ')':
    a. Add the last `num` (multiplied by its `sign`) to `res`.
    b. Reset `num` to 0.
    c. Return `res`. This signals the end of the current sub-expression evaluation.
6. If the current character `c` is a '+' or '-':
    a. Add the previously accumulated `num` (multiplied by its `sign`) to `res`. This finalizes the previous number.
    b. Reset `num` to 0.
    c. Update `sign`: if `c` is '-', set `sign` to -1; otherwise, set `sign` to 1.
7. After the loop finishes, there might be a pending `num` that hasn't been added to `res`. Add `sign * num` to `res`.
8. Return `res`.

## Concept to Remember
*   **Recursion:** Breaking down a problem into smaller, self-similar subproblems. The `(` character naturally triggers a recursive call for sub-expressions.
*   **Stack (Implicit):** The call stack in recursion implicitly handles the order of operations and the nesting of parentheses.
*   **State Management:** Keeping track of the current result, the number being parsed, and the sign is crucial for correct calculation.

## Common Mistakes
*   **Incorrectly handling the last number:** Forgetting to add the final `num` to `res` after the loop terminates.
*   **Mishandling signs with parentheses:** Not correctly applying the sign to the result of a parenthesized sub-expression.
*   **Off-by-one errors with index `idx`:** Incorrectly incrementing or decrementing `idx`, leading to skipping characters or processing them multiple times.
*   **Not resetting `num`:** Failing to reset `num` to 0 after it's been added to `res` or after encountering a parenthesis.

## Complexity Analysis
- Time: O(N) - The `idx` traverses the string at most once. Each character is visited a constant number of times. Recursive calls are made for sub-expressions, but the total work across all calls is proportional to the length of the string.
- Space: O(N) - In the worst case (e.g., deeply nested parentheses like `((((...))))`), the recursion depth can be proportional to the length of the string, leading to O(N) space complexity for the call stack.

## Commented Code
```java
class Solution {
    // This index traverses the string in one pass, between different levels of recursion.
    // It's a class member to be accessible across recursive calls without passing it as a parameter.
    int idx = 0;

    public int calculate(String s) {
        // res: stores the accumulated result of the current expression/sub-expression.
        int res = 0;
        // num: stores the current number being parsed.
        int num = 0;
        // sign: stores the sign of the current number (1 for positive, -1 for negative).
        int sign = 1;

        // Iterate through the string character by character.
        while (idx < s.length()) {
            // Get the current character and advance the index for the next iteration.
            char c = s.charAt(idx++);

            // If the character is a digit, build the current number.
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            }
            // If the character is an opening parenthesis, it signifies a sub-problem.
            else if (c == '(') {
                // Recursively call calculate to evaluate the sub-expression within the parentheses.
                // The result of the sub-expression is treated as a single number.
                num = calculate(s);
            }
            // If the character is a closing parenthesis, the current sub-expression is complete.
            else if (c == ')') {
                // Add the last parsed number (with its sign) to the result.
                res = res + (sign * num);
                // Reset num for any subsequent calculations outside this parenthesis.
                num = 0;
                // Return the calculated result of this sub-expression to the caller.
                return res;
            }
            // If the character is a '+' or '-', it means a number has just been fully parsed.
            else if (c == '+' || c == '-') {
                // Add the previously accumulated number (with its sign) to the result.
                res = res + (sign * num);
                // Reset num to start parsing the next number.
                num = 0;
                // Update the sign for the next number.
                sign = (c == '-') ? -1 : 1;
            }
            // If it's a space, we simply ignore it and continue.
            // The idx++ at the beginning of the loop handles moving past spaces.
        }
        // After the loop, there might be a last number that hasn't been added to res.
        // Add this final number (with its sign) to the result.
        return res + (sign * num);
    }
}
```

## Interview Tips
*   **Explain the recursive approach:** Clearly articulate why recursion is suitable for handling nested parentheses and sub-expressions.
*   **Trace an example:** Walk through a simple example like "1 + (2 - 3)" or "2-(5-6)" to demonstrate how your `idx`, `res`, `num`, and `sign` variables change.
*   **Discuss edge cases:** Mention how you handle empty strings, strings with only numbers, strings with only parentheses, and strings with leading/trailing spaces.
*   **Clarify state management:** Emphasize how `res`, `num`, and `sign` are used to maintain the calculation state across characters and recursive calls.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the need for handling parentheses and operator precedence.
- [ ] Grasp the recursive approach for sub-expressions.
- [ ] Implement the state variables (`res`, `num`, `sign`) correctly.
- [ ] Handle digit parsing accurately.
- [ ] Implement the logic for '(', ')', '+', and '-' characters.
- [ ] Ensure the last number is added to the result.
- [ ] Test with various examples, including nested parentheses and negative numbers.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Basic Calculator II
*   Basic Calculator III
*   Expression Add Operators
*   Different Ways to Add Parentheses

## Tags
`Recursion` `String` `Math`
