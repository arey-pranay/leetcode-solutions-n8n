# Concatenate Non Zero Digits And Multiply By Sum I

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Math` `Number Theory` `Digit Manipulation`  
**Time:** O(log10(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public long sumAndMultiply(int n) {
        int j=0;
        long x = 0;
        while(n!=0){
            long digit = n%10;
            n/=10;
            if(digit != 0) x += digit * Math.pow(10,j++);
        }
        long sum = 0;
        long temp = x;
        while(temp!=0){
            long  digit = temp%10;
            temp/=10;
            sum += digit;
        }
        return sum*x;
    }
}
```

---

---
## Quick Revision
Given an integer `n`, construct a new number by concatenating its non-zero digits in their original order. Then, calculate the sum of the digits of this new number and return the product of the new number and its sum.
The solution involves iterating through the digits of `n`, building the new number, and then calculating the sum of its digits.

## Intuition
The core idea is to process the digits of the input number `n` one by one. We need to extract each digit, check if it's non-zero, and if so, append it to a new number. The appending process requires careful handling of place values (powers of 10). Once the new number is formed, we need to sum its digits. Finally, we multiply the new number by its digit sum.

## Algorithm
1. Initialize a variable `newNumber` to 0 (this will store the concatenated non-zero digits).
2. Initialize a variable `powerOf10` to 1 (this will help in placing digits correctly in `newNumber`).
3. Iterate while `n` is not equal to 0:
    a. Extract the last digit of `n` using the modulo operator: `digit = n % 10`.
    b. Remove the last digit from `n` by integer division: `n = n / 10`.
    c. If `digit` is not 0:
        i. Add `digit * powerOf10` to `newNumber`.
        ii. Multiply `powerOf10` by 10 to prepare for the next digit.
4. Initialize a variable `digitSum` to 0.
5. Create a temporary variable `tempNumber` and assign `newNumber` to it.
6. Iterate while `tempNumber` is not equal to 0:
    a. Extract the last digit of `tempNumber`: `currentDigit = tempNumber % 10`.
    b. Remove the last digit from `tempNumber`: `tempNumber = tempNumber / 10`.
    c. Add `currentDigit` to `digitSum`.
7. Return `newNumber * digitSum`.

## Concept to Remember
*   **Digit Extraction:** Using the modulo operator (`% 10`) to get the last digit and integer division (`/ 10`) to remove it.
*   **Place Value Manipulation:** Understanding how to construct a new number by correctly multiplying digits with powers of 10.
*   **Iterative Processing:** Solving problems by repeatedly applying an operation until a condition is met.

## Common Mistakes
*   **Incorrectly handling place values:** Forgetting to multiply digits by the correct power of 10 when constructing the new number.
*   **Integer Overflow:** Using `int` for intermediate calculations or the final result when `long` is required, especially with larger input numbers.
*   **Modifying `n` while calculating `newNumber`:** If `n` is modified in a way that affects its original structure before `newNumber` is fully built, it can lead to errors.
*   **Not handling the case where `n` becomes 0:** The loop condition should correctly terminate.

## Complexity Analysis
- Time: O(log10(n)) - The number of operations is proportional to the number of digits in `n`, which is logarithmic with respect to `n`. We iterate through the digits of `n` twice.
- Space: O(1) - The space used is constant, as we only use a few variables regardless of the input size.

## Commented Code
```java
class Solution {
    public long sumAndMultiply(int n) {
        // Initialize j to 0. This variable will track the power of 10 for placing digits.
        int j = 0;
        // Initialize x to 0. This will store the new number formed by concatenating non-zero digits.
        long x = 0;
        // Loop until n becomes 0, meaning all digits have been processed.
        while (n != 0) {
            // Extract the last digit of n.
            long digit = n % 10;
            // Remove the last digit from n.
            n /= 10;
            // Check if the extracted digit is not zero.
            if (digit != 0) {
                // If the digit is non-zero, append it to x by multiplying with the appropriate power of 10.
                // Math.pow(10, j) calculates 10 raised to the power of j.
                // j++ increments j for the next non-zero digit's place value.
                x += digit * Math.pow(10, j++);
            }
        }
        // Initialize sum to 0. This will store the sum of digits of the new number x.
        long sum = 0;
        // Create a temporary variable temp to hold the value of x, so we don't modify x itself during summation.
        long temp = x;
        // Loop until temp becomes 0, meaning all digits of x have been processed.
        while (temp != 0) {
            // Extract the last digit of temp.
            long digit = temp % 10;
            // Remove the last digit from temp.
            temp /= 10;
            // Add the extracted digit to the sum.
            sum += digit;
        }
        // Return the product of the new number x and its digit sum.
        return sum * x;
    }
}
```

## Interview Tips
*   **Clarify Input/Output:** Ask about the constraints on `n` (e.g., positive, negative, range) and the expected return type (e.g., `long` to avoid overflow).
*   **Walk Through Examples:** Use a small example like `n = 10203` to demonstrate how your algorithm constructs `newNumber` (which would be `30201` if processed from right to left and then reversed, or `10203` if processed from left to right and appended - the problem implies right-to-left processing for digit extraction and then building the number). The provided code builds the number in reverse order of digit appearance in `n` but correctly places them based on their original position. For `n=10203`, `x` becomes `30201`. The sum of digits of `30201` is `3+0+2+0+1 = 6`. The result is `30201 * 6`.
*   **Edge Cases:** Consider `n = 0`, `n` with all zeros (e.g., `1000`), `n` with no zeros (e.g., `123`), and single-digit numbers.
*   **Data Types:** Be mindful of potential integer overflows. The problem statement implies `long` is necessary for the result.

## Revision Checklist
- [ ] Understand the problem statement clearly.
- [ ] Identify how to extract digits from an integer.
- [ ] Implement logic to build the new number with non-zero digits.
- [ ] Handle place values correctly when building the new number.
- [ ] Implement logic to sum the digits of the new number.
- [ ] Ensure appropriate data types (`long`) are used to prevent overflow.
- [ ] Test with edge cases (0, all zeros, no zeros, single digit).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Reverse Integer
*   Palindrome Number
*   String to Integer (atoi)
*   Number of Digits in a Number

## Tags
`Math` `Number Theory` `Digit Manipulation`
