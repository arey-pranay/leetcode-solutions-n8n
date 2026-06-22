# Divide Two Integers

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math` `Bit Manipulation`  
**Time:** O(log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int divide(int a, int b) {
        // Fast path: division by 1
        if (b == 1) {
            return a;
        }

        // Handle overflow case:
        // abs(Integer.MIN_VALUE) > Integer.MAX_VALUE
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign of answer
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);

        // Convert both numbers to negative.
        // Negative range is larger than positive range,
        // which helps avoid overflow for Integer.MIN_VALUE.
        a = a > 0 ? -a : a;
        b = b > 0 ? -b : b;

        int ans = 0;
// 2147483648
// -2147483648

// 32
// 2^31
// 0
// 2^31 -1

// 2 * 2^31 = 2^32
        // Repeatedly subtract the largest possible multiple of divisor
        // -10 
        // -3
        while (a <= b) {
            int x = b;      // current multiple of divisor
            int qt = 1;    // corresponding quotient contribution

            // Keep doubling divisor until it can no longer fit inside the remaining dividend
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;    // double divisor
                qt <<= 1;  // double quotient contribution
            }
// 3->6->12->24->48->96
// 96/3 = 32

            // Remove this chunk from dividend
            ans += qt;
            a -= x;
        }

        // Apply correct sign
        return sign ? ans : -ans;
    }
}
// Is approach mein hum dono numbers ko negative bana dete hain kyunki Integer.MIN_VALUE ka positive version represent nahi ho sakta aur negative range ek value zyada store kar sakti hai. Fir direct divisor ko baar-baar subtract karne ke bajay uska largest possible power-of-2 multiple (double, quadruple, etc.) dhoondte hain jo current dividend ke andar fit ho sake. Har step mein divisor ko repeatedly double karke sabse bada valid multiple nikalte hain, us multiple ko dividend se subtract kar dete hain aur quotient mein uska corresponding count add kar dete hain. Isse ek baar mein bahut bada chunk remove ho jata hai, isliye linear subtraction ki jagah logarithmic number of operations lagte hain. End mein shuru mein nikala hua sign apply karke answer return kar dete hain.
```

---

---
## Quick Revision
This problem asks to divide two integers without using multiplication, division, or the modulo operator.
The solution uses bit manipulation and repeated subtraction with doubling to achieve logarithmic time complexity.

## Intuition
The core idea is to avoid direct subtraction of the divisor `b` from the dividend `a` repeatedly, as this would be O(N) where N is the quotient. Instead, we want to subtract larger chunks. We can achieve this by repeatedly doubling the divisor `b` and keeping track of how many times we've doubled it (which corresponds to a power of 2 in the quotient). This allows us to subtract a significant portion of the divisor in each step, leading to a much faster, logarithmic time complexity.

A crucial detail is handling `Integer.MIN_VALUE`. Its absolute value is larger than `Integer.MAX_VALUE`, so converting it to positive can cause overflow. By converting both numbers to negative, we leverage the fact that the negative range in Java (`-2^31` to `-1`) is one element larger than the positive range (`0` to `2^31 - 1`). This allows us to safely represent `Integer.MIN_VALUE` as negative.

## Algorithm
1.  **Handle Edge Cases:**
    *   If the divisor `b` is 1, return the dividend `a` directly.
    *   If `a` is `Integer.MIN_VALUE` and `b` is -1, the result would be `Integer.MAX_VALUE` due to overflow. Return `Integer.MAX_VALUE`.
2.  **Determine the Sign:**
    *   Calculate the sign of the final result. It's positive if both `a` and `b` have the same sign, and negative otherwise. Store this in a boolean variable `sign`.
3.  **Convert to Negative:**
    *   Convert both `a` and `b` to their negative equivalents. This is to safely handle `Integer.MIN_VALUE` as its positive counterpart exceeds `Integer.MAX_VALUE`.
