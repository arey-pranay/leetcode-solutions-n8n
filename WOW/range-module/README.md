# Range Module

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Design` `Segment Tree` `Ordered Set`  
**Time:** O(log N)  
**Space:** O(N)

---

## Solution (java)

```java
class RangeModule {
    // +   10 -> 19
    // -   14 -> 15
    // ?   10 -> 13 => true
    // ?   13 -> 14 => false
    // ?   16 -> 16 => true 
    
    TreeMap<Integer,Integer> tm; // sorted order me start -> end
    public RangeModule() {
        this.tm = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        if(right <= left) return;
        if(e!=null && e.getValue() >= left){ // merge left side interval
            tm.remove(e.getKey());
            left = e.getKey();
            right = Math.max(right,e.getValue()); // update end value to merge correctly
        }
        
        while(true){    // merge right side interval
            Map.Entry<Integer,Integer> nextEntry = tm.ceilingEntry(left);
            if(nextEntry==null || nextEntry.getKey() > right) break;
            tm.remove(nextEntry.getKey()); // remove next entry
            right = Math.max(right,nextEntry.getValue()); // update end value to merge correctly
        }
        
        tm.put(left,right);
    }
    
    public boolean queryRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        return e != null && e.getKey() <= left && e.getValue() >= right;
    }
    
    public void removeRange(int left, int right) {
        Map.Entry<Integer,Integer> e = tm.floorEntry(left);
        if(e!=null && e.getValue() >= left){ // merge left side interval
            tm.put(e.getKey(),left);
            if(e.getValue()>right) tm.put(right,e.getValue()); // update end value to merge correctly
        }
        while(true){    // merge right side interval
            Map.Entry<Integer,Integer> nextEntry = tm.ceilingEntry(left);
            if(nextEntry==null || right < nextEntry.getKey()) break;
            tm.remove(nextEntry.getKey()); // remove next entry
            if(nextEntry.getValue()>right){tm.put(right,nextEntry.getValue()); break;}
        }
    }
}
// 10 20
// 25 35
// 40 50
// 55 100

// remove 19 60

// ["r","add","rem","rem","add","rem","add","que","que","que"]
// [[],[6,8],[7,8],[8,9],[8,9],[1,3],[1,8],[2,4],[2,9],[4,6]]

