# Data Stream As Disjoint Intervals

**Difficulty:** Hard  
**Language:** Java  
**Tags:** `Hash Table` `Binary Search` `Union-Find` `Design` `Data Stream` `Ordered Set`  
**Time:** O(log N)  
**Space:** O(N)

---

## Solution (java)

```java
class SummaryRanges {
    List<int[]> intervals;
    public SummaryRanges() {
        intervals = new ArrayList<>();
    }
    public void addNum(int target) {
        if(intervals.isEmpty()){intervals.add(new int[]{target,target}); return;}
        int low =0, high=intervals.size()-1;
        int mid = low + (high-low)/2;
        while(low <= high){
            mid = low + (high-low)/2;
            int start = intervals.get(mid)[0], end = intervals.get(mid)[1];
            if(target < start) high = mid-1;
            else if(target > end) low = mid+1;
            else return;
        }
        
        int left = low-1, right = low;
        
        boolean mergeLeft = left >= 0 && intervals.get(left)[1] + 1 == target;
        boolean mergeRight = right <= intervals.size()-1 && intervals.get(right)[0] - 1 == target;
        
        if(mergeLeft && mergeRight){
            intervals.get(left)[1] = intervals.get(right)[1];
            intervals.remove(right);
        } 
        else if (mergeLeft) intervals.get(left)[1] = target;
        else if (mergeRight) intervals.get(right)[0] = target;
        else intervals.add(low, new int[]{target,target});
        
        // <1,2,3,4,5,6>
        
        // 12 on 4th position
        
    }
    
    public int[][] getIntervals() {
        return intervals.toArray(new int[0][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
         //    addNum(312) => 312 313 314 315
        //   [[312,315]]
           // 9 8 5 
        // [9],[8],[5]
    //    getIntervals()
    //   [ 
    //     [1,1]
    //     [3,3]
    //     [4,10]
    //     [100,101]
    //     [200,250]
    // ->
    //     [500,501] low mid high
    //     [700,901] high
    //    ]
```

---

---
## Quick Revision
The problem asks to maintain a collection of disjoint intervals from a stream of numbers. We need to add numbers and retrieve the current set of disjoint intervals.

The solution uses a sorted list of intervals and binary search to efficiently find the insertion point and check for merging opportunities.

## Intuition
The core idea is that if we keep the intervals sorted, we can quickly determine where a new number `target` fits. If `target` is adjacent to an existing interval's start or end, we can merge them. If it's not adjacent to any, it forms a new, single-element interval. Binary search is the natural choice for finding the correct position in a sorted list.

## Algorithm
1. Initialize an empty list `intervals` to store disjoint intervals, where each interval is represented as `[start, end]`.
2. When `addNum(target)` is called:
    a. If `intervals` is empty, add `[target, target]` as the first interval and return.
    b. Perform a binary search on `intervals` to find the potential insertion point for `target`. The binary search should aim to find an interval `[start, end]` such that `start <= target <= end`.
    c. If `target` falls within an existing interval (i.e., `start <= target <= end`), do nothing as it's already covered.
    d. If `target` is not within any existing interval, determine if it can merge with the interval immediately before the insertion point (`left`) or immediately after (`right`).
        i. `mergeLeft` is true if `left` is a valid index and `intervals.get(left)[1] + 1 == target`.
        ii. `mergeRight` is true if `right` is a valid index and `intervals.get(right)[0] - 1 == target`.
    e. Handle the merging cases:
        i. If `mergeLeft` and `mergeRight` are both true, merge the `left` and `right` intervals by updating the end of the `left` interval to the end of the `right` interval, and then remove the `right` interval.
        ii. If only `mergeLeft` is true, extend the `left` interval by setting its end to `target`.
        iii. If only `mergeRight` is true, extend the `right` interval by setting its start to `target`.
        iv. If neither `mergeLeft` nor `mergeRight` is true, insert a new interval `[target, target]` at the `low` index (which is the insertion point found by binary search).
3. When `getIntervals()` is called, convert the `intervals` list into a 2D integer array and return it.

## Concept to Remember
*   **Sorted Data Structures:** Maintaining data in a sorted order is crucial for efficient searching and merging.
*   **Binary Search:** Essential for finding the correct position of a new element in a sorted list in logarithmic time.
*   **Interval Merging:** The logic for combining overlapping or adjacent intervals is a common pattern.
*   **Edge Cases:** Handling empty lists and boundary conditions for merging is important.

## Common Mistakes
*   **Incorrect Binary Search Implementation:** Off-by-one errors or incorrect loop conditions in binary search can lead to wrong insertion points or missed intervals.
*   **Flawed Merging Logic:** Not correctly identifying all mergeable conditions (e.g., only checking one side, or not handling the case where both sides merge) can result in incorrect interval sets.
*   **Modifying List During Iteration:** Removing elements from a list while iterating or using indices that become invalid after removal can cause `IndexOutOfBoundsException` or incorrect behavior. The provided solution correctly uses `remove(index)` after determining the `right` index.
*   **Not Handling Empty List:** Forgetting to initialize or handle the first element addition when the `intervals` list is empty.
*   **Integer Overflow:** While not a major concern with typical LeetCode constraints, in real-world scenarios, be mindful of potential overflows if interval boundaries can be very large.