4.  **Iterative Subtraction with Doubling:**
    *   Initialize `ans` (the quotient) to 0.
    *   While the (negative) dividend `a` is less than or equal to the (negative) divisor `b`:
        *   Initialize `x` to `b` (the current multiple of the divisor we're considering).
        *   Initialize `qt` (the quotient contribution for this chunk) to 1.
        *   **Inner Loop (Doubling):** While `x` is greater than or equal to `Integer.MIN_VALUE >> 1` (to prevent `x << 1` from overflowing to positive) AND `a` is less than or equal to `x << 1` (meaning doubling `x` still fits within the remaining `a`):
            *   Double `x` (`x <<= 1`).
            *   Double `qt` (`qt <<= 1`).
        *   **Subtract Chunk:** Add `qt` to `ans`.
        *   Subtract `x` from `a` (`a -= x`).
5.  **Apply Sign:**
    *   If the determined `sign` was false (meaning the result should be negative), return `-ans`. Otherwise, return `ans`.

## Concept to Remember
*   **Bitwise Operations:** Understanding left shift (`<<`) for doubling and right shift (`>>`) for halving is crucial.
*   **Integer Overflow:** Recognizing and handling the special case of `Integer.MIN_VALUE` and its implications for absolute values and range.
*   **Logarithmic Time Complexity:** The strategy of doubling the divisor in each step reduces the number of operations significantly compared to linear subtraction.
*   **Two's Complement Representation:** Understanding how negative numbers are represented in binary and why the negative range is larger than the positive range.

## Common Mistakes
*   **Not handling `Integer.MIN_VALUE` correctly:** This is the most common pitfall, leading to overflow errors when trying to take the absolute value.
*   **Incorrectly determining the sign:** Mishandling the signs of the dividend and divisor can lead to a wrong final answer.
*   **Integer overflow during doubling:** The inner loop's condition `a <= (x << 1)` needs to be carefully checked to prevent `x << 1` from overflowing to a positive number or wrapping around incorrectly. The `x >= (Integer.MIN_VALUE >> 1)` check is vital for this.
*   **Using forbidden operators:** Accidentally using `*`, `/`, or `%` in the implementation.
*   **Inefficient subtraction:** Falling back to simple linear subtraction, resulting in a Time Limit Exceeded error for large quotients.

## Complexity Analysis
*   **Time:** O(log N), where N is the absolute value of the quotient. In each step of the outer `while` loop, we subtract a chunk that is at least half the size of the previous chunk (due to doubling). This is similar to binary search or exponentiation by squaring, where the number of operations grows logarithmically with the magnitude of the result.
*   **Space:** O(1). The algorithm uses a constant amount of extra space for variables like `sign`, `ans`, `x`, and `qt`.

## Commented Code
```java
class Solution {
    public int divide(int a, int b) {
        // Fast path: if the divisor is 1, the result is simply the dividend.
        if (b == 1) {
            return a;
        }

        // Handle the specific overflow case: Integer.MIN_VALUE divided by -1.
        // The result of Integer.MIN_VALUE / -1 is Integer.MAX_VALUE + 1, which overflows.
        // The problem statement usually implies returning Integer.MAX_VALUE in this scenario.
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the final result.
        // The result is positive if both numbers have the same sign, negative otherwise.
        // (a > 0 && b > 0) || (a < 0 && b < 0) checks for same signs.
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);

        // Convert both numbers to negative.
        // This is crucial because the range of negative integers in Java is larger than positive integers.
        // Specifically, Integer.MIN_VALUE (-2^31) cannot be represented as a positive integer (max is 2^31 - 1).
        // By working with negative numbers, we avoid potential overflow issues when taking absolute values.
        a = a > 0 ? -a : a; // If 'a' is positive, make it negative; otherwise, keep it as is.
        b = b > 0 ? -b : b; // If 'b' is positive, make it negative; otherwise, keep it as is.

        int ans = 0; // Initialize the quotient (answer) to 0.

        // The main loop: continue as long as the (negative) dividend 'a' is less than or equal to the (negative) divisor 'b'.
        // This means there's still a portion of 'b' that can be subtracted from 'a'.
        while (a <= b) {
            int x = b;      // 'x' represents the current multiple of the divisor 'b' we are considering. Start with 'b' itself.
            int qt = 1;    // 'qt' represents the quotient contribution for the current multiple 'x'. Initially, if x=b, qt=1.

            // Inner loop: This loop finds the largest possible multiple of 'b' (by doubling)
            // that can still be subtracted from 'a'.
            // Condition 1: `x >= (Integer.MIN_VALUE >> 1)`: This prevents `x << 1` from overflowing.
            //              If `x` is already very close to `Integer.MIN_VALUE`, doubling it might wrap around to a positive number.
            //              `Integer.MIN_VALUE >> 1` is the largest negative number whose double is still representable as a negative int.
            // Condition 2: `a <= (x << 1)`: This checks if doubling `x` still keeps it "smaller" (more negative) than or equal to `a`.
            //              If `a` is -10 and `x << 1` is -12, then -10 <= -12 is false, meaning -12 is too large to subtract.
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;    // Double the current multiple of the divisor.
                qt <<= 1;  // Double the corresponding quotient contribution.
            }

            // Once the inner loop finishes, 'x' is the largest power-of-2 multiple of 'b' that fits into 'a'.
            // Subtract this chunk 'x' from the dividend 'a'.
            a -= x;
            // Add the corresponding quotient contribution 'qt' to the total answer.
            ans += qt;
        }

        // Apply the correct sign to the calculated quotient.
        // If 'sign' is true (meaning original numbers had same sign), return 'ans' as is.
        // If 'sign' is false (meaning original numbers had different signs), return the negative of 'ans'.
        return sign ? ans : -ans;
    }
}
```

## Interview Tips
1.  **Clarify Constraints:** Ask about the range of input integers and the expected behavior for edge cases like division by zero (though this problem usually implies `b != 0`) or overflow.
2.  **Explain the Overflow Handling:** Clearly articulate *why* you are converting numbers to negative and the implications of `Integer.MIN_VALUE`. This shows a deep understanding of integer representation.
3.  **Walk Through the Doubling Logic:** Explain how doubling the divisor and quotient contribution speeds up the process from O(N) to O(log N). Use a small example like 10 / 3 to illustrate.
4.  **Discuss Time Complexity:** Be prepared to justify the O(log N) time complexity by explaining how the search space (or the amount subtracted) is halved in each effective step.
5.  **Consider Edge Cases First:** Always address the trivial cases (like `b=1`) and the problematic overflow case (`MIN_VALUE / -1`) upfront.

## Revision Checklist
- [ ] Understand the problem: divide without `*`, `/`, `%`.
- [ ] Identify `Integer.MIN_VALUE` overflow issue.
- [ ] Strategy: Convert to negative numbers.
- [ ] Strategy: Use repeated subtraction with doubling.
- [ ] Implement sign determination.
- [ ] Implement the outer `while (a <= b)` loop.
- [ ] Implement the inner `while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1))` loop.
- [ ] Correctly update `a`, `x`, `qt`, and `ans`.
- [ ] Apply the final sign.
- [ ] Test with edge cases: `MIN_VALUE`, `MAX_VALUE`, `b=1`, `b=-1`, `a=0`.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Multiply Strings
*   Pow(x, n)
*   Integer Replacement
*   Reverse Integer

## Tags
`Bit Manipulation` `Math` `Binary Search`
