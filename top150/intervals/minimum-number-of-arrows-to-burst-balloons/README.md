# Minimum Number Of Arrows To Burst Balloons

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Greedy` `Sorting`  
**Time:** O(N log N)  
**Space:** O(1)

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
                start = Math.max(s,start); // max isliye kyonki aage waale bhi to dekhne hai na ki vo isme shamil ho paayenge yaa nahi
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
This problem asks for the minimum number of arrows to burst all balloons, where an arrow shot at x can burst balloons whose x-coordinates are within their [x_start, x_end] range.
We solve this by sorting balloons by their start points and greedily merging overlapping intervals to determine the minimum number of arrows needed.

## Intuition
The core idea is that if we shoot an arrow, we want it to burst as many balloons as possible. This suggests a greedy approach. If we sort the balloons by their starting x-coordinate, we can iterate through them. When we encounter a balloon, we can try to see if it overlaps with the current "bursting region" defined by the previous balloons. If it overlaps, we extend the bursting region to encompass this new balloon. If it doesn't overlap, it means we need a new arrow for this balloon and any subsequent overlapping balloons. The key insight is that the optimal strategy is to always shoot an arrow at the *earliest possible end point* of an overlapping group of balloons. Sorting by start point and then tracking the minimum end point of the current overlapping group allows us to achieve this.

## Algorithm
1. Sort the `points` array based on the starting x-coordinate (`points[i][0]`) in ascending order. If start points are equal, the order doesn't strictly matter for this greedy approach, but sorting by end point as a secondary criterion can sometimes simplify reasoning.
2. Initialize `arrowCount` to 1 (since at least one arrow is needed if there are any balloons).
3. Initialize `currentEnd` to the ending x-coordinate of the first balloon (`points[0][1]`). This represents the furthest reach of the current arrow.
4. Iterate through the sorted `points` array starting from the second balloon (index 1).
5. For each balloon `points[i]`:
    a. If the current balloon's start point (`points[i][0]`) is less than or equal to `currentEnd`, it means this balloon overlaps with the current arrow's reach. We update `currentEnd` to be the minimum of `currentEnd` and the current balloon's end point (`points[i][1]`). This is because the arrow must cover *all* balloons in the current group, so its effective reach is limited by the earliest end point among them.
    b. If the current balloon's start point (`points[i][0]`) is greater than `currentEnd`, it means this balloon does not overlap with the current arrow's reach. We need a new arrow. Increment `arrowCount` and update `currentEnd` to the end point of the current balloon (`points[i][1]`).
6. Return `arrowCount`.

## Concept to Remember
*   **Greedy Algorithms:** Making locally optimal choices at each step to achieve a globally optimal solution.
*   **Interval Merging/Scheduling:** Problems involving overlapping intervals often benefit from sorting and a greedy approach.
*   **Sorting:** Essential for organizing data to apply greedy strategies effectively.
*   **Edge Cases:** Handling empty input or single balloon scenarios.

## Common Mistakes
*   **Integer Overflow:** Using subtraction for comparison in `Arrays.sort` when dealing with potentially large integer differences (e.g., `b[0] - a[0]`). `Integer.compare(a[0], b[0])` is safer.
*   **Incorrect Overlap Condition:** Misunderstanding how to check for overlap between intervals. An overlap exists if `balloon_i.start <= current_arrow_end`.
*   **Incorrect `currentEnd` Update:** When an overlap occurs, `currentEnd` should be updated to the *minimum* of the current `currentEnd` and the new balloon's end. This ensures the arrow can burst all balloons in the group.
*   **Off-by-One Errors:** Incorrectly initializing `arrowCount` or loop bounds.
*   **Not Sorting:** Applying the greedy logic without sorting the balloons first.

## Complexity Analysis
*   **Time:** O(N log N) - due to the sorting step, where N is the number of balloons. The iteration through the sorted balloons takes O(N) time.
*   **Space:** O(1) - if the sorting is done in-place or if we don't consider the space used by the sorting algorithm itself (which can be O(log N) or O(N) depending on implementation). The variables used take constant extra space.

## Commented Code
```java
import java.util.Arrays; // Import the Arrays class for sorting functionality.

class Solution {
    public int findMinArrowShots(int[][] points) {
        // This is the main function that calls the helper method.
        return intersect(points);
    }

    public int intersect(int[][] points) {
        // Handle the edge case where there are no balloons.
        if (points == null || points.length == 0) {
            return 0; // No arrows needed if there are no balloons.
        }

        // Sort the balloons based on their starting x-coordinate.
        // Using Integer.compare is safer than subtraction to avoid potential integer overflow.
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        // Get the total number of balloons.
        int n = points.length;

        // Initialize the number of arrows needed to 1. We'll need at least one arrow if there are balloons.
        int arrowCount = 1;

        // Initialize the end point of the current arrow's reach.
        // This is initially the end point of the first balloon after sorting.
        int currentEnd = points[0][1];

        // Iterate through the balloons starting from the second one.
        for (int i = 1; i < n; i++) {
            // Get the current balloon's start and end points.
            int currentBalloonStart = points[i][0];
            int currentBalloonEnd = points[i][1];

            // Check if the current balloon overlaps with the current arrow's reach.
            // Overlap occurs if the current balloon starts before or at the point where the current arrow ends.
            if (currentBalloonStart <= currentEnd) {
                // If there's an overlap, we need to update the current arrow's reach.
                // The new reach must be the minimum of the current reach and the current balloon's end.
                // This is because the arrow must burst ALL balloons in the current overlapping group,
                // so its effective end point is determined by the earliest end point among them.
                currentEnd = Math.min(currentEnd, currentBalloonEnd);
            } else {
                // If there's no overlap, it means the current arrow cannot burst this balloon.
                // We need a new arrow for this balloon and any subsequent overlapping balloons.
                arrowCount++; // Increment the arrow count.
                // Update the current arrow's reach to the end point of the current balloon.
                currentEnd = currentBalloonEnd;
            }
        }

        // Return the total minimum number of arrows required.
        return arrowCount;
    }
}
```

## Interview Tips
1.  **Explain the Greedy Choice:** Clearly articulate *why* sorting by start point and then extending the current arrow's reach (or starting a new one) is optimal. Emphasize that you want to maximize the balloons burst by each arrow.
2.  **Handle Edge Cases:** Be prepared to discuss what happens if the input `points` array is empty or `null`.
3.  **Integer Overflow:** Mention the potential for integer overflow if using `b[0] - a[0]` for sorting and explain why `Integer.compare` is preferred.
4.  **Clarify `currentEnd` Update:** When an overlap occurs, explicitly state that `currentEnd` is updated to `Math.min(currentEnd, points[i][1])` and explain *why* this is crucial for covering all balloons in the group.

## Revision Checklist
- [ ] Understand the problem: minimum arrows to burst balloons.
- [ ] Identify the greedy strategy: sort by start, merge overlapping intervals.
- [ ] Implement sorting correctly (handling potential overflow).
- [ ] Initialize `arrowCount` and `currentEnd` properly.
- [ ] Correctly check for overlap (`currentBalloonStart <= currentEnd`).
- [ ] Correctly update `currentEnd` on overlap (`Math.min`).
- [ ] Correctly increment `arrowCount` and update `currentEnd` when no overlap.
- [ ] Handle edge cases (empty input).
- [ ] Analyze time and space complexity.

## Similar Problems
*   Merge Intervals
*   Non-overlapping Intervals
*   Meeting Rooms II
*   Partition Array Into Disjoint Intervals

## Tags
`Array` `Greedy` `Sorting` `Intervals`