// Output
// [null,null,null,null,null,null,null,true,false,true]
// Expected
// [null,null,null,null,null,null,null,true,true,true]

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
```

---

---
## Quick Revision
This problem requires managing a collection of disjoint intervals, supporting adding, querying, and removing ranges.
We use a `TreeMap` to store intervals sorted by their start points, enabling efficient merging and splitting operations.

## Intuition
The core challenge is efficiently handling overlapping and adjacent intervals when adding or removing ranges. A `TreeMap` is ideal because it keeps intervals sorted by their start points. This allows us to quickly find relevant intervals that might overlap with the range we're operating on.

When adding a range, we need to merge it with any existing intervals it overlaps with. This might involve extending the start of an existing interval or the end of an existing interval, or even merging multiple adjacent intervals into one.

When querying a range, we just need to find if there's a single existing interval that completely encompasses the query range.

When removing a range, we might split an existing interval into two, shorten an existing interval, or remove an interval entirely.

The `TreeMap`'s `floorEntry` and `ceilingEntry` methods are crucial for finding the intervals that are immediately before or after a given point, which helps in identifying potential overlaps or adjacencies.

## Algorithm
1.  **Initialization**: Use a `TreeMap<Integer, Integer>` where keys are the start of intervals and values are the end of intervals. This `TreeMap` will store disjoint intervals, sorted by their start points.

2.  **`addRange(left, right)`**:
    *   Handle edge case: if `right <= left`, return immediately.
    *   Find the interval `e` that might overlap with `left` using `tm.floorEntry(left)`.
    *   If `e` exists and its end (`e.getValue()`) is greater than or equal to `left`, it means `left` falls within or at the start of `e`. Merge `e` into the new range:
        *   Remove `e` from the `TreeMap`.
        *   Update `left` to `e.getKey()` (the start of the merged interval).
        *   Update `right` to `Math.max(right, e.getValue())` (the maximum end of the merged interval).
    *   Iterate using `tm.ceilingEntry(left)` to find subsequent intervals that might overlap with the current `right` boundary:
        *   If `nextEntry` is null or its start (`nextEntry.getKey()`) is greater than `right`, break the loop (no more overlaps to the right).
        *   Remove `nextEntry` from the `TreeMap`.
        *   Update `right` to `Math.max(right, nextEntry.getValue())` to extend the current range to encompass `nextEntry`.
    *   Finally, insert the merged interval `(left, right)` into the `TreeMap`.

3.  **`queryRange(left, right)`**:
    *   Find the interval `e` that might contain `left` using `tm.floorEntry(left)`.
    *   Return `true` if `e` exists, its start (`e.getKey()`) is less than or equal to `left`, and its end (`e.getValue()`) is greater than or equal to `right`. Otherwise, return `false`.

4.  **`removeRange(left, right)`**:
    *   Handle edge case: if `right <= left`, return immediately.
    *   Find the interval `e` that might overlap with `left` using `tm.floorEntry(left)`.
    *   If `e` exists and its end (`e.getValue()`) is greater than or equal to `left`:
        *   This interval `e` needs to be modified. Update its end to `left` (effectively trimming the part that is before `left`). `tm.put(e.getKey(), left)`.
        *   If `e.getValue()` is also greater than `right`, it means the original interval `e` extended beyond `right`. We need to create a new interval starting from `right` to `e.getValue()`. `tm.put(right, e.getValue())`.
    *   Iterate using `tm.ceilingEntry(left)` to find subsequent intervals that start before or at `right`:
        *   If `nextEntry` is null or its start (`nextEntry.getKey()`) is greater than `right`, break the loop (no more intervals to remove or split).
        *   Remove `nextEntry` from the `TreeMap`.
        *   If `nextEntry.getValue()` is greater than `right`, it means this interval extended beyond `right`. We need to create a new interval starting from `right` to `nextEntry.getValue()`. `tm.put(right, nextEntry.getValue())`. Then break the loop as we've handled the relevant part of this interval.

## Concept to Remember
*   **Interval Management**: Efficiently storing, querying, and modifying sets of intervals.
*   **TreeMap Properties**: Using `floorEntry` and `ceilingEntry` for efficient searching of relevant intervals based on keys.
*   **Merging and Splitting Intervals**: Logic for combining overlapping intervals and dividing existing intervals.
*   **Disjoint Intervals**: Maintaining a collection of intervals that do not overlap.

## Common Mistakes
*   **Incorrectly handling boundary conditions**: Off-by-one errors when comparing `left`, `right`, interval starts, and interval ends.
*   **Not properly merging/splitting intervals**: Failing to consider all overlapping intervals when adding, or not correctly splitting an interval when removing a part.
*   **Inefficient searching**: Using linear scans instead of `TreeMap`'s logarithmic time operations.
*   **Modifying `TreeMap` while iterating**: This can lead to `ConcurrentModificationException` or incorrect results. The provided solution avoids this by carefully removing and re-inserting.
*   **Forgetting to handle cases where `right <= left`**: These are invalid ranges and should be ignored.

## Complexity Analysis
*   **Time**:
    *   `addRange`: O(log N) for `floorEntry` and `ceilingEntry` operations. The `while` loop for merging right-side intervals might iterate up to N times in the worst case if all intervals need to be merged. However, each interval is added and removed at most once across all operations. So, amortized time is O(log N).
    *   `queryRange`: O(log N) due to `floorEntry`.
    *   `removeRange`: O(log N) for `floorEntry` and `ceilingEntry` operations. Similar to `addRange`, the `while` loop might iterate, but amortized time is O(log N).
    *   *Reason*: `TreeMap` operations (`floorEntry`, `ceilingEntry`, `put`, `remove`) take O(log N) time, where N is the number of intervals in the `TreeMap`. The loops iterate over a limited number of overlapping intervals.

*   **Space**: O(N), where N is the number of disjoint intervals stored in the `TreeMap`.
    *   *Reason*: The `TreeMap` stores the start and end points of all disjoint intervals.

## Commented Code
```java
import java.util.TreeMap;
import java.util.Map;

class RangeModule {
    // TreeMap to store disjoint intervals.
    // Key: start of an interval, Value: end of an interval.
    // Intervals are kept sorted by their start points.
    TreeMap<Integer, Integer> tm;

    // Constructor: Initializes an empty TreeMap.
    public RangeModule() {
        this.tm = new TreeMap<>();
    }

    // Adds a range [left, right) to the collection of intervals.
    // Merges overlapping or adjacent intervals.
    public void addRange(int left, int right) {
        // If the range is invalid (end <= start), do nothing.
        if (right <= left) return;

        // Find the entry whose key is the greatest less than or equal to 'left'.
        // This is a potential interval that might overlap with the left side of our new range.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // If such an entry 'e' exists AND its end is greater than or equal to 'left',
        // it means 'left' falls within or at the start of interval 'e'.
        // We need to merge 'e' into our new range.
        if (e != null && e.getValue() >= left) {
            // Remove the old entry 'e' as it will be part of the merged interval.
            tm.remove(e.getKey());
            // Update 'left' to the start of 'e' to encompass the merged interval.
            left = e.getKey();
            // Update 'right' to the maximum of the current 'right' and 'e's end,
            // ensuring the merged interval covers the furthest extent.
            right = Math.max(right, e.getValue());
        }

        // Now, we need to merge any intervals that overlap with the right side of our current range [left, right).
        // We iterate as long as there are intervals that start before or at 'right'.
        while (true) {
            // Find the entry whose key is the smallest greater than or equal to 'left'.
            // This is the next potential interval that might overlap or be adjacent.
            Map.Entry<Integer, Integer> nextEntry = tm.ceilingEntry(left);

            // If there's no next entry, or the next entry starts strictly after 'right',
            // then there are no more intervals to merge on the right. Break the loop.
            if (nextEntry == null || nextEntry.getKey() > right) break;

            // If the next entry overlaps with our current range [left, right), remove it.
            tm.remove(nextEntry.getKey());
            // Update 'right' to the maximum of the current 'right' and the end of 'nextEntry'.
            // This extends our merged interval to cover 'nextEntry'.
            right = Math.max(right, nextEntry.getValue());
        }

        // After merging all overlapping intervals, insert the final merged interval [left, right) into the TreeMap.
        tm.put(left, right);
    }

