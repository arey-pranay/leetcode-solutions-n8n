# Single Number Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Bit Manipulation`  
**Time:** O(32 * n)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int singleNumber(int[] nums) {
        if(nums.length == 1) return nums[0];
        int res = 0;
        for(int i=0;i<32;i++){
            int ones = 0;
            for(int j=0;j<nums.length;j++){
                ones += nums[j]&1;
                nums[j]>>=1;
            } 
            res = (res | ((ones%3) << i)); // res ke right se ith bit pe 1 ya 0 rkhna hai
        }
        // 000001 | (1<<2 ) -> 00001 | (100) -> 101 
        return res;
    }
}


```

---

---
## Quick Revision
Given an array of integers where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position modulo 3.

## Intuition
The core idea is that if we consider each bit position (0 to 31 for a 32-bit integer) across all numbers, the sum of the bits at any given position will be a multiple of 3 if the single number has a 0 at that position. If the single number has a 1 at that position, the sum of bits will be (3k + 1). By taking the sum modulo 3, we can determine the corresponding bit of the single number.

## Algorithm
1. Initialize a result variable `res` to 0. This will store the single number.
2. Iterate through each bit position from 0 to 31 (for a 32-bit integer).
3. For each bit position `i`:
    a. Initialize a counter `ones` to 0. This will count the number of set bits at position `i` across all numbers.
    b. Iterate through each number `num` in the input array `nums`.
    c. Check if the `i`-th bit of `num` is set. This can be done by `(num >> i) & 1`.
    d. If the `i`-th bit is set, increment `ones`.
    e. After iterating through all numbers, calculate `ones % 3`. This remainder will be 1 if the single number has a 1 at the `i`-th bit, and 0 otherwise.
    f. Set the `i`-th bit of `res` based on `ones % 3`. This can be done by `res = res | ((ones % 3) << i)`.
4. After iterating through all 32 bit positions, `res` will hold the single number.
5. Return `res`.

## Concept to Remember
*   Bitwise Operations: Understanding `&` (AND), `|` (OR), `>>` (right shift), and `<<` (left shift) is crucial.
*   Modulo Arithmetic: The property that sums of bits modulo 3 reveal the single number's bits.
*   Integer Representation: How numbers are represented in binary and how to access individual bits.

## Common Mistakes
*   Incorrectly handling the bit shifts: Misunderstanding how `>>` and `<<` work can lead to incorrect bit extraction or setting.
*   Forgetting to reset the bit counter for each bit position: The `ones` counter must be re-initialized for every bit position `i`.
*   Not considering the modulo 3 operation correctly: Applying modulo 3 only at the end or incorrectly can lead to wrong results.
*   Edge case: Not handling the case where the input array has only one element.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for each of the `n` numbers in the array.
- Space: O(1) - reason: We only use a few constant extra variables (`res`, `ones`, loop counters).

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        // Handle the edge case where the array has only one element.
        if(nums.length == 1) return nums[0];
        // Initialize the result variable to store the single number.
        int res = 0;
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for(int i = 0; i < 32; i++){
            // Initialize a counter to count the number of set bits at the current position 'i' across all numbers.
            int ones = 0;
            // Iterate through each number in the input array.
            for(int j = 0; j < nums.length; j++){
                // Check if the i-th bit of the current number nums[j] is set.
                // (nums[j] & 1) gets the least significant bit.
                // We then right shift nums[j] by 1 to process the next bit in the next iteration of the inner loop.
                ones += nums[j] & 1; // Add the least significant bit to 'ones'
                nums[j] >>= 1;       // Right shift nums[j] to process the next bit in the next iteration.
            }
            // After counting the set bits at position 'i' for all numbers,
            // take the count modulo 3. If the single number has a 1 at this bit position,
            // 'ones' will be 3k+1, so ones%3 will be 1. Otherwise, it will be 0.
            // Then, left shift this result (0 or 1) by 'i' positions to place it at the correct bit position in 'res'.
            // Finally, OR it with 'res' to set the i-th bit of 'res' if (ones % 3) is 1.
            res = (res | ((ones % 3) << i)); // Set the i-th bit of 'res' based on the count modulo 3.
        }
        // After processing all 32 bits, 'res' will contain the single number.
        return res;
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly: Walk through how you're counting bits and using modulo 3.
*   Discuss the time and space complexity: Be ready to justify O(n) time and O(1) space.
*   Consider alternative solutions (if asked): Briefly mention hash maps or sorting as less optimal alternatives.
*   Handle edge cases: Explicitly mention the single-element array case.

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Grasp the bitwise counting modulo 3 logic.
- [ ] Implement the nested loops correctly.
- [ ] Ensure bit shifts and OR operations are used appropriately.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
Single Number (LeetCode 136)
Single Number Iii (LeetCode 260)

## Tags
`Bit Manipulation` `Array`
