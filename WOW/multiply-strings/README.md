# Multiply Strings

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `String` `Simulation`  
**Time:** O(m * n)  
**Space:** O(m + n)

---

## Solution (java)

```java
class Solution {
    public String multiply(String num1, String num2) {
        int l1 = num1.length();
        int l2 = num2.length();
        int[] result = new int[l1 + l2]; // store digits of the answer. no. of digits in product of two numbers can not exceed the sum of their digits

        //  12 => i=1
        // 126 => j=2
        // res => l1+l2 = 5;
        //the product of 2 single-digit numbers can never exceed 2 digits. so any digit will not affect any result digit beyond its left neighbour
        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {

                int n1 = num1.charAt(i) - '0'; //2,2,2,1
                int n2 = num2.charAt(j) - '0'; //6,2,1,6
                int prod = n1 * n2; //12 , 4 , 2, 6,2,6

                int p1 = i + j, p2 = i + j + 1;
                int sum = prod + result[p2]; // product 18 aaya, pehle se 6 rkha tha, to ab 24 hogya. usme 2 carry, and 4 assign firse.

                result[p1] += sum / 10; //carry           
                result[p2] = sum % 10; // ones place          
            }
        }

       
        StringBuilder sb = new StringBuilder();
        for (int digit : result)
            if (sb.isEmpty() && digit == 0) continue;
            else sb.append(digit);
        
        return sb.isEmpty() ? "0" : sb.toString();
    }
}

 // [_, _, 2 , 5, 2]
        //     1   2  6  x
        // 0   1  2  3  4
        
//                                         1         2      3
//                                         4         5      6
// ------------------------------------------------------------
//     ->                             (m-3*n-1)     (m-2*n-1)  m-1*n-1 
// +   ->             (m-3 * n-2)     (m-2 * n-2)   (m-1*n-2)  X
// +   ->(m-3 * n-3)  (m-2 * n-3)     (m-1*n-3)        X       X
// -----------------------------------------------------------------

// -----------------------------------------------------------------

//   ( .. + carry)   ((n*m-1 + m*n-1 + carry)%10 = 8 -> carry2) ((m*n)%10 = 8 -> carry1)


//
```

---

---
## Quick Revision
Multiply two non-negative integers represented as strings without using built-in BigInteger or converting to integers.
The solution simulates manual multiplication by processing digits from right to left and handling carries.

## Intuition
The core idea is to mimic the manual multiplication process we learned in school. When we multiply two numbers, say 123 by 45, we multiply each digit of the bottom number (45) by the entire top number (123) and then sum up these intermediate results, aligning them correctly. This problem simplifies this by directly calculating the contribution of each pair of digits from `num1` and `num2` to the final result array. The key insight is that the product of two digits at `num1[i]` and `num2[j]` will contribute to the result at indices `i + j` and `i + j + 1` in a result array of size `len(num1) + len(num2)`.

## Algorithm
1. Initialize an integer array `result` of size `l1 + l2` (where `l1` and `l2` are lengths of `num1` and `num2`) with all zeros. This array will store the digits of the final product.
2. Iterate through `num1` from right to left (index `i` from `l1 - 1` to `0`).
3. For each digit in `num1`, iterate through `num2` from right to left (index `j` from `l2 - 1` to `0`).
4. Convert the characters `num1.charAt(i)` and `num2.charAt(j)` to their integer values (`n1` and `n2`).
5. Calculate the product `prod = n1 * n2`.
6. Determine the positions in the `result` array where this product will contribute: `p1 = i + j` and `p2 = i + j + 1`. `p2` is the "ones" place for this specific digit multiplication, and `p1` is the "tens" place.
7. Add the current `prod` to the existing value at `result[p2]`. This `result[p2]` might already contain a carry from a previous multiplication. Let this sum be `sum`.
8. Update `result[p1]` by adding the carry-over from `sum` (`sum / 10`).
9. Update `result[p2]` with the remainder of `sum` (`sum % 10`). This ensures that `result[p2]` only holds a single digit.
10. After iterating through all digit pairs, the `result` array will contain the digits of the product, possibly with leading zeros.
11. Construct a `StringBuilder` to form the final string. Iterate through the `result` array.
12. Skip leading zeros: if the `StringBuilder` is empty and the current digit is `0`, continue to the next digit. Otherwise, append the digit.
13. If the `StringBuilder` is still empty after processing all digits (meaning the result was `0`), return `"0"`. Otherwise, return the string representation of the `StringBuilder`.

## Concept to Remember
*   **Manual Multiplication Simulation:** Understanding how to break down the multiplication of large numbers into digit-by-digit operations.
*   **Array Indexing for Products:** The crucial insight that the product of digits at `num1[i]` and `num2[j]` affects `result[i+j]` and `result[i+j+1]`.
*   **Carry Handling:** Correctly managing and propagating carries during addition and multiplication.

