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
The core idea is to process the intervals in order. We can identify three phases: intervals that come strictly before the `newInterval` and don't overlap, intervals that overlap with `newInterval` and need to be merged, and intervals that come strictly after `newInterval` and don't overlap. By handling these phases sequentially, we can construct the merged list. The "aha moment" is realizing that because the input `intervals` are sorted, we can process them linearly and efficiently merge.

## Algorithm
1. Initialize an empty list `ans` to store the merged intervals.
2. Initialize an index `i` to 0.
3. **Phase 1: Add non-overlapping intervals before `newInterval`**:
   Iterate while `i` is within bounds and the current interval's end (`intervals[i][1]`) is less than the `newInterval`'s start (`newInterval[0]`). Add `intervals[i]` to `ans` and increment `i`.
4. **Phase 2: Merge overlapping intervals**:
   Iterate while `i` is within bounds and the `newInterval`'s end (`newInterval[1]`) is greater than or equal to the current interval's start (`intervals[i][0]`). This condition signifies an overlap.
   - Update `newInterval[0]` to be the minimum of its current start and `intervals[i][0]`.
   - Update `newInterval[1]` to be the maximum of its current end and `intervals[i][1]`.
   - Increment `i`.
5. **Add the merged `newInterval`**: After the merging loop, add the (potentially updated) `newInterval` to `ans`.
6. **Phase 3: Add non-overlapping intervals after `newInterval`**:
   Iterate while `i` is within bounds. Add `intervals[i]` to `ans` and increment `i`.
7. Convert the `ans` list of integer arrays into a 2D integer array and return it.

## Concept to Remember
*   **Interval Merging**: The fundamental operation is merging overlapping intervals by adjusting their start and end points.
*   **Sorted Input**: The problem relies heavily on the input `intervals` being sorted by their start times.
*   **Linear Scan**: Efficiently processing sorted data by iterating through it once.
*   **Data Structures**: Using a dynamic list (like `ArrayList`) to build the result before converting to a fixed-size array.

## Common Mistakes
*   **Incorrect Overlap Condition**: Misjudging when two intervals truly overlap (e.g., `newInterval.end < currentInterval.start` is not an overlap, but `newInterval.end >= currentInterval.start` is a potential overlap).
*   **Not Handling All Three Phases**: Forgetting to add intervals before, during, or after the merge process.
*   **Modifying Input Array Directly**: While possible, it's often cleaner and less error-prone to build a new result list.
*   **Off-by-One Errors**: Incorrectly handling loop conditions or index increments.
*   **Forgetting to Add the `newInterval`**: After merging, the `newInterval` itself needs to be added to the result.

## Complexity Analysis
- Time: O(N) - reason: We iterate through the `intervals` array at most three times (once for intervals before, once for merging, and once for intervals after). Each interval is processed a constant number of times.
- Space: O(N) - reason: In the worst case, we might need to store all `N` intervals in the `ans` list if no merging occurs.

## Commented Code
```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Initialize an ArrayList to store the resulting merged intervals.
        List<int[]> ans = new ArrayList<>();
        // Initialize an index 'i' to iterate through the input 'intervals' array.
        int i = 0;
        // Get the total number of intervals in the input array.
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
        // An overlap exists if the newInterval's end is greater than or equal to the current interval's start.
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

        // Convert the ArrayList of intervals back into a 2D integer array.
        // Create a new 2D array with dimensions matching the size of the result list.
        int[][] arr = new int[ans.size()][2];
        // Reset index 'i' to 0 for populating the new array.
        i = 0;
        // Iterate through each interval in the result list.
        for (int[] temp : ans) {
            // Copy the interval from the list to the new 2D array.
            arr[i++] = temp;
        }
        // Return the final merged and sorted list of intervals.
        return arr;
    }
}
```

## Interview Tips
*   **Clarify Input**: Ask if the input `intervals` are guaranteed to be sorted and non-overlapping. This is crucial for the algorithm's correctness.
*   **Edge Cases**: Discuss edge cases like an empty `intervals` array, `newInterval` being before all existing intervals, `newInterval` being after all existing intervals, and `newInterval` completely encompassing existing intervals.
*   **Walkthrough**: Be prepared to walk through an example manually, explaining each step of your algorithm and how the `newInterval` is being modified.
*   **Data Structure Choice**: Justify why `ArrayList` is a good choice for building the result dynamically before converting to a fixed-size array.

## Revision Checklist
- [ ] Understand the problem: insert and merge intervals.
- [ ] Recognize the importance of sorted input.
- [ ] Implement the three-phase approach: before, merge, after.
- [ ] Correctly define the overlap condition.
- [ ] Handle `newInterval` updates during merging.
- [ ] Convert `ArrayList` to `int[][]` at the end.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals (LeetCode 56)
*   Non-overlapping Intervals (LeetCode 435)
*   Meeting Rooms II (LeetCode 253)

## Tags
`Array` `Sort` `Greedy`
