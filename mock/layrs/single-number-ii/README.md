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
Given an array of integers where every element appears three times except for one, find that single one.
This is solved by counting the occurrences of each bit position modulo 3.

## Intuition
The core idea is to consider each bit position (0 to 31 for a 32-bit integer) independently. For any given bit position, if we sum up the values of that bit across all numbers in the array, the sum will be a multiple of 3 if the single number has a 0 at that bit position. If the single number has a 1 at that bit position, the sum will be (3k + 1) for some integer k. Therefore, by taking the sum modulo 3 for each bit position, we can reconstruct the bits of the single number.

## Algorithm
1. Initialize `ans` to 0. This will store the result.
2. Iterate through each bit position from 0 to 31 (inclusive).
3. For each bit position `i`:
    a. Initialize `ones` to 0. This will count how many numbers have a 1 at the current bit position.
    b. Iterate through each number `nums[j]` in the input array.
    c. Extract the last bit of `nums[j]` using a bitwise AND with 1 (`nums[j] & 1`).
    d. If the extracted bit is 1, increment `ones`.
    e. Right-shift `nums[j]` by 1 (`nums[j] >>= 1`) to prepare for checking the next bit in the next iteration of the inner loop. This modifies the original array elements.
    f. After iterating through all numbers for the current bit position `i`, check if `ones` modulo 3 is not equal to 0.
    g. If `ones % 3 != 0`, it means the single number has a 1 at this bit position. Set the `i`-th bit of `ans` to 1 using a bitwise OR with `1 << i` (`ans = ans | 1 << i`).
4. After iterating through all 32 bit positions, `ans` will hold the single number. Return `ans`.

## Concept to Remember
*   Bitwise Operations: Understanding AND (`&`), OR (`|`), and right shift (`>>`) is crucial.
*   Modular Arithmetic: The core logic relies on counting bits modulo 3.
*   Integer Representation: How numbers are represented in binary and how to manipulate individual bits.

## Common Mistakes
*   Using an enhanced for-loop (`for (int num : nums)`) for modifying the numbers: This passes a copy of the number, so the right shift operation inside the loop won't affect the original array elements needed for subsequent bit checks.
*   Incorrectly handling the bit shifting: Shifting the wrong way or not shifting at all will lead to incorrect bit counts.
*   Off-by-one errors in bit iteration: Ensuring all 32 bits are checked.
*   Forgetting to reset the `ones` count for each bit position.

## Complexity Analysis
- Time: O(32 * n) which simplifies to O(n) - reason: We iterate through each of the 32 bits for every number in the array.
- Space: O(1) - reason: We only use a few constant extra variables (`ans`, `ones`, loop counters).

## Commented Code
```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0; // Initialize the result variable to 0. This will store the single number.
        // Iterate through each bit position from 0 to 31 (for a 32-bit integer).
        for(int i = 0; i <= 31; i++){
            int ones = 0; // Initialize a counter for the number of elements with the i-th bit set to 1.
            // Iterate through each number in the input array.
            for(int j = 0; j < nums.length; j++){
                // Extract the last bit (the current bit we are interested in after shifts) of nums[j].
                int lastbit = nums[j] & 1;
                // If the last bit is 1, increment the 'ones' counter.
                if(lastbit == 1) ones++;
                // Right-shift nums[j] by 1 to move the next bit to the last position for the next iteration.
                // This modifies the array elements in place, effectively processing each bit of each number.
                nums[j] >>= 1;
            }
            // After counting the 'ones' for the current bit position across all numbers,
            // check if the count is not a multiple of 3.
            if(ones % 3 != 0) {
                // If 'ones' is not a multiple of 3, it means the single number has a 1 at this bit position.
                // Set the i-th bit of 'ans' to 1 using a bitwise OR operation.
                ans = ans | (1 << i);
            }
        }
        // After checking all 32 bits, 'ans' will hold the single number.
        return ans;
    }
}
```

## Interview Tips
*   Explain the bitwise approach clearly, emphasizing the modulo 3 counting.
*   Be prepared to discuss why the enhanced for-loop is problematic for modifying array elements.
*   Walk through an example with a few numbers and a few bits to illustrate the process.
*   Mention the alternative approach using two variables (ones, twos) if asked for a solution without modifying the input array.

## Revision Checklist
- [ ] Understand the problem statement: find the single element appearing once, others thrice.
- [ ] Grasp the bitwise counting modulo 3 intuition.
- [ ] Implement the nested loop structure correctly.
- [ ] Ensure correct bit extraction and shifting.
- [ ] Verify the modulo 3 check and bit setting in the result.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the solution and potential pitfalls.

## Similar Problems
Single Number
Single Number Iii

## Tags
`Bit Manipulation` `Array`