## Common Mistakes
*   **Incorrect Index Mapping:** Miscalculating `p1` and `p2` for storing the product and carry.
*   **Handling Leading Zeros:** Failing to correctly skip leading zeros in the final result, leading to outputs like "0123" instead of "123".
*   **Integer Overflow:** If intermediate products were stored in standard integer types without considering the maximum possible value (though this problem's constraints usually prevent this if `result` array is used correctly).
*   **Off-by-One Errors:** Errors in loop bounds or array indexing when processing strings or the result array.

## Complexity Analysis
*   **Time:** O(m * n) - where m and n are the lengths of `num1` and `num2`. This is because we have nested loops iterating through each digit of both numbers.
*   **Space:** O(m + n) - for the `result` array, which stores the digits of the product. The `StringBuilder` also takes up to O(m + n) space.

## Commented Code
```java
class Solution {
    public String multiply(String num1, String num2) {
        // Get the lengths of the two input strings.
        int l1 = num1.length();
        int l2 = num2.length();
        
        // Initialize an integer array to store the result digits.
        // The maximum possible length of the product of two numbers with lengths l1 and l2 is l1 + l2.
        // For example, 99 * 99 = 9801 (2 digits * 2 digits = 4 digits).
        int[] result = new int[l1 + l2]; 

        // Iterate through the first number (num1) from right to left.
        // 'i' represents the index of the current digit in num1.
        for (int i = l1 - 1; i >= 0; i--) {
            // Iterate through the second number (num2) from right to left.
            // 'j' represents the index of the current digit in num2.
            for (int j = l2 - 1; j >= 0; j--) {

                // Convert the character digit to its integer value.
                // Subtracting '0' from a character digit gives its integer representation.
                int n1 = num1.charAt(i) - '0'; // e.g., '2' - '0' = 2
                int n2 = num2.charAt(j) - '0'; // e.g., '6' - '0' = 6
                
                // Calculate the product of the two current digits.
                int prod = n1 * n2; // e.g., 2 * 6 = 12

                // Determine the positions in the result array where this product will contribute.
                // The product of digits at indices i and j will affect result[i+j] (tens place) and result[i+j+1] (ones place).
                // Example: num1="12", num2="34".
                // i=1 (digit '2'), j=1 (digit '4'). prod = 8. p1=1+1=2, p2=1+1+1=3. result[2] and result[3].
                // i=0 (digit '1'), j=1 (digit '4'). prod = 4. p1=0+1=1, p2=0+1+1=2. result[1] and result[2].
                int p1 = i + j;     // Index for the tens place of the current product.
                int p2 = i + j + 1; // Index for the ones place of the current product.

                // Add the current product to the existing value at result[p2].
                // result[p2] might already hold a carry from a previous multiplication.
                // Example: If result[p2] was 6 and prod is 12, sum becomes 18.
                int sum = prod + result[p2]; 

                // Update result[p1] with the carry from the sum.
                // sum / 10 gives the carry. This carry is added to the existing value at result[p1].
                // Example: If sum is 18, sum / 10 is 1. This 1 is added to result[p1].
                result[p1] += sum / 10; 
                
                // Update result[p2] with the ones digit of the sum.
                // sum % 10 gives the remainder (the ones digit).
                // Example: If sum is 18, sum % 10 is 8. result[p2] becomes 8.
                result[p2] = sum % 10; 
            }
        }

        // Build the final string representation of the product.
        StringBuilder sb = new StringBuilder();
        
        // Iterate through the result array to construct the string.
        for (int digit : result) {
            // Skip leading zeros. If the StringBuilder is empty and the current digit is 0, do nothing.
            // This prevents outputs like "00123".
            if (sb.isEmpty() && digit == 0) {
                continue; // Skip this leading zero.
            } else {
                // Append the digit to the StringBuilder.
                sb.append(digit);
            }
        }
        
        // If the StringBuilder is empty after processing (meaning the result was 0), return "0".
        // Otherwise, return the string representation of the StringBuilder.
        return sb.isEmpty() ? "0" : sb.toString();
    }
}
```

## Interview Tips
*   **Explain the Manual Process:** Start by explaining how you would multiply numbers manually on paper. This shows your understanding of the underlying logic.
*   **Focus on Index Mapping:** Clearly articulate why `result[i+j]` and `result[i+j+1]` are the correct indices for the product of `num1[i]` and `num2[j]`.
*   **Handle Edge Cases:** Be prepared to discuss how you handle cases like multiplying by zero, or when one of the numbers is "0". The leading zero handling is crucial here.
*   **Ask Clarifying Questions:** If unsure about constraints (e.g., maximum length of strings, character set), ask.

## Revision Checklist
- [ ] Understand the problem: Multiply strings without built-in functions.
- [ ] Recall manual multiplication steps.
- [ ] Identify the correct index mapping for digit products (`i+j`, `i+j+1`).
- [ ] Implement the nested loops for digit-by-digit multiplication.
- [ ] Correctly handle carry-overs.
- [ ] Implement logic to skip leading zeros in the final result.
- [ ] Handle the edge case where the result is "0".
- [ ] Analyze time and space complexity.

## Similar Problems
*   Add Two Numbers
*   Plus One
*   String to Integer (atoi)

## Tags
`Array` `Math` `String` `Simulation`
