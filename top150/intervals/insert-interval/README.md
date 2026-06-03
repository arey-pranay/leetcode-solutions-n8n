# Insert Interval

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
        // List to hold the merged intervals
        List<int[]> result = new ArrayList<>();
        
        int i = 0;
        int n = intervals.length;
        
        // Add all intervals that end before the new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        
        // Add the merged interval
        result.add(newInterval);
        
        // Add the remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        // Convert the list back to an array
        return result.toArray(new int[result.size()][]);
    }

}
```

---

---
## Quick Revision
Given a sorted list of non-overlapping intervals, insert a new interval and merge if necessary.
The solution iterates through intervals, adding non-overlapping ones, merging overlapping ones with the new interval, and then adding the rest.

## Intuition
The core idea is to process the intervals in order. Since the input `intervals` array is sorted by start times, we can efficiently find where the `newInterval` fits. We can divide the problem into three phases:
1. Intervals that come *before* `newInterval` and don't overlap.
2. Intervals that *overlap* with `newInterval` and need to be merged.
3. Intervals that come *after* `newInterval` and don't overlap.

By iterating through the sorted intervals, we can identify these three groups and construct the final merged list. The merging step is crucial: when we find an overlap, we expand the `newInterval` to encompass all overlapping intervals.

## Algorithm
1. Initialize an empty list `result` to store the merged intervals.
2. Initialize an index `i` to 0, representing the current interval being considered in the `intervals` array.
3. **Add intervals before overlap:** Iterate while `i` is within bounds and the current interval's end time (`intervals[i][1]`) is less than the `newInterval`'s start time (`newInterval[0]`). Add these non-overlapping intervals to `result`. Increment `i`.
4. **Merge overlapping intervals:** Iterate while `i` is within bounds and the current interval's start time (`intervals[i][0]`) is less than or equal to the `newInterval`'s end time (`newInterval[1]`). This condition signifies an overlap.
   - Update `newInterval[0]` to be the minimum of its current start time and the current interval's start time.
   - Update `newInterval[1]` to be the maximum of its current end time and the current interval's end time.
   - Increment `i`.
5. **Add the merged `newInterval`:** After the merging loop, add the (potentially expanded) `newInterval` to `result`.
6. **Add intervals after overlap:** Iterate while `i` is within bounds. Add the remaining intervals to `result`. Increment `i`.
7. Convert the `result` list into a 2D integer array and return it.

## Concept to Remember
*   **Interval Merging:** The fundamental operation is to combine overlapping intervals into a single, larger interval.
*   **Sorted Input:** The problem relies on the input `intervals` being sorted by start times, enabling a linear scan approach.
*   **Two Pointers/Single Pass:** A single pass through the sorted intervals is sufficient to solve the problem by categorizing intervals relative to the `newInterval`.

## Common Mistakes
*   **Incorrect Overlap Condition:** Using `intervals[i][0] < newInterval[1]` instead of `intervals[i][0] <= newInterval[1]` can miss cases where intervals touch at their boundaries.
*   **Not Handling Edge Cases:** Failing to correctly add intervals that come entirely before or entirely after the `newInterval`.
*   **Modifying Input Array Directly:** While not explicitly forbidden, it's generally better practice to create a new list for the result to avoid side effects.
*   **Off-by-One Errors in Loops:** Incorrectly setting loop conditions or incrementing indices can lead to missing intervals or processing them twice.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the `intervals` array at most once. The operations within the loops (comparisons, min/max, list additions) are constant time.
- Space: O(N) - reason: In the worst case, we might need to store all `N` intervals in the `result` list if no merging occurs or if the `newInterval` is inserted at the beginning or end.

## Commented Code
```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Initialize a list to store the resulting merged intervals.
        List<int[]> result = new ArrayList<>();
        
        // Initialize an index to iterate through the input intervals array.
        int i = 0;
        // Get the total number of intervals in the input array.
        int n = intervals.length;
        
        // Phase 1: Add all intervals that end strictly before the new interval starts.
        // These intervals do not overlap with newInterval and come before it.
        while (i < n && intervals[i][1] < newInterval[0]) {
            // Add the current interval to the result list.
            result.add(intervals[i]);
            // Move to the next interval.
            i++;
        }
        
        // Phase 2: Merge overlapping intervals.
        // This loop continues as long as the current interval's start time is less than or equal to the new interval's end time.
        // This condition signifies an overlap or that the current interval touches the new interval.
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Update the start of newInterval to be the minimum of its current start and the current interval's start.
            // This ensures the merged interval starts at the earliest possible point.
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            // Update the end of newInterval to be the maximum of its current end and the current interval's end.
            // This ensures the merged interval ends at the latest possible point.
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            // Move to the next interval to check for further overlaps.
            i++;
        }
        
        // After merging all overlapping intervals, add the (potentially expanded) newInterval to the result list.
        result.add(newInterval);
        
        // Phase 3: Add all remaining intervals that start after the new interval ends.
        // These intervals do not overlap with newInterval and come after it.
        while (i < n) {
            // Add the current interval to the result list.
            result.add(intervals[i]);
            // Move to the next interval.
            i++;
        }
        
        // Convert the list of merged intervals back into a 2D integer array format as required by the problem.
        // The toArray method takes an array of the correct type and size as an argument.
        return result.toArray(new int[result.size()][]);
    }
}
```

## Interview Tips
*   **Clarify Input:** Ask if the input `intervals` array is guaranteed to be sorted and non-overlapping. This is crucial for the algorithm's correctness.
*   **Walk Through Examples:** Be prepared to walk through a few examples manually, especially edge cases like inserting at the beginning, end, or in the middle of existing intervals, and cases with no overlaps.
*   **Explain the Three Phases:** Clearly articulate the three distinct phases of processing: intervals before, overlapping intervals, and intervals after. This demonstrates a structured approach.
*   **Discuss Space Optimization (if applicable):** While O(N) space is generally accepted here, briefly consider if an in-place modification were possible (though it's more complex and often not preferred for clarity).

## Revision Checklist
- [ ] Understand the problem statement: insert a new interval into a sorted, non-overlapping list of intervals and merge if necessary.
- [ ] Identify the three logical sections of intervals relative to the new interval: before, overlapping, after.
- [ ] Implement the loop for adding intervals that come strictly before the `newInterval`.
- [ ] Implement the loop for merging overlapping intervals, correctly updating `newInterval`'s bounds.
- [ ] Ensure the merged `newInterval` is added to the result.
- [ ] Implement the loop for adding intervals that come strictly after the `newInterval`.
- [ ] Handle the conversion from `List<int[]>` back to `int[][]`.
- [ ] Analyze time and space complexity.
- [ ] Practice with edge cases: empty `intervals`, `newInterval` before all, `newInterval` after all, `newInterval` overlapping with multiple, `newInterval` completely contained.

## Similar Problems
*   Merge Intervals (LeetCode 56)
*   Non-overlapping Intervals (LeetCode 435)
*   Meeting Rooms II (LeetCode 253)

## Tags
`Array` `Sort` `Greedy`
