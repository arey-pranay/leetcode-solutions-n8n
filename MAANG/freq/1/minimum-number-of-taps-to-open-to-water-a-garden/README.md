# Minimum Number Of Taps To Open To Water A Garden

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Array` `Dynamic Programming` `Greedy`  
**Time:** O(n \log n)  
**Space:** O(n)

---

## Solution (java)

```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[][] intervals = new int[n+1][2];
        for(int i=0;i<=n;i++){
            intervals[i][0] = Math.max(0,i-ranges[i]); // to prevent out of bounds
            intervals[i][1] = Math.min(n,i+ranges[i]);
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int done=0, taps=0,i=0;
        while(done < n){
            int maxReach = done;
            while(i<=n && intervals[i][0] <= done) maxReach = Math.max(maxReach, intervals[i++][1]); //eligible taps, jahan tk ki range humne cover kr li hai.
            if(maxReach == done) return -1;
            
            done = maxReach;
            taps++;
        }
        return taps;
    }
}
// 6
// 0-> -3,+3
// 1-> -3,5  => ans
// 2-> 1,3
// 3-> 2,4
// 4-> 4
// 5-> 5
```

---

---
## Quick Revision
Find the minimum number of taps to water a garden of length `n` given their individual watering ranges.
Transform the tap ranges into intervals, sort them, and use a greedy approach to jump as far as possible at each step.

## Intuition
The "aha moment" occurs when you realize this problem is identical to the classic "Jump Game II" or "Video Stitching" interval problem. Instead of thinking about selecting individual taps randomly, we can convert each tap $i$ with its range into a coverage interval `[max(0, i - range[i]), min(n, i + range[i])]`. Once we have these intervals, we want to cover the entire range from `0` to `n` with the minimum number of intervals. By sorting intervals by their start points, we can greedily look at all taps that can start within our currently covered region (`done`), and from those, pick the one that extends our coverage the furthest (`maxReach`).

## Algorithm
1. Create an interval array of size `n + 1` where each entry `i` stores the start and end coordinates `[Math.max(0, i - ranges[i]), Math.min(n, i + ranges[i])]` for each tap.
2. Sort the intervals array based on the start coordinates in ascending order.
3. Initialize `done = 0` (current covered boundary), `taps = 0` (count of taps used), and `i = 0` (pointer for intervals).
4. Loop while `done < n`:
   - Set `maxReach = done`.
   - Iterate through the intervals while the interval's start point is less than or equal to `done`. Update `maxReach` to be the maximum of `maxReach` and the current interval's end point, then increment the interval pointer `i`.
   - If after checking the eligible intervals, `maxReach == done`, it means we cannot extend our coverage further, so return `-1`.
   - Otherwise, update `done = maxReach` and increment `taps`.
5. Return the total `taps` count once `done >= n`.

## Concept to Remember
- Greedy algorithms and interval scheduling.
- Transforming a seemingly complex domain problem (gardening/ranges) into a standard algorithmic pattern (Jump Game / Interval Coverage).
- Sorting intervals by start time to process them sequentially.

## Common Mistakes
- Forgetting to clamp interval boundaries to `0` and `n`, leading to out-of-bound issues or invalid range logic.
- Failing to return `-1` when a gap exists in the coverage, causing an infinite loop.
- Incorrectly advancing the interval pointer `i`, which can cause valid intervals to be skipped or re-evaluated incorrectly.
- Confusing sorting by start time with sorting by end time.

## Complexity Analysis
- Time: $O(n \log n)$ - Sorting the `n + 1` intervals dominates the time complexity, followed by a linear scan of the intervals in the `while` loops.
- Space: $O(n)$ - Creating the `intervals` array of size `n + 1` to store the transformed ranges.

## Commented Code
```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        // Create a 2D array to store the start and end intervals for each of the n + 1 taps
        int[][] intervals = new int[n+1][2];
        // Loop through each tap to calculate its effective start and end coordinates
        for(int i=0; i<=n; i++){
            // Start of the interval cannot be less than 0
            intervals[i][0] = Math.max(0, i - ranges[i]); 
            // End of the interval cannot exceed n
            intervals[i][1] = Math.min(n, i + ranges[i]);
        }
        // Sort the intervals based on their start positions in ascending order
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Initialize tracking variables: 'done' is the furthest point covered so far, 'taps' counts used taps, 'i' is the interval pointer
        int done = 0, taps = 0, i = 0;
        // Continue until our covered range reaches or exceeds the end of the garden (n)
        while(done < n){
            int maxReach = done;
            // Greedily find all taps that can start within our currently covered range ('done')
            // and update 'maxReach' to find the one that stretches the furthest to the right
            while(i <= n && intervals[i][0] <= done) {
                maxReach = Math.max(maxReach, intervals[i][1]);
                i++;
            }
            // If we cannot extend our reach further, a gap exists and the garden cannot be watered
            if(maxReach == done) return -1;
            
            // Update the covered range boundary to the new maximum reach
            done = maxReach;
            // Increment the count of taps used
            taps++;
        }
        // Return the minimum number of taps required to cover the garden
        return taps;
    }
}
```

## Interview Tips
- State clearly that you recognize this as an interval/jump game variation within the first few minutes.
- Explain the transformation step explicitly: turning tap capacities into explicit coordinate intervals `[start, end]`.
- Walk through a small test case (like `n = 3, ranges = [3, 0, 1, 1]`) to demonstrate how the greedy pointer advances.

## Revision Checklist
- [ ] Understand interval transformation logic with boundary clamping.
- [ ] Know why sorting by start time is necessary.
- [ ] Trace the inner and outer loop conditions to prevent infinite loops.
- [ ] Handle impossible configurations by returning `-1`.

## Similar Problems
- `Jump Game II`
- `Video Stitching`
- `Merge Intervals`
- `Minimum Number of Arrows to Burst Balloons`

## Tags
`Array` `Greedy` `Sorting` `Dynamic Programming`
