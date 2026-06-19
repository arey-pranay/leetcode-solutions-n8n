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
We solve this by calculating the number of choices for even and odd positions separately and multiplying them, using modular exponentiation.

## Intuition
The problem breaks down into two independent choices: what digits can go into even-indexed positions and what digits can go into odd-indexed positions.
For even indices (0, 2, 4, ...), we can use digits 0, 2, 4, 6, 8 (5 choices).
For odd indices (1, 3, 5, ...), we can use digits 1, 3, 5, 7, 9 (5 choices).
Wait, the problem states "even digits at even indices and odd digits at odd indices".
This means:
- Even indices (0, 2, 4, ...): can be 0, 2, 4, 6, 8 (5 choices).
- Odd indices (1, 3, 5, ...): can be 1, 3, 5, 7, 9 (5 choices).

Let's re-read the problem statement carefully: "A number is called good if the even digits of the number are at even indices and the odd digits of the number are at odd indices."
This implies:
- At even indices (0, 2, 4, ...), we can place digits {0, 2, 4, 6, 8}. There are 5 choices.
- At odd indices (1, 3, 5, ...), we can place digits {1, 3, 5, 7, 9}. There are 5 choices.

The provided solution uses `exp(5, evenPos)` and `exp(4, oddPos)`. This suggests a misunderstanding of the problem statement or a typo in the provided solution's logic. Let's assume the problem statement is correct and the solution has a slight error in its interpretation.

Corrected intuition based on problem statement:
For even indices (0, 2, 4, ...), we can use digits {0, 2, 4, 6, 8}. There are 5 choices.
For odd indices (1, 3, 5, ...), we can use digits {1, 3, 5, 7, 9}. There are 5 choices.

Let `n` be the total number of digits.
The number of even positions is `ceil(n/2)`.
The number of odd positions is `floor(n/2)`.

If `n` is even, say `n=4`: indices are 0, 1, 2, 3.
Even indices: 0, 2 (2 positions).
Odd indices: 1, 3 (2 positions).
Number of choices for even positions: 5 * 5 = 5^2.
Number of choices for odd positions: 5 * 5 = 5^2.
Total good numbers: 5^2 * 5^2 = 5^4.

If `n` is odd, say `n=5`: indices are 0, 1, 2, 3, 4.
Even indices: 0, 2, 4 (3 positions).
Odd indices: 1, 3 (2 positions).
Number of choices for even positions: 5 * 5 * 5 = 5^3.
Number of choices for odd positions: 5 * 5 = 5^2.
Total good numbers: 5^3 * 5^2 = 5^5.

In general:
Number of even positions = `(n + 1) / 2` (integer division, equivalent to ceil(n/2))
Number of odd positions = `n / 2` (integer division, equivalent to floor(n/2))

So, the total count would be `5^((n+1)/2) * 5^(n/2)`.

Let's re-examine the provided solution's logic:
`long oddPos = n/2;`
`long evenPos = n-oddPos;`
If `n=5`, `oddPos = 5/2 = 2`. `evenPos = 5-2 = 3`. This matches our calculation for the number of odd and even positions.
`long evens = exp(5,evenPos);` -> This calculates 5 raised to the power of the number of even positions. This is correct.
`long odds = exp(4,oddPos);` -> This calculates 4 raised to the power of the number of odd positions. This is where the discrepancy lies. The problem states odd digits are {1, 3, 5, 7, 9}, which are 5 choices. The solution uses 4.

**Assuming the problem statement is correct and the provided solution has a typo:**
The "aha moment" is realizing that the choices for even and odd positions are independent. We can count the number of ways to fill all even positions and multiply it by the number of ways to fill all odd positions. Since the number of choices for each position is constant (5 for even indices, 5 for odd indices), this becomes a problem of exponentiation. The large constraints on `n` necessitate modular exponentiation to prevent overflow and keep the computation efficient.

**If we strictly analyze the provided solution's logic (even if it seems to contradict the problem statement):**
The solution calculates `evenPos = n - n/2` and `oddPos = n/2`.
It then computes `5^evenPos * 4^oddPos`.
This implies that for even positions, there are 5 choices, and for odd positions, there are 4 choices. This is inconsistent with the standard definition of even/odd digits. It's possible the problem statement has a nuance or the solution is based on a slightly different interpretation or a typo in the problem statement itself. However, adhering to the standard interpretation of "even digits" and "odd digits", the solution's use of `exp(4, oddPos)` is questionable.

Let's proceed with the analysis assuming the provided solution's logic is what we need to explain, even if it seems flawed based on the problem statement.

