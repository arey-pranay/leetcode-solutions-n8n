# Valid Number

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `String`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public boolean isNumber(String s) {
          int i =0;
          int n = s.length();
          char[] carr = s.toCharArray();
          
          // 1. SIGN?   (SIGN (- or +) <= 1 )
          if(i<n && carr[i]=='+' || carr[i]=='-') i++;
          
          // 2. NUM* (NUM ( 0 to 9 )  >= 0)
          boolean foundDigit = false; //either stage 2 or 4 should have a digit
          while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}

          //3. DOT? (DOT ( . ) <= 1 )
          if(i<n && carr[i]=='.') i++;

          //4. NUM* (NUM ( 0 to 9 )  >= 0)
          while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}
          if(!foundDigit) return false; // avoid -. like things

          //5. E?   ( e or E ) <= 0 
          if(i<n && (carr[i]=='e' || carr[i]=='E')){
            i++;
             // SIGN (- or +) <= 1  
            if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;

            // NUM ( 0 to 9 )  >=1
            foundDigit = false; // + instead of *, so we need at least one digit
            while(i<n && Character.isDigit(carr[i])){foundDigit=true; i++;}
            if(!foundDigit) return false;
          }
          return i==n;
    }
}
```

---

---
## Quick Revision
This problem asks to validate if a given string represents a valid numerical value.
The solution uses a state-machine-like approach by iterating through the string and checking for valid character sequences.

## Intuition
The core idea is to parse the string character by character, keeping track of the "state" of our number parsing. A valid number can have an optional sign, followed by digits, an optional decimal point, more digits, and an optional exponent part (which itself can have a sign and digits). We need to ensure that each part appears in the correct order and with the correct frequency. For example, we can't have two decimal points, or a decimal point after an exponent. The crucial part is that *some* digits must be present for the number to be valid (e.g., "." is not a valid number, but "0." or ".0" are).

## Algorithm
1. Initialize an index `i` to 0 and get the length `n` of the input string `s`. Convert `s` to a character array `carr` for easier access.
2. Check for an optional sign (`+` or `-`) at the beginning. If found, increment `i`.
3. Initialize a boolean flag `foundDigit` to `false`. This flag will track if we've encountered at least one digit in the number part (either before or after the decimal point).
4. Parse the integer part: while `i` is within bounds and the current character is a digit, set `foundDigit` to `true` and increment `i`.
5. Check for an optional decimal point (`.`). If found, increment `i`.
6. Parse the fractional part: while `i` is within bounds and the current character is a digit, set `foundDigit` to `true` and increment `i`.
7. If `foundDigit` is still `false` at this point, it means we haven't found any digits (e.g., the string was just a sign or a sign followed by a dot). Return `false`.
8. Check for an optional exponent part (`e` or `E`). If found, increment `i`.
9. If an exponent was found, check for an optional sign (`+` or `-`) immediately after it. If found, increment `i`.
10. Parse the exponent's integer part: reset `foundDigit` to `false` (because the exponent *must* have digits). While `i` is within bounds and the current character is a digit, set `foundDigit` to `true` and increment `i`.
11. If an exponent was present and `foundDigit` is `false` after parsing its digits, it means the exponent part is invalid (e.g., "1e" or "1e+"). Return `false`.
12. Finally, if `i` has reached the end of the string (`i == n`), it means the entire string was consumed and parsed successfully. Return `true`. Otherwise, return `false` (indicating trailing invalid characters).

## Concept to Remember
*   **State Machine / Sequential Parsing:** The problem can be modeled as a finite state machine where transitions depend on the current character and the current state of parsing.
*   **Edge Case Handling:** Many valid number formats have optional components (signs, decimal points, exponents), requiring careful handling of their presence and absence.
*   **Boolean Flags for Validation:** Using flags like `foundDigit` is crucial to ensure that essential components (like digits) are present where required.
*   **String Traversal and Character Properties:** Efficiently iterating through the string and using character utility functions (`Character.isDigit`) is key.

## Common Mistakes
*   **Missing `foundDigit` check:** Failing to ensure at least one digit is present before or after the decimal point, or in the exponent, leading to false positives for inputs like `.` or `e`.
*   **Incorrect order of checks:** Checking for the exponent before the decimal point, or allowing multiple decimal points or signs in invalid positions.
*   **Not handling empty strings or strings with only signs/dots:** These should all be invalid.
*   **Forgetting to reset `foundDigit` for the exponent:** The exponent part *requires* digits, so `foundDigit` must be re-evaluated for it.
*   **Not checking if the entire string is consumed:** Allowing trailing characters after a valid number part (e.g., "123a").

## Complexity Analysis
- Time: O(N) - The algorithm iterates through the input string `s` at most once. N is the length of the string.
- Space: O(N) - Converting the string to a character array takes O(N) space. If we operate directly on the string using `s.charAt(i)`, space complexity would be O(1).

## Commented Code
```java
class Solution {
    public boolean isNumber(String s) {
          // Initialize index to traverse the string
          int i =0;
          // Get the length of the string
          int n = s.length();
          // Convert string to char array for efficient character access
          char[] carr = s.toCharArray();
          
          // 1. Check for an optional sign at the beginning of the string.
          // The sign can be '+' or '-'. If present, advance the index.
          if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;
          
          // 2. Parse the integer part of the number.
          // 'foundDigit' tracks if we have encountered any digit so far.
          boolean foundDigit = false; 
          // Loop while the current character is a digit and we are within string bounds.
          while(i<n && Character.isDigit(carr[i])){
            // Mark that we have found at least one digit.
            foundDigit=true; 
            // Move to the next character.
            i++;
          }

          // 3. Check for an optional decimal point.
          // If present, advance the index.
          if(i<n && carr[i]=='.') i++;

          // 4. Parse the fractional part of the number.
          // Loop while the current character is a digit and we are within string bounds.
          while(i<n && Character.isDigit(carr[i])){
            // Mark that we have found at least one digit (either in integer or fractional part).
            foundDigit=true; 
            // Move to the next character.
            i++;
          }
          // If no digit was found in either the integer or fractional part, the number is invalid.
          // This handles cases like ".", "+.", "-.".
          if(!foundDigit) return false; 

          // 5. Check for the exponent part ('e' or 'E').
          // If present, advance the index.
          if(i<n && (carr[i]=='e' || carr[i]=='E')){
            // Move past 'e' or 'E'.
            i++;
             // Check for an optional sign for the exponent.
             // The exponent sign can be '+' or '-'. If present, advance the index.
            if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;

            // 6. Parse the digits of the exponent.
            // Reset 'foundDigit' because the exponent *must* have at least one digit.
            foundDigit = false; 
            // Loop while the current character is a digit and we are within string bounds.
            while(i<n && Character.isDigit(carr[i])){
              // Mark that we have found a digit in the exponent.
              foundDigit=true; 
              // Move to the next character.
              i++;
            }
            // If an exponent was present but no digits followed it, the number is invalid.
            // This handles cases like "1e", "1e+", "1e-".
            if(!foundDigit) return false;
          }
          // If the index has reached the end of the string, it means the entire string was parsed successfully.
          return i==n;
    }
}
```

## Interview Tips
*   **Walk through examples:** Be prepared to trace the execution of your code with various valid and invalid inputs like "0", " 0.1 ", "abc", "1 a", "2e10", " -90e3   ", " 1e", "e3", " 99e2.5 ", " --6 ", " +3.14e-10 ", " . ", " .1 ", "1. ".
*   **Explain the state transitions:** Describe how the index `i` and the `foundDigit` flag represent the current parsing state.
*   **Discuss edge cases explicitly:** Highlight how your code handles inputs with leading/trailing spaces (though this specific solution doesn't handle spaces, it's a common variation), empty strings, signs only, decimal points only, and invalid exponent formats.
*   **Consider alternative approaches:** Briefly mention that a more robust solution might involve regular expressions or a formal finite automaton, but the iterative parsing approach is often preferred in interviews for demonstrating step-by-step logic.

## Revision Checklist
- [ ] Understand the definition of a valid number (including scientific notation).
- [ ] Implement the logic to handle optional signs at the beginning and for the exponent.
- [ ] Correctly parse integer and fractional parts, ensuring at least one digit is present overall.
- [ ] Handle the optional decimal point.
- [ ] Implement the logic for the exponent part (`e` or `E`), including its optional sign and required digits.
- [ ] Ensure the entire string is consumed by the parsing process.
- [ ] Test with various edge cases: empty string, only signs, only dots, invalid exponent formats, trailing characters.

## Similar Problems
*   Regular Expression Matching
*   String to Integer (atoi)
*   Valid Palindrome
*   Valid Parentheses

## Tags
`String` `Math` `Parsing` `State Machine`
