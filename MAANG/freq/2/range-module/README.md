# Range Module

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Design` `Segment Tree` `Ordered Set`  
**Time:** See complexity section  
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
This problem asks to manage a collection of disjoint intervals, supporting adding, removing, and querying ranges.
We use a `TreeMap` to store intervals, leveraging its sorted nature for efficient operations.

## Intuition
The core idea is to maintain a set of disjoint intervals that cover all the added ranges. When adding a range, we need to merge it with any overlapping existing intervals. When removing a range, we might split existing intervals or shorten them. A `TreeMap` is ideal because it keeps intervals sorted by their start points, allowing us to quickly find overlapping intervals using `floorEntry` and `ceilingEntry`.

## Algorithm
1.  **Initialization**: Use a `TreeMap<Integer, Integer>` where keys are the start of a range and values are the end of that range.
2.  **`addRange(left, right)`**:
    *   Find the interval `e` that might overlap with `left` from the left side using `tm.floorEntry(left)`.
    *   If `e` exists and its end (`e.getValue()`) is greater than or equal to `left`, it means `left` falls within or touches `e`. Merge `e` into the new range: update `left` to `e.getKey()` and `right` to `max(right, e.getValue())`. Remove `e` from the `TreeMap`.
    *   Iterate using `tm.ceilingEntry(left)` to find intervals that start at or after the current `left`.
    *   While the `nextEntry` exists and its start (`nextEntry.getKey()`) is less than or equal to the current `right`, it means there's an overlap. Merge `nextEntry` into the current range: update `right` to `max(right, nextEntry.getValue())`. Remove `nextEntry` from the `TreeMap`.
    *   Finally, insert the merged range `(left, right)` into the `TreeMap`.
3.  **`queryRange(left, right)`**:
    *   Find the interval `e` that might contain `left` using `tm.floorEntry(left)`.
    *   If `e` exists, and its start (`e.getKey()`) is less than or equal to `left`, and its end (`e.getValue()`) is greater than or equal to `right`, then the query range is fully contained within `e`. Return `true`.
    *   Otherwise, return `false`.
4.  **`removeRange(left, right)`**:
    *   Find the interval `e` that might overlap with `left` from the left side using `tm.floorEntry(left)`.
    *   If `e` exists and its end (`e.getValue()`) is greater than or equal to `left`:
        *   Update `e`'s end to `left` (effectively trimming the right part of `e`). Remove `e` and re-insert it with the new end.
        *   If `e`'s original end (`e.getValue()`) was greater than `right`, it means a new interval `(right, e.getValue())` needs to be added. Insert this new interval.
    *   Iterate using `tm.ceilingEntry(left)` to find intervals that start at or after the current `left`.
    *   While the `nextEntry` exists and its start (`nextEntry.getKey()`) is less than or equal to `right`:
        *   If `nextEntry`'s end (`nextEntry.getValue()`) is greater than `right`, it means a new interval `(right, nextEntry.getValue())` needs to be added. Insert this new interval and break the loop.
        *   Remove `nextEntry` from the `TreeMap` (as it's fully covered by the removal range).

## Concept to Remember
*   **Sorted Data Structures**: `TreeMap` (or `TreeSet`) is crucial for maintaining ordered intervals and efficient searching.
*   **Interval Merging/Splitting**: The core logic involves correctly merging overlapping intervals during additions and splitting/shortening intervals during removals.
*   **Edge Cases**: Handling intervals that touch, are fully contained, or partially overlap requires careful boundary checks.
*   **Disjoint Intervals**: The `TreeMap` implicitly stores disjoint intervals by merging them.

## Common Mistakes
*   **Incorrectly handling boundary conditions**: Not properly checking if intervals touch or overlap at their endpoints.
*   **Failing to merge all overlapping intervals**: In `addRange`, not iterating through all subsequent overlapping intervals.
*   **Incorrectly splitting intervals in `removeRange`**: Not creating the correct new interval when a removal splits an existing one.
*   **Modifying `TreeMap` while iterating**: This can lead to `ConcurrentModificationException` or incorrect behavior. It's safer to collect entries to be removed/modified and then perform the operations.
*   **Not handling empty ranges or invalid inputs**: `right <= left` should be considered.

## Complexity Analysis
*   **Time**:
    *   `addRange`: O(log N) for `floorEntry`/`ceilingEntry` and `put`/`remove`. In the worst case, we might iterate through a few overlapping intervals, but since intervals are disjoint and merged, the number of intervals removed/merged is amortized. So, effectively O(log N).
    *   `queryRange`: O(log N) due to `floorEntry`.
    *   `removeRange`: O(log N) for `floorEntry`/`ceilingEntry` and `put`/`remove`. Similar to `addRange`, the iteration over overlapping intervals is amortized. So, effectively O(log N).
    *   N is the number of disjoint intervals in the `TreeMap`.
*   **Space**: O(N) to store the disjoint intervals in the `TreeMap`.

## Commented Code
```java
import java.util.TreeMap;
import java.util.Map;

class RangeModule {
    // TreeMap to store disjoint intervals.
    // Key: start of the interval, Value: end of the interval.
    // Intervals are stored in sorted order by their start points.
    TreeMap<Integer, Integer> tm;

    // Constructor initializes an empty TreeMap.
    public RangeModule() {
        this.tm = new TreeMap<>();
    }

