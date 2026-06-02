# Earliest Finish Time For Land And Water Rides I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Greedy` `Sorting`  
**Time:** O(M + N)  
**Space:** O(1)

---

## Solution (java)

```java
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        int minL = smallestEnd(landStartTime, landDuration);
        int minW = smallestEnd(waterStartTime, waterDuration);
        int m = landStartTime.length; int n = waterStartTime.length;
        
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<n;i++) ans = Math.min(ans,Math.max(minL,waterStartTime[i]) + waterDuration[i]);
        for(int i=0;i<m;i++) ans = Math.min(ans,Math.max(minW,landStartTime[i]) + landDuration[i]);
        return ans;
    }

    public int smallestEnd(int time[], int[] dur) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < time.length; i++) min = Math.min(min,time[i] + dur[i]);
        return  min;
    }
}
```

---

---
## Quick Revision
This problem asks for the earliest time all rides (land and water) can be completed.
We solve it by finding the earliest possible finish time for each type of ride and then considering combinations.

## Intuition
The core idea is that to finish *all* rides, we must complete the last land ride and the last water ride. However, we can interleave them. The earliest we can finish *all* rides is determined by the latest finish time of either a land ride or a water ride, considering that we might have to wait for one type of ride to finish before starting the other.

Specifically, if we decide to finish all land rides first, then start water rides, the finish time will be `max(earliest_land_finish, earliest_water_start) + water_duration`. Similarly, if we finish water rides first, then land rides. We need to find the minimum of these two scenarios.

The `smallestEnd` helper function is crucial. It calculates the earliest possible time *any* ride of a given type (land or water) can finish if we only consider rides of that type. This gives us a baseline for when all rides of that type *could* be done.

## Algorithm
1.  **`smallestEnd` Helper Function:**
    *   Initialize `min_finish_time` to `Integer.MAX_VALUE`.
    *   Iterate through each ride of the given type (land or water).
    *   For each ride, calculate its finish time: `startTime + duration`.
    *   Update `min_finish_time` to be the minimum of its current value and the calculated finish time.
    *   Return `min_finish_time`.

2.  **`earliestFinishTime` Main Function:**
    *   Calculate `minL`: the earliest possible finish time for *any* land ride using `smallestEnd(landStartTime, landDuration)`.
    *   Calculate `minW`: the earliest possible finish time for *any* water ride using `smallestEnd(waterStartTime, waterDuration)`.
    *   Initialize `ans` (overall earliest finish time) to `Integer.MAX_VALUE`.
    *   **Scenario 1: Finish all land rides, then start water rides.**
        *   Iterate through each water ride `i`.
        *   The earliest this water ride `i` can *start* is `max(minL, waterStartTime[i])`. This means we must wait until all land rides are done (`minL`) *and* the water ride `i` is available (`waterStartTime[i]`).
        *   The finish time for this scenario is `max(minL, waterStartTime[i]) + waterDuration[i]`.
        *   Update `ans = Math.min(ans, this_finish_time)`.
    *   **Scenario 2: Finish all water rides, then start land rides.**
        *   Iterate through each land ride `i`.
        *   The earliest this land ride `i` can *start* is `max(minW, landStartTime[i])`. This means we must wait until all water rides are done (`minW`) *and* the land ride `i` is available (`landStartTime[i]`).
        *   The finish time for this scenario is `max(minW, landStartTime[i]) + landDuration[i]`.
        *   Update `ans = Math.min(ans, this_finish_time)`.
    *   Return `ans`.

## Concept to Remember
*   **Greedy Approach:** The problem can be solved by considering the earliest possible finish times for subsets of rides and combining them.
*   **Time Complexity Optimization:** Identifying the minimum finish time for a category of rides (`smallestEnd`) is a key optimization.
*   **Handling Dependencies/Constraints:** The `Math.max(earliest_category_finish, current_ride_start)` logic correctly models the constraint that a ride cannot start before it's available *and* before all rides of the other category are finished.

## Common Mistakes
*   **Not considering both orders:** Forgetting to check both "land first, then water" and "water first, then land" scenarios.
*   **Incorrectly calculating start time:** Assuming a ride can start as soon as it's available, without considering the completion of the other ride type.
*   **Overlooking the `smallestEnd` optimization:** Trying to iterate through all possible combinations of starting and ending rides, which would be much less efficient.
*   **Integer Overflow:** While not an issue with typical LeetCode constraints for this problem, in general, be mindful of potential overflows with large time/duration values.

