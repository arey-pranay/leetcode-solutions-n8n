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
        for(int i = 0 ; i<s.length();i++){
            char c = s.charAt(i);
            if(c=='(') st.push(')');
            else if(c=='[') st.push(']'); 
            else if(c=='{') st.push('}');
            else {
                if(st.isEmpty()) return false;
                char temp = st.pop();
                if(temp != c) return false;
            }
        }
        return st.isEmpty();
    }
}
```

---

---
## Quick Revision
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
A string is valid if open brackets are closed by the same type of brackets and in the correct order.

## Intuition
The core idea is that when we encounter an opening bracket, we expect a corresponding closing bracket later. The most recently opened bracket must be the first one to be closed. This "last-in, first-out" behavior strongly suggests using a stack. When we see an opening bracket, we push its *expected closing bracket* onto the stack. When we see a closing bracket, we check if it matches the *expected closing bracket* at the top of the stack.

## Algorithm
1. Initialize an empty stack.
2. Iterate through each character in the input string `s`.
3. If the character is an opening bracket ('(', '[', or '{'):
    a. Push its corresponding closing bracket (')', ']', or '}') onto the stack.
4. If the character is a closing bracket (')', ']', or '}'):
    a. Check if the stack is empty. If it is, it means we have a closing bracket without a corresponding opening bracket, so return `false`.
    b. Pop the top element from the stack.
    c. Compare the popped element with the current character. If they do not match, it means the brackets are not closed in the correct order or with the correct type, so return `false`.
5. After iterating through the entire string, check if the stack is empty. If it is, all opening brackets have been correctly closed, so return `true`. If the stack is not empty, it means there are unclosed opening brackets, so return `false`.

## Concept to Remember
*   **Stacks:** LIFO (Last-In, First-Out) data structure, essential for problems involving matching pairs or nested structures.
*   **Bracket Matching:** The principle of ensuring that opening and closing delimiters are correctly paired and ordered.
*   **State Management:** Using a data structure (like a stack) to keep track of the "state" of pending operations or expected closures.

## Common Mistakes
*   Forgetting to handle the case where a closing bracket appears before any opening bracket (stack is empty).
*   Not checking if the stack is empty *before* popping an element when a closing bracket is encountered.
*   Pushing the opening bracket onto the stack instead of its corresponding closing bracket, making the comparison logic more complex.
*   Failing to check if the stack is empty at the end of the string, which indicates unclosed opening brackets.

## Complexity Analysis
- Time: O(n) - reason: We iterate through the string once, and each stack operation (push, pop, isEmpty) takes constant time.
- Space: O(n) - reason: In the worst case (e.g., a string of all opening brackets like "((((("), the stack can store up to n characters.

## Commented Code
```java
class Solution {
    public boolean isValid(String s) {
        // Initialize a stack to store expected closing characters.
        Stack<Character> st = new Stack<>();
        // Iterate through each character in the input string.
        for(int i = 0 ; i<s.length();i++){
            // Get the current character.
            char c = s.charAt(i);
            // If the character is an opening parenthesis, push its corresponding closing parenthesis onto the stack.
            if(c=='(') st.push(')');
            // If the character is an opening square bracket, push its corresponding closing square bracket onto the stack.
            else if(c=='[') st.push(']');
            // If the character is an opening curly brace, push its corresponding closing curly brace onto the stack.
            else if(c=='{') st.push('}');
            // If the character is a closing bracket.
            else {
                // Check if the stack is empty. If it is, we have a closing bracket without a matching opening bracket.
                if(st.isEmpty()) return false;
                // Pop the top element from the stack, which should be the expected closing bracket.
                char temp = st.pop();
                // If the popped character does not match the current closing bracket, the string is invalid.
                if(temp != c) return false;
            }
        }
        // After iterating through the string, if the stack is empty, all brackets were matched correctly.
        // If the stack is not empty, there are unclosed opening brackets.
        return st.isEmpty();
    }
}
```

## Interview Tips
*   Clearly explain your stack-based approach and why it's suitable for this problem.
*   Walk through an example like `"{[()]}"` and `"{[(])}"` to demonstrate how your algorithm handles valid and invalid cases.
*   Be prepared to discuss edge cases: empty string, string with only opening brackets, string with only closing brackets.
*   Mention the time and space complexity and justify them.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the LIFO nature of bracket matching.
- [ ] Implement the stack-based algorithm correctly.
- [ ] Handle edge cases: empty string, unmatched brackets.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
Valid Parentheses II (LeetCode 22)
Minimum Add to Make Parentheses Valid (LeetCode 921)
Longest Valid Parentheses (LeetCode 32)
Remove Invalid Parentheses (LeetCode 301)

## Tags
`Stack` `String` `Array` `Hash Map`
