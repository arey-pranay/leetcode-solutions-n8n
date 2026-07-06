# Remove Covered Intervals

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Sorting`  
**Time:** O(N log N)  
**Space:** O(log N)

---

## Solution (java)

```java
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b)-> a[0] == b[0] ? b[1]-a[1] :a[0]-b[0]);
        int[] curr = intervals[0];
        int n = intervals.length;
        int ans = n;
        for(int i=1;i<n;i++) if(curr[0] <= intervals[i][0] && intervals[i][1] <= curr[1]) ans--;else curr = intervals[i];
        return ans;
    }
}
```

---

---
## Quick Revision
Given a list of intervals, remove any interval that is completely covered by another interval.
Sort intervals by start point, then by end point in descending order, and iterate to count non-covered intervals.

## Intuition
The core idea is to efficiently identify and discard intervals that are "subsumed" by others. If we sort the intervals, we can process them in a way that makes this comparison straightforward. Sorting by the start point is natural. However, if two intervals start at the same point, say `[1, 5]` and `[1, 3]`, the longer one `[1, 5]` can potentially cover the shorter one `[1, 3]`. To ensure we consider the covering interval first, we should sort intervals with the same start point by their end points in *descending* order. Once sorted, we can maintain a "current" interval that represents the largest non-covered interval encountered so far. Any subsequent interval that starts at or after the current interval's start and ends at or before the current interval's end is covered.

## Algorithm
1. Sort the input `intervals` array. The primary sorting key is the start point in ascending order. The secondary sorting key is the end point in descending order. This ensures that if two intervals have the same start point, the one with the larger end point comes first.
2. Initialize `ans` (the count of non-covered intervals) to the total number of intervals (`n`).
3. Initialize `curr` to the first interval in the sorted array. This `curr` interval will represent the "largest" non-covered interval found so far.
4. Iterate through the sorted `intervals` array starting from the second interval (index 1).
5. For each `intervals[i]`:
    a. Check if `intervals[i]` is covered by `curr`. An interval `[a, b]` is covered by `[c, d]` if `c <= a` and `b <= d`. In our sorted context, `curr[0] <= intervals[i][0]` is always true due to sorting. So, we only need to check if `intervals[i][1] <= curr[1]`.
    b. If `intervals[i]` is covered by `curr` (i.e., `intervals[i][1] <= curr[1]`), decrement `ans` because this interval is covered.
    c. If `intervals[i]` is *not* covered by `curr`, it means `intervals[i]` starts a new potential non-covered interval. Update `curr` to `intervals[i]`.
6. Return `ans`.

## Concept to Remember
*   **Greedy Approach:** Making locally optimal choices (processing intervals in a specific order and maintaining the current largest non-covered interval) leads to a globally optimal solution.
*   **Sorting Strategy:** The custom sorting criteria (start ascending, end descending) is crucial for the greedy approach to work correctly.
*   **Interval Management:** Efficiently comparing and identifying overlapping or contained intervals.

## Common Mistakes
*   **Incorrect Sorting:** Not sorting by end points in descending order for intervals with the same start point can lead to missing covered intervals.
*   **Off-by-One Errors:** Incorrectly handling the loop bounds or the initial `ans` value.
*   **Misunderstanding "Covered":** Confusing "covered" with "overlapping." An interval `[a, b]` is covered by `[c, d]` if `c <= a` and `b <= d`.
*   **Not updating `curr` correctly:** Failing to update `curr` when a new, non-covered interval is encountered.

## Complexity Analysis
- Time: O(N log N) - due to the sorting step, where N is the number of intervals. The subsequent iteration is O(N).
- Space: O(log N) or O(N) - depending on the sorting algorithm's implementation (e.g., `Arrays.sort` in Java uses Timsort, which can take O(N) space in the worst case for auxiliary storage, or O(log N) for recursion stack if it's a quicksort variant).

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality.

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort the intervals.
        // The custom comparator sorts primarily by the start point in ascending order (a[0] - b[0]).
        // If start points are equal, it sorts by the end point in descending order (b[1] - a[1]).
        // This ensures that longer intervals starting at the same point come first.
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        // Initialize 'curr' to the first interval. This represents the current largest non-covered interval found so far.
        int[] curr = intervals[0];
        // Get the total number of intervals.
        int n = intervals.length;
        // Initialize 'ans' to the total number of intervals. We will decrement this count for each covered interval.
        int ans = n;

        // Iterate through the sorted intervals starting from the second interval (index 1).
        for (int i = 1; i < n; i++) {
            // Check if the current interval intervals[i] is covered by 'curr'.
            // An interval [a, b] is covered by [c, d] if c <= a and b <= d.
            // Since we sorted by start point ascending, curr[0] <= intervals[i][0] is always true.
            // So, we only need to check if the end point of intervals[i] is less than or equal to the end point of 'curr'.
            if (curr[0] <= intervals[i][0] && intervals[i][1] <= curr[1]) {
                // If intervals[i] is covered by 'curr', decrement the answer count.
                ans--;
            } else {
                // If intervals[i] is NOT covered by 'curr', it means intervals[i] starts a new potential non-covered interval.
                // Update 'curr' to be this new interval, as it's now the largest non-covered interval encountered.
                curr = intervals[i];
            }
        }
        // Return the final count of non-covered intervals.
        return ans;
    }
}
```

## Interview Tips
*   **Explain the Sorting Rationale:** Clearly articulate *why* the specific sorting order (start ascending, end descending) is necessary for the greedy approach to work.
*   **Walk Through an Example:** Use a small example like `[[1,4],[3,6],[2,8]]` or `[[1,2],[1,4],[3,4]]` to demonstrate how the algorithm processes intervals and updates `curr` and `ans`.
*   **Edge Cases:** Discuss edge cases like empty input, single interval, all intervals covered, or no intervals covered.
*   **Clarify "Covered":** Ensure you understand and can explain the definition of a "covered" interval precisely.

## Revision Checklist
- [ ] Understand the problem statement: identify covered intervals.
- [ ] Devise a sorting strategy: start ascending, end descending.
- [ ] Implement the greedy iteration: maintain `curr` and update `ans`.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the logic and sorting.

## Similar Problems
*   Merge Intervals
*   Non-overlapping Intervals
*   Interval List Intersections
*   Meeting Rooms II

## Tags
`Array` `Sort` `Greedy`
