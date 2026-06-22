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
This problem asks to divide two integers without using multiplication, division, or the modulo operator. The solution uses bit manipulation and repeated subtraction with doubling to achieve logarithmic time complexity.

## Intuition
The core idea is to avoid direct subtraction of the divisor repeatedly, which would be O(N) in the worst case. Instead, we can subtract multiples of the divisor. To make this efficient, we can use powers of two. If we can subtract `k * divisor` from the dividend, we can also subtract `2k * divisor`, `4k * divisor`, and so on, as long as these multiples fit within the remaining dividend. This "doubling" strategy allows us to subtract large chunks at a time, significantly reducing the number of operations.

A crucial detail is handling `Integer.MIN_VALUE`. Its absolute value exceeds `Integer.MAX_VALUE`. By converting both numbers to negative, we leverage the fact that the negative range in two's complement is one larger than the positive range, thus avoiding overflow issues when dealing with `Integer.MIN_VALUE`.

## Algorithm
1.  **Handle Edge Cases:**
    *   If the divisor `b` is 1, return the dividend `a` directly.
    *   If `a` is `Integer.MIN_VALUE` and `b` is -1, the result would overflow `Integer.MAX_VALUE`. Return `Integer.MAX_VALUE` as per problem constraints.
2.  **Determine Sign:** Calculate the sign of the final result. The result is positive if both `a` and `b` have the same sign, and negative otherwise. Store this in a boolean variable `sign`.
3.  **Convert to Negative:** Convert both `a` and `b` to their negative equivalents. This is important because `Integer.MIN_VALUE` cannot be represented as a positive integer in Java's `int` type. The negative range is larger, so this conversion helps avoid overflow.
4.  **Iterative Subtraction with Doubling:**
    *   Initialize the `ans` (quotient) to 0.
    *   While the (negative) dividend `a` is less than or equal to the (negative) divisor `b` (meaning `a` is "larger" or "less negative"):
        *   Initialize `x` to `b` (the current multiple of the divisor we are considering).
        *   Initialize `qt` (quotient contribution) to 1.
        *   **Inner Loop (Doubling):** While `x` is not too small (to prevent overflow when doubling) and `a` is less than or equal to `x` doubled (`x << 1`):
            *   Double `x` (`x <<= 1`).
            *   Double `qt` (`qt <<= 1`). This represents that we are now subtracting `2 * qt` times the original divisor.
        *   **Subtract Chunk:** Subtract the largest found multiple `x` from `a` (`a -= x`).
        *   **Add to Quotient:** Add the corresponding quotient contribution `qt` to `ans` (`ans += qt`).
5.  **Apply Sign:** If the `sign` determined earlier was false (meaning the result should be negative), negate `ans`.
6.  **Return Result:** Return `ans`.

## Concept to Remember
*   **Bitwise Operations:** Understanding left shift (`<<`) for efficient multiplication by powers of two.
*   **Two's Complement Representation:** How integers are represented in binary, particularly the asymmetry between positive and negative ranges (`Integer.MIN_VALUE` vs. `Integer.MAX_VALUE`).
*   **Algorithm Optimization:** Using a "doubling" or "binary exponentiation" like approach to reduce the number of subtractions from linear to logarithmic.
*   **Edge Case Handling:** Recognizing and correctly addressing potential overflows and special values like `Integer.MIN_VALUE`.

## Common Mistakes
*   **Integer Overflow:** Not handling the `Integer.MIN_VALUE` case correctly, especially when converting to positive or when `a = Integer.MIN_VALUE` and `b = -1`.
*   **Inefficient Subtraction:** Performing direct subtraction of `b` from `a` in a loop, leading to O(N) time complexity instead of O(log N).
*   **Incorrect Sign Handling:** Forgetting to determine and apply the correct sign for the final result.
*   **Overflow in Doubling:** Not checking for potential overflow when doubling `x` in the inner loop, which could lead to incorrect `x` values. The condition `x >= (Integer.MIN_VALUE >> 1)` is crucial here.
*   **Off-by-One Errors:** Mismanaging the loop conditions or the quotient calculation.

## Complexity Analysis
*   **Time:** O(log N), where N is the absolute value of the dividend. The inner `while` loop doubles `x` in each iteration. This means `x` grows exponentially. The number of times `x` can be doubled before it exceeds the dividend is logarithmic. The outer loop also makes progress by subtracting `x`, and the total number of subtractions is bounded by the number of bits in the integer, making the overall complexity logarithmic.
*   **Space:** O(1). The algorithm uses a constant amount of extra space for variables like `sign`, `ans`, `x`, and `qt`.

