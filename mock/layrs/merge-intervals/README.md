# Merge Intervals

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Sorting`  
**Time:** O(N log N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int curr = 0;
        int  n = intervals.length;
        for(int i=1;i<n;i++){
            if(intervals[curr][1] >= intervals[i][0]){ // merge
                intervals[curr][1] = Math.max(intervals[i][1], intervals[curr][1]);
            } else {
                curr++;
                intervals[curr] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals,0,curr+1);
    }
}
```

---

---
## Quick Revision
Given a collection of intervals, merge all overlapping intervals.
Sort intervals by start time and iterate, merging if overlap exists.

## Intuition
The core idea is that if we sort the intervals by their start times, we only need to consider merging the current interval with the *previous* merged interval. If the current interval's start time is less than or equal to the end time of the previous merged interval, they overlap, and we can extend the end of the previous merged interval to encompass the current one. If they don't overlap, the current interval starts a new, distinct merged interval.

## Algorithm
1. Sort the input `intervals` array based on the start times of the intervals.
2. Initialize a variable `curr` to 0, representing the index of the last merged interval.
3. Iterate through the sorted `intervals` array starting from the second interval (index 1).
4. For each interval `intervals[i]`:
    a. Check if `intervals[i]` overlaps with the current merged interval `intervals[curr]`. Overlap occurs if `intervals[curr][1] >= intervals[i][0]`.
    b. If they overlap:
        i. Update the end time of the current merged interval `intervals[curr][1]` to be the maximum of its current end time and the end time of `intervals[i]`. This effectively merges the two intervals.
    c. If they do not overlap:
        i. Increment `curr` to point to the next available slot for a new merged interval.
        ii. Copy `intervals[i]` to `intervals[curr]`. This starts a new merged interval.
5. After iterating through all intervals, the merged intervals will be stored in the `intervals` array from index 0 to `curr`.
6. Return a new array containing the merged intervals, from index 0 up to `curr + 1`.

## Concept to Remember
*   **Sorting:** Essential for efficiently comparing adjacent or potentially overlapping intervals.
*   **Greedy Approach:** Making the locally optimal choice (merging if possible) leads to the globally optimal solution.
*   **In-place Modification:** The algorithm modifies the input array to store merged intervals, saving space.

## Common Mistakes
*   **Not Sorting:** Failing to sort the intervals by start time will lead to incorrect merging logic.
*   **Incorrect Overlap Condition:** Using `intervals[curr][1] > intervals[i][0]` instead of `>=` will miss cases where intervals touch at their boundaries.
*   **Modifying the Wrong Interval:** Incorrectly updating the end time of `intervals[i]` instead of `intervals[curr][1]`.
*   **Handling the Last Merged Interval:** Not correctly returning the final set of merged intervals (e.g., off-by-one errors in array slicing).

## Complexity Analysis
*   **Time:** O(N log N) - due to the sorting step, where N is the number of intervals. The iteration is O(N).
*   **Space:** O(1) - if we consider the space used by the output array as not part of the auxiliary space. If the output array is considered, it's O(N) in the worst case (no intervals merged). The sorting might use O(log N) or O(N) space depending on the implementation.

## Commented Code
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on their start times. This is crucial for the greedy approach.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 'curr' tracks the index of the last merged interval in the 'intervals' array.
        int curr = 0;
        // Get the total number of intervals.
        int n = intervals.length;

        // Iterate through the intervals starting from the second one (index 1).
        for (int i = 1; i < n; i++) {
            // Check if the current interval 'intervals[i]' overlaps with the last merged interval 'intervals[curr]'.
            // Overlap occurs if the end of the current merged interval is greater than or equal to the start of the next interval.
            if (intervals[curr][1] >= intervals[i][0]) { // merge
                // If they overlap, merge them by updating the end time of the current merged interval.
                // The new end time is the maximum of the current merged interval's end and the current interval's end.
                intervals[curr][1] = Math.max(intervals[i][1], intervals[curr][1]);
            } else {
                // If there's no overlap, the current interval 'intervals[i]' starts a new merged interval.
                // Move 'curr' to the next position to indicate the start of a new merged interval.
                curr++;
                // Copy the current interval 'intervals[i]' to the new merged interval position.
                intervals[curr] = intervals[i];
            }
        }
        // After the loop, all merged intervals are in 'intervals' from index 0 to 'curr'.
        // Create a new array containing only the merged intervals and return it.
        // Arrays.copyOfRange creates a new array from the original 'intervals' array,
        // starting from index 0 up to (but not including) 'curr + 1'.
        return Arrays.copyOfRange(intervals, 0, curr + 1);
    }
}
```

## Interview Tips
*   **Explain the Sorting:** Emphasize why sorting is the first and most critical step.
*   **Walk Through an Example:** Use a small example like `[[1,3],[2,6],[8,10],[15,18]]` to demonstrate the merging process step-by-step.
*   **Discuss Edge Cases:** Mention what happens with an empty input array, an array with one interval, or intervals that are completely contained within others.
*   **Clarify Space Complexity:** Be ready to discuss whether the output array counts towards space complexity.

## Revision Checklist
- [ ] Understand the problem statement: merging overlapping intervals.
- [ ] Recognize the need for sorting by start time.
- [ ] Implement the greedy merging logic correctly.
- [ ] Handle the case where intervals do not overlap.
- [ ] Correctly extract the merged intervals for the final output.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Insert Interval
*   Non-overlapping Intervals
*   Meeting Rooms
*   Meeting Rooms II

## Tags
`Array` `Sort`
