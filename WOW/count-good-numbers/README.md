# Count Good Numbers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Recursion`  
**Time:** O(log n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    int MOD = 1000000007;
    public int countGoodNumbers(long n) {
        long oddPos = n/2;
        long evenPos = n-oddPos;
        long evens = exp(5,evenPos);
        long odds = exp(4,oddPos);
        return (int) ((odds*evens)%MOD);
    }
    public long exp(long base, long power){
        long res = 1;
        base %= MOD;
        while(power > 0){
            if(power % 2 == 1) res = (res*base)%MOD;
            power /=2;
            base = (base*base)%MOD;
        }
        return res;
    }
}
```

---

---
## Quick Revision
A good number has even digits at even indices and odd digits at odd indices.
We solve this by calculating the number of choices for even and odd positions separately and multiplying them, using modular exponentiation to handle large numbers.

## Intuition
The problem breaks down into two independent choices: what digits can go into even positions and what digits can go into odd positions.
For even positions (0, 2, 4, ...), we can use digits 0, 2, 4, 6, 8 (5 choices).
For odd positions (1, 3, 5, ...), we can use digits 1, 3, 5, 7, 9 (4 choices).
The total number of good numbers is the product of the number of ways to fill all even positions and the number of ways to fill all odd positions. Since `n` can be very large, we need to use modular arithmetic to prevent overflow, and modular exponentiation is the efficient way to calculate powers under a modulus.

## Algorithm
1.  Determine the number of even positions and odd positions. If `n` is the total length, `evenPos = (n + 1) / 2` and `oddPos = n / 2`.
2.  Calculate the number of ways to fill the even positions. This is 5 raised to the power of `evenPos` (5^evenPos), modulo 1000000007.
3.  Calculate the number of ways to fill the odd positions. This is 4 raised to the power of `oddPos` (4^oddPos), modulo 1000000007.
4.  Multiply the results from steps 2 and 3, and take the modulo 1000000007.
5.  Implement a modular exponentiation function (`exp` or `power`) to efficiently compute `base^power % MOD`. This function should handle large powers by using the property `(a*b)%m = ((a%m)*(b%m))%m` and binary exponentiation (exponentiation by squaring).

## Concept to Remember
*   **Modular Arithmetic:** Performing arithmetic operations (addition, multiplication) within a fixed range (modulo) to prevent integer overflow, especially with large numbers.
*   **Modular Exponentiation (Exponentiation by Squaring):** An efficient algorithm to compute `(base^power) % modulus` in O(log power) time.
*   **Combinatorics (Multiplication Principle):** If there are `m` ways to do one thing and `n` ways to do another, there are `m * n` ways to do both.

## Common Mistakes
*   **Integer Overflow:** Not using modular arithmetic when calculating powers or intermediate products, leading to incorrect results for large `n`.
*   **Incorrectly Calculating Even/Odd Positions:** Miscounting the number of even and odd indices for a given `n`. For example, `n/2` for odd positions and `n - n/2` for even positions is a common and correct way.
*   **Inefficient Power Calculation:** Using a naive loop to calculate powers (e.g., `Math.pow` or a simple `for` loop) which would be too slow for large exponents.
*   **Forgetting Modulo at Each Step:** Applying the modulo operation only at the very end, rather than after each multiplication in the modular exponentiation and final product calculation.

## Complexity Analysis
- Time: O(log n) - The dominant operation is the modular exponentiation function (`exp`), which takes logarithmic time with respect to the power (which is proportional to `n`).
- Space: O(1) - The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    // Define the modulus as a constant for repeated use.
    int MOD = 1000000007;

    // Main function to count good numbers.
    public int countGoodNumbers(long n) {
        // Calculate the number of positions that will have odd digits.
        // These are the odd indices: 1, 3, 5, ...
        // For n=5, indices are 0,1,2,3,4. Odd indices: 1, 3. Count = 2. n/2 = 5/2 = 2.
        long oddPos = n / 2;

        // Calculate the number of positions that will have even digits.
        // These are the even indices: 0, 2, 4, ...
        // For n=5, indices are 0,1,2,3,4. Even indices: 0, 2, 4. Count = 3. n-oddPos = 5-2 = 3.
        long evenPos = n - oddPos;

        // Calculate the number of ways to fill the even positions.
        // There are 5 choices for even positions (0, 2, 4, 6, 8).
        // We need to compute 5 raised to the power of evenPos, modulo MOD.
        long evens = exp(5, evenPos);

        // Calculate the number of ways to fill the odd positions.
        // There are 4 choices for odd positions (1, 3, 5, 7, 9).
        // We need to compute 4 raised to the power of oddPos, modulo MOD.
        long odds = exp(4, oddPos);

        // The total number of good numbers is the product of ways to fill even and odd positions.
        // We must take the modulo at this final multiplication step as well.
        // Cast the result to int as required by the function signature.
        return (int) ((odds * evens) % MOD);
    }

    // Helper function for modular exponentiation (calculates base^power % MOD).
    public long exp(long base, long power) {
        // Initialize result to 1, as any number to the power of 0 is 1.
        long res = 1;
        // Ensure the base is within the modulo range.
        base %= MOD;
        // Loop while there is still power to process.
        while (power > 0) {
            // If the current power is odd, multiply the result by the current base.
            // This is because power = 2k + 1, so base^power = base^(2k) * base^1.
            if (power % 2 == 1) {
                res = (res * base) % MOD;
            }
            // Halve the power for the next iteration.
            // This is equivalent to processing bits of the power from right to left.
            power /= 2;
            // Square the base for the next iteration.
            // This is because base^(2k) = (base^2)^k.
            base = (base * base) % MOD;
        }
        // Return the final computed result.
        return res;
    }
}
```

## Interview Tips
*   Clearly explain the logic of independent choices for even and odd positions.
*   Emphasize the need for modular arithmetic and why it's crucial for large inputs.
*   Be prepared to explain the modular exponentiation algorithm (exponentiation by squaring) and its time complexity.
*   Walk through a small example (e.g., n=3) to illustrate the calculation.

## Revision Checklist
- [ ] Understand the definition of a "good number".
- [ ] Identify the distinct choices for even and odd indices.
- [ ] Recognize the need for modular arithmetic.
- [ ] Implement modular exponentiation correctly.
- [ ] Handle edge cases or small values of `n`.
- [ ] Verify the calculation of `evenPos` and `oddPos`.

## Similar Problems
*   [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) (Basic exponentiation, can be extended to modular)
*   [1377. Frog Jump](https://leetcode.com/problems/frog-jump/) (Involves probabilities, but might touch upon large number calculations)
*   [1012. Numbers With Repeated Digits](https://leetcode.com/problems/numbers-with-repeated-digits/) (Digit DP problems often involve combinatorics and modular arithmetic)

## Tags
`Math` `Combinatorics` `Modular Arithmetic` `Exponentiation`
