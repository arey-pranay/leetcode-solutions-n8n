# Earliest Finish Time For Land And Water Rides Ii

**Difficulty:** Medium  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Greedy` `Sorting`  
**Time:** O(N + M)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    final static int MAX = 300005;

    public int earliestFinishTime(int[] la, int[] lb, int[] wa, int[] wb) {
        int l = MAX, w = MAX, minL = MAX, minW = MAX;
        int n = la.length, m = wa.length;

        for (int i = 0; i < n; ++i)
            l = Math.min(l, la[i] + lb[i]);

        for (int i = 0; i < m; ++i) {
            w = Math.min(w, wa[i] + wb[i]);
            minL = Math.min(minL, Math.max(wa[i], l) + wb[i]);
        }

        for (int i = 0; i < n; ++i)
            minW = Math.min(minW, Math.max(la[i], w) + lb[i]);

        return Math.min(minW, minL);
    }
}
```

---

---
## Quick Revision
This problem asks for the earliest time to complete two types of rides, land and water, where each ride has a start and end time. We need to consider the minimum time for each type and then find the overall minimum finish time considering potential overlaps.

## Intuition
The core idea is to realize that we can complete all land rides or all water rides independently first. The earliest time to finish all land rides is the minimum of `la[i] + lb[i]` across all land rides. Similarly, for water rides, it's the minimum of `wa[i] + wb[i]`.

However, we might need to switch between land and water. If we finish all land rides at time `l` and then need to start a water ride `i` (which starts at `wa[i]` and takes `wb[i]` time), the earliest we can finish this water ride is `max(wa[i], l) + wb[i]`. This is because we can only start the water ride after it's available (`wa[i]`) AND after we've finished all land rides (`l`). The same logic applies if we finish water rides first and then do land rides. The final answer is the minimum of these two scenarios.

## Algorithm
1. Initialize `l` (earliest finish for all land rides) and `w` (earliest finish for all water rides) to a very large value (e.g., `MAX`).
2. Initialize `minL` (earliest finish if water rides are done after land rides) and `minW` (earliest finish if land rides are done after water rides) to a very large value (e.g., `MAX`).
3. Iterate through all land rides (`la`, `lb`):
    - Update `l = min(l, la[i] + lb[i])`. This finds the minimum time to complete *any single* land ride, assuming it's the last one.
4. Iterate through all water rides (`wa`, `wb`):
    - Update `w = min(w, wa[i] + wb[i])`. This finds the minimum time to complete *any single* water ride, assuming it's the last one.
    - Update `minL = min(minL, max(wa[i], l) + wb[i])`. This calculates the finish time if we do all land rides first (finishing at `l`) and then start water ride `i`. We take the maximum of the water ride's availability (`wa[i]`) and the land rides' finish time (`l`) as the start time for this water ride.
5. Iterate through all land rides (`la`, `lb`) again:
    - Update `minW = min(minW, max(la[i], w) + lb[i])`. This calculates the finish time if we do all water rides first (finishing at `w`) and then start land ride `i`. We take the maximum of the land ride's availability (`la[i]`) and the water rides' finish time (`w`) as the start time for this land ride.
6. Return `min(minL, minW)`. This is the overall earliest finish time.

## Concept to Remember
*   **Greedy Approach:** The problem can be solved by finding the minimum time for independent tasks and then considering the minimum time for sequential tasks.
*   **Minimax Logic:** When switching between land and water, we need to consider the maximum of the previous task's completion time and the current task's availability.
*   **Optimization:** Finding the minimum of a set of values is a common pattern.

## Common Mistakes
*   **Incorrectly calculating `l` and `w`:** Not understanding that `l` and `w` represent the minimum time to finish *all* of one type of ride, not just any single ride. The provided code actually calculates the minimum time to finish *any single* ride, which is a subtle but important distinction for this problem's logic. The problem statement implies we need to finish *all* land rides and *all* water rides. The provided solution's interpretation of `l` and `w` as the minimum time for *any* land/water ride is what makes the `max(wa[i], l)` logic work. If `l` was the time to finish *all* land rides, then `max(wa[i], l)` would be correct. However, the code calculates `l` as `min(la[i] + lb[i])`, which is the earliest finish time for *a single* land ride. This implies the problem is asking for the earliest time *any* land ride can be finished, and *any* water ride can be finished, and then considering combinations.
*   **Forgetting the `max` condition:** Not realizing that a ride can only start after its availability time AND after the previous set of rides is completed.
*   **Not considering both switching orders:** Failing to check both "land then water" and "water then land" scenarios.
*   **Integer Overflow:** Using data types that cannot hold the maximum possible finish times (though `MAX = 300005` seems sufficient here).

