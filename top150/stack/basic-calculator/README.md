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
This problem involves evaluating a string expression containing integers, '+', '-', '(', and ')'. The solution uses recursion to handle nested parentheses and a single pass to process the expression.

## Intuition
The core idea is to break down the problem recursively. When we encounter an opening parenthesis '(', it signifies a sub-expression that needs to be evaluated independently. We can delegate this evaluation to a recursive call. The main function then treats the result of this sub-expression as a single number. For the main expression, we maintain a running result, the current number being parsed, and the sign of the current number. When we hit a '+', '-', or ')', it signals the end of a number, and we update the result accordingly.

## Algorithm
1. Initialize `res` (result), `num` (current number), and `sign` (sign of the current number) to 0, 0, and 1 respectively.
2. Iterate through the input string `s` using an index `idx`.
3. If the current character `c` is a digit:
    a. Update `num` by appending the digit: `num = num * 10 + c - '0'`.
4. If the current character `c` is an opening parenthesis '(':
    a. Increment `idx` to move past '('.
    b. Recursively call `calculate(s)` to evaluate the sub-expression within the parentheses.
    c. Assign the result of the recursive call to `num`.
5. If the current character `c` is a closing parenthesis ')':
    a. Add the last processed number (`sign * num`) to `res`.
    b. Return `res`. This signifies the end of a sub-expression evaluation.
6. If the current character `c` is a '+' or '-':
    a. Add the previously accumulated number (`sign * num`) to `res`.
    b. Reset `num` to 0.
    c. Update `sign` based on the current character: `sign = (c == '-') ? -1 : 1`.
7. After the loop finishes, add the last processed number (`sign * num`) to `res`.
8. Return `res`.

## Concept to Remember
*   **Recursion:** Effectively handling nested structures like parentheses by breaking them into smaller, self-similar subproblems.
*   **State Management:** Keeping track of the current number being parsed, the running result, and the sign of the current number is crucial for correct evaluation.
*   **String Parsing:** Iterating through a string and extracting meaningful tokens (numbers, operators, parentheses).

## Common Mistakes
*   **Incorrectly handling the global index:** The provided solution uses a class-level `idx`. If not managed carefully, especially with recursion, it can lead to incorrect parsing. A better approach might be to pass the index by reference or return the updated index from recursive calls.
*   **Forgetting to add the last number:** The final number in the expression (or sub-expression) needs to be added to the result after the loop terminates.
*   **Mismanaging signs with parentheses:** Ensuring that the sign preceding a parenthesized expression is correctly applied to its evaluated result.
*   **Off-by-one errors with index manipulation:** Care must be taken when incrementing `idx` and when characters are consumed within loops or recursive calls.

## Complexity Analysis
- Time: O(N) - reason The string is traversed at most once. Each character is visited a constant number of times, even with recursion, as the `idx` advances globally.
- Space: O(N) - reason In the worst case, the recursion depth can be proportional to the number of nested parentheses, which can be up to N/2 for a string like "((...))".

## Commented Code
```java
class Solution {
    int idx = 0; // This index traverses the string in one pass, between different levels of recursion. It's a class member to maintain state across recursive calls.

    public int calculate(String s) {
        int res = 0; // Initialize the result of the current expression/sub-expression.
        int num = 0; // Initialize the current number being parsed.
        int sign = 1; // Initialize the sign of the current number (1 for positive, -1 for negative).

        while (idx < s.length()) { // Loop through the string character by character.
            char c = s.charAt(idx++); // Get the current character and advance the index.

            if (c >= '0' && c <= '9') { // If the character is a digit:
                num = num * 10 + c - '0'; // Build the current number by appending the digit.
            } else if (c == '(') { // If the character is an opening parenthesis:
                // This signifies the start of a sub-expression.
                // Recursively call calculate to evaluate the sub-expression.
                // The result of the sub-expression is treated as a single number.
                num = calculate(s);
            } else if (c == ')') { // If the character is a closing parenthesis:
                // This signifies the end of the current sub-expression.
                // Add the last processed number (sign * num) to the result.
                // Return the result of this sub-expression.
                return res + (sign * num);
            } else if (c == '+' || c == '-') { // If the character is a '+' or '-':
                // This signifies the end of a number.
                // Add the previously accumulated number (sign * num) to the result.
                res = res + (sign * num);
                // Reset num to 0 to start parsing the next number.
                num = 0;
                // Update the sign for the next number.
                sign = (c == '-') ? -1 : 1;
            }
            // Spaces are implicitly ignored as they don't fall into any of the above conditions.
        }
        // After the loop, there might be a last number that hasn't been added to res yet.
        // Add the last processed number (sign * num) to the result.
        return res + (sign * num);
    }
}
```

## Interview Tips
1.  **Clarify Scope:** Ask if the input string will always be valid, if there are any constraints on the size of numbers, or if there are other operators like '*' or '/'.
2.  **Explain Recursion:** Clearly articulate how recursion helps in breaking down the problem, especially when dealing with nested parentheses. Walk through an example with nested parentheses.
3.  **Discuss Index Management:** Highlight the importance of the `idx` variable and how it's managed across recursive calls. Mention potential pitfalls of using a global index and suggest alternatives if asked (e.g., passing index by reference or returning it).
4.  **Edge Cases:** Consider edge cases like an empty string, a string with only numbers, a string with only parentheses, or expressions starting/ending with operators.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the role of parentheses in changing the order of operations.
- [ ] Grasp the recursive approach for handling nested parentheses.
- [ ] Trace the execution with a complex example involving nested parentheses.
- [ ] Analyze the time and space complexity.
- [ ] Practice implementing the solution from scratch.
- [ ] Be prepared to discuss alternative approaches (e.g., using a stack explicitly).

## Similar Problems
*   Basic Calculator II
*   Basic Calculator III
*   Expression Add Operators
*   Different Ways to Add Parentheses

## Tags
`Recursion` `String` `Math`