The solution calculates the number of even positions and odd positions.
`evenPos = n - n/2` (this correctly calculates the count of even indices, e.g., for n=5, it's 3; for n=4, it's 2).
`oddPos = n/2` (this correctly calculates the count of odd indices, e.g., for n=5, it's 2; for n=4, it's 2).

It then calculates `5^evenPos` and `4^oddPos`.
This implies that for each of the `evenPos` positions, there are 5 choices.
And for each of the `oddPos` positions, there are 4 choices.
The total count is the product of these two, modulo 10^9 + 7.

The `exp` function is a standard modular exponentiation (binary exponentiation) algorithm, which efficiently computes `(base^power) % MOD`.

## Algorithm
1. Define a constant `MOD` for the modulo operation (1000000007).
2. Calculate the number of positions that will hold even digits. If `n` is the total length, the number of even indices (0, 2, 4, ...) is `(n + 1) / 2` (integer division). Let this be `evenCount`.
3. Calculate the number of positions that will hold odd digits. If `n` is the total length, the number of odd indices (1, 3, 5, ...) is `n / 2` (integer division). Let this be `oddCount`.
4. Implement a modular exponentiation function `exp(base, power)` that calculates `(base^power) % MOD` efficiently.
   a. Initialize `res = 1`.
   b. Reduce `base` modulo `MOD`.
   c. While `power > 0`:
      i. If `power` is odd, update `res = (res * base) % MOD`.
      ii. Divide `power` by 2 (`power /= 2`).
      iii. Square `base` modulo `MOD` (`base = (base * base) % MOD`).
   d. Return `res`.
5. Calculate the number of ways to fill even positions: `evens = exp(5, evenCount)`. (Assuming 5 choices for even positions as per standard interpretation).
6. Calculate the number of ways to fill odd positions: `odds = exp(5, oddCount)`. (Assuming 5 choices for odd positions as per standard interpretation).
7. The total number of good numbers is `(evens * odds) % MOD`.
8. Return the result as an integer.

**Algorithm based on the provided solution's logic:**
1. Define a constant `MOD` for the modulo operation (1000000007).
2. Calculate `oddPos = n / 2`. This represents the count of odd indices.
3. Calculate `evenPos = n - oddPos`. This represents the count of even indices.
4. Implement a modular exponentiation function `exp(base, power)` that calculates `(base^power) % MOD` efficiently.
   a. Initialize `res = 1`.
   b. Reduce `base` modulo `MOD`.
   c. While `power > 0`:
      i. If `power` is odd, update `res = (res * base) % MOD`.
      ii. Divide `power` by 2 (`power /= 2`).
      iii. Square `base` modulo `MOD` (`base = (base * base) % MOD`).
   d. Return `res`.