    // Adds a range [left, right) to the collection.
    public void addRange(int left, int right) {
        // If the range is invalid (empty or negative length), do nothing.
        if (right <= left) return;

        // Find the entry that might overlap with 'left' from the left side.
        // floorEntry(key) returns a key-value mapping associated with the greatest key less than or equal to the given key, or null if there is no such key.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // Check if the found entry 'e' overlaps with the new range from the left.
        // e != null: an entry exists before or at 'left'.
        // e.getValue() >= left: the end of the existing interval is at or after 'left', meaning overlap.
        if (e != null && e.getValue() >= left) {
            // Merge the left side:
            // Remove the overlapping entry from the TreeMap.
            tm.remove(e.getKey());
            // Update 'left' to the start of the merged interval.
            left = e.getKey();
            // Update 'right' to be the maximum of the current 'right' and the end of the merged interval.
            right = Math.max(right, e.getValue());
        }

        // Now, merge any overlapping intervals on the right side.
        // We use a loop because multiple consecutive intervals might overlap.
        while (true) {
            // Find the next entry that starts at or after the current 'left'.
            // ceilingEntry(key) returns a key-value mapping associated with the least key greater than or equal to the given key, or null if there is no such key.
            Map.Entry<Integer, Integer> nextEntry = tm.ceilingEntry(left);

            // If there's no next entry, or the next entry starts after the current 'right', we've merged all overlaps.
            if (nextEntry == null || nextEntry.getKey() > right) {
                break; // Exit the loop.
            }

            // If there's an overlap (nextEntry.getKey() <= right):
            // Remove the overlapping entry from the TreeMap.
            tm.remove(nextEntry.getKey());
            // Update 'right' to be the maximum of the current 'right' and the end of the overlapping interval.
            right = Math.max(right, nextEntry.getValue());
        }

        // After merging all overlapping intervals, put the final merged range into the TreeMap.
        tm.put(left, right);
    }

    // Queries if the range [left, right) is entirely covered by existing intervals.
    public boolean queryRange(int left, int right) {
        // Find the entry that might contain 'left'.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // Check if an entry exists and if it fully covers the query range.
        // e != null: an interval exists before or at 'left'.
        // e.getKey() <= left: the start of the interval is at or before 'left'.
        // e.getValue() >= right: the end of the interval is at or after 'right'.
        return e != null && e.getKey() <= left && e.getValue() >= right;
    }

    // Removes the range [left, right) from the collection.
    public void removeRange(int left, int right) {
        // If the range to remove is invalid, do nothing.
        if (right <= left) return;

        // Find the entry that might overlap with 'left' from the left side.
        Map.Entry<Integer, Integer> e = tm.floorEntry(left);

        // Handle potential overlap on the left side of the removal range.
        // e != null: an entry exists before or at 'left'.
        // e.getValue() >= left: the end of the existing interval is at or after 'left', meaning overlap.
        if (e != null && e.getValue() >= left) {
            // If the existing interval extends beyond 'left', we need to adjust it.
            // Update the end of the existing interval to 'left'.
            // We remove and re-put to ensure the TreeMap is updated correctly.
            tm.put(e.getKey(), left);

            // If the original end of 'e' was beyond 'right', a new interval needs to be created.
            // e.getValue() > right: the original interval extended past the removal range.
            if (e.getValue() > right) {
                // Add a new interval starting from 'right' to the original end of 'e'.
                tm.put(right, e.getValue());
            }
        }

        // Now, handle intervals that are fully or partially within the removal range on the right side.
        // We use a loop because multiple consecutive intervals might be affected.
        while (true) {
            // Find the next entry that starts at or after the current 'left'.
            Map.Entry<Integer, Integer> nextEntry = tm.ceilingEntry(left);

            // If there's no next entry, or the next entry starts after 'right', we're done.
            if (nextEntry == null || right < nextEntry.getKey()) {
                break; // Exit the loop.
            }

            // If the next entry starts before or at 'right', it's affected by the removal.
            // Remove the overlapping entry from the TreeMap.
            tm.remove(nextEntry.getKey());

            // If the removed interval extended beyond 'right', a new interval needs to be created.
            // nextEntry.getValue() > right: the original interval extended past the removal range.
            if (nextEntry.getValue() > right) {
                // Add a new interval starting from 'right' to the original end of 'nextEntry'.
                tm.put(right, nextEntry.getValue());
                // Since we've handled the split and created a new interval, we can break.
                break;
            }
        }
    }
}
```

## Interview Tips
*   **Explain `TreeMap` usage**: Clearly articulate why `TreeMap` is chosen (sorted keys, efficient `floorEntry`/`ceilingEntry`).
*   **Walk through examples**: Use the provided examples or create your own to demonstrate how `addRange`, `queryRange`, and `removeRange` work step-by-step.
*   **Focus on edge cases**: Discuss how you handle intervals that touch, are fully contained, or partially overlap during additions and removals.
*   **Discuss complexity**: Be prepared to explain the time and space complexity of each operation and justify it based on the `TreeMap` operations.
*   **Clarify interval notation**: Ensure you and the interviewer are on the same page regarding whether ranges are inclusive/exclusive (e.g., `[left, right]` vs. `[left, right)`). The solution uses `[left, right)`.

## Revision Checklist
- [ ] Understand the problem: managing disjoint intervals.
- [ ] Choose appropriate data structure: `TreeMap` for sorted intervals.
- [ ] Implement `addRange`: handle merging from left and right.
- [ ] Implement `queryRange`: check for full containment.
- [ ] Implement `removeRange`: handle splitting and trimming.
- [ ] Consider edge cases: touching intervals, full containment, partial overlap.
- [ ] Analyze time and space complexity.
- [ ] Practice explaining the logic and code.

## Similar Problems
*   Merge Intervals (LeetCode 56)
*   Insert Interval (LeetCode 57)
*   Employee Free Time (LeetCode 759)
*   My Calendar I, II, III (LeetCode 729, 731, 732)

## Tags
`TreeMap` `Interval` `Data Structure` `Algorithm`