## Commented Code
```java
class Solution {
    public int divide(int a, int b) {
        // Fast path: division by 1 is trivial.
        if (b == 1) {
            return a;
        }

        // Handle overflow case: Integer.MIN_VALUE / -1 results in Integer.MAX_VALUE.
        // This is because abs(Integer.MIN_VALUE) is 2^31, which is one greater than Integer.MAX_VALUE (2^31 - 1).
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign of answer. The result is positive if both numbers have the same sign, negative otherwise.
        boolean sign = (a > 0 && b > 0) || (a < 0 && b < 0);

        // Convert both numbers to negative.
        // This is a crucial step to handle Integer.MIN_VALUE correctly.
        // The negative range in two's complement is larger than the positive range (e.g., -2^31 to 2^31 - 1).
        // So, -Integer.MIN_VALUE can be represented as a negative number, but Integer.MAX_VALUE + 1 cannot be represented as a positive number.
        a = a > 0 ? -a : a; // If 'a' is positive, make it negative. Otherwise, keep it negative.
        b = b > 0 ? -b : b; // If 'b' is positive, make it negative. Otherwise, keep it negative.

        int ans = 0; // Initialize the quotient (answer) to 0.

        // Repeatedly subtract the largest possible multiple of divisor from the dividend.
        // The loop continues as long as the dividend 'a' is "greater than or equal to" the divisor 'b' (in terms of negative values, this means 'a' is less negative or equal to 'b').
        while (a <= b) {
            int x = b;      // 'x' will store the current multiple of the divisor 'b' we are trying to subtract. Start with 'b' itself.
            int qt = 1;    // 'qt' stores the corresponding quotient contribution for the current multiple 'x'. Initially, it's 1 * b.

            // Keep doubling the divisor multiple 'x' and its quotient contribution 'qt'
            // as long as 'x' can be doubled without overflowing (checked by x >= (Integer.MIN_VALUE >> 1))
            // AND the doubled 'x' can still fit within the remaining dividend 'a' (checked by a <= (x << 1)).
            // The condition `x >= (Integer.MIN_VALUE >> 1)` is a safe way to check if `x << 1` would overflow.
            // `Integer.MIN_VALUE >> 1` is the smallest negative number that can be safely left-shifted once.
            while (x >= (Integer.MIN_VALUE >> 1) && a <= (x << 1)) {
                x <<= 1;    // Double the current multiple of the divisor.
                qt <<= 1;  // Double the corresponding quotient contribution.
            }

            // Once the largest possible multiple 'x' is found that fits into 'a',
            // subtract this chunk from the dividend.
            a -= x;
            // Add the quotient contribution 'qt' for this chunk to the total answer.
            ans += qt;
        }

        // Apply the correct sign to the final answer.
        // If 'sign' is true (original numbers had same sign), return 'ans' as is.
        // If 'sign' is false (original numbers had different signs), return the negative of 'ans'.
        return sign ? ans : -ans;
    }
}
```

## Interview Tips
1.  **Explain the `Integer.MIN_VALUE` Handling:** This is the most critical part of the problem. Clearly articulate why converting to negative numbers is necessary and how it avoids overflow.
2.  **Describe the Doubling Strategy:** Explain how repeatedly subtracting the divisor is inefficient and how doubling the divisor (and quotient) allows for a logarithmic solution. Use an example to illustrate.
3.  **Walk Through an Example:** Pick a simple example (e.g., 10 / 3 or -10 / 3) and trace the algorithm step-by-step, showing how `a`, `b`, `x`, `qt`, and `ans` change.
4.  **Discuss Time Complexity:** Be ready to justify why the solution is O(log N) and not O(N). Highlight the role of the inner doubling loop.
5.  **Clarify Constraints:** Ask about constraints on input values (e.g., are they guaranteed to be within `int` range? What about division by zero, though this problem statement implies `b != 0` implicitly by not mentioning it as an edge case to handle).

## Revision Checklist
- [ ] Understand the problem: Divide two integers without standard operators.
- [ ] Identify `Integer.MIN_VALUE` overflow issue.
- [ ] Implement conversion to negative numbers for safe handling.
- [ ] Develop the doubling strategy for efficient subtraction.
- [ ] Correctly determine and apply the final sign.
- [ ] Handle the `Integer.MIN_VALUE / -1` edge case.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution clearly.

## Similar Problems
*   [Multiply Strings](https://leetcode.com/problems/multiply-strings/) (similar constraint of not using built-in multiplication)
*   [Pow(x, n)](https://leetcode.com/problems/powx-n/) (uses exponentiation by squaring, a related concept to doubling)
*   [Integer Replacement](https://leetcode.com/problems/integer-replacement/) (involves bit manipulation and conditional logic)

## Tags
`Bit Manipulation` `Math` `Two Pointers`