## Complexity Analysis
- Time: O(N + M) - reason: We iterate through the land ride arrays twice (once for `l`, once for `minW`) and the water ride arrays twice (once for `w` and `minL`, once for `minL`). N is the length of `la`/`lb`, and M is the length of `wa`/`wb`.
- Space: O(1) - reason: We only use a few constant extra variables to store minimums and intermediate results.

## Commented Code
```java
class Solution {
    // Define a constant for a large initial value, representing infinity.
    final static int MAX = 300005;

    public int earliestFinishTime(int[] la, int[] lb, int[] wa, int[] wb) {
        // Initialize 'l' to MAX. This will store the minimum finish time for any single land ride.
        int l = MAX;
        // Initialize 'w' to MAX. This will store the minimum finish time for any single water ride.
        int w = MAX;
        // Initialize 'minL' to MAX. This will store the minimum finish time if we do all land rides first, then a water ride.
        int minL = MAX;
        // Initialize 'minW' to MAX. This will store the minimum finish time if we do all water rides first, then a land ride.
        int minW = MAX;

        // Get the number of land rides.
        int n = la.length;
        // Get the number of water rides.
        int m = wa.length;

        // First pass: Calculate the minimum finish time for any single land ride.
        for (int i = 0; i < n; ++i)
            // Update 'l' with the minimum of its current value and the finish time of the current land ride (start + duration).
            l = Math.min(l, la[i] + lb[i]);

        // Second pass: Calculate the minimum finish time for any single water ride AND
        // calculate the minimum finish time if we do land rides first then a water ride.
        for (int i = 0; i < m; ++i) {
            // Update 'w' with the minimum of its current value and the finish time of the current water ride (start + duration).
            w = Math.min(w, wa[i] + wb[i]);
            // Calculate the finish time for the scenario: finish all land rides (at time 'l'), then start water ride 'i'.
            // The start time for water ride 'i' is the maximum of its availability time 'wa[i]' and the land rides' finish time 'l'.
            // Add the duration of water ride 'i' ('wb[i]') to get the finish time.
            // Update 'minL' with the minimum of its current value and this calculated finish time.
            minL = Math.min(minL, Math.max(wa[i], l) + wb[i]);
        }

        // Third pass: Calculate the minimum finish time if we do water rides first then a land ride.
        for (int i = 0; i < n; ++i)
            // Calculate the finish time for the scenario: finish all water rides (at time 'w'), then start land ride 'i'.
            // The start time for land ride 'i' is the maximum of its availability time 'la[i]' and the water rides' finish time 'w'.
            // Add the duration of land ride 'i' ('lb[i]') to get the finish time.
            // Update 'minW' with the minimum of its current value and this calculated finish time.
            minW = Math.min(minW, Math.max(la[i], w) + lb[i]);

        // The overall earliest finish time is the minimum of the two scenarios:
        // 1. Finish land rides first, then a water ride ('minL').
        // 2. Finish water rides first, then a land ride ('minW').
        return Math.min(minW, minL);
    }
}
```

## Interview Tips
*   **Clarify the problem statement:** Ensure you understand if `l` and `w` should represent the minimum time to finish *all* rides of a type, or the minimum time to finish *any single* ride of a type. The provided solution interprets it as the latter, which is crucial for the `max(availability, previous_finish_time)` logic.
*   **Walk through an example:** Use a small, concrete example with a few land and water rides to trace the algorithm's execution and verify your understanding.
*   **Explain the `max` function's role:** Clearly articulate why `Math.max(availability, previous_finish_time)` is necessary to correctly model the start time of the second type of ride.
*   **Discuss edge cases:** Consider scenarios with no land rides, no water rides, or rides with overlapping availability times.

## Revision Checklist
- [ ] Understood the problem's goal: finding the earliest time to complete both land and water rides.
- [ ] Identified the two main scenarios: land-then-water and water-then-land.
- [ ] Correctly calculated the minimum finish time for *any single* land ride (`l`) and *any single* water ride (`w`).
- [ ] Implemented the `max(availability, previous_finish_time)` logic for switching ride types.
- [ ] Considered both switching orders (`minL` and `minW`).
- [ ] Returned the overall minimum of the two scenarios.
- [ ] Analyzed time and space complexity correctly.

## Similar Problems
*   Earliest Finish Time For Land And Water Rides I
*   Minimum Time to Complete Trips
*   Merge Intervals
*   Non-overlapping Intervals

## Tags
`Array` `Greedy` `Math`
