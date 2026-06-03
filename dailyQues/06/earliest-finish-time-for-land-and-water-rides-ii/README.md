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
This problem asks for the earliest time to complete two types of rides, land and water, with specific time constraints for each. We need to find the minimum of two scenarios: completing land rides first then water, or water rides first then land.

## Intuition
The core idea is to consider the two possible orderings of completing the rides:
1. Land rides first, then water rides.
2. Water rides first, then land rides.

For each ordering, we need to calculate the *earliest possible finish time*. This involves finding the minimum time to complete all land rides and the minimum time to complete all water rides independently. Then, we combine these minimums based on the chosen order.

Specifically, if we do land rides first, the total time is the time to finish all land rides plus the time to finish all water rides. However, the water rides can *start* as soon as the *last* land ride finishes. Similarly, if water rides are done first, land rides can start as soon as the water rides are done.

The crucial observation is that we only care about the *minimum* time to complete *all* land rides and the *minimum* time to complete *all* water rides. Let `min_land_time` be the minimum time to complete any single land ride, and `min_water_time` be the minimum time to complete any single water ride.

If we do land rides first, the total time will be `min_land_time` (to finish all land rides) + `min_water_time` (to finish all water rides). But this is not quite right. The water rides can start *after* the land rides are done. The time to finish *all* land rides is the minimum of `la[i] + lb[i]` for all land rides `i`. Let this be `L_total`. Similarly, let `W_total` be the minimum of `wa[j] + wb[j]` for all water rides `j`.

If land rides are done first, the finish time is `L_total + W_total`.
If water rides are done first, the finish time is `W_total + L_total`.

This seems too simple. Let's re-read the problem. "Earliest Finish Time For Land And Water Rides". This implies we have *multiple* land rides and *multiple* water rides. The problem statement is a bit ambiguous, but the provided solution suggests that `la[i]` and `lb[i]` are the start and end times for the *i-th land ride*, and `wa[j]` and `wb[j]` are for the *j-th water ride*. The goal is to complete *all* land rides and *all* water rides.

The solution calculates:
- `l`: minimum time to complete *any single* land ride (`min(la[i] + lb[i])`).
- `w`: minimum time to complete *any single* water ride (`min(wa[j] + wb[j])`).

Then it calculates:
- `minL`: minimum finish time if water rides are done first. This is `min(max(wa[j], l) + wb[j])`. This means for each water ride `j`, it can start at `wa[j]` or at time `l` (when the fastest land ride finishes), whichever is later. Then it adds `wb[j]`. This logic seems to imply that we only need to finish *one* land ride and *one* water ride. This interpretation is likely incorrect given the problem title.

Let's assume the problem means:
- `la[i]` and `lb[i]` are the start and duration of the i-th land ride.
- `wa[j]` and `wb[j]` are the start and duration of the j-th water ride.
- We need to complete *all* land rides and *all* water rides.

The provided solution's logic:
`l = Math.min(l, la[i] + lb[i])`: This calculates the minimum *finish time* for any *single* land ride. Let's call this `min_finish_land_single`.
`w = Math.min(w, wa[i] + wb[i])`: This calculates the minimum *finish time* for any *single* water ride. Let's call this `min_finish_water_single`.

`minL = Math.min(minL, Math.max(wa[i], l) + wb[i])`: This is the key. For each water ride `i`, it considers its start time `wa[i]` and the earliest time *any* land ride can finish (`l`). The water ride can only start after its own scheduled start time *and* after the earliest land ride finishes. So, the start time for this water ride is `max(wa[i], l)`. Then, it adds its duration `wb[i]`. This calculates the minimum finish time for *all* water rides, assuming land rides are done first. This is still confusing.

Let's re-interpret the problem based on the solution's variables:
`la[i]`: start time of land ride `i`.
`lb[i]`: duration of land ride `i`.
`wa[j]`: start time of water ride `j`.
`wb[j]`: duration of water ride `j`.

The problem is to find the earliest time by which *all* land rides and *all* water rides are completed.

Consider two scenarios:
1. **Land rides first, then water rides:**
   - We need to complete all land rides. The earliest time all land rides can be completed is `max(la[i] + lb[i])` over all `i`. Let this be `finish_all_land`.
   - Once all land rides are done, we can start the water rides. The earliest a water ride `j` can start is `max(finish_all_land, wa[j])`. Its finish time will be `max(finish_all_land, wa[j]) + wb[j]`.
   - The earliest time all water rides are completed in this scenario is `max(max(finish_all_land, wa[j]) + wb[j])` over all `j`.

