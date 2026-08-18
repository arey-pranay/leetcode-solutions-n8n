# The Skyline Problem

**Difficulty:** Unknown  
**Language:** Java  
**Tags:** `Array` `Divide and Conquer` `Sweep Line` `TreeMap` `Segment Tree` `Binary Indexed Tree`  
**Time:** O(N log N)  
**Space:** O(N)

---

## Solution (java)

```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
      int n = buildings.length;
      int[][] events = new int[2*n][2];
      int idx = 0;
      for(int[] arr : buildings){
        events[idx++] = new int[]{arr[0],arr[2]};
        events[idx++] = new int[]{arr[1],-arr[2]};
      }
      Arrays.sort(events,(a,b)->a[0]-b[0]);
      TreeMap<Integer,Integer> tm = new TreeMap<>();
      tm.put(0,1);
      int prevMax = -1;
      List<List<Integer>> ans = new ArrayList<>();
      int i=0;
      while(i<2*n){
        int x= events[i][0];
        while(i<2*n && x == events[i][0]){
          int h = events[i][1];
          if(h>0){
            tm.put(h,tm.getOrDefault(h,0)+1);
          } else {
            h = -h;
            int count = tm.get(h)-1;
            if(count==0) tm.remove(h);
            else tm.put(h,count);
          }
          i++;
        }
        int currMax = tm.lastKey();
        if(currMax != prevMax){
           ans.add(Arrays.asList(x,currMax));
           prevMax = currMax;
        }
      }
      return ans;
    }
}

```

---

---
## Quick Revision
This problem asks to generate the skyline contour from a list of rectangular buildings.
We solve it by processing critical points (start and end of buildings) and maintaining the current maximum height.

## Intuition
The skyline changes only at the start or end of a building. These are our "critical points." If we process these points in order of their x-coordinates, we can track the maximum height at each point. When the maximum height changes, we've found a new point in the skyline. A data structure that efficiently keeps track of active heights and allows quick retrieval of the maximum is needed.

## Algorithm
1.  **Event Creation**: For each building `[left, right, height]`, create two "events":
    *   A start event: `[left, height]`
    *   An end event: `[right, -height]` (negative height signifies an end event)
2.  **Event Sorting**: Sort all events primarily by their x-coordinate. If two events have the same x-coordinate, sort start events (positive heights) before end events (negative heights). This ensures that at a given x, we process all building starts before any building ends.
3.  **Data Structure Initialization**: Use a `TreeMap` (or a balanced BST) to store the heights of the active buildings. The keys will be heights, and the values will be their counts. Initialize it with `(0, 1)` to represent the ground level.
4.  **Sweep Line Processing**: Iterate through the sorted events. Maintain `prevMaxHeight` (initially -1 or 0).
    *   For each x-coordinate:
        *   Process all events at this x-coordinate:
            *   If it's a start event (positive height `h`): Add `h` to the `TreeMap` (increment its count if it already exists).
            *   If it's an end event (negative height `-h`): Remove `h` from the `TreeMap` (decrement its count; if count becomes 0, remove the height from the map).
        *   After processing all events at the current x-coordinate, get the current maximum height (`currMaxHeight`) from the `TreeMap` (which is the `lastKey()` in a `TreeMap`).
        *   If `currMaxHeight` is different from `prevMaxHeight`:
            *   Add a new skyline point `[x, currMaxHeight]` to the result list.
            *   Update `prevMaxHeight = currMaxHeight`.
5.  **Return Result**: The list of skyline points is the answer.

## Concept to Remember
*   **Sweep Line Algorithm**: A technique where a conceptual line sweeps across a geometric space, processing events as it encounters them.
*   **Priority Queue/Balanced BST**: Efficient data structures for maintaining a dynamic set of values and querying for the maximum/minimum element. `TreeMap` in Java provides ordered keys and efficient insertion/deletion/retrieval of the largest key.
*   **Event Points**: Identifying critical points where the state of the system (in this case, the skyline) can change.

