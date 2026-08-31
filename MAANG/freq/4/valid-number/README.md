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
The core idea is to parse the string character by character, keeping track of what we expect next. A valid number can have an optional sign, followed by digits, an optional decimal point, more digits, and an optional exponent part (which itself can have a sign and digits). We need to ensure that each part appears at most once in the correct order and that essential components like digits are present where required.

## Algorithm
1. Initialize an index `i` to 0 and get the length `n` of the input string `s`. Convert `s` to a character array `carr` for easier access.
2. Check for an optional sign (`+` or `-`) at the beginning. If found, increment `i`.
3. Initialize a boolean flag `foundDigit` to `false`. This flag will track if any digit has been encountered so far.
4. Parse digits after the initial sign. While `i` is within bounds and the character is a digit, set `foundDigit` to `true` and increment `i`.
5. Check for an optional decimal point (`.`). If found, increment `i`.
6. Parse digits after the decimal point. While `i` is within bounds and the character is a digit, set `foundDigit` to `true` and increment `i`.
7. If `foundDigit` is still `false` at this point, it means no digits were found before or after the decimal point (e.g., just a sign or a dot), so return `false`.
8. Check for an optional exponent part (`e` or `E`). If found, increment `i`.
9. Inside the exponent part, check for an optional sign (`+` or `-`). If found, increment `i`.
10. Parse digits for the exponent. Reset `foundDigit` to `false` because the exponent *must* have at least one digit. While `i` is within bounds and the character is a digit, set `foundDigit` to `true` and increment `i`.
11. If `foundDigit` is `false` after checking for exponent digits, it means the exponent part is invalid (e.g., "1e" or "1e+"), so return `false`.
12. Finally, if the index `i` has reached the end of the string (`i == n`), it means the entire string was consumed and parsed successfully, so return `true`. Otherwise, return `false`.

## Concept to Remember
*   **State Machine Logic:** The problem can be modeled as a finite state machine where each character transitions the parser to a new state.
*   **String Parsing:** Efficiently iterating and validating character sequences within a string.
*   **Edge Case Handling:** Carefully considering and handling various valid and invalid number formats (e.g., signs, decimal points, exponents, empty strings, strings with only signs/dots).

## Common Mistakes
*   Not handling cases like just a sign (`+`, `-`) or just a decimal point (`.`).
*   Allowing multiple decimal points or multiple exponent markers (`e`, `E`).
*   Not ensuring that the exponent part, if present, has at least one digit after its sign.
*   Failing to check if the entire string has been consumed by the parser.
*   Incorrectly handling the order of components (e.g., digits after exponent sign).

## Complexity Analysis
- Time: O(N) - reason: We iterate through the input string `s` at most once.
- Space: O(N) - reason: Converting the string to a character array takes O(N) space. If we operate directly on the string using `s.charAt(i)`, space complexity would be O(1).

## Commented Code
```java
class Solution {
    public boolean isNumber(String s) {
          // Initialize index to the start of the string.
          int i =0;
          // Get the total length of the string.
          int n = s.length();
          // Convert the string to a character array for efficient access.
          char[] carr = s.toCharArray();
          
          // 1. Check for an optional sign (+ or -) at the beginning.
          // If a sign is found, advance the index.
          if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;
          
          // 2. Parse digits before the decimal point.
          boolean foundDigit = false; // Flag to track if any digit has been found.
          // Iterate as long as we are within bounds and encounter digits.
          while(i<n && Character.isDigit(carr[i])){
            // Mark that a digit has been found.
            foundDigit=true; 
            // Advance the index.
            i++;
          }

          // 3. Check for an optional decimal point.
          // If a dot is found, advance the index.
          if(i<n && carr[i]=='.') i++;

          // 4. Parse digits after the decimal point.
          // Iterate as long as we are within bounds and encounter digits.
          while(i<n && Character.isDigit(carr[i])){
            // Mark that a digit has been found.
            foundDigit=true; 
            // Advance the index.
            i++;
          }
          // If no digits were found at all (e.g., ".-", "+", "."), it's invalid.
          if(!foundDigit) return false; 

          // 5. Check for an optional exponent part (e or E).
          // If 'e' or 'E' is found, advance the index.
          if(i<n && (carr[i]=='e' || carr[i]=='E')){
            // Advance past 'e' or 'E'.
            i++;
             // Check for an optional sign (+ or -) for the exponent.
            if(i<n && (carr[i]=='+' || carr[i]=='-')) i++;

            // 6. Parse digits for the exponent.
            foundDigit = false; // Reset flag: exponent MUST have at least one digit.
            // Iterate as long as we are within bounds and encounter digits.
            while(i<n && Character.isDigit(carr[i])){
              // Mark that a digit has been found for the exponent.
              foundDigit=true; 
              // Advance the index.
              i++;
            }
            // If no digits were found for the exponent (e.g., "1e", "1e+"), it's invalid.
            if(!foundDigit) return false;
          }
          // If the index has reached the end of the string, the entire string was a valid number.
          return i==n;
    }
}
```

## Interview Tips
*   Explain your approach clearly, perhaps by drawing a state diagram or describing the expected structure of a valid number.
*   Walk through a few examples, including edge cases like "0", ".1", "1.", "2e10", "-90e3", "abc", "1a", "e3", "99e2.5", "--6", "95a54e53".
*   Be prepared to discuss alternative approaches, such as using regular expressions (though often discouraged in interviews for this specific problem to test parsing skills) or a more formal state machine implementation.
*   Emphasize the importance of handling all valid components and their order, as well as invalid sequences.

## Revision Checklist
- [ ] Understand the definition of a valid number (integers, decimals, scientific notation).
- [ ] Implement the parsing logic step-by-step.
- [ ] Handle optional signs at the beginning and for the exponent.
- [ ] Ensure at least one digit is present in the mantissa part (before or after decimal).
- [ ] Ensure at least one digit is present in the exponent part if 'e' or 'E' is used.
- [ ] Handle cases with only a decimal point or only a sign.
- [ ] Verify that the entire string is consumed.
- [ ] Test with various edge cases.

## Similar Problems
*   String to Integer (atoi)
*   Regular Expression Matching
*   Basic Calculator
*   Basic Calculator II

## Tags
`String` `Math` `Parsing` `State Machine`