## Complexity Analysis
- Time: O(log N) for `addNum` - The dominant operation is the binary search to find the insertion point. Merging or inserting takes constant time after the search. `getIntervals` takes O(N) to convert the list to an array, where N is the number of disjoint intervals.
- Space: O(N) - To store the N disjoint intervals.

## Commented Code
```java
import java.util.ArrayList; // Import the ArrayList class to use dynamic arrays.
import java.util.List; // Import the List interface.

class SummaryRanges {
    List<int[]> intervals; // Declare a list to store the disjoint intervals. Each interval is an int array of size 2: [start, end].

    public SummaryRanges() {
        intervals = new ArrayList<>(); // Initialize the list of intervals as an empty ArrayList.
    }

    public void addNum(int target) {
        // If the intervals list is empty, this is the first number.
        if (intervals.isEmpty()) {
            intervals.add(new int[]{target, target}); // Add the number as a new interval [target, target].
            return; // Exit the method.
        }

        // Binary search to find the correct position for 'target' or an existing interval it belongs to.
        int low = 0; // Initialize the lower bound of the search range.
        int high = intervals.size() - 1; // Initialize the upper bound of the search range.
        int mid; // Declare a variable for the middle index.

        // Standard binary search loop.
        while (low <= high) {
            mid = low + (high - low) / 2; // Calculate the middle index to avoid potential integer overflow.
            int start = intervals.get(mid)[0]; // Get the start of the current interval.
            int end = intervals.get(mid)[1]; // Get the end of the current interval.

            if (target < start) {
                // If target is less than the start of the current interval, search in the left half.
                high = mid - 1;
            } else if (target > end) {
                // If target is greater than the end of the current interval, search in the right half.
                low = mid + 1;
            } else {
                // If target is within the current interval [start, end], it's already covered.
                return; // Do nothing and exit.
            }
        }

        // After binary search, 'low' is the index where 'target' would be inserted if it doesn't merge.
        // 'low - 1' is the index of the interval potentially to the left of 'target'.
        // 'low' is the index of the interval potentially to the right of 'target'.
        int left = low - 1; // Index of the interval immediately before the insertion point.
        int right = low; // Index of the interval immediately after the insertion point.

        // Check if 'target' can merge with the interval to its left.
        // 'left >= 0' ensures 'left' is a valid index.
        // 'intervals.get(left)[1] + 1 == target' checks if 'target' is exactly one greater than the end of the left interval.
        boolean mergeLeft = left >= 0 && intervals.get(left)[1] + 1 == target;

        // Check if 'target' can merge with the interval to its right.
        // 'right <= intervals.size() - 1' ensures 'right' is a valid index.
        // 'intervals.get(right)[0] - 1 == target' checks if 'target' is exactly one less than the start of the right interval.
        boolean mergeRight = right <= intervals.size() - 1 && intervals.get(right)[0] - 1 == target;

        if (mergeLeft && mergeRight) {
            // Case 1: 'target' can merge with both the left and right intervals.
            // Merge them by extending the left interval's end to the right interval's end.
            intervals.get(left)[1] = intervals.get(right)[1];
            // Remove the right interval as it's now merged into the left one.
            intervals.remove(right);
        } else if (mergeLeft) {
            // Case 2: 'target' can only merge with the left interval.
            // Extend the left interval's end to include 'target'.
            intervals.get(left)[1] = target;
        } else if (mergeRight) {
            // Case 3: 'target' can only merge with the right interval.
            // Extend the right interval's start to include 'target'.
            intervals.get(right)[0] = target;
        } else {
            // Case 4: 'target' does not merge with any existing interval.
            // Insert a new interval [target, target] at the 'low' index.
            intervals.add(low, new int[]{target, target});
        }
    }

    public int[][] getIntervals() {
        // Convert the list of intervals into a 2D integer array.
        // 'new int[0][]' is used as a type hint for the toArray method.
        return intervals.toArray(new int[0][]);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */
```

## Interview Tips
*   **Explain the Binary Search:** Clearly articulate how binary search is used to find the insertion point and why it's efficient.
*   **Walk Through Merging Scenarios:** Verbally explain each of the four merging/insertion cases (`mergeLeft && mergeRight`, `mergeLeft`, `mergeRight`, neither) with examples.
*   **Data Structure Choice:** Justify why a sorted `List` (like `ArrayList` in Java) is a good choice, and discuss potential alternatives (e.g., `TreeMap` for key-value pairs, but here we need ordered intervals).
*   **Edge Cases:** Be prepared to discuss how you handle an empty stream, single-element intervals, and merging at the boundaries of the list.

## Revision Checklist
- [ ] Understand the problem: maintain disjoint intervals from a stream.
- [ ] Implemented `addNum` with binary search.
- [ ] Correctly handled the empty `intervals` list.
- [ ] Implemented logic for merging with the left interval.
- [ ] Implemented logic for merging with the right interval.
- [ ] Implemented logic for merging with both left and right intervals.
- [ ] Implemented logic for inserting a new, non-merging interval.
- [ ] Implemented `getIntervals` to return the correct format.
- [ ] Analyzed time and space complexity.
- [ ] Considered common mistakes and edge cases.

## Similar Problems
*   Merge Intervals
*   Insert Interval
*   Non-overlapping Intervals
*   My Calendar I/II/III

## Tags
`Array` `Binary Search` `List` `Data Stream`