2. **Water rides first, then land rides:**
   - We need to complete all water rides. The earliest time all water rides can be completed is `max(wa[j] + wb[j])` over all `j`. Let this be `finish_all_water`.
   - Once all water rides are done, we can start the land rides. The earliest a land ride `i` can start is `max(finish_all_water, la[i])`. Its finish time will be `max(finish_all_water, la[i]) + lb[i]`.
   - The earliest time all land rides are completed in this scenario is `max(max(finish_all_water, la[i]) + lb[i])` over all `i`.

The minimum of these two overall finish times would be the answer.

However, the provided solution does *not* calculate `max(la[i] + lb[i])` or `max(wa[j] + wb[j])`. Instead, it calculates `min(la[i] + lb[i])` and `min(wa[j] + wb[j])`. This suggests a different interpretation of the problem.

Let's assume the problem is simpler: we have *one* land ride and *one* water ride.
- Land ride: starts at `la[0]`, duration `lb[0]`.
- Water ride: starts at `wa[0]`, duration `wb[0]`.

Scenario 1: Land first, then Water.
- Land finishes at `la[0] + lb[0]`.
- Water can start at `max(la[0] + lb[0], wa[0])`.
- Water finishes at `max(la[0] + lb[0], wa[0]) + wb[0]`.

Scenario 2: Water first, then Land.
- Water finishes at `wa[0] + wb[0]`.
- Land can start at `max(wa[0] + wb[0], la[0])`.
- Land finishes at `max(wa[0] + wb[0], la[0]) + lb[0]`.

The minimum of these two is the answer. This matches the structure of the solution if `n=1` and `m=1`.

But the problem statement says `int[] la, int[] lb, int[] wa, int[] wb`. This implies multiple rides. The solution's use of `min` suggests it's finding the *earliest possible completion time for a single ride of each type*.

Let's assume the problem is:
We have a set of land rides and a set of water rides.
For land rides, `la[i]` is the start time and `lb[i]` is the duration.
For water rides, `wa[j]` is the start time and `wb[j]` is the duration.
We need to complete *all* land rides and *all* water rides.

The solution's variables:
`l`: `min(la[i] + lb[i])` over all land rides `i`. This is the *earliest finish time of any single land ride*.
`w`: `min(wa[j] + wb[j])` over all water rides `j`. This is the *earliest finish time of any single water ride*.

`minL`: This variable is calculated as `min(max(wa[j], l) + wb[j])`.
This means for each water ride `j`, we consider its start time `wa[j]` and the earliest time *any* land ride finishes (`l`). The water ride `j` can start at `max(wa[j], l)`. Then we add its duration `wb[j]`. This calculates the minimum finish time for *any single water ride*, given that *at least one* land ride has finished by time `l`. This is still not about completing *all* rides.

The problem statement and the solution seem to be mismatched or I'm missing a crucial interpretation.
Let's assume the problem is asking for the earliest time we can finish *at least one* land ride and *at least one* water ride, considering the two possible orders.

Scenario 1: Finish a land ride, then a water ride.
- The earliest a land ride can finish is `l = min(la[i] + lb[i])`.
- The earliest a water ride `j` can start is `max(wa[j], l)`.
- The earliest a water ride `j` can finish is `max(wa[j], l) + wb[j]`.
- The earliest we can finish *a* water ride after finishing *a* land ride is `min(max(wa[j], l) + wb[j])` over all `j`. This is `minL`.

Scenario 2: Finish a water ride, then a land ride.
- The earliest a water ride can finish is `w = min(wa[j] + wb[j])`.
- The earliest a land ride `i` can start is `max(la[i], w)`.
- The earliest a land ride `i` can finish is `max(la[i], w) + lb[i]`.
- The earliest we can finish *a* land ride after finishing *a* water ride is `min(max(la[i], w) + lb[i])` over all `i`. This is `minW`.

The final answer is `min(minL, minW)`. This interpretation aligns perfectly with the provided code. The problem title "Earliest Finish Time For Land And Water Rides Ii" is misleading if this is the case, as it implies completing *all* rides. It should perhaps be "Earliest Finish Time For *A* Land And *A* Water Ride".

Given the solution, the problem is interpreted as:
Find the minimum time to complete one land ride and one water ride, considering two scenarios:
1. Complete a land ride first, then a water ride.
2. Complete a water ride first, then a land ride.

