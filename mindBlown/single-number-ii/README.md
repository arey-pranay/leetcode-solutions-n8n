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
        int ans = 0;
        for(int i=0;i<=31;i++){
            int ones = 0;
            for(int j=0; j<nums.length;j++){
                int lastbit = nums[j] & 1;
                if(lastbit==1) ones++;
                nums[j] >>= 1;
            }
            if(ones % 3 != 0) ans = ans | 1<<i;
        }
        return ans;
    }
}
// Inside the inner loop:
//  for(int num : nums){
//      num >>= 1;
//      does nothing useful because num is just a copy from the enhanced for-loop.
// therefore I used a normal for-loop instead of for-each loop.

```

---

---
## Quick Revision
Given an integer array where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position modulo 3.

## Intuition
The core idea is to consider each bit position (0 to 31 for a 32-bit integer) independently. For any given bit position, if we sum up the values of that bit across all numbers in the array, the sum will be a multiple of 3 if the single number has a 0 at that bit position. If the single number has a 1 at that bit position, the sum will be (3k + 1) for some integer k. By taking this sum modulo 3, we can determine the value of the single number's bit at that position.

## Algorithm
1. Initialize a variable `ans` to 0. This will store the final single number.
2. Iterate through each bit position from 0 to 31 (inclusive), representing the bits of a 32-bit integer.
3. For each bit position `i`:
    a. Initialize a counter `ones` to 0. This counter will track how many numbers have a 1 at the current bit position `i`.
    b. Iterate through each number `nums[j]` in the input array.
    c. Extract the least significant bit (LSB) of `nums[j]` using a bitwise AND operation: `nums[j] & 1`.
    d. If the LSB is 1, increment the `ones` counter.
    e. Right-shift `nums[j]` by 1 bit (`nums[j] >>= 1`) to prepare for checking the next bit in the subsequent iteration of the inner loop. This effectively moves the next bit to the LSB position.
    f. After iterating through all numbers for the current bit position `i`, check if `ones % 3` is not equal to 0.
    g. If `ones % 3 != 0`, it means the single number has a 1 at this bit position. Set the `i`-th bit of `ans` to 1 using a bitwise OR operation: `ans = ans | (1 << i)`.
4. After iterating through all 32 bit positions, `ans` will hold the single number. Return `ans`.

## Concept to Remember
*   Bitwise Operations: Understanding `&` (AND), `|` (OR), `>>` (Right Shift), and `<<` (Left Shift) is crucial.
*   Modulo Arithmetic: The core logic relies on the property that sums of bits will be `3k` or `3k+1`.
*   Handling Integer Representation: Recognizing that integers are represented in binary and can be processed bit by bit.

## Common Mistakes
*   Using an enhanced for-loop (`for (int num : nums)`) for modifying the numbers: The enhanced for-loop passes copies of the elements, so right-shifting `num` inside it does not affect the original array elements. A traditional for-loop with an index is necessary to modify the array elements in place.
*   Incorrectly handling the bit shifts: Ensuring the right shift is applied correctly to examine each bit of every number.
*   Off-by-one errors in bit iteration: Iterating from 0 to 31 for 32 bits is standard, but mistakes can occur.
*   Misunderstanding the modulo 3 logic: Not realizing that `ones % 3` directly reveals the single number's bit.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for every number in the array. Since 32 is a constant, the complexity is linear with respect to the number of elements `n`.
- Space: O(1) - reason: We only use a few extra variables (`ans`, `ones`, `i`, `j`, `lastbit`) regardless of the input array size.

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0; // Initialize the result variable to 0. This will store the single number.
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for(int i = 0; i <= 31; i++){
            int ones = 0; // Initialize a counter for the current bit position. It counts how many numbers have a '1' at this bit.
            // Iterate through each number in the input array.
            for(int j = 0; j < nums.length; j++){
                // Extract the least significant bit (LSB) of the current number.
                int lastbit = nums[j] & 1;
                // If the LSB is 1, increment the 'ones' counter.
                if(lastbit == 1) ones++;
                // Right-shift the current number by 1 bit. This moves the next bit to the LSB position for the next iteration.
                // This modification is crucial and why a standard for-loop is used instead of an enhanced for-loop.
                nums[j] >>= 1;
            }
            // After checking all numbers for the current bit position 'i',
            // if the count of '1's is not a multiple of 3, it means the single number has a '1' at this bit position.
            if(ones % 3 != 0) {
                // Set the i-th bit of the 'ans' to 1.
                // (1 << i) creates a number with only the i-th bit set to 1.
                // The bitwise OR operation merges this bit into 'ans'.
                ans = ans | (1 << i);
            }
        }
        // After iterating through all 32 bits, 'ans' will hold the single number.
        return ans;
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly: Emphasize how you're processing each bit independently and using modulo 3 to isolate the single number's bit.
*   Address the enhanced for-loop issue: Be prepared to explain why a standard for-loop is necessary for modifying the array elements in place.
*   Walk through an example: Use a small array (e.g., `[2, 2, 3, 2]`) and trace the bit counting for a couple of bit positions to demonstrate your understanding.
*   Discuss alternative solutions briefly (if asked): Mentioning the bit manipulation logic is key, but if time permits, you could briefly touch upon hash map solutions (though they use more space).

## Revision Checklist
- [ ] Understand the problem statement: every element appears three times except one.
- [ ] Grasp the bitwise counting intuition.
- [ ] Implement the nested loop structure correctly.
- [ ] Ensure correct bit extraction (`& 1`).
- [ ] Ensure correct bit shifting (`>>= 1`).
- [ ] Apply modulo 3 logic correctly.
- [ ] Reconstruct the result using bitwise OR and left shift.
- [ ] Be aware of the enhanced for-loop pitfall.
- [ ] Analyze time and space complexity.

## Similar Problems
Single Number (LeetCode 136)
Single Number II (LeetCode 137) - This problem
Single Number III (LeetCode 260)

## Tags
`Bit Manipulation` `Array`
