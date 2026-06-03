# Minimum Number Of Arrows To Burst Balloons

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(log N)

---

## Solution (java)

```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        return intersect(points);
    }
    public int intersect(int[][] points) {
        Arrays.sort(points,(a,b)->Integer.compare(a[0],b[0]));//substract krenge to integer overflow aajayega na
        int n = points.length;
        int j = 0;
        int start = points[0][0];
        int end = points[0][1];
        int arrow = 1;
        for(int i=1;i<n;i++){
            int[] next = points[i];
            int s = next[0];
            int e = next[1];
            if(s <= end){ //intersection
                start = Math.max(s,start); // max isliye kyonki aage waal
                end = Math.min(e,end);
            } else{
                arrow++;
                start = s;
                end = e;
            }
        }
        return arrow;
    }
}
// . .  .  . ( ) . . 
// . . . (      ) .  .
// (    ) . . (  ) . .
// . (        ) . . . .
// . . . .( ) . . . . .

// . .  .  . . . 
// . . .  .  .
// (    ) (       ). . . .
//          . . . .
// . . . . . . . . .
   
```

---

---
## Quick Revision
This problem asks for the minimum number of arrows needed to burst all balloons, where an arrow shot at x bursts balloons whose x-coordinates are within their [x_start, x_end].
We solve this by sorting the balloons by their start points and greedily determining overlapping intervals.

## Intuition
The core idea is that if we can burst multiple balloons with a single arrow, we should. This suggests a greedy approach. If we sort the balloons by their starting x-coordinate, we can iterate through them and try to find the maximum overlap. When we encounter a balloon that *doesn't* overlap with the current "bursting region" defined by our arrow, we must use a new arrow. The key insight is that to maximize the chance of overlapping with future balloons, the arrow should be shot at the *earliest possible ending point* of the current overlapping group. Sorting by start points and then maintaining the minimum end point of the current group allows us to efficiently check for overlaps.

