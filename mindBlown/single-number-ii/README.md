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
The core idea is to consider the binary representation of each number. If we sum up the bits at each position (0th bit, 1st bit, etc.) across all numbers in the array, the sum for any given bit position will be a multiple of 3 if the single number has a 0 at that position. If the single number has a 1 at that position, the sum will be (3k + 1). Therefore, by taking the sum of bits at each position modulo 3, we can reconstruct the bits of the single number.

## Algorithm
1. Initialize a result variable `res` to 0. This will store the single number.
2. Iterate through each bit position from 0 to 31 (for a 32-bit integer).
3. For each bit position `i`:
    a. Initialize a counter `ones` to 0. This will count the number of set bits (1s) at the current bit position across all numbers.
    b. Iterate through each number `num` in the input array `nums`.
    c. Check the `i`-th bit of `num` using a bitwise AND operation (`num & 1`). Add this bit to `ones`.
    d. Right-shift `num` by 1 (`num >>= 1`) to prepare for checking the next bit in the subsequent iteration.
    e. After iterating through all numbers for the current bit position, calculate `ones % 3`. This gives us the `i`-th bit of the single number.
    f. If `ones % 3` is 1, it means the single number has a 1 at this bit position. Set the `i`-th bit in `res` by ORing `res` with `(1 << i)`.
4. Return `res`.

## Concept to Remember
*   Bitwise Operations: Understanding AND (`&`), OR (`|`), and right shift (`>>`) is crucial for manipulating individual bits.
*   Modulo Arithmetic: The property that sums of bits modulo 3 reveal the bits of the single number is key.
*   Binary Representation of Integers: Recognizing that numbers can be decomposed into their binary bits.
*   Handling Edge Cases: Considering arrays with a single element.

## Common Mistakes
*   Modifying the input array: The provided solution modifies the input array by right-shifting elements. This might be undesirable in some contexts. A solution that doesn't modify the input is often preferred.
*   Incorrect bit manipulation: Errors in checking or setting bits can lead to wrong results.
*   Off-by-one errors in loops: Iterating through the wrong number of bits or array elements.
*   Forgetting to handle the modulo 3 logic correctly: Simply summing bits without the modulo operation will not work.
*   Not considering negative numbers: While this solution works for negative numbers due to two's complement representation, it's good to be aware of how bitwise operations behave with them.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for every number in the array. Since 32 is a constant, the time complexity is linear with respect to the number of elements in the array.
- Space: O(1) - reason: We only use a few extra variables (`res`, `ones`, loop counters) regardless of the input array size.

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
            // This will count how many numbers have a '1' at this bit position.
            int ones = 0;
            // Iterate through each number in the input array.
            for (int j = 0; j < nums.length; j++) {
                // Check the least significant bit (LSB) of the current number.
                // If it's 1, add 1 to 'ones'.
                ones += nums[j] & 1;
                // Right-shift the current number by 1. This moves the next bit to the LSB position
                // for the next iteration of the inner loop. This modifies the input array.
                nums[j] >>= 1;
            }
            // After checking all numbers for the current bit position 'i':
            // If 'ones % 3' is 1, it means the single number has a '1' at this bit position.
            // Otherwise, if 'ones % 3' is 0, the single number has a '0' at this bit position.
            // We then left-shift this result (0 or 1) by 'i' positions to place it correctly
            // in the 'res' variable.
            res |= (ones % 3) << i;
        }
        // Return the reconstructed single number.
        return res;
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly: Start by explaining the intuition of summing bits modulo 3.
*   Discuss the modification of the input array: Mention that the current solution modifies the input and ask if that's acceptable, or if an O(1) space solution that *doesn't* modify the input is required.
*   Consider alternative solutions: Briefly mention or be prepared to discuss other approaches like using a hash map (O(n) space) or a more complex bit manipulation logic using two variables (often called `ones` and `twos` to track counts modulo 3).
*   Walk through an example: Use a small example like `[2, 2, 3, 2]` to demonstrate how the bits are counted and how `res` is built.

## Revision Checklist
- [ ] Understand the problem statement: every element appears three times except one.
- [ ] Grasp the bitwise counting intuition (sum of bits modulo 3).
- [ ] Implement the outer loop for bit positions (0 to 31).
- [ ] Implement the inner loop to iterate through numbers.
- [ ] Correctly use bitwise AND (`& 1`) to get the LSB.
- [ ] Correctly use right shift (`>>= 1`) to move to the next bit.
- [ ] Apply the modulo 3 operation to the bit counts.
- [ ] Correctly use bitwise OR (`|=`) and left shift (`<< i`) to build the result.
- [ ] Handle the edge case of a single-element array.
- [ ] Analyze time and space complexity.
- [ ] Consider solutions that don't modify the input array.

## Similar Problems
*   Single Number (LeetCode 136)
*   Single Number Iii (LeetCode 260)
*   Missing Number (LeetCode 268)
*   Number of 1 Bits (LeetCode 191)

## Tags
`Bit Manipulation` `Array`
