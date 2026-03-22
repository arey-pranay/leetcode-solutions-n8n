# Valid Parentheses

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `String` `Stack`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(char c : s.toCharArray()){
            switch(c){
                case ')':
                    if(!st.isEmpty() && st.peek() == '(') st.pop();
                    else return false;
                    break;
                    
                case ']':
                    if(!st.isEmpty() && st.peek() == '[' ) st.pop();
                    else return false;
                    break;
                    
                case'}' :
                    if(!st.isEmpty() && st.peek() == '{' ) st.pop();
                    else return false;
                    break;
                    
                default:
                    st.push(c);
                    break;
            }    
        }
        return st.isEmpty();
    }
}

// (][}{)}])
```

---

---
## Quick Revision
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
A string is valid if open brackets are closed by the same type of brackets and in the correct order.

## Intuition
The core idea is that when we encounter an opening bracket, we need to remember it because it must be closed later by its corresponding closing bracket. When we see a closing bracket, it must match the *most recently opened* and *still unclosed* opening bracket. This "last-in, first-out" behavior strongly suggests using a stack.

## Algorithm
1. Initialize an empty stack to store opening brackets.
2. Iterate through each character in the input string `s`.
3. If the character is an opening bracket ('(', '[', or '{'), push it onto the stack.
4. If the character is a closing bracket (')', ']', or '}'):
    a. Check if the stack is empty. If it is, it means we have a closing bracket without a corresponding opening bracket, so the string is invalid. Return `false`.
    b. Peek at the top element of the stack.
    c. If the top element is the matching opening bracket for the current closing bracket (e.g., '(' for ')', '[' for ']', '{' for '}'), pop the opening bracket from the stack.
    d. If the top element does not match, the brackets are mismatched or out of order, so the string is invalid. Return `false`.
5. After iterating through the entire string, if the stack is empty, it means all opening brackets have been correctly closed. Return `true`.
6. If the stack is not empty, it means there are unclosed opening brackets, so the string is invalid. Return `false`.

## Concept to Remember
*   **Stacks (LIFO):** Essential for problems requiring tracking of nested structures or last-in, first-out operations.
*   **Bracket Matching:** Understanding how to pair opening and closing delimiters.
*   **String Traversal:** Iterating through characters of a string.

## Common Mistakes
*   Forgetting to handle the case where a closing bracket appears before any opening bracket (empty stack).
*   Not checking if the stack is empty before peeking or popping.
*   Incorrectly matching closing brackets to their corresponding opening brackets.
*   Failing to check if the stack is empty at the end of the string to ensure all brackets were closed.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the string once, and stack operations (push, pop, peek, isEmpty) take constant time.
- Space: O(n) - reason: In the worst case, if the string consists only of opening brackets (e.g., "((((("), the stack will store all characters.

## Commented Code
```java
class Solution {
    public boolean isValid(String s) {
        // Initialize a stack to keep track of opening brackets encountered.
        Stack<Character> st = new Stack<>();
        
        // Iterate over each character in the input string.
        for(char c : s.toCharArray()){
            // Use a switch statement to handle different character types.
            switch(c){
                // If the character is a closing parenthesis ')'.
                case ')':
                    // Check if the stack is not empty AND the top element is its matching opening parenthesis '('.
                    if(!st.isEmpty() && st.peek() == '(') {
                        // If it matches, pop the opening parenthesis from the stack as it's now closed.
                        st.pop();
                    } else {
                        // If the stack is empty or the top element doesn't match, the string is invalid.
                        return false;
                    }
                    // Break out of the switch case.
                    break;
                    
                // If the character is a closing square bracket ']'.
                case ']':
                    // Check if the stack is not empty AND the top element is its matching opening square bracket '['.
                    if(!st.isEmpty() && st.peek() == '[' ) {
                        // If it matches, pop the opening square bracket from the stack.
                        st.pop();
                    } else {
                        // If the stack is empty or the top element doesn't match, the string is invalid.
                        return false;
                    }
                    // Break out of the switch case.
                    break;
                    
                // If the character is a closing curly brace '}'.
                case'}' :
                    // Check if the stack is not empty AND the top element is its matching opening curly brace '{'.
                    if(!st.isEmpty() && st.peek() == '{' ) {
                        // If it matches, pop the opening curly brace from the stack.
                        st.pop();
                    } else {
                        // If the stack is empty or the top element doesn't match, the string is invalid.
                        return false;
                    }
                    // Break out of the switch case.
                    break;
                    
                // If the character is not a closing bracket, it must be an opening bracket.
                default:
                    // Push the opening bracket onto the stack.
                    st.push(c);
                    // Break out of the switch case.
                    break;
            }    
        }
        // After processing all characters, if the stack is empty, all brackets were matched correctly.
        // If the stack is not empty, there are unmatched opening brackets.
        return st.isEmpty();
    }
}
```

## Interview Tips
*   Clearly explain the stack's role and why it's the appropriate data structure.
*   Walk through an example like `"{[()]}"` and `"{[(])}"` to illustrate the algorithm's steps.
*   Be prepared to discuss edge cases like an empty string, a string with only opening brackets, or a string with only closing brackets.
*   Mention the importance of checking for an empty stack *before* peeking or popping.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the LIFO nature of bracket matching.
- [ ] Implement the stack-based algorithm correctly.
- [ ] Handle all types of brackets and their pairings.
- [ ] Consider edge cases (empty string, unbalanced strings).
- [ ] Analyze time and space complexity.

## Similar Problems
Valid Parentheses II (LeetCode 22)
Longest Valid Parentheses (LeetCode 32)
Remove Invalid Parentheses (LeetCode 301)

## Tags
`Stack` `String` `Array` `Hash Map`

## My Notes
classic stack and switchcase question.