    // Queries if the range [left, right) is completely covered by existing intervals.
    public boolean queryRange(int left, int right) {
        // Find the entry whose key is the greatest less than or equal to 'left'.
        // This is the only interval that could potentially cover 'left'.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // Check if such an entry 'e' exists, AND
        // if its start is less than or equal to 'left' (meaning it covers 'left'), AND
        // if its end is greater than or equal to 'right' (meaning it covers 'right').
        // If all conditions are met, the range [left, right) is covered.
        return e != null && e.getKey() <= left && e.getValue() >= right;
    }

    // Removes the range [left, right) from the collection of intervals.
    // This might split existing intervals.
    public void removeRange(int left, int right) {
        // If the range is invalid (end <= start), do nothing.
        if (right <= left) return;

        // Find the entry whose key is the greatest less than or equal to 'left'.
        // This is a potential interval that might overlap with the left side of our removal range.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // If such an entry 'e' exists AND its end is greater than or equal to 'left',
        // it means 'left' falls within or at the start of interval 'e'.
        // This interval 'e' needs to be modified.
        if (e != null && e.getValue() >= left) {
            // We need to trim the part of 'e' that is from 'left' onwards.
            // So, update the end of 'e' to 'left'.
            tm.put(e.getKey(), left);
            // If the original end of 'e' was strictly greater than 'right',
            // it means there's a remaining part of 'e' that starts after 'right'.
            // We need to create a new interval for this remaining part.
            if (e.getValue() > right) {
                // The new interval starts at 'right' and ends at the original end of 'e'.
                tm.put(right, e.getValue());
            }
        }

        // Now, we need to remove any intervals that are fully contained within [left, right)
        // or that extend beyond 'right'.
        // We iterate as long as there are intervals that start before or at 'right'.
        while (true) {
            // Find the entry whose key is the smallest greater than or equal to 'left'.
            // This is the next potential interval to consider for removal.
            Map.Entry<Integer, Integer> nextEntry = tm.ceilingEntry(left);

            // If there's no next entry, or the next entry starts strictly after 'right',
            // then there are no more intervals to remove or split on the right. Break the loop.
            if (nextEntry == null || nextEntry.getKey() > right) break;

            // If the next entry starts before or at 'right', it needs to be removed or split.
            // Remove the entire 'nextEntry' for now.
            tm.remove(nextEntry.getKey());
            // If the end of 'nextEntry' is strictly greater than 'right',
            // it means this interval extended beyond 'right'.
            // We need to create a new interval for the part that starts at 'right'.
            if (nextEntry.getValue() > right) {
                // The new interval starts at 'right' and ends at the original end of 'nextEntry'.
                tm.put(right, nextEntry.getValue());
                // Since we've handled the part of this interval that extends beyond 'right',
                // and we've already removed the full interval, we can break.
                break;
            }
            // If nextEntry.getValue() <= right, the entire interval was within [left, right) and is now removed.
            // The loop continues to check for the next interval.
        }
    }
}
```

## Interview Tips
1.  **Clarify Interval Definitions**: Ask if ranges are inclusive or exclusive (e.g., `[left, right]` or `[left, right)`). The provided solution assumes `[left, right)`.
2.  **Data Structure Choice**: Explain why `TreeMap` is a good choice for managing sorted, disjoint intervals and how its methods (`floorEntry`, `ceilingEntry`) are key to efficiency.
3.  **Edge Cases**: Discuss how you handle empty ranges (`right <= left`), intervals at the boundaries, and cases where a range completely contains, is contained by, or partially overlaps with existing intervals.
4.  **Step-by-Step Logic**: Walk through the `addRange` and `removeRange` operations with a small example on a whiteboard, showing how the `TreeMap` changes. This demonstrates your understanding of the merging and splitting logic.
5.  **Complexity Justification**: Be prepared to explain the time and space complexity, especially the amortized analysis for `addRange` and `removeRange` if asked.

## Revision Checklist
- [ ] Understand the problem: managing disjoint intervals.
- [ ] Choose appropriate data structure: `TreeMap` for sorted intervals.
- [ ] Implement `addRange`: handle merging with `floorEntry` and `ceilingEntry`.
- [ ] Implement `queryRange`: check for complete containment using `floorEntry`.
- [ ] Implement `removeRange`: handle splitting and trimming intervals.
- [ ] Consider edge cases: `right <= left`, boundary overlaps.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the logic clearly.

## Similar Problems
*   Merge Intervals (LeetCode 56)
*   Insert Interval (LeetCode 57)
*   Employee Free Time (LeetCode 759)
*   Meeting Rooms II (LeetCode 253)

## Tags
`TreeMap` `Interval` `Data Structure` `Algorithm`