## Algorithm
1. Sort the `points` array based on the starting x-coordinate (`points[i][0]`) in ascending order. If start points are equal, the order doesn't strictly matter for this greedy approach, but sorting by end point as a secondary criterion can sometimes simplify reasoning.
2. Initialize `arrowCount` to 1 (we'll need at least one arrow if there are any balloons).
3. Initialize `currentEnd` to the ending x-coordinate of the first balloon (`points[0][1]`). This represents the furthest reach of the current arrow.
4. Iterate through the sorted balloons starting from the second balloon (index `i = 1`).
5. For each balloon `points[i]`:
    a. If the current balloon's start point (`points[i][0]`) is less than or equal to `currentEnd`, it means this balloon overlaps with the current arrow's reach. We can potentially burst it with the same arrow. To maximize future overlaps, we should update `currentEnd` to be the minimum of the current `currentEnd` and the current balloon's end point (`points[i][1]`). This ensures our arrow is shot at the earliest possible point that still covers all overlapping balloons so far.
    b. If the current balloon's start point (`points[i][0]`) is greater than `currentEnd`, it means this balloon *cannot* be burst by the current arrow. We need a new arrow. Increment `arrowCount` and update `currentEnd` to the end point of this new balloon (`points[i][1]`).
6. Return `arrowCount`.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Interval Scheduling/Overlapping Intervals:** Problems that involve finding optimal ways to select or manage a set of intervals.
*   **Sorting:** Essential for many greedy algorithms to process elements in a structured order.
*   **Coordinate Compression (Not directly used here, but related):** Useful for problems involving large coordinate ranges.

## Common Mistakes
*   **Incorrect Sorting:** Not sorting the balloons, or sorting them incorrectly (e.g., by end point only, or not handling ties properly if it were a different problem).
*   **Incorrect Overlap Condition:** Using `s < end` instead of `s <= end` for overlap, or `s < currentEnd` instead of `s <= currentEnd`.
*   **Incorrect `currentEnd` Update:** Not updating `currentEnd` to the minimum of the current end and the new balloon's end when there's an overlap, which might lead to an arrow being shot too far and missing subsequent balloons.
*   **Off-by-One Errors:** Mismanaging loop bounds or initial `arrowCount`.
*   **Integer Overflow:** Using subtraction for comparison in `Arrays.sort` with `Integer.compare` is a good practice to avoid overflow if the coordinates were extremely large, though `Integer.compare` handles this correctly.

## Complexity Analysis
*   **Time:** O(N log N) - due to the sorting step, where N is the number of balloons. The iteration is O(N).
*   **Space:** O(log N) or O(N) - depending on the sorting algorithm used by the Java `Arrays.sort` implementation (typically Timsort or MergeSort, which can use O(N) auxiliary space in the worst case, or O(log N) for the recursion stack in some implementations).

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality.

class Solution {
    // Main method to find the minimum number of arrows.
    public int findMinArrowShots(int[][] points) {
        // Delegate the actual logic to the intersect method.
        return intersect(points);
    }

    // Helper method that implements the core greedy logic.
    public int intersect(int[][] points) {
        // Handle the edge case where there are no balloons.
        if (points == null || points.length == 0) {
            return 0; // No arrows needed if there are no balloons.
        }

        // Sort the balloons based on their starting x-coordinate.
        // Using Integer.compare(a[0], b[0]) is safer than (a[0] - b[0]) to prevent integer overflow.
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        // Get the total number of balloons.
        int n = points.length;

        // Initialize the end point of the current arrow's reach.
        // We start with the end point of the first balloon.
        int currentEnd = points[0][1];

        // Initialize the count of arrows needed. We start with 1 arrow for the first balloon.
        int arrowCount = 1;

        // Iterate through the balloons starting from the second one.
        for (int i = 1; i < n; i++) {
            // Get the current balloon's start and end points.
            int currentBalloonStart = points[i][0];
            int currentBalloonEnd = points[i][1];

            // Check if the current balloon overlaps with the current arrow's reach.
            // An overlap occurs if the balloon starts at or before the current arrow's reach ends.
            if (currentBalloonStart <= currentEnd) {
                // If there's an overlap, we can potentially burst this balloon with the same arrow.
                // To maximize the chance of overlapping with future balloons, we must ensure the arrow
                // covers all balloons in the current group. Thus, we update `currentEnd` to be the
                // minimum of the current `currentEnd` and the current balloon's end. This effectively
                // narrows down the "bursting region" to the most restrictive end point.
                currentEnd = Math.min(currentEnd, currentBalloonEnd);
            } else {
                // If the current balloon does not overlap with the current arrow's reach,
                // we need a new arrow to burst this balloon.
                arrowCount++; // Increment the arrow count.
                // Update `currentEnd` to the end point of this new balloon, as this is the
                // furthest reach of the new arrow for this new group of balloons.
                currentEnd = currentBalloonEnd;
            }
        }

        // Return the total number of arrows required.
        return arrowCount;
    }
}
```

## Interview Tips
*   **Explain the Greedy Choice:** Clearly articulate *why* sorting by start point and then choosing the minimum end point for the current arrow is the optimal greedy strategy. Emphasize that this maximizes the chance of hitting subsequent balloons.
*   **Handle Edge Cases:** Be prepared to discuss what happens if the input array is empty or null.
*   **Walk Through an Example:** Use a small, representative example (e.g., `[[10,16],[2,8],[1,6],[7,12]]`) to trace your algorithm step-by-step, showing how `currentEnd` and `arrowCount` change.
*   **Discuss Sorting Stability:** While not strictly necessary for correctness here, briefly mentioning that sorting by start point is sufficient and that secondary sorting criteria (like end point) don't change the outcome for this specific greedy approach can show deeper understanding.

## Revision Checklist
- [ ] Understand the problem statement and constraints.
- [ ] Identify the greedy nature of the problem.
- [ ] Implement sorting by the start coordinate.
- [ ] Correctly manage the `currentEnd` variable to track the furthest reach of the current arrow.
- [ ] Implement the overlap condition (`currentBalloonStart <= currentEnd`).
- [ ] Correctly update `currentEnd` to `min(currentEnd, currentBalloonEnd)` when overlapping.
- [ ] Increment `arrowCount` and reset `currentEnd` when a new arrow is needed.
- [ ] Handle the edge case of an empty input array.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals
*   Non-overlapping Intervals
*   Meeting Rooms II
*   Partition Array Into Disjoint Intervals

## Tags
`Array` `Greedy` `Sorting`
