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
Given an integer array where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position modulo 3.

## Intuition
The core idea is to consider each bit position (0 to 31 for a 32-bit integer) independently. For any given bit position, if we sum up the values of that bit across all numbers in the array, the sum will be a multiple of 3 if the single number has a 0 at that bit position. If the single number has a 1 at that bit position, the sum will be `3k + 1`. Therefore, by taking the sum modulo 3 for each bit position, we can reconstruct the bits of the single number.

## Algorithm
1. Initialize a result variable `res` to 0. This will store the single number.
2. Iterate through each bit position from 0 to 31 (for a 32-bit integer).
3. For each bit position `i`:
    a. Initialize a counter `ones` to 0. This counter will track the sum of the `i`-th bit across all numbers.
    b. Iterate through each number `num` in the input array `nums`.
    c. Extract the `i`-th bit of `num` by performing a bitwise AND with 1 (`num & 1`).
    d. Add this extracted bit to `ones`.
    e. Right-shift `num` by 1 (`num >>= 1`) to prepare for checking the next bit in the next iteration of the inner loop.
    f. After iterating through all numbers for the current bit position `i`, calculate `ones % 3`. This gives us the value of the `i`-th bit of the single number (either 0 or 1).
    g. Left-shift this result (`ones % 3`) by `i` positions (`(ones % 3) << i`) and OR it with `res`. This sets the `i`-th bit of `res` to the correct value.
4. After iterating through all 32 bit positions, `res` will hold the single number.
5. Return `res`.

## Concept to Remember
*   Bitwise Operations: Understanding `&` (AND), `|` (OR), `>>` (Right Shift), and `<<` (Left Shift).
*   Modulo Arithmetic: Applying modulo 3 to count occurrences of bits.
*   Bit Manipulation for Counting: Using bitwise operations to isolate and sum bits across numbers.

## Common Mistakes
*   Incorrectly handling the right shift: Modifying the original array `nums` in place by right-shifting can be problematic if the original values are needed later or if the problem statement implies immutability. A better approach is to use a temporary variable for the current number being processed.
*   Off-by-one errors in bit iteration: Ensuring the loop covers all 32 bits (0 to 31) correctly.
*   Misunderstanding the modulo 3 logic: Incorrectly applying the modulo operation or interpreting its result.
*   Not considering negative numbers: While this specific solution works for negative numbers due to two's complement representation, it's a good point to be aware of for bit manipulation problems.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for every number in the input array. Since 32 is a constant, the complexity is linear with respect to the number of elements in the array.
- Space: O(1) - reason: We only use a few extra variables (`res`, `ones`, loop counters) regardless of the input array size.

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        // Handle the edge case where the array has only one element.
        if(nums.length == 1) return nums[0];
        
        // Initialize the result variable to 0. This will store the single number.
        int res = 0;
        
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for(int i = 0; i < 32; i++){
            // Initialize a counter 'ones' to 0 for the current bit position.
            // This will sum up the i-th bit across all numbers.
            int ones = 0;
            
            // Iterate through each number in the input array.
            for(int j = 0; j < nums.length; j++){
                // Extract the i-th bit of the current number (nums[j]) by ANDing with 1.
                // Add this bit to the 'ones' counter.
                ones += nums[j] & 1;
                
                // Right-shift the current number by 1 to prepare for checking the next bit in the next iteration.
                // This effectively moves the next bit to the least significant position.
                nums[j] >>= 1;
            } 
            
            // After counting the i-th bit for all numbers, take the count modulo 3.
            // If the single number has a 0 at this bit position, ones % 3 will be 0.
            // If the single number has a 1 at this bit position, ones % 3 will be 1.
            // Left-shift this result by 'i' positions to place it at the correct bit index.
            // Then, OR it with 'res' to set the i-th bit of the result.
            res = (res | ((ones % 3) << i)); 
        }
        
        // After iterating through all 32 bit positions, 'res' will contain the single number.
        return res;
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly: Walk through how you're counting bits modulo 3 and why it works.
*   Discuss the time and space complexity: Be ready to justify O(n) time and O(1) space.
*   Consider edge cases: Mention the `nums.length == 1` case and how your logic handles it.
*   If asked for an alternative, mention the `(3 * sum - sum_of_all_elements) / 2` approach for the "appears twice except one" problem, and how it doesn't directly apply here but highlights the idea of using sums.

## Revision Checklist
- [ ] Understand the problem statement: every element appears three times except one.
- [ ] Grasp the bitwise counting modulo 3 intuition.
- [ ] Implement the outer loop for bit positions (0-31).
- [ ] Implement the inner loop to iterate through numbers.
- [ ] Correctly extract and sum bits using `& 1`.
- [ ] Correctly right-shift numbers using `>>= 1`.
- [ ] Apply `ones % 3` to get the bit for the single number.
- [ ] Use `(bit << i)` and `res | ...` to build the result.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution verbally.

## Similar Problems
*   Single Number (LeetCode 136)
*   Single Number II (LeetCode 137) - This is the problem.
*   Single Number III (LeetCode 260)

## Tags
`Bit Manipulation` `Array` `Math`
