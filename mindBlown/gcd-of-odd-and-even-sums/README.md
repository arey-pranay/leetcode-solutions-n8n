# Gcd Of Odd And Even Sums

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Math` `Number Theory`  
**Time:** O(1)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int gcdOfOddEvenSums(int n) {
        return n;
        // 2*1 + 2*2 + 2*3 + 2*4
        // 2 * (n*(n+1)/2) = n*(n+1)

        // (2*1)-1 + (2*2)-1 + (2*3)-1 + (2*4)-1
        // (2* (n*(n+1)/2)) - n = n*(n+1) - n =  n*n + n - n = n*(n)

        // n divides both numbers always, and those quotients are adjacent, so n is the HCF or the GCD.
    }
}


```

---

---
## Quick Revision
This problem asks for the greatest common divisor (GCD) of the sum of the first `n` even numbers and the sum of the first `n` odd numbers. The solution leverages mathematical properties of arithmetic series.

## Intuition
The "aha moment" comes from recognizing the structure of the sums of even and odd numbers. The sum of the first `n` even numbers is `n*(n+1)`, and the sum of the first `n` odd numbers is `n*n`. The GCD of `n*(n+1)` and `n*n` is simply `n`, because `n` is a common factor, and the remaining factors `(n+1)` and `n` are consecutive integers, whose GCD is always 1.

## Algorithm
1. Calculate the sum of the first `n` even numbers. This sum is given by the formula `n * (n + 1)`.
2. Calculate the sum of the first `n` odd numbers. This sum is given by the formula `n * n`.
3. Find the greatest common divisor (GCD) of these two sums.
4. Observe that `n` is a common factor of both `n * (n + 1)` and `n * n`.
5. The remaining factors are `(n + 1)` and `n`. Since `n` and `n + 1` are consecutive integers, their GCD is always 1.
6. Therefore, the GCD of `n * (n + 1)` and `n * n` is `n * GCD(n + 1, n) = n * 1 = n`.
7. Return `n`.

## Concept to Remember
*   **Arithmetic Series:** Understanding how to calculate the sum of sequences with a constant difference.
*   **Properties of GCD:** Specifically, `GCD(a*c, b*c) = c * GCD(a, b)` and `GCD(k, k+1) = 1`.
*   **Mathematical Derivation:** The ability to derive or recall formulas for sums of arithmetic progressions.

## Common Mistakes
*   **Incorrect Sum Formulas:** Using wrong formulas for the sum of even or odd numbers.
*   **Brute-Force GCD Calculation:** Attempting to calculate the sums and then using a GCD algorithm (like Euclidean algorithm) instead of recognizing the mathematical shortcut.
*   **Off-by-One Errors:** Miscounting the number of terms or misapplying the formulas for `n` terms.
*   **Not Simplifying:** Failing to see that the GCD can be determined directly from the structure of the sums.

## Complexity Analysis
*   Time: O(1) - The solution involves a few constant-time arithmetic operations and returns a value directly.
*   Space: O(1) - No extra space is used beyond a few variables to hold intermediate calculations (though even those are not strictly necessary for the final result).

## Commented Code
```java
class Solution {
    public int gcdOfOddEvenSums(int n) {
        // The problem asks for the GCD of the sum of the first n even numbers
        // and the sum of the first n odd numbers.

        // Sum of the first n even numbers: 2 + 4 + 6 + ... + 2n
        // This can be written as 2 * (1 + 2 + 3 + ... + n)
        // The sum of the first n integers is n*(n+1)/2.
        // So, the sum of the first n even numbers is 2 * (n*(n+1)/2) = n*(n+1).
        // int sumOfEvens = n * (n + 1); // This line is not needed for the final result

        // Sum of the first n odd numbers: 1 + 3 + 5 + ... + (2n-1)
        // This is an arithmetic series with n terms, first term a1=1, and last term an=2n-1.
        // The sum is n/2 * (a1 + an) = n/2 * (1 + 2n-1) = n/2 * (2n) = n*n.
        // int sumOfOdds = n * n; // This line is not needed for the final result

        // We need to find GCD(sumOfEvens, sumOfOdds) which is GCD(n*(n+1), n*n).
        // Using the property GCD(a*c, b*c) = c * GCD(a, b), we can factor out 'n'.
        // GCD(n*(n+1), n*n) = n * GCD(n+1, n).

        // The GCD of two consecutive integers (n and n+1) is always 1.
        // For example, GCD(5, 6) = 1, GCD(10, 11) = 1.
        // This is because any common divisor of n and n+1 must also divide their difference, which is (n+1) - n = 1.
        // The only positive integer that divides 1 is 1.

        // So, GCD(n+1, n) = 1.
        // Therefore, GCD(n*(n+1), n*n) = n * 1 = n.

        // The function directly returns n, as it is the GCD of the two sums.
        return n;
    }
}
```

## Interview Tips
*   **Explain the Math:** Clearly articulate the formulas for the sums of even and odd numbers and how you derived the GCD.
*   **Don't Over-Engineer:** Recognize that a direct mathematical solution is more efficient than implementing a generic GCD algorithm.
*   **Handle Edge Cases (if applicable):** For this problem, `n` is usually assumed to be a positive integer. If constraints allowed `n=0` or negative `n`, you'd need to consider that, but for typical LeetCode "easy" problems, positive `n` is standard.
*   **Show Your Work:** Even though the code is simple, walk through the derivation on a whiteboard or in your head to demonstrate your thought process.

## Revision Checklist
- [ ] Understand the problem statement: GCD of sums of first `n` evens and first `n` odds.
- [ ] Recall/Derive sum of first `n` even numbers: `n*(n+1)`.
- [ ] Recall/Derive sum of first `n` odd numbers: `n*n`.
- [ ] Apply GCD property: `GCD(a*c, b*c) = c * GCD(a, b)`.
- [ ] Recognize `GCD(k, k+1) = 1`.
- [ ] Conclude that the GCD is `n`.
- [ ] Verify with small examples (e.g., n=1, n=2, n=3).

## Similar Problems
*   Sum of Even Numbers After Queries
*   Sum of Digits of a Number
*   Greatest Common Divisor of Strings

## Tags
`Math` `Number Theory` `Arithmetic`
