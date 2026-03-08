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
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int j = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[j][1] >= intervals[i][0]) {
                intervals[j][1] = Math.max(intervals[j][1], intervals[i][1]);
            } else {
                j++;
                intervals[j] = intervals[i];
            }
        }
        return Arrays.copyOfRange(intervals, 0, j + 1);
    }
}
```

---

---
## Problem Summary
Given a collection of intervals, merge all overlapping intervals.

## Approach and Intuition
The core idea is to first sort the intervals based on their start times. This is crucial because it allows us to process intervals in a sequential manner. Once sorted, we can iterate through the intervals and maintain a "current merged interval."

We start with the first interval as our initial current merged interval. Then, for each subsequent interval:
1. If the current interval overlaps with the current merged interval (i.e., the current interval's start time is less than or equal to the current merged interval's end time), we merge them by updating the end time of the current merged interval to be the maximum of its current end time and the current interval's end time.
2. If there's no overlap, it means the current merged interval is complete. We then move to the next interval and make it the new current merged interval.

The provided solution cleverly uses the input array itself to store the merged intervals in place. It uses an index `j` to track the end of the merged portion of the array. When an overlap occurs, it updates `intervals[j][1]`. When no overlap occurs, it increments `j` and copies the non-overlapping interval to `intervals[j]`. Finally, it returns a sub-array containing only the merged intervals.

## Complexity Analysis
- Time: O(N log N) - reason: The dominant factor is sorting the intervals, which takes O(N log N) time, where N is the number of intervals. The subsequent iteration through the sorted intervals takes O(N) time.
- Space: O(1) - reason: The solution modifies the input array in-place and uses a few variables for indices and temporary storage. The space used by the sorting algorithm itself might be O(log N) or O(N) depending on the implementation, but if we consider the auxiliary space used by the algorithm *beyond* the input, it's O(1). If the sorting algorithm requires O(N) space (e.g., merge sort), then the overall space complexity would be O(N). However, Java's `Arrays.sort` for primitive arrays typically uses quicksort (average O(log N) stack space) or dual-pivot quicksort. For object arrays, it uses Timsort (O(N) worst-case space). Assuming the intervals are treated as objects, it could be O(N). But often, for interview purposes, in-place modification is considered O(1) auxiliary space.

## Code Walkthrough
1. `Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));`
   - Sorts the input `intervals` array. The custom comparator `(a, b) -> Integer.compare(a[0], b[0])` ensures sorting is done based on the start time (the first element `[0]`) of each interval.

2. `int j = 0;`
   - Initializes an index `j`. This index will track the end of the merged intervals within the `intervals` array itself. `intervals[0]` to `intervals[j]` will represent the merged intervals found so far.

3. `for (int i = 1; i < intervals.length; i++) { ... }`
   - Iterates through the sorted intervals starting from the second interval (`i = 1`).

4. `if (intervals[j][1] >= intervals[i][0]) { ... }`
   - Checks for overlap between the current merged interval (ending at `intervals[j]`) and the interval being considered (`intervals[i]`).
   - Overlap condition: The end of the current merged interval (`intervals[j][1]`) is greater than or equal to the start of the next interval (`intervals[i][0]`).

5. `intervals[j][1] = Math.max(intervals[j][1], intervals[i][1]);`
   - If there's an overlap, merge the intervals. The new end of the merged interval is the maximum of the current merged interval's end and the next interval's end. This effectively extends the current merged interval.

6. `else { ... }`
   - If there is no overlap (`intervals[j][1] < intervals[i][0]`).

7. `j++;`
   - Increment `j` to move to the next position in the `intervals` array, signifying the start of a new merged interval.

8. `intervals[j] = intervals[i];`
   - Copy the current non-overlapping interval (`intervals[i]`) to the new position `intervals[j]`. This interval becomes the start of the next potential merged interval.

9. `return Arrays.copyOfRange(intervals, 0, j + 1);`
   - After the loop finishes, `j` points to the last merged interval. The merged intervals are stored from index `0` to `j` (inclusive) in the `intervals` array.
   - `Arrays.copyOfRange(intervals, 0, j + 1)` creates a new array containing only these merged intervals and returns it. The `j + 1` is because the end index in `copyOfRange` is exclusive.

## Interview Tips
- **Clarify Edge Cases:** Ask about empty input, single interval, intervals that are already sorted, intervals that are completely contained within others.
- **Explain Sorting:** Emphasize why sorting by start time is crucial for the greedy approach.
- **In-place Modification:** Discuss the trade-offs of modifying the input array versus creating a new one. The provided solution is efficient in space by doing it in-place.
- **Greedy Approach:** Frame this as a classic greedy problem where making the locally optimal choice (merging overlapping intervals) leads to a globally optimal solution.
- **Data Structure Choice:** While not strictly necessary here, for similar problems, consider if a stack or priority queue might be useful.
- **Walkthrough:** Be prepared to walk through an example manually on a whiteboard or in the editor.

## Optimization and Alternatives
- **Using a List:** Instead of modifying the input array in-place, one could use an `ArrayList<int[]>` to store the merged intervals. This might be slightly cleaner conceptually but could incur O(N) space for the new list.
- **No Sorting (if intervals are already sorted):** If the problem statement guaranteed sorted intervals, the time complexity would reduce to O(N).
- **Alternative Sorting Keys:** While sorting by start time is standard, consider if sorting by end time could also work (it's more complex and less intuitive for this specific problem).

## Revision Checklist
- [ ] Understand the problem statement thoroughly.
- [ ] Identify the core challenge: handling overlapping intervals.
- [ ] Realize the importance of sorting.
- [ ] Develop a greedy strategy: process intervals sequentially after sorting.
- [ ] Implement the merging logic correctly (checking overlap and updating end times).
- [ ] Handle the case where a new non-overlapping interval starts a new merged segment.
- [ ] Ensure the final output format is correct.
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
- Insert Interval
- Non-overlapping Intervals
- Meeting Rooms
- Employee Free Time

## Tags
`Array` `Sorting` `Greedy`

## My Notes
Merge intervals shortest solution