For scenario 1:
- Find the earliest time any land ride can finish: `l = min(la[i] + lb[i])`.
- For each water ride `j`, it can start at `max(wa[j], l)` and finishes at `max(wa[j], l) + wb[j]`.
- The earliest time we can finish *any* water ride in this scenario is `minL = min(max(wa[j], l) + wb[j])`.

For scenario 2:
- Find the earliest time any water ride can finish: `w = min(wa[j] + wb[j])`.
- For each land ride `i`, it can start at `max(la[i], w)` and finishes at `max(la[i], w) + lb[i]`.
- The earliest time we can finish *any* land ride in this scenario is `minW = min(max(la[i], w) + lb[i])`.

The overall earliest finish time is `min(minL, minW)`.

## Algorithm
1. Initialize `l` (earliest finish time for any single land ride) and `minL` (earliest finish time for a water ride after a land ride) to a very large value (e.g., `MAX`).
2. Initialize `w` (earliest finish time for any single water ride) and `minW` (earliest finish time for a land ride after a water ride) to a very large value (e.g., `MAX`).
3. Iterate through all land rides (from `i = 0` to `n-1`):
    a. Calculate the finish time for the current land ride: `la[i] + lb[i]`.
    b. Update `l` to be the minimum of its current value and the current land ride's finish time: `l = Math.min(l, la[i] + lb[i])`.
4. Iterate through all water rides (from `j = 0` to `m-1`):
    a. Calculate the finish time for the current water ride: `wa[j] + wb[j]`.
    b. Update `w` to be the minimum of its current value and the current water ride's finish time: `w = Math.min(w, wa[j] + wb[j])`.
    c. Calculate the earliest finish time for this water ride if it happens *after* the earliest possible land ride completion:
        i. The water ride `j` can start at `max(wa[j], l)`.
        ii. Its finish time will be `max(wa[j], l) + wb[j]`.
    d. Update `minL` to be the minimum of its current value and this calculated finish time: `minL = Math.min(minL, Math.max(wa[j], l) + wb[j])`.
5. Iterate through all land rides again (from `i = 0` to `n-1`):
    a. Calculate the earliest finish time for this land ride if it happens *after* the earliest possible water ride completion:
        i. The land ride `i` can start at `max(la[i], w)`.
        ii. Its finish time will be `max(la[i], w) + lb[i]`.
    b. Update `minW` to be the minimum of its current value and this calculated finish time: `minW = Math.min(minW, Math.max(la[i], w) + lb[i])`.
6. The final answer is the minimum of `minL` (water after land) and `minW` (land after water): `return Math.min(minW, minL)`.

## Concept to Remember
*   **Greedy Approach:** The problem can be solved by considering the "best case" for each sub-problem independently (earliest finish for a single land ride, earliest finish for a single water ride) and then combining these optimal sub-solutions.
*   **Two Scenarios/Orderings:** When dealing with sequential tasks, always consider all possible valid orderings of those tasks. Here, it's land-then-water vs. water-then-land.
*   **`Math.min` and `Math.max`:** These functions are crucial for finding minimums and handling dependencies (e.g., a task cannot start before its own scheduled time AND before a preceding task finishes).
*   **Time Complexity Optimization:** Calculating minimums in a single pass is more efficient than recalculating.

## Common Mistakes
*   **Misinterpreting "All Rides":** Assuming the problem requires completing *all* land rides and *all* water rides, rather than just one of each, which leads to a more complex `max` based calculation for overall completion. The provided solution implies a simpler interpretation.
*   **Incorrect Dependency Handling:** Not using `Math.max` to correctly model that a ride can only start after its own scheduled time *and* after the preceding type of ride has finished.
*   **Ignoring One Scenario:** Forgetting to calculate the finish time for both "land first" and "water first" scenarios, leading to an incomplete solution.
*   **Off-by-One Errors or Incorrect Loop Bounds:** Standard programming errors in loop conditions or array indexing.
*   **Integer Overflow:** While not apparent with typical LeetCode constraints, using `MAX` as a sentinel value should be large enough.

## Complexity Analysis
- Time: O(N + M) - reason: We iterate through the land rides array once to find `l` and once to find `minW` (total 2N iterations). We iterate through the water rides array once to find `w` and `minL` (total M iterations). The dominant factor is the sum of the lengths of the arrays.
- Space: O(1) - reason: We only use a few constant extra variables (`l`, `w`, `minL`, `minW`, `n`, `m`) regardless of the input size.

