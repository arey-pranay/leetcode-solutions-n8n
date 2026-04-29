# Factorial Trailing Zeroes

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Math`  
**Time:** O(log n)  
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
This problem asks to count the number of trailing zeroes in n!.
We solve it by counting the factors of 5 in the prime factorization of n!.

## Intuition
Trailing zeroes in a factorial are created by factors of 10. A factor of 10 is formed by a pair of prime factors 2 and 5. Since there will always be more factors of 2 than 5 in the prime factorization of n!, we only need to count the number of factors of 5.

The number of factors of 5 can be found by summing up how many numbers up to n are divisible by 5, how many are divisible by 25 (which contribute an additional factor of 5), how many are divisible by 125 (contributing yet another factor of 5), and so on.

## Algorithm
1. Initialize a counter `count` to 0.
2. Initialize a variable `powerOf5` to 5.
3. While `n` is greater than or equal to `powerOf5`:
    a. Add `n / powerOf5` to `count`. This counts how many numbers up to `n` are divisible by `powerOf5`.
    b. Multiply `powerOf5` by 5 to consider the next higher power of 5 (25, 125, etc.).
4. Return `count`.

## Concept to Remember
*   **Prime Factorization:** Understanding that trailing zeroes are formed by factors of 10 (2 * 5).
*   **Divisibility Rules:** Recognizing that counting multiples of powers of 5 directly gives the number of factors of 5.
*   **Logarithmic Growth:** The number of factors of 5 grows much slower than n, allowing for an efficient solution.

## Common Mistakes
*   **Calculating Factorial Directly:** Trying to compute n! and then counting zeroes is infeasible for large n due to overflow and time limits.
*   **Only Counting Multiples of 5:** Forgetting that numbers like 25, 125, etc., contribute multiple factors of 5.
*   **Integer Overflow:** Using `Math.pow` with large exponents can lead to precision issues or overflow if not handled carefully, though in this specific solution it's used in a loop that limits its magnitude. A more robust approach avoids `Math.pow` altogether.
*   **Off-by-one Errors:** Incorrectly handling the loop condition or the division.

## Complexity Analysis
- Time: O(log n) - The loop iterates through powers of 5 (5, 25, 125, ...), which grow exponentially. The number of iterations is logarithmic with respect to n.
- Space: O(1) - The algorithm uses a constant amount of extra space for variables like `count` and `powerOf5`.

## Commented Code
```java
class Solution {
    public int trailingZeroes(int n) {
        // Initialize the count of trailing zeroes to 0.
        int ans = 0;
        // Initialize a variable to represent powers of 5 (5, 25, 125, ...).
        // We start with 5 because each multiple of 5 contributes at least one factor of 5.
        long powerOf5 = 5; // Use long to prevent potential overflow for large n

        // Loop as long as n is greater than or equal to the current power of 5.
        // This ensures we consider all numbers up to n that are divisible by powerOf5.
        while (n >= powerOf5) {
            // Add the number of multiples of powerOf5 up to n to our count.
            // For example, if n=26 and powerOf5=5, n/powerOf5 = 5 (numbers 5, 10, 15, 20, 25).
            // If n=26 and powerOf5=25, n/powerOf5 = 1 (number 25).
            ans += (n / powerOf5);
            // Move to the next power of 5 (5*5=25, 25*5=125, etc.).
            // This accounts for numbers that contribute multiple factors of 5.
            powerOf5 *= 5;
        }
        // Return the total count of factors of 5, which equals the number of trailing zeroes.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the "Why":** Clearly articulate why counting factors of 5 is the correct approach, referencing the 2*5 = 10 relationship.
*   **Discuss Edge Cases:** Mention what happens for n=0 or small values of n.
*   **Avoid Naive Solution:** Be ready to explain why calculating n! directly is not a viable solution.
*   **Optimize `Math.pow`:** If you use `Math.pow`, be aware of its potential performance and precision implications. A loop multiplying by 5 is generally preferred.

## Revision Checklist
- [ ] Understand the problem: count trailing zeroes in n!.
- [ ] Identify the core concept: factors of 10 (2*5).
- [ ] Realize factors of 5 are the bottleneck.
- [ ] Develop the algorithm: sum of n/5^k.
- [ ] Consider edge cases (n=0).
- [ ] Analyze time and space complexity.
- [ ] Practice coding the solution efficiently.

## Similar Problems
*   LeetCode 157. Read N Characters Given Read4
*   LeetCode 165. Compare Version Numbers
*   LeetCode 172. Factorial Trailing Zeroes (This problem)
*   LeetCode 238. Product of Array Except Self

## Tags
`Math` `Number Theory`
