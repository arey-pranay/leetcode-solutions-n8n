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
This problem is solved using recursion or a stack to handle nested structures.

## Intuition
The core challenge is handling nested repetitions like `3[a2[c]]`. When we encounter a number followed by `[`, we know a repetition is starting. The string inside the brackets needs to be decoded first, and then repeated. This nested nature strongly suggests a recursive approach. The recursive function can handle decoding a substring, and when it hits a closing bracket `]`, it returns the decoded substring to its caller, which then repeats it.

## Algorithm
1. Initialize a global or class-level index `i` to 0 to keep track of the current position in the input string `s`.
2. Create a `StringBuilder` called `ans` to build the decoded string.
3. Iterate through the string `s` using the index `i`.
4. If the current character `s.charAt(i)` is `]`, it signifies the end of a decoded segment. Return the current `ans.toString()`.
5. If the current character is a letter, append it to `ans` and increment `i`.
6. If the current character is a digit:
    a. Parse the full number `k` by repeatedly multiplying by 10 and adding the digit value until a non-digit character is encountered. Increment `i` for each digit.
    b. Increment `i` to skip the opening bracket `[`.
    c. Recursively call `decodeString(s)` to decode the string inside the brackets. This call will return the decoded substring.
    d. Increment `i` to skip the closing bracket `]`.
    e. Append the returned decoded substring to `ans` exactly `k` times.
7. After the loop finishes (if the entire string is processed without hitting a `]`), return `ans.toString()`.

## Concept to Remember
*   **Recursion:** Effectively handles nested structures by breaking down the problem into smaller, self-similar subproblems.
*   **String Manipulation:** Efficiently building strings using `StringBuilder` to avoid repeated object creation.
*   **State Management:** Using an index (global or passed by reference/class member) to maintain the current parsing position across recursive calls.

## Common Mistakes
*   **Incorrectly handling the index `i`:** Forgetting to increment `i` after processing digits, brackets, or characters can lead to infinite loops or incorrect parsing.
*   **Not handling nested brackets properly:** A non-recursive or improperly implemented recursive solution might fail on complex nested structures.
*   **Integer overflow for `k`:** While less likely in typical LeetCode constraints, extremely large repetition counts could theoretically cause issues if not handled with appropriate data types.
*   **Off-by-one errors with brackets:** Mismatched or skipped brackets during parsing.

## Complexity Analysis
*   Time: O(N), where N is the length of the decoded string. In the worst case, the decoded string can be much longer than the encoded string. Each character in the original string is processed a constant number of times, and appending to `StringBuilder` is amortized O(1). The total length of the decoded string can be exponential in the number of repetitions, but the work done per character of the *original* string is constant.
*   Space: O(D), where D is the maximum nesting depth of the brackets. This is due to the recursion call stack. In the worst case, if the string is `1[1[1[...1[a]...]]]`, the depth can be proportional to the length of the string.

## Commented Code
```java
class Solution {
    // Declare a class-level index to keep track of the current position in the string 's' across recursive calls.
    int i = 0;

    public String decodeString(String s) {
        // Initialize a StringBuilder to efficiently build the decoded string.
        StringBuilder ans = new StringBuilder();

        // Loop through the input string 's' starting from the current index 'i'.
        while (i < s.length()) {
            // If the current character is a closing bracket ']', it means we have finished decoding a segment.
            // Return the accumulated decoded string for this segment.
            if (s.charAt(i) == ']') {
                return ans.toString();
            }

            // If the current character is a letter, it's part of the decoded string.
            if (Character.isLetter(s.charAt(i))) {
                // Append the letter to our result.
                ans.append(s.charAt(i));
                // Move to the next character.
                i++;
            } else { // If it's not a letter, it must be a digit (start of a repetition count).
                // Initialize a variable 'k' to store the repetition count.
                int k = 0;
                // Parse the full number for the repetition count.
                while (Character.isDigit(s.charAt(i))) {
                    // Build the number by shifting existing digits left and adding the new digit.
                    k = k * 10 + s.charAt(i) - '0';
                    // Move to the next character.
                    i++;
                }
                // After reading the digits, the current character must be the opening bracket '['.
                // Increment 'i' to skip this opening bracket.
                i++;

                // Recursively call decodeString to decode the substring within the brackets.
                // This call will handle nested decoding and return the fully decoded inner string.
                String temp = decodeString(s);

                // After the recursive call returns, the current character must be the closing bracket ']'.
                // Increment 'i' to skip this closing bracket.
                i++;

                // Repeat the decoded inner string 'temp' exactly 'k' times and append it to our result.
                while (k-- > 0) {
                    ans.append(temp);
                }
            }
        }
        // If the loop finishes without encountering a closing bracket (e.g., the entire string is processed),
        // return the final decoded string.
        return ans.toString();
    }
}
```

## Interview Tips
*   **Explain the recursive approach:** Clearly articulate why recursion is a natural fit for this problem due to the nested structure.
*   **Trace an example:** Walk through a complex example like `3[a2[c]]` step-by-step, showing how the index `i` and the `StringBuilder` change with each recursive call and return.
*   **Discuss the index management:** Emphasize how the class-level `i` is crucial for maintaining state across recursive calls. If using a stack-based approach, explain how the stack stores counts and partial strings.
*   **Consider edge cases:** Mention empty strings, strings with no numbers/brackets, and strings with only numbers or only letters.

## Revision Checklist
- [ ] Understand the encoding rule: k[encoded_string].
- [ ] Recognize the need for handling nested structures.
- [ ] Implement a recursive solution or a stack-based solution.
- [ ] Correctly parse numbers (repetition counts).
- [ ] Properly handle opening `[` and closing `]` brackets.
- [ ] Manage the current parsing index accurately.
- [ ] Use `StringBuilder` for efficient string construction.
- [ ] Analyze time and space complexity.

## Similar Problems
*   LeetCode 394: Decode Ways (different encoding rule, but similar recursive/DP thinking)
*   LeetCode 71: Simplify Path
*   LeetCode 20: Valid Parentheses (basic bracket matching)

## Tags
`Recursion` `String` `Stack`
