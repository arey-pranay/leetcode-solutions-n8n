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
                intervals[curr] = intervals[i]; // copy it ahead kyuki uspe updated intervals aana chaiye for later comparison(s)
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
The core idea is that if we process intervals in sorted order of their start times, we only need to consider the current interval and the *last merged interval*. If the current interval overlaps with the last merged interval, we extend the last merged interval. Otherwise, the current interval starts a new, non-overlapping interval. This greedy approach works because sorting ensures we're always looking at the earliest possible start times.

## Algorithm
1. Sort the input `intervals` array based on the start time of each interval.
2. Initialize a variable `curr` to 0, representing the index of the last merged interval.
3. Iterate through the sorted `intervals` array starting from the second interval (index 1).
4. For each interval `intervals[i]`:
    a. Check if it overlaps with the current merged interval `intervals[curr]`. Overlap occurs if `intervals[curr][1]` (end of current merged) is greater than or equal to `intervals[i][0]` (start of current interval).
    b. If they overlap:
        i. Update the end time of the current merged interval `intervals[curr][1]` to be the maximum of its current end time and the end time of `intervals[i]`. This effectively extends the merged interval.
    c. If they do not overlap:
        i. Increment `curr` to point to the next available slot for a new merged interval.
        ii. Copy the current interval `intervals[i]` to `intervals[curr]`. This is because `intervals[curr]` will now represent the start of a new merged interval, and subsequent intervals will be compared against it.
5. After the loop, the merged intervals are stored in the `intervals` array from index 0 up to `curr`.
6. Return a new array containing the merged intervals, from index 0 to `curr` (inclusive).

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a global optimum.
*   **Sorting:** Essential for enabling efficient comparison and merging of intervals.
*   **In-place Modification:** The algorithm modifies the input array to store merged intervals, optimizing space.

## Common Mistakes
*   **Not Sorting:** Failing to sort the intervals by their start times will lead to incorrect merging logic.
*   **Incorrect Overlap Condition:** Using `<` instead of `>=` for the end of the current merged interval and the start of the next interval.
*   **Handling the Last Merged Interval:** Not correctly updating the end of the `intervals[curr]` when merging, or not correctly advancing `curr` when a new interval starts.
*   **Returning the Correct Range:** Returning the entire original array instead of the portion containing the merged intervals.

## Complexity Analysis
*   **Time:** O(N log N) - due to the sorting step, where N is the number of intervals. The iteration is O(N).
*   **Space:** O(1) - if we consider the space used by the output array as not part of the auxiliary space. If the output array is considered, it's O(N) in the worst case (no intervals merge). The sorting might use O(log N) or O(N) space depending on the implementation.

## Commented Code
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on their start times. This is crucial for the greedy approach.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 'curr' tracks the index of the last merged interval. We start with the first interval.
        int curr = 0;
        // Get the total number of intervals.
        int n = intervals.length;

        // Iterate through the intervals starting from the second one.
        for (int i = 1; i < n; i++) {
            // Check if the current interval 'intervals[i]' overlaps with the last merged interval 'intervals[curr]'.
            // Overlap occurs if the end of the last merged interval is greater than or equal to the start of the current interval.
            if (intervals[curr][1] >= intervals[i][0]) { // merge
                // If there's an overlap, merge them by extending the end of the last merged interval.
                // The new end is the maximum of the current merged interval's end and the current interval's end.
                intervals[curr][1] = Math.max(intervals[i][1], intervals[curr][1]);
            } else {
                // If there's no overlap, the current interval 'intervals[i]' starts a new merged interval.
                // Increment 'curr' to point to the next available slot for a new merged interval.
                curr++;
                // Copy the current interval 'intervals[i]' to the new 'curr' position.
                // This is because 'intervals[curr]' will now represent the start of a new merged interval,
                // and subsequent intervals will be compared against it.
                intervals[curr] = intervals[i];
            }
        }
        // After iterating through all intervals, the merged intervals are stored from index 0 to 'curr'.
        // Create a new array containing only the merged intervals.
        return Arrays.copyOfRange(intervals, 0, curr + 1);
    }
}
```

## Interview Tips
*   **Explain the Sorting:** Emphasize why sorting by start time is the critical first step and how it enables the greedy approach.
*   **Walk Through an Example:** Use a small example like `[[1,3],[2,6],[8,10],[15,18]]` to demonstrate the merging process step-by-step.
*   **Discuss Edge Cases:** Mention what happens with an empty input array, an array with a single interval, or intervals that are completely contained within others.
*   **Clarify Space Complexity:** Be ready to discuss whether the output array counts towards space complexity and the implications of in-place modification.

## Revision Checklist
- [ ] Understand the problem: merging overlapping intervals.
- [ ] Recognize the need for sorting by start time.
- [ ] Implement the greedy merging logic correctly.
- [ ] Handle the update of the last merged interval's end.
- [ ] Correctly advance the pointer for new, non-overlapping intervals.
- [ ] Return the correct sub-array of merged intervals.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Insert Interval
*   Non-overlapping Intervals
*   Meeting Rooms
*   Meeting Rooms II

## Tags
`Array` `Sort`
