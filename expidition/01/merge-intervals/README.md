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
        Arrays.sort(intervals, (a,b)->a[0][0]-b[0][0]);
        int filling=0;
        
        for(int i=1;i<intervals.length;i++){
            if(intervals[filling][1]>=intervals[i][0]){
                intervals[filling][1]=Math.max(intervals[i][1],intervals[filling][1]);
                
            } else {
                filling++;
                intervals[filling] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, filling+1);
    }
}
```

---

---
## Quick Revision
Given a collection of intervals, merge all overlapping intervals.
Sort intervals by start time and iterate, merging if overlap exists.

## Intuition
The core idea is that if we process intervals in sorted order of their start times, we only need to consider the current interval and the *last merged interval*. If the current interval overlaps with the last merged interval, we extend the last merged interval. Otherwise, the current interval starts a new, non-overlapping interval.

## Algorithm
1. Sort the input `intervals` array based on the start time of each interval.
2. Initialize a pointer `filling` to 0, representing the index of the last merged interval.
3. Iterate through the sorted `intervals` starting from the second interval (index 1).
4. For each current interval `intervals[i]`:
    a. Check if it overlaps with the last merged interval `intervals[filling]`. Overlap occurs if `intervals[filling][1]` (end of last merged) is greater than or equal to `intervals[i][0]` (start of current).
    b. If they overlap:
        i. Update the end time of the last merged interval `intervals[filling][1]` to be the maximum of its current end time and the end time of the current interval `intervals[i][1]`. This effectively extends the merged interval.
    c. If they do not overlap:
        i. Increment `filling` to point to the next available slot for a new merged interval.
        ii. Copy the current interval `intervals[i]` to `intervals[filling]`. This starts a new merged interval.
5. After iterating through all intervals, the merged intervals are stored in the `intervals` array from index 0 up to `filling`.
6. Return a new array containing only the merged intervals, from index 0 to `filling + 1`.

## Concept to Remember
*   **Sorting:** Essential for efficiently identifying overlapping intervals by processing them in a specific order.
*   **Greedy Approach:** At each step, we make the locally optimal choice (merging or starting a new interval) which leads to a globally optimal solution.
*   **In-place Modification:** The algorithm modifies the input array to store merged intervals, saving space.

## Common Mistakes
*   Not sorting the intervals first, leading to incorrect overlap detection.
*   Incorrectly handling the `filling` pointer, either by not incrementing it or by overwriting necessary data.
*   Off-by-one errors when checking for overlap or when returning the final result.
*   Not correctly updating the end time of the merged interval (e.g., only taking the current interval's end time instead of the maximum).

## Complexity Analysis
*   **Time:** O(N log N) - due to the sorting step, where N is the number of intervals. The iteration is O(N).
*   **Space:** O(1) - if we consider the space used by the sorting algorithm to be in-place or O(log N) to O(N) depending on the sorting implementation. The modification is done in-place. If a new array is explicitly created for the result, it would be O(N) in the worst case.

## Commented Code
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on their start times. This is crucial for the greedy approach.
        // The lambda expression (a,b)->a[0]-b[0] compares the first element (start time) of two intervals.
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);

        // 'filling' acts as a pointer to the index of the last merged interval in the 'intervals' array.
        // We will overwrite intervals from this index onwards.
        int filling = 0;

        // Iterate through the intervals starting from the second one (index 1).
        // The first interval (index 0) is implicitly the first merged interval.
        for(int i = 1; i < intervals.length; i++){
            // Check for overlap: if the end of the last merged interval (intervals[filling][1])
            // is greater than or equal to the start of the current interval (intervals[i][0]).
            if(intervals[filling][1] >= intervals[i][0]){
                // If there's an overlap, merge the current interval into the last merged interval.
                // Update the end time of the last merged interval to be the maximum of its current end
                // and the end of the current interval. This ensures the merged interval covers both.
                intervals[filling][1] = Math.max(intervals[i][1], intervals[filling][1]);
            } else {
                // If there's no overlap, the current interval starts a new, distinct merged interval.
                // Increment 'filling' to move to the next position where a new merged interval will be stored.
                filling++;
                // Copy the current interval to the new 'filling' position.
                intervals[filling] = intervals[i];
            }
        }

        // After the loop, all merged intervals are in the 'intervals' array from index 0 to 'filling'.
        // We need to return a new array containing only these merged intervals.
        // Arrays.copyOfRange creates a new array from the original 'intervals' array,
        // starting at index 0 and ending at 'filling + 1' (exclusive of the end index).
        return Arrays.copyOfRange(intervals, 0, filling + 1);
    }
}
```

## Interview Tips
*   Clearly explain the sorting step and why it's necessary for the greedy approach.
*   Walk through an example by hand, showing how the `filling` pointer and interval merging works.
*   Discuss the time and space complexity, justifying each part.
*   Be prepared to discuss edge cases like an empty input array or an array with only one interval.

## Revision Checklist
- [ ] Understand the problem statement: merging overlapping intervals.
- [ ] Implement sorting by start time.
- [ ] Correctly use a pointer for the last merged interval.
- [ ] Implement the overlap condition check.
- [ ] Correctly update the end time of a merged interval.
- [ ] Handle non-overlapping intervals by advancing the pointer and copying.
- [ ] Return the correct sub-array of merged intervals.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Insert Interval
*   Non-overlapping Intervals
*   Meeting Rooms
*   Meeting Rooms II

## Tags
`Array` `Sort`

## My Notes
Bestest Solution
