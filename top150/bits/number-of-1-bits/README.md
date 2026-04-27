# Number Of 1 Bits

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Divide and Conquer` `Bit Manipulation`  
**Time:** O(log n)  
**Space:** O(1)

---

## Solution (java)

```java
// class Solution {
//     public int hammingWeight(int n) {
//         return Integer.bitCount(n);
//     }
// }
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while(n>0){
            if(n%2==1){
                count++;
            } 
            n /= 2; // this means right shift
        }
        return count;
    }
}
// 10100
// 1010
// 101 c++
// 10
// 1 c++
// 0

```

---

---
## Quick Revision
Given an integer, count the number of set bits (1s) in its binary representation.
This can be solved by repeatedly checking the least significant bit and right-shifting the number.

## Intuition
The core idea is to examine each bit of the integer. We can do this by looking at the least significant bit (LSB) and then shifting the number to the right to bring the next bit into the LSB position. The LSB can be checked using the modulo operator with 2 (or a bitwise AND with 1).

## Algorithm
1. Initialize a counter `count` to 0.
2. While the input integer `n` is greater than 0:
   a. Check if the least significant bit of `n` is 1. This can be done by checking if `n % 2 == 1`.
   b. If the LSB is 1, increment `count`.
   c. Right-shift `n` by one position. In Java, this is equivalent to integer division by 2 (`n /= 2`).
3. Return `count`.

## Concept to Remember
*   Binary Representation of Integers
*   Bitwise Operations (specifically, checking the LSB and right shifting)
*   Looping and Conditional Logic

## Common Mistakes
*   Forgetting to handle the case where the input `n` is 0.
*   Incorrectly implementing the right-shift operation (e.g., using a logical right shift when an arithmetic right shift is intended, though for positive integers they behave the same).
*   Off-by-one errors in the loop condition or counter increment.
*   Not considering the sign bit for negative numbers if the problem statement implies unsigned integers (though Java's `int` is signed, this problem usually implies treating it as a 32-bit unsigned value).

## Complexity Analysis
- Time: O(log n) or O(k), where k is the number of bits in the integer (typically 32 for an `int`). The loop runs once for each bit that needs to be examined until `n` becomes 0.
- Space: O(1) - We only use a constant amount of extra space for the `count` variable.

## Commented Code
```java
class Solution {
    public int hammingWeight(int n) {
        // Initialize a counter to store the number of set bits.
        int count = 0;
        // Loop as long as the number 'n' is positive.
        // This ensures we process all bits that could be set.
        while(n > 0){
            // Check if the least significant bit (LSB) is 1.
            // n % 2 == 1 is equivalent to (n & 1) == 1.
            if(n % 2 == 1){
                // If the LSB is 1, increment the count of set bits.
                count++;
            }
            // Right-shift 'n' by one position.
            // This effectively divides 'n' by 2 and discards the LSB,
            // bringing the next bit to the LSB position for the next iteration.
            n /= 2;
        }
        // Return the total count of set bits found.
        return count;
    }
}
```

## Interview Tips
*   Explain your thought process clearly, starting with the basic idea of checking each bit.
*   Mention the alternative bitwise approach (`n & 1` and `n >>> 1` for unsigned right shift) as it's more idiomatic for bit manipulation.
*   Discuss the time and space complexity of your solution.
*   Be prepared to discuss how you would handle negative numbers if the problem implied treating the `int` as an unsigned 32-bit integer.

## Revision Checklist
- [ ] Understand the problem: count set bits in binary.
- [ ] Algorithm: iterate, check LSB, right shift.
- [ ] Edge cases: `n=0`.
- [ ] Complexity: O(log n) time, O(1) space.
- [ ] Alternative bitwise approach.

## Similar Problems
*   Reverse Bits
*   Single Number
*   Missing Number

## Tags
`Bit Manipulation` `Math`
