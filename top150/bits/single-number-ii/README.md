# Single Number Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Bit Manipulation`  
**Time:** O(n*k)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int singleNumber(int[] nums) {
        if (nums.length == 1) return nums[0];
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int ones = 0;
            for (int j = 0; j < nums.length; j++) {
                ones += nums[j] & 1;
                nums[j] >>= 1;
            }
            res |= (ones % 3) << i;  // place bit at position i directly
        }
        return res;
    }
}
```

---

---
## Quick Revision
Given an array of integers where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position across all numbers modulo 3.

## Intuition
The core idea is to consider the binary representation of each number. If we sum up the bits at each position (0th bit, 1st bit, etc.) across all numbers, and a number appears three times, its contribution to the sum at any bit position will be either 0 or 3. The single number's bit at that position will be either 0 or 1. Therefore, if the sum of bits at a particular position modulo 3 is 1, it means the single number has a 1 at that bit position. If the sum modulo 3 is 0, the single number has a 0 at that bit position.

## Algorithm
1. Initialize a result variable `res` to 0. This will store the single number.
2. Iterate through each bit position from 0 to 31 (for a 32-bit integer).
3. For each bit position `i`:
    a. Initialize a counter `ones` to 0. This will count the number of set bits at the current position `i` across all numbers.
    b. Iterate through each number `num` in the input array `nums`.
    c. Check the `i`-th bit of `num`. A simple way is to check the least significant bit (`num & 1`) and then right-shift `num` by 1 (`num >>= 1`) to process the next bit in the subsequent iteration.
    d. Add the least significant bit of `num` to `ones`.
    e. After iterating through all numbers for the current bit position `i`, calculate `ones % 3`.
    f. If `ones % 3` is 1, it means the single number has a 1 at this bit position. Set the `i`-th bit in `res` by ORing `res` with `(1 << i)`.
4. After iterating through all 32 bit positions, `res` will hold the single number. Return `res`.

## Concept to Remember
*   Bitwise Operations: Understanding `&` (AND), `|` (OR), `>>` (Right Shift), and `<<` (Left Shift) is crucial.
*   Modulo Arithmetic: The property that `(a + b) % m = ((a % m) + (b % m)) % m` is implicitly used.
*   Binary Representation of Integers: How numbers are represented in binary and how to access individual bits.
*   Handling Duplicates: Techniques to identify unique elements when others appear a fixed number of times.

## Common Mistakes
*   Incorrectly handling negative numbers: The bitwise approach generally works for signed integers due to two's complement representation, but one must be careful with sign extension if using arithmetic right shifts. The provided solution uses logical right shift implicitly for unsigned interpretation of bits.
*   Off-by-one errors in bit iteration: Ensuring all 32 bits are checked.
*   Misunderstanding the modulo 3 logic: Forgetting that the sum of bits modulo 3 reveals the single number's bit.
*   Modifying the input array in place when it's not allowed or intended: The provided solution modifies `nums` by right-shifting. If the original array must be preserved, a copy or a different approach would be needed.

## Complexity Analysis
- Time: O(n*k) - reason: We iterate through each of the `n` numbers 32 times (where `k=32` is the number of bits in an integer).
- Space: O(1) - reason: We only use a few extra variables (`res`, `ones`, loop counters), which do not depend on the input size.

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        // Handle the edge case where the array has only one element.
        if (nums.length == 1) return nums[0];
        
        // Initialize the result variable to 0. This will store the single number.
        int res = 0;
        
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for (int i = 0; i < 32; i++) {
            // Initialize a counter 'ones' to 0 for the current bit position.
            // This will count how many numbers have the i-th bit set.
            int ones = 0;
            
            // Iterate through each number in the input array.
            for (int j = 0; j < nums.length; j++) {
                // Check the least significant bit (LSB) of the current number.
                // If it's 1, add 1 to 'ones'.
                ones += nums[j] & 1;
                
                // Right-shift the current number by 1. This effectively moves the next bit to the LSB position
                // for the next iteration, and also prepares the number for processing the next bit position in the outer loop.
                // This modification is in-place.
                nums[j] >>= 1;
            }
            
            // After checking all numbers for the current bit position 'i':
            // If 'ones % 3' is 1, it means the single number has a 1 at this bit position.
            // If 'ones % 3' is 0, it means the single number has a 0 at this bit position.
            // We then left-shift this result (0 or 1) by 'i' positions to place it at the correct bit index in 'res'.
            // Finally, we OR it with 'res' to set the bit in our result.
            res |= (ones % 3) << i;
        }
        
        // After iterating through all 32 bit positions, 'res' will contain the single number.
        return res;
    }
}
```

## Interview Tips
*   Start by explaining the brute-force approach (e.g., using a hash map to count frequencies) and its complexity.
*   Then, introduce the bit manipulation idea. Walk through an example with small numbers (e.g., `[2, 2, 3, 2]`) to illustrate how summing bits modulo 3 works.
*   Be prepared to explain the bitwise operations (`&`, `>>`, `|`, `<<`) clearly.
*   Discuss the space complexity advantage of the bit manipulation approach over a hash map.
*   Mention the constraint that numbers appear *three* times. If it were *two* times, a simpler XOR approach would work.

## Revision Checklist
- [ ] Understand the problem statement: find the single element appearing once, others thrice.
- [ ] Recall the bitwise approach: summing bits modulo 3.
- [ ] Practice bitwise operations: `&`, `>>`, `|`, `<<`.
- [ ] Trace the algorithm with an example.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (e.g., single element array).
- [ ] Be ready to explain the logic to an interviewer.

## Similar Problems
Single Number (LeetCode 136)
Single Number Ii (LeetCode 137) - This problem
Single Number Iii (LeetCode 260)

## Tags
`Bit Manipulation` `Array`
