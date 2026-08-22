# Check Divisibility By Digit Sum And Product

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(log10(n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public boolean checkDivisibility(int n){
       int sum = 0;
       int prod =1; 
       int N = n;
        while(n!=0){
            int rem = n%10;
            sum+=rem;
            prod*=rem;  
            n/=10;
        }
        return N%(sum+prod)==0; 
    }
}

```

---

---
## Quick Revision
Given an integer `n`, determine if it is divisible by the sum of its digits and the product of its digits.
We calculate the sum and product of digits, then check the divisibility of the original number by their sum.

## Intuition
The problem asks us to perform two distinct calculations on the digits of a given number: their sum and their product. Once we have these two values, the final step is a straightforward divisibility check. The core idea is to iterate through the digits of the number, accumulating the sum and product as we go. The "aha moment" is realizing that we can extract digits using the modulo operator (`% 10`) and then reduce the number by integer division (`/ 10`) until the number becomes zero.

## Algorithm
1. Initialize `sum` to 0 and `prod` to 1. These will store the sum and product of the digits, respectively.
2. Store the original number `n` in a temporary variable, say `original_n`, because `n` will be modified during digit extraction.
3. While `n` is not equal to 0:
    a. Get the last digit of `n` using the modulo operator: `digit = n % 10`.
    b. Add `digit` to `sum`: `sum = sum + digit`.
    c. Multiply `prod` by `digit`: `prod = prod * digit`.
    d. Remove the last digit from `n` using integer division: `n = n / 10`.
4. After the loop, `sum` will hold the sum of digits and `prod` will hold the product of digits.
5. Check if `original_n` is divisible by `sum + prod`. This means `original_n % (sum + prod) == 0`.
6. Return `true` if divisible, `false` otherwise.

## Concept to Remember
*   **Digit Extraction:** Using the modulo operator (`% 10`) to get the last digit and integer division (`/ 10`) to remove the last digit is a standard technique for processing digits of a number.
*   **Accumulation:** Using variables to accumulate a sum or product over an iteration is a fundamental programming pattern.
*   **Divisibility Check:** The modulo operator (`%`) is used to determine if one number is perfectly divisible by another.

## Common Mistakes
*   **Modifying Original Number:** Forgetting to store the original number before starting the digit extraction loop, leading to an incorrect final divisibility check.
*   **Initialization of Product:** Initializing `prod` to 0 instead of 1. Multiplying by 0 will always result in 0, making the product incorrect.
*   **Handling Zero Digits:** If a digit is 0, the product will become 0. This is usually handled correctly by the logic, but it's worth noting that `sum + prod` might become just `sum` if `prod` is 0.
*   **Integer Overflow:** For very large input numbers, the `prod` could potentially overflow standard integer types. However, for typical LeetCode constraints on `int`, this is less likely to be an issue for this specific problem.

## Complexity Analysis
- Time: O(log10(n)) - The number of iterations in the `while` loop is proportional to the number of digits in `n`, which is logarithmic with respect to `n` (base 10).
- Space: O(1) - We only use a few extra variables (`sum`, `prod`, `original_n`, `rem`) regardless of the input size.

## Commented Code
```java
class Solution {
    public boolean checkDivisibility(int n){
       int sum = 0; // Initialize sum of digits to 0
       int prod =1; // Initialize product of digits to 1 (important for multiplication)
       int N = n; // Store the original number 'n' because 'n' will be modified
        while(n!=0){ // Loop until all digits of 'n' have been processed
            int rem = n%10; // Get the last digit of 'n'
            sum+=rem; // Add the last digit to the sum
            prod*=rem;  // Multiply the product by the last digit
            n/=10; // Remove the last digit from 'n' by integer division
        }
        // Check if the original number 'N' is divisible by the sum of its digits plus the product of its digits
        return N%(sum+prod)==0; 
    }
}
```

## Interview Tips
*   **Clarify Edge Cases:** Ask about constraints on `n`. What if `n` is 0? What if `n` has a 0 digit? (The current code handles 0 correctly: sum=0, prod=0, sum+prod=0. Division by zero would be an issue if `N` was also 0, but `0 % 0` is undefined. For `N > 0` and `sum+prod == 0`, it's division by zero. However, for `n=0`, the loop doesn't run, `sum=0`, `prod=1`, `N=0`. `0 % (0+1) == 0` which is true. This seems to be the intended behavior for `n=0`.)
*   **Walk Through Example:** Be prepared to trace the execution with a small number like 12 or 24.
*   **Explain Logic Clearly:** Articulate *why* you are using modulo and division for digit extraction.
*   **Consider Data Types:** Briefly mention potential overflow for `prod` if the input `n` could be extremely large, though for `int` it's usually fine.

## Revision Checklist
- [ ] Understand the problem statement: check divisibility by sum and product of digits.
- [ ] Implement digit extraction using `% 10` and `/ 10`.
- [ ] Correctly initialize `sum` to 0 and `prod` to 1.
- [ ] Preserve the original number for the final check.
- [ ] Handle the case where `sum + prod` might be 0 (though for positive `n` with at least one non-zero digit, `sum+prod` will be positive).

## Similar Problems
*   Reverse Integer
*   Palindrome Number
*   Sum of Digits in String
*   Number of Digits in a Number

## Tags
`Math` `Number Theory`
