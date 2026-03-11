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
        int result = 0;
        for(int i=0;i<32;i++){
            int bitCount = 0;
            for(int j =0; j<nums.length;j++){
                int num = nums[j];
                if((num& 1) == 1) bitCount++;
                nums[j] = num>>1;
            }
            if(bitCount%3 == 1){
               result += (1<<i);
            };
        }
        return result;
    }
}
```

---

---
## Quick Revision
Given an array of integers where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position modulo 3.

## Intuition
The core idea is to consider the binary representation of the numbers. If a number appears three times, each of its set bits will contribute 3 to the total count for that bit position across all numbers. The single number's set bits will contribute only 1. Therefore, if we sum up the bits at each position across all numbers and take the sum modulo 3, we can isolate the bits of the single number.

## Algorithm
1. Initialize `result` to 0. This will store the single number.
2. Iterate through each bit position from 0 to 31 (for a 32-bit integer).
3. For each bit position `i`:
    a. Initialize `bitCount` to 0. This will count how many numbers have the `i`-th bit set.
    b. Iterate through each number `num` in the input array `nums`.
    c. Check if the `i`-th bit of `num` is set. This can be done by `(num >> i) & 1`.
    d. If the `i`-th bit is set, increment `bitCount`.
    e. After iterating through all numbers, check if `bitCount % 3` is 1.
    f. If `bitCount % 3` is 1, it means the single number has the `i`-th bit set. Add `(1 << i)` to `result`.
4. Return `result`.

## Concept to Remember
*   Bitwise Operations: Understanding `&` (AND), `|` (OR), `^` (XOR), `~` (NOT), `<<` (left shift), `>>` (right shift).
*   Modulo Arithmetic: Applying modulo 3 to bit counts to identify the unique bit.
*   Integer Representation: How numbers are represented in binary and how to manipulate individual bits.

## Common Mistakes
*   Modifying the input array directly: The provided solution modifies the input array by right-shifting elements. This might be undesirable in some contexts. A better approach would be to use a temporary variable or a copy.
*   Incorrect bit manipulation: Errors in shifting or masking bits can lead to incorrect counts.
*   Handling negative numbers: While this specific solution might work for negative numbers due to two's complement representation, it's crucial to be aware of how bitwise operations behave with signed integers.
*   Off-by-one errors in loops or bit positions.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for each of the `n` numbers in the array.
- Space: O(1) - reason: We only use a few extra variables (`result`, `bitCount`, loop counters) regardless of the input size.

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        int result = 0; // Initialize the result variable to store the single number.
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for(int i = 0; i < 32; i++){
            int bitCount = 0; // Initialize a counter for the current bit position.
            // Iterate through each number in the input array.
            for(int j = 0; j < nums.length; j++){
                int num = nums[j]; // Get the current number.
                // Check if the i-th bit of the current number is 1.
                // (num & 1) isolates the least significant bit.
                // We are effectively checking the i-th bit by repeatedly right-shifting.
                if((num & 1) == 1) {
                    bitCount++; // Increment the count if the bit is set.
                }
                // Right-shift the current number by 1 to prepare for checking the next bit in the next iteration.
                // This modifies the original array elements, which might not be ideal.
                nums[j] = num >> 1;
            }
            // If the count of set bits at the current position is not divisible by 3,
            // it means the single number has this bit set.
            if(bitCount % 3 == 1){
               // Add the value of this bit (2^i) to the result.
               result += (1 << i);
            }
        }
        return result; // Return the reconstructed single number.
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly. Start with the intuition of summing bits modulo 3.
*   Discuss the trade-offs of modifying the input array versus using extra space.
*   Be prepared to explain how negative numbers are handled in two's complement if asked.
*   If time permits, mention alternative solutions like using a hash map (though less optimal in space).

## Revision Checklist
- [ ] Understand the problem statement: find the single element appearing once when others appear thrice.
- [ ] Grasp the bitwise counting intuition (modulo 3).
- [ ] Implement the bit iteration and counting logic correctly.
- [ ] Handle the reconstruction of the result using bit shifts.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases (e.g., empty array, array with only the single number).

## Similar Problems
*   Single Number (LeetCode 136)
*   Single Number Iii (LeetCode 260)

## Tags
`Bit Manipulation` `Array`
