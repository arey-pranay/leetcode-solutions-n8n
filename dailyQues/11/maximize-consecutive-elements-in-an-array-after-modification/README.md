# Maximize Consecutive Elements In An Array After Modification

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Sorting`  
**Time:** O(N \log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int best = 1, keep = 1, bump = 1, last = -10;
        for (int x : nums) {
            switch (x - last) {
                case 0 -> bump = keep + 1;  // x repeat: bump it on top of keep
                case 1 -> { keep++; bump++; }  // x extends keep, x + 1 extends bump
                case 2 -> { keep = bump + 1; bump = 1; }  // x extends bump, x + 1 starts over
                default -> keep = bump = 1;
            }
            best = Math.max(best, Math.max(keep, bump));
            last = x;
        }
        return best;
    }
}
```

---

---
## Quick Revision
Maximize the length of consecutive integers formable by either keeping each number as is or incrementing it by 1.
Sort the array and track progression states using dynamic state tracking for original and incremented values.

## Intuition
The "aha moment" comes from realizing that after sorting the array, any consecutive sequence must be built from numbers that appear in a specific relative order. For each element $x$, we can either keep it as $x$ or modify it to $x + 1$. By maintaining two parallel tracking variables—`keep` (representing sequences ending at the current original value) and `bump` (representing sequences ending at the current value plus 1)—we can efficiently evaluate how the current number extends previous chains based on the difference between the current number and the previous number.

## Algorithm
1. Sort the input array `nums` in ascending order to process elements sequentially.
2. Initialize `best` to 1, `keep` to 1, `bump` to 1, and `last` to a sentinel value like `-10`.
3. Iterate through each number `x` in the sorted array.
4. Calculate the difference between the current number and the last processed number: `x - last`.
5. Update `keep` and `bump` using a switch-case on the difference:
   - Difference 0 (duplicate): `bump` becomes `keep + 1`.
   - Difference 1 (sequential): `keep` and `bump` both increment.
   - Difference 2 (gap of 1): `keep` becomes `bump + 1`, and `bump` resets to 1.
   - Default (gap > 2): reset both `keep` and `bump` to 1.
6. Update `best` with the maximum value between `best`, `keep`, and `bump`.
7. Update `last` to `x` and continue to the next iteration.
8. Return `best` after processing all elements.

## Concept to Remember
- **Array Sorting:** Essential for establishing order so we can greedily build consecutive sequences.
- **State Machine / DP Tracking:** Using multiple state variables (`keep` and `bump`) to track overlapping modification choices without heavy memory overhead.
- **Difference Analysis:** Utilizing the mathematical difference between adjacent elements to classify transition types.

## Common Mistakes
- Forgetting to sort the array initially, which ruins the sequential difference logic.
- Miscalculating the state updates when duplicate elements (difference of 0) are encountered.
- Incorrectly handling gaps larger than 2, failing to reset or transition state variables properly.
- Overflowing or mismanaging the sentinel value for the `last` tracker.

## Complexity Analysis
- Time: $O(N \log N)$ - Dominated by the initial sorting step of the array of size $N$, followed by a linear scan of $O(N)$.
- Space: $O(1)$ or $O(\log N)$ - Dependent on the sorting algorithm's auxiliary space complexity, using constant extra space for variables.

## Commented Code
```java
class Solution {
    public int maxSelectedElements(int[] nums) {
        // Sort the array so that potential consecutive elements are adjacent
        Arrays.sort(nums);
        // Initialize best length, keep state, bump state, and last seen value
        int best = 1, keep = 1, bump = 1, last = -10;
        // Iterate through each number in the sorted array
        for (int x : nums) {
            // Check the difference between current number and the previous number
            switch (x - last) {
                // If x is a duplicate, bump chain can build on top of keep
                case 0 -> bump = keep + 1;  
                // If x extends keep, keep increments; x + 1 extends bump
                case 1 -> { keep++; bump++; }  
                // If x extends bump, keep takes bump + 1; bump resets
                case 2 -> { keep = bump + 1; bump = 1; }  
                // If gap is too large, reset both sequences to start fresh
                default -> keep = bump = 1;
            }
            // Update the global maximum sequence length found so far
            best = Math.max(best, Math.max(keep, bump));
            // Update last to the current number for the next iteration
            last = x;
        }
        // Return the maximum consecutive sequence length achieved
        return best;
    }
}
```

## Interview Tips
- Clarify whether elements can be modified more than once (the problem states each element can be modified at most once by adding 1).
- Explain the state transition logic clearly to the interviewer before writing code, drawing out an example with differences 0, 1, and 2.
- Discuss alternative approaches like Hash Maps, but highlight why sorting plus state tracking is more space-efficient.

## Revision Checklist
- [ ] Understand why sorting is the mandatory first step.
- [ ] Trace the 4 cases of differences (0, 1, 2, default).
- [ ] Explain what `keep` and `bump` represent.
- [ ] Verify time and space complexity bounds.

## Similar Problems
- Longest Consecutive Sequence
- Consecutive Numbers Sum
- Maximum Length of Pair Chain

## Tags
`Array` `Sorting` `Dynamic Programming` `Greedy`
