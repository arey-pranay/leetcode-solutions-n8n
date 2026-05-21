# Integer To Roman

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Hash Table` `Math` `String`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder("");// 
        while(num >0) num = func(num,sb);
        return sb.toString();
    }
    public int func(int num,StringBuilder sb){
        int pow = (int) Math.log10(num);
        if(Integer.toString(num).charAt(0)=='4'){
            if(pow == 2){sb.append("CD"); return num-400;}
            if(pow == 1){sb.append("XL"); return num-40;}
            sb.append("IV");return num-4;
        }else if(Integer.toString(num).charAt(0)=='9'){
            if(pow == 2){sb.append("CM"); return num-900;}
            if(pow == 1){sb.append("XC"); return num-90;}
            sb.append("IX");return num-9;
        }
        if(num >= 1000){sb.append("M");return num-1000;}
        if(num >= 500){ sb.append("D");return num-500;}
        if(num >= 100){ sb.append("C");return num-100;}
        if(num >= 50){ sb.append("L");return num-50;}
        if(num >= 10){ sb.append("X");return num-10;}
        if(num >= 5){ sb.append("V");return num-5;}        
        sb.append("I"); return num-1;
    }
}




// // class Solution {
// //     public String intToRoman(int num) {
// //         String res = "";
// //         int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
// //         String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
// //         for (int i = 0; i < values.length; i++) {
// //             while (num >= values[i]) {
// //                 num -= values[i];
// //                 res += symbols[i];
// //             }
// //         }
// //         return res;
// //     }
// // }
// class Solution {
//     public String intToRoman(int num) {
        
//         StringBuilder ans = new StringBuilder();
//         int curMul = 1;
//         int curDigit = 0;
//         while (num != 0) {
//             curDigit = num % 10;

//             if (curDigit == 9) {
//                 ans.insert(0, getRomanNumeral(curMul * 10));
//                 ans.insert(0, getRomanNumeral(1 * curMul));
//             }
//             else if (curDigit == 4) {
//                 ans.insert(0, getRomanNumeral(curMul * 5));
//                 ans.insert(0, getRomanNumeral(1 * curMul));
//             }
//             else if (curDigit > 4) {
//                 curDigit -= 5;
//                 addCharacters(ans, getRomanNumeral(1 * curMul), curDigit);
//                 ans.insert(0, getRomanNumeral(5 * curMul));
//             }
//             else {
//                 addCharacters(ans, getRomanNumeral(1 * curMul), curDigit);
//             }

//             curMul *= 10;
//             num /= 10;
//         }
//         return ans.toString();
//     }

//     private void addCharacters(StringBuilder ans, char c, int digit) {
//         for (int i = 0; i < digit; i++) {
//             ans.insert(0, c);
//         }
//     }

//     private char getRomanNumeral(int x) {
//         if (x == 1) return 'I';

//         if (x == 5) return 'V';

//         if (x == 10) return 'X';

//         if (x == 50) return 'L';

//         if (x == 100) return 'C';

//         if (x == 500) return 'D';

//         return 'M';
//     }
// }
```

---

---
## Quick Revision
Convert an integer to its Roman numeral representation.
This is solved by greedily subtracting the largest possible Roman numeral values from the integer.

## Intuition
The core idea is that Roman numerals are constructed by combining symbols representing specific values. The key to conversion is to process the integer from largest to smallest place values, and for each place value, to use the largest possible Roman numeral symbol that fits. For example, to represent 1994, we'd first take the largest symbol less than or equal to 1994, which is 'M' (1000), leaving 994. Then, we'd look for the largest symbol less than or equal to 994, which is 'CM' (900), leaving 94. We continue this process until the number becomes zero. The special cases for 4 (IV) and 9 (IX) and their larger counterparts (XL, XC, CD, CM) are crucial for an efficient and correct representation.

## Algorithm
1. Create a mapping of integer values to their corresponding Roman numeral symbols. This mapping should be ordered from largest value to smallest, including the subtractive combinations (e.g., 900 for "CM").
2. Initialize an empty string or `StringBuilder` to store the resulting Roman numeral.
3. Iterate through the ordered mapping. For each integer value and its symbol:
    a. While the input integer `num` is greater than or equal to the current integer value:
        i. Subtract the integer value from `num`.
        ii. Append the corresponding Roman numeral symbol to the result.
4. Once the loop finishes (meaning `num` has become 0), return the constructed Roman numeral string.

## Concept to Remember
*   **Greedy Algorithm:** Making the locally optimal choice at each step to achieve a global optimum. Here, we always pick the largest possible Roman numeral value.
*   **Place Value System:** Understanding how numbers are represented based on the position of digits (thousands, hundreds, tens, ones).
*   **Roman Numeral System Rules:** Familiarity with the standard symbols (I, V, X, L, C, D, M) and the subtractive notation (IV, IX, XL, XC, CD, CM).

## Common Mistakes
*   **Not handling subtractive cases:** Forgetting to include "IV", "IX", "XL", "XC", "CD", "CM" in the mapping, leading to incorrect representations like "IIII" instead of "IV".
*   **Incorrect ordering of values:** If the mapping is not ordered from largest to smallest, the greedy approach will fail. For example, if 100 comes before 900, you might incorrectly use "DCCCC" instead of "CM".
*   **Integer overflow/underflow:** While less common for this specific problem's constraints, it's a general consideration for numerical manipulations.
*   **Inefficient string concatenation:** Repeatedly concatenating strings in a loop can be slow in some languages. Using a `StringBuilder` is generally preferred.