## Common Mistakes
*   **Incorrect Event Sorting**: Not handling ties in x-coordinates correctly (e.g., processing an end event before a start event at the same x, leading to an incorrect maximum height).
*   **`TreeMap` Usage**: Incorrectly adding/removing heights, especially when a height's count drops to zero. Not handling the initial ground level (height 0).
*   **Redundant Points**: Not checking if the `currMaxHeight` actually changed from `prevMaxHeight`, leading to unnecessary points in the skyline (e.g., `[x, h]` followed by `[x+1, h]`).
*   **Edge Cases**: Not considering cases with no buildings, single buildings, or overlapping buildings that form complex shapes.

## Complexity Analysis
*   **Time**: O(N log N) - where N is the number of buildings.
    *   Creating events: O(N)
    *   Sorting events: O(N log N) because there are 2N events.
    *   Processing events: Each event involves a `TreeMap` operation (insertion, deletion, or retrieval of last key), which takes O(log K) time, where K is the number of distinct active heights. In the worst case, K can be up to N. So, processing 2N events takes O(N log N).
    *   Overall: O(N log N).
*   **Space**: O(N)
    *   Storing events: O(N) for 2N events.
    *   `TreeMap`: In the worst case, all N buildings might be active simultaneously, so the `TreeMap` can store up to N distinct heights, taking O(N) space.
    *   Result list: In the worst case, the skyline can have O(N) points.

## Commented Code
```java
import java.util.ArrayList; // Import ArrayList for storing the result
import java.util.Arrays; // Import Arrays for sorting and creating lists
import java.util.List; // Import List interface
import java.util.TreeMap; // Import TreeMap for ordered map operations

class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        // Initialize a list to store the skyline points
        List<List<Integer>> ans = new ArrayList<>();
        // Create a list of events. Each building generates two events: start and end.
        // We use a 2D array where each row is [x_coordinate, height].
        // Positive height indicates a building start, negative height indicates a building end.
        int[][] events = new int[buildings.length * 2][2];
        int idx = 0; // Index for populating the events array

        // Iterate through each building to create its start and end events
        for (int[] building : buildings) {
            // Add the start event: [left_x, height]
            events[idx++] = new int[]{building[0], building[2]};
            // Add the end event: [right_x, -height] (negative height signifies end)
            events[idx++] = new int[]{building[1], -building[2]};
        }

        // Sort the events.
        // Primary sort key: x-coordinate (ascending).
        // Secondary sort key (for ties in x-coordinate):
        // - Start events (positive heights) should come before end events (negative heights).
        // - Among start events, taller buildings should come first (though not strictly necessary for correctness here, it's a common optimization).
        // - Among end events, shorter buildings should come first (again, not strictly necessary for correctness here).
        // The provided lambda `(a, b) -> a[0] - b[0]` only sorts by x-coordinate.
        // A more robust sort would be:
        // Arrays.sort(events, (a, b) -> {
        //     if (a[0] != b[0]) {
        //         return a[0] - b[0]; // Sort by x-coordinate
        //     } else {
        //         // If x-coordinates are the same:
        //         // Process starts before ends. A positive height is a start, negative is an end.
        //         // So, if a is start and b is end, a comes first (return negative).
        //         // If a is end and b is start, b comes first (return positive).
        //         // If both are starts, taller first (descending height).
        //         // If both are ends, shorter first (ascending height, so -h1 vs -h2 means h1 vs h2).
        //         return b[1] - a[1]; // This handles starts before ends and taller starts first, shorter ends first.
        //     }
        // });
        // The provided code's sort `Arrays.sort(events,(a,b)->a[0]-b[0]);` is sufficient because the logic inside the loop correctly handles processing all events at the same x before moving to the next x.
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        // Use a TreeMap to store the current active heights and their counts.
        // The keys are heights, and values are the number of buildings with that height currently active.
        // TreeMap keeps keys sorted, so `lastKey()` gives the maximum height.
        // Initialize with height 0 and count 1 to represent the ground.
        TreeMap<Integer, Integer> heightCounts = new TreeMap<>();
        heightCounts.put(0, 1); // Ground level

        // Variable to store the previous maximum height encountered.
        // Initialize to -1 or 0. Using -1 ensures the first height change is captured.
        int prevMaxHeight = 0; // Changed from -1 to 0 for consistency with initial heightCounts.put(0,1)

        // Iterate through the sorted events using a pointer `i`.
        int i = 0;
        while (i < events.length) {
            // Get the current x-coordinate.
            int currentX = events[i][0];

            // Process all events that occur at this `currentX` coordinate.
            // This inner loop ensures we handle all starts and ends at the same x before determining the skyline point.
            while (i < events.length && events[i][0] == currentX) {
                int height = events[i][1]; // Get the height from the event

                if (height > 0) { // This is a building start event
                    // Add the height to the TreeMap or increment its count.
                    heightCounts.put(height, heightCounts.getOrDefault(height, 0) + 1);
                } else { // This is a building end event
                    // Remove the height from the TreeMap.
                    height = -height; // Get the actual positive height
                    int count = heightCounts.get(height) - 1; // Decrement the count

                    if (count == 0) {
                        // If the count becomes 0, remove the height from the map entirely.
                        heightCounts.remove(height);
                    } else {
                        // Otherwise, update the count.
                        heightCounts.put(height, count);
                    }
                }
                // Move to the next event.
                i++;
            }

            // After processing all events at `currentX`, find the current maximum height.
            // `lastKey()` in TreeMap returns the largest key (maximum height).
            int currentMaxHeight = heightCounts.lastKey();

            // If the current maximum height is different from the previous maximum height,
            // it means the skyline has changed, so we add a new point to our result.
            if (currentMaxHeight != prevMaxHeight) {
                // Add the skyline point [x_coordinate, new_max_height] to the answer list.
                ans.add(Arrays.asList(currentX, currentMaxHeight));
                // Update the previous maximum height for the next iteration.
                prevMaxHeight = currentMaxHeight;
            }
        }

        // Return the list of skyline points.
        return ans;
    }
}
```

