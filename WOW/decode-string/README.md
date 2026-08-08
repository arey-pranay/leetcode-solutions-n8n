# Decode String

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `String` `Stack` `Recursion`  
**Time:** O(N)  
**Space:** O(D)

---

## Solution (java)

```java
class Solution {
    int i=0;
    public String decodeString(String s) {
      StringBuilder ans = new StringBuilder();
      while(i<s.length()){
        if(s.charAt(i)==']') return ans.toString();

        if(Character.isLetter(s.charAt(i))) ans.append(s.charAt(i++));
        else{
            int k=0;
            while(Character.isDigit(s.charAt(i))) k = k*10 + s.charAt(i++)-'0';
            i++; //opening bracket
            String temp = decodeString(s);
            i++; //closing
            while(k-->0)ans.append(temp);
        }
    }
    return ans.toString();
  }
}

```

---

---
## Quick Revision
Given an encoded string, return its decoded string. The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is repeated exactly k times.
This problem can be solved using recursion or a stack to handle nested structures.

## Intuition
The core challenge is handling nested repetitions. When we encounter a number followed by `[`, it signifies a repetition. The string inside the `[]` needs to be decoded first, and then repeated. This nested nature strongly suggests a recursive approach or a stack-based solution. The provided solution uses recursion, where `decodeString` is called for each nested `[encoded_string]`. The `i` variable acts as a global pointer to traverse the string, ensuring we don't re-process parts of the string.

## Algorithm
1. Initialize a global index `i` to 0.
2. Create a `StringBuilder` `ans` to store the decoded string.
3. Iterate through the input string `s` using the index `i`.
4. If the current character is `]`, return the current `ans`. This signifies the end of a decoded segment.
5. If the current character is a letter, append it to `ans` and increment `i`.
6. If the current character is a digit:
    a. Parse the full number `k` by repeatedly multiplying by 10 and adding the digit's value. Increment `i` as digits are consumed.
    b. Increment `i` to skip the opening bracket `[`.
    c. Recursively call `decodeString(s)` to decode the string within the brackets. Store the result in `temp`.
    d. Increment `i` to skip the closing bracket `]`.
    e. Repeat `temp` `k` times and append it to `ans`.
7. After the loop finishes (or if the initial string has no `]`), return `ans.toString()`.

## Concept to Remember
*   **Recursion:** Effectively handles nested structures by breaking down the problem into smaller, self-similar subproblems.
*   **String Manipulation:** Efficiently building strings using `StringBuilder` to avoid repeated object creation.
*   **State Management:** Using a global or passed-by-reference index to keep track of the current position in the string across recursive calls.

## Common Mistakes
*   **Incorrectly handling the index `i`:** Failing to increment `i` correctly after processing digits, brackets, or letters can lead to infinite loops or incorrect parsing.
*   **Not handling nested brackets properly:** A non-recursive or non-stack approach might struggle with multiple levels of nesting.
*   **Integer overflow for `k`:** While less likely in typical LeetCode constraints, very large repetition counts could theoretically cause issues.
*   **Off-by-one errors with brackets:** Misplacing the `i++` after encountering `[` or `]` can cause parsing errors.

## Complexity Analysis
*   **Time:** O(N), where N is the length of the decoded string. In the worst case, the decoded string can be much longer than the encoded string. Each character of the original string is processed a constant number of times, but the appended characters contribute to the overall work.
*   **Space:** O(D), where D is the maximum depth of nested brackets. This is due to the recursion stack. In the worst case, if the string is `10[10[...10[a]...]]`, the depth can be significant.

## Commented Code
```java
class Solution {
    // Global index to keep track of the current position in the string 's' across recursive calls.
    int i = 0;

    public String decodeString(String s) {
        // StringBuilder to efficiently build the decoded string.
        StringBuilder ans = new StringBuilder();

        // Loop through the string 's' starting from the current index 'i'.
        while (i < s.length()) {
            // If we encounter a closing bracket, it means we have finished decoding a segment.
            // Return the accumulated decoded string for this segment.
            if (s.charAt(i) == ']') return ans.toString();

            // If the current character is a letter, it's part of the decoded string.
            if (Character.isLetter(s.charAt(i))) {
                // Append the letter to our result and move to the next character.
                ans.append(s.charAt(i++));
            } else { // If it's not a letter, it must be a digit (start of a repetition).
                // Initialize 'k' to store the repetition count.
                int k = 0;
                // Parse the full number 'k'.
                while (Character.isDigit(s.charAt(i))) {
                    // Build the number by shifting existing digits left and adding the new digit.
                    k = k * 10 + s.charAt(i++) - '0';
                }
                // Increment 'i' to skip the opening bracket '[' after the number.
                i++;
                // Recursively call decodeString to decode the string inside the brackets.
                // The 'i' will be updated by the recursive call to point after the matching ']'.
                String temp = decodeString(s);
                // Increment 'i' to skip the closing bracket ']' after the recursive call returns.
                i++;
                // Repeat the decoded 'temp' string 'k' times and append it to our result.
                while (k-- > 0) ans.append(temp);
            }
        }
        // Return the final decoded string. This is reached if the initial call completes without hitting a ']'.
        return ans.toString();
    }
}
```

## Interview Tips
*   **Explain your approach:** Clearly articulate whether you're using recursion or a stack and why it's suitable for nested structures.
*   **Trace an example:** Walk through a simple nested example like "3[a]2[bc]" to demonstrate how your logic handles numbers, letters, and brackets.
*   **Discuss edge cases:** Consider empty strings, strings with no numbers, strings with only numbers, and deeply nested structures.
*   **Clarify index management:** Be prepared to explain how you are tracking your position in the string, especially if using a global index or passing it by reference.

## Revision Checklist
- [ ] Understand the encoding rule: k[encoded_string].
- [ ] Recognize the need for handling nested structures.
- [ ] Implement a recursive solution or a stack-based solution.
- [ ] Correctly parse numbers (k).
- [ ] Handle letters and append them.
- [ ] Manage the string index `i` accurately across recursive calls or stack operations.
- [ ] Test with various examples, including nested ones.

## Similar Problems
*   LeetCode 394: Decode Ways (different encoding rule, but similar concept of parsing and state)
*   LeetCode 20: Valid Parentheses (stack usage for matching brackets)
*   LeetCode 71: Simplify Path (stack usage for path manipulation)

## Tags
`Recursion` `Stack` `String` `Depth-First Search`
