# Check If Array Is Good

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Hash Table` `Sorting`  
**Time:** O(N)  
**Space:** O(M)

---

## Solution (java)

```java
class Solution {
    public boolean isGood(int[] nums) {
       int[] count = new int[201];
       int n= nums.length;
       for(int i = 0;i<n;i++) count[nums[i]]++;
       
       int rem = n;
       for(int i=1;i<=200;i++){
         if(count[i] == 2){
            if(rem==2) return true;
            else return false;
         }
         if(count[i] == 1) rem--;
         else return false;
       }
       return false;
    }
}
```

---

---
## Quick Revision
Given an array `nums`, determine if it's a "good" array. A good array contains numbers from 1 to n-1 exactly once, and the number n exactly twice, where n is the length of the array.
We can solve this by counting the occurrences of each number and verifying the conditions.

## Intuition
The problem defines a "good" array based on specific counts of numbers. If an array `nums` has length `n`, it should contain numbers from 1 up to `n-1` exactly once, and the number `n` exactly twice. This means that if we count the occurrences of each number, we should see a pattern: most numbers appear once, and only one specific number (which should be `n`) appears twice. The key insight is that the largest number in the array *must* be `n`, and it must appear exactly twice. All other numbers from 1 to `n-1` must appear exactly once.

## Algorithm
1. Get the length of the input array `nums` and store it in `n`.
2. Create a frequency map (or an array if the numbers are within a reasonable range) to store the counts of each number in `nums`.
3. Iterate through `nums` and populate the frequency map. For each number `num` in `nums`, increment its count in the map.
4. Initialize a variable `expected_count_of_n` to 2. This is the expected count for the number `n`.
5. Iterate from 1 up to `n-1`. For each number `i` in this range:
    a. Check if `i` exists in the frequency map and if its count is exactly 1. If not, the array is not good, return `false`.
6. Check the count of the number `n` in the frequency map. It must be exactly 2. If not, return `false`.
7. If all checks pass, return `true`.

*Note: The provided solution uses a slightly different approach by iterating up to 200 and tracking a `rem` variable. This is valid if the maximum possible value in `nums` is bounded (e.g., 200 as in the code). A more general approach would be to use a HashMap or sort the array.*

## Concept to Remember
*   Frequency Counting: Using data structures like arrays or hash maps to efficiently count occurrences of elements.
*   Array Properties: Understanding how the length of an array relates to the expected values within it.
*   Edge Case Handling: Considering scenarios like empty arrays or arrays with unexpected values.

## Common Mistakes
*   Not handling the specific count of `n` correctly: Forgetting that `n` must appear *exactly twice*.
*   Assuming numbers are contiguous without checking: The problem requires numbers from 1 to `n-1` to be present *exactly once*.
*   Off-by-one errors in loops or index access: Especially when dealing with array lengths and expected values.
*   Not considering the maximum possible value for array indexing: If using an array for frequency counting, ensure its size is sufficient.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the input array `nums` once to populate the frequency count, and then iterate through a range of numbers (up to the maximum possible value or `n`) to check their counts.
- Space: O(M) - reason: We use an auxiliary array `count` to store frequencies. The size of this array is fixed (201 in the provided solution), making it O(1) space if M is a constant. If M were dependent on N (e.g., using a HashMap for arbitrary values), it would be O(N) in the worst case.

## Commented Code
```java
class Solution {
    public boolean isGood(int[] nums) {
       // Initialize a frequency array to count occurrences of numbers.
       // The size 201 is chosen assuming numbers in nums are at most 200.
       int[] count = new int[201];
       
       // Get the length of the input array. This 'n' is crucial for the definition of a "good" array.
       int n = nums.length;
       
       // Iterate through the input array 'nums' to populate the frequency count.
       for(int i = 0; i < n; i++) {
           // Increment the count for the current number nums[i] in the 'count' array.
           count[nums[i]]++;
       }
       
       // 'rem' tracks how many numbers from 1 to n-1 we expect to see exactly once.
       // Initially, we expect 'n' numbers in total, so 'rem' starts as 'n'.
       int rem = n;
       
       // Iterate through possible numbers from 1 up to 200 (the maximum possible value in a "good" array of length up to 201).
       for(int i = 1; i <= 200; i++) {
         // Check if the current number 'i' appears exactly twice.
         if(count[i] == 2) {
            // If it appears twice, it must be the number 'n' (the length of the array).
            // If 'rem' is also 2, it means we have accounted for 'n' and all numbers from 1 to n-1.
            // This signifies a "good" array.
            if(rem == 2) return true;
            // If 'rem' is not 2, it means we have an extra number appearing twice, or 'n' is not the length of the array.
            else return false;
         }
         // Check if the current number 'i' appears exactly once.
         if(count[i] == 1) {
            // If it appears once, decrement 'rem'. This means we've found one of the numbers from 1 to n-1.
            rem--;
         }
         // If the current number 'i' does not appear 0 times, 1 time, or 2 times (as handled above),
         // it means its count is invalid for a "good" array.
         else if (count[i] != 0) { // Explicitly check for non-zero counts other than 1 or 2
             return false;
         }
         // If count[i] is 0, and i < n, this means a number from 1 to n-1 is missing.
         // This case is implicitly handled by the fact that 'rem' will not reach 0 correctly if a number is missing.
         // However, if i > n and count[i] is 0, it's fine.
       }
       
       // If the loop finishes and we haven't returned true, it means the conditions for a "good" array were not met.
       // This could happen if 'n' was not found twice, or if 'rem' did not correctly reach 0.
       return false;
    }
}
```

## Interview Tips
*   Clarify the definition of a "good" array thoroughly with the interviewer. Ask for examples.
*   Discuss the constraints on the input array size and the range of numbers. This will influence your choice of data structure for frequency counting.
*   Walk through your logic with a small example array (e.g., `[1, 3, 3, 2]`) to ensure your algorithm correctly identifies it as good.
*   Explain the trade-offs between using a fixed-size array for frequency counting versus a HashMap.

## Revision Checklist
- [ ] Understand the definition of a "good" array: `n-1` distinct numbers from 1 to `n-1`, and `n` appearing twice.
- [ ] Choose an appropriate data structure for frequency counting (array or HashMap).
- [ ] Implement the logic to count element occurrences.
- [ ] Verify that numbers from 1 to `n-1` appear exactly once.
- [ ] Verify that the number `n` appears exactly twice.
- [ ] Handle edge cases (e.g., empty array, array with invalid numbers).

## Similar Problems
*   Contains Duplicate
*   Missing Number
*   First Missing Positive
*   Find All Numbers Disappeared in an Array

## Tags
`Array` `Hash Map` `Counting`