## Interview Tips
1.  **Explain the Sweep Line**: Clearly articulate the sweep line concept and why it's applicable here. Emphasize that skyline changes only occur at building boundaries.
2.  **Data Structure Choice**: Justify the use of `TreeMap` (or a similar ordered map/priority queue) for efficiently tracking active heights and finding the maximum. Discuss its time complexity implications.
3.  **Event Handling Logic**: Walk through the logic for processing start and end events, especially how you handle multiple events at the same x-coordinate and how you update the active heights.
4.  **Edge Cases**: Be prepared to discuss edge cases like no buildings, single buildings, buildings with the same start/end points, and buildings that perfectly align.
5.  **Code Walkthrough**: Verbally walk through your commented code, explaining each significant step and data structure operation.

## Revision Checklist
- [ ] Understand the problem: generating skyline from buildings.
- [ ] Identify critical points: building start and end x-coordinates.
- [ ] Choose appropriate data structure: `TreeMap` for active heights.
- [ ] Implement event creation and sorting.
- [ ] Implement sweep line logic: processing events and updating max height.
- [ ] Handle `TreeMap` operations correctly (add, remove, get max).
- [ ] Ensure correct skyline point generation (only when max height changes).
- [ ] Analyze time and space complexity.
- [ ] Consider edge cases.

## Similar Problems
*   [1851. Minimum Interval to Include Each Query](https://leetcode.com/problems/minimum-interval-to-include-each-query/) (Uses sweep line and similar data structures)
*   [218. The Skyline Problem](https://leetcode.com/problems/the-skyline-problem/) (This is the problem itself)
*   [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) (Sliding window, related to interval processing)

## Tags
`Array` `Divide and Conquer` `Sweep Line` `TreeMap` `Segment Tree` `Binary Indexed Tree`
