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
        int i=0;
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();
        while(i<n && intervals[i][1] < newInterval[0]) ans.add(intervals[i++]);
        while(i<n && (newInterval[1] >= intervals[i][0])){
          newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
          newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
          i++;
        }
        ans.add(newInterval);
        while(i<n)ans.add(intervals[i++]);
        int[][] arr = new int[ans.size()][2];
        i=0;
        for(int[] temp : ans) arr[i++] = temp;
        return arr;
    }
}
```

---

---
## Quick Revision
Given a sorted list of non-overlapping intervals, insert a new interval and merge if necessary.
Iterate through intervals, add non-overlapping ones, merge overlapping ones with the new interval, and then add remaining intervals.

## Intuition
The core idea is to process the intervals in order. We can identify three phases: intervals that come strictly before the `newInterval` and don't overlap, intervals that overlap with `newInterval` and need to be merged, and intervals that come strictly after `newInterval` and don't overlap. By iterating and handling these phases sequentially, we can construct the merged list.

## Algorithm
1. Initialize an empty list `ans` to store the merged intervals.
2. Initialize an index `i` to 0.
3. **Phase 1: Add non-overlapping intervals before `newInterval`**:
   Iterate while `i` is within bounds and the end of the current interval `intervals[i][1]` is less than the start of `newInterval[0]`. Add `intervals[i]` to `ans` and increment `i`.
4. **Phase 2: Merge overlapping intervals**:
   Iterate while `i` is within bounds and the end of `newInterval[1]` is greater than or equal to the start of the current interval `intervals[i][0]`. This condition signifies an overlap.
   - Update `newInterval[0]` to be the minimum of its current value and `intervals[i][0]`.
   - Update `newInterval[1]` to be the maximum of its current value and `intervals[i][1]`.
   - Increment `i`.
5. **Add the merged `newInterval`**: After the merging loop, add the (potentially updated) `newInterval` to `ans`.
6. **Phase 3: Add non-overlapping intervals after `newInterval`**:
   Iterate while `i` is within bounds. Add `intervals[i]` to `ans` and increment `i`.
7. Convert the `ans` list of integer arrays into a 2D integer array and return it.

## Concept to Remember
*   Interval Merging: Combining overlapping intervals into a single larger interval.
*   Two Pointers/Iterative Approach: Efficiently processing sorted data by using indices to track progress.
*   Greedy Strategy: Making locally optimal choices (merging when overlap is detected) to achieve a globally optimal solution.

## Common Mistakes
*   Incorrectly defining the overlap condition: Missing cases where intervals touch or one is fully contained within another.
*   Not handling the edge cases: Empty `intervals` list, `newInterval` being before all existing intervals, or after all existing intervals.
*   Modifying the original `intervals` array directly: This can lead to unexpected behavior and is generally bad practice. Using a new list is safer.
*   Forgetting to add the `newInterval` itself after merging: The merged `newInterval` needs to be explicitly added to the result list.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the `intervals` array at most three times (once for intervals before, once for merging, once for intervals after). Each interval is processed a constant number of times.
- Space: O(N) - reason: In the worst case, we might need to store all `N` intervals in the `ans` list if no merging occurs, or if the `newInterval` is inserted without merging.

## Commented Code
```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Initialize a list to store the resulting merged intervals.
        List<int[]> ans = new ArrayList<>();
        // Initialize an index to iterate through the input intervals.
        int i = 0;
        // Get the total number of intervals.
        int n = intervals.length;

        // Phase 1: Add all intervals that end before the newInterval starts.
        // These intervals do not overlap with newInterval and come before it.
        while (i < n && intervals[i][1] < newInterval[0]) {
            // Add the current interval to the result list.
            ans.add(intervals[i]);
            // Move to the next interval.
            i++;
        }

        // Phase 2: Merge overlapping intervals.
        // This loop continues as long as there's an overlap between newInterval and the current interval.
        // Overlap condition: newInterval's end is greater than or equal to the current interval's start.
        while (i < n && (newInterval[1] >= intervals[i][0])) {
            // Update the start of newInterval to be the minimum of its current start and the current interval's start.
            // This ensures the merged interval starts at the earliest possible point.
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            // Update the end of newInterval to be the maximum of its current end and the current interval's end.
            // This ensures the merged interval ends at the latest possible point.
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            // Move to the next interval to check for further overlaps.
            i++;
        }
        // After merging all overlapping intervals, add the (potentially updated) newInterval to the result list.
        ans.add(newInterval);

        // Phase 3: Add all remaining intervals that start after the newInterval ends.
        // These intervals do not overlap with newInterval and come after it.
        while (i < n) {
            // Add the current interval to the result list.
            ans.add(intervals[i]);
            // Move to the next interval.
            i++;
        }

        // Convert the list of intervals back into a 2D integer array.
        int[][] arr = new int[ans.size()][2];
        // Initialize an index for the new array.
        i = 0;
        // Iterate through the list of merged intervals.
        for (int[] temp : ans) {
            // Copy each merged interval into the result array.
            arr[i++] = temp;
        }
        // Return the final array of merged intervals.
        return arr;
    }
}
```

## Interview Tips
*   Clearly explain the three phases of processing: before, during (merging), and after the `newInterval`.
*   Walk through an example by hand on a whiteboard or paper to demonstrate your understanding of the merging logic.
*   Pay attention to the boundary conditions for the loops and the overlap check.
*   Discuss the time and space complexity of your solution.

## Revision Checklist
- [ ] Understand the problem statement: insert and merge intervals.
- [ ] Identify non-overlapping intervals before the new one.
- [ ] Implement the merging logic for overlapping intervals.
- [ ] Handle the case where the new interval doesn't overlap with any existing ones.
- [ ] Add non-overlapping intervals after the new one.
- [ ] Convert the result list back to a 2D array.
- [ ] Consider edge cases: empty input, `newInterval` at the beginning/end.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals (LeetCode 56)
*   Non-overlapping Intervals (LeetCode 435)
*   Meeting Rooms II (LeetCode 253)

## Tags
`Array` `Sort` `Greedy`
