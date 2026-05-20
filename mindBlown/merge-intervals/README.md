# Merge Intervals

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Sort`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if(n==0) return new int[][]{};

        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);
        ArrayList<int[]> arr = new ArrayList<>();
        arr.add(intervals[0]);

        for(int i=1;i<n;i++){
            int[] curr = arr.get(arr.size()-1);
            if(curr[1] < intervals[i][0]){
                arr.add(intervals[i]);
            } else{
                if(curr[1] >= intervals[i][1]) continue;
                else curr[1] = intervals[i][1];
            } 
        }
        int[][] ans = new int[arr.size()][2];
        int i=0;
        for(int[] temp : arr)ans[i++] = temp;
        return ans;
    }
}
```

---

---
## Quick Revision
Given a collection of intervals, merge all overlapping intervals.
Sort intervals by start time and iterate, merging if the current interval overlaps with the last merged interval.

## Intuition
The core idea is that if we process intervals in order of their start times, we only need to consider merging the current interval with the *last* merged interval. If the current interval's start is after the last merged interval's end, it's a new, non-overlapping interval. Otherwise, they overlap, and we extend the end of the last merged interval to encompass the current one.

## Algorithm
1. Handle the edge case: If the input `intervals` array is empty, return an empty 2D array.
2. Sort the `intervals` array based on the start time of each interval (the first element of each inner array).
3. Initialize an `ArrayList` called `arr` to store the merged intervals. Add the first interval from the sorted `intervals` array to `arr`.
4. Iterate through the sorted `intervals` array starting from the second interval (index 1).
5. For each current interval `intervals[i]`:
    a. Get the last merged interval from `arr` (let's call it `curr`).
    b. Check for overlap: If `curr[1]` (end of the last merged interval) is less than `intervals[i][0]` (start of the current interval), it means there's no overlap. Add `intervals[i]` as a new merged interval to `arr`.
    c. If there is an overlap (`curr[1] >= intervals[i][0]`):
        i. Update the end of the `curr` interval to be the maximum of `curr[1]` and `intervals[i][1]`. This effectively merges the current interval into the last one.
6. After iterating through all intervals, convert the `ArrayList<int[]>` `arr` into a 2D integer array `ans`.
7. Return the `ans` array.

## Concept to Remember
*   **Sorting:** Efficiently processing intervals often requires sorting them by a key attribute (like start time).
*   **Greedy Approach:** Making the locally optimal choice at each step (merging if possible) leads to the globally optimal solution.
*   **Interval Management:** Understanding how to represent and manipulate intervals, especially for merging or checking overlaps.

## Common Mistakes
*   **Not Sorting:** Failing to sort the intervals first will lead to incorrect merging logic as you might miss overlaps.
*   **Incorrect Overlap Condition:** Using `<` instead of `<=` or vice-versa when comparing interval ends and starts.
*   **Modifying Original Array Incorrectly:** Directly modifying the input `intervals` array when it's not intended or when it can lead to issues if not handled carefully. Using a separate list for merged intervals is safer.
*   **Off-by-One Errors:** Incorrectly handling the loop bounds or array indices.

## Complexity Analysis
*   **Time:** O(N log N) - The dominant factor is sorting the intervals. The subsequent iteration is O(N).
*   **Space:** O(N) - In the worst case, no intervals merge, and the `ArrayList` will store all N intervals. If we consider the output array as part of space complexity, it's also O(N).

## Commented Code
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        // Get the number of intervals.
        int n = intervals.length;
        // If there are no intervals, return an empty result.
        if(n==0) return new int[][]{};

        // Sort the intervals based on their start times. This is crucial for the greedy approach.
        // The lambda expression (a,b) -> a[0] - b[0] sorts in ascending order of the first element.
        Arrays.sort(intervals,(a,b) -> a[0] - b[0]);

        // Use an ArrayList to store the merged intervals. ArrayList is dynamic and easier to add to.
        ArrayList<int[]> arr = new ArrayList<>();
        // Add the first interval to our list of merged intervals.
        arr.add(intervals[0]);

        // Iterate through the sorted intervals starting from the second one.
        for(int i=1;i<n;i++){
            // Get the last merged interval from our ArrayList.
            int[] curr = arr.get(arr.size()-1);
            // Check if the current interval's start time is after the last merged interval's end time.
            if(curr[1] < intervals[i][0]){
                // If there's no overlap, add the current interval as a new merged interval.
                arr.add(intervals[i]);
            } else{
                // If there is an overlap, we need to merge.
                // Check if the current interval is completely contained within the last merged interval.
                if(curr[1] >= intervals[i][1]) {
                    // If it is, we don't need to do anything, just continue to the next interval.
                    continue;
                }
                else {
                    // If the current interval extends beyond the last merged interval, update the end of the last merged interval.
                    curr[1] = intervals[i][1];
                }
            }
        }
        // Convert the ArrayList of merged intervals back into a 2D integer array for the return type.
        int[][] ans = new int[arr.size()][2];
        int i=0;
        // Iterate through the ArrayList and copy each merged interval into the result array.
        for(int[] temp : arr)ans[i++] = temp;
        // Return the array of merged intervals.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Sorting:** Clearly articulate why sorting by start time is essential for the greedy strategy to work.
*   **Walk Through an Example:** Use a small example like `[[1,3],[2,6],[8,10],[15,18]]` to demonstrate how your algorithm processes intervals and merges them.
*   **Discuss Edge Cases:** Mention handling empty input and single-interval inputs.
*   **Clarify Output Format:** Ensure you understand the expected output format (a 2D array) and how you're converting your intermediate data structure (like an `ArrayList`) to it.

## Revision Checklist
- [ ] Understand the problem: merging overlapping intervals.
- [ ] Implement sorting by start time.
- [ ] Correctly identify overlap conditions.
- [ ] Implement the merging logic (updating the end of the last interval).
- [ ] Handle non-overlapping intervals by adding them as new.
- [ ] Convert the result to the required 2D array format.
- [ ] Consider edge cases (empty input).

## Similar Problems
*   Insert Interval
*   Non-overlapping Intervals
*   Meeting Rooms
*   Meeting Rooms II

## Tags
`Array` `Sort`
