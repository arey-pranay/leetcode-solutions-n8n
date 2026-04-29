# Factorial Trailing Zeroes

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(log₅ n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int trailingZeroes(int n) {
        int i=1;
        int ans = 0;
        while(n>=Math.pow(5,i)) ans += (n/(Math.pow(5,i++)));
        return ans;
    }
}
// trailing zero ke liye we need to count 10 kitni baar aayega a*b*c*d... ki sequence me
// for every 10, we need 2 and 5. 2 to har even number se aajayega, 5 se zyaada hi baar
// so we just need to find, kitni baar 5 aayega is poori multiplication sequence me

// 5 , 25 , 125 se cross hua to jitna usse divide ho rha hai utna badha dena hota hai (5 helps make 1 10. 25 helps make 2 tens, 125 helps make 3 tens, etc) 
//jese 20 tk to 20/4 hi zeroes aayenge kyonki 5 hi utne bann paaye hai 
//lekin 30 tk jb hum aagye to 25 m do 5 aagye na to islie
```

---

---
## Quick Revision
This problem asks to count the number of trailing zeros in n!.
We solve it by counting the occurrences of the prime factor 5 in the factorial.

## Intuition
Trailing zeros in a factorial are formed by pairs of 2 and 5 in its prime factorization, as 2 * 5 = 10. Since the factor 2 appears much more frequently than the factor 5 in any factorial (every even number contributes a 2, while only multiples of 5 contribute a 5), the number of trailing zeros is limited by the number of times 5 appears as a prime factor.

## Algorithm
1. Initialize a counter `count` to 0.
2. Iterate through powers of 5 (5, 25, 125, etc.) as long as the current power of 5 is less than or equal to `n`.
3. For each power of 5, calculate how many multiples of that power exist up to `n`. This is done by integer division: `n / power_of_5`.
4. Add this result to the `count`. Each multiple of 5 contributes at least one factor of 5. Multiples of 25 contribute an *additional* factor of 5 (since 25 = 5 * 5), multiples of 125 contribute yet another factor of 5, and so on.
5. Return the final `count`.

## Concept to Remember
*   Prime Factorization: Understanding that trailing zeros are formed by factors of 10, which are 2 * 5.
*   Factorial Properties: Recognizing that the frequency of prime factors in a factorial can be determined by analyzing multiples.
*   Divisibility Rules: Applying integer division to count multiples of a number.

## Common Mistakes
*   Calculating the factorial directly: Factorials grow extremely large very quickly, leading to overflow issues even with `long` data types.
*   Only counting multiples of 5: This misses the additional factors of 5 contributed by multiples of 25, 125, etc.
*   Incorrectly handling powers of 5: Using floating-point `Math.pow` can lead to precision errors; integer arithmetic is preferred.
*   Loop termination condition: Not iterating through all necessary powers of 5.

## Complexity Analysis
- Time: O(log₅ n) - The loop iterates through powers of 5 (5, 25, 125, ...), which grows logarithmically with `n`.
- Space: O(1) - The algorithm uses a constant amount of extra space for variables.

## Commented Code
```java
class Solution {
    public int trailingZeroes(int n) {
        // Initialize the count of trailing zeros to 0.
        int count = 0;
        // Initialize a variable 'powerOf5' to 1 to represent 5^0.
        // We will multiply this by 5 in each iteration to get 5^1, 5^2, etc.
        long powerOf5 = 5; // Use long to prevent overflow for large powers of 5

        // Loop as long as the current power of 5 is less than or equal to n.
        // This ensures we consider all multiples of 5, 25, 125, etc., up to n.
        while (n >= powerOf5) {
            // Add the number of multiples of the current 'powerOf5' to the count.
            // Integer division n / powerOf5 gives us how many times 'powerOf5' fits into 'n'.
            // For example, if n=26 and powerOf5=5, n/powerOf5 = 5 (multiples are 5, 10, 15, 20, 25).
            // If n=26 and powerOf5=25, n/powerOf5 = 1 (multiple is 25).
            count += (n / powerOf5);

            // Move to the next power of 5.
            // Check for potential overflow before multiplying.
            if (powerOf5 > Integer.MAX_VALUE / 5) {
                break; // Prevent overflow if powerOf5 * 5 exceeds Integer.MAX_VALUE
            }
            powerOf5 *= 5;
        }
        // Return the total count of trailing zeros.
        return count;
    }
}
```

## Interview Tips
*   Explain the "why" behind counting factors of 5. Don't just jump to the formula.
*   Discuss the overflow issue if you were to calculate the factorial directly.
*   Clarify the logic for multiples of 25, 125, etc., contributing *additional* factors of 5.
*   Be prepared to discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the relationship between trailing zeros and factors of 10.
- [ ] Identify that factors of 5 are the limiting factor.
- [ ] Implement the logic to count multiples of 5, 25, 125, etc.
- [ ] Consider potential integer overflow issues.
- [ ] Analyze time and space complexity.

## Similar Problems
*   [Number of 1 Bits](https://leetcode.com/problems/number-of-1-bits/)
*   [Power of Two](https://leetcode.com/problems/power-of-two/)
*   [Happy Number](https://leetcode.com/problems/happy-number/)

## Tags
`Math` `Number Theory`
