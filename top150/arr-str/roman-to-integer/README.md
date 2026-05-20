# Roman To Integer

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Hash Table` `Math` `String`  
**Time:** O(N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int romanToInt(String s) {
        HashMap<Character,Integer> hm = new HashMap<>();
        hm.put('V',5);
        hm.put('L',50);
        hm.put('D',500);
        hm.put('M',1000);
        int i =-1;
        int ans =0;
        int n = s.length();
        while(i<n-1){
            i++;
            switch(s.charAt(i)){
                case 'I':
                    if(i<n-1 && s.charAt(i+1)=='V'){
                        ans += 4;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='X'){
                        ans += 9;
                        i++;
                    } else ans++;
                    break;
                case 'X':
                    if(i<n-1 && s.charAt(i+1)=='L'){
                        ans += 40;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='C'){
                        ans += 90;
                        i++;
                    } else ans+= 10;
                    
                    break;
                case 'C':
                    if(i<n-1 && s.charAt(i+1)=='D'){
                        ans += 400;
                        i++;
                    } else if(i<n-1 && s.charAt(i+1)=='M'){
                        ans += 900;
                        i++;
                    } else ans += 100;
                    break;
                default:
                    ans += hm.get(s.charAt(i));
                    break;
            }    
        }
        return ans;
    }
}
```

---

---
## Quick Revision
Convert a Roman numeral string to its integer equivalent.
This is solved by iterating through the string and handling subtractive cases (like IV, IX) specially.

## Intuition
The core idea is that Roman numerals are generally additive (e.g., VI = 5 + 1 = 6). However, there are specific subtractive rules where a smaller numeral placed before a larger one indicates subtraction (e.g., IV = 5 - 1 = 4). When scanning from left to right, if we encounter a numeral that is smaller than the *next* numeral, we know it's a subtractive case. Otherwise, it's an additive case.