## Complexity Analysis
*   **Time:** O(M + N) - The `smallestEnd` function takes O(M) for land and O(N) for water. The main loop iterates through water rides (O(N)) and land rides (O(M)). Thus, the total time complexity is dominated by these linear scans.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables like `minL`, `minW`, `ans`, and loop counters, regardless of the input size.

## Commented Code
```java
class Solution {
    // Main function to find the earliest finish time for all rides.
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        // Calculate the earliest possible finish time if we only consider land rides.
        int minL = smallestEnd(landStartTime, landDuration);
        // Calculate the earliest possible finish time if we only consider water rides.
        int minW = smallestEnd(waterStartTime, waterDuration);
        
        // Get the number of land rides.
        int m = landStartTime.length; 
        // Get the number of water rides.
        int n = waterStartTime.length;
        
        // Initialize the overall minimum finish time to the maximum possible integer value.
        int ans = Integer.MAX_VALUE;
        
        // Scenario 1: Consider finishing all land rides first, then starting water rides.
        // Iterate through each water ride to find the earliest finish time for this scenario.
        for(int i=0;i<n;i++) {
            // The water ride 'i' can start only after all land rides are finished (minL) AND it's available (waterStartTime[i]).
            // So, the start time for this water ride is the maximum of these two.
            // The finish time for this specific water ride in this scenario is its start time + its duration.
            // We update 'ans' with the minimum finish time found so far.
            ans = Math.min(ans,Math.max(minL,waterStartTime[i]) + waterDuration[i]);
        }
        
        // Scenario 2: Consider finishing all water rides first, then starting land rides.
        // Iterate through each land ride to find the earliest finish time for this scenario.
        for(int i=0;i<m;i++) {
            // The land ride 'i' can start only after all water rides are finished (minW) AND it's available (landStartTime[i]).
            // So, the start time for this land ride is the maximum of these two.
            // The finish time for this specific land ride in this scenario is its start time + its duration.
            // We update 'ans' with the minimum finish time found so far.
            ans = Math.min(ans,Math.max(minW,landStartTime[i]) + landDuration[i]);
        }
        
        // Return the overall earliest finish time found across both scenarios.
        return ans;
    }

    // Helper function to find the earliest finish time among all rides of a single type (land or water).
    public int smallestEnd(int time[], int[] dur) {
        // Initialize the minimum finish time to the maximum possible integer value.
        int min = Integer.MAX_VALUE;
        // Iterate through all rides of this type.
        for (int i = 0; i < time.length; i++) {
            // Calculate the finish time for the current ride.
            // Update 'min' if the current ride finishes earlier than any previous ride of this type.
            min = Math.min(min,time[i] + dur[i]);
        }
        // Return the earliest finish time found for this type of ride.
        return  min;
    }
}
```

## Interview Tips
*   **Clarify the Goal:** Ensure you understand that the objective is to complete *all* land rides and *all* water rides, not just one of each.
*   **Explain the `smallestEnd` Logic:** Clearly articulate why finding the minimum finish time for each category is a necessary first step.
*   **Walk Through Scenarios:** Verbally explain the two main scenarios (land first, then water; water first, then land) and how the `Math.max` function correctly models the waiting time.
*   **Consider Edge Cases:** Briefly mention what happens if there are no land rides or no water rides (though the problem constraints likely prevent this, it shows thoroughness).

## Revision Checklist
- [ ] Understand the problem: find the earliest time *all* land and *all* water rides are completed.
- [ ] Implement `smallestEnd` helper function correctly.
- [ ] Calculate `minL` and `minW`.
- [ ] Implement the loop for "land first, then water" scenario.
- [ ] Implement the loop for "water first, then land" scenario.
- [ ] Correctly use `Math.max` to determine the actual start time considering dependencies.
- [ ] Correctly use `Math.min` to track the overall minimum finish time.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Earliest Finish Time For Land And Water Rides II (likely a harder variant)
*   Minimum Time to Complete Trips
*   Meeting Rooms II
*   Merge Intervals

## Tags
`Array` `Greedy` `Math`