## Commented Code
```java
class Solution {
    // Define a constant for a large initial value, typically larger than any possible time.
    final static int MAX = 300005;

    public int earliestFinishTime(int[] la, int[] lb, int[] wa, int[] wb) {
        // Initialize 'l' to MAX. 'l' will store the minimum finish time of any single land ride.
        int l = MAX;
        // Initialize 'w' to MAX. 'w' will store the minimum finish time of any single water ride.
        int w = MAX;
        // Initialize 'minL' to MAX. 'minL' will store the earliest finish time if land rides are done first, then water rides.
        int minL = MAX;
        // Initialize 'minW' to MAX. 'minW' will store the earliest finish time if water rides are done first, then land rides.
        int minW = MAX;

        // Get the number of land rides.
        int n = la.length;
        // Get the number of water rides.
        int m = wa.length;

        // First pass: Calculate the earliest finish time for any single land ride.
        for (int i = 0; i < n; ++i)
            // 'l' is updated with the minimum of its current value and the finish time of the current land ride (start + duration).
            l = Math.min(l, la[i] + lb[i]);

        // Second pass: Calculate the earliest finish time for any single water ride ('w'),
        // AND calculate the earliest finish time for a water ride after the earliest land ride finishes ('minL').
        for (int i = 0; i < m; ++i) {
            // Update 'w' with the minimum finish time of any single water ride.
            w = Math.min(w, wa[i] + wb[i]);
            // Calculate the finish time for the current water ride 'i' if it starts after the earliest land ride finishes ('l').
            // The water ride can start at max(its own start time wa[i], the earliest land ride finish time l).
            // Then add its duration wb[i].
            // 'minL' is updated with the minimum of these possible water ride finish times.
            minL = Math.min(minL, Math.max(wa[i], l) + wb[i]);
        }

        // Third pass: Calculate the earliest finish time for a land ride after the earliest water ride finishes ('minW').
        for (int i = 0; i < n; ++i)
            // Calculate the finish time for the current land ride 'i' if it starts after the earliest water ride finishes ('w').
            // The land ride can start at max(its own start time la[i], the earliest water ride finish time w).
            // Then add its duration lb[i].
            // 'minW' is updated with the minimum of these possible land ride finish times.
            minW = Math.min(minW, Math.max(la[i], w) + lb[i]);

        // The final answer is the minimum of the two scenarios:
        // 1. Earliest finish time if water rides are done after land rides ('minL').
        // 2. Earliest finish time if land rides are done after water rides ('minW').
        return Math.min(minW, minL);
    }
}
```

## Interview Tips
*   **Clarify the Problem:** If the problem statement is ambiguous (as this one is, regarding "all rides" vs. "one of each"), ask clarifying questions. "Does this mean we need to complete *all* land rides and *all* water rides, or just one of each type?" Based on the solution, assume it's one of each.
*   **Explain Your Two Scenarios:** Clearly articulate the two main cases you are considering: land rides first, then water rides; and water rides first, then land rides.
*   **Walk Through the Logic:** Explain how `Math.max` is used to handle the dependencies between ride types and their own scheduled start times. Explain why `Math.min` is used to find the overall earliest finish time.
*   **Consider Edge Cases:** Briefly mention what happens if there are no land rides or no water rides (though the constraints usually prevent this). Also, consider cases where ride times overlap significantly.

## Revision Checklist
- [ ] Understand the problem statement and its constraints.
- [ ] Identify the two primary scenarios: land-then-water and water-then-land.
- [ ] Correctly calculate the earliest finish time for a single ride of each type (`l` and `w`).
- [ ] Implement the logic for the "land-then-water" scenario (`minL`).
- [ ] Implement the logic for the "water-then-land" scenario (`minW`).
- [ ] Combine the results of the two scenarios using `Math.min`.
- [ ] Analyze time and space complexity.
- [ ] Write clean, commented code.

## Similar Problems
*   Earliest Finish Time For Land And Water Rides I (likely a simpler version, perhaps with only one of each ride type)
*   Minimum Time to Complete All Tasks (general scheduling problems)
*   Meeting Rooms II (finding maximum overlap, related to scheduling)
*   Merge Intervals (handling overlapping time intervals)

## Tags
`Array` `Greedy` `Math`