## Algorithm
1. Initialize a mapping (e.g., a HashMap) to store the integer values of individual Roman numeral characters (I=1, V=5, X=10, L=50, C=100, D=500, M=1000).
2. Initialize a variable `total_value` to 0.
3. Iterate through the input Roman numeral string `s` from left to right, using an index `i`.
4. For each character `s[i]`:
    a. Check if `i` is not the last character and if the value of `s[i]` is less than the value of `s[i+1]`.
    b. If it is a subtractive case (condition in 4a is true):
        i. Calculate the combined value (value of `s[i+1]` - value of `s[i]`).
        ii. Add this combined value to `total_value`.
        iii. Increment `i` by an extra step (to skip the next character, as it's already been processed).
    c. If it's not a subtractive case (condition in 4a is false):
        i. Add the value of `s[i]` to `total_value`.
5. After the loop finishes, `total_value` will hold the integer representation of the Roman numeral. Return `total_value`.

## Concept to Remember
*   **Hash Maps/Dictionaries:** Efficiently storing and retrieving key-value pairs (Roman numeral character to its integer value).
*   **String Traversal:** Iterating through characters of a string.
*   **Conditional Logic:** Handling different cases based on character values and their positions.

## Common Mistakes
*   **Not handling subtractive cases:** Forgetting that 'IV' is 4, not 6, or 'IX' is 9, not 11.
*   **Incorrectly advancing the index:** When a subtractive pair is found, failing to increment the index by two, leading to double-counting or incorrect calculations.
*   **Off-by-one errors:** Incorrectly handling the loop boundary conditions, especially when checking the next character.
*   **Missing base cases:** Not correctly mapping the standard Roman numeral values.

## Complexity Analysis
- Time: O(N) - reason The code iterates through the string `s` once. In the worst case, each character is visited and processed.
- Space: O(1) - reason The space used by the HashMap is constant because the number of Roman numeral characters is fixed (7 characters).

## Commented Code
```java
class Solution {
    public int romanToInt(String s) {
        // Create a HashMap to store the integer values of Roman numeral characters.
        HashMap<Character,Integer> hm = new HashMap<>();
        // Populate the HashMap with standard Roman numeral values.
        hm.put('V',5); // Value of 'V' is 5.
        hm.put('L',50); // Value of 'L' is 50.
        hm.put('D',500); // Value of 'D' is 500.
        hm.put('M',1000); // Value of 'M' is 1000.
        // Note: 'I', 'X', 'C' are handled specially due to subtractive rules.

        // Initialize an index variable 'i' to -1. This is a common pattern to start processing from the first character (index 0) within the loop.
        int i =-1;
        // Initialize the answer variable to store the total integer value.
        int ans =0;
        // Get the length of the input Roman numeral string.
        int n = s.length();

        // Loop through the string. The condition `i < n - 1` ensures we process up to the second-to-last character, allowing lookahead.
        while(i<n-1){
            // Increment the index to point to the current character being processed.
            i++;
            // Use a switch statement to handle different Roman numeral characters.
            switch(s.charAt(i)){
                case 'I': // If the current character is 'I'.
                    // Check if we are not at the last character AND the next character is 'V'.
                    if(i<n-1 && s.charAt(i+1)=='V'){
                        // This is the subtractive case 'IV', which equals 4.
                        ans += 4;
                        // Increment 'i' to skip the next character ('V') as it has been processed.
                        i++;
                    // Check if we are not at the last character AND the next character is 'X'.
                    } else if(i<n-1 && s.charAt(i+1)=='X'){
                        // This is the subtractive case 'IX', which equals 9.
                        ans += 9;
                        // Increment 'i' to skip the next character ('X') as it has been processed.
                        i++;
                    // If it's not a subtractive case with 'V' or 'X'.
                    } else {
                        // 'I' is simply added (e.g., 'III' or 'VI').
                        ans++; // 'I' has a value of 1.
                    }
                    break; // Exit the switch case for 'I'.
                case 'X': // If the current character is 'X'.
                    // Check if we are not at the last character AND the next character is 'L'.
                    if(i<n-1 && s.charAt(i+1)=='L'){
                        // This is the subtractive case 'XL', which equals 40.
                        ans += 40;
                        // Increment 'i' to skip the next character ('L').
                        i++;
                    // Check if we are not at the last character AND the next character is 'C'.
                    } else if(i<n-1 && s.charAt(i+1)=='C'){
                        // This is the subtractive case 'XC', which equals 90.
                        ans += 90;
                        // Increment 'i' to skip the next character ('C').
                        i++;
                    // If it's not a subtractive case with 'L' or 'C'.
                    } else {
                        // 'X' is simply added (e.g., 'XX' or 'LX').
                        ans+= 10; // 'X' has a value of 10.
                    }
                    break; // Exit the switch case for 'X'.
                case 'C': // If the current character is 'C'.
                    // Check if we are not at the last character AND the next character is 'D'.
                    if(i<n-1 && s.charAt(i+1)=='D'){
                        // This is the subtractive case 'CD', which equals 400.
                        ans += 400;
                        // Increment 'i' to skip the next character ('D').
                        i++;
                    // Check if we are not at the last character AND the next character is 'M'.
                    } else if(i<n-1 && s.charAt(i+1)=='M'){
                        // This is the subtractive case 'CM', which equals 900.
                        ans += 900;
                        // Increment 'i' to skip the next character ('M').
                        i++;
                    // If it's not a subtractive case with 'D' or 'M'.
                    } else {
                        // 'C' is simply added (e.g., 'CC' or 'DC').
                        ans += 100; // 'C' has a value of 100.
                    }
                    break; // Exit the switch case for 'C'.
                default: // For any other character (V, L, D, M) which are not part of subtractive rules from the left.
                    // Retrieve the integer value from the HashMap and add it to the answer.
                    ans += hm.get(s.charAt(i));
                    break; // Exit the default case.
            }
        }
        // Return the final calculated integer value.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the Subtractive Rule:** Clearly articulate the rule where a smaller numeral before a larger one means subtraction (IV=4, IX=9, XL=40, XC=90, CD=400, CM=900). This is the crux of the problem.
2.  **Walk Through an Example:** Use an example like "MCMXCIV" and trace your algorithm step-by-step, showing how you handle each character and the subtractive cases.
3.  **Consider Edge Cases:** Discuss what happens with single-character inputs (e.g., "I", "V"), or inputs with only additive values (e.g., "VI", "LX").
4.  **Alternative Approach:** Briefly mention an alternative approach where you iterate from right to left. This shows you've thought about the problem from multiple angles.

## Revision Checklist
- [ ] Understand Roman numeral system rules (additive and subtractive).
- [ ] Implement a mapping for Roman numeral values.
- [ ] Handle subtractive cases correctly (IV, IX, XL, XC, CD, CM).
- [ ] Iterate through the string and accumulate the total value.
- [ ] Ensure correct index management when handling subtractive pairs.
- [ ] Test with various valid Roman numerals.

## Similar Problems
*   Integer to Roman
*   Longest Common Prefix

## Tags
`String` `Hash Map` `Math`