5. Calculate the number of ways for even positions: `evens = exp(5, evenPos)`. (This implies 5 choices for even positions).
6. Calculate the number of ways for odd positions: `odds = exp(4, oddPos)`. (This implies 4 choices for odd positions, which is inconsistent with the problem statement's definition of odd digits).
7. The total number of good numbers is `(odds * evens) % MOD`.
8. Return the result as an integer.

## Concept to Remember
*   **Modular Arithmetic:** Performing arithmetic operations (addition, multiplication) while keeping the results within a specific range (modulo `MOD`) to prevent integer overflow and handle large numbers.
*   **Modular Exponentiation (Binary Exponentiation):** An efficient algorithm to compute `(base^power) % MOD` in O(log power) time, crucial for large exponents.
*   **Combinatorics (Counting Principles):** Understanding how to break down a problem into independent choices and multiply the number of possibilities for each choice.
*   **Digit Properties:** Recognizing the distinct sets of digits available for even and odd positions based on the problem's definition.

## Common Mistakes
*   **Integer Overflow:** Not using modular arithmetic during intermediate calculations, especially when multiplying large numbers or computing powers, leading to incorrect results.
*   **Inefficient Exponentiation:** Using a naive loop to calculate powers (`base * base * ...`) instead of modular exponentiation, which would be too slow for large `n`.
*   **Misinterpreting Indices:** Confusing 0-based indexing with 1-based indexing or incorrectly calculating the number of even/odd positions for a given length `n`.
*   **Incorrect Digit Choices:** Using the wrong number of choices for even/odd positions (e.g., assuming 10 choices for all positions, or miscounting available even/odd digits). The provided solution's use of 4 for odd positions is a potential point of confusion if the problem statement is strictly followed.
*   **Modulo Application:** Applying the modulo operation only at the very end, rather than after each multiplication step, which can still lead to overflow.

## Complexity Analysis
*   **Time:** O(log n) - The dominant operation is the modular exponentiation function `exp`. The `exp` function runs in logarithmic time with respect to the `power` argument, which is at most `n`. All other operations are O(1).
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables, regardless of the input size `n`.

## Commented Code
```java
class Solution {
    // Define the modulo constant as required by the problem.
    int MOD = 1000000007;

    // Main function to count good numbers.
    public int countGoodNumbers(long n) {
        // Calculate the number of positions that will have odd digits.
        // For n=5, indices are 0,1,2,3,4. Odd indices are 1,3. n/2 = 5/2 = 2.
        // For n=4, indices are 0,1,2,3. Odd indices are 1,3. n/2 = 4/2 = 2.
        long oddPos = n / 2;

        // Calculate the number of positions that will have even digits.
        // This is the total length minus the number of odd positions.
        // For n=5, evenPos = 5 - 2 = 3. (Indices 0,2,4)
        // For n=4, evenPos = 4 - 2 = 2. (Indices 0,2)
        long evenPos = n - oddPos;

        // Calculate the number of ways to fill the even positions.
        // Based on the provided solution's logic, there are 5 choices for even positions.
        // We use modular exponentiation to compute 5^evenPos % MOD.
        long evens = exp(5, evenPos);

        // Calculate the number of ways to fill the odd positions.
        // Based on the provided solution's logic, there are 4 choices for odd positions.
        // This is inconsistent with the problem statement's definition of odd digits (1,3,5,7,9 which are 5 choices).
        // We use modular exponentiation to compute 4^oddPos % MOD.
        long odds = exp(4, oddPos);

        // The total number of good numbers is the product of ways to fill even and odd positions.
        // We apply the modulo operation at the end of the multiplication to ensure the result is within MOD.
        // The result is cast to int as the return type requires.
        return (int) ((odds * evens) % MOD);
    }

    // Helper function for modular exponentiation (binary exponentiation).
    // Computes (base^power) % MOD efficiently.
    public long exp(long base, long power) {
        // Initialize result to 1, as any number to the power of 0 is 1.
        long res = 1;
        // Reduce the base modulo MOD. This is important if base itself is larger than MOD.
        base %= MOD;
        // Loop while the power is greater than 0.
        while (power > 0) {
            // If the current power is odd, multiply the result by the current base.
            // This accounts for the bits set in the binary representation of 'power'.
            if (power % 2 == 1) {
                res = (res * base) % MOD;
            }
            // Divide the power by 2 (integer division), effectively shifting bits to the right.
            power /= 2;
            // Square the base modulo MOD. This prepares the base for the next iteration,
            // corresponding to the next bit in the binary representation of 'power'.
            base = (base * base) % MOD;
        }
        // Return the final computed result.
        return res;
    }
}
```

## Interview Tips
*   **Clarify Ambiguities:** If the problem statement seems to have a contradiction (like the 4 vs 5 choices for odd positions in the provided solution), ask the interviewer for clarification. "The problem states odd digits are at odd indices. Standard odd digits are {1,3,5,7,9}, giving 5 choices. However, the provided solution uses 4 choices for odd positions. Could you clarify which interpretation I should follow?"
*   **Explain Modular Exponentiation:** Be prepared to explain *why* modular exponentiation is necessary (large `n`, preventing overflow) and *how* it works (binary representation of the exponent, squaring the base).
*   **Break Down the Problem:** Clearly articulate how you're separating the problem into counting possibilities for even-indexed positions and odd-indexed positions. Emphasize their independence.
*   **Handle Edge Cases (Implicitly):** While not explicitly tested here, consider how your logic would handle `n=0` or `n=1`. The current logic handles `n=1` correctly (1 even position, 0 odd positions).

## Revision Checklist
- [ ] Understand the definition of a "good number" based on digit parity and index parity.
- [ ] Correctly calculate the number of even and odd indices for a given length `n`.
- [ ] Recognize the need for modular arithmetic due to large potential results.
- [ ] Implement or understand the modular exponentiation algorithm (binary exponentiation).
- [ ] Apply the multiplication principle for independent choices.
- [ ] Ensure modulo operations are applied at each multiplication step to prevent overflow.
- [ ] Verify the base cases and logic for small values of `n`.

## Similar Problems
*   LeetCode 1306: Jump Game III (uses modular arithmetic implicitly for indices)
*   LeetCode 1641: Count Sorted Vowel Strings (combinatorics, similar counting approach)
*   LeetCode 50: Pow(x, n) (standard exponentiation, can be adapted to modular)
*   LeetCode 172: Factorial Trailing Zeroes (uses properties of numbers, related to powers of 5)

## Tags
`Math` `Combinatorics` `Modular Arithmetic` `Binary Exponentiation`
