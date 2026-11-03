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
Find the minimum number of taps to water a garden of length `n` given the range each tap can cover.
Transform the problem into interval scheduling (Jump Game II variation) by converting taps to intervals, sorting them by start point, and greedily picking the one with the maximum reach.

## Intuition
The "aha moment" is realizing that each tap can be modeled as a closed interval `[max(0, i - range), min(n, i + range)]` representing the left and right bounds it can water. Once we transform the taps into intervals, the problem becomes identical to the classic "Jump Game II" or "Video Stitching" problem. We want to cover the entire range `[0, n]` using the minimum number of overlapping intervals. By sorting the intervals based on their start points, we can greedily iterate through all intervals that can start within our currently covered region (`done`) and extend our coverage as far as possible (`maxReach`).

## Algorithm
1. Initialize an array of intervals of size `n + 1` where each index `i` stores the left and right bounds of the `i`-th tap, clamped between `0` and `n`.
2. Sort the intervals array in ascending order based on their starting positions (`a[0]`).
3. Initialize three variables: `done` (current covered right bound, starting at `0`), `taps` (count of taps used, starting at `0`), and `i` (pointer to iterate through the sorted intervals).
4. Loop while `done < n`:
   - Initialize `maxReach` to the current `done` value.
   - While `i` is within bounds and the start of the current interval is less than or equal to `done`:
     - Update `maxReach` to be the maximum of `maxReach` and the end of the current interval (`intervals[i][1]`).
     - Increment `i`.
   - If `maxReach` equals `done`, it means no interval can extend our coverage, so return `-1` (impossible to cover).
   - Otherwise, update `done` to `maxReach` and increment `taps` by `1`.
5. Return the total `taps` count once `done >= n`.

## Concept to Remember
- **Interval Transformation:** Converting position-based data into interval representations to unlock standard greedy patterns.
- **Greedy Choice Property:** At any point, among all available intervals that can bridge the current gap, pick the one that stretches the furthest.
- **Jump Game Paradigm:** Recognizing that interval covering problems often share the exact same state machine as finding minimum jumps in an array.

## Common Mistakes
- Failing to clamp the interval boundaries to `[0, n]`, leading to out-of-bounds array indices or incorrect range logic.
- Forgetting to sort the intervals by their start points, which breaks the sequential greedy assumption.
- Not handling the impossible case where a gap cannot be bridged, resulting in an infinite loop if `maxReach == done` is not checked.
- Mismanaging the pointer `i` inside the inner loop, causing skipped intervals or redundant checks.

## Complexity Analysis
- Time: $O(n \log n)$ - Sorting the `n + 1` intervals dominates the time complexity, while the while-loops process each interval at most once in linear time.
- Space: $O(n)$ - Creating the auxiliary `intervals` matrix of size `(n + 1) x 2` requires linear extra space.

## Commented Code
```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        // Create a 2D array to store the intervals [start, end] for each of the n+1 taps
        int[][] intervals = new int[n+1][2];
        
        // Populate the interval boundaries for every tap index i
        for(int i = 0; i <= n; i++){
            // Left bound cannot be less than 0, prevent out of bounds
            intervals[i][0] = Math.max(0, i - ranges[i]); 
            // Right bound cannot exceed the garden length n
            intervals[i][1] = Math.min(n, i + ranges[i]);
        }
        
        // Sort the intervals in ascending order based on their starting positions
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // Initialize variables: 'done' tracks current covered distance, 'taps' counts used taps, 'i' is the interval pointer
        int done = 0, taps = 0, i = 0;
        
        // Continue until the entire garden length 'n' is fully covered
        while(done < n){
            int maxReach = done;
            
            // Greedily find all taps that can start within the currently covered range ('done')
            // and update 'maxReach' with the furthest endpoint they can reach
            while(i <= n && intervals[i][0] <= done) {
                maxReach = Math.max(maxReach, intervals[i][1]);
                i++; // Move to the next interval
            }
            
            // If the reach hasn't extended, a gap exists and the garden cannot be fully watered
            if(maxReach == done) return -1;
            
            // Expand the covered region and increment the tap count
            done = maxReach;
            taps++;
        }
        
        // Return the minimum number of taps required to cover the garden
        return taps;
    }
}
```

## Interview Tips
- Clarify the input constraints: What if `n` is small or `ranges` contains zeros?
- State the relationship to interval scheduling early to demonstrate architectural pattern recognition.
- Walk through a dry run with a small test case (like `n = 3, ranges = [3, 0, 1, 1]`) to show how `maxReach` and `done` interact.
- Discuss alternative approaches like Dynamic Programming, then explain why the Greedy approach is optimal in both time and space.

## Revision Checklist
- [ ] Understand how to map tap positions to closed intervals `[start, end]`.
- [ ] Remember to clamp interval ranges using `Math.max(0, ...)` and `Math.min(n, ...)`.
- [ ] Recall why sorting by start time is necessary for the greedy search.
- [ ] Implement the `maxReach == done` break condition to avoid infinite loops.
- [ ] Trace the `done` variable progression up to `n`.

## Similar Problems
- `Jump Game II`
- `Video Stitching`
- `Merge Intervals`
- `Removing Covered Intervals`

## Tags
`Array` `Greedy` `Sorting` `Dynamic Programming`
