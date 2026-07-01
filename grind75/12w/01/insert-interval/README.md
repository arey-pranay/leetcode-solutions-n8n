# Insert Interval

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array`  
**Time:** O(n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        ArrayList<int[]> ans = new ArrayList<>();
        int i=0;
        while(i<n && intervals[i][1] < newInterval[0]) ans.add(intervals[i++]);
        while(i<n && intervals[i][0] <= newInterval[1]){
            newInterval[0] = Math.min(intervals[i][0],newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        ans.add(newInterval);
        while(i<n) ans.add(intervals[i++]);
        i=0;
        int[][] res = new int[ans.size()][2];
        for(int[] temp : ans) res[i++] = temp;
        return res;
    }
}
//newInterval ko sabse bada bana rhe hai , aur intervals ko chhota banate jaa rhe hai 
```

---

---

## Quick Revision
Insert new interval into a list of existing intervals while maintaining sorted order.
Solution involves merging overlapping intervals and adding the new interval.

## Intuition
The key insight here is to recognize that we can merge overlapping intervals as we iterate through them. By keeping track of the minimum start time and maximum end time, we can efficiently insert the new interval without having to compare each existing interval with every other one.

## Algorithm
1. Initialize an empty list `ans` to store the merged intervals.
2. Iterate through the input intervals:
	* If the current interval's end time is less than the new interval's start time, add it to `ans`.
	* If the current interval's start time is greater than or equal to the new interval's start time and its end time is less than or equal to the new interval's end time, merge the two intervals by updating the new interval's start and end times.
3. Add the new interval to `ans`.
4. Add any remaining intervals from the input list to `ans`.
5. Convert `ans` back into a 2D array.

## Concept to Remember
* **Interval merging**: Overlapping intervals can be merged by updating the minimum start time and maximum end time.
* **Sorted order**: The input intervals are already sorted, which allows us to efficiently merge overlapping intervals.
* **Time complexity**: The algorithm's time complexity is O(n), where n is the number of input intervals.

## Common Mistakes
* **Not initializing the `ans` list correctly**: Failing to initialize `ans` as an empty list can lead to incorrect results.
* **Incorrectly merging intervals**: Not updating the new interval's start and end times when merging with existing intervals can result in incorrect merged intervals.
* **Missing edge cases**: Forgetting to handle edge cases, such as an empty input list or a single-element input list, can cause issues.

## Complexity Analysis
- Time: O(n) - The algorithm iterates through the input intervals once.
- Space: O(n) - In the worst case, we need to store all input intervals in the `ans` list.

## Commented Code
```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // Initialize an empty list to store merged intervals
        ArrayList<int[]> ans = new ArrayList<>();

        // Iterate through input intervals
        for (int i = 0; i < intervals.length; i++) {
            // If current interval's end time is less than new interval's start time, add it to ans
            if (intervals[i][1] < newInterval[0]) {
                ans.add(intervals[i]);
            }
            // If current interval overlaps with new interval, merge them by updating new interval's start and end times
            else if (intervals[i][0] <= newInterval[1]) {
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }

        // Add new interval to ans
        ans.add(newInterval);

        // Add any remaining input intervals to ans
        for (int i = 0; i < intervals.length; i++) {
            if (!overlaps(intervals[i], newInterval)) {
                ans.add(intervals[i]);
            }
        }

        // Convert ans back into a 2D array
        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;
    }

    // Helper function to check if two intervals overlap
    private boolean overlaps(int[] a, int[] b) {
        return a[0] <= b[1] && b[0] <= a[1];
    }
}
```

## Interview Tips

* **Focus on edge cases**: Make sure to handle edge cases, such as an empty input list or a single-element input list.
* **Use efficient merging**: Take advantage of the fact that intervals are already sorted to efficiently merge overlapping intervals.
* **Pay attention to time and space complexity**: Ensure your solution meets the required time and space complexity constraints.

## Revision Checklist
- [ ] Review edge cases, such as an empty input list or a single-element input list.
- [ ] Verify that the algorithm correctly merges overlapping intervals.
- [ ] Confirm that the solution meets the required time and space complexity constraints.

## Similar Problems

* **Merge Intervals**: Merge all overlapping intervals in a given list of intervals.
* **Insert Interval II**: Insert new interval into a sorted list of existing intervals, allowing for duplicate intervals.
* **Split Array Sorted Order**: Split an array of integers into two subarrays such that each subarray is sorted in ascending order.

## Tags
`Array`, `Hash Map`, `Interval Merging`