## Complexity Analysis
*   **Time:** O(1) - The input integer `num` is constrained (typically up to 3999). The number of Roman numeral symbols and their values is fixed and small. The `while` loop for each symbol will execute at most a few times (e.g., for 'M', it can run at most 3 times for 3000). Therefore, the total number of operations is constant with respect to the input integer's magnitude within its typical constraints.
*   **Space:** O(1) - The space used is for the `StringBuilder` to store the result. The maximum length of a Roman numeral for numbers up to 3999 is fixed (e.g., "MMMCMXCIX" is 15 characters). Thus, the space complexity is constant.

## Commented Code
```java
class Solution {
    // The main method to convert an integer to a Roman numeral.
    public String intToRoman(int num) {
        // Use StringBuilder for efficient string manipulation, as we'll be appending characters.
        StringBuilder sb = new StringBuilder("");
        // Loop as long as the number is greater than 0, meaning there are still digits to convert.
        while(num > 0) {
            // Call a helper function 'func' to process the number and append Roman numerals to the StringBuilder.
            // 'func' also returns the remaining value of 'num' after subtracting the largest possible Roman numeral.
            num = func(num, sb);
        }
        // Convert the StringBuilder to a String and return it.
        return sb.toString();
    }

    // Helper function to find the largest Roman numeral symbol that can be subtracted from 'num'
    // and append it to the StringBuilder 'sb'. It returns the updated 'num'.
    public int func(int num, StringBuilder sb) {
        // Calculate the power of 10 corresponding to the most significant digit.
        // For example, if num is 1994, log10(1994) is approx 3.28, so pow will be 3.
        // This helps in determining the place value (thousands, hundreds, tens, ones).
        int pow = (int) Math.log10(num);

        // Check for the special case of '4' at the current most significant digit.
        // Integer.toString(num).charAt(0) gets the first digit of the number.
        if (Integer.toString(num).charAt(0) == '4') {
            // If the number is in the hundreds (e.g., 400-499), append "CD".
            if (pow == 2) { sb.append("CD"); return num - 400; }
            // If the number is in the tens (e.g., 40-49), append "XL".
            if (pow == 1) { sb.append("XL"); return num - 40; }
            // If the number is in the ones (e.g., 4), append "IV".
            sb.append("IV"); return num - 4;
        }
        // Check for the special case of '9' at the current most significant digit.
        else if (Integer.toString(num).charAt(0) == '9') {
            // If the number is in the hundreds (e.g., 900-999), append "CM".
            if (pow == 2) { sb.append("CM"); return num - 900; }
            // If the number is in the tens (e.g., 90-99), append "XC".
            if (pow == 1) { sb.append("XC"); return num - 90; }
            // If the number is in the ones (e.g., 9), append "IX".
            sb.append("IX"); return num - 9;
        }

        // Handle standard Roman numeral values from largest to smallest.
        // If the number is 1000 or more, append "M" and subtract 1000.
        if (num >= 1000) { sb.append("M"); return num - 1000; }
        // If the number is 500 or more (but less than 1000), append "D" and subtract 500.
        if (num >= 500) { sb.append("D"); return num - 500; }
        // If the number is 100 or more (but less than 500), append "C" and subtract 100.
        if (num >= 100) { sb.append("C"); return num - 100; }
        // If the number is 50 or more (but less than 100), append "L" and subtract 50.
        if (num >= 50) { sb.append("L"); return num - 50; }
        // If the number is 10 or more (but less than 50), append "X" and subtract 10.
        if (num >= 10) { sb.append("X"); return num - 10; }
        // If the number is 5 or more (but less than 10), append "V" and subtract 5.
        if (num >= 5) { sb.append("V"); return num - 5; }
        // If the number is 1 or more (but less than 5), append "I" and subtract 1.
        sb.append("I"); return num - 1;
    }
}
```

## Interview Tips
1.  **Explain the Greedy Approach:** Clearly articulate why picking the largest possible Roman numeral value at each step is the correct strategy.
2.  **Discuss Subtractive Notation:** Emphasize the importance of handling cases like 4 (IV) and 9 (IX) correctly, as these are common pitfalls.
3.  **Consider Data Structures:** Mention that a map or arrays can be used to store the integer-to-Roman mappings. Discuss the trade-offs of different structures (e.g., ordered arrays are good for greedy iteration).
4.  **Edge Cases:** Briefly touch upon the constraints of the input (e.g., numbers up to 3999) and how they simplify the problem.

## Revision Checklist
- [ ] Understand the Roman numeral system and its rules.
- [ ] Implement a greedy approach by iterating through values from largest to smallest.
- [ ] Correctly handle subtractive notations (IV, IX, XL, XC, CD, CM).
- [ ] Use `StringBuilder` for efficient string construction.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Roman to Integer (LeetCode 13)
*   Integer to English Words (LeetCode 273)

## Tags
`Math` `String` `Greedy`
