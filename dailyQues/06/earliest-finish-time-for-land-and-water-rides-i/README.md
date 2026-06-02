# Earliest Finish Time For Land And Water Rides I

**Difficulty:** Easy  
**Language:** Java  
**Tags:** `Array` `Two Pointers` `Binary Search` `Greedy` `Sorting`  
**Time:** O(N + M)  
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
This problem asks for the earliest possible time to complete both a land ride and a water ride.
We solve it by finding the earliest possible finish time for each type of ride and then considering all combinations of starting the other ride after the first one finishes.

## Intuition
The core idea is that to finish both a land and a water ride, we must complete the *earlier* of the two rides first, and then start the *second* ride. The second ride cannot start before its scheduled start time *and* cannot start before the first ride is finished. Therefore, the start time for the second ride is the maximum of its scheduled start time and the finish time of the first ride. The total time for a specific combination is this adjusted start time plus the duration of the second ride. We want to find the minimum such total time across all possible pairings.

## Algorithm
1.  **`smallestEnd` Helper Function:**
    *   Initialize `minFinishTime` to `Integer.MAX_VALUE`.
    *   Iterate through the `startTime` and `duration` arrays.
    *   For each ride, calculate its finish time: `startTime[i] + duration[i]`.
    *   Update `minFinishTime` to be the minimum of its current value and the calculated finish time.
    *   Return `minFinishTime`.
2.  **`earliestFinishTime` Main Function:**
    *   Calculate `minLandFinishTime` by calling `smallestEnd` with `landStartTime` and `landDuration`.
    *   Calculate `minWaterFinishTime` by calling `smallestEnd` with `waterStartTime` and `waterDuration`.
    *   Initialize `overallMinFinishTime` to `Integer.MAX_VALUE`.
    *   **Scenario 1: Land ride first, then Water ride.**
        *   Iterate through each water ride (`i` from 0 to `waterStartTime.length - 1`).
        *   The water ride can start at `max(minLandFinishTime, waterStartTime[i])`.
        *   The finish time for this combination is `max(minLandFinishTime, waterStartTime[i]) + waterDuration[i]`.
        *   Update `overallMinFinishTime` with the minimum of its current value and this calculated finish time.
    *   **Scenario 2: Water ride first, then Land ride.**
        *   Iterate through each land ride (`i` from 0 to `landStartTime.length - 1`).
        *   The land ride can start at `max(minWaterFinishTime, landStartTime[i])`.
        *   The finish time for this combination is `max(minWaterFinishTime, landStartTime[i]) + landDuration[i]`.
        *   Update `overallMinFinishTime` with the minimum of its current value and this calculated finish time.
    *   Return `overallMinFinishTime`.

## Concept to Remember
*   **Greedy Approach:** Identifying the earliest possible finish time for a single type of activity (land or water) is a greedy step.
*   **Optimization:** The problem requires finding the minimum across multiple combinations, which is a common optimization pattern.
*   **Time Constraints:** Understanding that an activity cannot start before its scheduled time *and* cannot start before a preceding activity finishes.
*   **Maximum Function:** Using `Math.max` to correctly determine the earliest possible start time for the second activity.

## Common Mistakes
*   **Assuming a fixed order:** Not considering both possibilities (land first, then water; and water first, then land).
*   **Incorrectly calculating start time for the second activity:** Forgetting to include the `max` of the previous activity's finish time and the current activity's scheduled start time.
*   **Not initializing minimums correctly:** Using 0 or other incorrect values instead of `Integer.MAX_VALUE` for finding minimums.
*   **Off-by-one errors in loops:** Incorrectly defining loop bounds.

## Complexity Analysis
*   **Time:** O(N + M) - The `smallestEnd` function takes O(N) for land and O(M) for water. The main function then iterates through water rides (O(M)) and land rides (O(N)). Thus, the total time complexity is dominated by these linear scans.
*   **Space:** O(1) - The algorithm uses a constant amount of extra space for variables, regardless of the input size.

## Commented Code
```java
class Solution {
    // Main function to find the earliest finish time for both land and water rides.
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        
        // Calculate the earliest possible finish time if we only consider land rides.
        int minL = smallestEnd(landStartTime, landDuration);
        // Calculate the earliest possible finish time if we only consider water rides.
        int minW = smallestEnd(waterStartTime, waterDuration);
        
        // Get the number of land rides.
        int m = landStartTime.length; 
        // Get the number of water rides.
        int n = waterStartTime.length;
        
        // Initialize the overall minimum finish time to the largest possible integer value.
        int ans = Integer.MAX_VALUE;
        
        // Scenario 1: Consider completing the earliest land ride first, then any water ride.
        // Iterate through each water ride.
        for(int i=0;i<n;i++) {
            // The water ride can start at the maximum of:
            // 1. The earliest finish time of any land ride (minL).
            // 2. The scheduled start time of this specific water ride (waterStartTime[i]).
            // Add the duration of this water ride to find its finish time.
            // Update the overall minimum finish time if this combination is earlier.
            ans = Math.min(ans,Math.max(minL,waterStartTime[i]) + waterDuration[i]);
        }
        
        // Scenario 2: Consider completing the earliest water ride first, then any land ride.
        // Iterate through each land ride.
        for(int i=0;i<m;i++) {
            // The land ride can start at the maximum of:
            // 1. The earliest finish time of any water ride (minW).
            // 2. The scheduled start time of this specific land ride (landStartTime[i]).
            // Add the duration of this land ride to find its finish time.
            // Update the overall minimum finish time if this combination is earlier.
            ans = Math.min(ans,Math.max(minW,landStartTime[i]) + landDuration[i]);
        }
        
        // Return the overall earliest finish time found.
        return ans;
    }

    // Helper function to find the earliest finish time for a given type of ride (land or water).
    public int smallestEnd(int[] time, int[] dur) {
        // Initialize the minimum finish time to the largest possible integer value.
        int min = Integer.MAX_VALUE;
        // Iterate through all rides of this type.
        for (int i = 0; i < time.length; i++) {
            // Calculate the finish time for the current ride: start time + duration.
            // Update 'min' if the current ride finishes earlier.
            min = Math.min(min,time[i] + dur[i]);
        }
        // Return the earliest finish time found for this type of ride.
        return  min;
    }
}
```

## Interview Tips
*   **Clarify the Goal:** Ensure you understand that you need to complete *one* land ride and *one* water ride, not necessarily all of them.
*   **Break Down the Problem:** Explain that you'll first find the earliest finish for each category (land/water) independently, then combine them.
*   **Explain the `max` Logic:** Clearly articulate why `Math.max(previous_finish_time, current_start_time)` is crucial for determining the start of the second activity.
*   **Consider Edge Cases:** Briefly mention what happens if one of the arrays is empty (though constraints usually prevent this for this problem type).

## Revision Checklist
- [ ] Understand the problem statement: find the minimum time to complete one land and one water ride.
- [ ] Implement the `smallestEnd` helper function correctly.
- [ ] Calculate the earliest finish time for land rides.
- [ ] Calculate the earliest finish time for water rides.
- [ ] Iterate through all combinations: land first, then water.
- [ ] Iterate through all combinations: water first, then land.
- [ ] Correctly use `Math.max` to determine the start time of the second ride.
- [ ] Correctly update the overall minimum finish time.
- [ ] Analyze time and space complexity.

## Similar Problems
*   Earliest Finish Time For Land And Water Rides II (likely a harder variation)
*   Minimum Time to Complete Trips
*   Meeting Rooms II
*   Merge Intervals

## Tags
`Array` `Greedy` `Math`
