# Merge Intervals

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Sorting`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        ArrayList<int[]> ans = new ArrayList<>();
        int n = intervals.length;
        int j = 0;
        ans.add(intervals[0]);
        for(int i=1;i<n;i++){
            int[] curr = ans.get(j);
            int[] next = intervals[i];
            if(curr[1] > next[1]) i+=0;
            else if(curr[1] >= next[0])curr[1]=next[1];
            else{ans.add(next);j++;} 
        }
        int[][] arr = new int[ans.size()][2];
        int i=0;
        for(int[] temp : ans) arr[i] = ans.get(i++);
        return arr;
    }
}
```

---

---
## Quick Revision
Given a collection of intervals, merge all overlapping intervals.
Sort intervals by start time and iterate, merging if the current interval overlaps with the last merged one.

## Intuition
The key insight is that if we sort the intervals by their start times, we only need to consider merging the current interval with the *last* merged interval. If the current interval's start time is less than or equal to the end time of the last merged interval, they overlap. We then extend the end time of the last merged interval to be the maximum of its current end time and the current interval's end time. If they don't overlap, the current interval starts a new, non-overlapping interval.

## Algorithm
1. Sort the input `intervals` array based on the start times of the intervals.
2. Initialize an empty list `mergedIntervals` to store the non-overlapping intervals.
3. If the input `intervals` is empty, return an empty 2D array.
4. Add the first interval from the sorted `intervals` to `mergedIntervals`.
5. Iterate through the sorted `intervals` starting from the second interval.
6. For each current interval, compare its start time with the end time of the last interval in `mergedIntervals`.
7. If the current interval overlaps with the last merged interval (i.e., `current_start <= last_merged_end`):
    a. Update the end time of the last merged interval to be the maximum of its current end time and the current interval's end time (`last_merged_end = max(last_merged_end, current_end)`).
8. If the current interval does not overlap:
    a. Add the current interval to `mergedIntervals`.
9. Convert the `mergedIntervals` list back into a 2D array and return it.

## Concept to Remember
*   **Sorting:** Efficiently ordering data is crucial for many interval problems.
*   **Greedy Approach:** Making the locally optimal choice at each step leads to a globally optimal solution.
*   **Interval Overlap Condition:** Understanding how to mathematically determine if two intervals intersect.

## Common Mistakes
*   Forgetting to sort the intervals first, which breaks the greedy logic.
*   Incorrectly handling the overlap condition (e.g., using `<` instead of `<=`).
*   Modifying the original `intervals` array in place when it's not intended or leads to complex logic.
*   Not correctly updating the end time of the merged interval when an overlap occurs.

## Complexity Analysis
*   **Time:** O(N log N) - due to sorting the intervals. The iteration through the sorted intervals takes O(N) time.
*   **Space:** O(N) - in the worst case, if no intervals overlap, the `mergedIntervals` list will store all N intervals. If we consider the output array as part of space complexity, it's O(N). If not, it's O(1) if we modify in place (though the provided solution uses extra space).

## Commented Code
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on their start times. This is crucial for the greedy approach.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Use an ArrayList to store the merged intervals because its size can grow dynamically.
        ArrayList<int[]> ans = new ArrayList<>();
        int n = intervals.length; // Get the total number of intervals.

        // If there are no intervals, return an empty 2D array.
        if (n == 0) {
            return new int[0][0];
        }

        // Add the first interval to our result list to start the merging process.
        ans.add(intervals[0]);
        // 'j' will track the index of the last merged interval in the 'ans' list.
        int j = 0;

        // Iterate through the intervals starting from the second one.
        for (int i = 1; i < n; i++) {
            // Get the last merged interval from our 'ans' list.
            int[] curr = ans.get(j);
            // Get the current interval we are considering from the sorted input.
            int[] next = intervals[i];

            // Check for overlap: if the current interval's end is greater than the last merged interval's end.
            // This condition is slightly simplified in the original code. A more explicit check is:
            // if (curr[1] >= next[0]) { // Overlap detected
            //     curr[1] = Math.max(curr[1], next[1]); // Merge by extending the end time
            // } else { // No overlap
            //     ans.add(next); // Add the current interval as a new, separate interval
            //     j++; // Increment 'j' to point to this new last merged interval
            // }

            // The original code's logic:
            // if(curr[1] > next[1]) i+=0; // This line is redundant and doesn't affect logic. It implies if the current merged interval ends *after* the next interval, no merge is needed for the end time.
            // else if(curr[1] >= next[0])curr[1]=next[1]; // If the current merged interval's end is greater than or equal to the next interval's start, they overlap. Update the current merged interval's end to be the maximum of its current end and the next interval's end.
            // else{ans.add(next);j++;} // If there's no overlap (next interval starts after current merged interval ends), add the next interval as a new merged interval and advance 'j'.

            // Corrected and clearer logic based on the original intent:
            if (curr[1] >= next[0]) { // If the last merged interval overlaps with the current interval
                curr[1] = Math.max(curr[1], next[1]); // Extend the end of the last merged interval
            } else { // If there is no overlap
                ans.add(next); // Add the current interval as a new, separate merged interval
                j++; // Move 'j' to point to this newly added interval
            }
        }

        // Convert the ArrayList of merged intervals back into a 2D array for the return type.
        int[][] arr = new int[ans.size()][2];
        int i = 0;
        // Iterate through the ArrayList and copy each interval into the result array.
        for (int[] temp : ans) {
            arr[i] = ans.get(i++); // Copy the interval and increment index 'i'
        }
        // Return the final array of merged intervals.
        return arr;
    }
}
```

## Interview Tips
*   Clearly explain your sorting step and why it's necessary.
*   Walk through an example on the whiteboard to demonstrate the merging logic.
*   Be prepared to discuss edge cases like empty input or intervals that are completely contained within others.
*   If asked about in-place modification, consider how you might achieve that (though it's often more complex and less readable).

## Revision Checklist
- [ ] Understand the problem statement for merging overlapping intervals.
- [ ] Implement sorting intervals by start time.
- [ ] Correctly identify overlapping intervals.
- [ ] Implement the logic to merge overlapping intervals by updating the end time.
- [ ] Handle non-overlapping intervals by adding them as new merged intervals.
- [ ] Convert the result back to the required 2D array format.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Insert Interval
*   Non-overlapping Intervals
*   Meeting Rooms
*   Meeting Rooms II

## Tags
`Array` `Sort`
